CREATE DATABASE  IF NOT EXISTS `busservice` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `busservice`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: busservice
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminID` int NOT NULL AUTO_INCREMENT,
  `adminUsername` varchar(50) NOT NULL,
  `adminPassword` varchar(100) NOT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (3,'Nam11','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tripID` int NOT NULL,
  `fromStopID` int NOT NULL,
  `toStopID` int NOT NULL,
  `userID` int NOT NULL,
  `startTime` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tripID` (`tripID`),
  KEY `fromStopID` (`fromStopID`),
  KEY `toStopID` (`toStopID`),
  KEY `booking_ibfk_4` (`userID`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`tripID`) REFERENCES `trip` (`tripID`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`fromStopID`) REFERENCES `stop` (`stopID`),
  CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`toStopID`) REFERENCES `stop` (`stopID`),
  CONSTRAINT `booking_ibfk_4` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (5,333,7,9,5,'16:00:00'),(6,333,7,9,5,'16:00:00'),(7,222,5,6,5,'12:00:00'),(8,333,7,9,5,'16:00:00');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `busID` int NOT NULL,
  `licensePlate` varchar(20) NOT NULL,
  `capacity` int NOT NULL,
  `routeID` int DEFAULT NULL,
  PRIMARY KEY (`busID`),
  KEY `routeID` (`routeID`),
  CONSTRAINT `bus_ibfk_1` FOREIGN KEY (`routeID`) REFERENCES `route` (`routeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,'61A-12345',10,101),(2,'61A-22222',2,102),(3,'61A-45678',10,103);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedbackID` int NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `submitDate` date NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`feedbackID`),
  KEY `userID` (`userID`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (6,'check','2025-06-09',5),(7,'check','2025-06-10',5);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `routeID` int NOT NULL,
  PRIMARY KEY (`routeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (101),(102),(103);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_stop`
--

DROP TABLE IF EXISTS `route_stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route_stop` (
  `routeID` int NOT NULL,
  `stopID` int NOT NULL,
  `stopOrder` int DEFAULT NULL,
  PRIMARY KEY (`routeID`,`stopID`),
  KEY `stopID` (`stopID`),
  CONSTRAINT `route_stop_ibfk_1` FOREIGN KEY (`routeID`) REFERENCES `route` (`routeID`),
  CONSTRAINT `route_stop_ibfk_2` FOREIGN KEY (`stopID`) REFERENCES `stop` (`stopID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_stop`
--

LOCK TABLES `route_stop` WRITE;
/*!40000 ALTER TABLE `route_stop` DISABLE KEYS */;
INSERT INTO `route_stop` VALUES (101,1,1),(101,2,2),(101,3,3),(102,4,1),(102,5,2),(102,6,3),(103,7,1),(103,8,2),(103,9,3);
/*!40000 ALTER TABLE `route_stop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stop`
--

DROP TABLE IF EXISTS `stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stop` (
  `stopID` int NOT NULL,
  `stopName` varchar(100) NOT NULL,
  PRIMARY KEY (`stopID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stop`
--

LOCK TABLES `stop` WRITE;
/*!40000 ALTER TABLE `stop` DISABLE KEYS */;
INSERT INTO `stop` VALUES (1,'Stop Dinh Hoa'),(2,'Stop Hoa Phu'),(3,'Stop Phu Chanh'),(4,'Stop Ben Cat'),(5,'Stop Hiep An'),(6,'Stop Tuong Binh Hiep'),(7,'Stop Phu Cuong'),(8,'Stop Chanh Nghia'),(9,'Stop Pho Loi'),(111,'Binh Chuan');
/*!40000 ALTER TABLE `stop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stoptime`
--

DROP TABLE IF EXISTS `stoptime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stoptime` (
  `stopID` int NOT NULL,
  `arrivalTime` time DEFAULT NULL,
  `departureTime` time DEFAULT NULL,
  PRIMARY KEY (`stopID`),
  CONSTRAINT `stoptime_ibfk_2` FOREIGN KEY (`stopID`) REFERENCES `stop` (`stopID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stoptime`
--

LOCK TABLES `stoptime` WRITE;
/*!40000 ALTER TABLE `stoptime` DISABLE KEYS */;
INSERT INTO `stoptime` VALUES (1,'07:00:00','07:05:00'),(2,'07:30:00','07:35:00'),(3,'08:00:00','08:05:00'),(4,'11:30:00','11:35:00'),(5,'12:00:00','12:05:00'),(6,'12:30:00','12:35:00'),(7,'16:00:00','16:05:00'),(8,'16:30:00','16:35:00'),(9,'17:00:00','17:05:00'),(111,'17:30:00','17:35:00');
/*!40000 ALTER TABLE `stoptime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `tripID` int NOT NULL,
  `routeID` int NOT NULL,
  `busID` int NOT NULL,
  PRIMARY KEY (`tripID`),
  KEY `routeID` (`routeID`),
  KEY `busID` (`busID`),
  CONSTRAINT `trip_ibfk_1` FOREIGN KEY (`routeID`) REFERENCES `route` (`routeID`),
  CONSTRAINT `trip_ibfk_2` FOREIGN KEY (`busID`) REFERENCES `bus` (`busID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (111,101,1),(222,102,2),(333,103,3);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip_stoptime`
--

DROP TABLE IF EXISTS `trip_stoptime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_stoptime` (
  `tripID` int NOT NULL,
  `stopID` int NOT NULL,
  `stopOrder` int DEFAULT NULL,
  PRIMARY KEY (`tripID`,`stopID`),
  KEY `stopID` (`stopID`),
  CONSTRAINT `trip_stoptime_ibfk_1` FOREIGN KEY (`tripID`) REFERENCES `trip` (`tripID`),
  CONSTRAINT `trip_stoptime_ibfk_2` FOREIGN KEY (`stopID`) REFERENCES `stoptime` (`stopID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_stoptime`
--

LOCK TABLES `trip_stoptime` WRITE;
/*!40000 ALTER TABLE `trip_stoptime` DISABLE KEYS */;
INSERT INTO `trip_stoptime` VALUES (111,1,1),(111,2,2),(111,3,3),(222,4,1),(222,5,2),(222,6,3),(333,7,1),(333,8,2),(333,9,3);
/*!40000 ALTER TABLE `trip_stoptime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `userPassword` varchar(100) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'nam20','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','Tan Nam','nam','403204');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-13 10:58:29
