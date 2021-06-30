package service;


import model.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorService {

    List<Author> getAuthors();

    void addAuthorToDB(String author);

    long getID(String author_name);

    void deleteAuthorFromDB(String author_name) throws SQLException;

    void editAuthorFromDB(String author_name, String author_newName) throws SQLException;

}
