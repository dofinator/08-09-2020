package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Dto;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.ejb.PostActivate;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class Endpoints {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String countAllMovies() {
        long count = FACADE.countAllMovies();

        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("addMovie")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String addMovie() {

        Dto movie = new Dto(FACADE.addMovie());

        return GSON.toJson(movie);

    }

    @Path("title/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByTitle(@PathParam("title") String title) {

        Dto movie = new Dto(FACADE.getMovieFromTitle(title));

        return GSON.toJson(movie);

    }
    
    @Path("year/{year}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByYear(@PathParam("year") int year) {

        Dto movie = new Dto(FACADE.getMovieFromYear(year));

        return GSON.toJson(movie);

    }
    
    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") Long id){
        
        Dto movie = new Dto(FACADE.getMovieById(id));
        
        return GSON.toJson(movie);
    }
}
