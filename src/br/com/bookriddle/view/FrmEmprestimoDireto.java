/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.view;

import br.com.bookriddle.controller.EmprestimoDao;
import br.com.bookriddle.controller.LivroDao;
import br.com.bookriddle.controller.ReservaDao;
import br.com.bookriddle.model.Emprestimo;
import br.com.bookriddle.model.Funcionario;
import br.com.bookriddle.model.Livro;
import br.com.bookriddle.model.Supervisor;
import br.com.bookriddle.utilities.DateConvert;
import java.awt.Color;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class FrmEmprestimoDireto extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    private Supervisor supervisor;
    private Date data_emprestimo, data_devolucao;
    private Livro livro;
    private Emprestimo emprestimo;
    private Funcionario funcionario;
    private FrmControleEmprestimo frameLista;

    public FrmEmprestimoDireto() {
        initComponents();
    }

    // Construtor para fazer um empréstimo direto à partir da opção "Realizar Empréstimo" da tela de listagem de livros
    public FrmEmprestimoDireto(Livro livro, Supervisor supervisor) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        this.livro = livro;
        this.supervisor = supervisor;
        preencherCampos1();
        btn_devolver.setVisible(false);

        calendar_devolucao.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                data_devolucao = calendar_devolucao.getDate();
                lbl_data_devolucao.setText(DateConvert.convertToDayMonthYear(data_devolucao));
            }
        });

        calendar_emprestimo.setVisible(false);
    }

    // Contrutor da edição ou exclusão de empréstimo chamado pela opção "Detalhes do Empréstimo" na tela de listagem de empréstimos
    public FrmEmprestimoDireto(Supervisor supervisor, Emprestimo emprestimo, FrmControleEmprestimo frameLista) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        this.emprestimo = emprestimo;
        this.supervisor = supervisor;
        this.frameLista = frameLista;
        preencherCampos2();

        calendar_devolucao.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                data_devolucao = calendar_devolucao.getDate();
                lbl_data_devolucao.setText(DateConvert.convertToDayMonthYear(data_devolucao));
            }
        });

        calendar_emprestimo.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                data_emprestimo = calendar_devolucao.getDate();
                lbl_data_emprestimo.setText(DateConvert.convertToDayMonthYear(data_emprestimo));
            }
        });

        lbl_titulo.setText("Detalhes do empréstimo");
        lbl_concluir.setText("Renovar");
    }

    // Método para preencher campos de acordo com o primeiro construtor
    private void preencherCampos1() {
        ReservaDao rDao = new ReservaDao();

        try { // Exibindo a imagem salva no banco
            File imagem_selecionada = new File(System.getProperty("user.dir") + "/" + livro.getUrl());
            String caminho = imagem_selecionada.getAbsolutePath(); //Caminho da imagem selecionada
            ImageIcon imagem = new ImageIcon(caminho);
            ImageIcon thumbnail = new ImageIcon(imagem.getImage().getScaledInstance(-12, lbl_img.getHeight(), Image.SCALE_SMOOTH));
            lbl_img.setIcon(thumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lbl_livro.setText(livro.getTitulo());
        lbl_supervisor.setText(supervisor.getNome());
        data_emprestimo = new Date();
        lbl_data_emprestimo.setText(DateConvert.convertToDayMonthYear(data_emprestimo));
    }

    // Método para preencher campos de acordo com o segundo construtor
    private void preencherCampos2() {
        ReservaDao rDao = new ReservaDao();

        try { // Exibindo a imagem salva no banco
            File imagem_selecionada = new File(System.getProperty("user.dir") + "/" + emprestimo.getInfo()[2]);
            String caminho = imagem_selecionada.getAbsolutePath(); //Caminho da imagem selecionada
            ImageIcon imagem = new ImageIcon(caminho);
            ImageIcon thumbnail = new ImageIcon(imagem.getImage().getScaledInstance(-12, lbl_img.getHeight(), Image.SCALE_SMOOTH));
            lbl_img.setIcon(thumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        funcionario = new Funcionario();
        funcionario.setMatricula(emprestimo.getMatriculaFuncionario());
        data_emprestimo = emprestimo.getDataEmprestimo();
        data_devolucao = emprestimo.getDataDevolucao();
        lbl_livro.setText(emprestimo.getInfo()[1]);
        lbl_supervisor.setText(supervisor.getNome());
        lbl_funcionario.setText(emprestimo.getInfo()[0]);
        lbl_data_emprestimo.setText(DateConvert.convertToDayMonthYear(data_emprestimo));
        lbl_data_devolucao.setText(DateConvert.convertToDayMonthYear(data_devolucao));
    }

    // Método que pega o funcionário selecionado na tela de seleção de funcionários
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        lbl_funcionario.setText(funcionario.getNome());
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
        lbl_titulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_voltar = new javax.swing.JPanel();
        lbl_voltar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_concluir = new javax.swing.JPanel();
        lbl_concluir = new javax.swing.JLabel();
        btn_devolver = new javax.swing.JPanel();
        lbl_devolver = new javax.swing.JLabel();
        lbl_img = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbl_livro = new javax.swing.JLabel();
        lbl_funcionario = new javax.swing.JLabel();
        lbl_supervisor = new javax.swing.JLabel();
        lbl_data_emprestimo = new javax.swing.JLabel();
        calendar_emprestimo = new com.toedter.calendar.JDateChooser();
        lbl_data_devolucao = new javax.swing.JLabel();
        calendar_devolucao = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Book Riddle - Conclusão de Empréstimo");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(237, 250, 251));

        lbl_titulo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(51, 51, 51));
        lbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titulo.setText("Conclusão de Empréstimo");

        jPanel2.setBackground(new java.awt.Color(227, 6, 19));
        jPanel2.setPreferredSize(new java.awt.Dimension(418, 562));

        btn_voltar.setBackground(new java.awt.Color(227, 6, 19));
        btn_voltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_voltarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_voltarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_voltarMouseExited(evt);
            }
        });

        lbl_voltar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_voltar.setForeground(new java.awt.Color(255, 255, 255));
        lbl_voltar.setText("Voltar");

        javax.swing.GroupLayout btn_voltarLayout = new javax.swing.GroupLayout(btn_voltar);
        btn_voltar.setLayout(btn_voltarLayout);
        btn_voltarLayout.setHorizontalGroup(
            btn_voltarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_voltarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_voltar)
                .addGap(174, 174, 174))
        );
        btn_voltarLayout.setVerticalGroup(
            btn_voltarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_voltar, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/logo_senai.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(227, 6, 19));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/white_brlogo_small.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Riddle Project 2015");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("SENAI Belo Horizonte CETEL César Rodrigues");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(123, 123, 123))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        btn_concluir.setBackground(new java.awt.Color(227, 6, 19));
        btn_concluir.setMaximumSize(new java.awt.Dimension(196, 54));
        btn_concluir.setMinimumSize(new java.awt.Dimension(196, 54));
        btn_concluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_concluirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_concluirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_concluirMouseExited(evt);
            }
        });

        lbl_concluir.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_concluir.setForeground(new java.awt.Color(255, 255, 255));
        lbl_concluir.setText("Concluir");

        javax.swing.GroupLayout btn_concluirLayout = new javax.swing.GroupLayout(btn_concluir);
        btn_concluir.setLayout(btn_concluirLayout);
        btn_concluirLayout.setHorizontalGroup(
            btn_concluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_concluirLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(lbl_concluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_concluirLayout.setVerticalGroup(
            btn_concluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_concluir, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        btn_devolver.setBackground(new java.awt.Color(227, 6, 19));
        btn_devolver.setMaximumSize(new java.awt.Dimension(196, 54));
        btn_devolver.setMinimumSize(new java.awt.Dimension(196, 54));
        btn_devolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_devolverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_devolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_devolverMouseExited(evt);
            }
        });

        lbl_devolver.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbl_devolver.setForeground(new java.awt.Color(255, 255, 255));
        lbl_devolver.setText("Devolver Livro");

        javax.swing.GroupLayout btn_devolverLayout = new javax.swing.GroupLayout(btn_devolver);
        btn_devolver.setLayout(btn_devolverLayout);
        btn_devolverLayout.setHorizontalGroup(
            btn_devolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_devolverLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(lbl_devolver)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_devolverLayout.setVerticalGroup(
            btn_devolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_devolver, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addComponent(btn_concluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_devolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addGap(94, 94, 94)
                .addComponent(btn_devolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_concluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(btn_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbl_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel4.setBackground(new java.awt.Color(237, 250, 251));

        lbl_livro.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lbl_livro.setForeground(new java.awt.Color(51, 51, 51));
        lbl_livro.setText("Livro");

        lbl_funcionario.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lbl_funcionario.setForeground(new java.awt.Color(51, 51, 51));
        lbl_funcionario.setText("<Clique para selecionar um funcionário>");
        lbl_funcionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_funcionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_funcionarioMouseClicked(evt);
            }
        });

        lbl_supervisor.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lbl_supervisor.setForeground(new java.awt.Color(51, 51, 51));
        lbl_supervisor.setText("Supervisor");

        lbl_data_emprestimo.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lbl_data_emprestimo.setForeground(new java.awt.Color(51, 51, 51));
        lbl_data_emprestimo.setText("Data");

        calendar_emprestimo.setBackground(new java.awt.Color(237, 250, 251));

        lbl_data_devolucao.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lbl_data_devolucao.setForeground(new java.awt.Color(51, 51, 51));
        lbl_data_devolucao.setText("Selecione");

        calendar_devolucao.setBackground(new java.awt.Color(237, 250, 251));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(227, 6, 19));
        jLabel6.setText("Data de Devolução");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_supervisor)
                    .addComponent(lbl_livro)
                    .addComponent(lbl_funcionario)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_data_emprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendar_emprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_data_devolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendar_devolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbl_livro)
                .addGap(18, 18, 18)
                .addComponent(lbl_funcionario)
                .addGap(18, 18, 18)
                .addComponent(lbl_supervisor)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(calendar_emprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_data_emprestimo))
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(calendar_devolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_data_devolucao)))
                .addGap(0, 83, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(237, 250, 251));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(227, 6, 19));
        jLabel1.setText("Livro");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(227, 6, 19));
        jLabel3.setText("Funcionário");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(227, 6, 19));
        jLabel2.setText("Supervisor");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(227, 6, 19));
        jLabel5.setText("Data de Empréstimo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 103, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(0, 83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(lbl_img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbl_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_img, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
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

    private void btn_concluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_concluirMouseClicked
        if (data_devolucao != null && funcionario.getMatricula() != null) {
            
            if(frameLista == null) { // Nesse caso foi chamado o primeiro contrutor, ao qual apenas pode ter a opção de realizar empréstimo
                // Concluindo o empréstimo
                Emprestimo emp = new Emprestimo();
                emp.setIdLivro(livro.getId());
                emp.setMatriculaFuncionario(funcionario.getMatricula());
                emp.setMatriculaSupervisor(supervisor.getMatricula());
                emp.setStatusEmprestimo(1);
                emp.setStatusDevolucao(1);
                emp.setDataEmprestimo(data_emprestimo);
                emp.setDataDevolucao(data_devolucao);
                EmprestimoDao eDao = new EmprestimoDao(emp);
                boolean resultado = eDao.inserir();
                
                if(resultado) {
                    JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso.");
                }
                eDao.closeConnection();

                // Alterando a quantidade de unidades do livro
                livro.setQuantidade(livro.getQuantidade() - 1);
                LivroDao lDao = new LivroDao(livro);
                lDao.editar();
                lDao.closeConnection();
                btn_concluir.setVisible(false);
            } else { // Se não, foi chamado o segundo construtor, ao qual permite edição
                ReservaDao rDao = new ReservaDao();
                int count = rDao.countRegisters("where livro_id = " + emprestimo.getIdLivro());
                
                emprestimo.setMatriculaFuncionario(funcionario.getMatricula());
                emprestimo.setMatriculaSupervisor(supervisor.getMatricula());
                emprestimo.setDataEmprestimo(data_emprestimo);
                emprestimo.setDataDevolucao(data_devolucao);
                EmprestimoDao eDao = new EmprestimoDao(emprestimo);
                boolean resultado = eDao.editar();
                
                if(resultado) {
                    JOptionPane.showMessageDialog(null, "Empréstimo renovado com sucesso");
                }
                
                frameLista.setList(eDao.buscarTodos("where (status_emprestimo != 0) and (status_devolucao != 0) order by data_emprestimo"));
                eDao.closeConnection();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, verifique se todos as informações foram preenchidas");
        }
    }//GEN-LAST:event_btn_concluirMouseClicked

    private void btn_concluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_concluirMouseEntered
        btn_concluir.setBackground(new Color(237, 250, 251));
        lbl_concluir.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_concluirMouseEntered

    private void btn_concluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_concluirMouseExited
        btn_concluir.setBackground(new Color(227, 6, 19));
        lbl_concluir.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_concluirMouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void btn_voltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_voltarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btn_voltarMouseClicked

    private void btn_voltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_voltarMouseEntered
        btn_voltar.setBackground(new Color(237, 250, 251));
        lbl_voltar.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_voltarMouseEntered

    private void btn_voltarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_voltarMouseExited
        btn_voltar.setBackground(new Color(227, 6, 19));
        lbl_voltar.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_voltarMouseExited

    private void lbl_funcionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_funcionarioMouseClicked
        new FrmSelecionarFuncionario(this).setVisible(true);
    }//GEN-LAST:event_lbl_funcionarioMouseClicked

    private void btn_devolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_devolverMouseClicked
        LivroDao lDao = new LivroDao();
        Livro livro = (Livro) lDao.buscar("where id = " + emprestimo.getIdLivro());
        
        int result = JOptionPane.showConfirmDialog(null, "Deseja devolver o livro '" + livro.getTitulo() + "'?");
        
        if(result == 0) {
            livro.setQuantidade(livro.getQuantidade() + 1);
            lDao.setLivro(livro);
            lDao.editar();
            lDao.closeConnection();

            emprestimo.setStatusDevolucao(0);
            EmprestimoDao eDao = new EmprestimoDao(emprestimo);
            boolean resultado = eDao.editar();
            if(resultado) {
                JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso.");
            }
            frameLista.setList(eDao.buscarTodos("where (status_emprestimo != 0) and (status_devolucao != 0) order by data_emprestimo"));
            eDao.closeConnection();
            btn_devolver.setVisible(false);
            btn_concluir.setVisible(false);
        }
    }//GEN-LAST:event_btn_devolverMouseClicked

    private void btn_devolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_devolverMouseEntered
        btn_devolver.setBackground(new Color(237, 250, 251));
        lbl_devolver.setForeground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_devolverMouseEntered

    private void btn_devolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_devolverMouseExited
        btn_devolver.setBackground(new Color(227, 6, 19));
        lbl_devolver.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_devolverMouseExited

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
            java.util.logging.Logger.getLogger(FrmEmprestimoDireto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEmprestimoDireto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEmprestimoDireto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEmprestimoDireto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEmprestimoDireto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_concluir;
    private javax.swing.JPanel btn_devolver;
    private javax.swing.JPanel btn_voltar;
    private com.toedter.calendar.JDateChooser calendar_devolucao;
    private com.toedter.calendar.JDateChooser calendar_emprestimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbl_concluir;
    private javax.swing.JLabel lbl_data_devolucao;
    private javax.swing.JLabel lbl_data_emprestimo;
    private javax.swing.JLabel lbl_devolver;
    private javax.swing.JLabel lbl_funcionario;
    private javax.swing.JLabel lbl_img;
    private javax.swing.JLabel lbl_livro;
    private javax.swing.JLabel lbl_supervisor;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_voltar;
    // End of variables declaration//GEN-END:variables
}
