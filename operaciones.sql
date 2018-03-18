CREATE DATABASE  IF NOT EXISTS `operacionesbancarias` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `operacionesbancarias`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: operacionesbancarias
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `cuentasbancarias`
--

DROP TABLE IF EXISTS `cuentasbancarias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentasbancarias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numcuenta` varchar(45) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `dni_propietario` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Cuentasbancarias_Propietarios_idx` (`dni_propietario`),
  CONSTRAINT `fk_Cuentasbancarias_Propietarios` FOREIGN KEY (`dni_propietario`) REFERENCES `propietarios` (`dni`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentasbancarias`
--

LOCK TABLES `cuentasbancarias` WRITE;
/*!40000 ALTER TABLE `cuentasbancarias` DISABLE KEYS */;
INSERT INTO `cuentasbancarias` VALUES (7,'214578648',5000,'57846821R'),(8,'210487598',200,'54725764W');
/*!40000 ALTER TABLE `cuentasbancarias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoevento` varchar(1) DEFAULT NULL,
  `fechahoraevento` datetime DEFAULT NULL,
  `dni_propietario` varchar(45) DEFAULT NULL,
  `id_cuentabancaria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (8,'I','2018-03-11 00:00:00',NULL,'1'),(9,'I','2018-03-11 00:00:00',NULL,'1'),(10,'E','2018-03-11 00:00:00',NULL,'1'),(11,'E','2018-03-11 00:00:00',NULL,'1'),(12,'E','2018-03-11 00:00:00',NULL,'1'),(13,'E',NULL,NULL,'1'),(14,'I','2018-03-11 23:29:35',NULL,'1'),(15,'I','2018-03-11 23:30:28',NULL,'1'),(16,'E','2018-03-14 22:28:23',NULL,'7'),(17,'I','2018-03-14 22:29:06',NULL,'7'),(18,'L','2018-03-14 22:57:34','51478478J',NULL),(19,'L','2018-03-18 19:57:11','51478478J',NULL),(20,'L','2018-03-18 19:57:21','51478478J',NULL),(21,'L','2018-03-18 20:28:19','51478478J',NULL),(22,'L','2018-03-18 20:41:21','51478478J',NULL),(23,'L','2018-03-18 20:42:35','51478478J',NULL),(24,'L','2018-03-18 20:45:48','51478478J',NULL),(25,'L','2018-03-18 20:47:18','51478478J',NULL),(26,'L','2018-03-18 20:48:24','51478478J',NULL);
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciones`
--

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechahora` datetime DEFAULT NULL,
  `tipo` varchar(1) DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  `saldoactualizado` double DEFAULT NULL,
  `id_cuentabancaria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Operaciones_Cuentasbancarias1_idx` (`id_cuentabancaria`),
  CONSTRAINT `fk_Operaciones_Cuentasbancarias1` FOREIGN KEY (`id_cuentabancaria`) REFERENCES `cuentasbancarias` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciones`
--

LOCK TABLES `operaciones` WRITE;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
INSERT INTO `operaciones` VALUES (20,'2018-03-11 23:29:35','I',1000,1600,1),(21,'2018-03-11 23:30:28','I',1000,2600,1),(22,'2018-03-14 22:04:43','I',500,5500,7),(23,'2018-03-14 22:05:59','E',-200,5300,7),(24,'2018-03-14 22:23:58','E',-5400,-100,7),(25,'2018-03-14 22:27:06','I',200,100,7),(26,'2018-03-14 22:28:23','E',-800,-700,7),(27,'2018-03-14 22:29:06','I',800,100,7);
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propietarios`
--

DROP TABLE IF EXISTS `propietarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `propietarios` (
  `dni` varchar(20) NOT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `primerapellido` varchar(45) DEFAULT NULL,
  `segundoapellido` varchar(45) DEFAULT NULL,
  `numerosecreto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propietarios`
--

LOCK TABLES `propietarios` WRITE;
/*!40000 ALTER TABLE `propietarios` DISABLE KEYS */;
INSERT INTO `propietarios` VALUES ('51164312F','vlazaro','VICTOR','LAZARO','VASQUEZ','0000'),('51478478J','Sandra','Sandra','Martinez','Polo','12345'),('54725764W','Nerea','Nerea','Lazaro','Boggio','12346'),('547264F','JBOGGIO','JESSICA','BOGGIO','NORIEGA','45123'),('57846821R','mramirez','Maria','Ramirez','Escobar','23456');
/*!40000 ALTER TABLE `propietarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-18 23:29:36
