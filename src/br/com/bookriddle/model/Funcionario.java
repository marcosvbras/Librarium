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
public class Funcionario {
    
    public static final int STATUS_ATIVO = 1;
    public static final int STATUS_INATIVO = 0;
    
    private String nome, telefone, email, cpf;
    protected String matricula;
    private int status;
    private String temp_matricula;

    public String getTempMatricula() {
        return temp_matricula;
    }

    public void setTempMatricula(String temp_matricula) {
        this.temp_matricula = temp_matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
