package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;
import ru.otus.spring.utils.Convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class QuestionsFromResFile implements QuestionsDao {

    private final List<Question> questions;

    public QuestionsFromResFile(String fileName) {
        this.questions = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                Question question;
                while ((line = reader.readLine()) != null) {
                    question = Convert.getQuestionFromCsvLine(line);
                    this.questions.add(question);
                    System.out.println(question.getQuestionText());
                }
            }
            // TODO:  Исключения лучше не подавлять, а заворачивать в свои бизнес исключения (например, QuestionsLoadingException)
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
