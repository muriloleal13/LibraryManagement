/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author musle
 */
public class MultasDAO {
    
    private UsuarioDAO user;
    private LivrosDAO livro;
    private Date dataRetirada;
    private Date dataDevolucao;
    private double valor;
    private double acrescimos = 1.00;
    
    public UsuarioDAO getUser() {
        return user;
    }

    public void setUser(UsuarioDAO user) {
        this.user = user;
    }

    public LivrosDAO getLivro() {
        return livro;
    }

    public void setLivro(LivrosDAO livro) {
        this.livro = livro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getAcrescimos() {
        return acrescimos;
    }

    public void setAcrescimos(double acrescimos) {
        this.acrescimos = acrescimos;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }


    
}
