create table country_rates (
    id                  serial primary key,
    base_country         varchar not null,
    base_city            varchar not null,
    compared_with_country varchar not null,
    compared_with_city    varchar not null,
    expenses            real not null,
    salary              real not null,
    overall             real not null
);