/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Supervisor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class SupervisorDao implements DBModel {
    
    public static final String TABELA_SUPERVISOR = "supervisor";
    private Connection conn;
    private Supervisor supervisor;
    
    public SupervisorDao() {
        connect();
    }
    
    public SupervisorDao(Supervisor supervisor) {
        connect();
        this.supervisor = supervisor;
    }
    
    public static Supervisor login(Supervisor sup) {
        Connection conn = null;
        try{
            conn = BookRiddleDB.getConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        String sql = "select supervisor.login, supervisor.senha, supervisor.funcionario_matricula, supervisor.status_supervisor, funcionario.nome from " 
                + TABELA_SUPERVISOR + " join " + FuncionarioDao.TABELA_FUNCIONARIO + " on supervisor.funcionario_matricula = matricula where login = '" 
                + sup.getLogin() + "' and status_supervisor = 1";
        Supervisor supervisor = null;
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                supervisor = new Supervisor();
                supervisor.setLogin(rs.getString("login"));
                supervisor.setSenha(rs.getString("senha"));
                supervisor.setStatus(rs.getInt("status_supervisor"));
                supervisor.setNome(rs.getString("nome"));
                supervisor.setMatricula(rs.getString("funcionario_matricula"));
            }
            conn.close();
            if(supervisor != null && supervisor.getSenha().equals(sup.getSenha())) {
                return supervisor;
            } else {
                return null;
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Supervisor loginAdmin(Supervisor sup) {
        Connection conn = null;
        try{
            conn = BookRiddleDB.getConnection();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        String sql = "select * from " + TABELA_SUPERVISOR + " where login = 'admin'";
        Supervisor supervisor = null;
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                supervisor = new Supervisor();
                supervisor.setLogin(rs.getString("login"));
                supervisor.setSenha(rs.getString("senha"));
                supervisor.setStatus(rs.getInt("status_supervisor"));
                supervisor.setMatricula(rs.getString("funcionario_matricula"));
            }
            conn.close();
            if(supervisor.getLogin().equals(sup.getLogin()) && supervisor.getSenha().equals(sup.getSenha())) {
                return supervisor;
            } else {
                return null;
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean editar() {
        String sql = null;
        if(!supervisor.getLogin().equals(supervisor.getTemp_login())) {
            Supervisor sup = (Supervisor) buscar("where login = '" + supervisor.getLogin() + "'");
            if(sup == null) {
                sql = "update " + TABELA_SUPERVISOR + " set login = '" + supervisor.getLogin() + "', funcionario_matricula = '" +
                supervisor.getMatricula() + "', senha = '" + supervisor.getSenha() + "' where login = '" + supervisor.getTempLogin() + "'";
            }
        } else {
            sql = "update " + TABELA_SUPERVISOR + " set funcionario_matricula = '" +
                supervisor.getMatricula() + "', senha = '" + supervisor.getSenha() + "', codigo = " + supervisor.getCodValidacao() + " where login = '" + supervisor.getLogin() + "'";
        }
        
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
        String sql = "update " + TABELA_SUPERVISOR + " set status_supervisor = " + 0 + " where login = '" + supervisor.getLogin() + "'";
        
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso");
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean inserir() {
        String sql = "insert into " + TABELA_SUPERVISOR + " values('" + supervisor.getLogin() + "', '" + supervisor.getSenha() + "', " + supervisor.getMatricula() + ", 1, null)";
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
        String sql = "select supervisor.login, supervisor.senha, supervisor.codigo, supervisor.funcionario_matricula, supervisor.status_supervisor, " 
                + "funcionario.nome, funcionario.email, funcionario.cpf, funcionario.telefone from " + TABELA_SUPERVISOR 
                + " join " + FuncionarioDao.TABELA_FUNCIONARIO + " on supervisor.funcionario_matricula = funcionario.matricula " + clause;
        Supervisor sup = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                sup = new Supervisor();
                sup.setLogin(rs.getString("login"));
                sup.setSenha(rs.getString("senha"));
                sup.setStatus(rs.getInt("status_supervisor"));
                sup.setMatricula(rs.getString("funcionario_matricula"));
                sup.setCpf(rs.getString("cpf"));
                sup.setEmail(rs.getString("email"));
                sup.setTelefone(rs.getString("telefone"));
                sup.setNome(rs.getString("nome"));
                sup.setCodValidacao(rs.getInt("codigo"));
                sup.setTempLogin(sup.getLogin());
            }
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        Object o = (Supervisor)sup;
        return o;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select supervisor.login, supervisor.senha, supervisor.codigo, supervisor.funcionario_matricula, supervisor.status_supervisor, " 
                + "funcionario.nome, funcionario.email, funcionario.cpf, funcionario.telefone from " + TABELA_SUPERVISOR 
                + " join " + FuncionarioDao.TABELA_FUNCIONARIO + " on supervisor.funcionario_matricula = funcionario.matricula " + clause;
        ArrayList<Supervisor> listSuper = new ArrayList<>();
        Supervisor sup = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                sup = new Supervisor();
                sup.setLogin(rs.getString("login"));
                sup.setSenha(rs.getString("senha"));
                sup.setStatus(rs.getInt("status_supervisor"));
                sup.setMatricula(rs.getString("funcionario_matricula"));
                sup.setCpf(rs.getString("cpf"));
                sup.setEmail(rs.getString("email"));
                sup.setTelefone(rs.getString("telefone"));
                sup.setNome(rs.getString("nome"));
                sup.setCodValidacao(rs.getInt("codigo"));
                sup.setTempLogin(sup.getLogin());
                listSuper.add(sup);
            }
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> generic = (ArrayList<Supervisor>)listSuper;
        return generic;
    }
    
    @Override
    public Integer countRegisters(String clause) {
        String sql = "select count(login) as count from " + TABELA_SUPERVISOR + " " + clause;
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



