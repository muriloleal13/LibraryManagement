# Biblioteca

Este projeto consiste em desenvolver um sistema de biblioteca completo. 

Os dados são armazenados em uma base de dados relacional mysql.
Deve ser executa o arquivo biblioteca.sql que está na pasta sql.
É possível manter livros: incluir livros, alterar livros, excluir livros, buscar(listar) livros pelo ISBN, por título e pelo autor.
Cada livro contém informações acerca de: autores, edição, editora, nome, ano.
É possível manter usuários: incluir usuários, excluir usuários, listar usuários, buscar usuários por parte do nome, pelo login e pelo tipo.
É possível manter operações de empréstimo de livros: emprestar livros, renovar empréstimo, devolver livros, buscar(listar) empréstimos por livro (ISBN, título, usuario).
É possível manter operações de reservas de livros: solicitar reserva de livro, cancelar reserva de livro.
É possível listar e pagar multas pendentes.
Os alunos podem retirar até 3 livros, por uma semana. Os professores podem retirar até 5 livros, por 15 dias.
Os empréstimos são renovados pelo mesmo período permitido para o usuário em questão, não sendo permitidos, no entanto, renovações de livros que estão com o exemplar reservado por algum usuário. 
Não é permitido aos usuários com multas reservarem livros.
A multa é de R$ 1,00 por dia (útil ou não útil)

O sistema tem um sistema de segurança, para que se haja a exclusão de um usuário do tipo "Adm" no próximo login será criado um usuário padrão com login: adm e senha: 123
