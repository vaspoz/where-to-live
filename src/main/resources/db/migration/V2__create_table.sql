create table country_rates (
    id                  int primary key auto_increment,
    base_country         varchar not null,
    base_city            varchar not null,
    compared_with_country varchar not null,
    compared_with_city    varchar not null,
    expenses            double not null,
    salary              double not null,
    overall             double not null
);