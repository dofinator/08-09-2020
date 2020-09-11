package entities;

public class Dto {

    private Long id;
    private int year;
    private String title;
    private String author;
    
    public Dto(Movie movie){
        this.id = movie.getId();
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.author = movie.getAuthor();
    }
}
