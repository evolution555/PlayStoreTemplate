# --- !Ups

create table item (
  item_id                       varchar(255) not null,
  title                         varchar(255),
  cost                          double,
  description                   varchar(255),
  catagory                      varchar(255),
  address                       varchar(255),
  image                         varchar(255),
  constraint pk_item primary key (item_id)
);
create sequence id_gen;

create table testimony (
  id                            varchar(255) not null,
  title                         varchar(255),
  description                   varchar(255),
  constraint pk_testimony primary key (id)
);
create sequence id;

create table user (
  email                         varchar(255) not null,
  name                          varchar(255),
  role                          varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (email)
);

INSERT INTO user VALUES('admin@coachconlon.com', 'Evan', 'Admin', 'Gh9`rYu9nCD.a!Y');

# --- !Downs

drop table if exists item;
drop sequence if exists id_gen;

drop table if exists testimony;
drop sequence if exists id;

drop table if exists user;

