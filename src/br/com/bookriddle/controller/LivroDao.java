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
        String sql = "update " + TABELA_LIVRO + " set titulo = '" + livro.getTitulo() + "', autor_id = " + livro.getAutorId()
                + ", area_id = " + livro.getAreaId() + ", edicao = '" + livro.getEdicao() + "', editora_id = " 
                + livro.getEditoraId() + ", armario = '" + livro.getArmario() + "', prateleira = '" + livro.getPrateleira() 
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
    public boolean excluir() {
        String sql = "update " + TABELA_LIVRO + " set status_livro = 0 where id = " + livro.getId();
        
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            stm.close();
            excluirImagem();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void excluirImagem() { // Método que exclui a imagem do livro da aplicação
        File file = new File(System.getProperty("user.dir") + "/" + livro.getUrl());  
        file.delete(); 
    }

    @Override
    public boolean inserir() {
        String sql = "insert into " + TABELA_LIVRO + " values(null, '" + livro.getTitulo() + "', " 
                + livro.getAutorId() + ", " + livro.getAreaId() + ", '" + livro.getEdicao() + "', " 
                + livro.getEditoraId() + ", " + livro.getQuantidade() + ", " + livro.getPaginas() + ", '" 
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
        String sql = "select livro.id, livro.titulo, livro.autor_id, livro.area_id, livro.edicao, livro.editora_id, livro.quantidade, livro.paginas, livro.armario, " 
                + "livro.ano, livro.prateleira, livro.url, livro.status_livro, livro.isbn, autor.nome as autor, area.nome as area, editora.nome as editora from " + TABELA_LIVRO + " join "
                + AutorDao.TABELA_AUTOR + " on livro.autor_id = autor.id join " + AreaDao.TABELA_AREA + " on livro.area_id = area.id join " + EditoraDao.TABELA_EDITORA
                + " on livro.editora_id = editora.id " + clause;
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutorId(rs.getInt("autor_id"));
                livro.setAreaId(rs.getInt("area_id"));
                livro.setEdicao(rs.getString("edicao"));
                livro.setEditoraId(rs.getInt("editora_id"));
                livro.setArmario(rs.getString("armario"));
                livro.setPrateleira(rs.getString("prateleira"));
                livro.setAno(rs.getInt("ano"));
                livro.setPaginas(rs.getInt("paginas"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setUrl(rs.getString("url"));
                livro.setStatus(rs.getInt("status_livro"));
                livro.setInfo(new String[]{rs.getString("autor"), rs.getString("area"), rs.getString("editora")});
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
        String sql = "select livro.id, livro.titulo, livro.autor_id, livro.area_id, livro.edicao, livro.editora_id, livro.quantidade, livro.paginas, livro.armario, " 
                + "livro.ano, livro.prateleira, livro.url, livro.status_livro, livro.isbn, autor.nome as autor, area.nome as area, editora.nome as editora from " + TABELA_LIVRO + " join "
                + AutorDao.TABELA_AUTOR + " on livro.autor_id = autor.id join " + AreaDao.TABELA_AREA + " on livro.area_id = area.id join " + EditoraDao.TABELA_EDITORA
                + " on livro.editora_id = editora.id " + clause;
        ArrayList<Livro> listLivro = new ArrayList<Livro>();
        
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setIsbn(rs.getString("isbn"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutorId(rs.getInt("autor_id"));
                livro.setAreaId(rs.getInt("area_id"));
                livro.setEdicao(rs.getString("edicao"));
                livro.setEditoraId(rs.getInt("editora_id"));
                livro.setArmario(rs.getString("armario"));
                livro.setPrateleira(rs.getString("prateleira"));
                livro.setAno(rs.getInt("ano"));
                livro.setPaginas(rs.getInt("paginas"));
                livro.setQuantidade(rs.getInt("quantidade"));
                livro.setUrl(rs.getString("url"));
                livro.setStatus(rs.getInt("status_livro"));
                livro.setInfo(new String[]{rs.getString("autor"), rs.getString("area"), rs.getString("editora")});
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
    public Integer countRegisters(String clause) {
        String sql = "select count(id) as count from " + TABELA_LIVRO + " " + clause;
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
