create table person (
    id serial primary key,
    name text,
    age integer check (age > 0),
    driver_license boolean,
    car_id integer references car (id)
);

create table car (
    id serial primary key,
    brand text,
    model text,
    price money
);