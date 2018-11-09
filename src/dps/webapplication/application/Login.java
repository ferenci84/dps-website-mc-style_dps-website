package dps.webapplication.application;

import dps.webapplication.authentication.CurrentAuthenticationManager;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("login")
public class Login {

    @Inject
    CurrentAuthenticationManager authenticationManager;

    @POST
    @Path("/login")
    public Response loginAndRedirect(@FormParam("username") String username, @FormParam("password") String password, @FormParam("successUrl") String successUrl, @FormParam("failUrl") String failUrl)
    {
        if (authenticationManager.login(username,password)) {
            return Response.temporaryRedirect(URI.create(successUrl)).build();
        } else {
            return Response.temporaryRedirect(URI.create(failUrl)).build();
        }
    }

}
