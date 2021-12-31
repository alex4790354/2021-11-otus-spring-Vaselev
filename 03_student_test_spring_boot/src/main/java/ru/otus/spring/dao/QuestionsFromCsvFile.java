package ru.otus.spring.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.util.QuestionsLoadingException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class QuestionsFromCsvFile implements QuestionsDao {

    public List<Question> takeExamQuestionsList(String fileName) throws QuestionsLoadingException {

        List<Question> questions = new ArrayList<>();
        try {
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<Question> readValues = mapper.reader(Question.class).with(csvSchema).readValues(file);
            for (Question question : readValues.readAll()) {
                questions.add(question);
            }
        } catch (FileNotFoundException e) {
            throw new QuestionsLoadingException("Exception: wasn't able to find file " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}




        /**
        *
        *  Alternative unused example of csv file reading with manual object extraction.
        *
        **/
        /*ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                Question question;
                while ((line = reader.readLine()) != null) {
                    this.questions.add(new Question(line.split(",")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

