INSERT INTO author(name) VALUES ('Михаил Булгаков');
INSERT INTO author(name) VALUES ('Антуан де Сент-Экзюпери');
INSERT INTO author(name) VALUES ('Александр Дюма');
INSERT INTO author(name) VALUES ('Аркадий и Борис Стругацкие');

INSERT INTO genre (name) VALUES ('Роман');
INSERT INTO genre (name) VALUES ('Проза');
INSERT INTO genre (name) VALUES ('Фантастика');
INSERT INTO genre (name) VALUES ('Приключения');

INSERT INTO book(author_id, genre_id, name) VALUES (1, 1, 'Мастер и Маргарита');
INSERT INTO book(author_id, genre_id, name) VALUES (1, 2, 'Белая гвардия');
INSERT INTO book(author_id, genre_id, name) VALUES (1, 3, 'Собачье сердце');
INSERT INTO book(author_id, genre_id, name) VALUES (2, 2, 'Маленький принц');
INSERT INTO book(author_id, genre_id, name) VALUES (3, 4, 'Граф Монте-Кристо');
INSERT INTO book(author_id, genre_id, name) VALUES (4, 3, 'Трудно быть Богом');
INSERT INTO book(author_id, genre_id, name) VALUES (4, 3, 'Пикник на обочине');
INSERT INTO book(author_id, genre_id, name) VALUES (4, 3, 'Улитка на склоне');
INSERT INTO book(author_id, genre_id, name) VALUES (4, 3, 'Обитаемый остров');
INSERT INTO book(author_id, genre_id, name) VALUES (4, 3, 'Отель у погибшего альпениста');

