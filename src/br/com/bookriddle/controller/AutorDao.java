/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Autor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class AutorDao implements DBModel {

    public static final String TABELA_AUTOR = "autor";
    private Autor autor;
    private Connection conn;
    
    public AutorDao() {
        connect();
    }
    
    public AutorDao(Autor autor) {
        this.autor = autor;
        connect();
    }
    
    @Override
    public boolean editar() {
        String sql = "update " + TABELA_AUTOR + " set nome = '" + autor.getNome() + "' where id = " + autor.getId();
                        
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
    public boolean excluir() {
        return editar();
    }

    @Override
    public boolean inserir() {
        String sql = "insert into " + TABELA_AUTOR + " values(null, '" + autor.getNome() + "')";
    
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
        String sql = "select * from " + TABELA_AUTOR + " " + clause;
        Autor a = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                a = new Autor();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        Object o = (Autor)a;
        return o;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select * from " + TABELA_AUTOR + " " + clause;

        ArrayList<Autor> listAutor = new ArrayList<>();
        Autor a = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                a = new Autor();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                listAutor.add(a);
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> generic = (ArrayList<Autor>)listAutor;
        return generic;
    }
    
    @Override
    public Integer countRegisters(String clause) {
        String sql = "select count(id) as count from " + TABELA_AUTOR + " " + clause;
        int count = 0;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                count = rs.getInt("count");
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return count;
    }

    @Override
    public void connect() {
        try {
            conn = BookRiddleDB.getConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
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
}
