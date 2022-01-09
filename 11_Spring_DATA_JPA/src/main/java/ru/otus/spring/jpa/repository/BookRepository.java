package ru.otus.spring.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jpa.domain.Book;

import java.util.*;


public interface BookRepository extends CrudRepository<Book, Long>  {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String bookName);


    void deleteById(Long id);

    @Query("select count(b) from Book b ")
    int findCount();

    @Modifying
    @Transactional
    @Query("update Book b set b.name = :bookName where b.id = :id")
    void updateBookNameById(@Param("id") long id, @Param("bookName") String bookName);

    /*@Modifying
    @Transactional
    @Query("INSERT INTO book(author_id, genre_id, name) VALUES(:authorId, :genreId, :name) ")
    void insertBook(@Param("authorId") Long authorId,
                    @Param("genreId") Long genreId,
                    @Param("name") String name);*/



    /*@Override
    public void insert(Book book) throws DaoException {
        Map<String, Object> params = new HashMap<>();
        params.put("author_id", book.getAuthor().getId());
        params.put("genre_id", book.getGenre().getId());
        params.put("book_name", book.getName());
        try {
            npJdbc.update("INSERT INTO book(author_id, genre_id, name) VALUES (:author_id, :genre_id, :book_name )", params);
        } catch (Exception exc) {
            if (exc.getClass().getName().equals("DataIntegrityViolationException")) {
                throw new DaoException("Error: Author or genre doesn't exist. Please check and correct it");
            } else {
                throw exc;
            }
        }
    }*/

    /*@Override
    public int getCount() {
        Map<String, Object> params = Collections.emptyMap();
        return npJdbc.getJdbcOperations().queryForObject("SELECT count(*) FROM book ", Integer.class);
    }*/

}
