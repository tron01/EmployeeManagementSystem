CREATE DATABASE  IF NOT EXISTS `employeemanagement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `employeemanagement`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: employeemanagement
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `head_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8ginee11c6lp4st3l24gb7rv6` (`head_id`),
  CONSTRAINT `FK33txpop8xcpaae0uetkw9elsd` FOREIGN KEY (`head_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'2024-06-01','IT',2),(2,'2024-06-05','HR',3),(3,'2024-06-10','Finance',4),(4,'2024-06-15','Marketing',5);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `join_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `yearly_bonus_percentage` double DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  `manager_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbejtwvg9bxus2mffsm3swj3u9` (`department_id`),
  KEY `FKou6wbxug1d0qf9mabut3xqblo` (`manager_id`),
  CONSTRAINT `FKbejtwvg9bxus2mffsm3swj3u9` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKou6wbxug1d0qf9mabut3xqblo` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'123 Main Street, Kochi','1990-06-10','2022-04-15','AdminManager','Admin',75000,10.5,NULL,NULL),(2,'101 Tech Park, Kochi','1980-03-25','2021-01-10','Manager IT','Manager',85000,12,1,1),(3,'102 HR Ave, Trivandrum','1982-07-12','2021-03-15','Manager HR','Manager',83000,11.8,2,1),(4,'103 Finance Road, Calicut','1981-10-10','2021-05-05','Manager Finance','Manager',86000,11.9,3,1),(5,'104 Market St, Kannur','1983-12-01','2021-07-01','Manager Marketing','Manager',84000,11.6,4,1),(6,'201 Infopark, Kochi','1995-01-15','2023-02-10','Arjun P','Employee',42000,5,1,2),(7,'202 City Lane, Kochi','1996-08-25','2022-11-20','Sneha R','Employee',43000,4.7,1,2),(8,'203 IT Plaza, Kochi','1994-05-10','2023-04-15','Vishnu D','Employee',44000,5.2,1,2),(9,'204 Green Road, Kochi','1997-02-05','2022-09-01','Divya K','Employee',41000,4.9,1,2),(10,'205 Cyber Ave, Kochi','1993-11-18','2023-01-25','Hari S','Employee',42500,5.1,1,2),(11,'206 HR Towers, Trivandrum','1996-03-15','2022-12-10','Lakshmi M','Employee',40000,4.5,2,3),(12,'207 Office Park, Trivandrum','1995-09-22','2023-02-28','Ravi N','Employee',41000,4.6,2,3),(13,'208 City Road, Trivandrum','1994-12-10','2023-04-18','Meera V','Employee',40500,4.8,2,3),(14,'209 Hill Lane, Trivandrum','1992-06-07','2023-05-01','Nikhil T','Employee',42000,5,2,3),(15,'210 Garden St, Trivandrum','1997-07-19','2022-10-15','Anjali J','Employee',39500,4.7,2,3),(16,'211 Money Lane, Calicut','1995-02-08','2023-03-03','Rahul M','Employee',43000,5.3,3,4),(17,'212 Finance Block, Calicut','1996-05-14','2023-01-10','Anu P','Employee',41500,4.6,3,4),(18,'213 Bank Road, Calicut','1994-10-30','2022-11-25','Sagar R','Employee',40500,4.9,3,4),(19,'214 East Street, Calicut','1997-03-11','2022-09-08','Diya K','Employee',40000,4.4,3,4),(20,'215 South Ave, Calicut','1993-08-03','2023-04-12','Kiran J','Employee',42000,5.1,3,4),(21,'216 Ad Lane, Kannur','1995-10-28','2023-05-25','Sandra D','Employee',41500,4.8,4,5),(22,'217 Biz Road, Kannur','1992-12-03','2023-02-14','Manu E','Employee',40500,4.9,4,5),(23,'218 Market Lane, Kannur','1996-09-30','2022-07-19','Tina L','Employee',40000,4.6,4,5),(24,'219 Main Road, Kannur','1993-01-16','2022-10-28','Aravind G','Employee',43000,5,4,5),(25,'220 Valley Street, Kannur','1997-06-22','2023-03-20','Keerthana H','Employee',39500,4.7,4,5),(26,'221 Tech Park, Kochi','1994-04-20','2023-06-01','Neha S','Employee',43500,5,1,2),(27,'223 Lakeview, Kochi','1993-02-27','2022-08-25','Asha M','Employee',44500,5.3,3,4),(28,'225 Digital Tower, Kochi','1997-07-01','2023-10-01','Fathima Z','Employee',43000,5.1,4,5);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'employeemanagement'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-13  3:13:02
