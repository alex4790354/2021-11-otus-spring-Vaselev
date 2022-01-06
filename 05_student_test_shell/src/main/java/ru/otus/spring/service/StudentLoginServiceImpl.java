package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.interfaces.StudentLoginService;

@Service
public class StudentLoginServiceImpl implements StudentLoginService {

    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
