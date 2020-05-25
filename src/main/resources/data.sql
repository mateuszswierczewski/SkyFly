INSERT INTO airports values ('POL-WAR-01', 'Warsaw', 'Poland', 52.175204, 20.966431, 'Chopin Airport');
INSERT INTO airports values ('POL-CRA-01', 'Cracow', 'Poland', 50.064651, 19.944981, 'John Paul II Airport');
INSERT INTO airports values ('POL-GDA-01', 'Gdansk', 'Poland', 54.356030, 18.646120, 'Lech Walesa Airport');

INSERT INTO airplanes values ('BOE001', '717', 2, 'Boening', 70, 'REGIONAL');

INSERT INTO flights(standard_price, start_flight_date_time, airplane_id_airplane, arrival_airport_airport_code, departure_airport_airport_code)
VALUES (120, '2020-6-1 16:00:00', 'BOE001', 'POL-CRA-01', 'POL-WAR-01');
INSERT INTO flights(standard_price, start_flight_date_time, airplane_id_airplane, arrival_airport_airport_code, departure_airport_airport_code)
VALUES (120, '2020-6-2 12:00:00', 'BOE001', 'POL-WAR-01', 'POL-CRA-01');
INSERT INTO flights(standard_price, start_flight_date_time, airplane_id_airplane, arrival_airport_airport_code, departure_airport_airport_code)
VALUES (140, '2020-6-3 12:00:00', 'BOE001', 'POL-GDA-01', 'POL-WAR-01');
INSERT INTO flights(standard_price, start_flight_date_time, airplane_id_airplane, arrival_airport_airport_code, departure_airport_airport_code)
VALUES (140, '2020-6-4 16:00:00', 'BOE001', 'POL-WAR-01', 'POL-GDA-01');