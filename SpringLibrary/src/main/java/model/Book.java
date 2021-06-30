package model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private long id;
    private String title;
    private String ISBN;
    private Genre genre = new Genre();
    private List<Author> authors = new ArrayList<>();


    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String toString() {
        return "id = " + id + ", название = " + title + ", ISBN = " + ISBN + ", жанр = " + genre.getName() + ", автор = " + getAuthors().toString();
    }

}
