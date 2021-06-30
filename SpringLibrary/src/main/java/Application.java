import lombok.SneakyThrows;
import model.Book;

import java.util.*;

public class Application {

    @SneakyThrows
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        String command;
        String author_name;
        List<String> authors_name = new ArrayList<>();


        library.addAllObjectsXMLToDB();


        do {
            System.out.println("Выберите действие и нажмите \"Enter\" \n" +
                    "1. Показать все книги \n" +
                    "2. Вывести информацию о книге по ID \n" +
                    "3. Добавить книгу \n" +
                    "4. Удалить книгу \n" +
                    "5. Редактировать книгу \n" +
                    "6. Выход");
            command = scanner.nextLine();

            switch (command) {

                case ("1"):
                    List<Book> books = library.getAllBooks();
                    for (Book book : books) {
                        System.out.println(book.toString());
                    }
                    break;
                case ("2"):
                    System.out.println("Введите ID книги");
                    try {
                        System.out.println(library.getBook(Long.parseLong(scanner.nextLine())).toString());
                    } catch (NumberFormatException e) {
                        System.out.println("ID должно состоять из цифр");
                    }
                    break;
                case ("3"):
                    authors_name.clear();
                    System.out.println("Введите автора книги. Если их несколько введите имена по очереди. Для перехода к вводу названия книги нажмите 0. ");
                    while (true) {
                        author_name = scanner.nextLine();
                        if (!author_name.equals("0")) {
                            authors_name.add(author_name);
                        } else
                            break;
                    }
                    System.out.println("Введите название книги, жанр и ISBN.");
                    System.out.println(library.addBook(scanner.nextLine(), scanner.nextLine(), authors_name, scanner.nextLine()).toString());
                    break;
                case ("4"):
                    System.out.println("Введите ID книги, которую хотите удалить");
                    try {
                        library.deleteBook(Long.parseLong(scanner.nextLine()));
                        System.out.println("Книга удалена!");
                    } catch (NumberFormatException e) {
                        System.out.println("ID должно состоять из цифр");
                    }
                    break;
                case ("5"):
                    authors_name.clear();
                    try {
                        System.out.println("Введите Id книги которую хотите изменить");
                        long book_id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите автора книги. Если их несколько введите имена по очереди. Для перехода к вводу названия книги нажмите 0. ");
                        while (true) {
                            author_name = scanner.nextLine();
                            if (!author_name.equals("0")) {
                                authors_name.add(author_name);
                            } else
                                break;
                        }
                        System.out.println("Введите новое название и жанр книги.");
                        System.out.println(library.editBook(book_id, scanner.nextLine(), authors_name, scanner.nextLine()).toString());
                    } catch (NumberFormatException e) {
                        System.out.println("ID должно состоять из цифр");
                    }


            }
        }
        while (!command.equals("6"));
    }
}

