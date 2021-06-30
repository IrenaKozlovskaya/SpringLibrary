package repository;

import model.Author;
import model.Book;

import java.sql.SQLException;
import java.util.List;

public interface AuthorRepository {

    void setUpAuthorsInBook(Book book);

    void updateAuthorsInBook(Book book);

    List<Author> getAuthorsByBookID(long id)throws SQLException;

    List<Author> getAuthors()throws SQLException;

    void addAuthorToBD(String author)throws SQLException;

    long getAuthorId(String author_name);

    void deleteAuthorByName(String authorName) throws SQLException;

    void editAuthorByName(String authorName, String newAuthorName) throws SQLException;
}
