create table accounts (
    id serial not null primary key,
    name char(128) not null,
    description char(256) not null,
    created_at TIMESTAMPTZ not null default now()
);