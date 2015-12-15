/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.model;

/**
 *
 * @author Marcos
 */
public class Editora {
    
    private int id, status_editora;
    private String nome;
    
    public Editora() {}
    
    public Editora(String nome) {
        this.nome = nome;
        this.status_editora = 1;
    }
    
    public int getStatusEditora() {
        return status_editora;
    }

    public void setStatusEditora(int status_editora) {
        this.status_editora = status_editora;
    }

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
    
}
