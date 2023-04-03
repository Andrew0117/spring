insert into roles(authority) values('ROLE_USER');
select LAST_INSERT_ID() into @id_roles;

insert into users(name, pass, active, fkRole) values('test@test.com', 'testcom', 1, @id_roles);
insert into users(name, pass, active, fkRole) values('test@test.org', 'testorg', 1, @id_roles);
insert into users(name, pass, active, fkRole) values('test@test.edu', 'testedu', 0, @id_roles);
