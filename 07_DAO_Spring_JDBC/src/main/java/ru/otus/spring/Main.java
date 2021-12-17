package ru.otus.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.customExceptions.WrongSqlStatement;
import ru.otus.spring.dao.interfaces.AuthorDao;
import ru.otus.spring.dao.interfaces.BookDao;
import ru.otus.spring.dao.interfaces.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws WrongSqlStatement { //throws Exception {

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
        System.out.println("Book with id=3: " + bookDao.getById(3));
        bookDao.insert("Аркадий и Борис Стругацкие", "Фантастика", "Отель у погибшего альпениста 1");
        bookDao.insert("Нат такого автора", "Нет такого жанра", "Отель у погибшего альпениста 2");
        for (Book book : bookDao.getAll()) {
            System.out.println(book.getName());
        }
        System.out.println(System.lineSeparator());

        //Console.main(args);
    }
}
