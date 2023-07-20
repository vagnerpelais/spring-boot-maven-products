create table users
(
    id UUID DEFAULT uuid_generate_v4() primary key,
    login varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(255) not null
);