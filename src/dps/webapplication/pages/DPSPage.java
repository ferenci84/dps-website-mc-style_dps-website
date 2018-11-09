package dps.webapplication.pages;

public class DPSPage implements Page {

    private String name;
    private String lan;
    private String link;
    public DPSPage(String name, String lan, String link) {
        this.name = name;
        this.lan = lan;
        this.link = link;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLan() {
        return lan;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getView() {
        return "/WEB-INF/"+lan+"/"+name+".jsp";
    }
}
