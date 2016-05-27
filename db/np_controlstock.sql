-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: np_controlstock
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.10-MariaDB

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
-- Table structure for table `adm_acciones`
--

DROP TABLE IF EXISTS `adm_acciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_acciones` (
  `id_accion` int(11) NOT NULL AUTO_INCREMENT,
  `accion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_accion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_acciones`
--

LOCK TABLES `adm_acciones` WRITE;
/*!40000 ALTER TABLE `adm_acciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `adm_acciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_controllers`
--

DROP TABLE IF EXISTS `adm_controllers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_controllers` (
  `id_controller` int(11) NOT NULL AUTO_INCREMENT,
  `controller` varchar(50) NOT NULL,
  PRIMARY KEY (`id_controller`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_controllers`
--

LOCK TABLES `adm_controllers` WRITE;
/*!40000 ALTER TABLE `adm_controllers` DISABLE KEYS */;
/*!40000 ALTER TABLE `adm_controllers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_er_rol_funcionalidad`
--

DROP TABLE IF EXISTS `adm_er_rol_funcionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_er_rol_funcionalidad` (
  `id_rol` int(11) NOT NULL,
  `id_func` int(11) NOT NULL,
  KEY `id_rol` (`id_rol`),
  KEY `id_func` (`id_func`),
  CONSTRAINT `ADM_ER_rol_funcionalidad_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `adm_roles` (`id_rol`),
  CONSTRAINT `ADM_ER_rol_funcionalidad_ibfk_2` FOREIGN KEY (`id_func`) REFERENCES `adm_funcionalidades` (`id_func`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_er_rol_funcionalidad`
--

LOCK TABLES `adm_er_rol_funcionalidad` WRITE;
/*!40000 ALTER TABLE `adm_er_rol_funcionalidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `adm_er_rol_funcionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_er_usuarios_roles`
--

DROP TABLE IF EXISTS `adm_er_usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_er_usuarios_roles` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  KEY `id_rol` (`id_rol`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `ADM_ER_usuarios_roles_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `adm_roles` (`id_rol`),
  CONSTRAINT `ADM_ER_usuarios_roles_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `adm_usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_er_usuarios_roles`
--

LOCK TABLES `adm_er_usuarios_roles` WRITE;
/*!40000 ALTER TABLE `adm_er_usuarios_roles` DISABLE KEYS */;
INSERT INTO `adm_er_usuarios_roles` VALUES (30,1);
/*!40000 ALTER TABLE `adm_er_usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_funcionalidades`
--

DROP TABLE IF EXISTS `adm_funcionalidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_funcionalidades` (
  `id_func` int(11) NOT NULL AUTO_INCREMENT,
  `id_controller` int(11) NOT NULL,
  `id_accion` int(11) NOT NULL,
  PRIMARY KEY (`id_func`),
  KEY `id_controller` (`id_controller`),
  KEY `id_accion` (`id_accion`),
  CONSTRAINT `ADM_Funcionalidades_ibfk_1` FOREIGN KEY (`id_controller`) REFERENCES `adm_controllers` (`id_controller`),
  CONSTRAINT `ADM_Funcionalidades_ibfk_2` FOREIGN KEY (`id_accion`) REFERENCES `adm_acciones` (`id_accion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_funcionalidades`
--

LOCK TABLES `adm_funcionalidades` WRITE;
/*!40000 ALTER TABLE `adm_funcionalidades` DISABLE KEYS */;
/*!40000 ALTER TABLE `adm_funcionalidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_genericrepo`
--

DROP TABLE IF EXISTS `adm_genericrepo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_genericrepo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_genericrepo`
--

LOCK TABLES `adm_genericrepo` WRITE;
/*!40000 ALTER TABLE `adm_genericrepo` DISABLE KEYS */;
/*!40000 ALTER TABLE `adm_genericrepo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_genericreporel`
--

DROP TABLE IF EXISTS `adm_genericreporel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_genericreporel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_rel` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_rel` (`id_rel`),
  CONSTRAINT `ADM_GenericRepoRel_ibfk_1` FOREIGN KEY (`id_rel`) REFERENCES `adm_genericrepo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_genericreporel`
--

LOCK TABLES `adm_genericreporel` WRITE;
/*!40000 ALTER TABLE `adm_genericreporel` DISABLE KEYS */;
/*!40000 ALTER TABLE `adm_genericreporel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_roles`
--

DROP TABLE IF EXISTS `adm_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_roles` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(50) NOT NULL,
  `fec_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_roles`
--

LOCK TABLES `adm_roles` WRITE;
/*!40000 ALTER TABLE `adm_roles` DISABLE KEYS */;
INSERT INTO `adm_roles` VALUES (1,'Superadmin','0000-00-00 00:00:00'),(2,'Administrador','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `adm_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adm_usuarios`
--

DROP TABLE IF EXISTS `adm_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fec_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_usuarios`
--

LOCK TABLES `adm_usuarios` WRITE;
/*!40000 ALTER TABLE `adm_usuarios` DISABLE KEYS */;
INSERT INTO `adm_usuarios` VALUES (1,'root','$2a$10$0LEKJb8nQxMzdRlULo7B7uOqXU6wZ04tLS2atOC27.925mqv/HHDy','0000-00-00 00:00:00'),(28,'test','$2a$10$0LEKJb8nQxMzdRlULo7B7uOqXU6wZ04tLS2atOC27.925mqv/HHDy','0000-00-00 00:00:00'),(30,'usuario_prueba','$2a$10$0LEKJb8nQxMzdRlULo7B7uOqXU6wZ04tLS2atOC27.925mqv/HHDy','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `adm_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_items`
--

DROP TABLE IF EXISTS `stk_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_items` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `photo` varchar(45) DEFAULT NULL,
  `id_product` int(11) NOT NULL,
  `fec_mod` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fec_delete` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_item`),
  KEY `id_product_idx` (`id_product`),
  CONSTRAINT `id_product` FOREIGN KEY (`id_product`) REFERENCES `stk_products` (`id_product`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_items`
--

LOCK TABLES `stk_items` WRITE;
/*!40000 ALTER TABLE `stk_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_locations`
--

DROP TABLE IF EXISTS `stk_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_locations` (
  `id_location` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `id_warehouse` int(11) NOT NULL,
  PRIMARY KEY (`id_location`),
  KEY `id_warehouse_idx` (`id_warehouse`),
  CONSTRAINT `id_warehouse` FOREIGN KEY (`id_warehouse`) REFERENCES `stk_warehouse` (`id_warehouse`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_locations`
--

LOCK TABLES `stk_locations` WRITE;
/*!40000 ALTER TABLE `stk_locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_orders`
--

DROP TABLE IF EXISTS `stk_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `supplier` varchar(50) NOT NULL,
  `fec_order` datetime DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_orders`
--

LOCK TABLES `stk_orders` WRITE;
/*!40000 ALTER TABLE `stk_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_orders_items`
--

DROP TABLE IF EXISTS `stk_orders_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_orders_items` (
  `id_order` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id_order`,`id_item`),
  KEY `stok_oriders_items.id_item_idx` (`id_item`),
  CONSTRAINT `stk_orders_items.id_order` FOREIGN KEY (`id_order`) REFERENCES `stk_orders` (`id_order`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `stok_oriders_items.id_item` FOREIGN KEY (`id_item`) REFERENCES `stk_items` (`id_item`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_orders_items`
--

LOCK TABLES `stk_orders_items` WRITE;
/*!40000 ALTER TABLE `stk_orders_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_orders_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_products`
--

DROP TABLE IF EXISTS `stk_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_products` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `photo` varchar(45) DEFAULT NULL,
  `fec_mod` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fec_delete` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_products`
--

LOCK TABLES `stk_products` WRITE;
/*!40000 ALTER TABLE `stk_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_reasons`
--

DROP TABLE IF EXISTS `stk_reasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_reasons` (
  `id_reason` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(45) NOT NULL,
  PRIMARY KEY (`id_reason`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_reasons`
--

LOCK TABLES `stk_reasons` WRITE;
/*!40000 ALTER TABLE `stk_reasons` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_reasons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_stock`
--

DROP TABLE IF EXISTS `stk_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_stock` (
  `id_stock` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  `id_location` int(11) NOT NULL,
  `fec_mod` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  KEY `id_item_idx` (`id_item`),
  KEY `id_location_idx` (`id_location`),
  CONSTRAINT `id_item` FOREIGN KEY (`id_item`) REFERENCES `stk_items` (`id_item`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_location` FOREIGN KEY (`id_location`) REFERENCES `stk_locations` (`id_location`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_stock`
--

LOCK TABLES `stk_stock` WRITE;
/*!40000 ALTER TABLE `stk_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_stock_history`
--

DROP TABLE IF EXISTS `stk_stock_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_stock_history` (
  `id_stock_history` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `id_reason` int(11) DEFAULT NULL,
  `id_stock` int(11) NOT NULL,
  `fec_add` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_stock_history`),
  KEY `id_stock_idx` (`id_stock`),
  KEY `id_reason_idx` (`id_reason`),
  KEY `id_user_idx` (`id_user`),
  CONSTRAINT `id_reason` FOREIGN KEY (`id_reason`) REFERENCES `stk_reasons` (`id_reason`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_stock` FOREIGN KEY (`id_stock`) REFERENCES `stk_stock` (`id_stock`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `adm_usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_stock_history`
--

LOCK TABLES `stk_stock_history` WRITE;
/*!40000 ALTER TABLE `stk_stock_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_stock_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stk_warehouse`
--

DROP TABLE IF EXISTS `stk_warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stk_warehouse` (
  `id_warehouse` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_warehouse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stk_warehouse`
--

LOCK TABLES `stk_warehouse` WRITE;
/*!40000 ALTER TABLE `stk_warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `stk_warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-27 12:39:35
