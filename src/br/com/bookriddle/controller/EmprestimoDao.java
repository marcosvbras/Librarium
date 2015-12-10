/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import br.com.bookriddle.interfaces.DBModel;
import br.com.bookriddle.model.Emprestimo;
import br.com.bookriddle.utilities.DateConvert;
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
public class EmprestimoDao implements DBModel {
    
    public static final String TABELA_EMPRESTIMO = "emprestimo";
    private Connection conn;
    private Emprestimo emprestimo;
    
    public EmprestimoDao() {
        connect();
    }
    
    public EmprestimoDao(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
        connect();
    }

    @Override
    public boolean editar() {
        String sql = "update " + TABELA_EMPRESTIMO + " set status_emprestimo = " + emprestimo.getStatusEmprestimo() + ", livro_id = " 
                + emprestimo.getIdLivro() + ", data_emprestimo = '" + DateConvert.convertToYearMonthDay(emprestimo.getDataEmprestimo()) 
                + "', data_devolucao = '" + DateConvert.convertToYearMonthDay(emprestimo.getDataDevolucao()) + "', supervisor_funcionario_matricula = " 
                + emprestimo.getMatriculaSupervisor() + ", funcionario_matricula = " + emprestimo.getMatriculaFuncionario() + ", status_devolucao = " 
                + emprestimo.getStatusDevolucao() + " where id = " + emprestimo.getId();
        
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
        String sql = "update " + TABELA_EMPRESTIMO + " set status_emprestimo = 0 where id = " + emprestimo.getId();
        
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            JOptionPane.showMessageDialog(null, "Empréstimo excluído com sucesso");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean inserir() {
        String sql = "insert into " + TABELA_EMPRESTIMO + " values(null, " + emprestimo.getStatusEmprestimo() + ", " 
                + emprestimo.getIdLivro() + ", '" + DateConvert.convertToYearMonthDay(emprestimo.getDataEmprestimo()) + "', '" 
                + DateConvert.convertToYearMonthDay(emprestimo.getDataDevolucao()) + "', " + emprestimo.getMatriculaSupervisor() + ", "
                + emprestimo.getMatriculaFuncionario() + ", " + emprestimo.getStatusDevolucao() + ")";
                
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
        String sql = "select funcionario.nome, livro.titulo, livro.url, emprestimo.id, emprestimo.status_emprestimo, emprestimo.livro_id, " 
                + "emprestimo.data_emprestimo, emprestimo.data_devolucao, emprestimo.supervisor_funcionario_matricula, "
                + "emprestimo.funcionario_matricula, emprestimo.status_devolucao from " + TABELA_EMPRESTIMO + " join " + FuncionarioDao.TABELA_FUNCIONARIO 
                + " on funcionario.matricula = emprestimo.funcionario_matricula join " + LivroDao.TABELA_LIVRO
                + " on livro.id = emprestimo.livro_id where emprestimo.id = " + emprestimo.getId();
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setIdLivro(rs.getInt("id_livro"));
                emprestimo.setMatriculaFuncionario(rs.getString("matricula_funcionario"));
                emprestimo.setMatriculaSupervisor(rs.getString("supervisor_funcionario_matricula"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao"));
                emprestimo.setStatusEmprestimo(rs.getInt("status_emprestimo"));
                emprestimo.setStatusDevolucao(rs.getInt("status_devolucao"));
                String[] info = new String[] {rs.getString("nome"), rs.getString("titulo"), rs.getString("url")};
                emprestimo.setInfo(info);
            }
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        Object o = (Emprestimo)this.emprestimo;
        return o;
    }
    
    public String buscarMaisPopular() {
        String sql = "select count(livro.id) as quantidade, livro.titulo from emprestimo join livro on emprestimo.livro_id = livro.id group by livro.titulo order by quantidade desc limit 1";
        String livro = "";
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                livro = rs.getString("titulo");
            }
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return livro;
    }

    @Override
    public ArrayList<?> buscarTodos(String clause) {
        String sql = "select funcionario.nome, livro.titulo, livro.url, emprestimo.id, emprestimo.status_emprestimo, emprestimo.livro_id, " 
                + "emprestimo.data_emprestimo, emprestimo.data_devolucao, emprestimo.supervisor_funcionario_matricula, "
                + "emprestimo.funcionario_matricula, emprestimo.status_devolucao from " + TABELA_EMPRESTIMO + " join " + FuncionarioDao.TABELA_FUNCIONARIO 
                + " on funcionario.matricula = emprestimo.funcionario_matricula join " + LivroDao.TABELA_LIVRO
                + " on livro.id = emprestimo.livro_id " + clause;
        ArrayList<Emprestimo> listEmp = new ArrayList<>();

        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setIdLivro(rs.getInt("livro_id"));
                emprestimo.setMatriculaFuncionario(rs.getString("funcionario_matricula"));
                emprestimo.setMatriculaSupervisor(rs.getString("supervisor_funcionario_matricula"));
                emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setDataDevolucao(rs.getDate("data_devolucao"));
                emprestimo.setStatusEmprestimo(rs.getInt("status_emprestimo"));
                emprestimo.setStatusDevolucao(rs.getInt("status_devolucao"));
                String[] info = new String[] {rs.getString("nome"), rs.getString("titulo"), rs.getString("url")};
                emprestimo.setInfo(info);
                listEmp.add(emprestimo);
            }
            stm.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        ArrayList<?> genericList = (ArrayList<Emprestimo>)listEmp;
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
    
    public Integer countEmprestimo(String clause) {
        String sql = "select count(id) as count from " + TABELA_EMPRESTIMO + " " + clause;
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
