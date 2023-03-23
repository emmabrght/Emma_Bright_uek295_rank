--USERS
insert into users (id, username, password)
values ('001', 'user', 'uek295' ),
('002', 'user2', 'uek295_2')
 ON CONFLICT DO NOTHING;

--ROLES
INSERT INTO roles(id, name)
VALUES ('010', 'USER')
ON CONFLICT DO NOTHING;

--AUTHORITIES
INSERT INTO authorities(id, name)
VALUES ('100', 'DEFAULT')
ON CONFLICT DO NOTHING;

--assign roles to users
insert into users_roles (user_id, role_id)
values ('001', '010'),
       ('002', '010')
 ON CONFLICT DO NOTHING;

--assign authorities to roles
INSERT INTO roles_authorities(role_id, authority_id)
VALUES ('010', '100')
 ON CONFLICT DO NOTHING;