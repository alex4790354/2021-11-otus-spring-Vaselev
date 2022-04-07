package ru.otus.spring.integration.integration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.integration.domain.Student;
import ru.otus.spring.integration.domain.Report;
import java.util.Collection;

@MessagingGateway
public interface ReportGateway {

    @Gateway(requestChannel = "examChannel", replyChannel = "reportChannel")
    Collection<Report> process(Collection<Student> students);
}
