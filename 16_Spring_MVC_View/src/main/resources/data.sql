INSERT INTO author(name) VALUES ('Михаил Булгаков');
INSERT INTO author(name) VALUES ('Антуан де Сент-Экзюпери');
INSERT INTO author(name) VALUES ('Александр Дюма');
INSERT INTO author(name) VALUES ('Аркадий и Борис Стругацкие');

INSERT INTO genre (name) VALUES ('Роман');
INSERT INTO genre (name) VALUES ('Проза');
INSERT INTO genre (name) VALUES ('Фантастика');
INSERT INTO genre (name) VALUES ('Приключения');

INSERT INTO book(author_id, genre_id, title) VALUES (1, 1, 'Мастер и Маргарита');
INSERT INTO book(author_id, genre_id, title) VALUES (1, 2, 'Белая гвардия');
INSERT INTO book(author_id, genre_id, title) VALUES (1, 3, 'Собачье сердце');
INSERT INTO book(author_id, genre_id, title) VALUES (2, 2, 'Маленький принц');
INSERT INTO book(author_id, genre_id, title) VALUES (3, 4, 'Граф Монте-Кристо');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Трудно быть Богом');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Пикник на обочине');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Улитка на склоне');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Обитаемый остров');
INSERT INTO book(author_id, genre_id, title) VALUES (4, 3, 'Отель у погибшего альпениста');

INSERT INTO note(book_id, note) VALUES (1,  'Note-01.1 - Мастер');
INSERT INTO note(book_id, note) VALUES (1,  'Note-01.2 - Мастер');
INSERT INTO note(book_id, note) VALUES (2,  'Note-02.1 - гвардия');
INSERT INTO note(book_id, note) VALUES (2,  'Note-02.2 - гвардия');
INSERT INTO note(book_id, note) VALUES (3,  'Note-03.1 - сердце');
INSERT INTO note(book_id, note) VALUES (3,  'Note-03.2 - сердце');
INSERT INTO note(book_id, note) VALUES (3,  'Note-03.3 - сердце');
INSERT INTO note(book_id, note) VALUES (4,  'Note-04.1 - M принц');
INSERT INTO note(book_id, note) VALUES (4,  'Note-04.2 - M принц');
INSERT INTO note(book_id, note) VALUES (5,  'Note-05.1 - Граф MK');
INSERT INTO note(book_id, note) VALUES (5,  'Note-05.2 - Граф MK');
INSERT INTO note(book_id, note) VALUES (5,  'Note-05.3 - Граф MK');
INSERT INTO note(book_id, note) VALUES (6,  'Note-06.1 - Трудно');
INSERT INTO note(book_id, note) VALUES (6,  'Note-06.2 - Трудно');
INSERT INTO note(book_id, note) VALUES (7,  'Note-07.1 - Пикник');
INSERT INTO note(book_id, note) VALUES (7,  'Note-07.2 - Пикник');
INSERT INTO note(book_id, note) VALUES (7,  'Note-07.3 - Пикник');
INSERT INTO note(book_id, note) VALUES (8,  'Note-08.1 - Улитка');
INSERT INTO note(book_id, note) VALUES (8,  'Note-08.2 - Улитка');
INSERT INTO note(book_id, note) VALUES (8,  'Note-08.3 - Улитка');
INSERT INTO note(book_id, note) VALUES (8,  'Note-08.4 - Улитка');
INSERT INTO note(book_id, note) VALUES (9,  'Note-09.1 - остров');
INSERT INTO note(book_id, note) VALUES (9,  'Note-09.1 - остров');
INSERT INTO note(book_id, note) VALUES (10, 'Note-10.1 - Отель');
INSERT INTO note(book_id, note) VALUES (10, 'Note-10.2 - Отель');
INSERT INTO note(book_id, note) VALUES (10, 'Note-10.3 - Отель');
INSERT INTO note(book_id, note) VALUES (10, 'Note-10.4 - Отель');

