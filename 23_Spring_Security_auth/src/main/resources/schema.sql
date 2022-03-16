DROP TABLE IF EXISTS author CASCADE;
CREATE TABLE author(
                       id BIGSERIAL,
                       name CHARACTER VARYING(255),
                       CONSTRAINT author_pk PRIMARY KEY (id)
);


DROP TABLE IF EXISTS genre CASCADE;
CREATE TABLE genre(
                      id BIGSERIAL,
                      name CHARACTER VARYING(50),
                      CONSTRAINT genre_pk PRIMARY KEY (id)
);


DROP TABLE IF EXISTS book CASCADE;
CREATE TABLE book(
                     id BIGSERIAL,
                     author_id BIGINT NOT NULL,
                     genre_id BIGINT NOT NULL,
                     title CHARACTER VARYING(50),
                     CONSTRAINT book_pk PRIMARY KEY (id)
);
ALTER TABLE book ADD CONSTRAINT book_fk_01 FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE;
ALTER TABLE book ADD CONSTRAINT book_fk_02 FOREIGN KEY (genre_id)  REFERENCES genre  (id) ON DELETE CASCADE;


DROP TABLE IF EXISTS note CASCADE;
CREATE TABLE note(
                       id BIGSERIAL,
                       book_id BIGINT NOT NULL,
                       note CHARACTER VARYING(255),
                       CONSTRAINT note_pk PRIMARY KEY (id)
);
ALTER TABLE note ADD CONSTRAINT book_note_fk_01 FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE;

