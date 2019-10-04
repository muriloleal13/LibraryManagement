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

-- Copiando dados para a tabela biblioteca.livros: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` (`id`, `nome`, `autor`, `isbn`, `editora`, `ano`, `edicao`, `qtd`) VALUES
	(1, 'LivroNovo', 'AutorNovo', '123', 'EditoraNova', 2019, 1, 10),
	(3, 'lvnew', 'at', '456', 'ed', 2019, 1, 1),
	(4, 'aaa', 'aa', 'aa', NULL, 0, 0, 4);
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;

-- Copiando dados para a tabela biblioteca.livros_reservados: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `livros_reservados` DISABLE KEYS */;
INSERT INTO `livros_reservados` (`id`, `id_usuario`, `id_livro`, `reservado`) VALUES
	(2, 5, 4, 1);
/*!40000 ALTER TABLE `livros_reservados` ENABLE KEYS */;

-- Copiando dados para a tabela biblioteca.livros_retirados: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `livros_retirados` DISABLE KEYS */;
INSERT INTO `livros_retirados` (`id`, `id_livro`, `id_usuario`, `data`) VALUES
	(4, 4, 7, '2019-10-04 00:00:00'),
	(6, 3, 5, '2019-01-25 00:00:00');
/*!40000 ALTER TABLE `livros_retirados` ENABLE KEYS */;

-- Copiando dados para a tabela biblioteca.usuario: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nome`, `login`, `senha`, `tipo`) VALUES
	(5, 'Murilo Leal', 'msleal', '202cb962ac59075b964b07152d234b70', 'Func'),
	(7, 'Adm Padrão', 'adm', '202cb962ac59075b964b07152d234b70', 'Adm');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
