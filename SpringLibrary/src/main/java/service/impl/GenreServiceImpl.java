package service.impl;

import model.Genre;
import repository.impl.GenreRepositoryImpl;
import service.GenreService;

import java.sql.SQLException;
import java.util.List;


public class GenreServiceImpl implements GenreService {

    private GenreRepositoryImpl genreRepository;

    public void setGenreRepository(GenreRepositoryImpl genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre fetchGenreByName(String name) {
        try {
            return genreRepository.getGenreByName(name);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Genre> fetchGenres() {
        try {
            return genreRepository.getGenres();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void addGenreToDB(String genre) {
        try {
            genreRepository.addGenreToBD(genre);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteGenreFromDB(String genre_name) throws SQLException {
        genreRepository.deleteGenreByName(genre_name);
    }

    @Override
    public void editGenreByName(String genre_name, String genre_newName) throws SQLException {
        genreRepository.editGenreByName(genre_name, genre_newName);
    }

    @Override
    public long getID(String genre_name) {
        return genreRepository.getGenreId(genre_name);
    }
}
