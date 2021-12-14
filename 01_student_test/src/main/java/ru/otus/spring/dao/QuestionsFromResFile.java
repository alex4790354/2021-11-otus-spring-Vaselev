package ru.otus.spring.dao;

import ru.otus.spring.customExceptions.QuestionsLoadingException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.utils.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class QuestionsFromResFile implements QuestionsDao {

    public QuestionsFromResFile(String fileName) throws QuestionsLoadingException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                Question question;
                while ((line = reader.readLine()) != null) {
                    question = Util.convertCsvLineToQuestion(line);
                    System.out.println(question.getQuestionText());
                }
            }
            catch (IOException exc) {
                throw new QuestionsLoadingException ("Wasn't able to find a file " + fileName);
            }
        }
    }

}
