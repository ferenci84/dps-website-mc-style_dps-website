package dps.webapplication.mail;

import javax.enterprise.inject.Vetoed;
import javax.xml.bind.annotation.XmlRootElement;

/*@ApplicationScoped
@Startup*/
@XmlRootElement
@Vetoed
public class MailSettings/* extends XmlConfiguration*/ {

    /*@Resource(
            name = "emailsettings"
    )
    @XmlTransient
    String settingsFile;*/

    private String host;
    private String port;
    private String username;
    private String password;
    private String contactEmail;
    private String systemEmail;
    private String recapatchaSecret;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }

    public String getRecapatchaSecret() {
        return recapatchaSecret;
    }

    public void setRecapatchaSecret(String recapatchaSecret) {
        this.recapatchaSecret = recapatchaSecret;
    }

    /*@Override
    protected String getSettingsFile() {
        return settingsFile;
    }*/

    @Override
    public String toString() {
        return "MailSettings{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}