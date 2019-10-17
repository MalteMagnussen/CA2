package rest;

import dto.CityInfoDTO_IN;
import dto.CityInfoDTO_OUT;
import dto.HobbyDTO_IN;
import dto.HobbyDTO_OUT;
import dto.PersonDTO_IN;
import dto.PersonDTO_OUT;
import facades.ISearchFacade;
import facades.SearchFacade_Impl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
import utils.EMF_Creator;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@OpenAPIDefinition(
        info = @Info(
                title = "CA2 API",
                version = "1.3",
                description = "API related to Cphbusiness 3rd semester CS assignment 'CA2'.",
                contact = @Contact(name = "Github Contributors", url = "https://github.com/MalteMagnussen/CA2/")
        ),
        tags = {
            @Tag(name = "General", description = "API related to CA2"),
            @Tag(name = "Persons", description = "CRUD-operations for Person"),
            @Tag(name = "Hobbies", description = "CRUD-operations for Hobby"),
            @Tag(name = "Cities", description = "CRUD-operations for City")
        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/CA2"
            ),
            @Server(
                    description = "Server API",
                    url = "https://maltemagnussen.com/CA2/"
            )

        }
)
@Path("search")
public class SearchResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final ISearchFacade FACADE = SearchFacade_Impl.getSearchFacade(EMF);

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
        List<PersonDTO_OUT> returnList = FACADE.getPersonDTO_OUT_ByHobby(hobby);
        return returnList;
    }

    @GET
    @Path("/allpersons")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons",
            tags = {"General"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "The Requested list of persons"),
                @ApiResponse(responseCode = "404", description = "No persons found")})
    public List<PersonDTO_OUT> getAllPersons() {
        List<PersonDTO_OUT> returnList = FACADE.getAllPersonDTO_OUT();
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
    public String getPersonsCountByHobby(@PathParam("hobby") String hobby) {
        JsonObject dbMsg = new JsonObject();
        dbMsg.addProperty("count", FACADE.getCountPersonByHobby(hobby)); //returned from query
        return dbMsg.toString();
    }

    @GET
    @Path("person/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get person given a name (firstname lastname)", tags = {"Persons"},
            responses = {
                @ApiResponse(responseCode = "200", description = "List of persons with name"),
                @ApiResponse(responseCode = "404", description = "No person found")
            })
    public List<PersonDTO_OUT> getPersonByFullName(@PathParam("name") String name) {
        if (name == null) {
            throw new WebApplicationException("No person found", 404);
        }
        List<PersonDTO_OUT> returnList = FACADE.getPersonByFullName(name);
        return returnList;
    }

    @POST
    @Path("/create/person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add new person", tags = {"Persons"},
            requestBody = @RequestBody(description = "Person Data (DTO) to be stored. Hobbies not included.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PersonDTO_IN.class))),
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO_OUT addPersonNoHobbies(PersonDTO_IN person) {
        if (person == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        return FACADE.addPerson(person);
    }

    @POST
    @Path("/create-all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add new person", tags = {"Persons"},
            requestBody = @RequestBody(description = "Person Data (DTO) to be stored. Include hobbies.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PersonDTO_IN.class))),
            responses = {
                @ApiResponse(responseCode = "200", description = "The Newly created Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO_OUT addPersonWithEverything(PersonDTO_IN person) {
        if (person == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        return FACADE.addPersonWithEverything(person);
    }

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
        return FACADE.getPersonByPhone(phone);
    }

    @GET
    @Path("/city")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all persons living in a given city (i.e. 2800 Lyngby)",
            tags = {"General"}, parameters = @Parameter(array = @ArraySchema(schema = @Schema(implementation = CityInfoDTO_IN.class))),
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CityInfoDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "The Requested list of persons"),
                @ApiResponse(responseCode = "404", description = "No persons in that city found")})
    public List<PersonDTO_OUT> getPersonsByCity(@QueryParam("zip") String zip, @QueryParam("city") String city) {
        //    /api/search/city?zip=<zip>&city=<city>
        return FACADE.getPersonsInCity(new CityInfoDTO_IN(zip, city));
    }

    @GET
    @Path("/zip/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a list of all zip codes in Denmark",
            tags = {"General"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CityInfoDTO_OUT.class))),
                @ApiResponse(responseCode = "200", description = "An list of all the zipcodes"),
                @ApiResponse(responseCode = "400", description = "No zipcodes found")})
    public List<Integer> getAllZipCodes() {
        //    /api/search/zip
        return FACADE.getZipcodes();
    }

    @PUT
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit existing person", tags = {"Persons"},
            requestBody = @RequestBody(description = "Person Data (DTO) to be edited.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PersonDTO_IN.class))),
            responses = {
                @ApiResponse(responseCode = "200", description = "The edited Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO_OUT editPerson(PersonDTO_IN person) {
        return FACADE.editPerson(person);
    }

    //<editor-fold defaultstate="collapsed" desc="API NOT YET DONE">
    @DELETE
    @Path("person/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete existing person", tags = {"Persons"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The deleted Person"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public PersonDTO_OUT deletePerson(@PathParam("id") int personId) {
        return FACADE.deletePerson(personId);
    }

    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get hobby given a name", tags = {"Hobbies"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Hobby with the given name"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })

    public HobbyDTO_OUT getHobbyByName(@PathParam("hobby") String hobby) {
        if (hobby == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //Based on entity & DTO we might want first name + last name
        //get from facade, return
        HobbyDTO_OUT result = FACADE.getHobbyByName(hobby);
        return result;
    }

    @POST
    @Path("hobby")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add new hobby", tags = {"Hobbies"}, deprecated = true,
            requestBody = @RequestBody(description = "Hobby Data (DTO) to be added.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = HobbyDTO_IN.class))),
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
    @Operation(summary = "Edit existing hobby", tags = {"Hobbies"}, deprecated = true,
            requestBody = @RequestBody(description = "Hobby Data (DTO) to be edited.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = HobbyDTO_IN.class))),
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
    @Operation(summary = "Delete existing Hobby", tags = {"Hobbies"}, deprecated = true,
            requestBody = @RequestBody(description = "Hobby Data (DTO) to be deleted.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = HobbyDTO_IN.class))),
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

    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets city given a name", tags = {"Cities"}, deprecated = true,
            responses = {
                @ApiResponse(responseCode = "200", description = "Returns city based on name if it exists"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })

    public CityInfoDTO_OUT getCityByName(@PathParam("city") String city) {
        if (city == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //Based on entity & DTO we might want first name + last name
        //get from facade, return
        CityInfoDTO_OUT returnItem = FACADE.getCityByName(city);
        return returnItem;
    }

    @GET
    @Path("city/zip/{zip}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get city given a zip-code", tags = {"Cities"}, deprecated = true,
            responses = {
                @ApiResponse(responseCode = "200", description = "City based on zip code"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })

    public CityInfoDTO_OUT getCityByZip(@PathParam("zip") String zip) {
        if (zip == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //Based on entity & DTO we might want first name + last name
        //get from facade, return
        CityInfoDTO_OUT returnList = FACADE.getCityByZipCode(zip);
        return returnList;
    }

    @POST
    @Path("city")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add new city", tags = {"Cities"}, deprecated = true,
            requestBody = @RequestBody(description = "City Data (DTO) to be added.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CityInfoDTO_IN.class))),
            responses = {
                @ApiResponse(responseCode = "200", description = "The newly created City"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public CityInfoDTO_OUT addCity(CityInfoDTO_IN city) {
        if (city == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //add through facade, return
        return new CityInfoDTO_OUT();
    }

    @PUT
    @Path("city")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit existing city", tags = {"Cities"}, deprecated = true,
            requestBody = @RequestBody(description = "City Data (DTO) to be edited.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CityInfoDTO_IN.class))),
            responses = {
                @ApiResponse(responseCode = "200", description = "The edited city"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public CityInfoDTO_OUT editCity(CityInfoDTO_IN city) {
        if (city == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //change through facade, return
        return new CityInfoDTO_OUT();
    }

    @DELETE
    @Path("city")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete existing city", tags = {"Cities"}, deprecated = true,
            requestBody = @RequestBody(description = "City Data (DTO) to be deleted.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CityInfoDTO_IN.class))),
            responses = {
                @ApiResponse(responseCode = "200", description = "The deleted City"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public CityInfoDTO_OUT deleteCity(CityInfoDTO_IN city) {
        if (city == null) {
            throw new WebApplicationException("Not all required arguments included", 400);
        }
        //delete through facade, return
        return new CityInfoDTO_OUT();
    }
    //</editor-fold>
}
