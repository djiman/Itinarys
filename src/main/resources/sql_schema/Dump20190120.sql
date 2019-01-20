CREATE DATABASE  IF NOT EXISTS `itineraire` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `itineraire`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: itineraire
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `gare`
--

DROP TABLE IF EXISTS `gare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gare` (
  `GareID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Statut` char(1) DEFAULT '0',
  `Ville` varchar(100) DEFAULT NULL,
  `commentaire` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`GareID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gare`
--

LOCK TABLES `gare` WRITE;
/*!40000 ALTER TABLE `gare` DISABLE KEYS */;
INSERT INTO `gare` VALUES (1,'Rueil','1','Rueil',NULL),(2,'Chatou','0','Chatou',NULL);
/*!40000 ALTER TABLE `gare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garesLigne`
--

DROP TABLE IF EXISTS `garesLigne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `garesLigne` (
  `LigneID` int(11) NOT NULL,
  `GareID` int(11) NOT NULL,
  `ORDRE` int(11) NOT NULL,
  PRIMARY KEY (`LigneID`,`GareID`),
  KEY `GareID` (`GareID`),
  CONSTRAINT `garesligne_ibfk_1` FOREIGN KEY (`GareID`) REFERENCES `gare` (`GareID`),
  CONSTRAINT `garesligne_ibfk_2` FOREIGN KEY (`LigneID`) REFERENCES `ligne` (`LigneID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garesLigne`
--

LOCK TABLES `garesLigne` WRITE;
/*!40000 ALTER TABLE `garesLigne` DISABLE KEYS */;
INSERT INTO `garesLigne` VALUES (1,1,2),(1,2,1);
/*!40000 ALTER TABLE `garesLigne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ligne`
--

DROP TABLE IF EXISTS `ligne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ligne` (
  `LigneID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Statut` char(1) DEFAULT '0',
  `Type` varchar(100) DEFAULT NULL,
  `commentaire` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`LigneID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ligne`
--

LOCK TABLES `ligne` WRITE;
/*!40000 ALTER TABLE `ligne` DISABLE KEYS */;
INSERT INTO `ligne` VALUES (1,'RerA','0','Train',NULL),(2,'RerB','0',NULL,NULL);
/*!40000 ALTER TABLE `ligne` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-20 11:17:06
