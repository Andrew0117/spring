
drop view if exists vwUserWithRole;
drop table IF EXISTS osoba;
drop table IF EXISTS users;
drop table IF EXISTS roles;

CREATE TABLE IF NOT EXISTS `osoba` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`e_mail` VARCHAR(100) NOT NULL,
	`home_phone` VARCHAR(100) NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`office_phone` VARCHAR(100) NOT NULL,
	`photo_of_a_person` LONGBLOB NULL DEFAULT NULL,
	`surname` VARCHAR(100) NOT NULL,
	constraint PK_osoba primary key (id)
);

create table IF NOT EXISTS roles (
   id                   bigint(20)          NOT NULL AUTO_INCREMENT,
   authority            varchar(45)         not null,
   constraint PK_ROLES primary key (id)
);

create table IF NOT EXISTS users (
   id                   bigint(20)       NOT NULL AUTO_INCREMENT,
   name                 varchar(93)      not null,
   pass                 varchar(101)     not null,
   active               int              not null,
   fkRole               bigint(20)              not null,
   constraint PK_USERS primary key (id)
);

alter table users
   add constraint FK_Users_Ref_Roles foreign key (fkRole)
      references roles (id);

create unique index UI_NAME on users ( name ASC );

create view vwUserWithRole
as
select u.id as userID, u.name, u.pass, u.active, r.id as roleID, r.authority
from users u
join roles r on r.id = u.fkRole;
