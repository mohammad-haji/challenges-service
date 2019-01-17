/*################## challenges ###################*/
DROP TABLE IF EXISTS challenges;
-- CREATE TABLE challenges(id serial PRIMARY KEY, title VARCHAR(100), bio VARCHAR(300));

create table jashnvareh.challenges
(
  title varchar(100),
  id serial not null,
  bio varchar(300),
  description varchar (10000),
  type varchar(200)
);

create unique index challenges_id_uindex
on jashnvareh.challenges (id);

alter table jashnvareh.challenge
  add constraint challenges_pk
    primary key (id);

alter table challenges
  add createdAt timestamp default current_date;

alter table challenges
  add updatedAt timestamp default current_date;



/*################### news #####################*/
DROP TABLE IF EXISTS news;
CREATE TABLE news(id serial PRIMARY KEY, title VARCHAR(100));

create table jashnvareh.news
(
  title varchar(100),
  id serial not null
);

create unique index news_id_uindex
on jashnvareh.news (id);

alter table jashnvareh.news
  add constraint news_pk
    primary key (id);

alter table news
  add challenge_id int;

alter table news
  add constraint news_challenges_id_fk
    foreign key (challenge_id) references challenges
      on update cascade on delete cascade;



/*################## users #################*/
DROP TABLE IF EXISTS users;
CREATE TABLE users(id serial PRIMARY KEY, password VARCHAR(100), email VARCHAR(100),
 token VARCHAR(100000), username VARCHAR(100), bio VARCHAR(100), image VARCHAR(100));

create table jashnvareh.users
(
  password varchar(100),
  email varchar (100),
  token varchar (100000),
  username varchar (100),
  bio varchar (100),
  image varchar (100),
  id serial not null
);

create unique index users_id_uindex
on jashnvareh.users (id);

alter table jashnvareh.users
  add constraint users_pk
    primary key (id);

/*################## records #################*/
DROP TABLE IF EXISTS records;

create table records
(
  title varchar(100),
  id serial not null,
  challenge_id bigserial not null
    constraint records_challenges_id_fk
      references challenges,
  user_id bigserial not null
    constraint records_users_id_fk
      references users
);

create unique index records_id_uindex
on records (id);

alter table records
  add constraint records_pk
    primary key (id);

/*################# user_challenges_assignment#############*/
create table user_challenges_assignment
(
  id serial not null,
  challenge_id int not null,
  user_id int not null
);

create unique index user_challenges_assignment_id_uindex
on user_challenges_assignment (id);

alter table user_challenges_assignment
  add constraint user_challenges_assignment_pk
    primary key (id);

alter table user_challenges_assignment
  add constraint user_challenges_assignment_challenges_id_fk
    foreign key (challenge_id) references challenges;

alter table user_challenges_assignment
  add constraint user_challenges_assignment_users_id_fk
    foreign key (user_id) references users;



/*###################  comments ########################*/
create table comments
(
  id serial not null,
  title varchar(200) not null,
  description varchar(100000),
  user_id int not null
    constraint user_id
      references users
      on update cascade on delete cascade
);

create unique index comments_id_uindex
on comments (id);

alter table comments
  add constraint comments_pk
    primary key (id);

alter table comments
  add challenge_id int not null;

alter table comments
  add constraint comments_challenges_id_fk
    foreign key (challenge_id) references challenges;

alter table comments
  add createdat timestamp default current_date;

alter table comments
  add updatedat timestamp default current_date;





