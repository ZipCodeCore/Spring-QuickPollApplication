-->  poll creation --

insert into poll (poll_id, question) values (1, 'What is your favorite color?');
insert into poll (poll_id, question) values (2, 'What is your favorite car?');
insert into poll (poll_id, question) values (3, 'What is your favorite city?');

--> option creation --

insert into option (option_id, option_value, poll_id) values (1, 'Red', 1);
insert into option (option_id, option_value, poll_id) values (2, 'Blue', 1);
insert into option (option_id, option_value, poll_id) values (3, 'Green', 1);
insert into option (option_id, option_value, poll_id) values (4, 'Yellow', 1);

insert into option (option_id, option_value, poll_id) values (5, 'BMW', 2);
insert into option (option_id, option_value, poll_id) values (6, 'Audi', 2);
insert into option (option_id, option_value, poll_id) values (7, 'Mercedes', 2);
insert into option (option_id, option_value, poll_id) values (8, 'Masserati', 2);

insert into option (option_id, option_value, poll_id) values (9, 'Philly', 3);
insert into option (option_id, option_value, poll_id) values (10, 'Boston', 3);
insert into option (option_id, option_value, poll_id) values (11, 'New York', 3);
insert into option (option_id, option_value, poll_id) values (12, 'Los Angeles', 3);