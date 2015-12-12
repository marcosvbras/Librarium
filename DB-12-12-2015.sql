-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: bookriddle
-- ------------------------------------------------------
-- Server version	5.6.12-log

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
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editora`
--

DROP TABLE IF EXISTS `editora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `editora` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editora`
--

LOCK TABLES `editora` WRITE;
/*!40000 ALTER TABLE `editora` DISABLE KEYS */;
/*!40000 ALTER TABLE `editora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprestimo` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status_emprestimo` tinyint(4) NOT NULL,
  `livro_ID` int(10) unsigned NOT NULL,
  `data_emprestimo` datetime NOT NULL,
  `data_devolucao` datetime NOT NULL,
  `supervisor_funcionario_matricula` int(11) NOT NULL,
  `funcionario_matricula` int(11) NOT NULL,
  `status_devolucao` tinyint(4) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_emprestimo_livro1_idx` (`livro_ID`),
  KEY `fk_emprestimo_supervisor1_idx` (`supervisor_funcionario_matricula`),
  KEY `fk_emprestimo_funcionario1_idx` (`funcionario_matricula`),
  CONSTRAINT `fk_emprestimo_funcionario1` FOREIGN KEY (`funcionario_matricula`) REFERENCES `funcionario` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_livro1` FOREIGN KEY (`livro_ID`) REFERENCES `livro` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_supervisor1` FOREIGN KEY (`supervisor_funcionario_matricula`) REFERENCES `supervisor` (`funcionario_matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
