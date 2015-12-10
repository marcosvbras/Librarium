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
public class Emprestimo {
    
    private int id, id_livro, status_emprestimo, status_devolucao;
    private String matricula_supervisor, matricula_funcionario;
    private Date data_emprestimo, data_devolucao;
    private String[] info;

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }
    
    public int getStatusEmprestimo() {
        return status_emprestimo;
    }

    public void setStatusEmprestimo(int status_emprestimo) {
        this.status_emprestimo = status_emprestimo;
    }
    
    public int getStatusDevolucao() {
        return status_devolucao;
    }

    public void setStatusDevolucao(int status_devolucao) {
        this.status_devolucao = status_devolucao;
    }

    public String getMatriculaSupervisor() {
        return matricula_supervisor;
    }

    public void setMatriculaSupervisor(String matricula_supervisor) {
        this.matricula_supervisor = matricula_supervisor;
    }

    public int getIdLivro() {
        return id_livro;
    }

    public void setIdLivro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatriculaFuncionario() {
        return matricula_funcionario;
    }

    public void setMatriculaFuncionario(String matricula_funcionario) {
        this.matricula_funcionario = matricula_funcionario;
    }

    public Date getDataEmprestimo() {
        return data_emprestimo;
    }

    public void setDataEmprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getDataDevolucao() {
        return data_devolucao;
    }

    public void setDataDevolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
