package ru.otus.spring.customExceptions;

import java.sql.SQLException;

public class WrongSqlStatement extends SQLException {

    public WrongSqlStatement(String reason) {
        super(reason);
    }

}
