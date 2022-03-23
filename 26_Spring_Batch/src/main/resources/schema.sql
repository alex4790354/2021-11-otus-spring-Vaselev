drop table if exists genre;
create table genre(id bigserial, name varchar(255) not null unique);

drop table if exists author;
create table author(id bigserial, first_name varchar(255) not null, last_name varchar(255) not null);

drop table if exists book;
create table book(
    id bigserial,
    name varchar(255) not null,
    author_id bigint not null,
    genre_id bigint not null,
    foreign key (author_id) references author(id) on delete cascade,
    foreign key (genre_id) references genre(id) on delete cascade
);

drop table if exists comment;
create table comment(
    id bigserial,
    text varchar(255) not null,
    time datetime default current_timestamp,
    book_id bigint not null,
    foreign key (book_id) references book(id) on delete cascade
);

drop table if exists mongo_author;
create table mongo_author(author_id bigserial, mongo_id varchar(255) not null unique);

drop table if exists mongo_genre;
create table mongo_genre(genre_id bigserial, mongo_id varchar(255) not null unique);

drop table if exists mongo_book;
create table mongo_book(book_id bigserial, mongo_id varchar(255) not null unique);
