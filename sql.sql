
use busService;

CREATE TABLE Admin (
    adminID INT PRIMARY KEY,
    adminUsername VARCHAR(50) NOT NULL,
    adminPassword VARCHAR(100) NOT NULL
);

CREATE TABLE User (
    userID INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    userPassword VARCHAR(100) NOT NULL,
    fullName VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20)
);

CREATE TABLE Feedback (
    feedbackID INT PRIMARY KEY,
    message TEXT NOT NULL,
    submitDate DATE NOT NULL,
    userID INT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE Route (
    routeID INT PRIMARY KEY
);

CREATE TABLE Stop (
    stopID INT PRIMARY KEY,
    stopName VARCHAR(100) NOT NULL
);

CREATE TABLE Route_Stop (
    routeID INT,
    stopID INT,
    stopOrder INT, -- vị trí của stop trong route
    PRIMARY KEY (routeID, stopID),
    FOREIGN KEY (routeID) REFERENCES Route(routeID),
    FOREIGN KEY (stopID) REFERENCES Stop(stopID)
);

CREATE TABLE Bus (
    busID INT PRIMARY KEY,
    licensePlate VARCHAR(20) NOT NULL,
    capacity INT NOT NULL,
    routeID INT,
    FOREIGN KEY (routeID) REFERENCES Route(routeID)
);

CREATE TABLE Trip (
    tripID INT PRIMARY KEY,
    routeID INT NOT NULL,
    busID INT NOT NULL,
    FOREIGN KEY (routeID) REFERENCES Route(routeID),
    FOREIGN KEY (busID) REFERENCES Bus(busID)
);

CREATE TABLE StopTime (
    tripID INT,
    stopID INT,
    arrivalTime TIME,
    departureTime TIME,
    PRIMARY KEY (tripID, stopID),
    FOREIGN KEY (tripID) REFERENCES Trip(tripID),
    FOREIGN KEY (stopID) REFERENCES Stop(stopID)
);



ALTER TABLE StopTime 
drop column stop_order;

CREATE TABLE Booking (
    id INT PRIMARY KEY,
    tripID INT NOT NULL,
    fromStopID INT NOT NULL,
    toStopID INT NOT NULL,
    userID INT NOT NULL,
    startTime TIME,
    FOREIGN KEY (tripID) REFERENCES Trip(tripID),
    FOREIGN KEY (fromStopID) REFERENCES Stop(stopID),
    FOREIGN KEY (toStopID) REFERENCES Stop(stopID),
    FOREIGN KEY (userID) REFERENCES User(userID)
);














