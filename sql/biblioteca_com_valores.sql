-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.38-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para biblioteca
CREATE DATABASE IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `biblioteca`;

-- Copiando estrutura para tabela biblioteca.livros
CREATE TABLE IF NOT EXISTS `livros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `isbn` varchar(45) NOT NULL,
  `ano` varchar(45) DEFAULT NULL,
  `editora` varchar(45) DEFAULT NULL,
  `edicao` varchar(45) DEFAULT NULL,
  `qtd` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela biblioteca.livros: ~3 rows (aproximadamente)
DELETE FROM `livros`;
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` (`id`, `nome`, `autor`, `isbn`, `ano`, `editora`, `edicao`, `qtd`) VALUES
	(1, 'LivroNovo', 'AutorNovo', '123', '2019', 'EditoraNova', '1', 10),
	(3, 'lvnew', 'at', '456', '2019', 'ed', '1', 1),
	(4, 'aaa', 'aa', 'aa', '0', NULL, '0', 4);
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;

-- Copiando estrutura para tabela biblioteca.livros_reservados
CREATE TABLE IF NOT EXISTS `livros_reservados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_livro` int(11) NOT NULL,
  `reservado` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela biblioteca.livros_reservados: ~0 rows (aproximadamente)
DELETE FROM `livros_reservados`;
/*!40000 ALTER TABLE `livros_reservados` DISABLE KEYS */;
INSERT INTO `livros_reservados` (`id`, `id_usuario`, `id_livro`, `reservado`) VALUES
	(2, 5, 4, 1);
/*!40000 ALTER TABLE `livros_reservados` ENABLE KEYS */;

-- Copiando estrutura para tabela biblioteca.livros_retirados
CREATE TABLE IF NOT EXISTS `livros_retirados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_livro` int(11) NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela biblioteca.livros_retirados: ~2 rows (aproximadamente)
DELETE FROM `livros_retirados`;
/*!40000 ALTER TABLE `livros_retirados` DISABLE KEYS */;
INSERT INTO `livros_retirados` (`id`, `id_usuario`, `id_livro`, `data`) VALUES
	(4, 7, 4, '2019-10-04'),
	(6, 5, 3, '2019-01-25');
/*!40000 ALTER TABLE `livros_retirados` ENABLE KEYS */;

-- Copiando estrutura para tabela biblioteca.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `tipo` enum('Adm','Func','Aluno') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela biblioteca.usuario: ~2 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nome`, `login`, `senha`, `tipo`) VALUES
	(5, 'Murilo Leal', 'msleal', '202cb962ac59075b964b07152d234b70', 'Func'),
	(7, 'Adm Padrão', 'adm', '202cb962ac59075b964b07152d234b70', 'Adm');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
