package dps.webapplication.pages;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Named("pages")
@ApplicationScoped
public class Pages {

    private Map<String,Map<String,Page>> pages = new HashMap<>();
    private Map<String,Map<String,Menu>> menus = new HashMap<>();
    private Map<String, Page> pagesByLink = new HashMap<>();

    public void registerPage(Page page) {
        Map<String, Page> lanPages = pages.computeIfAbsent(page.getLan(), k -> new HashMap<>());
        lanPages.put(page.getName(),page);
        pagesByLink.put(page.getLink(),page);
    }

    public void registerMenu(Menu menu) {
        Map<String, Menu> lanMenu = menus.computeIfAbsent(menu.getLan(), k -> new HashMap<>());
        lanMenu.put(menu.getName(),menu);
    }

    public Page getPage(String name, String lan) {
        Map<String, Page> lanPages = pages.get(lan);
        if (lanPages == null) return null;
        return lanPages.get(name);
    }

    public Page getPageByLink(String link) {
        return pagesByLink.get(link);
    }

    public Menu getMenu(String name, String lan) {
        Map<String, Menu> lanMenu = menus.get(lan);
        if (lanMenu == null) return null;
        return lanMenu.get(name);
    }


}
