package dps.webapplication.application.admin;


import dps.webapplication.application.providers.annotations.AllowedRoles;
import dps.webapplication.application.providers.annotations.NotAuthorizedRedirect;
import dps.webapplication.configuration.Settings;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("admin")
@AllowedRoles("admin")
public class AdminIndex {

    @Inject
    Settings settings;

    @GET
    @Path("/")
    @NotAuthorizedRedirect("admin/auth/login")
    public Response index()
    {
        try {
            return Response.temporaryRedirect(new URI(settings.getHost()+settings.getRoot()+"admin/users")).build();
        } catch (URISyntaxException e) {
            throw new WebApplicationException("invalid redirection url");
        }
    }


}
