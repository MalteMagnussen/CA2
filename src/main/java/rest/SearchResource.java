package rest;

import com.google.gson.JsonObject;
import dto.HobbyDTO_IN;
import dto.HobbyDTO_OUT;
import dto.MovieInfo;
import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@OpenAPIDefinition(
        info = @Info(
                title = "CA2 API",
                version = "1.0",
                description = "API related to Cphbusiness 3rd semester CS assignment 'CA2'.",
                contact = @Contact(name = "Github Contributors", url = "https://github.com/MalteMagnussen/CA2/")
        ),
        tags = {
            @Tag(name = "General", description = "API related to CA2"),
            @Tag(name = "Persons", description = "CRUD-operations for Person"),
            @Tag(name = "Hobbies", description = "CRUD-operations for Hobby"),
            @Tag(name = "Movies", description = "Deprecated")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/startcodeoas"
            ),
            @Server(
                    description = "Server API",
                    url = "https://maltemagnussen.com/CA2/"
            )

        }
)
@Path("search")
public class SearchResource {
    //<editor-fold defaultstate="collapsed" desc="API recommended for assignment">
    @GET
    @Path("/phone")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get information about a person (address, hobbies etc) given a phone number",
            tags = {"General"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "The Requested Person"),
                @ApiResponse(responseCode = "404", description = "Person not found")})

    public PersonDTO_OUT getPersonInfoByPhone(@QueryParam("phone") long phone) {
        //    /api/search/phone?phone=<phone>
        //get from facade
        return new PersonDTO_OUT();
    }

    @GET
    @Path("/hobby")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons with a given hobby",
            tags = {"General"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "The Requested list of persons"),
                @ApiResponse(responseCode = "404", description = "No persons with that hobby found")})
    public List<PersonDTO_OUT> getPersonsByHobby(@QueryParam("hobby") String hobby) {
        //    /api/search/hobby?hobby=<hobby>
        //get from facade
        List<PersonDTO_OUT> returnList = new ArrayList();
        return returnList;
    }

    @GET
    @Path("/city")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons living in a given city (i.e. 2800 Lyngby)",
            tags = {"General"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "The Requested list of persons"),
                @ApiResponse(responseCode = "404", description = "No persons in that city found")})
    public List<PersonDTO_OUT> getPersonsByCity(@QueryParam("city") String city) {
        //    /api/search/city?city=<city>
        //example mentions both zip & city?
        //get from facade
        List<PersonDTO_OUT> returnList = new ArrayList();
        return returnList;
    }

    @GET
    @Path("/hobby/{hobby}/count")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get the count of people with a given hobby",
            tags = {"General"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "The Requested count of persons with that hoby"),
                @ApiResponse(responseCode = "404", description = "No hobbies like that found")})
    public Response getPersonsCountByHobby(@PathParam("hobby") String hobby) {
        //    /api/search/hobby/{hobby}/count
        //get from facade, use pathparam
        int value = 404;
        JsonObject dbMsg = new JsonObject();
        dbMsg.addProperty("Persons with request hobby", value);
        return Response.ok(dbMsg).build();
    }

    @GET
    @Path("/zip/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a list of all zip codes in Denmark",
            tags = {"General"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "The Requested count of persons with that hoby"),
                @ApiResponse(responseCode = "404", description = "No hobbies like that found")})
    public List<String> getAllZipCodes() {
        //    /api/search/zip
        //get from facade. Not sure if we want this to be string, object or ?
        List<String> returnList = new ArrayList();
        return returnList;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="API - Person CRUD">
    @GET
    @Path("person/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get person given a name", tags = {"Persons"},
            responses = {
                @ApiResponse(responseCode = "200", description = "List of persons with name"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })

    public List<PersonDTO_OUT> getPersonByName(@PathParam("name") String name) {
        if (name == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //Based on entity & DTO we might want first name + last name
        //get from facade, return
        List<PersonDTO_OUT> returnList = new ArrayList();
        return returnList;
    }

    @POST
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add new person", tags = {"Persons"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO_OUT addPerson(PersonDTO_IN person) {
        if (person == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //add through facade, return
        return new PersonDTO_OUT();
    }

    @PUT
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit existing person", tags = {"Persons"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The edited Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO_OUT editPerson(PersonDTO_IN person) {
        if (person == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //change through facade, return
        return new PersonDTO_OUT();
    }

    @DELETE
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete existing person", tags = {"Persons"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The deleted Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO_OUT deletePerson(PersonDTO_IN person) {
        if (person == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //delete through facade, return
        return new PersonDTO_OUT();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="API - Hobby CRUD">
    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get hobbies given a name", tags = {"Hobbies"},
            responses = {
                @ApiResponse(responseCode = "200", description = "List of hobbies with name"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })

    public List<HobbyDTO_OUT> getHobbyByName(@PathParam("hobby") String hobby) {
        if (hobby == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //Based on entity & DTO we might want first name + last name
        //get from facade, return
        List<HobbyDTO_OUT> returnList = new ArrayList();
        return returnList;
    }

    @POST
    @Path("hobby")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add new hobby", tags = {"Hobbies"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created Hobby"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public HobbyDTO_OUT addHobby(HobbyDTO_IN hobby) {
        if (hobby == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //add through facade, return
        return new HobbyDTO_OUT();
    }

    @PUT
    @Path("hobby")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit existing hobby", tags = {"Hobbies"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The edited Hobby"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public HobbyDTO_OUT editHobby(HobbyDTO_IN hobby) {
        if (hobby == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //change through facade, return
        return new HobbyDTO_OUT();
    }

    @DELETE
    @Path("hobby")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete existing Hobby", tags = {"Hobbies"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The deleted Hobby"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public HobbyDTO_OUT deleteHobby(HobbyDTO_IN hobby) {
        if (hobby == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //delete through facade, return
        return new HobbyDTO_OUT();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Old stuff (Movie)">
    @Deprecated
    @GET
    @Path("/deprecated/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "MovieInfoGet - To be deleted", deprecated = true,
            tags = {"Movies"},
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

    @Deprecated
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "MovieInfoPost - To be deleted", tags = {"Movies"}, deprecated = true,
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
//</editor-fold>
}