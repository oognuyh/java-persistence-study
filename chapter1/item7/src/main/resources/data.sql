 
insert into author (age, name, genre, id) values (23, 'Mark Janel', 'Anthology', 1); 
insert into author (age, name, genre, id) values (43, 'Olivia Goy', 'Horror', 2);
insert into author (age, name, genre, id) values (51, 'Quartis Young', 'Anthology', 3);
insert into author (age, name, genre, id) values (34, 'Joana Nimar', 'History', 4);
insert into author (age, name, genre, id) values (38, 'Alicia Tom', 'Anthology', 5);
insert into author (age, name, genre, id) values (56, 'Katy Loin', 'Anthology', 6);
insert into author (age, name, genre, id) values (23, 'Wuth Troll', 'Anthology', 7);

insert into book (isbn, title, author_id, id) values ('001-JN', 'A History of Ancient Prague', 4, 1);
insert into book (isbn, title, author_id, id) values ('002-JN', 'A People''s History', 4, 2);
insert into book (isbn, title, author_id, id) values ('003-JN', 'History Day', 4, 3);
insert into book (isbn, title, author_id, id) values ('001-MJ', 'The Beatles Anthology', 1, 4);
insert into book (isbn, title, author_id, id) values ('001-OG', 'Carrie', 2, 5);
insert into book (isbn, title, author_id, id) values ('002-OG', 'House Of Pain', 2, 6);
insert into book (isbn, title, author_id, id) values ('001-AT', 'Anthology 2000', 5, 7);

insert into parent (id, name, age) values (1, 'A', 23);
insert into parent (id, name, age) values (2, 'B', 43);
insert into parent (id, name, age) values (3, 'C', 51);
insert into parent (id, name, age) values (4, 'D', 34);
insert into parent (id, name, age) values (5, 'E', 38);
insert into parent (id, name, age) values (6, 'F', 56);
insert into parent (id, name, age) values (7, 'G', 23);

insert into child (id, name, age, parent_id) values (1, 'A''', 2, 1);
insert into child (id, name, age, parent_id) values (2, 'B''', 15, 2);
insert into child (id, name, age, parent_id) values (3, 'A''''', 1, 1);
insert into child (id, name, age, parent_id) values (4, 'C''', 26, 3);
insert into child (id, name, age, parent_id) values (5, 'D''', 10, 4);
insert into child (id, name, age, parent_id) values (6, 'F''', 29, 6);
insert into child (id, name, age, parent_id) values (7, 'E''', 7, 5);
insert into child (id, name, age, parent_id) values (8, 'G''', 2, 7);