# 2021-11-otus-spring-Vaselev

JPQL, Spring ORM, DAO based on Spring ORM + JPA


Rewrite book storage application with ORM

Target:
Purpose: to fully work with JPA + Hibernate to connect to relational databases through an ORM framework
Result: A high-level application with JPA entity mapping

Homework is done by rewriting the previous one in JPA.

Requirements:

1) Use JPA, Hibernate only as a JPA provider.
2) To solve the N+1 problem, you can use the Hibernate-specific @Fetch and @BatchSize annotations.
3) Add a "book comment" entity, implement CRUD for the new entity.
4) Cover the repositories with tests using an H2 database and the corresponding H2 Hibernate dialect for tests.
5) Don't forget to disable DDL via Hibernate
6) @Transactional is recommended to be set only on service methods.


======================================== 

JPQL, Spring ORM, DAO на основе Spring ORM + JPA 

Переписать приложение для хранения книг на ORM

Цель:
Цель: полноценно работать с JPA + Hibernate для подключения к реляционным БД посредством ORM-фреймворка
Результат: Высокоуровневое приложение с JPA-маппингом сущностей

Домашнее задание выполняется переписыванием предыдущего на JPA.

Требования:

1) Использовать JPA, Hibernate только в качестве JPA-провайдера.
2) Для решения проблемы N+1 можно использовать специфические для Hibernate аннотации @Fetch и @BatchSize.
3) Добавить сущность "комментария к книге", реализовать CRUD для новой сущности.
4) Покрыть репозитории тестами, используя H2 базу данных и соответствующий H2 Hibernate-диалект для тестов.
5) Не забудьте отключить DDL через Hibernate
6) @Transactional рекомендуется ставить только на методы сервиса.

