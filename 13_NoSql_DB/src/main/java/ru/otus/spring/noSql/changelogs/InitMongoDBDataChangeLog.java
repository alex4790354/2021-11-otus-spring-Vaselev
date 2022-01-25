package ru.otus.spring.noSql.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import ru.otus.spring.noSql.domain.Author;
import ru.otus.spring.noSql.domain.Book;
import ru.otus.spring.noSql.domain.Genre;


@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {


}
