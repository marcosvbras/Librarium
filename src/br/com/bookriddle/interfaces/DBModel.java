/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public interface DBModel {
    
    public boolean editar();
    
    public boolean excluir();
    
    public boolean inserir();
    
    public Object buscar(String clause);
    
    public ArrayList<?> buscarTodos(String clause);
    
    public Integer countRegisters(String clause);
    
    public void connect();
    
    public void closeConnection();
  
}
