CREATE DATABASE  IF NOT EXISTS `empresa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `empresa`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: empresa
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `distrito`
--

DROP TABLE IF EXISTS `distrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distrito` (
  `cod_dis` int NOT NULL,
  `nom_dis` varchar(30) DEFAULT NULL,
  `ruta` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cod_dis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distrito`
--

LOCK TABLES `distrito` WRITE;
/*!40000 ALTER TABLE `distrito` DISABLE KEYS */;
INSERT INTO `distrito` VALUES (100,'San Borja','segura'),(101,'San Juan','Dudosa'),(102,'La Victoria','Cuidadoso'),(103,'LOs olivos','Lejano'),(104,'Breña','Cercano');
/*!40000 ALTER TABLE `distrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emple`
--

DROP TABLE IF EXISTS `emple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emple` (
  `cod_em` int NOT NULL,
  `fecha` date DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod_em`),
  UNIQUE KEY `ini` (`cod_em`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emple`
--

LOCK TABLES `emple` WRITE;
/*!40000 ALTER TABLE `emple` DISABLE KEYS */;
INSERT INTO `emple` VALUES (2000,'2022-01-30','cristian'),(2001,'2022-01-08','asuna'),(2002,'2022-01-04','asuna'),(2003,'2022-01-02','bansito'),(2004,'2022-01-02','bansito'),(3000,'2022-01-08','maddy'),(3001,'2022-05-07','ggggggghj'),(3005,'1970-01-01','bansito'),(3010,'2020-07-02','momo'),(3020,'2022-12-05','asaf'),(3021,'2022-07-18','momo'),(3022,'2022-07-19','surgi'),(3023,'2020-12-30','momo');
/*!40000 ALTER TABLE `emple` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `num_fact` int NOT NULL,
  `fecha_emi` date DEFAULT NULL,
  `cod_Emple` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `cod_cli` int DEFAULT NULL,
  PRIMARY KEY (`num_fact`),
  KEY `fk_cog` (`cod_Emple`),
  KEY `fk_clie` (`cod_cli`),
  CONSTRAINT `fk_clie` FOREIGN KEY (`cod_cli`) REFERENCES `tb_cliente` (`cod_cli`),
  CONSTRAINT `fk_cog` FOREIGN KEY (`cod_Emple`) REFERENCES `trabajador` (`cod_Emple`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1000,'2022-01-27',1001,102.75,1001),(1001,'2022-01-27',1001,100,1001),(1002,'2022-01-27',1001,100,1001),(1003,'2022-01-27',1002,341.6,1005),(1004,'2022-01-27',1001,288.65,1002),(1005,'2022-03-19',1001,65.8,1003),(1006,'2022-07-02',1001,164.85,1010),(1007,'2022-07-04',1002,30.6,1005);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nini`
--

DROP TABLE IF EXISTS `nini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nini` (
  `codigo` varchar(8) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nini`
--

LOCK TABLES `nini` WRITE;
/*!40000 ALTER TABLE `nini` DISABLE KEYS */;
/*!40000 ALTER TABLE `nini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `cod_pro` int NOT NULL,
  `nombre_pro` varchar(50) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`cod_pro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (2001,'Arroz',20.55),(2002,'Azucar',30.6),(2003,'Menestra',10.5),(2004,'Yogur',22.4),(2005,'Cereal',18.5),(2006,'Aceite',16.9);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cliente` (
  `cod_cli` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `dni` int DEFAULT NULL,
  `sexo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (1001,'Chok','Yamato',84563219,'Masculino'),(1002,'Peyton','Lisst',84599219,'Femenino'),(1003,'Lan ','Hikari',82263219,'Masculino'),(1005,'Raven','Gamer',84566619,'Masculino'),(1007,'Kamikase','Gamer',86541239,'Masculino'),(1010,'nini','salazar',12344567,'Femenino'),(1011,'MOmo','Ligh',78945612,'Masculino');
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_detalle_factura`
--

DROP TABLE IF EXISTS `tb_detalle_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_detalle_factura` (
  `num_fact` int DEFAULT NULL,
  `cod_pro` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  KEY `fkfact` (`num_fact`),
  KEY `fkcodi` (`cod_pro`),
  CONSTRAINT `fkcodi` FOREIGN KEY (`cod_pro`) REFERENCES `productos` (`cod_pro`),
  CONSTRAINT `fkfact` FOREIGN KEY (`num_fact`) REFERENCES `factura` (`num_fact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_factura`
--

LOCK TABLES `tb_detalle_factura` WRITE;
/*!40000 ALTER TABLE `tb_detalle_factura` DISABLE KEYS */;
INSERT INTO `tb_detalle_factura` VALUES (1000,2001,5),(1003,2001,4),(1003,2002,2),(1003,2003,6),(1003,2006,8),(1004,2001,3),(1004,2002,5),(1004,2005,4),(1005,2003,2),(1005,2004,2),(1006,2001,7),(1006,2003,2),(1007,2002,1);
/*!40000 ALTER TABLE `tb_detalle_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_empleado`
--

DROP TABLE IF EXISTS `tb_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_empleado` (
  `codi_empl` int NOT NULL AUTO_INCREMENT,
  `nom_em` varchar(50) DEFAULT NULL,
  `apell_em` varchar(50) DEFAULT NULL,
  `num_hijos` int DEFAULT NULL,
  `sueldo` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`codi_empl`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_empleado`
--

LOCK TABLES `tb_empleado` WRITE;
/*!40000 ALTER TABLE `tb_empleado` DISABLE KEYS */;
INSERT INTO `tb_empleado` VALUES (1,'crsitian','orizano',5,25.00);
/*!40000 ALTER TABLE `tb_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajador` (
  `cod_Emple` int NOT NULL,
  `nom_empl` varchar(20) DEFAULT NULL,
  `ape_empl` varchar(50) DEFAULT NULL,
  `sul_empl` double DEFAULT NULL,
  `hij_empl` int DEFAULT NULL,
  `cod_dis` int DEFAULT NULL,
  PRIMARY KEY (`cod_Emple`),
  KEY `fkCod` (`cod_dis`),
  CONSTRAINT `fkCod` FOREIGN KEY (`cod_dis`) REFERENCES `distrito` (`cod_dis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES (1001,'cistianoO','orizano',20,4,101),(1002,'debby','Ryan',30,2,102),(1005,'Misora','orizano',20.55,4,103),(1006,'Maddy','Flipsp',21.55,1,101),(1007,'cristiano','Huuyhua',100,2,104),(1008,'Riuzaki','Hidete',100,2,103),(1009,'Olivia','Rodrigo',100.5,2,104),(1010,'Jessie','Prescot',100,1,104),(1011,'Lan','Hikari',200,1,100),(1012,'Chok','wortre',100,1,100),(1013,'BrendaSon','ListSo',100,1,103),(1014,'surgical','Goblin',50.3,2,100),(1019,'NIni Roberts','Zalazar',100,2,104),(1020,'surg','Zalazar',100,4,100),(1021,'cristian','orizano',50,5,101),(1023,'Gamer','Huuyhua',100,2,102),(1024,'ASAF','Gomez',100.05,2,101),(1025,'Emma','Salaz',40,5,103);
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `cod_Emple` int DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `clave` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `fk` (`cod_Emple`),
  CONSTRAINT `fk` FOREIGN KEY (`cod_Emple`) REFERENCES `trabajador` (`cod_Emple`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1001,'cristiano2014','Cib123'),(2,1002,'debbyRyan','de12345'),(3,1005,'Misora10','Fusor123');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'empresa'
--

--
-- Dumping routines for database 'empresa'
--
/*!50003 DROP PROCEDURE IF EXISTS `Actualizar_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Actualizar_cliente`(non varchar(20),ape varchar(30),dn int,se varchar(20),cod int)
BEGIN
        update tb_cliente set nombre=non,apellido=ape,dni=dn,sexo=se
where cod_cli=cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Buscar_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Buscar_cliente`(vnombre varchar(20))
BEGIN
     select * from tb_cliente where nombre like concat(vnombre,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Buscar_Producto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Buscar_Producto`()
BEGIN
         select * from Productos where nombre_pro like concat(vproducto,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Buscar_Productod` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Buscar_Productod`(vproducto varchar(20))
BEGIN
         select * from Productos where nombre_pro like concat(vproducto,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ConsultaTrabajador` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ConsultaTrabajador`(vtipo int, filtro varchar(20))
BEGIN
    if vtipo =1 then
    begin
          select  cod_Emple,nom_empl,ape_empl,sul_empl,hij_empl,concat(tr.cod_dis,"-",di.nom_dis)
         from  Trabajador as tr join Distrito as di
         on tr.cod_dis=di.cod_dis where nom_empl like concat(filtro,'%');
         end;
      end if;
      
       if vtipo =2 then
    begin
          select  cod_Emple,nom_empl,ape_empl,sul_empl,hij_empl,concat(tr.cod_dis,"-",di.nom_dis)
         from  Trabajador as tr join Distrito as di
         on tr.cod_dis=di.cod_dis where sul_empl >= convert(filtro,double);
         end;
      end if;
      
       if vtipo =3 then
      begin
          select  cod_Emple,nom_empl,ape_empl,sul_empl,hij_empl,concat(tr.cod_dis,"-",di.nom_dis)
         from  Trabajador as tr join Distrito as di
         on tr.cod_dis=di.cod_dis where hij_empl >= convert(filtro,unsigned);
         end;
      end if;
      
       if vtipo = 4 then
    begin
          select  cod_Emple,nom_empl,ape_empl,sul_empl,hij_empl,concat(tr.cod_dis,"-",di.nom_dis)
         from  Trabajador as tr join Distrito as di
         on tr.cod_dis=di.cod_dis where tr.cod_dis= convert(filtro, unsigned);
         end;
      end if;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Consulta_por_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Consulta_por_nombre`(vnum varchar(20))
BEGIN
      select  cod_Emple,nom_empl,ape_empl,sul_empl,hij_empl,concat(tr.cod_dis,"-",di.nom_dis)
        from  Trabajador as tr join Distrito as di
        on tr.cod_dis=di.cod_dis  where nom_empl like concat(vnum,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Inserta_detalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Inserta_detalle`(codif int,codipro int,canti int)
BEGIN
     insert into tb_detalle_factura values(codif,codipro,canti);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `listar_inter` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_inter`()
begin
select*from tb_internamiento;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Registrar_factura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Registrar_factura`(num int,fech char(10),cod_Emple int,total double,cod_cli int)
BEGIN
      insert into Factura values(num,fech,cod_Emple,total,cod_cli);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_ConsultaCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ConsultaCliente`(vfiltro int,criterio varchar(20))
BEGIN
      if vfiltro=1 then
      begin 
         select * from tb_cliente where nombre like concat(criterio,'%');
         end;
       end if;
       if vfiltro=2 then
      begin 
         select * from tb_cliente where sexo =criterio;
         end;
       end if;
       
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_iniciar_secion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_iniciar_secion`(vLogin varchar(50),vclave varchar(50))
BEGIN
      select  t.cod_Emple,t.nom_empl,t.ape_empl from Trabajador as t join Usuario as u
       on t.cod_Emple=u.cod_Emple where u.login=vLogin and u.clave= vclave;
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

-- Dump completed on 2022-12-27 17:21:16
