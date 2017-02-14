create table countries (
    id                  int primary key auto_increment,
    country             varchar,
);

create table cities (
    id          int primary key auto_increment,
    city        varchar,
    country_id  int,
    foreign key (country_id) references countries(id)
)