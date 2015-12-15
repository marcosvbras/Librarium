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
public class Autor {
    
    private int id, status_autor;
    private String nome;
    
    public Autor() {}
    
    public Autor(String nome) {
        this.nome = nome;
        this.status_autor = 1;
    }
    
    public int getStatusAutor() {
        return status_autor;
    }

    public void setStatusAutor(int status_autor) {
        this.status_autor = status_autor;
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
