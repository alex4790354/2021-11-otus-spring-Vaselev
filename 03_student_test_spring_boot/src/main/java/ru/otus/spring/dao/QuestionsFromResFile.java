package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.config.TestConfig;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


@Component
public class QuestionsFromResFile implements QuestionsDao {

    ArrayList<Question> questions;
    private final String fileName;

    //public QuestionsFromResFile(String fileName) {
    public QuestionsFromResFile(TestConfig testCofig) {
        // TODO:  String fileName  should be parametr.
        this.fileName = testCofig.getFileName();
        System.out.println("this.fileName2: " + this.fileName);

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
                    this.questions.add(new Question(line.split(",")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    // private InputStream getFileFromResourceAsStream(String fileName) {

}