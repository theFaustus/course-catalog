create table public.instructors
(
    id         integer   not null
        primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    first_name       varchar(255),
    last_name       varchar(255),
    title varchar(255)
);

alter table public.instructors
    owner to postgres;

INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (0, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (1, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (2, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (3, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (4, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (5, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (6, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (7, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (8, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (9, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (10, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (11, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (12, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (13, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (14, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (15, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (16, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (17, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (18, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (19, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (20, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (21, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (22, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (23, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (24, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (25, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (26, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (27, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (28, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (29, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (30, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (31, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (32, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (33, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (34, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (35, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Thomas', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (36, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (37, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (38, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (39, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (40, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (41, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (42, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (43, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Miller', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (44, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (45, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (46, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (47, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (48, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (49, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (50, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (51, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (52, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (53, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Thomas', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (54, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (55, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (56, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (57, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (58, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (59, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (60, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (61, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (62, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (63, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (64, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (65, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (66, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (67, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Thomas', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (68, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (69, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (70, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (71, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (72, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (73, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Thomas', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (74, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Thomas', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (75, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (76, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (77, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Miller', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (78, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (79, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (80, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (81, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (82, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (83, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (84, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (85, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (86, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (87, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (88, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (89, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (90, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (91, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Hernandez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (92, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (93, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (94, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (95, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (96, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Jackson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (97, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (98, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (99, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (100, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (101, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (102, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (103, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Miller', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (104, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Jackson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (105, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Liam', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (106, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (107, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (108, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (109, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (110, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (111, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (112, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (113, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (114, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (115, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (116, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (117, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (118, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (119, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (120, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (121, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (122, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (123, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (124, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (125, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (126, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bob', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (127, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Thomas', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (128, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (129, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Thomas', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (130, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (131, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (132, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (133, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (134, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (135, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (136, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (137, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (138, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Jackson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (139, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (140, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (141, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (142, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bautista', 'Miller', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (143, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (144, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (145, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (146, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (147, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (148, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (149, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Miller', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (150, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Johnson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (151, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (152, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (153, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (154, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (155, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (156, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Jones', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (157, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (158, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (159, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (160, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Miller', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (161, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (162, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (163, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (164, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Lopez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (165, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Garcia', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (166, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Jackson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (167, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Hernandez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (168, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (169, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (170, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (171, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (172, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Jackson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (173, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Benicio', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (174, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (175, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (176, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (177, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Mateo', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (178, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thiago', 'Moore', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (179, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Hernandez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (180, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (181, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (182, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruno', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (183, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Jackson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (184, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michael', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (185, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Williams', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (186, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Anderson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (187, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Rodriguez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (188, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Wilson', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (189, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Martinez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (190, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Felipe', 'Davis', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (191, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Gonzalez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (192, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Ciro', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (193, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (194, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tony', 'Hernandez', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (195, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Smith', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (196, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Taylor', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (197, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Juan', 'Brown', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (198, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Noah', 'Martin', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (199, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Bruce', 'Martinez', 'Mr.');

create view public.instructor_aggregate_vw(id, created_at, updated_at, name) as
SELECT instructors.id,
       instructors.created_at,
       instructors.updated_at,
       (((instructors.title::text || ' '::text) || instructors.first_name::text) || ' '::text) ||
       instructors.last_name::text AS name
FROM public.instructors;

alter table public.instructor_aggregate_vw
    owner to postgres;

