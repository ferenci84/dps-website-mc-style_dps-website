package dps.webapplication.mail;

import dps.commons.startup.Startup;
import dps.webapplication.configuration.Configuration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@Startup
@ApplicationScoped
public class RegisterConfiguration {

    @Inject
    Configuration configuration;

    @PostConstruct
    public void init() {
        configuration.registerConfig("email-settings",MailSettings.class);
    }

    @Produces @Dependent
    public MailSettings get()
    {
        return configuration.get(MailSettings.class);
    }

}
