package service.impl;


import model.Book;
import repository.impl.BookRepositoryImpl;
import service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepositoryImpl bookRepository;

    public void setBookRepository(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> fetchAllBooks() {

        try {
            return bookRepository.getBooks();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book fetchBookById(long id) {
        try {
            return bookRepository.getBookByID(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> fetchBookByAuthor(String author_name) {
        try {
            return bookRepository.getBooksByAuthorName(author_name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> fetchBookByGenre(String genre) {
        try {
            return bookRepository.getBooksByGenre(genre);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteBookFromDB(long id) {
        try {
            bookRepository.deleteBookByID(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBookToDB(Book book) {
        try {
            bookRepository.addBookToBD(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editBookFromDB(Book book) {
        try {
            bookRepository.editBookByID(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getID(String title) {
        return bookRepository.getBookId(title);
    }
}
