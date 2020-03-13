create table LOGS (
    id                  int primary key auto_increment,
    username            varchar not null,
    action              varchar not null,
    basecountry         varchar,
    basecity            varchar,
    comparecountry      varchar,
    comments            varchar,
    createdatetime      TIMESTAMP not null
);