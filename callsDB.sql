CREATE DATABASE  IF NOT EXISTS `callsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `callsdb`;
-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: callsdb
-- ------------------------------------------------------
-- Server version	5.6.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `daysoff`
--

DROP TABLE IF EXISTS `daysoff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daysoff` (
  `Days_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Employee_ID` int(11) NOT NULL,
  `day_Off` date NOT NULL,
  PRIMARY KEY (`Days_ID`),
  KEY `Employee_idx` (`Employee_ID`),
  CONSTRAINT `Employee` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daysoff`
--

LOCK TABLES `daysoff` WRITE;
/*!40000 ALTER TABLE `daysoff` DISABLE KEYS */;
/*!40000 ALTER TABLE `daysoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hours`
--

DROP TABLE IF EXISTS `hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hours` (
  `Shift_Date` date NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  `Hours_Worked` int(11) DEFAULT NULL,
  PRIMARY KEY (`Shift_Date`,`Employee_ID`),
  KEY `Physician_ID_idx` (`Employee_ID`),
  CONSTRAINT `Physician_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hours`
--

LOCK TABLES `hours` WRITE;
/*!40000 ALTER TABLE `hours` DISABLE KEYS */;
/*!40000 ALTER TABLE `hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physician`
--

DROP TABLE IF EXISTS `physician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `physician` (
  `Employee_ID` int(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(80) NOT NULL,
  `Last_Name` varchar(80) NOT NULL,
  `Birth_Date` date DEFAULT NULL,
  `Start_Employment_Date` date NOT NULL,
  `End_Employment_Date` date DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Employee_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician`
--

LOCK TABLES `physician` WRITE;
/*!40000 ALTER TABLE `physician` DISABLE KEYS */;
/*!40000 ALTER TABLE `physician` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shift` (
  `Shift_Date` date NOT NULL,
  `Employee_ID` int(11) DEFAULT NULL,
  `Day_Type` int(11) DEFAULT NULL,
  `Comments` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`Shift_Date`),
  KEY `Employee_ID_idx` (`Employee_ID`),
  CONSTRAINT `Employee_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-06 14:08:29
