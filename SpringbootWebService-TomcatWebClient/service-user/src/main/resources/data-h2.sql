insert into roles(authority) values('ROLE_USER');

insert into users(name, pass, active, fkRole) values('test@test.com', 'testcom', 1, select id from roles where authority = 'ROLE_USER');
insert into users(name, pass, active, fkRole) values('test@test.org', 'testorg', 1, select id from roles where authority = 'ROLE_USER');
insert into users(name, pass, active, fkRole) values('test@test.edu', 'testedu', 0, select id from roles where authority = 'ROLE_USER');
