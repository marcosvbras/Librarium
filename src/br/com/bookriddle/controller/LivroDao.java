/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Livro;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class LivroDao implements DBModel {
    
    public static final String TABELA_LIVRO = "livro";
    private Connection conn;
    private Livro livro;
    
    public LivroDao() {
        connect();
    }
    
    public LivroDao(Livro livro){
        connect();
        this.livro = livro;
    }
    
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public boolean editar() {
        String sql = "update " + TABELA_LIVRO + " set titulo = '" + livro.getTitulo() + "', autor = '" + livro.getAutor()
                + "', area = '" + livro.getArea() + "', edicao = '" + livro.getEdicao() + "', editora = '" 
                + livro.getEditora() + "', armario = '" + livro.getArmario() + "', prateleira = '" + livro.getPrateleira() 
                + "', ano = " + livro.getAno() + ", quantidade = " + livro.getQuantidade() + ", paginas = "
                + livro.getPaginas() + ", url = '" + livro.getUrl() + "', isbn = '" + livro.getIsbn() + "' where id = " + livro.getId();
        
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void excluir() {
        String sql = "update " + TABELA_LIVRO + " set status_livro = 0 where id = " + livro.getId();
        
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            excluirImagem();
            JOptionPane.showMessageDialog(null, "Livro excluído com sucesso");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void excluirImagem() { // Método que exclui a imagem do livro da aplicação
        File file = new File(System.getProperty("user.dir") + "/" + livro.getUrl());  
        file.delete(); 
    }

    @Override
    public boolean inserir() {
        String sql = "insert into " + TABELA_LIVRO + " values(null, '" + livro.getTitulo() + "', '" 
                + livro.getAutor() + "', '" + livro.getArea() + "', '" + livro.getEdicao() + "', '" 
                + livro.getEditora() + "', " + livro.getQuantidade() + ", " + livro.getPaginas() + ", '" 
                + livro.getArmario() + "', " + livro.getAno() + ", '" + livro.getPrateleira() + "', '" 
                + livro.getUrl() + "', " + livro.getStatus() + ", '" + livro.getIsbn() + "')";
        
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Object buscar(String clause) {
        String sql = "select * from " + TABELA_LIVRO + " " + clause;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setArea(rs.getString("area"));
                livro.setEdicao(rs.getString("edicao"));
                livro.setEditora(rs.getString("editora"));
                livro.setArmario(rs.getString("armario"));
                livro.setPrateleira(rs.getString("prateleira"));
                livro.setAno(rs.getInt("ano"));
                livro.setPaginas(rs.getInt("paginas"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setUrl(rs.getString("url"));
                livro.setStatus(rs.getInt("status_livro"));
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        Object o = (Livro)livro;
        return o;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select * from " + TABELA_LIVRO + " " + clause;
        ArrayList<Livro> listLivro = new ArrayList<Livro>();
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setArea(rs.getString("area"));
                livro.setEdicao(rs.getString("edicao"));
                livro.setEditora(rs.getString("editora"));
                livro.setArmario(rs.getString("armario"));
                livro.setPrateleira(rs.getString("prateleira"));
                livro.setAno(rs.getInt("ano"));
                livro.setPaginas(rs.getInt("paginas"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setUrl(rs.getString("url"));
                livro.setStatus(rs.getInt("status_livro"));
                listLivro.add(livro);
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> genericList = (ArrayList<Livro>) listLivro;
        return genericList;
    }

    @Override
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect() {
        try {
            conn = BookRiddleDB.getConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
