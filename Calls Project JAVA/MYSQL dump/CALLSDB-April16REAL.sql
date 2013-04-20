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
  CONSTRAINT `Employee` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daysoff`
--

LOCK TABLES `daysoff` WRITE;
/*!40000 ALTER TABLE `daysoff` DISABLE KEYS */;
INSERT INTO `daysoff` VALUES (16,13,'2013-03-13'),(17,13,'2013-03-21'),(19,13,'2013-03-06'),(20,13,'2013-04-01'),(21,13,'2013-04-02');
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
INSERT INTO `holidays` VALUES ('2013-01-01'),('2013-02-18'),('2013-03-29'),('2013-04-01'),('2013-05-20'),('2013-07-01'),('2013-09-02'),('2013-10-14'),('2013-11-11'),('2013-12-25'),('2014-01-01'),('2014-02-17'),('2014-04-18'),('2014-04-21'),('2014-05-19'),('2014-07-01'),('2014-09-01'),('2014-10-13'),('2014-11-11'),('2014-12-25'),('2015-01-01'),('2015-02-16'),('2015-04-03'),('2015-04-06'),('2015-05-18'),('2015-07-01'),('2015-09-07'),('2015-10-12'),('2015-11-11'),('2015-12-25');
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
  CONSTRAINT `Physician_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hours`
--

LOCK TABLES `hours` WRITE;
/*!40000 ALTER TABLE `hours` DISABLE KEYS */;
INSERT INTO `hours` VALUES (2013,2,8,0),(2013,2,9,162),(2013,2,10,0),(2013,2,11,153),(2013,2,12,186),(2013,2,13,0),(2013,3,8,0),(2013,3,9,180),(2013,3,10,123),(2013,3,11,141),(2013,3,12,120),(2013,3,13,0),(2013,4,8,24),(2013,4,9,138),(2013,4,10,138),(2013,4,11,168),(2013,4,12,63),(2013,4,13,0),(2013,5,8,0),(2013,5,9,177),(2013,5,10,186),(2013,5,11,183),(2013,5,13,0),(2013,6,8,0),(2013,6,9,201),(2013,6,10,186),(2013,6,11,153),(2013,6,13,0);
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
INSERT INTO `initialdays` VALUES (8,10,10,5),(9,10,10,0),(10,5,5,0),(11,0,0,0),(12,5,8,0),(13,15,5,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician`
--

LOCK TABLES `physician` WRITE;
/*!40000 ALTER TABLE `physician` DISABLE KEYS */;
INSERT INTO `physician` VALUES (8,'Darek','Zborowski','1991-10-10','2012-10-10',NULL,'Coachway 123','403-123-1288'),(9,'Sean','Nette','1980-10-10','2013-01-12',NULL,'HelloWorld 123','403-123-2344'),(10,'Evan','Matej','1999-10-15','2013-03-02',NULL,'Wowman','403-123-2344'),(11,'Wojtek','Grzyb','1970-10-10','2013-01-01',NULL,'Hellomanwhas','403-123-2344'),(12,'John','Smith','1990-06-06','2013-02-05','2013-04-15','HelloMan','403-123-4567'),(13,'Robert','B','1990-10-11','2011-12-12',NULL,'HelloAddress 123 AB','403-123-1234');
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
  CONSTRAINT `Employee_ID` FOREIGN KEY (`Employee_ID`) REFERENCES `physician` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
