create database biblioteca;
use biblioteca;



create table user
(
    user_name varchar(30) not null
        primary key,
    password  varchar(30) not null,
    dni       int unique  not null,
    admin     boolean     not null default false
)

