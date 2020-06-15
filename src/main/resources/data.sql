INSERT INTO airports values ('POL-WAR-01', 'Warsaw', 'Poland', 52.175204, 20.966431, 'Chopin Airport');
INSERT INTO airports values ('POL-CRA-01', 'Cracow', 'Poland', 50.064651, 19.944981, 'John Paul II Airport');
INSERT INTO airports values ('POL-GDA-01', 'Gdansk', 'Poland', 54.356030, 18.646120, 'Lech Walesa Airport');

INSERT INTO airplanes values ('BOE001', '717', 2, 'Boening', 70, 'REGIONAL');

INSERT INTO flights(standard_price, departure_date_time, estimated_arrival_date_time, airplane_id_airplane, departure_airport_airport_code, arrival_airport_airport_code)
VALUES (120, '2020-7-1 16:00:00', '2020-6-1 17:00:00', 'BOE001', 'POL-WAR-01', 'POL-CRA-01');
INSERT INTO flights(standard_price, departure_date_time, estimated_arrival_date_time, airplane_id_airplane, departure_airport_airport_code, arrival_airport_airport_code)
VALUES (120, '2020-7-2 12:00:00', '2020-6-2 13:00:00', 'BOE001', 'POL-CRA-01', 'POL-WAR-01');
INSERT INTO flights(standard_price, departure_date_time, estimated_arrival_date_time, airplane_id_airplane, departure_airport_airport_code, arrival_airport_airport_code)
VALUES (140, '2020-7-3 12:00:00', '2020-6-3 13:00:00', 'BOE001', 'POL-WAR-01', 'POL-GDA-01');
INSERT INTO flights(standard_price, departure_date_time, estimated_arrival_date_time, airplane_id_airplane, departure_airport_airport_code, arrival_airport_airport_code)
VALUES (140, '2020-7-4 16:00:00', '2020-6-4 17:00:00', 'BOE001', 'POL-GDA-01', 'POL-WAR-01');
INSERT INTO flights(standard_price, departure_date_time, estimated_arrival_date_time, airplane_id_airplane, departure_airport_airport_code, arrival_airport_airport_code)
VALUES (120, '2020-7-5 18:00:00', '2020-6-5 19:00:00', 'BOE001', 'POL-WAR-01', 'POL-CRA-01');

INSERT INTO contacts_details(address, email, phone_number)
VALUES ('Warszawa 1', 'user@example.com', 111111111);
INSERT INTO persons(person_type, birth_date, first_name, last_name, second_name, contact_details)
VALUES ('Passenger', '1990-1-1', 'Jan', 'Kowalski', null, 1);
INSERT INTO passengers VALUES (1);
INSERT INTO users(username, password, user_details)
VALUES ('user', '$2a$10$J4VgZcANdBwp708r5nCnR.QEg4HCTzqUhefnS.D8EVItXYmp9ysqW', 1);