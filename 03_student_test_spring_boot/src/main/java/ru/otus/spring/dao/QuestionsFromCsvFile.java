package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Component
public class QuestionsFromCsvFile implements QuestionsDao {

    public List<Question> takeExamQuestionsList(String fileName) throws IOException {
        List<Question> questions = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

    private static String[] headers(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine().split(",");
        }
    }
}




        /**
        *
        *  Alternative unused example with  CsvSchema
        *
        **/
        /*public List<Question> takeExamQuestionsList(String fileName) throws QuestionsLoadingException {
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
        }*/

        // TODO: change mapping to:
        /*try (Stream<String> stream = Files.lines(Paths.get(path))) {
            result = stream
            .skip(1) // skip headers
            .map(line -> line.split(","))
            .map(data -> {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < data.length; i++) {
            map.put(headers[i], data[i]);
            }
            return map;
            })
            .collect(Collectors.toList());
        }*/