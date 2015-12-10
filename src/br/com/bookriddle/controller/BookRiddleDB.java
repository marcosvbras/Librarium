/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class BookRiddleDB {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    // Identificação do driver do banco de dados
    private static final String USER = "root"; 
    // Usuário do banco de dados
    private static final String PASSWORD = ""; 
    // Senha do banco de dados
    private static final String IP = "localhost";
    // IP do banco de dados
    private static final String DATABASE = "bookriddle";
    // Nome do banco de dados
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE;
    // Endereço completo da conexão

    public static Connection getConnection() throws SQLException {
	Connection conn = null;
	try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
            return conn;
	} catch (ClassNotFoundException e) {
            String errorMsg = "Driver não encontrado";
            throw new SQLException(errorMsg, e);
	} catch (SQLException e) {
            String errorMsg = "Erro ao obter a conexão";
            throw new SQLException(errorMsg, e);
	}
    }	
}
