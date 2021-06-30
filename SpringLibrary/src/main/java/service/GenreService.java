package service;

import model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreService {

    List<Genre> fetchGenres();

    Genre fetchGenreByName(String name);

    void addGenreToDB(String genre);

    void deleteGenreFromDB(String genre_name) throws SQLException;

    void editGenreByName(String genre_name, String genre_newName) throws SQLException;

    long getID(String genre_name);
}
