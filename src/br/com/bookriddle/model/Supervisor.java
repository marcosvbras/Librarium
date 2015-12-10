/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.model;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class Supervisor extends Funcionario {
    
    private String login, senha, temp_login;
    private int status, cod_validacao;
    
    public Supervisor(){};
    
    public Supervisor(String login, String senha) {
        setLogin(login);
        setSenha(senha);
    }
    
    public Integer getCodValidacao() {
        return cod_validacao;
    }

    public void setCodValidacao(Integer cod_verificacao) {
        this.cod_validacao = cod_verificacao;
    }
    
    public String getTemp_login() {
        return temp_login;
    }

    public void setTemp_login(String temp_login) {
        this.temp_login = temp_login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getTempLogin() {
        return temp_login;
    }

    public void setTempLogin(String temp_login) {
        this.temp_login = temp_login;
    }
}
