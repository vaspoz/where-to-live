create table CITY_RATES_FULL (
    id                  serial primary key,
    username            varchar not null,
    action              varchar not null,
    basecountry         varchar,
    basecity            varchar,
    comparecountry      varchar,
    comments            varchar,
    createdatetime      TIMESTAMP not null
) ;