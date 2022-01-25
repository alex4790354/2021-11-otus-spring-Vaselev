# 2021-11-otus-spring-Vaselev
Otus Spring Sandbox

Create an application that stores information about books in a library

Target:
Goal: Use Spring JDBC and spring-boot-starter-jdbc to connect to relational databases
Result: an application with data storage in a relational database, which we will further develop

This homework is NOT based on the previous one.

Use Spring JDBC and a relational database (H2 or a real relational database). We highly recommend using NamedParametersJdbcTemplate
Provide tables of authors, books and genres.
A many-to-one relationship is assumed (the book has the same author and genre). An optional complication is a many-to-many relationship (a book can have many authors and/or genres).
The interface is executed on Spring Shell (CRUD books are required, operations with authors and genres - as it will be convenient).
The table creation script and the data populating script should automatically run with spring-boot-starter-jdbc.
Write tests for all DAO methods and book service.
Recommendations for the work:

Do NOT do AbstractDao.
Do NOT do inheritance in tests


====================================== 

Создать приложение хранящее информацию о книгах в библиотеке

Цель:
Цель: использовать возможности Spring JDBC и spring-boot-starter-jdbc для подключения к реляционным базам данных
Результат: приложение с хранением данных в реляционной БД, которое в дальнейшем будем развивать

Это домашнее задание выполняется НЕ на основе предыдущего.

Использовать Spring JDBC и реляционную базу (H2 или настоящую реляционную БД). Настоятельно рекомендуем использовать NamedParametersJdbcTemplate
Предусмотреть таблицы авторов, книг и жанров.
Предполагается отношение многие-к-одному (у книги один автор и жанр). Опциональное усложнение - отношения многие-ко-многим (у книги может быть много авторов и/или жанров).
Интерфейс выполняется на Spring Shell (CRUD книги обязателен, операции с авторами и жанрами - как будет удобно).
Скрипт создания таблиц и скрипт заполнения данными должны автоматически запускаться с помощью spring-boot-starter-jdbc.
Написать тесты для всех методов DAO и сервиса работы с книгами.
Рекомендации к выполнению работы:

НЕ делать AbstractDao.
НЕ делать наследования в тестах


commands to run: 

    login <Login_name>
    all
    count
    id <book_id>
    d <book_id>
    u <book_id> "New book name"
    insert - don't know how? 
    
    exit

