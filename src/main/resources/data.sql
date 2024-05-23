-- create dummy data for testing
insert into Customer (user_name, first_name, last_name, email_address, delivery_address, phone_number) values ('henry', 'Henry', 'Chan', 'henry@gmail.com', '99 Tung Tau Wan Rd, Stanley', 26418893);
insert into Customer (user_name, first_name, last_name, email_address, delivery_address, phone_number) values ('peter', 'Peter', 'Ho', 'peter@hotmail.com', '21 Hong Fai Road, Siu Lam, New Territories', 24528060);

insert into Book (isbn13, title, author, price, category, rating) values (9780062316110, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 119.9, 'History', 3.6);
insert into Book (isbn13, title, author, price, category, rating) values (9780553380163, 'A Brief History of Time', 'Stephen Hawking', 78.0, 'Science', 4.7);
insert into Book (isbn13, title, author, price, category, rating) values (9781557427663, 'The Metamorphosis', 'Franz Kafka', 31.2,  'Fiction', 1.5);