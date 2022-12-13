create table book
(
    id     serial,
    author_id int references author(id),
    title  varchar,
    page_number int not null default 0,
    release_date date,
    primary key (id)
);