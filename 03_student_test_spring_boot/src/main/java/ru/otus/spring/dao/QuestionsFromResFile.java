package ru.otus.spring.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.spring.Util.QuestionsLoadingException;
import ru.otus.spring.Util.Util;
import ru.otus.spring.domain.Question;

import java.io.*;


@Component
public class QuestionsFromResFile implements QuestionsDao {

    private final Util util;

    @Autowired
    public QuestionsFromResFile(Util util) throws QuestionsLoadingException {

        this.util = util;
        String fileName = this.util.getExamPropertiesValue("exam.file-name");

        try {
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            String studentAnswer;
            int correctAnswers = 0;
            MappingIterator<Question> readValues = mapper.reader(Question.class).with(csvSchema).readValues(file);

            for (Question question : readValues.readAll()) {
                this.util.SendMessage("Screen", question.getQuestionText());
                studentAnswer = this.util.ReadMessage("Screen");
                if (question.getAnswer().equals(studentAnswer)) {
                    correctAnswers++;
                }
            }
            System.out.println("Total correct answers: " + correctAnswers);

        } catch (Exception e) {
            if (e.getClass().toString().contains("FileNotFoundException")) {
                throw new QuestionsLoadingException("Exception: wasn't able to find file " + fileName);
            }
            e.printStackTrace();
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
    }

}
