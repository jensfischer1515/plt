create table players (
    id uuid not null,
    version int8 not null,
    created_date timestamp not null,
    last_modified_date timestamp not null,
    email varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);

alter table players add constraint uk_players_email unique (email);
