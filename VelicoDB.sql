-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: velico
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `boat`
--

DROP TABLE IF EXISTS `boat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boat` (
  `idboat` int NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `length` int DEFAULT NULL,
  `owner` int DEFAULT NULL,
  PRIMARY KEY (`idboat`),
  KEY `owner` (`owner`),
  CONSTRAINT `boat_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `person` (`idperson`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat`
--

LOCK TABLES `boat` WRITE;
/*!40000 ALTER TABLE `boat` DISABLE KEYS */;
INSERT INTO `boat` VALUES (1,'Test',123,1),(4,'Eni',324,9),(5,'Craco',24,9),(9,'romina',234,11);
/*!40000 ALTER TABLE `boat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boat_storage_sum`
--

DROP TABLE IF EXISTS `boat_storage_sum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boat_storage_sum` (
  `idsum` int NOT NULL AUTO_INCREMENT,
  `boat` int DEFAULT NULL,
  `payment` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`idsum`),
  KEY `boat` (`boat`),
  KEY `payment` (`payment`),
  CONSTRAINT `boat_storage_sum_ibfk_1` FOREIGN KEY (`boat`) REFERENCES `boat` (`idboat`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `boat_storage_sum_ibfk_2` FOREIGN KEY (`payment`) REFERENCES `payment` (`idpayment`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boat_storage_sum`
--

LOCK TABLES `boat_storage_sum` WRITE;
/*!40000 ALTER TABLE `boat_storage_sum` DISABLE KEYS */;
INSERT INTO `boat_storage_sum` VALUES (7,4,5,'2020-12-12',3240),(10,4,7,'2019-12-12',3435),(12,4,17,'2022-02-03',3240),(14,5,21,'2022-02-03',240);
/*!40000 ALTER TABLE `boat_storage_sum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `idnotification` int NOT NULL AUTO_INCREMENT,
  `person` int DEFAULT NULL,
  `sum_type` char(255) DEFAULT NULL,
  `message_read` int DEFAULT NULL,
  PRIMARY KEY (`idnotification`),
  KEY `person` (`person`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`person`) REFERENCES `person` (`idperson`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_sum`
--

DROP TABLE IF EXISTS `organization_sum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization_sum` (
  `idsum` int NOT NULL AUTO_INCREMENT,
  `person` int DEFAULT NULL,
  `payment` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`idsum`),
  KEY `person` (`person`),
  KEY `payment` (`payment`),
  CONSTRAINT `organization_sum_ibfk_1` FOREIGN KEY (`person`) REFERENCES `person` (`idperson`),
  CONSTRAINT `organization_sum_ibfk_2` FOREIGN KEY (`payment`) REFERENCES `payment` (`idpayment`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_sum`
--

LOCK TABLES `organization_sum` WRITE;
/*!40000 ALTER TABLE `organization_sum` DISABLE KEYS */;
INSERT INTO `organization_sum` VALUES (1,9,1,'2019-12-12',200),(5,9,10,'2020-12-12',200),(6,9,4,'2018-12-12',200),(7,9,18,'2022-02-03',300);
/*!40000 ALTER TABLE `organization_sum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participant`
--

DROP TABLE IF EXISTS `participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participant` (
  `idparticipant` int NOT NULL AUTO_INCREMENT,
  `boat` int DEFAULT NULL,
  `race` int DEFAULT NULL,
  `payment` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`idparticipant`),
  KEY `boat` (`boat`),
  KEY `race` (`race`),
  KEY `payment` (`payment`),
  CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`boat`) REFERENCES `boat` (`idboat`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `participant_ibfk_2` FOREIGN KEY (`race`) REFERENCES `race` (`idrace`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `participant_ibfk_3` FOREIGN KEY (`payment`) REFERENCES `payment` (`idpayment`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participant`
--

LOCK TABLES `participant` WRITE;
/*!40000 ALTER TABLE `participant` DISABLE KEYS */;
INSERT INTO `participant` VALUES (10,9,5,24,100);
/*!40000 ALTER TABLE `participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `idpayment` int NOT NULL AUTO_INCREMENT,
  `payment_type` char(255) DEFAULT NULL,
  PRIMARY KEY (`idpayment`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'Credit card'),(3,'Credit card'),(4,'Credit card'),(5,'Credit card'),(6,'Credit card'),(7,'Credit card'),(8,'Credit card'),(9,'Credit card'),(10,'Credit card'),(11,'Credit card'),(12,'Credit card'),(13,'Credit card'),(14,'Credit card'),(15,'Credit card'),(16,'Credit card'),(17,'Credit card'),(18,'Transfer'),(19,'Transfer'),(20,'Credit card'),(21,'Transfer'),(22,'Credit card'),(23,'Credit card'),(24,'Credit card');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `idperson` int NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `surname` char(255) DEFAULT NULL,
  `address` char(255) DEFAULT NULL,
  `fiscalcode` char(255) DEFAULT NULL,
  `username` char(255) DEFAULT NULL,
  `password` char(255) DEFAULT NULL,
  `role` char(255) DEFAULT NULL,
  PRIMARY KEY (`idperson`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'test','test','via test','fisctest','test','test','Member'),(9,'Eni','Zeza','via nonloso','zzzeeio768','root','1234','Member'),(10,'Leo','Craco','via notiinteressa','zzcrc4546','leo','leo','Staff'),(11,'eni','zeza','gddsfd','fgd','eni','eni','Member');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `race`
--

DROP TABLE IF EXISTS `race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `race` (
  `idrace` int NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `place` char(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idrace`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `race`
--

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` VALUES (1,'TestRace','TestPlace','2023-12-23'),(5,'romaeef','milano','2023-03-08'),(6,'Beppe','Traina','2020-12-02');
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-14 11:56:07
