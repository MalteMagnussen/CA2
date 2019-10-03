package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashSet;
import java.util.Set;

/*
EXAMPLE CLASS
*/

@Schema(name = "MovieInfo")  //Because of this we could have called the class MovieInfoDTO
public class MovieInfo {

    private int id;
    private String title;
    @Schema(required = true, example = "Forest Gump")
    private String director;
    @Schema(required = true, example = "Robert Zemeckis")
    private Integer year;
    @Schema(required = true, example = "1994")
    private int rating;

    @Schema(example = "[\"Tom Hanks\",\"Robin Wright\"]")
    private Set<String> actors = new HashSet();


    public MovieInfo(String title, String director, int year, int rating, Set<String> actors) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.actors = actors;
    }

    public MovieInfo() {
    }

    //Add getters and setters for ALL fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

}
