package plt.api;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/hello")
public class HelloEndpoint {

    @GET
    @Secured("ROLE_USER")
    public String message() {
        return "Hello";
    }
}
