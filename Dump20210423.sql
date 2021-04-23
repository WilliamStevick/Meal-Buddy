-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: mealbuddy
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `foods`
--

DROP TABLE IF EXISTS `foods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foods` (
  `idfoods` int NOT NULL AUTO_INCREMENT,
  `categories` varchar(45) NOT NULL,
  `names` varchar(45) NOT NULL,
  `calories` varchar(45) NOT NULL,
  `servingsize` varchar(45) NOT NULL,
  PRIMARY KEY (`idfoods`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foods`
--

LOCK TABLES `foods` WRITE;
/*!40000 ALTER TABLE `foods` DISABLE KEYS */;
INSERT INTO `foods` VALUES (1,'protein','fried chicken','305','1 breast'),(2,'protein','roasted chicken','190','1 breast'),(3,'protein','fried fish','400','1 filet'),(4,'protein','baked fish','145','1 filet'),(5,'protein','beef','407','1 steak'),(6,'protein','pork chops','257','1 chop'),(7,'protein','ham','236','1 slice'),(8,'fruit','apple','95','1 apple'),(9,'fruit','banana','111','1 banana'),(10,'fruit','lemon','17','1 lemon'),(11,'fruit','orange','62','1 orange'),(12,'fruit','strawberry','4','1 strawberry'),(13,'fruit','watermelon','86','1 slice'),(14,'vegetable','broccoli','207','1 bunch'),(15,'vegetable','carrot','25','1 carrot'),(16,'vegetable','green beans','34','1 cup'),(17,'vegetable','pepper','20','1 pepper'),(18,'fruit','tomato','20','1 tomato'),(19,'vegetable','zucchini','33','1 zucchini'),(20,'dairy','almond milk','40','1 cup'),(21,'dairy','coconut milk','552','1 cup'),(22,'dairy','milk','149','1 cup'),(23,'dairy','soy milk','109','1 cup'),(24,'dairy','yogurt','138','1 container'),(25,'dairy','american cheese','31','1 slice'),(27,'dairy','cheddar','89','1 slice'),(29,'dairy','mozzarella','78','1 slice'),(30,'dairy','swiss cheese','95','1 slice'),(31,'dairy','white cheddar','532','1 cup'),(32,'grain','bagel','252','1 bagel'),(33,'grain','brownies','162','1 square'),(34,'grain','muffin','210','1 muffin'),(35,'grain','tortilla','146','1 tortilla'),(36,'grain','white bread','67','1 slice'),(37,'grain','brown rice','670','1 cup'),(38,'protein','ground beef','405','6 ounces'),(39,'protein','duck','2137','1/2 duck'),(40,'protein','turkey legs','1136','1 leg'),(41,'vegetable','bell pepper','15','1 pepper'),(42,'vegetable','cabbage','227','1 head'),(43,'vegetable','celery','6','1 stalk'),(44,'vegetable','lettuce','90','1 head'),(45,'vegetable','onion','34','1 onion'),(46,'vegetable','potato','164','1 potato'),(47,'meals','bacon and eggs','539','1 serving'),(48,'meals','chicken caesar salad','392','1 salad'),(49,'meals','corn dog','438','1 item'),(50,'meals','fish and chips','585','1 serving'),(51,'meals','grilled cheese sandwich','392','1 sandwich'),(52,'meals','lasagna','368','1 serving'),(53,'meals','macaroni and cheese','699','1 serving'),(54,'meals','mashed potatoes','174','1 cup'),(55,'meals','meatloaf','721','1 loaf'),(56,'meals','peanut butter sandwich','200','1 sandwich'),(57,'meals','philly cheese steak','300','1 sandwich'),(58,'meals','shepherds pie','159','1 pie'),(59,'meals','reuben sandwich','641','1 sandwich'),(60,'meals','calzone','841','1 calzone'),(61,'protein','almonds','546','1 cup'),(62,'protein','cashews','155','1 ounce'),(63,'protein','peanuts','828','1 cup'),(64,'protein','pecans','684','1 cup'),(65,'protein','pistachios','691','1 cup'),(66,'protein','sunflower seeds','818','1 cup'),(67,'protein','walnuts','523','1 cup'),(68,'fruit','blackberry','62','1 cup'),(69,'fruit','blueberry','84','1 cup'),(70,'fruit','grapes','104','1 cup'),(71,'fruit','kiwi','112','1 kiwi'),(72,'fruit','mango','202','1 mango'),(73,'fruit','nectarine','66','1 nectarine'),(74,'fruit','peach','59','1 peach'),(75,'fruit','plum','30','1 plum'),(76,'fruit','pomegranate','234','1 pomegrante'),(77,'fruit','raspberry','64','1 cup');
/*!40000 ALTER TABLE `foods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `names` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dailycalories` int NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tyler','miguel','porto',1800),(10,'liam alonso','student145','poorcookie23',2000),(11,'ramon','coolkid99','snoopdog',2100),(12,'tyler','trial1','trial1',1090),(13,'sam','velocity','velocity',1850);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-23 17:11:59
