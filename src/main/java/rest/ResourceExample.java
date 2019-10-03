package rest;

import dto.MovieInfo;
import facades.FacadeExample;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

//Todo Remove or change relevant parts before ACTUAL use
@OpenAPIDefinition(
        info = @Info(
                title = "Simple Movie API",
                version = "0.4",
                description = "Simple API to get info about movies.",
                contact = @Contact(name = "Lars Mortensen", email = "lam@cphbusiness.dk")
        ),
        tags = {
            @Tag(name = "movie", description = "API related to Movie Info")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/startcodeoas"
            ),
            @Server(
                    description = "Server API",
                    url = "http://mydroplet"
            )

        }
)
@Path("movieinfo")
public class ResourceExample {

//    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
//    private static final FacadeExample FACADE = FacadeExample.getFacadeExample(EMF);
    //  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Movie info by ID",
            tags = {"movie"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieInfo.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Movie"),
                @ApiResponse(responseCode = "404", description = "Movie not found")})
    public MovieInfo getMovieInfo(@PathParam("id") int id) {
        if (id == 13) {
            throw new WebApplicationException("Requested movie not found.", 400);
        }
        return new MovieInfo("Forest Gump", "?", 2002, 10, null);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Movie info by ID", tags = {"movie"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created Movie"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public MovieInfo createMovie(MovieInfo movie) {
        if (movie.getTitle() == null || movie.getDirector() == null || movie.getYear() == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        movie.setId(464);
        return movie;
    }

}
