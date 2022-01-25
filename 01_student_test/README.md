# 2021-11-otus-spring-Vaselev
Otus Spring Sandbox


Application for conducting student testing (only the output of questions)

Target:
build an application with Spring IoC to get familiar with the core IoC functionality that all of Spring is built on.
Result: a simple application configured with an XML context.

Job description:
The resources store questions and various answers to them in the form of a CSV file (5 questions).
Questions can be with a choice of several options or with a free answer - at your desire and discretion.
The application should simply output the test questions from the CSV file with possible answers.


Requirements:
0. The application must have an object model (we prefer objects and classes, not strings and arrays/lists of strings).
1. All classes in the application must solve a strictly defined task (see paragraphs 18-19 of the "Coding Rules.pdf", attached to the lesson materials).
2. The context is described by an XML file.
3. All dependencies must be configured in the IoC container.
4. The name of the resource with questions (CSV file) must be hardcoded as a line in the XML file with the context.
5. CSV with questions is read exactly as a resource, not as a file.
6. Scanner, PrintStream and other standard types do not need to be put in the context!
7. All I/O is in English.
8. It is highly desirable to write a unit test of some service (only an attempt to write a test will be evaluated).


========================== 

Приложение по проведению тестирования студентов (только вывод вопросов)

Цель:
создать приложение с помощью Spring IoC, чтобы познакомиться с основной функциональностью IoC, на которой строится весь Spring.
Результат: простое приложение, сконфигурированное XML-контекстом.

Описание задание:

В ресурсах хранятся вопросы и различные ответы к ним в виде CSV файла (5 вопросов).
Вопросы могут быть с выбором из нескольких вариантов или со свободным ответом - на Ваше желание и усмотрение.
Приложение должна просто вывести вопросы теста из CSV-файла с возможными вариантами ответа.

Требования:
0. В приложении должна присутствовать объектная модель (отдаём предпочтение объектам и классам, а не строчкам и массивам/спискам строчек).

1. Все классы в приложении должны решать строго определённую задачу (см. п. 18-19 "Правила оформления кода.pdf", прикреплённые к материалам занятия).
2. Контекст описывается XML-файлом.
3. Все зависимости должны быть настроены в IoC контейнере.
4. Имя ресурса с вопросами (CSV-файла) необходимо захардкодить строчкой в XML-файле с контекстом.
5CSV с вопросами читается именно как ресурс, а не как файл.
6Scanner, PrintStream и другие стандартные типы в контекст класть не нужно!
7Весь ввод-вывод осуществляется на английском языке.
8Крайне желательно написать юнит-тест какого-нибудь сервиса (оцениваться будет только попытка написать тест).