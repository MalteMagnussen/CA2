/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cors;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/*
How to set the CORS headers in your Java backend
This Request-filter will handle requests of the type OPTIONS

It Will abort the request, so none of your REST-Endpoint code is activated.
The response will however still go through the chain of possible Response-filters

How to set the right headers using JAX-RX Filters
You can set the required headers for each REST-endpoint, but for a large REST-API that would be extremely cumbersome

JAX-RS provides a concept Filters which can be used when you want to modify any request or response parameters like headers.
In our case we can use it to set the required CORS headers on all request
 */
@Provider  //This will ensure that the filter is used "automatically"
@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger(CorsRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if (requestCtx.getRequest().getMethod().equals("OPTIONS")) {
            log.info("HTTP Method (OPTIONS) - Detected!");
            // Just send a OK response back to the browser.
            // The response goes through the chain of applicable response filters.
            requestCtx.abortWith(Response.status(Response.Status.OK).build());
        }
    }
}
