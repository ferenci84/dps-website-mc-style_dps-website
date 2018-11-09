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
        pages.registerPage(new DPSPage("products","en","en/products"));
        pages.registerPage(new DPSPage("privacy","en","en/privacy"));
        pages.registerPage(new DPSPage("contact","en","en/contact"));
        pages.registerPage(new DPSPage("terms","en","en/terms"));

        pages.registerPage(new DPSPage("index","hu","hu"));
        pages.registerPage(new DPSPage("services","hu","hu/szolgaltatasok"));
        pages.registerPage(new DPSPage("products","hu","hu/termekek"));
        pages.registerPage(new DPSPage("privacy","hu","hu/adatkezeles"));
        pages.registerPage(new DPSPage("contact","hu","hu/kapcsolat"));
        pages.registerPage(new DPSPage("terms","hu","hu/felhasznalasi-feltetelek"));

        Menu menu = new Menu("top","en");
        menu.addItem("Home",pages.getPage("index","en"));
        menu.addItem("Services",pages.getPage("services","en"));
        menu.addItem("Products",pages.getPage("products","en"));
        menu.addItem("Contact",pages.getPage("contact","en"));
        pages.registerMenu(menu);

        menu = new Menu("top","hu");
        menu.addItem("Főoldal",pages.getPage("index","hu"));
        menu.addItem("Szolgáltatások",pages.getPage("services","hu"));
        menu.addItem("Termékek",pages.getPage("products","hu"));
        menu.addItem("Kapcsolat",pages.getPage("contact","hu"));
        pages.registerMenu(menu);

    }
}
