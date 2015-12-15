/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.view;

import br.com.bookriddle.controller.SupervisorDao;
import br.com.bookriddle.model.Funcionario;
import br.com.bookriddle.model.Supervisor;
import br.com.bookriddle.utilities.MailSender;
import java.awt.Color;
import java.util.Random;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class FrmRecuperarSenha extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    
    private FrmInicial principal;
    private Supervisor supervisor;
    private SupervisorDao safaDao;
    private Funcionario funcionario;
    private boolean state = false;
    
    public FrmRecuperarSenha() {
        initComponents();
        setLocationRelativeTo(null);
        txt_email.setEnabled(false);
        btn_send.setVisible(false);
        txt_password.setEnabled(false);
        txt_password_repeat.setEnabled(false);
        lbl_salvar.setForeground(new Color(227,6,19));
    }
    
    private String senhaToString(char[] pass) {
        String senha = "";
        
        for (int i = 0; i < pass.length; i++) {
            senha += Character.toString(pass[i]);	
	}
        return senha;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_tipo = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        lbl_recovery = new javax.swing.JLabel();
        btn_validar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        painel_1 = new javax.swing.JPanel();
        txt_email = new javax.swing.JTextField();
        btn_send = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        painel_2 = new javax.swing.JPanel();
        lbl_nova_senha = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        lbl_nova_senha_repeat = new javax.swing.JLabel();
        txt_password_repeat = new javax.swing.JPasswordField();
        btn_salvar = new javax.swing.JPanel();
        lbl_salvar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Book Riddle - Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(237, 250, 251));

        lbl_tipo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_tipo.setForeground(new java.awt.Color(51, 51, 51));
        lbl_tipo.setText("Código");

        txt_codigo.setForeground(new java.awt.Color(51, 51, 51));

        lbl_recovery.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbl_recovery.setForeground(new java.awt.Color(51, 51, 51));
        lbl_recovery.setText("Ainda não possuo o código");
        lbl_recovery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_recovery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_recoveryMouseClicked(evt);
            }
        });

        btn_validar.setBackground(new java.awt.Color(227, 6, 19));
        btn_validar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_validarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_validarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_validarMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Validar");

        javax.swing.GroupLayout btn_validarLayout = new javax.swing.GroupLayout(btn_validar);
        btn_validar.setLayout(btn_validarLayout);
        btn_validarLayout.setHorizontalGroup(
            btn_validarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        btn_validarLayout.setVerticalGroup(
            btn_validarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        painel_1.setBackground(new java.awt.Color(237, 250, 251));

        txt_email.setForeground(new java.awt.Color(51, 51, 51));

        btn_send.setBackground(new java.awt.Color(227, 6, 19));
        btn_send.setPreferredSize(new java.awt.Dimension(100, 42));
        btn_send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sendMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sendMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_sendMouseExited(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/white_airplane_small.png"))); // NOI18N

        javax.swing.GroupLayout btn_sendLayout = new javax.swing.GroupLayout(btn_send);
        btn_send.setLayout(btn_sendLayout);
        btn_sendLayout.setHorizontalGroup(
            btn_sendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );
        btn_sendLayout.setVerticalGroup(
            btn_sendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        lbl_email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_email.setForeground(new java.awt.Color(51, 51, 51));
        lbl_email.setText("Email");

        javax.swing.GroupLayout painel_1Layout = new javax.swing.GroupLayout(painel_1);
        painel_1.setLayout(painel_1Layout);
        painel_1Layout.setHorizontalGroup(
            painel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_1Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(lbl_email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        painel_1Layout.setVerticalGroup(
            painel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_1Layout.createSequentialGroup()
                .addGroup(painel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_email, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        painel_2.setBackground(new java.awt.Color(237, 250, 251));

        lbl_nova_senha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_nova_senha.setForeground(new java.awt.Color(51, 51, 51));
        lbl_nova_senha.setText("Nova Senha");

        txt_password.setForeground(new java.awt.Color(51, 51, 51));

        lbl_nova_senha_repeat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_nova_senha_repeat.setForeground(new java.awt.Color(51, 51, 51));
        lbl_nova_senha_repeat.setText("<html>Repita a<br>Nova Senha<html>");

        txt_password_repeat.setForeground(new java.awt.Color(51, 51, 51));

        btn_salvar.setBackground(new java.awt.Color(227, 6, 19));
        btn_salvar.setMaximumSize(new java.awt.Dimension(464, 53));
        btn_salvar.setMinimumSize(new java.awt.Dimension(464, 53));
        btn_salvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salvarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_salvarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_salvarMouseExited(evt);
            }
        });

        lbl_salvar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_salvar.setForeground(new java.awt.Color(255, 255, 255));
        lbl_salvar.setText("Salvar");

        javax.swing.GroupLayout btn_salvarLayout = new javax.swing.GroupLayout(btn_salvar);
        btn_salvar.setLayout(btn_salvarLayout);
        btn_salvarLayout.setHorizontalGroup(
            btn_salvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_salvarLayout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .addComponent(lbl_salvar)
                .addGap(196, 196, 196))
        );
        btn_salvarLayout.setVerticalGroup(
            btn_salvarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_salvarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_salvar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout painel_2Layout = new javax.swing.GroupLayout(painel_2);
        painel_2.setLayout(painel_2Layout);
        painel_2Layout.setHorizontalGroup(
            painel_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(painel_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_nova_senha)
                    .addComponent(lbl_nova_senha_repeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painel_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_password_repeat, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(txt_password))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btn_salvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        painel_2Layout.setVerticalGroup(
            painel_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_2Layout.createSequentialGroup()
                .addGroup(painel_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nova_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_nova_senha_repeat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_password_repeat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Recuperação de Senha");

        jPanel2.setBackground(new java.awt.Color(227, 6, 19));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/white_logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(87, 87, 87))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(painel_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_recovery)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_tipo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_validar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(painel_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_validar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(lbl_recovery, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painel_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painel_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_recoveryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_recoveryMouseClicked
        txt_email.setEnabled(true);
        btn_send.setVisible(true);
    }//GEN-LAST:event_lbl_recoveryMouseClicked

    private void btn_sendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sendMouseClicked
        String email = txt_email.getText();
        if(!email.equals("")) {
            safaDao = new SupervisorDao();
            supervisor = (Supervisor)safaDao.buscar("where funcionario.email = '" + email + "'");
            
            if(supervisor != null) {
                try {
                    String assunto = "Recuperação de Senha - Book Riddle";
                    Random gerador = new Random();
                    int codigo = gerador.nextInt(10000) + 1000;
                    String mensagem = "Insira o código: " + codigo + " no Book Riddle e recupere sua senha.";
                    MailSender.sendMail(email, assunto, mensagem);
                    supervisor.setCodValidacao(codigo);
                    safaDao = new SupervisorDao(supervisor);
                    safaDao.editar();
                    safaDao.closeConnection();

                    btn_send.setVisible(false);
                    txt_email.setEnabled(false);
                    txt_email.setText("");
                    JOptionPane.showMessageDialog(null, "Código de verificação enviado para " + email);
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Este email não está cadastrado no sistema");
            }          
        } else {
            JOptionPane.showMessageDialog(null, "Digite um e-mail válido");
        }
    }//GEN-LAST:event_btn_sendMouseClicked

    private void btn_sendMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sendMouseEntered
        btn_send.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_btn_sendMouseEntered

    private void btn_sendMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sendMouseExited
        btn_send.setBackground(new Color(227,6,19));
    }//GEN-LAST:event_btn_sendMouseExited

    private void btn_validarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_validarMouseClicked
        String codigo = txt_codigo.getText();

        if(!codigo.equals("")) {
            safaDao = new SupervisorDao();
            this.supervisor = (Supervisor)safaDao.buscar("where codigo = " + codigo);
            safaDao.closeConnection();
            
            if(this.supervisor != null) {
                txt_password.setEnabled(true);
                txt_password_repeat.setEnabled(true);
                lbl_salvar.setForeground(new Color(255,255,255));
                state = true;
            } else {
                JOptionPane.showMessageDialog(null, "Digite um código válido ou gere um novo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Digite um código de recuperação válido");
        }
    }//GEN-LAST:event_btn_validarMouseClicked

    private void btn_validarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_validarMouseEntered
        btn_validar.setBackground(new Color(255,0,0));
    }//GEN-LAST:event_btn_validarMouseEntered

    private void btn_validarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_validarMouseExited
        btn_validar.setBackground(new Color(227,6,19));
    }//GEN-LAST:event_btn_validarMouseExited

    private void btn_salvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salvarMouseClicked
        if(state) {
            char[] pass1 = txt_password.getPassword();
            char[] pass2 = txt_password_repeat.getPassword();
            String senha1 = senhaToString(pass1);
            String senha2 = senhaToString(pass2);

            if(!senha1.equals("") && !senha2.equals("")) {
                if(senha1.equals(senha2)) {
                    this.supervisor.setSenha(senha1);
                    this.supervisor.setCodValidacao(0);
                    SupervisorDao dao = new SupervisorDao(supervisor);
                    dao.editar();
                    dao.closeConnection();
                    dispose();
                    JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "As senhas digitadas não são iguais");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite a nova senha nos dois campos");
            }
        }
        
    }//GEN-LAST:event_btn_salvarMouseClicked

    private void btn_salvarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salvarMouseEntered
        if(state) {
            btn_salvar.setBackground(new Color(255,0,0));
        }
    }//GEN-LAST:event_btn_salvarMouseEntered

    private void btn_salvarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salvarMouseExited
        btn_salvar.setBackground(new Color(227,6,19));
    }//GEN-LAST:event_btn_salvarMouseExited
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmRecuperarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRecuperarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRecuperarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRecuperarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRecuperarSenha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_salvar;
    private javax.swing.JPanel btn_send;
    private javax.swing.JPanel btn_validar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_nova_senha;
    private javax.swing.JLabel lbl_nova_senha_repeat;
    private javax.swing.JLabel lbl_recovery;
    private javax.swing.JLabel lbl_salvar;
    private javax.swing.JLabel lbl_tipo;
    private javax.swing.JPanel painel_1;
    private javax.swing.JPanel painel_2;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JPasswordField txt_password_repeat;
    // End of variables declaration//GEN-END:variables
}
