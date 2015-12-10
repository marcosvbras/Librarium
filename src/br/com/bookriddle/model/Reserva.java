/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.model;

import java.util.Date;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class Reserva {
    
    private int id, id_livro, status;
    private String matricula_funcionario;
    private Date data;
    private String[] info;

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdLivro() {
        return id_livro;
    }

    public void setIdLivro(int id_livro) {
        this.id_livro = id_livro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMatriculaFuncionario() {
        return matricula_funcionario;
    }

    public void setMatriculaFuncionario(String matricula_funcionario) {
        this.matricula_funcionario = matricula_funcionario;
    }
}
