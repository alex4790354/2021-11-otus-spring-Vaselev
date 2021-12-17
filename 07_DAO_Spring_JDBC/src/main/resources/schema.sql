DROP TABLE IF EXISTS author CASCADE;
CREATE TABLE author(
    id INT,
    name CHARACTER VARYING(255),
    CONSTRAINT author_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS genre CASCADE;
CREATE TABLE genre(
    id INT,
    name CHARACTER VARYING(50),
    CONSTRAINT genre_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS book;
CREATE TABLE book(
    id int,
    author_id int NOT NULL,
    genre_id int NOT NULL,
    name CHARACTER VARYING(50),
    CONSTRAINT book_pk PRIMARY KEY (id)
);
ALTER TABLE book ADD CONSTRAINT book_fk_01 FOREIGN KEY (author_id) REFERENCES author (id);
ALTER TABLE book ADD CONSTRAINT book_fk_02 FOREIGN KEY (genre_id)  REFERENCES genre  (id);

