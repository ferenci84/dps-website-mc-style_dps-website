package dps.webapplication.application;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
public class Messages {

    @Inject
    dps.webapplication.messages.Messages messages;

    @GET
    @Path("/")
    public List<dps.webapplication.messages.Messages.Message> getMessages()
    {
        return messages.getMessages();
    }

}
