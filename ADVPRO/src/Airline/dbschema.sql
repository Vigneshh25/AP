CREATE TABLE Flight (
    flight_number VARCHAR(10) PRIMARY KEY,
    aircraft_model VARCHAR(50),
    aircraft_capacity INT,
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP,
    source_airport VARCHAR(10),
    destination_airport VARCHAR(10),
    pricing_strategy VARCHAR(20),
    seat_map_economy INT,
    seat_map_business INT,
    seat_map_first_class INT
);
CREATE TABLE Passenger (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    ticket_number VARCHAR(20),
    seat_number VARCHAR(10),
    flight_number VARCHAR(10),
    FOREIGN KEY (flight_number) REFERENCES Flight(flight_number)
);
CREATE TABLE Baggage (
    id SERIAL PRIMARY KEY,
    passenger_id INT,
    weight DOUBLE,
    FOREIGN KEY (passenger_id) REFERENCES Passenger(id)
);
CREATE TABLE Seat (
    seat_number VARCHAR(10) PRIMARY KEY,
    seat_type VARCHAR(20),
    flight_number VARCHAR(10),
    passenger_id INT,
    FOREIGN KEY (flight_number) REFERENCES Flight(flight_number),
    FOREIGN KEY (passenger_id) REFERENCES Passenger(id)
);
CREATE TABLE Schedule (
    flight_number VARCHAR(10) PRIMARY KEY,
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP,
    source_airport VARCHAR(10),
    destination_airport VARCHAR(10),
    FOREIGN KEY (flight_number) REFERENCES Flight(flight_number)
);
CREATE TABLE Ticket (
    ticket_number VARCHAR(20) PRIMARY KEY,
    flight_number VARCHAR(10),
    passenger_id INT,
    seat_number VARCHAR(10),
    price DOUBLE,
    FOREIGN KEY (flight_number) REFERENCES Flight(flight_number),
    FOREIGN KEY (passenger_id) REFERENCES Passenger(id)
);
CREATE TABLE Airport (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100)
);
