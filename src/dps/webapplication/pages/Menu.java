package dps.webapplication.pages;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String name;
    private String lan;
    private List<MenuItem> items = new ArrayList<>();

    Menu(String name, String lan) {
        this.name = name;
        this.lan = lan;
    }

    public void addItem(String name, Page page) {
        items.add(new MenuItem(name,page));
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public String getLan() {
        return lan;
    }

    public static class MenuItem {
        String name;
        Page page;

        public MenuItem(String name, Page page) {
            this.name = name;
            this.page = page;
        }

        public String getName() {
            return name;
        }

        public Page getPage() {
            return page;
        }
    }
}
