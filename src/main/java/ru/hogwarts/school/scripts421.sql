alter table student alter constraint age_constraint check (age > 15)
alter table student add constraint name_ubique unique (name)
alter table student alter column name set not null
alter table faculty add constraint name_color_unique unique (name, color)
alter table student alter column age set default 20