/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.interfaces;

import java.util.ArrayList;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public interface ListagemModel {
    
    public void atualizarTabela();
    
    public void verDetalhes();
    
    public boolean isSelected();
    
    public void setList(ArrayList<?> list);

}
