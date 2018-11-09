package dps.webapplication.application;

import dps.logging.HasLogger;
import dps.webapplication.authentication.CurrentAuthenticationManager;
import dps.webapplication.configuration.Settings;
import dps.webapplication.i18n.CurrentLocale;
import dps.webapplication.mail.MailSettings;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("contact")
@ApplicationScoped
public class Contact implements HasLogger {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Inject
    MailSettings mailSettings;

    //@Inject UserManager userManager;

    Session session;

    @Inject CurrentLocale locale;

    @Inject Pages pages;


    @PostConstruct
    public void postConstruct()
    {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", mailSettings.getHost());
        prop.put("mail.smtp.port", mailSettings.getPort());

        session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSettings.getUsername(), mailSettings.getPassword());
            }
        });
    }

    public Boolean sendTextMail(String fromEmail, String toEmail, String subject, String body)
    {
        logInfo("Sending email " + subject + " from " + fromEmail + " to " + toEmail);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            logInfo("Message Sent");
            return true;
        } catch (MessagingException ex) {
            logWarning(ex.getMessage());
            return false;
        }

    }

    public Boolean sendHtmlMail(String fromEmail, String toEmail, String subject, String textBody, String htmlBody)
    {
        logInfo("Sending email " + subject + " from " + fromEmail + " to " + toEmail);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
            message.setSubject(subject);
            Multipart content = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(textBody,"UTF-8");
            content.addBodyPart(textPart);

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody,"text/html");
            content.addBodyPart(htmlPart);

            message.setContent(content);
            Transport.send(message);
            logInfo("Message Sent");
            return true;
        } catch (MessagingException ex) {
            logWarning(ex.getMessage());
            return false;
        }

    }


    @Inject Validator validator;

    @Inject
    CurrentAuthenticationManager authenticationManager;

    @Inject
    Settings settings;

    @POST
    @Path("/send")
    //@Consumes("multipart/form-data")
    public Response loginAndRedirect(@BeanParam ContactForm contactForm, @Encoded @FormParam("name") String name, @FormParam("g-recaptcha-response") String capatchaResponse, @Context HttpServletRequest request)
    {
        Set<ConstraintViolation<ContactForm>> violations = validator.validate(contactForm);
        for (ConstraintViolation<ContactForm> violation: violations) {
            System.out.println(violation.getMessage());
            throw new BadRequestException();
        }

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.google.com/recaptcha/api/siteverify");
        Form form = new Form();
        form.param("secret","6LfgWW0UAAAAAOEVffjgXpcSzpWrp0xfoW0xGh0E");
        form.param("response",capatchaResponse);
        form.param("remoteip",request.getRemoteAddr());
        Response response = target.request().post(Entity.form(form));

        JsonObject reCapatchaResponse = response.readEntity(JsonObject.class);
        Boolean success = reCapatchaResponse.getBoolean("success");
        if (!success) {
            System.out.println("Blocked by reCapatcha: "+reCapatchaResponse);
            throw new BadRequestException();
        }
        System.out.println("New Contact: "+contactForm);

        String subject = "Üzenet: " + contactForm.name;

        String message =
                "Feladó: "+contactForm.name+" <"+contactForm.email+">\n" +
                "Telefon: "+contactForm.phone+"\n" +
                "Cégnév: "+contactForm.company+"\n" +
                "Másolatot kér: "+("on".equals(contactForm.copyToEmail)?"igen":"nem")+"\n" +
                "Visszahívást kér: "+("on".equals(contactForm.callback)?"igen":"nem")+"\n" +
                "Üzenet:\n" +
                contactForm.message;

        executorService.submit(() -> {
            this.sendTextMail(mailSettings.getSystemEmail(),mailSettings.getContactEmail(),subject,message);
            if ("on".equals(contactForm.copyToEmail))
                this.sendTextMail(mailSettings.getContactEmail(),contactForm.email,"Üzenet másolata",message);
        });

        try {
            String lan = locale.getLocale().toLanguageTag();
            return Response.status(Response.Status.SEE_OTHER).location(new URI(settings.getHost()+settings.getRoot()+lan+"/"+pages.getLink(lan,"contact"))).build();
        } catch (URISyntaxException e) {
            throw new WebApplicationException();
        }
    }

    public static class ReCapatchaResponse {
        String success;
        Date challenge_ts;
        String hostname;
        @XmlElement(name = "error-codes")
        List<String> errorCodes;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public Date getChallenge_ts() {
            return challenge_ts;
        }

        public void setChallenge_ts(Date challenge_ts) {
            this.challenge_ts = challenge_ts;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public List<String> getErrorCodes() {
            return errorCodes;
        }

        public void setErrorCodes(List<String> errorCodes) {
            this.errorCodes = errorCodes;
        }

        @Override
        public String toString() {
            return "ReCapatchaResponse{" +
                    "success='" + success + '\'' +
                    ", challenge_ts=" + challenge_ts +
                    ", hostname='" + hostname + '\'' +
                    ", errorCodes=" + errorCodes +
                    '}';
        }
    }

    public static class ContactForm {

        @FormParam("name")
        String name;

        @FormParam("email")
        String email;

        @FormParam("phone")
        String phone;

        @FormParam("company")
        String company;

        @FormParam("subject")
        String subject;

        @FormParam("copy_to_email")
        String copyToEmail;

        @FormParam("callback")
        String callback;

        @NotNull
        @Size(min=5)
        @FormParam("message")
        String message;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCopyToEmail() {
            return copyToEmail;
        }

        public void setCopyToEmail(String copyToEmail) {
            this.copyToEmail = copyToEmail;
        }

        public String getCallback() {
            return callback;
        }

        public void setCallback(String callback) {
            this.callback = callback;
        }

        @Override
        public String toString() {
            return "ContactForm{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", subject='" + subject + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
