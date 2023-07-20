create extension if not exists "uuid-ossp";

create table products
(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(255) not null,
    price double precision not null,
    type varchar(255) not null,
    created_at timestamp with time zone,
    updated_at timestamp with time zone
);