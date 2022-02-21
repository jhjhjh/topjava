DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2015-6-1 14:0:0', 'Админ ланч', 510, 100001),
        ('2015-6-1 21:0:0', 'Админ ланч', 1500, 100001),
        ('2015-6-1 07:0:0', 'USER завтра', 510, 100000),
        ('2015-6-1 13:0:0', 'USER обед', 1500, 100000),
        ('2015-6-1 21:0:0', 'USER ужин', 1500, 100000),
        ('2015-6-2 7:0:0', 'USER завтра', 510, 100000),
        ('2015-6-2 13:0:0', 'USER обед', 1500, 100000),
        ('2015-6-2 21:0:0', 'USER ужин', 800, 100000),
        ('2022-2-20 0:59:59', 'USER ужин', 800, 100000);