create table courses
(
    id         integer   not null
        primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    category   varchar(255),
    name       varchar(255)
);

create sequence hibernate_sequence;


