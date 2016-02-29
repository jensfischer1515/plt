create table campaigns (
    id uuid not null,
    version int8 not null,
    created_date timestamp not null,
    last_modified_date timestamp not null,
    name varchar(255) not null,
    active boolean not null,
    primary key (id)
);

alter table campaigns add constraint uk_campaigns_name unique (name);
