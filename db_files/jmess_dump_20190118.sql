CREATE DATABASE  IF NOT EXISTS `messaging` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `messaging`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: messaging
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `folders`
--

DROP TABLE IF EXISTS `folders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `folders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `folder_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`folder_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folders`
--

LOCK TABLES `folders` WRITE;
/*!40000 ALTER TABLE `folders` DISABLE KEYS */;
INSERT INTO `folders` VALUES (1,'Inbox'),(2,'Sentbox'),(3,'Trash');
/*!40000 ALTER TABLE `folders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_receivers`
--

DROP TABLE IF EXISTS `message_receivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message_receivers` (
  `message_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  KEY `fk_message_id_idx` (`message_id`),
  KEY `fk_receiver_id_idx` (`receiver_id`),
  CONSTRAINT `fk_message_idr` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_receiver_idm` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_receivers`
--

LOCK TABLES `message_receivers` WRITE;
/*!40000 ALTER TABLE `message_receivers` DISABLE KEYS */;
INSERT INTO `message_receivers` VALUES (19,8),(20,7),(20,9),(21,12),(23,14),(23,11),(24,14),(24,11),(25,13),(26,11),(27,13),(28,10),(29,11),(30,1),(31,1),(32,10),(32,11),(33,14),(35,5),(36,11),(37,13),(37,11),(38,11),(39,5),(40,15);
/*!40000 ALTER TABLE `message_receivers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(100) NOT NULL,
  `body` varchar(250) NOT NULL,
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sender_id_idx` (`sender_id`),
  CONSTRAINT `fk_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (19,'Hi Soula (Edited)','Hi Soula! This is John! Just saying hi! :)','2019-01-13 18:47:21',5),(20,'Hey Paul, Stefanos!','Hey guys!! I just wanted to tell you I messaged Soula! ^^','2019-01-13 18:50:05',5),(21,'Edited subj','Edited','2019-01-13 23:10:45',11),(23,'Hey Chris, Kostas!','Are we up for tonight?','2019-01-14 14:01:55',13),(24,'I said...','Are we up for tonight????? :D','2019-01-14 14:05:30',13),(25,'Hello Nick!','Send me your news man!','2019-01-17 12:07:48',11),(26,'News','Well not much here. But you know what they say. No news is good news!','2019-01-17 12:10:12',13),(27,'Re: News','You look good man. Dont worry about it','2019-01-17 12:18:18',11),(28,'About Paul','Hey Maria. Have you seen Paul lately? He\'s a bit edgy..','2019-01-17 12:20:54',11),(29,'Re: About Paul','Hey. I haven\'t seen him no.  I see he is deactivated. What\'s up with that?','2019-01-17 12:24:24',10),(30,'About Paul','Hi. I noticed I cannot contact Paul. Is there a problem with his account?','2019-01-17 12:25:18',10),(31,'About Paul','Hey! Is everything ok with Paul\'s account? I cannot message him for some reason. Thanks!','2019-01-17 12:27:09',11),(32,'Re: About Paul','Yeap. Paul\'s account has been deactivated for the moment. He\'s been a bad boy lately','2019-01-17 12:28:18',1),(33,'Birthday!','Hey! Happy birthday! I almost forgot it!!','2019-01-17 12:46:52',11),(35,'Hey!','Hey! Long time no see!','2019-01-17 17:31:26',8),(36,'Thanks!','Thanks for the wishes. Drinks are on me this weekend!;)','2019-01-17 20:07:46',14),(37,'Sorry','Hey! I was busy. Didn\'t have any time. Soon we\'ll make it happen!','2019-01-17 20:09:03',14),(38,'Hey there!','I\'m new here. I\'ve been told we will be working together. Cheers!','2019-01-17 21:08:27',6),(39,'John!','You need a haircut. Trust me I\'m your friend','2019-01-18 11:00:48',15),(40,'I know','Will go for it soon!','2019-01-18 11:01:47',5);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'User'),(2,'Viewer'),(3,'Editor'),(4,'Moderator'),(5,'Admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `status_name_UNIQUE` (`status_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Active'),(0,'Deleted');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '1',
  `status_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_users_roles_idx` (`role_id`),
  KEY `fk_users_status_idx` (`status_id`),
  CONSTRAINT `fk_users_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_users_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3',5,1),(2,'deleter','5bd34eb57b85e257f31ede4360e5690d',4,1),(3,'editor','5aee9dbd2a188839105073571bee1b1f',3,1),(4,'viewer','4b2a1529867b8d697685b1722ccd0149',2,1),(5,'John','527BD5B5D689E2C32AE974C6229FF785',1,0),(6,'George','9b306ab04ef5e25f9fb89c998a6aedab',1,1),(7,'Paul','DE05FCED4685D6F4BA1ED5B49BF4E64D',1,0),(8,'Soula','e74cfce451df85edc3d64f3204395290',1,1),(9,'Stefanos','FCEEE26B875AF26C025F43B078D825A6',1,1),(10,'Maria','263BCE650E68AB4E23F28263760B9FA5',1,1),(11,'Kostas','5759A7CEC9124BE77FD0017F2B44C780',1,1),(12,'Sofia','17DA1AE431F965D839EC8EB93087FB2B',1,1),(13,'Nikos','C4E38D2C974A61477FF29089C04EC7A5',1,1),(14,'Chris','6B34FE24AC2FF8103F6FCE1F0DA2EF57',1,1),(15,'Mike','18126E7BD3F84B3F3E4DF094DEF5B7DE',1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_folders_messages`
--

DROP TABLE IF EXISTS `users_folders_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users_folders_messages` (
  `user_id` int(11) NOT NULL,
  `folder_id` int(11) NOT NULL,
  `message_id` int(11) NOT NULL,
  `is_read` bit(1) NOT NULL DEFAULT b'0',
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_container_id_idx` (`folder_id`),
  KEY `fk_message_id_idx` (`message_id`),
  CONSTRAINT `fk_folder_id` FOREIGN KEY (`folder_id`) REFERENCES `folders` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_message_id` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_folders_messages`
--

LOCK TABLES `users_folders_messages` WRITE;
/*!40000 ALTER TABLE `users_folders_messages` DISABLE KEYS */;
INSERT INTO `users_folders_messages` VALUES (5,2,19,_binary ''),(8,3,19,_binary ''),(5,2,20,_binary ''),(7,1,20,_binary '\0'),(9,1,20,_binary '\0'),(12,3,21,_binary '\0'),(13,2,23,_binary '\0'),(14,1,23,_binary ''),(11,1,23,_binary ''),(13,2,24,_binary '\0'),(14,1,24,_binary ''),(11,1,24,_binary ''),(11,2,25,_binary ''),(13,1,25,_binary '\0'),(13,2,26,_binary '\0'),(11,1,26,_binary ''),(11,2,27,_binary ''),(13,1,27,_binary '\0'),(11,2,28,_binary ''),(10,1,28,_binary ''),(10,2,29,_binary '\0'),(11,1,29,_binary ''),(10,2,30,_binary '\0'),(1,1,30,_binary '\0'),(11,2,31,_binary ''),(1,1,31,_binary '\0'),(1,2,32,_binary '\0'),(10,1,32,_binary ''),(11,1,32,_binary ''),(11,2,33,_binary ''),(14,1,33,_binary ''),(8,2,35,_binary ''),(5,1,35,_binary ''),(14,2,36,_binary ''),(11,1,36,_binary ''),(14,2,37,_binary ''),(13,1,37,_binary '\0'),(11,1,37,_binary ''),(6,2,38,_binary '\0'),(11,1,38,_binary ''),(15,2,39,_binary ''),(5,1,39,_binary ''),(5,2,40,_binary ''),(15,1,40,_binary '');
/*!40000 ALTER TABLE `users_folders_messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-18 14:42:02
