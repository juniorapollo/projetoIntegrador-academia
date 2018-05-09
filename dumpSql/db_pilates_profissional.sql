-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: db_pilates
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `profissional`
--

DROP TABLE IF EXISTS `profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profissional` (
  `id` bigint(20) NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `celular` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) NOT NULL,
  `data_nasc` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `idade` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `numero_end` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `uf` varchar(255) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nivel_profissional` varchar(255) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ps4st8u2dhixxuu02augyf1gk` (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional`
--

LOCK TABLES `profissional` WRITE;
/*!40000 ALTER TABLE `profissional` DISABLE KEYS */;
INSERT INTO `profissional` VALUES (98304,'bairro','celular','86020-380','cidade','','404.223.020-23','1988-08-10','email@a','17','logradouro','10NOME USUARIO','1','M','tel','UF','','admin','ADMINISTRADOR','$2a$10$n.DUuF71lt95QDoaaPKL6uGzmDYksNhva6.FoB2XZ.6dauq05cHBy'),(163840,'bairro','celular','86020-380','cidade','','404.223.020-23','1988-08-10','bruna@email.com','29','logradouro','Bruna Andrade','1','M','tel','UF','','bruna','ADMINISTRADOR','$2a$10$LDBJYUX/5x2LOM2zIP2e8O2yUrFzr54F0IT5i8mOydxfLKojqyRhW'),(196608,'Centro','(12) 3 1231-3212','86020-380','LOndrina','','338.048.248-03','1977-08-10','teste@hotmailc.om','50','PioXii','Daniel','856','M','(13) 1321-2331','PR','','teste','PROFESSOR','$2a$10$8KYI7LIzMABqvGqMxVCf/./dL6B84bJWFP1ueNxF/NkXmqCXLTfLi'),(229376,'1231','(12) 3 1231-2312','12313-212','123123','12311','338.048.248-03','123123-03-12','123@123','1','31231','as,dmbadsm','123','F','(12) 3123-1231','12','\0','12313','ADMINISTRADOR','$2a$10$NOIL6B5f1u3fHtebAd/fJevzMPu0tydNDU0Atj2Nc9Ail7FE/BuMy'),(294912,'123123','(12) 3 1231-2312','12312-312','123123','123','338.048.248-03','123123-03-12','123@123','1','1','123123','12313','M','(12) 3123-1231','12','','123123','ADMINISTRADOR','$2a$10$c3hwyLXG2nYqpUn4feS6WeEcUyBqXH.S3bsU19GmSS3Ynt5xaAFh2'),(393217,'1231','(23) 1 2312-3123','12312-312','123123','123','610.739.170-37','1231-03-12','fulanodetal@email','12','123','TEste profissional','123','F','(33) 4333-2233','12','','testelogin','ADMINISTRADOR','$2a$10$ICuy/IAvKACbDtVtxY.5N.VDnU.sqSho.7qvsXxWDhg2rkbbTmjVm'),(393219,'1231','(12) 3 1231-2313','12312-312','1231','1231','008.716.170-21','3131-12-12','123@123','1','1231','123123','1231','F','(12) 3131-2312','12','','asafssdfsdff','ADMINISTRADOR','$2a$10$8GG37Dqsshlq/HfzqOLrYOGiNrHH7ioPl1cqaRnCMjxFpm.005Uzu'),(425987,'Jardim Graziela','(43) 9 8766-7766','86036-100','Londrina','casa','338.048.248-03','1975-06-09','123@1234','43','Rua Gertrudes Bischof','Joice2 Cristina Araujo','220','M','(43) 6666-6666','PR','','test2hj','ADMINISTRADOR','$2a$10$vhuL67zPIzuzp2t7ePYzguq1s7nUMsOh7m9x.22Ttlb2UHgvbNyLy'),(688128,'Centro','(12) 3 6123-6136','86020-380','Londrina','1','848.337.120-08','1111-11-11','teste@com','11','Rua Pio XII','Profissional 2','12','F','(66) 7617-6172','PR','','123123132123132','PROFESSOR','$2a$10$.iNfXc0X5wy7cRDkbnjEHe37vyVEQvBQ/cOFZG7loEomBw43o9PNS'),(720897,'123123','(34) 2 4324-2342','12313-123','12313','31231','072.779.560-01','11111-11-04','teste@com','1','12313','TEste','31231','M','(44) 3423-4243','12','','12312312313','PROFESSOR','$2a$10$48RLtO4LqqO30.gm1KIQI.UI1SNHsfZ1iTQtzyQU8UhfPkH1DbpqW'),(753665,'123','(12) 3 1231-2312','12312-312','123','13','072.779.560-01','1988-03-01','123@123123123','11','123','123123','123','M','(12) 3131-2312','12','','123312313123','PROFESSOR','$2a$10$kRENIN6pqJAkAW3FSDHEO.ggZrvSzbFE0yekrmkNzuAgILETHWK/a'),(786433,'1231','(12) 3 1231-2312','13123-123','1231','1','495.449.780-97','1111-03-12','123@123','11','123','123123123','123','F','(11) 2312-3123','12','','1231312331231312','OPERADOR','$2a$10$VUfSLuTAm1UFnTSHIU9PPOB9jO2EBin0D1G3J2I4Fq7flSp13Tpvq');
/*!40000 ALTER TABLE `profissional` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-17 17:17:48
