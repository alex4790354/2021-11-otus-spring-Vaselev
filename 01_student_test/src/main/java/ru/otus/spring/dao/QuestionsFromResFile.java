package ru.otus.spring.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Question;
import ru.otus.spring.myExceptions.QuestionsLoadingException;
import ru.otus.spring.utils.Util;
import java.io.*;



public class QuestionsFromResFile implements QuestionsDao {

    public QuestionsFromResFile(String fileName) throws QuestionsLoadingException {

        try {
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<Question> readValues = mapper.reader(Question.class).with(csvSchema).readValues(file);
            for (Question question : readValues.readAll()) {
                Util.SendMessage("Screen", question.getQuestionText());
            }
        } catch (Exception e) {
            if (e.getClass().toString().contains("FileNotFoundException")) {
                throw new QuestionsLoadingException("Exception: wasn't able to find file " + fileName);
            }
            e.printStackTrace();
        }


        /*                  Or we can use another method:


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
                    Util.SendMessage("Screen", question.getQuestionText());
                }
            }
            catch (IOException exc) {
                throw new QuestionsLoadingException ("Wasn't able to find a file " + fileName);
            }
        }  */

    }

}
