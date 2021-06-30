package service;

import model.Book;

import java.util.List;

public interface BookService {

    List<Book> fetchAllBooks();

    Book fetchBookById(long id);

    List<Book> fetchBookByAuthor(String author_name);

    List<Book> fetchBookByGenre(String genre);

    void deleteBookFromDB(long id);

    void addBookToDB(Book book);

    void editBookFromDB(Book book);


}
