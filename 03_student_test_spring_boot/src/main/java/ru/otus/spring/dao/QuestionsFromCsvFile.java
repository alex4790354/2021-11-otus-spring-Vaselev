package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.interfaces.QuestionsDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.util.ExamException;
import ru.otus.spring.dao.interfaces.QstnsDaoFileNameProvider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Component
public class QuestionsFromCsvFile implements QuestionsDao {

    private final QstnsDaoFileNameProvider qstnsDaoFileNameProvider;

    @Autowired
    public QuestionsFromCsvFile(QstnsDaoFileNameProvider qstnsDaoFileNameProvider) {
        this.qstnsDaoFileNameProvider = qstnsDaoFileNameProvider;
    }


    public List<Question> takeExamQuestionsList() {

        try {


            List<Question> questions = new ArrayList<>();

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(qstnsDaoFileNameProvider.getFileName());
            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + qstnsDaoFileNameProvider.getFileName());
            }
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {
                String line;
                String[] header = new String[0];
                String[] data;
                Question question;
                int indx = 0;
                while ((line = reader.readLine()) != null) {
                    String[] array = line.split(",");
                    question = new Question();
                    if (indx == 0) {
                        header = array;
                    } else {
                        data = array;
                        for (int i = 0; i < data.length; i++) {
                            question.setQuestion(header[i], data[i]);
                        }
                        questions.add(question);
                    }
                    indx++;
                }
            } catch (Exception e) {
                throw new ExamException("Please check the header file (first row in " + qstnsDaoFileNameProvider.getFileName() + ")", e);
            }
            return questions;
        } catch (Exception e) {
            throw new ExamException("Error in takeExamQuestionsList()", e);
        }
    }
}