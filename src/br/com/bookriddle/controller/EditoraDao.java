/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Editora;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class EditoraDao implements DBModel {
    
    public static final String TABELA_EDITORA = "editora";
    private Editora editora;
    private Connection conn;
    
    public EditoraDao() {
        connect();
    }
    
    public EditoraDao(Editora editora) {
        this.editora = editora;
        connect();
    }

    @Override
    public boolean editar() {
        String sql = "update " + TABELA_EDITORA + " set nome = '" + editora.getNome() + "', status_editora = " + editora.getStatusEditora() + " where id = " + editora.getId();
                        
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
        String sql = "insert into " + TABELA_EDITORA + " values(null, '" + editora.getNome() + "', " + editora.getStatusEditora() + ")";
    
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
        String sql = "select * from " + TABELA_EDITORA + " " + clause;
        Editora e = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                e = new Editora();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setStatusEditora(rs.getInt("status_editora"));
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        Object o = (Editora)e;
        return o;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select * from " + TABELA_EDITORA + " " + clause;

        ArrayList<Editora> listEditora = new ArrayList<>();
        Editora e = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                e = new Editora();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setStatusEditora(rs.getInt("status_editora"));
                listEditora.add(e);
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> generic = (ArrayList<Editora>)listEditora;
        return generic;
    }

    @Override
    public Integer countRegisters(String clause) {
        String sql = "select count(id) as count from " + TABELA_EDITORA + " " + clause;
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
