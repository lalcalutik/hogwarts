-- liquibase formatted sql

-- changeset lalcalutik:1
create index name_index on student (name);

-- changeset lalcalutik:2
create index name_and_color_index on faculty (name, color);