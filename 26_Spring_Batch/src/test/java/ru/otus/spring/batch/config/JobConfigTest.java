package ru.otus.spring.batch.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.batch.domain.sql.Book;
import ru.otus.spring.batch.domain.mongo.MongoBook;
import ru.otus.spring.batch.repository.BookRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.batch.config.JobConfig.IMPORT_BOOK_JOB_NAME;


@SpringBootTest
@SpringBatchTest
class JobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void testJob() throws Exception {
        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(IMPORT_BOOK_JOB_NAME);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        List<MongoBook> mongoBooks = mongoTemplate.findAll(MongoBook.class);
        List<Book> books = bookRepository.findAll();
        System.out.println(books);

        assertThat(books.size()).isEqualTo(mongoBooks.size());

        Book firstBook = books.get(0);
        MongoBook mongoBook = mongoBooks.stream()
                .filter(mBook -> mBook.getId().equals(firstBook.getMongoId()))
                .findFirst().orElse(null);
        assertThat(mongoBook).isNotNull();
        assertThat(firstBook.getAuthor().getMongoId()).isEqualTo(mongoBook.getAuthor().getId());
        assertThat(firstBook.getGenre().getMongoId()).isEqualTo(mongoBook.getGenre().getId());
    }

}