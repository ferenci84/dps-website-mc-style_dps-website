package dps.webapplication.pages;

import dps.commons.startup.Startup;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Startup
public class DPSPages {

    @Inject
    Pages pages;

    @PostConstruct
    public void init()
    {

        pages.registerPage(new DPSPage("index","en",""));
        pages.registerPage(new DPSPage("services","en","en/services"));
        pages.registerPage(new DPSPage("about","en","en/about"));
        pages.registerPage(new DPSPage("process","en","en/process"));
        pages.registerPage(new DPSPage("faq","en","en/faq"));
        pages.registerPage(new DPSPage("consultation","en","en/consultation"));
        pages.registerPage(new DPSPage("contact","en","en/contact"));
        pages.registerPage(new DPSPage("privacy","en","en/privacy"));
        pages.registerPage(new DPSPage("terms","en","en/terms"));

        pages.registerPage(new DPSPage("index","hu","hu"));
        pages.registerPage(new DPSPage("services","hu","hu/services"));
        pages.registerPage(new DPSPage("about","hu","hu/about"));
        pages.registerPage(new DPSPage("process","hu","hu/process"));
        pages.registerPage(new DPSPage("faq","hu","hu/faq"));
        pages.registerPage(new DPSPage("consultation","hu","hu/consultation"));
        pages.registerPage(new DPSPage("contact","hu","hu/contact"));
        pages.registerPage(new DPSPage("privacy","hu","hu/privacy"));
        pages.registerPage(new DPSPage("terms","hu","hu/terms"));

        Menu menu = new Menu("top","en");
        menu.addItem("Home",pages.getPage("index","en"));
        menu.addItem("About Us",pages.getPage("about","en"));
        menu.addItem("Services",pages.getPage("services","en"));
        menu.addItem("FAQ",pages.getPage("faq","en"));
        menu.addItem("Contact",pages.getPage("contact","en"));
        pages.registerMenu(menu);

        menu = new Menu("bottom","en");
        menu.addItem("Home",pages.getPage("index","en"));
        menu.addItem("About Us",pages.getPage("about","en"));
        menu.addItem("Services",pages.getPage("services","en"));
        menu.addItem("FAQ",pages.getPage("faq","en"));
        menu.addItem("Contact",pages.getPage("contact","en"));
        menu.addItem("Privacy",pages.getPage("privacy","en"));
        menu.addItem("Terms",pages.getPage("terms","en"));
        pages.registerMenu(menu);

        menu = new Menu("top","hu");
        menu.addItem("Föoldal",pages.getPage("index","hu"));
        menu.addItem("Rólunk",pages.getPage("about","hu"));
        menu.addItem("Szolgáltatások",pages.getPage("services","hu"));
        menu.addItem("Gyik",pages.getPage("faq","hu"));
        menu.addItem("Kontakt",pages.getPage("contact","hu"));
        pages.registerMenu(menu);

        menu = new Menu("bottom","hu");
        menu.addItem("Föoldal",pages.getPage("index","hu"));
        menu.addItem("Rólunk",pages.getPage("about","hu"));
        menu.addItem("Szolgáltatások",pages.getPage("services","hu"));
        menu.addItem("Gyik",pages.getPage("faq","hu"));
        menu.addItem("Kontakt",pages.getPage("contact","hu"));
        menu.addItem("Adatkezelés",pages.getPage("privacy","hu"));
        menu.addItem("Feltételek",pages.getPage("terms","hu"));
        pages.registerMenu(menu);

    }
}
