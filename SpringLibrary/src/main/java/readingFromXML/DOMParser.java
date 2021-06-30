package readingFromXML;

import model.Author;
import model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {


    public static List<Book> readXML() {

//        File file = new File("D:/Programming/dev/java_course_FreeIT/second_part/пиздец/Project/books.xml");
        URL url = DOMParser.class.getClassLoader().getResource("xml/books.xml");
        File file = null;
        try {
            assert url != null;
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            System.out.println("Файл не найден");
        }

        List<Book> list = new ArrayList<>();

        try {
            DocumentBuilder docParser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docParser.parse(file);

            Node root = document.getDocumentElement();

            NodeList books = root.getChildNodes();

            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);

                if (book.getNodeType() != Node.TEXT_NODE) {
                    list.add(getBook(book));

                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace(System.out);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static Book getBook(Node node) {
        Book book = new Book();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            book.setTitle(getTagValue("title", element));
            book.setISBN(getTagValue("ISBN", element));

            NodeList ofParameters = node.getChildNodes();

            for (int i = 0; i < ofParameters.getLength(); i++) {
                Node authorOrGenre = ofParameters.item(i);

                if (authorOrGenre.getNodeType() != Node.TEXT_NODE) {

                    switch (authorOrGenre.getNodeName()) {
                        case "authors":
                            book.setAuthors(createAuthors(authorOrGenre));
                            break;
                        case "genre":
                            book.getGenre().setName(getTagValue("name", element));


                    }
                }

            }


        }
        return book;
    }

    public static List<Author> createAuthors(Node node) {
        List<Author> authors = new ArrayList<>();

        NodeList listOfAuthors = node.getChildNodes();

        for (int i = 0; i < listOfAuthors.getLength(); i++) {
            Node author = listOfAuthors.item(i);

            Author newAuthor = new Author();

            if (author.getNodeType() != Node.TEXT_NODE) {
                Element element = (Element) author;
                newAuthor.setName(getTagValue("authorName", element));
                authors.add(newAuthor);
            }
        }
        return authors;
    }

    public static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}

