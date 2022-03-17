DROP TABLE IF EXISTS author CASCADE;
CREATE TABLE author(
       id           BIGSERIAL,
       name         CHARACTER VARYING(255),
       CONSTRAINT author_pk PRIMARY KEY (id)
);


DROP TABLE IF EXISTS genre CASCADE;
CREATE TABLE genre(
      id            BIGSERIAL,
      name          CHARACTER VARYING(50),
      CONSTRAINT genre_pk PRIMARY KEY (id)
);


DROP TABLE IF EXISTS book CASCADE;
CREATE TABLE book(
     id             BIGSERIAL,
     author_id      BIGINT NOT NULL,
     genre_id       BIGINT NOT NULL,
     title          CHARACTER VARYING(50),
     CONSTRAINT book_pk PRIMARY KEY (id)
);
ALTER TABLE book ADD CONSTRAINT book_fk_01 FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE;
ALTER TABLE book ADD CONSTRAINT book_fk_02 FOREIGN KEY (genre_id)  REFERENCES genre  (id) ON DELETE CASCADE;


DROP TABLE IF EXISTS note CASCADE;
CREATE TABLE note(
        id           BIGSERIAL,
        book_id      BIGINT NOT NULL,
        note         CHARACTER VARYING(255),
        CONSTRAINT note_pk PRIMARY KEY (id)
);
ALTER TABLE note ADD CONSTRAINT book_note_fk_01 FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE;


DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
      id            BIGSERIAL,
      username      CHARACTER VARYING(50) NOT NULL,
      password      CHARACTER VARYING(100) NOT NULL,
      full_name     CHARACTER VARYING(100),
      email         CHARACTER VARYING(100),
      enabled       boolean NOT NULL DEFAULT true,
      account_non_expired       boolean NOT NULL DEFAULT true,
      account_non_locked        boolean NOT NULL DEFAULT true,
      credentials_non_expired   boolean NOT NULL DEFAULT true,
      CONSTRAINT user_pk PRIMARY KEY (id)
);

drop table if exists authority;
CREATE TABLE authority (
       id           BIGSERIAL,
       user_id      BIGINT NOT NULL,
       authority    CHARACTER VARYING(50) NOT NULL  --,FOREIGN KEY (user_id) REFERENCES user(id)
);
ALTER TABLE authority ADD CONSTRAINT authority_fk_01 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE UNIQUE INDEX ix_auth_user ON authority (user_id, authority);