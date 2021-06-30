package repository.impl;

import config.DataSourceUtil;
import model.Genre;
import repository.GenreRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepositoryImpl implements GenreRepository {

    private DataSourceUtil dataSourceUtil;

    public void setDataSourceUtil(DataSourceUtil dataSourceUtil) {
        this.dataSourceUtil = dataSourceUtil;
    }

    @Override
    public List<Genre> getGenres() throws SQLException {

        String sql = "SELECT * FROM  genres";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return createGenres(resultSet);
    }

    @Override
    public Genre getGenreByName(String name) throws SQLException {

        String sql = "SELECT id, genre FROM genres WHERE genre = ?";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        return createGenres(resultSet).get(0);
    }

    private List<Genre> createGenres(ResultSet resultSet) throws SQLException {
        List<Genre> genreList = new ArrayList<>();
        while (resultSet.next()) {
            Genre genre = new Genre();
            genre.setId(resultSet.getLong(1));
            genre.setName(resultSet.getString(2));
            genreList.add(genre);
        }
        dataSourceUtil.disconnect();

        return genreList;
    }

    @Override
    public void addGenreToBD(String genre) throws SQLException {

        String sql = "INSERT INTO genres(genre) VALUES (?)";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        statement.setString(1, genre);
        statement.executeUpdate();

        dataSourceUtil.disconnect();
    }

    @Override
    public void deleteGenreByName(String genre_name) throws SQLException {
        String sql = "DELETE FROM genres WHERE genre = ?";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        statement.setString(1, genre_name);
        statement.executeUpdate();

        dataSourceUtil.disconnect();
    }

    @Override
    public void editGenreByName(String genre_name, String genre_newName) throws SQLException {
        String request = "UPDATE genres SET genre=? WHERE genre=?";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(request);
        statement.setString(1, genre_newName);
        statement.setString(2, genre_name);
        statement.executeUpdate();

        dataSourceUtil.disconnect();
    }

    public long getGenreId(String genre_name) {

        List<Genre> genres = null;
        try {
            genres = getGenres();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long genre_id = 0;

        assert genres != null;
        for (Genre i : genres) {
            if (i.getName().equals(genre_name)) {
                genre_id = i.getId();
                break;
            }
        }
        return genre_id;
    }
}
