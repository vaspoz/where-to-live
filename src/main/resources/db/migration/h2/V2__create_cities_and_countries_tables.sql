create table countries (
    id                  int primary key auto_increment,
    country             varchar,
) as select * from CSVREAD('src/main/resources/db/migration/countries.csv');

create table cities (
    id          int primary key auto_increment,
    city        varchar,
    country_id  int,
    foreign key (country_id) references countries(id)
)  as select * from CSVREAD('src/main/resources/db/migration/cities.csv');