package repository;

import model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreRepository {

    List<Genre> getGenres()throws SQLException;

    Genre getGenreByName(String name)throws SQLException;

    void addGenreToBD(String genre)throws SQLException;

    long getGenreId(String genre_name);

    void deleteGenreByName(String genre_name)throws SQLException;

    void editGenreByName(String genre_name, String genre_newName)throws SQLException;
}
