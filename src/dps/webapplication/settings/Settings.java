package dps.webapplication.settings;

import javax.enterprise.inject.Vetoed;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;

@XmlRootElement
@Vetoed
public class Settings implements dps.webapplication.configuration.Settings {

    /*@Resource(name="settingsfile")
    String settingsFile;
    protected String getSettingsFile() {
        return settingsFile;
    }

    @PostConstruct
    void init() {
        if (loglevels != null) {
            Loggers loggers = Loggers.getInstance();
            for (Map.Entry<String, String> entry : loglevels.entrySet()) {
                if (entry.getKey() == null) {
                    throw new RuntimeException("invalid loglevel entry: " + entry);
                }
                Logger logger = loggers.getLogger(entry.getKey());
                logger.setLevel(Level.parse(entry.getValue()));
                logInfo(logger.getName() + " loglevel: " + logger.getLevel());
            }
        } else {
            logInfo("No loglevel entry in settings file");
        }
    }*/

    HashMap<String,String> loglevels;
    String host;
    String root;
    String locale;
    Boolean development;

    public String getHost() {
        return host;
    }

    @XmlElement
    public void setHost(String host) {
        this.host = host;
    }


    public String getRoot() {
        return root;
    }

    @XmlElement
    public void setRoot(String root) {
        this.root = root;
    }

    public String getLocale() {
        return locale;
    }

    @XmlElement
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public HashMap<String, String> getLoglevels() {
        return loglevels;
    }

    @XmlElement
    public void setLoglevels(HashMap<String, String> loglevels) {
        this.loglevels = loglevels;
    }

    @XmlElement
    public Boolean getDevelopment() {
        return development;
    }

    public void setDevelopment(Boolean development) {
        this.development = development;
    }
}
