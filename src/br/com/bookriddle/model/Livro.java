/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.model;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class Livro {
    
    public static final int STATUS_ATIVO = 1;
    public static final int STATUS_INATIVO = 0;
    private String titulo, edicao, armario, url, prateleira, isbn, descricao;
    private int ano, quantidade, id, paginas, status, autor_id, editora_id, area_id;
    private String[] info;
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAutorId() {
        return autor_id;
    }

    public void setAutorId(Integer autor_id) {
        this.autor_id = autor_id;
    }

    public String getArmario() {
        return armario;
    }

    public Integer getAreaId() {
        return area_id;
    }

    public void setAreaId(Integer area_id) {
        this.area_id = area_id;
    }

    public void setArmario(String armario) {
        this.armario = armario;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Integer getEditoraId() {
        return editora_id;
    }

    public void setEditoraId(Integer editora_id) {
        this.editora_id = editora_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
