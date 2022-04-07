package ru.otus.spring.integration.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Student;
import ru.otus.spring.integration.domain.Report;

import java.util.Collection;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;

@RequiredArgsConstructor
@IntegrationComponentScan
@Service
public class IntegrationService {

    private final ReportGateway reportGateway;

    @Async
    @Scheduled(fixedRate = 1000)
    public void start() {
        Collection<Student> students = generateInspections();
        System.out.println("---------New student: " + students.stream()
                .map(Student::toString)
                .collect(joining("\n")));

        Collection<Report> reports = reportGateway.process(students);
        System.out.println("---------Result: " + reports.stream()
                .map(Report::toString)
                .collect(joining("\n")));
    }

    private static Collection<Student> generateInspections() {
        return IntStream.range(0, nextInt(1, 5))
                .mapToObj(i -> new Student(nextLong()))
                .collect(toList());
    }
}
