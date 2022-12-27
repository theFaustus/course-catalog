SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE instructors;
TRUNCATE TABLE courses;
SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO instructors (id, name, created_at, updated_at)
VALUES (-1, 'Bruce Eckel', '2022-08-24 13:38:18.056924', '2022-08-24 13:38:18.056924');

INSERT INTO courses (id, programming_language, programming_language_description, created_at, updated_at, category, name, instructor_id)
VALUES (-1, 'Java', 'description', '2022-08-22 20:22:36.510984', '2022-08-22 20:22:36.572486', 'TUTORIAL', 'Kotlin course', -1);
INSERT INTO courses (id, programming_language, programming_language_description, created_at, updated_at, category, name, instructor_id)
VALUES (-2, 'Java', 'description', '2022-08-22 20:25:12.555538', '2022-08-22 20:25:12.555538', 'TUTORIAL', 'Java Development', -1);
INSERT INTO courses (id, programming_language, programming_language_description, created_at, updated_at, category, name, instructor_id)
VALUES (-3, 'Java', 'description', '2022-08-22 20:25:12.555538', '2022-08-22 20:25:12.555538', 'INTERVIEW',
        'How To Conduct a Technical Interview', -1);
