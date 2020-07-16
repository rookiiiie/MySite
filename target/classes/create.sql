use users;
create table type(type_name varchar(50));


create table article
(
article_id int primary key auto_increment,
article_name varchar(50),
content text,
add_time date,
views int,
type_name varchar(50)
);

create table users
(
name varchar(20) primary key,
password varchar(20),
type varchar(20)
);

insert into users values ('gusang','gusang','files');
insert into users values ('gusang2','GuSang','articles');
insert into users values('gusang3','GuSang','home');

insert into article(article_name, content, add_time, views, type_name) values ('article4','content~!','2020-06-28',1,'PAT甲级');

-- create table quotes
-- (
-- content text,
-- author varchar(50)
-- );