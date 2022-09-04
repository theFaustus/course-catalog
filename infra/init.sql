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

INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (1, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Thomas', 'William', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (2, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Michale', 'Nexter', 'Mr.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (3, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Tatiana', 'Usic', 'Mrs.');
INSERT INTO public.instructors (id, created_at, updated_at, first_name, last_name, title) VALUES (4, '2022-08-28 21:00:19.315552', '2022-08-28 21:00:19.315552', 'Hector', 'Variance', 'Mr.');

create view public.instructor_aggregate_vw(id, created_at, updated_at, name) as
SELECT instructors.id,
       instructors.created_at,
       instructors.updated_at,
       (((instructors.title::text || ' '::text) || instructors.first_name::text) || ' '::text) ||
       instructors.last_name::text AS name
FROM public.instructors;

alter table public.instructor_aggregate_vw
    owner to postgres;

