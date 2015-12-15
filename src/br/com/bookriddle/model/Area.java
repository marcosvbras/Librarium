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
public class Area {
    
    private int id, status_area;
    private String nome;
    
    public Area(){}
    
    public Area(String nome) {
        this.nome = nome;
        this.status_area = 1;
    }
    
    public int getStatusArea() {
        return status_area;
    }

    public void setStatusArea(int status_area) {
        this.status_area = status_area;
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
