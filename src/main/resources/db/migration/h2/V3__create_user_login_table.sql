create table users (
    id                          int primary key auto_increment,
    first_name                   varchar,
    last_name                    varchar,
    username                    varchar,
    password                    varchar,
    email                       varchar,
    country_origin              varchar,
    is_enabled                  boolean,
    is_credentials_non_expired  boolean,
    is_account_non_locked       boolean,
    is_account_non_expired      boolean
);