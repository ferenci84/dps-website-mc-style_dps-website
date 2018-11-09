package dps.webapplication.application;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named("Pages")
@ApplicationScoped
public class Pages {

    private Map<String, Map<String,String>> pageToLink = new HashMap<>();
    private Map<String, Map<String,String>> linkToPage = new HashMap<>();

    @PostConstruct
    public void init()
    {
        registerLanguage("en");
        registerLanguage("hu");

        registerPage("en","services","services");
        registerPage("hu","services","szolgaltatasok");

        registerPage("en","products","products");
        registerPage("hu","products","termekek");

        registerPage("en","contact","contact");
        registerPage("hu","contact","kapcsolat");

        registerPage("en","privacy","privacy");
        registerPage("hu","privacy","adatkezeles");

        registerPage("en","terms","terms");
        registerPage("hu","terms","felhasznalasi-feltetelek");

    }

    public void registerLanguage(String lan)
    {
        pageToLink.put(lan,new HashMap<>());
        linkToPage.put(lan,new HashMap<>());
    }

    public void registerPage(String lan, String page, String link)
    {
        pageToLink.get(lan).put(page,link);
        linkToPage.get(lan).put(link,page);
    }

    public String getPage(String lan, String link)
    {
        return linkToPage.get(lan).get(link);
    }

    public String getLink(String lan, String page)
    {
        return pageToLink.get(lan).get(page);
    }

}
