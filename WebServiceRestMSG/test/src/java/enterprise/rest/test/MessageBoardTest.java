package enterprise.rest.test;

import enterprise.messageboard.entities.Message;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageBoardTest {

    private WebTarget webTarget;
    
    public MessageBoardTest() {
        URI serverUri;
        try {
            if(System.getProperty("samples.javaee.serveruri") != null) {
                serverUri = new URI(System.getProperty("samples.javaee.serveruri"));
            } else {
                serverUri = new URI("http://localhost:8080");
            }
            Client client = ClientBuilder.newClient();
            webTarget = client.target(serverUri).path("message-board/app/messages");

        } catch(URISyntaxException e) {
            assertTrue(false);
        }
    }

    @Test public void testDeployed() {
        
        // test GET [app/messages]
        String result = webTarget.request().get(String.class);
        assertFalse(result.length() == 0);
    }

    @Test public void testPostGetDeleteMessage() {
        
        // test POST [app/messages]
        Response response = webTarget.request().post(Entity.entity("Hello World !!!", MediaType.TEXT_PLAIN), Response.class);
        assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
        
        // test GET [app/messages/{number}]
        URI location = response.getLocation();
        String resourceNum = location.getPath().substring(location.getPath().lastIndexOf("/")+1);
        Message message = webTarget.path(resourceNum).request(MediaType.APPLICATION_XML).get(Message.class);
        assertEquals("Hello World !!!", message.getMessage());
        
        // test DELETE [app/messages/{number}]
        response = webTarget.path(resourceNum).request().delete();
        assertNotSame(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}

