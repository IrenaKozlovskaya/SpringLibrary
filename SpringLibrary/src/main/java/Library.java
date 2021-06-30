import model.Author;
import model.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import readingFromXML.DOMParser;
import service.BookService;
import service.impl.GenreServiceImpl;
import service.impl.AuthorServiceImpl;
import service.impl.BookServiceImpl;

public class Library {

    private final ApplicationContext context = new GenericXmlApplicationContext("/xml/spring-context.xml");
    private final BookServiceImpl bookService = context.getBean(BookServiceImpl.class);
    private final AuthorServiceImpl authorService = context.getBean(AuthorServiceImpl.class);
    private final GenreServiceImpl genreService = context.getBean(GenreServiceImpl.class);


    public void addAllObjectsXMLToDB() throws SQLException {

        List<Book> book = DOMParser.readXML();

        for (Book book1 : book) {

            String genre_name = book1.getGenre().getName();
            authorVerificationByID(book1);
            genreVerificationByID(book1, genre_name);

            bookService.addBookToDB(book1);

        }
    }


    private void authorVerificationByID(Book book) {
        List<Author> authors = book.getAuthors();
        for (Author author : authors) {
            String author_name = author.getName();

            long author_id = authorService.getID(author_name);

            if (author_id == 0) {
                authorService.addAuthorToDB(author_name);
                author_id = authorService.getID(author_name);
            }
            author.setId(author_id);
        }
    }

    private void genreVerificationByID(Book book, String genre_name) {
        long genre_id = genreService.getID(genre_name);

        if (genre_id == 0) {
            genreService.addGenreToDB(genre_name);
        }
        book.setGenre(genreService.fetchGenreByName(genre_name));

    }

    public Book addBook(String title, String genre, List<String> authors, String ISBN) {
        for (Book book : getAllBooks()) {
            if (book.getTitle().equals(title)) {
                System.out.println("Книга с таким названием уже есть в библиотеке!");
                return bookService.fetchBookById(book.getId());
            }
        }
        Book book = buildBook(title, genre, authors, ISBN);
        bookService.addBookToDB(book);
        return bookService.fetchBookById(bookService.getID(title));
    }

    private Book buildBook(String title, String genre, List<String> author_name, String ISBN) {
        Book book = new Book();

        book.setTitle(title);
        book.setISBN(ISBN);
        for (String name : author_name) {
            Author author = new Author();
            author.setName(name);
            book.getAuthors().add(author);
        }

        genreVerificationByID(book, genre);
        authorVerificationByID(book);

        return book;
    }

    public void deleteBook(long id) {
        bookService.deleteBookFromDB(id);
    }

    public Book editBook(long id, String title, List<String> author_newName, String genre_newName) {
        Book book = bookService.fetchBookById(id);
        book.setTitle(title);
        List<Author> authors = new ArrayList<>();
        for (String name : author_newName) {
            Author author = new Author();
            author.setName(name);
            authors.add(author);
        }
        book.setAuthors(authors);


        genreVerificationByID(book, genre_newName);
        authorVerificationByID(book);

        bookService.editBookFromDB(book);

        return book;

    }

    public Book getBookById(long id) {
        return bookService.fetchBookById(id);
    }

    public List<Book> getAllBooks() {

        return new ArrayList<>(bookService.fetchAllBooks());
    }

    public Book getBook(long id) {
        return bookService.fetchBookById(id);
    }

    public List<Book> getBooksByAuthor(String author_name) {
        return bookService.fetchBookByAuthor(author_name);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookService.fetchBookByGenre(genre);
    }

}
