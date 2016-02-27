create table players (
    id bytea not null,
    created_date timestamp not null,
    email varchar(255) not null,
    last_modified_date timestamp not null,
    name varchar(255) not null,
    version int8 not null,
    primary key (id)
);

alter table players add constraint UK_pnrwm9bkjel7qss1ekm05j953 unique (email);
