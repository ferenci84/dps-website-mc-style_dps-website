package dps.webapplication.application.admin;

import dps.webapplication.application.providers.Redirect;
import dps.webapplication.application.providers.View;
import dps.webapplication.authentication.CurrentAuthenticationManager;
import dps.webapplication.configuration.Settings;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("admin/auth")
public class Authentication {

    @Inject
    Settings settings;

    @Inject
    CurrentAuthenticationManager authenticationManager;


    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    public View login()
    {
        return new View("/WEB-INF/admin/login.jsp");
    }

    @POST
    @Path("/login")
    public Redirect processLogin(@FormParam("username") String username, @FormParam("password") String password)
    {
        //TODO: redirect to requested resource
        if (authenticationManager.login(username,password)) {
            return new Redirect(settings.getHost()+settings.getRoot()+"admin/users");
        } else {
            return new Redirect(settings.getHost()+settings.getRoot()+"admin/auth/login");
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public void processLogin(Credentials credentials)
    {
        if (authenticationManager.login(credentials.getUsername(),credentials.getPassword())) {
            return;
        } else {
            throw new NotAuthorizedException("login credentials not correct");
        }
    }


    public static class Credentials {
        private String username;
        private String password;

        public Credentials() {}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @GET
    @Path("/logout")
    public Redirect logout()
    {
        authenticationManager.logout();
        return new Redirect(settings.getHost()+settings.getRoot());
    }

}
