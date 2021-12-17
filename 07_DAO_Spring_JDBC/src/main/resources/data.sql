INSERT INTO author(id, name) VALUES (1, 'Михаил Булгаков');
INSERT INTO author(id, name) VALUES (2, 'Антуан де Сент-Экзюпери');
INSERT INTO author(id, name) VALUES (3, 'Александр Дюма');
INSERT INTO author(id, name) VALUES (4, 'Аркадий и Борис Стругацкие');


INSERT INTO genre (id, name) VALUES (1, 'Роман');
INSERT INTO genre (id, name) VALUES (2, 'Проза');
INSERT INTO genre (id, name) VALUES (3, 'Фантастика');
INSERT INTO genre (id, name) VALUES (4, 'Приключения');

INSERT INTO book(id, author_id, genre_id, name) VALUES (1, 1, 1, 'Мастер и Маргарита');
INSERT INTO book(id, author_id, genre_id, name) VALUES (2, 1, 2, 'Белая гвардия');
INSERT INTO book(id, author_id, genre_id, name) VALUES (3, 1, 3, 'Собачье сердце');
INSERT INTO book(id, author_id, genre_id, name) VALUES (4, 2, 2, 'Маленький принц');
INSERT INTO book(id, author_id, genre_id, name) VALUES (5, 3, 4, 'Граф Монте-Кристо');

INSERT INTO book(id, author_id, genre_id, name) VALUES (6, 4, 3, 'Трудно быть Богом');
INSERT INTO book(id, author_id, genre_id, name) VALUES (7, 4, 3, 'Пикник на обочине');
INSERT INTO book(id, author_id, genre_id, name) VALUES (8, 4, 3, 'Улитка на склоне');
INSERT INTO book(id, author_id, genre_id, name) VALUES (9, 4, 3, 'Обитаемый остров');

--INSERT INTO book(id, author_id, genre_id, book_name) VALUES (10, 4, 3, 'Отель у погибшего альпениста');

