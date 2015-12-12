/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Funcionario;
import br.com.bookriddle.utilities.MailSender;
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
public class FuncionarioDao implements DBModel {
    
    public static final String TABELA_FUNCIONARIO = "funcionario";
    private Connection conn;
    private Funcionario funcionario;
    
    public FuncionarioDao() {
        connect();
    }
    
    public FuncionarioDao(Funcionario funcionario) {
        connect();
        this.funcionario = funcionario;
    }

    @Override
    public boolean editar() {
        String sql = null;
       
        if(!funcionario.getTempMatricula().equals(funcionario.getMatricula())) { // Caso a matrícula tenha sido alterada
            Funcionario f = (Funcionario)buscar("where matricula = '" + funcionario.getMatricula() + "'");
            if(f != null) { // Se for diferente de null, a matrícula já existe
                JOptionPane.showMessageDialog(null, "Matrícula já cadastrada, tente novamente");
            } else { 
                sql = "update " + TABELA_FUNCIONARIO + " set matricula = '" + funcionario.getMatricula() + "', nome = '" + funcionario.getNome() + "', telefone = '" + funcionario.getTelefone()
                    + "', email = '" + funcionario.getEmail() + "', cpf = '" + funcionario.getCpf() + "' where matricula = '"
                    + funcionario.getTempMatricula() + "'";
            }
        } else { // Caso a matrícula não tenha sido alterada
            sql = "update " + TABELA_FUNCIONARIO + " set nome = '" + funcionario.getNome() + "', telefone = '" + funcionario.getTelefone()
                    + "', email = '" + funcionario.getEmail() + "', cpf = '" + funcionario.getCpf() + "' where matricula = '"
                    + funcionario.getMatricula() + "'";
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
        String sql = "update " + TABELA_FUNCIONARIO + " set status_funcionario = 0 where matricula = " + funcionario.getMatricula();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso");
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean inserir() {
        String sql = "insert into " + TABELA_FUNCIONARIO + " values('" + funcionario.getMatricula() + "', '" + funcionario.getNome()
                + "', '" + funcionario.getCpf() + "', '" + funcionario.getEmail() + "', '" + funcionario.getTelefone() 
                + "', 1)";
                
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            enviarEmail();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private void enviarEmail() {
        String assunto = "Cadastro Book Riddle";
        String mensagem = "Olá, " + funcionario.getNome() + ".\nVocê foi cadastrado com sucesso no Book Riddle.";
        try {
            MailSender.sendMail(funcionario.getEmail(), assunto, mensagem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object buscar(String clause) {
        String sql = "select * from " + TABELA_FUNCIONARIO + " " + clause;
        Funcionario funcionario = null;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                funcionario = new Funcionario();
                funcionario.setMatricula(rs.getString("matricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setStatus(rs.getInt("status_funcionario"));
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        Object o = (Funcionario)funcionario;
        return o;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select * from " + TABELA_FUNCIONARIO + " " + clause;
        ArrayList<Funcionario> listFun = new ArrayList<>();
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                funcionario = new Funcionario();
                funcionario.setMatricula(rs.getString("matricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setStatus(rs.getInt("status_funcionario"));
                listFun.add(funcionario);
            }
            
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> genericList = (ArrayList<Funcionario>)listFun;
        return genericList;
    }
    
    @Override
    public Integer countRegisters(String clause) {
        String sql = "select count(matricula) as count from " + TABELA_FUNCIONARIO + " " + clause;
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
