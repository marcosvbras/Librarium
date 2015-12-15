/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.view;

import br.com.bookriddle.controller.LivroDao;
import br.com.bookriddle.controller.ReservaDao;
import br.com.bookriddle.interfaces.Verificacao;
import br.com.bookriddle.model.Livro;
import br.com.bookriddle.model.Reserva;
import java.awt.Color;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno Manha
 */
public class FrmReserva extends javax.swing.JFrame implements Verificacao {

    /**
     * Creates new form FrmReserva1
     */
    private Livro l;
    private FrmControleLivro busca;

    public FrmReserva() {
        initComponents();
    }

    public FrmReserva(Livro l, FrmControleLivro busca) {
        initComponents();
        setLocationRelativeTo(null);
        this.l = l;
        label_livro.setText(l.getTitulo());
        label_autor.setText(l.getInfo()[0]);
        this.busca = busca;
    }

    @Override
    public boolean validarCampos() {
        if (!txt_matricula.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group_tipo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        label_livro = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_matricula = new javax.swing.JTextField();
        btn_reservar = new javax.swing.JPanel();
        label_btn3 = new javax.swing.JLabel();
        label_autor = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(237, 250, 251));

        label_livro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label_livro.setForeground(new java.awt.Color(51, 51, 51));
        label_livro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_livro.setText("Nome do Livro");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Matrícula");

        txt_matricula.setForeground(new java.awt.Color(51, 51, 51));

        btn_reservar.setBackground(new java.awt.Color(227, 6, 19));
        btn_reservar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_reservarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reservarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reservarMouseExited(evt);
            }
        });

        label_btn3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        label_btn3.setForeground(new java.awt.Color(255, 255, 255));
        label_btn3.setText("Reservar");

        javax.swing.GroupLayout btn_reservarLayout = new javax.swing.GroupLayout(btn_reservar);
        btn_reservar.setLayout(btn_reservarLayout);
        btn_reservarLayout.setHorizontalGroup(
            btn_reservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_reservarLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(label_btn3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_reservarLayout.setVerticalGroup(
            btn_reservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_btn3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        label_autor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_autor.setForeground(new java.awt.Color(51, 51, 51));
        label_autor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_autor.setText("Nome do Autor");

        jPanel2.setBackground(new java.awt.Color(227, 6, 19));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/white_logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Conclusão de Reserva");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_autor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label_livro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel10)))
                .addContainerGap(170, Short.MAX_VALUE))
            .addComponent(btn_reservar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(34, 34, 34)
                .addComponent(label_livro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_autor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btn_reservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_reservarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reservarMouseClicked
        if (validarCampos()) {
            boolean resultado = false;
            Reserva res = new Reserva();
            res.setIdLivro(l.getId());
            Date date = new Date();
            res.setData(date);
            res.setMatriculaFuncionario(txt_matricula.getText());
            res.setStatus(1);
            ReservaDao rdao = new ReservaDao(res);
            LivroDao ldao = null;

            if (!isAvailable()) {
                int result = JOptionPane.showConfirmDialog(null, "Os exemplares deste livro se encontram-se indisponíveis.\nDeseja entrar na lista de espera?");

                if (result == 0) {
                    resultado = rdao.inserir();

                    if (resultado) {
                        JOptionPane.showMessageDialog(null, "Reserva efetuada com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Informe uma matrícula válida.");
                    }
                }
            } else {
                resultado = rdao.inserir();

                if (resultado) {
                    if (l.getQuantidade() > 0) {
                        l.setQuantidade(l.getQuantidade() - 1);
                        ldao = new LivroDao(l);
                        ldao.editar();
                    }
                    JOptionPane.showMessageDialog(null, "Reserva efetuada com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Informe uma matrícula válida.");
                }
            }

            busca.setList(ldao.buscarTodos("where status_livro != 0 order by titulo"));

            rdao.closeConnection();
            ldao.closeConnection();
        } else {
            JOptionPane.showMessageDialog(null, "Digite a matrícula");
        }
    }//GEN-LAST:event_btn_reservarMouseClicked

    private void btn_reservarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reservarMouseEntered
        btn_reservar.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_btn_reservarMouseEntered

    private void btn_reservarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reservarMouseExited
        btn_reservar.setBackground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_reservarMouseExited

    private boolean isAvailable() {
        if (l.getQuantidade() > 0) {
            return true;
        } else {
            return false;
        }
    }

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
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_reservar;
    private javax.swing.ButtonGroup group_tipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_autor;
    private javax.swing.JLabel label_btn3;
    private javax.swing.JLabel label_livro;
    private javax.swing.JTextField txt_matricula;
    // End of variables declaration//GEN-END:variables
}