INSERT INTO `emprestimo` VALUES (16,1,21,'2015-12-08 00:00:00','2015-12-16 00:00:00',997892,631643,0),(17,1,24,'2015-12-08 00:00:00','2015-12-28 00:00:00',997892,9027251,0),(18,1,11,'2015-12-08 00:00:00','2015-12-17 00:00:00',997892,646362,0),(19,0,25,'2015-12-09 00:00:00','2015-12-11 00:00:00',997892,646362,0),(20,1,14,'2015-12-09 19:19:19','2015-12-10 19:19:36',997892,9027251,1),(21,1,14,'2015-12-09 00:00:00','2015-12-11 00:00:00',997892,997892,1),(22,1,14,'2015-12-09 00:00:00','2015-12-12 00:00:00',997892,631643,0),(23,1,7,'2015-12-09 00:00:00','2015-12-16 00:00:00',997892,631643,1),(24,1,12,'2015-12-09 00:00:00','2015-12-11 00:00:00',997892,646362,1),(25,1,9,'2015-12-10 00:00:00','2015-12-16 00:00:00',997892,646362,0),(26,1,27,'2015-12-10 00:00:00','2015-12-28 00:00:00',123456,646362,1),(27,1,5,'2015-12-10 00:00:00','2015-12-30 00:00:00',997892,9027251,1),(28,1,12,'2015-12-10 00:00:00','2015-12-24 00:00:00',997892,123456,1),(29,1,31,'2015-12-10 00:00:00','2015-12-22 00:00:00',997892,286688,1),(30,1,15,'2015-12-10 00:00:00','2015-12-21 00:00:00',997892,646362,1);
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `status_funcionario` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (123456,'Luiz Otávio','666.666.666-66','luizotavioff@hotmail.com','(31) 8888-88888',1),(286688,'Marcos Vinícius','085.830.936-01','marcosvbras@gmail.com','(31) 9999-99999',1),(631643,'Larissa Rodrigues Rabelo Foreman Grill','069.455.415-66','larissa@foremail.com','(11) 9283-92892',1),(646362,'Lucas Skywalker Lemos','000.000.000-00','lukinhas@tatoine.com','(31) 9867-87890',1),(997892,'Pedro da Costa','555.555.555-55','pedroinf97@gmail.com','(31) 9878-97978',1),(9027251,'José Lucimar','888.888.888-88','exemplo@exemplo.com','(31) 9999-99999',1);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livro` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(60) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `area` varchar(45) DEFAULT NULL,
  `edicao` varchar(15) DEFAULT NULL,
  `editora` varchar(255) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `paginas` int(11) NOT NULL,
  `armario` varchar(30) DEFAULT NULL,
  `ano` int(11) NOT NULL,
  `prateleira` varchar(30) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status_livro` tinyint(4) DEFAULT NULL,
  `isbn` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES (1,'Resistência dos Materiais','BEER, Ferdinand','Engenharia','5º','Mcgraw-Hill Interamericana',3,567,'I',2006,'5','null',0,'863549756-0'),(3,'Orientação a Objetos e SOLID para Ninjas','Mauricio Aniche','Algoritmos e Programação','3ª','Casa do Código',1,166,'II',2015,'1','src/br/com/bookriddle/imagens/livros/oo-solid-sumario-featured_large.png',1,'324965782-0'),(4,'Guia do mestre programador','Carlos Bueno','Guia de programação','1ª','Casa do Código',0,203,'II',2015,'2','src/br/com/bookriddle/imagens/livros/guia_programador.jpeg',1,'495641325-0'),(5,'Controlando versões com Git e GitHub','Alexandre Aquiles, Rodrigo Ferreira','Controle de Versão','2ª','Casa do Código',0,175,'III',2014,'1','src/br/com/bookriddle/imagens/livros/git-github-featured_large.png',1,'316497521-0'),(6,'Java SE 8 Programmer I','Guilherme Silveira, Mário Amaral','Certificação','1ª','Casa do Código',1,412,'IV',2014,'1','src/br/com/bookriddle/imagens/livros/certificacao-java-featured_large.png',1,'634921457-0'),(7,'Google Android - Crie Aplicações para celulares e tables','João Bosco Monteiro','Programação Mobile','2ª','Casa do Código',7,230,'I',2013,'7','src/br/com/bookriddle/imagens/livros/android-featured_large.png',1,'162947243-0'),(8,'Desenvolvimento de Jogos para Android','Anderson Leite','Programação Mobile','1ª','Casa do Código',1,175,'I',2013,'7','src/br/com/bookriddle/imagens/livros/jogos-android-featured_large.png',1,'364916736-0'),(9,'Começando com Linux ','Daniel Romero','Sistemas Operacionais','2ª','Casa do Código',7,313,'II',2012,'6','src/br/com/bookriddle/imagens/livros/linux-featured_large.png',1,'645645633-0'),(10,'Ruby - Aprenda a programar na linguagem mais divertida','Lucas Souza','Programação','3ª','Casa do Código',1,299,'X',2013,'4','src/br/com/bookriddle/imagens/livros/ruby-featured_large.png',1,'454166678-0'),(11,'Vire o jogo com Spring Framework','Henrique Lobo Weissmann','Programação','3ª','Casa do Código',3,250,'IX',2014,'1','src/br/com/bookriddle/imagens/livros/spring-framework-featured_large.png',1,'126663457-0'),(12,'Swift - Programe para iPhone e iPad','Guilherme Silveira, Joviane Jardim','Programação Mobile','1ª','Casa do Código',0,212,'III',2015,'2','src/br/com/bookriddle/imagens/livros/Swift-ebook_large.png',1,'124345344-0'),(13,'Web Design Responsivo','Tárcio Zemel','Web Design','2ª','Casa do Código',2,130,'I',2013,'2','src/br/com/bookriddle/imagens/livros/web-design-responsivo-featured_large.png',1,'369767968-0'),(14,'Criando aplicações para o seu Windows Phone','Gabriel Schade Cardoso','Programação Mobile','4ª','Casa do Código',4,280,'II',2013,'3','src/br/com/bookriddle/imagens/livros/windows-phone-featured_large.png',1,'346455789-0'),(15,'Azure','Thiago Custódio','Cloud Computing','1ª','Casa do Código',0,120,'IV',2015,'7','src/br/com/bookriddle/imagens/livros/azure-featured_large.png',1,'787875421-1'),(16,'C# e Visual Studio','Evertor Coimbra de Araújo','Programação','1ª','Casa do Código',0,222,'III',2015,'3','src/br/com/bookriddle/imagens/livros/csharp-featured_large.png',1,'677784123-1'),(17,'Design Patterns com Java','Eduardo Guerra','Padrão de Projetos','2ª','Casa do Código',1,192,'II',2014,'1','src/br/com/bookriddle/imagens/livros/design-patterns-featured_large.png',1,'789679461-1'),(18,'Jogos Android','Felipe Torres','Programação','2ª','Casa do Código',1,215,'I',2015,'5','src/br/com/bookriddle/imagens/livros/games-android-featured_large.png',1,'792531272-1'),(19,'Java EE','Alberto Souza','Programação Web','1ª','Casa do Código',4,200,'V',2015,'3','src/br/com/bookriddle/imagens/livros/java-ee.jpeg',1,'678695526-0'),(20,'Dominando JavaScript com jQuery','Plínio Balduino','Programação Web','5ª','Casa do Código',1,145,'II',2014,'4','src/br/com/bookriddle/imagens/livros/javascript-jquery-featured_large.png',1,'374675611-0'),(21,'Desenvolva jogos com HTML5 Canvas e JavaScript','Éderson Cássio','Programação Web','1ª','Casa do Código',1,290,'VI',2015,'1','src/br/com/bookriddle/imagens/livros/jogos-html-javascript-featured_large.png',1,'459679895-1'),(22,'Desenvolvimento de Jogos para iOS','Maurício Tollin, Rodrigo Gomes, Anderson Leite','Programação Mobile','2ª','Casa do Código',4,180,'V',2014,'3','src/br/com/bookriddle/imagens/livros/jogos-ios-featured_large.png',1,'978912121-0'),(23,'JSF Eficaz','Hébert Coelho','Programação Web','1ª','Casa do Código',1,231,'I',2014,'1','src/br/com/bookriddle/imagens/livros/jsf-eficaz-featured_large.png',1,'797898595-1'),(24,'PHP e Laravel','Rodrigo Turin','Programação Web','2ª','Casa do Código',2,190,'II',2014,'1','src/br/com/bookriddle/imagens/livros/laravel-featured_large.png',1,'978952312-0'),(25,'Desbravando Java e Orientação a Objetos','Rodrigo Turni','Algoritmos e Programação','3ª','Casa do Código',7,356,'III',2013,'1','src/br/com/bookriddle/imagens/livros/orientacao-objetos-java-featured_large.png',1,'656312371-0'),(26,'Python - Escreva seus primeiros programas','Felipe Cruz','Algoritmos e Programação','1ª','Casa do Código',0,298,'VII',2015,'3','src/br/com/bookriddle/imagens/livros/python.png',1,'646213135-1'),(27,'Ruby on Rails','Vinícius Baggio Fuentes','Algoritmos e Programação','3ª','Casa do Código',0,321,'V',2013,'3','src/br/com/bookriddle/imagens/livros/ruby-on-rails-featured_large.png',1,'146546789-0'),(28,'eXtreme Programming','Daniel Wldt, Dionata Moura','Padrão de Projetos','1ª','Casa do Código',2,200,'III-A',2014,'4','src/br/com/bookriddle/imagens/livros/extreme_programming.jpeg',1,'972531378-1'),(29,'Padrões de Projeto para Android','Greg Nudelman','Programação Mobile','1ª','Novatec',2,395,'II-B',2015,'5','src/br/com/bookriddle/imagens/livros/padroes.jpg',1,'6747748-8'),(30,'SQL - Uma Abordagem para banco de dados','Eduardo Gonçalves','Banco de Dados','1ª','Casa do Código',2,200,'II-B',2014,'5','src/br/com/bookriddle/imagens/livros/Amazon-SQL-Oracle_large.jpg',1,'767565-8'),(31,'Microsoft Kinect','Gabriel Schade Cardoso','Programação','1ª','Casa do Código',1,200,'II-A',2013,'1','src/br/com/bookriddle/imagens/livros/kinect-featured_large.png',1,'6756757-7');
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `livro_ID` int(10) unsigned NOT NULL,
  `funcionario_matricula` int(11) NOT NULL,
  `status_reserva` tinyint(4) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_reserva_livro_idx` (`livro_ID`),
  KEY `fk_reserva_funcionario1_idx` (`funcionario_matricula`),
  CONSTRAINT `fk_reserva_funcionario1` FOREIGN KEY (`funcionario_matricula`) REFERENCES `funcionario` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_livro` FOREIGN KEY (`livro_ID`) REFERENCES `livro` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (46,'2015-12-08 00:00:00',9,646362,0),(47,'2015-12-08 00:00:00',6,646362,0),(48,'2015-12-08 22:26:24',10,646362,1),(49,'2015-12-08 00:00:00',12,646362,0),(50,'2015-12-08 00:00:00',11,646362,0),(51,'2015-12-08 00:00:00',25,646362,0),(52,'2015-12-08 22:27:02',15,646362,1),(53,'2015-12-09 00:23:11',20,646362,1),(54,'2015-12-09 00:00:00',25,646362,0),(55,'2015-12-09 00:46:25',23,646362,1),(63,'2015-12-09 00:00:00',27,646362,0),(64,'2015-12-09 22:00:10',4,646362,1),(65,'2015-12-09 22:01:03',4,646362,1),(68,'2015-12-09 00:00:00',15,646362,0),(70,'2015-12-09 22:49:28',26,646362,1),(72,'2015-12-10 00:00:00',26,123456,1),(73,'2015-12-10 00:00:00',25,123456,1),(74,'2015-12-10 00:00:00',4,646362,1),(75,'2015-12-10 00:00:00',27,646362,1),(77,'2015-12-10 00:00:00',12,123456,0),(78,'2015-12-10 00:00:00',13,646362,1),(79,'2015-12-10 00:00:00',16,646362,1);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supervisor`
--

DROP TABLE IF EXISTS `supervisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supervisor` (
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `funcionario_matricula` int(11) NOT NULL,
  `status_supervisor` tinyint(4) DEFAULT NULL,
  `codigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`funcionario_matricula`),
  KEY `fk_supervisor_funcionario1_idx` (`funcionario_matricula`),
  CONSTRAINT `funcionario_matricula1` FOREIGN KEY (`funcionario_matricula`) REFERENCES `funcionario` (`matricula`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supervisor`
--

LOCK TABLES `supervisor` WRITE;
/*!40000 ALTER TABLE `supervisor` DISABLE KEYS */;
INSERT INTO `supervisor` VALUES ('luizao','zxcv',123456,1,0),('fornoindustrial','12345',631643,1,NULL),('admin','admin',997892,1,NULL),('lucimar','asdf',9027251,1,NULL);
/*!40000 ALTER TABLE `supervisor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-12 13:28:52
