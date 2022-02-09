CREATE TABLE IF NOT EXISTS users
(
    id         bigint  not null auto_increment,
    age        integer not null,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    username   varchar(255),
    primary key (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS roles
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
) engine = InnoDB;

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
) engine = InnoDB;

INSERT INTO users (id, age, email, first_name, last_name, password, username)
VALUES (1, 32, 'admin@mail.ru', 'admin', 'admin', '$2a$12$jDe2zrLGneUfjsbsat5zI.ZMDqVPoHje1R/.u3MqcC/T9Zrs1Bn4K',
        'admin');
INSERT INTO users (id, age, email, first_name, last_name, password, username)
VALUES (2, 31, 'user@mail.ru', 'user', 'user', '$2a$12$yapeOKJ4g7JTqnKZCOO5k.1gmaR7MmZ6tzvZUZxXfvlQSAzI72apC', 'user');

INSERT INTO roles (id, name)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name)
VALUES (2, 'ROLE_USER');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id)
VALUES (2, 2);