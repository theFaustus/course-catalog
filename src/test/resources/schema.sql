create table if not exists instructors
(
    id          integer   not null
        primary key,
    created_at  timestamp not null,
    updated_at  timestamp not null,
    description varchar(3000),
    name        varchar(255),
    summary     varchar(3000)
);

create table if not exists courses
(
    id            integer   not null
        primary key,
    created_at    timestamp not null,
    updated_at    timestamp not null,
    category      varchar(255),
    name          varchar(255),
    instructor_id integer
        constraint fk1kswo6qqebbdy2kq0kx6udof7
            references instructors
);

create sequence hibernate_sequence;


