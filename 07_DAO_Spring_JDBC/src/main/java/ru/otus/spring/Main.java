package ru.otus.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.customExceptions.DaoException;
import ru.otus.spring.dao.interfaces.AuthorDao;
import ru.otus.spring.dao.interfaces.BookDao;
import ru.otus.spring.dao.interfaces.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws DaoException { //throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        AuthorDao authorDao = context.getBean(AuthorDao.class);
        for (Author author : authorDao.getAll()) {
            System.out.println(author.getName());
        }

        System.out.println(System.lineSeparator() + "Genre: ");
        GenreDao genreDao = context.getBean(GenreDao.class);
        for (Genre genre : genreDao.getAll()) {
            System.out.println(genre.getName());
        }


        System.out.println(System.lineSeparator() + "Books: ");
        BookDao bookDao = context.getBean(BookDao.class);

        System.out.println("Book with id=1: " + bookDao.getById(1));
        System.out.println("Total books: " + bookDao.getCount());


        bookDao.deleteById(1);
        System.out.println("Total books now: " + bookDao.getCount());

        bookDao.updateById(3, "NO NO NO");
        System.out.println("Book with id=3: " + bookDao.getById(3) + System.lineSeparator());

        Author author = new Author(4, "Аркадий и Борис Стругацкие");
        Genre genre = new Genre(3, "Фантастика");
        Book newBook = new Book(author, genre, "Отель у погибшего альпениста 2");
        System.out.println("Insert book: " + newBook + System.lineSeparator());
        bookDao.insert(newBook);

        author = new Author(10, "Нат такого автора");
        genre = new Genre(10, "Нет такого жанра");
        newBook = new Book(author, genre, "Попытка внести эту книгу должна вызвать ошибку");
        System.out.println("Insert book-2: " + newBook + System.lineSeparator());
        bookDao.insert(newBook);
        for (Book book : bookDao.getAll()) {
            System.out.println(book.getName());
        }
        System.out.println(System.lineSeparator());

        //Console.main(args);
    }
}
