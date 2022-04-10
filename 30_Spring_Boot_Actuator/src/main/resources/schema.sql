drop table if exists genre CASCADE;
create table genre
(
    id   bigserial,
    name varchar(255) not null unique,
    CONSTRAINT genre_pk PRIMARY KEY (id)
);


drop table if exists author CASCADE;
create table author
(
    id         bigserial,
    name varchar(255) not null,
    CONSTRAINT author_pk PRIMARY KEY (id)
);


drop table if exists book CASCADE;
create table book
(
    id        bigserial,
    title      varchar(255) not null,
    author_id bigint       not null,
    genre_id  bigint       not null,
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
