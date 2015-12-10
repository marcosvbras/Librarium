/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class ImageTransfer {
    
    private File imagem_selecionada;
    private String source_path;
    
    public ImageTransfer(File imagem_selecionada, String source_path) {
        this.imagem_selecionada = imagem_selecionada;
        this.source_path = source_path;
    }
    
    public String getDestinationPath() { // Método que retorna o caminho da pasta Imagens do projeto
        return "src/br/com/bookriddle/imagens/livros/" + imagem_selecionada.getName();
    }  
        
    public void transferirImagem() {
        /*
         * Método que faz uma cópia da imagem selecionada e a salva na pasta de imagens da aplicação
         */
        
        if(imagem_selecionada != null) {
            try {
                FileInputStream fisDe = new FileInputStream(source_path); 
                FileOutputStream fisPara = new FileOutputStream(getDestinationPath());
                
                FileChannel fcPara = fisDe.getChannel();
                FileChannel fcDe = fisPara.getChannel();

                if(fcPara.transferTo(0, fcPara.size(), fcDe ) == 0L) {
                    fcPara.close();
                    fcDe.close();
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao duplicar a imagem");
                e.printStackTrace();
            }
        }
    }
}
