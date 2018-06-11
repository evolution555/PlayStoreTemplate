# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table item (
  item_id                       varchar(255) not null,
  title                         varchar(255),
  cost                          double,
  description                   varchar(255),
  catagory                      varchar(255),
  availability                  boolean,
  quantity                      integer,
  image                         varchar(255),
  production_cost               double,
  shipping_cost                 double,
  profit                        double,
  constraint pk_item primary key (item_id)
);
create sequence id_gen;

create table order (
  order_id                      varchar(255) not null,
  cost                          double,
  shipping_cost                 double,
  shipping_address              varchar(255),
  discount_applied              boolean,
  discount_ammount              integer,
  constraint pk_order primary key (order_id)
);
create sequence order_id_gen;

create table user (
  email                         varchar(255) not null,
  name                          varchar(255),
  role                          varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (email)
);


# --- !Downs

drop table if exists item;
drop sequence if exists id_gen;

drop table if exists order;
drop sequence if exists order_id_gen;

drop table if exists user;

