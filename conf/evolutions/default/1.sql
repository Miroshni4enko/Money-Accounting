# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table customer (
  customer_id                   bigint auto_increment not null,
  name                          varchar(255),
  address                       varchar(255),
  phone                         integer,
  constraint pk_customer primary key (customer_id)
);

create table customer_account (
  account_number                bigint auto_increment not null,
  balance                       integer,
  description                   varchar(255),
  customer_customer_id          bigint,
  constraint pk_customer_account primary key (account_number)
);

create table transaction (
  transaction_id                bigint auto_increment not null,
  external_account_number       bigint,
  operation                     integer,
  amount                        integer,
  description                   varchar(255),
  date                          timestamp,
  customer_account_account_number bigint,
  constraint ck_transaction_operation check ( operation in (0,1)),
  constraint pk_transaction primary key (transaction_id)
);

alter table customer_account add constraint fk_customer_account_customer_customer_id foreign key (customer_customer_id) references customer (customer_id) on delete restrict on update restrict;
create index ix_customer_account_customer_customer_id on customer_account (customer_customer_id);

alter table transaction add constraint fk_transaction_customer_account_account_number foreign key (customer_account_account_number) references customer_account (account_number) on delete restrict on update restrict;
create index ix_transaction_customer_account_account_number on transaction (customer_account_account_number);


# --- !Downs

alter table customer_account drop constraint if exists fk_customer_account_customer_customer_id;
drop index if exists ix_customer_account_customer_customer_id;

alter table transaction drop constraint if exists fk_transaction_customer_account_account_number;
drop index if exists ix_transaction_customer_account_account_number;

drop table if exists customer;

drop table if exists customer_account;

drop table if exists transaction;

