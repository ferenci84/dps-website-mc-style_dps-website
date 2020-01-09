package dps.webapplication.application;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import dps.webapplication.application.providers.View;
import dps.webapplication.configuration.Settings;
import dps.webapplication.i18n.CurrentLocale;
import dps.webapplication.i18n.SessionLocale;
import dps.webapplication.pages.Page;
import dps.webapplication.pages.Pages;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.NavigableMap;

@Path("/")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
public class Frontend {

    @Inject
    Settings settings;

    Boolean development = false;

    @Context
    HttpServletRequest request;

    @Inject
    CurrentLocale locale;

    @Inject
    SessionLocale sessionLocale;

    @Resource(
            name = "applicationName"
    )
    String applicationName;

/*
    @GET
    @Path("index")
    public Object index() throws URISyntaxException {
        setLocale("en");
        String lan = locale.getLocale().toLanguageTag();
        request.setAttribute("dev",development);
        return new View("/WEB-INF/" + lan + "/index.jsp");
    }
*/
    @Inject
    Pages pages;

    @GET
    @Path("/")
    public Object index() throws URISyntaxException {
        return this.page("");
    }

    @GET
    @Path("{link:(.*)}")
    public View page(@PathParam("link") String link) {

        Page page = pages.getPageByLink(link);
        if (page == null) throw new NotFoundException();
        setLocale(page.getLan());
        request.setAttribute("dev",development);
        request.setAttribute("page",page);
        return new View(page.getView());
    }

    /*
    @GET
    @Path("{lan:(hu)}")
    public View lanIndex(@PathParam("lan") String lan) {
        setLocale(lan);
        request.setAttribute("dev",development);
        return new View("/WEB-INF/" + lan + "/index.jsp");
    }

    @GET
    @Path("{lan:(en|hu)}/{pageLink}")
    public View lanPage(@PathParam("lan") String lan, @PathParam("pageLink") String pageLink) {
        setLocale(lan);
        String page = pages.getPage(lan,pageLink);
        request.setAttribute("page",page);
        request.setAttribute("dev",development);
        return new View("/WEB-INF/" + lan + "/" + page + ".jsp");
    }
    */

    public void setLocale(String lan)
    {
        if (!locale.getLocale().toLanguageTag().equals(lan)) {
            sessionLocale.setLocale(lan);
        }
    }

}
