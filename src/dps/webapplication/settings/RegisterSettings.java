package dps.webapplication.settings;

import dps.commons.startup.Startup;
import dps.webapplication.configuration.Configuration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Startup
@ApplicationScoped
public class RegisterSettings {

    @Inject
    Configuration configuration;

    @PostConstruct
    public void init() {
        configuration.registerConfig("application-settings",Settings.class);

    }

    @Produces @Dependent @Named("Settings")
    public Settings get()
    {
        return configuration.get(Settings.class);
    }

}
