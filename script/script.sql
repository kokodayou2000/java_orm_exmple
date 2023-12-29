create database mybatis_t;

use mybatis_t;

create table c_s
(
    c_id int ,
    s_id int
);

create table course_t
(
    c_id   int auto_increment
        primary key,
    c_name varchar(128)
);

create table stu_t
(
    s_id   int auto_increment
        primary key,
    s_name varchar(128)
);

create table test
(
    id   int auto_increment
        primary key,
    name varchar(128)
);

create table user_t
(
    id   int auto_increment
        primary key,
    name varchar(128)
);

