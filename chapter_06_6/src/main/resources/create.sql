create table users (
    id varchar(10) primary key,
    name varchar(20) not null,
    password varchar(10) not null
)
alter table users add column level tinyint not null;
alter table users add column login int not null;
alter table users add column recommend int not null;
alter table users modify column email varchar(20) not null;

create table users (
    id varchar(10) primary key,
    name varchar(20) not null,
    password varchar(10) not null,
    email varchar(20) not null,
    level tinyint not null,
    login int not null,
    recommend int not null
)