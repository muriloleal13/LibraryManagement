/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author musle
 */
public class DAL {
    
    private final String driver = "org.gjt.mm.mysql.Driver";
    private final String url = "jdbc:mysql://localhost/biblioteca";
    private final String usuario = "root";
    private final String senha = "";
    private final Connection conexao;
    private static DAL dal = null;
    
    public static DAL getInstancia() throws ClassNotFoundException, SQLException {
        if (dal == null) {
            dal = new DAL();
        }
        return dal;
    }

    public DAL() throws ClassNotFoundException, SQLException {

        Class.forName(driver); //CARREGAR O DRIVER
        
        conexao = DriverManager.getConnection(url, usuario, senha); //ABRIR A CONEXAO
        
    }
    
    public void insereUsuario(UsuarioDAO user) throws SQLException {

        //INSERT
        String sql = "insert into usuario (nome, login, senha, tipo) values (?, ?, md5(?), ?)";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, user.getNome());
        st.setString(2, user.getLogin());
        st.setString(3, user.getSenha());
        st.setString(4, user.getTipo());
        st.executeUpdate();
    }
        
    public void editaUsuario(UsuarioDAO user) throws SQLException {

        //UPDATE
        String sql = "update usuario set nome=?, login=?, senha=md5(?), tipo=? where id=?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, user.getNome());
        st.setString(2, user.getLogin());
        st.setString(3, user.getSenha());
        st.setString(4, user.getTipo());
        st.setInt(5, user.getId());
        st.executeUpdate();
    }
    
    public void removeGeral(String id, String tabela) throws SQLException {

        //DELETE        
        String sql = "delete from " + tabela + " where id=?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, id);
        st.executeUpdate();   
        
        if(tabela.equals("livros"))
            tabela = "livro";
        
        sql = "delete from livros_retirados where id_" + tabela + "=?";
        st = conexao.prepareStatement(sql);
        st.setString(1, id);
        st.executeUpdate();
        
        sql = "delete from livros_reservados where id_" + tabela + "=?";
        st = conexao.prepareStatement(sql);
        st.setString(1, id);
        st.executeUpdate();
    }
    
    public void insereLivro(LivrosDAO livro) throws SQLException {

        //INSERT
        String sql = "insert into livros (nome, isbn, autor, ano, edicao, editora, qtd) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, livro.getNome());
        st.setString(2, livro.getIsbn());
        st.setString(3, livro.getAutor());
        st.setInt(4, livro.getAno());
        st.setInt(5, livro.getEdicao());
        st.setString(6, livro.getEditora());
        st.setInt(7, livro.getDisponiveis());
        st.executeUpdate();
    }
    
    public void editaLivro(LivrosDAO livro) throws SQLException {

        //UPDATE
        String sql = "update livros set nome=?, isbn=?, autor=?, ano=?, edicao=?, editora=?, qtd=? where id=?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, livro.getNome());
        st.setString(2, livro.getIsbn());
        st.setString(3, livro.getAutor());
        st.setInt(4, livro.getAno());
        st.setInt(5, livro.getEdicao());
        st.setString(6, livro.getEditora());
        st.setInt(7, livro.getDisponiveis());
        st.setInt(8, livro.getId());
        st.executeUpdate();
    }
    
    public ArrayList<UsuarioDAO> retornaUser(String pesquisa, String tipo) throws SQLException, ClassNotFoundException  {

        //SELECT
        ArrayList<UsuarioDAO> ret = new ArrayList<>();
        String sql = "select * from usuario";
        
        if(tipo.equals("login"))
            sql = sql.concat(" where login like ?");
        else if(tipo.equals("id"))
            sql = sql.concat(" where id = ?");
        else if(tipo.equals("tipo"))
            sql = sql.concat(" where tipo like ?");
        else if(tipo.equals("nome"))
            sql = sql.concat(" where nome like ?");
        else
            sql = sql.concat(" where 1=1");

        PreparedStatement st = conexao.prepareStatement(sql);

        if(tipo.equals("id"))
            st.setString(1, pesquisa); 
        else if(!tipo.equals(""))
            st.setString(1, "%" + pesquisa + "%");

        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {//ENQUANTO TEM CONTEUDO NA TABELA
            UsuarioDAO user = new UsuarioDAO();//CRIA NOVO ALUNO
            user.setId(rs.getInt("id"));
            user.setNome(rs.getString("nome"));//ATRIBUI AS VARIAVEIS
            user.setLogin(rs.getString("login"));
            user.setTipo(rs.getString("tipo"));
            if(user.getTipo().equals("Aluno")){
                user.setTempoRetirada(user.getTempoAluno());
                user.setMaxLivros(user.getMaxALuno());
            }else{
                user.setTempoRetirada(user.getTempoFuncs());
                user.setMaxLivros(user.getMaxFuncs());
            }
            user.setSenha(rs.getString("senha"));
//            if(pesquisa.equals("")){
                user.setLstLivros(this.livrosRetirado(user));
                user.setLstMultas(this.getMultas(user));
//            }
            ret.add(user);//ADICIONA NA LISTA
        }
        rs.close();
        st.close();
        return ret;
    }

    public Boolean verificaUserPadrao() throws SQLException {
        
        //se não existir ao menos um usuário administrador, insere
        String sql = "select count(*) as cont from usuario where tipo='Adm'";
        Statement st = conexao.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        if (rs.getInt(1) == 0) {
            UsuarioDAO user = new UsuarioDAO();
            user.setNome("Adm Padrão");
            user.setLogin("adm");
            user.setSenha("123");
            user.setTipo("Adm");
            insereUsuario(user);
            rs.close();
            st.close();
            return false;
        }else{
            rs.close();
            st.close();
            return true;
        }
        
    }
    
    public ArrayList<LivrosDAO> retornaLivro(String pesquisa, String tipo) throws SQLException, ClassNotFoundException {
            
        //SELECT
        ArrayList<LivrosDAO> ret = new ArrayList<>();
        String sql = "select * from livros";

        if(tipo.equals("nome"))
            sql = sql.concat(" where nome like ?");
        else if(tipo.equals("autor"))
            sql = sql.concat(" where autor like ?");
        else if(tipo.equals("id"))
            sql = sql.concat(" where id = ?");
        else
            sql = sql.concat(" where isbn like ?");        

        sql = sql.concat(" order by " + tipo);
        
        PreparedStatement st = conexao.prepareStatement(sql);

        if(tipo.equals("id"))
            st.setString(1, pesquisa); 
        else 
            st.setString(1, "%" + pesquisa + "%");

        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {//ENQUANTO TEM CONTEUDO NA TABELA
            LivrosDAO livro = new LivrosDAO();//CRIA NOVO LIVRO
            livro.setId(rs.getInt("id"));
            livro.setNome(rs.getString("nome"));//ATRIBUI AS VARIAVEIS
            livro.setAutor(rs.getString("autor"));
            livro.setIsbn(rs.getString("isbn"));
            livro.setEditora(rs.getString("editora"));
            livro.setAno(rs.getInt("ano"));
            livro.setEdicao(rs.getInt("edicao"));
            livro.setDisponiveis(rs.getInt("qtd"));
            livro.setReservado(this.getReservado(livro.getId()));
            ret.add(livro);//ADICIONA NA LISTA
        }
        rs.close();
        st.close();
        return ret;
    }
    
    public ArrayList<UsuarioDAO> retornaEmpRes(String pesquisa, String tipo, String operacao, UsuarioDAO usuario) throws SQLException, ClassNotFoundException {
        
        ArrayList<String> pesquisas = new ArrayList<>();
        
        //DESCOBRE A PESQUISA
        ArrayList<UsuarioDAO> ids_usuario = new ArrayList<>();
        ArrayList<LivrosDAO> ids_livro = new ArrayList<>();
        if(!pesquisa.equals("")){
            if(tipo.equals("usuario"))
                ids_usuario = this.retornaUser(pesquisa, "nome");
            else if(tipo.equals("livro"))
                ids_livro = this.retornaLivro(pesquisa, "nome");
            else
                ids_livro = this.retornaLivro(pesquisa, "isbn");
        }else{
            pesquisas.add(pesquisa);
        }
        
        if(ids_usuario.size() > 0){
            for(UsuarioDAO us : ids_usuario)
                pesquisas.add(Integer.toString(us.getId()));
        }else{
            for(LivrosDAO ls : ids_livro)
                pesquisas.add(Integer.toString(ls.getId()));
        }
        ArrayList<UsuarioDAO> ret = new ArrayList<>();
        
        for(String pesquisar : pesquisas){
            
            //SELECT
            String sql;
            if(operacao.equals("Reservas"))
                sql = "select * from livros_reservados";
            else
                sql = "select * from livros_retirados";

            if(pesquisa.equals(""))
                sql = sql.concat(" where 1=?");
            else if(tipo.equals("usuario"))
                sql = sql.concat(" where id_usuario=?");
            else if(tipo.equals("isbn") || tipo.equals("livro"))
                sql = sql.concat(" where id_livro=?");
            else
                sql = sql.concat(" where id=?");

            if(usuario.getTipo().equals("Aluno"))
                sql = sql.concat(" and id_usuario=?");

            PreparedStatement st = conexao.prepareStatement(sql);

            if(pesquisa.equals(""))
                st.setString(1, "1");
            else
                st.setString(1, pesquisar);

            if(usuario.getTipo().equals("Aluno"))
                st.setInt(2, usuario.getId());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {//ENQUANTO TEM CONTEUDO NA TABELA
                UsuarioDAO user = new UsuarioDAO();
                ArrayList<LivrosDAO> livros = new ArrayList<>();
                user = this.retornaUser(rs.getString("id_usuario"), "id").get(0);
                livros = this.retornaLivro(rs.getString("id_livro"), "id");
                if(!operacao.equals("Reservas"))
                    livros.get(0).setDataRetirada(rs.getDate("data"));
                user.setLstLivros(livros);
                ret.add(user);//ADICIONA NA LISTA
            }
            rs.close();
            st.close();
        }
        return ret;
    }
    
    public ArrayList<MultasDAO> retornaMultas(String pesquisa, String tipo, String operacao, UsuarioDAO usuario) throws SQLException, ClassNotFoundException {
        
        ArrayList<String> pesquisas = new ArrayList<>();
        
        //DESCOBRE A PESQUISA
        ArrayList<UsuarioDAO> ids_usuario = new ArrayList<>();
        ArrayList<LivrosDAO> ids_livro = new ArrayList<>();
        if(!pesquisa.equals("")){
            if(tipo.equals("usuario"))
                ids_usuario = this.retornaUser(pesquisa, "nome");
            else if(tipo.equals("livro"))
                ids_livro = this.retornaLivro(pesquisa, "nome");
            else
                ids_livro = this.retornaLivro(pesquisa, "isbn");
        }else{
            pesquisas.add(pesquisa);
        }
        
        if(ids_usuario.size() > 0){
            for(UsuarioDAO us : ids_usuario)
                pesquisas.add(Integer.toString(us.getId()));
        }else{
            for(LivrosDAO ls : ids_livro)
                pesquisas.add(Integer.toString(ls.getId()));
        }
        ArrayList<MultasDAO> ret = new ArrayList<>();
        
        for(String pesquisar : pesquisas){
            
            //SELECT
            String sql = "select * from livros_retirados";

            if(pesquisa.equals(""))
                sql = sql.concat(" where 1=?");
            else if(tipo.equals("usuario"))
                sql = sql.concat(" where id_usuario=?");
            else if(tipo.equals("isbn") || tipo.equals("livro"))
                sql = sql.concat(" where id_livro=?");
            else
                sql = sql.concat(" where id=?");

            if(usuario.getTipo().equals("Aluno"))
                sql = sql.concat(" and id_usuario=?");

            PreparedStatement st = conexao.prepareStatement(sql);

            if(pesquisa.equals(""))
                st.setString(1, "1");
            else
                st.setString(1, pesquisar);

            if(usuario.getTipo().equals("Aluno"))
                st.setInt(2, usuario.getId());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {//ENQUANTO TEM CONTEUDO NA TABELA
                UsuarioDAO user = this.retornaUser(rs.getString("id_usuario"), "id").get(0);
                LivrosDAO livro = this.retornaLivro(rs.getString("id_livro"), "id").get(0);
                livro.setDataRetirada(rs.getDate("data"));
                MultasDAO multa = new MultasDAO();
                multa.setUser(user);
                multa.setLivro(livro);
                multa.setDataRetirada(rs.getDate("data"));
                multa.setDataDevolucao(this.getDevolucao(multa.getUser(), multa.getDataRetirada()));
                multa.setValor((int)this.diferencaEmDias(livro.getDataRetirada()) * multa.getAcrescimos());
                if(((int)this.diferencaEmDias(multa.getDataRetirada()) > multa.getUser().getTempoRetirada())){
                    ret.add(multa);//ADICIONA NA LISTA
                }
            }
            rs.close();
            st.close();
        }
        return ret;
    }
    
    public Boolean getReservado(int id) throws SQLException{
        
        //SELECT
        String sql = "select * from livros_reservados where id_livro=?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, id); 
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            return true;
        }
        rs.close();
        st.close();
        return false;
    }
    
    public void retiraLivro(LivrosDAO livro, UsuarioDAO user, String data) throws SQLException, ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date data_desejada = new SimpleDateFormat("dd/MM/yyyy").parse(data);  
        String dataBD = formato.format(data_desejada);

        //INSERT
        String sql = "insert into livros_retirados (id_livro, id_usuario, data) values (?, ?, ?)";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, livro.getId());
        st.setInt(2, user.getId());        
        st.setString(3, dataBD);   
        st.executeUpdate();        
        
        //UPDATE
        sql = "update livros set qtd=? where id=?";
        st = conexao.prepareStatement(sql);
        st.setInt(1, livro.getDisponiveis()-1);
        st.setInt(2, livro.getId());   
        st.executeUpdate();  
    }
    
    public void reservaLivro(LivrosDAO livro, UsuarioDAO user) throws SQLException, ParseException {
        
         //INSERT
        String sql = "insert into livros_reservados (id_livro, id_usuario, reservado) values (?, ?, '1')";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, livro.getId());
        st.setInt(2, user.getId());
        st.executeUpdate();   
    }
    
    public void devolveLivro(LivrosDAO livro, UsuarioDAO user) throws SQLException, ParseException {

        //DELETE
        String sql = "delete from livros_retirados where id_livro=? and id_usuario=?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, livro.getId());
        st.setInt(2, user.getId());
        st.executeUpdate();        
        
        //UPDATE
        sql = "update livros set qtd=? where id=?";
        st = conexao.prepareStatement(sql);
        st.setInt(1, livro.getDisponiveis()+1);
        st.setInt(2, livro.getId());   
        st.executeUpdate();  
    }
       
    public void renovaLivro(LivrosDAO livro, UsuarioDAO user, String data) throws SQLException, ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date data_desejada = new SimpleDateFormat("dd/MM/yyyy").parse(data);  
        String dataBD = formato.format(data_desejada);

        //UPDATE
        String sql = "update livros_retirados set data=? where id_usuario=? and id_livro=?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, dataBD); 
        st.setInt(2, user.getId()); 
        st.setInt(3, livro.getId());
        st.executeUpdate();        

    }
    
    public ArrayList<LivrosDAO> livrosRetirado(UsuarioDAO user) throws SQLException, ClassNotFoundException{
        
        //SELECT
        ArrayList<LivrosDAO> ret = new ArrayList<>();
        String sql = "select * from livros_retirados where id_usuario=?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, user.getId()); 
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            LivrosDAO livro = new LivrosDAO();//CRIA NOVO LIVRO
            int id_livro = rs.getInt("id_livro");
            livro = retornaLivro(Integer.toString(id_livro), "id").get(0);
            livro.setDataRetirada(rs.getDate("data"));
            ret.add(livro);
        }
        rs.close();
        st.close();
        return ret;
    }
    
    public double diferencaEmDias(Date dataInicial){
        Date dataFinal = new Date();
        dataFinal.getTime();
	long diferenca = dataFinal.getTime() - dataInicial.getTime();
	return (diferenca /1000) / 60 / 60 /24; //resultado é diferença entre as datas em dias
    }
    
    public ArrayList<MultasDAO> getMultas(UsuarioDAO user){
        ArrayList<MultasDAO> ret = new ArrayList<>();
        for(LivrosDAO livro : user.getLstLivros()){
            double diferencaDias = this.diferencaEmDias(livro.getDataRetirada());
            if((int)diferencaDias > user.getTempoRetirada()){
                MultasDAO multa = new MultasDAO();
                multa.setLivro(livro);
                multa.setUser(user);
                multa.setValor((int)diferencaDias * multa.getAcrescimos());
                ret.add(multa);
            }
        }
        return ret;
    }
        
    private Date getDevolucao(UsuarioDAO user, Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, user.getTempoRetirada());
        return c.getTime();
    }
    
}
