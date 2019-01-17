-- challenges
create table challenges
(
  title varchar(100) not null,
  id serial not null,
  bio varchar(10000),
  description varchar(10000),
  type varchar(200),
  createdAt timestamp default current_date,
  updatedAt timestamp default current_date
);

create unique index challenges_id_uindex
on challenges (id);

alter table challenges
  add constraint challenges_pk
    primary key (id);

-- news

create table news
(
  title varchar(1000) not null,
  id serial not null,
  createdAt timestamp default current_date,
  updatedAt timestamp default current_date
);

create unique index news_id_uindex
on news (id);

alter table news
  add constraint news_pk
    primary key (id);

-- user

create table users
(
  password varchar(100) not null,
  email varchar(100),
  token varchar(1000000),
  username varchar(100),
  bio varchar(1000),
  image varchar(1000),
  id serial not null,
  createdAt timestamp default current_date,
  updatedAt timestamp default current_date
);

create unique index users_id_uindex
on users (id);

alter table users
  add constraint users_pk
    primary key (id);

-- records

create table records
(
  title varchar(100),
  id serial not null,
  challenge_id bigint not null
    constraint records_challenges_id_fk
      references challenges
      on update cascade on delete cascade,
  user_id bigint
    constraint records_users_id_fk
      references users,
  createdAt timestamp default current_date,
  updatedAt timestamp default current_date
);

create unique index records_id_uindex
on records (id);

alter table records
  add constraint records_pk
    primary key (id);


-- user_challenges_assignment

create table user_challenges_assignment
(
  id serial not null,
  challenge_id bigint
    constraint user_challenges_assignment_challenges_id_fk
      references challenges,
  user_id bigint
    constraint user_challenges_assignment_users_id_fk
      references users,
  createdAt timestamp default current_date,
  updatedAt timestamp default current_date
);

create unique index user_challenges_assignment_id_uindex
on user_challenges_assignment (id);

alter table user_challenges_assignment
  add constraint user_challenges_assignment_pk
    primary key (id);

-- comments

create table comments
(
  id serial not null,
  title varchar(1000),
  description varchar(10000),
  user_id bigint
    constraint comments_users_id_fk
      references users,
  createdAt timestamp default current_date,
  updatedAt timestamp default current_date
);

create unique index comments_id_uindex
on comments (id);

alter table comments
  add constraint comments_pk
    primary key (id);






