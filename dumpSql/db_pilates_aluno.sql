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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `id` bigint(20) NOT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_nasc` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idade` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero_end` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `altura` varchar(255) DEFAULT NULL,
  `ativo` bit(1) NOT NULL,
  `foto_perfil` varchar(255) DEFAULT NULL,
  `objetivo` varchar(255) DEFAULT NULL,
  `peso` varchar(255) DEFAULT NULL,
  `pratica_atividade` varchar(255) DEFAULT NULL,
  `nivel_aluno` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'Parque Residencial Ana Rosa','(43) 9 9635-4623','86183-540','Cambé','casa','576.113.720-84','1994-11-09','teste@hotmailc.om','23','Rua Dalto','Rafaeel Cubaski','805','M','(43) 3254-2032','PR','1.72','\0',NULL,NULL,'72.2','true','INTERMEDIARIO'),(32768,'Centro','(43) 9 8463-9760','86020-400','Londrina','apto 901','383.978.368-29','1989-10-14','joice.araujo.adm@hotmail.com','28','Rua Pará','Joice Cristina Araujo','900','F','(43) 3345-4545','PR','1,64','',NULL,NULL,'48','true','INICIANTE'),(262144,'...','(12) 3 1312-3123','12312-312','...','312313','338.048.248-03','1231-03-12','123@123','1','...','Marcos Oliveira','12312','F','','...','312313','',NULL,NULL,'31231','true','INTERMEDIARIO'),(360448,'1231','(12) 3 1231-3123','12312-312','.123123123.','1231','445.626.000-98','123123-03-12','123@123','11','1231','Edson Amorin','12312','M','(12) 3131-2312','12','123123','',NULL,NULL,'123123',NULL,'AVANCADO'),(393218,'1231','(12) 3 1231-2312','12312-312','1231','123','992.302.780-50','1231-03-12','testeqtetatd@asdtasdt','12','123','Rodrigo do Skate','123','F','(12) 3123-1231','12','12313','',NULL,NULL,'123',NULL,'INICIANTE');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
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