INSERT INTO `shift` VALUES ('2013-02-01',11,1,NULL),('2013-02-02',11,1,NULL),('2013-02-03',11,1,NULL),('2013-02-04',11,0,NULL),('2013-02-05',12,0,NULL),('2013-02-06',12,0,NULL),('2013-02-07',12,0,NULL),('2013-02-08',12,1,NULL),('2013-02-09',12,1,NULL),('2013-02-10',12,1,NULL),('2013-02-11',11,0,NULL),('2013-02-12',9,0,NULL),('2013-02-13',9,0,NULL),('2013-02-14',9,0,NULL),('2013-02-15',9,1,NULL),('2013-02-16',9,1,NULL),('2013-02-17',9,1,NULL),('2013-02-18',9,2,NULL),('2013-02-19',11,0,NULL),('2013-02-20',11,0,NULL),('2013-02-21',12,0,NULL),('2013-02-22',12,1,NULL),('2013-02-23',12,1,NULL),('2013-02-24',12,1,NULL),('2013-02-25',11,0,NULL),('2013-02-26',9,0,NULL),('2013-02-27',11,0,NULL),('2013-02-28',9,0,NULL),('2013-03-01',8,1,''),('2013-03-02',8,1,''),('2013-03-03',8,1,''),('2013-03-04',10,0,NULL),('2013-03-05',11,0,''),('2013-03-06',10,0,NULL),('2013-03-07',12,0,NULL),('2013-03-08',8,1,''),('2013-03-09',8,1,''),('2013-03-10',8,1,''),('2013-03-11',12,0,NULL),('2013-03-12',9,0,NULL),('2013-03-13',11,0,''),('2013-03-14',9,0,NULL),('2013-03-15',8,1,''),('2013-03-16',8,1,''),('2013-03-17',8,1,''),('2013-03-18',12,0,NULL),('2013-03-19',12,0,NULL),('2013-03-20',9,0,''),('2013-03-21',12,0,NULL),('2013-03-22',9,1,'DayOff '),('2013-03-23',9,1,'DayOff '),('2013-03-24',9,1,'DayOff '),('2013-03-25',12,0,NULL),('2013-03-26',10,0,NULL),('2013-03-27',12,0,NULL),('2013-03-28',8,0,'grhbrtgtgre'),('2013-03-29',8,2,''),('2013-03-30',8,1,''),('2013-03-31',8,1,''),('2013-04-01',8,2,NULL),('2013-04-02',10,0,NULL),('2013-04-03',8,0,''),('2013-04-04',10,0,NULL),('2013-04-05',12,1,NULL),('2013-04-06',12,1,NULL),('2013-04-07',12,1,NULL),('2013-04-08',11,0,NULL),('2013-04-09',10,0,NULL),('2013-04-10',11,0,NULL),('2013-04-11',10,0,NULL),('2013-04-12',9,1,NULL),('2013-04-13',9,1,NULL),('2013-04-14',9,1,NULL),('2013-04-15',11,0,NULL),('2013-04-16',10,0,NULL),('2013-04-17',11,0,NULL),('2013-04-18',9,0,NULL),('2013-04-19',10,1,NULL),('2013-04-20',10,1,NULL),('2013-04-21',10,1,NULL),('2013-04-22',11,0,NULL),('2013-04-23',9,0,NULL),('2013-04-24',11,0,NULL),('2013-04-25',9,0,NULL),('2013-04-26',11,1,NULL),('2013-04-27',11,1,NULL),('2013-04-28',11,1,NULL),('2013-04-29',9,0,NULL),('2013-04-30',9,0,NULL),('2013-05-01',11,0,NULL),('2013-05-02',10,0,NULL),('2013-05-03',10,1,NULL),('2013-05-04',10,1,NULL),('2013-05-05',10,1,NULL),('2013-05-06',11,0,NULL),('2013-05-07',11,0,NULL),('2013-05-08',11,0,NULL),('2013-05-09',11,0,NULL),('2013-05-10',11,1,NULL),('2013-05-11',11,1,NULL),('2013-05-12',11,1,NULL),('2013-05-13',9,0,NULL),('2013-05-14',9,0,NULL),('2013-05-15',9,0,NULL),('2013-05-16',9,0,NULL),('2013-05-17',9,1,NULL),('2013-05-18',9,1,NULL),('2013-05-19',9,1,NULL),('2013-05-20',9,2,NULL),('2013-05-21',10,0,NULL),('2013-05-22',10,0,NULL),('2013-05-23',10,0,NULL),('2013-05-24',10,1,NULL),('2013-05-25',10,1,NULL),('2013-05-26',10,1,NULL),('2013-05-27',11,0,NULL),('2013-05-28',9,0,NULL),('2013-05-29',11,0,NULL),('2013-05-30',9,0,NULL),('2013-05-31',11,1,NULL),('2013-06-01',11,1,NULL),('2013-06-02',11,1,NULL),('2013-06-03',10,0,NULL),('2013-06-04',10,0,NULL),('2013-06-05',10,0,NULL),('2013-06-06',10,0,NULL),('2013-06-07',10,1,NULL),('2013-06-08',10,1,NULL),('2013-06-09',10,1,NULL),('2013-06-10',11,0,NULL),('2013-06-11',11,0,NULL),('2013-06-12',9,0,NULL),('2013-06-13',9,0,NULL),('2013-06-14',9,1,NULL),('2013-06-15',9,1,NULL),('2013-06-16',9,1,NULL),('2013-06-17',11,0,NULL),('2013-06-18',11,0,NULL),('2013-06-19',9,0,NULL),('2013-06-20',11,0,NULL),('2013-06-21',10,1,NULL),('2013-06-22',10,1,NULL),('2013-06-23',10,1,NULL),('2013-06-24',9,0,NULL),('2013-06-25',11,0,NULL),('2013-06-26',9,0,NULL),('2013-06-27',11,0,NULL),('2013-06-28',9,1,NULL),('2013-06-29',9,1,NULL),('2013-06-30',9,1,NULL);
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

-- Dump completed on 2013-04-20 10:02:42
