CREATE DATABASE  IF NOT EXISTS `callsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `callsdb`;
-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: callsdb
-- ------------------------------------------------------
-- Server version	5.6.10

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daysoff`
--

LOCK TABLES `daysoff` WRITE;
/*!40000 ALTER TABLE `daysoff` DISABLE KEYS */;
INSERT INTO `daysoff` VALUES (1,3,'2013-07-08'),(2,3,'2013-07-09'),(3,3,'2013-07-10'),(4,3,'2013-07-11');
/*!40000 ALTER TABLE `daysoff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holidays`
--

DROP TABLE IF EXISTS `holidays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `holidays` (
  `holiday` date NOT NULL,
  PRIMARY KEY (`holiday`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holidays`
--

LOCK TABLES `holidays` WRITE;
/*!40000 ALTER TABLE `holidays` DISABLE KEYS */;
/*!40000 ALTER TABLE `holidays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hours`
--

DROP TABLE IF EXISTS `hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hours` (
  `Year` int(11) NOT NULL,
  `Month` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  `Hours_Worked` int(11) DEFAULT '0',
  PRIMARY KEY (`Year`,`Month`,`Employee_ID`),
  KEY `Physician_ID_idx` (`Employee_ID`),
  CONSTRAINT `Physician_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hours`
--

LOCK TABLES `hours` WRITE;
/*!40000 ALTER TABLE `hours` DISABLE KEYS */;
INSERT INTO `hours` VALUES (2012,7,1,186),(2012,7,2,138),(2012,7,3,138),(2012,7,4,75),(2013,1,1,183),(2013,1,2,186),(2013,1,3,168),(2013,2,1,171),(2013,2,2,153),(2013,2,3,168),(2013,3,1,171),(2013,3,2,216),(2013,3,3,168),(2013,4,1,168),(2013,4,2,156),(2013,4,3,198),(2013,5,1,183),(2013,5,2,168),(2013,5,3,186),(2013,6,1,156),(2013,6,2,171),(2013,6,3,213),(2013,8,1,159),(2013,8,2,123),(2013,8,3,141),(2013,8,4,123),(2013,9,1,162),(2013,9,2,201),(2013,9,3,168);
/*!40000 ALTER TABLE `hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `initialdays`
--

DROP TABLE IF EXISTS `initialdays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `initialdays` (
  `Employee_ID` int(11) NOT NULL,
  `weekdays` int(11) DEFAULT NULL,
  `weekend_days` int(11) DEFAULT NULL,
  `holidays` int(11) DEFAULT NULL,
  PRIMARY KEY (`Employee_ID`),
  CONSTRAINT `employee_fk` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `initialdays`
--

LOCK TABLES `initialdays` WRITE;
/*!40000 ALTER TABLE `initialdays` DISABLE KEYS */;
/*!40000 ALTER TABLE `initialdays` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician`
--

LOCK TABLES `physician` WRITE;
/*!40000 ALTER TABLE `physician` DISABLE KEYS */;
INSERT INTO `physician` VALUES (1,'Physician','Physician','1988-02-12','2011-12-01',NULL,'asfe','4031234567'),(2,'Test','Hello','1600-01-22','2000-01-22',NULL,'sfefe','1112314876'),(3,'Blah','Blah','2006-05-15','2007-08-09',NULL,'sefefe','6305547894'),(4,'sfesfe','asfaef','2006-05-15','2012-07-09','2012-07-20','fsfesf','7894578451');
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
INSERT INTO `shift` VALUES ('2012-07-01',1,1,NULL),('2012-07-02',2,0,NULL),('2012-07-03',3,0,NULL),('2012-07-04',3,0,NULL),('2012-07-05',2,0,NULL),('2012-07-06',2,1,NULL),('2012-07-07',2,1,NULL),('2012-07-08',2,1,NULL),('2012-07-09',1,0,NULL),('2012-07-10',4,0,NULL),('2012-07-11',4,0,NULL),('2012-07-12',4,0,NULL),('2012-07-13',1,1,NULL),('2012-07-14',1,1,NULL),('2012-07-15',1,1,NULL),('2012-07-16',3,0,NULL),('2012-07-17',3,0,NULL),('2012-07-18',4,0,NULL),('2012-07-19',4,0,NULL),('2012-07-20',3,1,NULL),('2012-07-21',3,1,NULL),('2012-07-22',3,1,NULL),('2012-07-23',1,0,NULL),('2012-07-24',2,0,NULL),('2012-07-25',2,0,NULL),('2012-07-26',1,0,NULL),('2012-07-27',1,1,NULL),('2012-07-28',1,1,NULL),('2012-07-29',1,1,NULL),('2012-07-30',2,0,NULL),('2012-07-31',3,0,NULL),('2013-01-01',1,0,NULL),('2013-01-02',2,0,NULL),('2013-01-03',3,0,NULL),('2013-01-04',2,1,NULL),('2013-01-05',2,1,NULL),('2013-01-06',2,1,NULL),('2013-01-07',3,0,NULL),('2013-01-08',1,0,NULL),('2013-01-09',1,0,NULL),('2013-01-10',3,0,NULL),('2013-01-11',3,1,NULL),('2013-01-12',3,1,NULL),('2013-01-13',3,1,NULL),('2013-01-14',1,0,NULL),('2013-01-15',1,0,NULL),('2013-01-16',1,0,NULL),('2013-01-17',2,0,NULL),('2013-01-18',1,1,NULL),('2013-01-19',1,1,NULL),('2013-01-20',1,1,NULL),('2013-01-21',2,0,NULL),('2013-01-22',2,0,NULL),('2013-01-23',3,0,NULL),('2013-01-24',3,0,NULL),('2013-01-25',2,1,NULL),('2013-01-26',2,1,NULL),('2013-01-27',2,1,NULL),('2013-01-28',3,0,NULL),('2013-01-29',3,0,NULL),('2013-01-30',1,0,NULL),('2013-02-01',3,1,NULL),('2013-02-02',3,1,NULL),('2013-02-03',3,1,NULL),('2013-02-04',1,0,NULL),('2013-02-05',2,0,NULL),('2013-02-06',1,0,NULL),('2013-02-07',2,0,NULL),('2013-02-08',1,1,NULL),('2013-02-09',1,1,NULL),('2013-02-10',1,1,NULL),('2013-02-11',2,0,NULL),('2013-02-12',2,0,NULL),('2013-02-13',2,0,NULL),('2013-02-14',3,0,NULL),('2013-02-15',2,1,NULL),('2013-02-16',2,1,NULL),('2013-02-17',2,1,NULL),('2013-02-18',3,0,NULL),('2013-02-19',3,0,NULL),('2013-02-20',1,0,NULL),('2013-02-21',3,0,NULL),('2013-02-22',1,1,NULL),('2013-02-23',1,1,NULL),('2013-02-24',1,1,NULL),('2013-02-25',3,0,NULL),('2013-02-26',3,0,NULL),('2013-02-27',2,0,NULL),('2013-02-28',3,0,NULL),('2013-03-01',3,1,NULL),('2013-03-02',3,1,NULL),('2013-03-03',3,1,NULL),('2013-03-04',1,0,NULL),('2013-03-05',2,0,NULL),('2013-03-06',1,0,NULL),('2013-03-07',2,0,NULL),('2013-03-08',1,1,NULL),('2013-03-09',1,1,NULL),('2013-03-10',1,1,NULL),('2013-03-11',2,0,NULL),('2013-03-12',2,0,NULL),('2013-03-13',2,0,NULL),('2013-03-14',3,0,NULL),('2013-03-15',2,1,NULL),('2013-03-16',2,1,NULL),('2013-03-17',2,1,NULL),('2013-03-18',3,0,NULL),('2013-03-19',3,0,NULL),('2013-03-20',1,0,NULL),('2013-03-21',3,0,NULL),('2013-03-22',1,1,NULL),('2013-03-23',1,1,NULL),('2013-03-24',1,1,NULL),('2013-03-25',3,0,NULL),('2013-03-26',3,0,NULL),('2013-03-27',2,0,NULL),('2013-03-28',3,0,NULL),('2013-03-29',2,1,NULL),('2013-03-30',2,1,NULL),('2013-03-31',2,1,NULL),('2013-04-01',1,0,NULL),('2013-04-02',2,0,NULL),('2013-04-03',3,0,NULL),('2013-04-04',3,0,NULL),('2013-04-05',2,1,NULL),('2013-04-06',2,1,NULL),('2013-04-07',2,1,NULL),('2013-04-08',1,0,NULL),('2013-04-09',1,0,NULL),('2013-04-10',3,0,NULL),('2013-04-11',3,0,NULL),('2013-04-12',1,1,NULL),('2013-04-13',1,1,NULL),('2013-04-14',1,1,NULL),('2013-04-15',3,0,NULL),('2013-04-16',3,0,NULL),('2013-04-17',2,0,NULL),('2013-04-18',3,0,NULL),('2013-04-19',2,1,NULL),('2013-04-20',2,1,NULL),('2013-04-21',2,1,NULL),('2013-04-22',3,0,NULL),('2013-04-23',1,0,NULL),('2013-04-24',3,0,NULL),('2013-04-25',1,0,NULL),('2013-04-26',3,1,NULL),('2013-04-27',3,1,NULL),('2013-04-28',3,1,NULL),('2013-04-29',1,0,NULL),('2013-04-30',1,0,NULL),('2013-05-01',3,0,NULL),('2013-05-02',1,0,NULL),('2013-05-03',3,1,NULL),('2013-05-04',3,1,NULL),('2013-05-05',3,1,NULL),('2013-05-06',2,0,NULL),('2013-05-07',1,0,NULL),('2013-05-08',2,0,NULL),('2013-05-09',1,0,NULL),('2013-05-10',2,1,NULL),('2013-05-11',2,1,NULL),('2013-05-12',2,1,NULL),('2013-05-13',1,0,NULL),('2013-05-14',1,0,NULL),('2013-05-15',1,0,NULL),('2013-05-16',3,0,NULL),('2013-05-17',1,1,NULL),('2013-05-18',1,1,NULL),('2013-05-19',1,1,NULL),('2013-05-20',3,0,NULL),('2013-05-21',2,0,NULL),('2013-05-22',3,0,NULL),('2013-05-23',2,0,NULL),('2013-05-24',3,1,NULL),('2013-05-25',3,1,NULL),('2013-05-26',3,1,NULL),('2013-05-27',2,0,NULL),('2013-05-28',2,0,NULL),('2013-05-29',1,0,NULL),('2013-05-30',2,0,NULL),('2013-05-31',1,1,NULL),('2013-06-01',1,1,NULL),('2013-06-02',1,1,NULL),('2013-06-03',2,0,NULL),('2013-06-04',3,0,NULL),('2013-06-05',2,0,NULL),('2013-06-06',3,0,NULL),('2013-06-07',2,1,NULL),('2013-06-08',2,1,NULL),('2013-06-09',2,1,NULL),('2013-06-10',3,0,NULL),('2013-06-11',3,0,NULL),('2013-06-12',1,0,NULL),('2013-06-13',3,0,NULL),('2013-06-14',1,1,NULL),('2013-06-15',1,1,NULL),('2013-06-16',1,1,NULL),('2013-06-17',3,0,NULL),('2013-06-18',3,0,NULL),('2013-06-19',2,0,NULL),('2013-06-20',3,0,NULL),('2013-06-21',2,1,NULL),('2013-06-22',2,1,NULL),('2013-06-23',2,1,NULL),('2013-06-24',3,0,NULL),('2013-06-25',1,0,NULL),('2013-06-26',3,0,NULL),('2013-06-27',1,0,NULL),('2013-06-28',3,1,NULL),('2013-06-29',3,1,NULL),('2013-06-30',3,1,NULL),('2013-07-01',1,0,NULL),('2013-07-02',2,0,NULL),('2013-07-03',3,0,NULL),('2013-07-04',1,0,NULL),('2013-07-05',2,1,NULL),('2013-07-06',2,1,NULL),('2013-07-07',2,1,NULL),('2013-07-08',1,0,NULL),('2013-07-09',1,0,NULL),('2013-07-10',1,0,NULL),('2013-07-11',1,0,NULL),('2013-07-12',3,1,NULL),('2013-07-13',3,1,NULL),('2013-07-14',3,1,NULL),('2013-07-15',2,0,NULL),('2013-07-16',3,0,NULL),('2013-07-17',1,0,NULL),('2013-07-18',2,0,NULL),('2013-07-19',1,1,NULL),('2013-07-20',1,1,NULL),('2013-07-21',1,1,NULL),('2013-07-22',3,0,NULL),('2013-07-23',2,0,NULL),('2013-07-24',3,0,NULL),('2013-07-25',2,0,NULL),('2013-07-26',3,1,NULL),('2013-07-27',3,1,NULL),('2013-07-28',3,1,NULL),('2013-07-29',2,0,NULL),('2013-07-30',2,0,NULL),('2013-07-31',1,0,NULL),('2013-08-01',4,0,NULL),('2013-08-02',2,1,NULL),('2013-08-03',2,1,NULL),('2013-08-04',2,1,NULL),('2013-08-05',1,0,NULL),('2013-08-06',3,0,NULL),('2013-08-07',4,0,NULL),('2013-08-08',1,0,NULL),('2013-08-09',3,1,NULL),('2013-08-10',3,1,NULL),('2013-08-11',3,1,NULL),('2013-08-12',4,0,NULL),('2013-08-13',1,0,NULL),('2013-08-14',4,0,NULL),('2013-08-15',1,0,NULL),('2013-08-16',4,1,NULL),('2013-08-17',4,1,NULL),('2013-08-18',4,1,NULL),('2013-08-19',1,0,NULL),('2013-08-20',2,0,NULL),('2013-08-21',1,0,NULL),('2013-08-22',2,0,NULL),('2013-08-23',3,1,NULL),('2013-08-24',3,1,NULL),('2013-08-25',3,1,NULL),('2013-08-26',1,0,NULL),('2013-08-27',2,0,NULL),('2013-08-28',1,0,NULL),('2013-08-29',2,0,NULL),('2013-08-30',1,1,NULL),('2013-08-31',1,1,NULL),('2013-09-01',1,1,NULL),('2013-09-02',2,0,NULL),('2013-09-03',3,0,NULL),('2013-09-04',2,0,NULL),('2013-09-05',3,0,NULL),('2013-09-06',2,1,NULL),('2013-09-07',2,1,NULL),('2013-09-08',2,1,NULL),('2013-09-09',1,0,NULL),('2013-09-10',3,0,NULL),('2013-09-11',1,0,NULL),('2013-09-12',3,0,NULL),('2013-09-13',1,1,NULL),('2013-09-14',1,1,NULL),('2013-09-15',1,1,NULL),('2013-09-16',3,0,NULL),('2013-09-17',3,0,NULL),('2013-09-18',3,0,NULL),('2013-09-19',2,0,NULL),('2013-09-20',3,1,NULL),('2013-09-21',3,1,NULL),('2013-09-22',3,1,NULL),('2013-09-23',2,0,NULL),('2013-09-24',1,0,NULL),('2013-09-25',2,0,NULL),('2013-09-26',1,0,NULL),('2013-09-27',2,1,NULL),('2013-09-28',2,1,NULL),('2013-09-29',2,1,NULL),('2013-09-30',1,0,NULL);
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'callsdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `addPhysician` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addPhysician`(
	IN in_First_Name 			 varchar(80),
	IN in_Last_Name 			 varchar(80),
	IN in_Birth_Date 			 date,
	IN in_Start_Employment_Date date,
	IN in_End_Employment_Date 	 date,
	IN in_Address				 varchar(45),
	IN in_Phone				 varchar(45)
)
BEGIN
	INSERT INTO physician 
	(
		First_Name,
		Last_Name,
		Birth_Date,
		Start_Employment_Date,
		End_Employment_Date,
		Address,
		Phone
	)
	VALUES 
	(
		in_First_Name,
		in_Last_Name,
		in_Birth_Date,
		in_Start_Employment_Date,
		in_End_Employment_Date,
		in_Address,
		in_Phone
	);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addShift` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addShift`(
	IN in_empID		INT,
	IN in_date		DATE,
	IN in_type		INT,
	IN in_comments	VARCHAR(300)
)
BEGIN
	INSERT INTO shift
	VALUES (in_date, in_empID, in_type, in_comments);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deletePhysician` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deletePhysician`(
	IN in_Employee_ID	int(10)
)
BEGIN
	DELETE FROM physician 
	WHERE Employee_ID = in_Employee_ID; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectPhysician` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectPhysician`(IN in_Employee_ID int(10)
)
BEGIN
	SELECT 	* FROM physician WHERE in_Employee_ID = Employee_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateHours` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateHours`(
	IN in_empID		INT,
	IN in_month		INT,
	IN in_year		INT,
	IN in_hours		INT

)
BEGIN
	INSERT INTO hours
	VALUES (in_year, in_month, in_empID, in_hours);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updatePhysician` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePhysician`(
	IN in_Employee_ID 				int(10),
	IN in_First_Name 			 varchar(80),
	IN in_Last_Name 			 varchar(80),
	IN in_Birth_Date 			 date,
	IN in_Start_Employment_Date date,
	IN in_End_Employment_Date 	 date,
	IN in_Address				 varchar(45),
	IN in_Phone				 varchar(45)
)
BEGIN
	UPDATE physician 
	SET 
		First_Name 				= in_First_Name,
		Last_Name 				= in_Last_Name,
		Birth_Date				= in_Birth_Date,
		Start_Employment_Date	= in_Start_Employment_Date,
		End_Employment_Date		= in_End_Employment_Date,
		Address					= in_Address,
		Phone					= in_Phone
	WHERE 
		Employee_ID = in_Employee_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-14 21:09:59
