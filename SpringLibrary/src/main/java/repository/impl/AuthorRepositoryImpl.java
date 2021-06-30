package repository.impl;

import model.Author;
import config.DataSourceUtil;
import model.Book;
import repository.AuthorRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    private DataSourceUtil dataSourceUtil;

    public void setDataSourceUtil(DataSourceUtil dataSourceUtil) {
        this.dataSourceUtil = dataSourceUtil;
    }

    @Override
    public void setUpAuthorsInBook(Book book) {

        String trigger = "INSERT INTO book_authors (book_id,author_id) VALUES (?,?)";

        try {
            PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(trigger);

            List<Author> authors = book.getAuthors();
            for (Author author : authors) {
                statement.setLong(1, book.getId());
                statement.setLong(2, author.getId());

                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSourceUtil.disconnect();
    }

    @Override
    public void updateAuthorsInBook(Book book) {

        String trigger = "DELETE FROM book_authors WHERE book_id = ?";

        try {
            PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(trigger);

            statement.setLong(1, book.getId());
            statement.executeUpdate();

            setUpAuthorsInBook(book);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSourceUtil.disconnect();
    }

    @Override
    public List<Author> getAuthorsByBookID(long id) throws SQLException {

        String sql = "SELECT *  FROM authors a INNER JOIN book_authors b_a ON a.id = b_a.author_id INNER JOIN books b ON b.id=b_a.book_id where b.id = ?";

        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        assert resultSet != null;
        return createAuthors(resultSet);

    }

    @Override
    public List<Author> getAuthors() throws SQLException {

        String sql = "SELECT * FROM  authors";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        return createAuthors(resultSet);
    }

    private List<Author> createAuthors(ResultSet resultSet) throws SQLException {
        List<Author> authorList = new ArrayList<>();

        while (resultSet.next()) {
            Author author = new Author();
            author.setId(resultSet.getLong(1));
            author.setName(resultSet.getString(2));
            authorList.add(author);
        }


        return authorList;

    }

    @Override
    public void addAuthorToBD(String author) throws SQLException {

        String sql = "INSERT INTO authors( author) VALUES (?)";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        statement.setString(1, author);
        statement.executeUpdate();

        dataSourceUtil.disconnect();
    }

    @Override
    public void deleteAuthorByName(String author_name) throws SQLException {
        String sql = "DELETE FROM authors WHERE author = ?";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(sql);
        statement.setString(1, author_name);
        statement.executeUpdate();

        dataSourceUtil.disconnect();
    }

    @Override
    public void editAuthorByName(String author_name, String author_newName) throws SQLException {
        String request = "UPDATE authors SET author=? WHERE author=?";
        PreparedStatement statement = dataSourceUtil.getConnection().prepareStatement(request);
        statement.setString(1, author_newName);
        statement.setString(2, author_name);
        statement.executeUpdate();

        dataSourceUtil.disconnect();
    }

    @Override
    public long getAuthorId(String author_name) {

        List<Author> authors = null;
        try {
            authors = getAuthors();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long author_id = 0;
        assert authors != null;
        for (Author author : authors) {
            if (author.getName().equals(author_name)) {
                author_id = author.getId();
                break;
            }
        }
        return author_id;
    }
}
