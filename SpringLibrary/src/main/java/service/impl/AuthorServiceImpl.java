package service.impl;

import model.Author;
import repository.impl.AuthorRepositoryImpl;
import service.AuthorService;

import java.sql.SQLException;
import java.util.List;


public class AuthorServiceImpl implements AuthorService {

    private AuthorRepositoryImpl authorRepository;

    public void setAuthorRepository(AuthorRepositoryImpl authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAuthors() {
        try {
            return authorRepository.getAuthors();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addAuthorToDB(String author) {
        try {
            authorRepository.addAuthorToBD(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getID(String author_name) {
        return authorRepository.getAuthorId(author_name);
    }

    @Override
    public void deleteAuthorFromDB(String author_name) throws SQLException {
        authorRepository.deleteAuthorByName(author_name);
    }

    @Override
    public void editAuthorFromDB(String author_name, String author_newName) throws SQLException {
        authorRepository.editAuthorByName(author_name, author_newName);
    }
}
