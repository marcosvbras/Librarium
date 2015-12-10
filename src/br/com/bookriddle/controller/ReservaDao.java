/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Reserva;
import br.com.bookriddle.utilities.DateConvert;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class ReservaDao implements DBModel {
    
    public static final String TABELA_RESERVA = "reserva";
    private Connection conn;
    private Reserva reserva;
    
    public ReservaDao() {
        connect();
    }
    
    public ReservaDao(Reserva reserva) {
        connect();
        this.reserva = reserva;
    }

    @Override
    public boolean editar() {
        String sql = "update " + TABELA_RESERVA + " set data = '" + reserva.getData() + "', livro_id = " + reserva.getIdLivro()
                + ", funcionario_matricula = '" + reserva.getMatriculaFuncionario() + "', status_reserva = " + reserva.getStatus() 
                + " where id = " + reserva.getId();
                        
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
        
    }

    @Override
    public boolean inserir() {
        String sql = "insert into " + TABELA_RESERVA + " values(null, '" + DateConvert.convertToYearMonthDay(reserva.getData()) + "', " + reserva.getIdLivro() + ", " + reserva.getMatriculaFuncionario() + ", " + reserva.getStatus() + ")";
    
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            LivroDao ldao = null;
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Object buscar(String clause) {
        String sql = "select reserva.id, reserva.data, reserva.livro_id, reserva.funcionario_matricula, reserva.status_reserva, " 
                + "funcionario.nome, livro.titulo, livro.url from  " + TABELA_RESERVA + " join " + FuncionarioDao.TABELA_FUNCIONARIO
                + " on reserva.funcionario_matricula = funcionario.matricula join " + LivroDao.TABELA_LIVRO + " on livro.id = reserva.livro_id"
                + " " + clause;
        Reserva res = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                res = new Reserva();
                res.setId(rs.getInt("id"));
                res.setData(rs.getDate("data"));
                res.setIdLivro(rs.getInt("livro_ID"));
                res.setMatriculaFuncionario(rs.getString("funcionario_matricula"));
                res.setStatus(rs.getInt("status_reserva"));
                res.setInfo(new String[]{rs.getString("nome"), rs.getString("titulo"), rs.getString("url")});
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        Object o = (Reserva)res;
        return o;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select reserva.id, reserva.data, reserva.livro_id, reserva.funcionario_matricula, reserva.status_reserva, " 
                + "funcionario.nome, livro.titulo, livro.url from  " + TABELA_RESERVA + " join " + FuncionarioDao.TABELA_FUNCIONARIO
                + " on reserva.funcionario_matricula = funcionario.matricula join " + LivroDao.TABELA_LIVRO + " on livro.id = reserva.livro_id"
                + " " + clause;

        ArrayList<Reserva> listRes = new ArrayList<>();
        Reserva res = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                res = new Reserva();
                res.setId(rs.getInt("id"));
                res.setData(rs.getDate("data"));
                res.setIdLivro(rs.getInt("livro_ID"));
                res.setMatriculaFuncionario(rs.getString("funcionario_matricula"));
                res.setStatus(rs.getInt("status_reserva"));
                res.setInfo(new String[]{rs.getString("nome"), rs.getString("titulo"), rs.getString("url")});
                listRes.add(res);
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> generic = (ArrayList<Reserva>)listRes;
        return generic;
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
    
    public Integer countReserva(String clause) {
        String sql = "select count(id) as count from " + TABELA_RESERVA + " " + clause;
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
}
