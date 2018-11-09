package dps.webapplication.locale;

import dps.webapplication.i18n.CurrentLocale;

import javax.inject.Inject;
import javax.inject.Named;

@Named("locale")
public class Locale {
    @Inject
    CurrentLocale currentLocale;

    public String getTag() {
        return currentLocale.getLocale().toLanguageTag();
    }

    public Boolean is(String tag) {
        return tag.equals(currentLocale.getLocale().toLanguageTag());
    }

}
