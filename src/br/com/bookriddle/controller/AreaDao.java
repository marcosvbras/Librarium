/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Area;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marcos
 */
public class AreaDao implements DBModel {
    
    public static final String TABELA_AREA = "area";
    private Area area;
    private Connection conn;
    
    public AreaDao() {
        connect();
    }
    
    public AreaDao(Area area) {
        this.area = area;
        connect();
    }
    
    @Override
    public boolean editar() {
        String sql = "update " + TABELA_AREA + " set nome = '" + area.getNome() + "', status_area = " + area.getStatusArea() + " where id = " + area.getId();
                        
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
        String sql = "insert into " + TABELA_AREA + " values(null, '" + area.getNome() + "', " + area.getStatusArea() +")";
    
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
        String sql = "select * from " + TABELA_AREA + " " + clause;
        Area a = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                a = new Area();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setStatusArea(rs.getInt("status_area"));
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        Object o = (Area)a;
        return o;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select * from " + TABELA_AREA + " " + clause;

        ArrayList<Area> listArea = new ArrayList<>();
        Area a = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                a = new Area();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setStatusArea(rs.getInt("status_area"));
                listArea.add(a);
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> generic = (ArrayList<Area>)listArea;
        return generic;
    }

    @Override
    public Integer countRegisters(String clause) {
        String sql = "select count(id) as count from " + TABELA_AREA + " " + clause;
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
