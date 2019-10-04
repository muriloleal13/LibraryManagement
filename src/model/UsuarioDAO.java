/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author musle
 */
public class UsuarioDAO {
    private int id;
    private String nome;
    private String login;
    private String tipo;
    private String senha;
    private Integer tempoRetirada;
    private Integer maxLivros;
    private ArrayList<LivrosDAO> lstLivros;
    private ArrayList<MultasDAO> lstMultas;
    private Integer tempoFuncs = 15;
    private Integer tempoAluno = 7;
    private Integer maxFuncs = 5;
    private Integer maxALuno = 3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<LivrosDAO> getLstLivros() {
        return lstLivros;
    }

    public void setLstLivros(ArrayList<LivrosDAO> lstLivros) {
        this.lstLivros = lstLivros;
    }

    public ArrayList<MultasDAO> getLstMultas() {
        return lstMultas;
    }

    public void setLstMultas(ArrayList<MultasDAO> lstMultas) {
        this.lstMultas = lstMultas;
    }

    public Integer getTempoRetirada() {
        if(getTipo().equals("Adm") || getTipo().equals("Func") || getTipo().equals("Prof"))
            return getTempoFuncs();
        else
            return getTempoAluno();
    }

    public void setTempoRetirada(Integer tempoRetirada) {
        this.tempoRetirada = tempoRetirada;
    }

    public Integer getTempoFuncs() {
        return tempoFuncs;
    }

    public void setTempoFuncs(Integer tempoFuncs) {
        this.tempoFuncs = tempoFuncs;
    }

    public Integer getTempoAluno() {
        return tempoAluno;
    }

    public void setTempoAluno(Integer tempoAluno) {
        this.tempoAluno = tempoAluno;
    }

    public Integer getMaxLivros() {
        return maxLivros;
    }

    public void setMaxLivros(Integer maxLivros) {
        this.maxLivros = maxLivros;
    }

    public Integer getMaxFuncs() {
        return maxFuncs;
    }

    public void setMaxFuncs(Integer maxFuncs) {
        this.maxFuncs = maxFuncs;
    }

    public Integer getMaxALuno() {
        return maxALuno;
    }

    public void setMaxALuno(Integer maxALuno) {
        this.maxALuno = maxALuno;
    }
    
    public String toString(){
        return getId() + " - " + getNome();
    }
    
}
