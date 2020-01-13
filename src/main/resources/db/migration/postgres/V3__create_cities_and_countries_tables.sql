create table countries (
    id                  serial primary key,
    country             varchar
);

create table cities (
    id          serial primary key,
    city        varchar,
    country_id  int,
    foreign key (country_id) references countries(id)
);