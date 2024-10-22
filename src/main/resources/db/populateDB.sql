DELETE FROM user_role;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE meal_seq RESTART WITH 1;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date, description, calories, user_id) VALUES
                                                             ('2023-10-01', 'Завтрак: Овсянка с ягодами', 350, 100000),
                                                             ('2023-10-01', 'Обед: Куриный салат с овощами', 400, 100001),
                                                             ('2023-10-01', 'Ужин: Тушеная говядина с картофелем', 600, 100000),
                                                             ('2023-10-02', 'Завтрак: Сырники с медом', 500, 100001),
                                                             ('2023-10-02', 'Обед: Греческий салат', 300, 100000),
                                                             ('2023-10-02', 'Ужин: Жареные креветки с рисом', 550, 100001),
                                                             ('2023-10-03', 'Завтрак: Яичница с беконом', 450, 100000),
                                                             ('2023-10-03', 'Обед: Суп с фрикадельками', 400, 100001),
                                                             ('2023-10-03', 'Ужин: Стейк с запеченными овощами', 700, 100000),
                                                             ('2023-10-04', 'Завтрак: Творог с фруктами', 300, 100001);