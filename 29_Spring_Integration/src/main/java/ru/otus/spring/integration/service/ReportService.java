package ru.otus.spring.integration.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.integration.domain.Student;
import ru.otus.spring.integration.domain.Report;


@Service
public class ReportService {

    public Report generateReport(Student student) throws InterruptedException {
        System.out.printf( "***** %s %s (studentID: %s) start the test\n", student.getFirstName(), student.getLastName(), student.getId() );
        Thread.sleep(3000);
        System.out.printf( "***** %s %s (studentID: %s) finished test\n", student.getFirstName(), student.getLastName(), student.getId() );
        return new Report(student.getId());
    }
}
