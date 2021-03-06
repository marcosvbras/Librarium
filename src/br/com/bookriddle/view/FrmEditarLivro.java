/*
 import javax.imageio.Imag
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.view;

import br.com.bookriddle.controller.LivroDao;
import br.com.bookriddle.interfaces.Verificacao;
import br.com.bookriddle.model.Area;
import br.com.bookriddle.model.Autor;
import br.com.bookriddle.model.Editora;
import br.com.bookriddle.model.Livro;
import br.com.bookriddle.model.Supervisor;
import br.com.bookriddle.utilities.ImageTransfer;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class FrmEditarLivro extends javax.swing.JFrame implements Verificacao {

    /**
     * Creates new form FrmPrincipal
     */
    public static final int ACTION_INSERT = 1;
    public static final int ACTION_UPDATE = 2;
    public final int ACTION_SELECIONAR = 1;
    public final int ACTION_REMOVER = 2;
    public final int ACTION_ALTERACAO = 1500;
    public final String SALVAR = "Salvar", EDITAR = "Editar";
    private int action = ACTION_UPDATE;
    private Livro livro;
    private final String SELECIONAR = "Selecionar Imagem", REMOVER = "Remover Imagem";
    private int state_image = ACTION_SELECIONAR;
    private ImageTransfer it;
    private String destination_path = null;
    private File imagem_selecionada;
    private ImageIcon img;
    private Supervisor sup;
    private String temp_url;
    private int resposta;
    private FrmControleLivro busca;
    private Autor autor;
    private Area area;
    private Editora editora;

    public FrmEditarLivro() {
        initComponents();
    }

    public FrmEditarLivro(Livro livro, Supervisor sup, FrmControleLivro busca) {
        initComponents();
        setLocationRelativeTo(null);
        habilitar(false); // Impede que os campos sejam editados
        this.livro = livro;
        preencherCampos();
        btn_editar.requestFocus();
        this.sup = sup;
        this.busca = busca;
        resposta = ACTION_ALTERACAO; // Define que a imagem não foi trocada
    }

    private void preencherCampos() { // Método usado para preencher os campos de acordo com o livro selecionado na tela de busca
        txt_isbn.setText(livro.getIsbn());
        txt_titulo.setText(livro.getTitulo());
        lbl_autor.setText(livro.getInfo()[0]);
        lbl_area.setText(livro.getInfo()[1]);
        txt_edicao.setText(livro.getEdicao());
        lbl_editora.setText(livro.getInfo()[2]);
        txt_paginas.setText("" + livro.getPaginas());
        txt_quantidade.setText("" + livro.getQuantidade());
        txt_ano.setText("" + livro.getAno());
        txt_armario.setText(livro.getArmario());
        txt_prateleira.setText(livro.getPrateleira());
        temp_url = livro.getUrl();

        try { // Exibindo a imagem salva no banco
            File imagem_selecionada = new File(System.getProperty("user.dir") + "/" + livro.getUrl());
            String caminho = imagem_selecionada.getAbsolutePath(); //Caminho da imagem selecionada
            ImageIcon imagem = new ImageIcon(caminho);
            ImageIcon thumbnail = new ImageIcon(imagem.getImage().getScaledInstance(-12, lbl_img.getHeight(), Image.SCALE_SMOOTH));
            lbl_img.setIcon(thumbnail);

            if (!livro.getUrl().equals("null")) { // Se tiver url de imagem, o botão deve apresentar a opção de remover a imagem
                state_image = ACTION_REMOVER;
                label_btn2.setText(REMOVER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        autor = new Autor();
        autor.setId(livro.getAutorId());
        editora = new Editora();
        editora.setId(livro.getEditoraId());
        area = new Area();
        area.setId(livro.getAreaId());
    }

    @Override
    public boolean validarCampos() {
        boolean resultado = false;

        try {
            int paginas = Integer.parseInt(txt_paginas.getText());
            int quantidade = Integer.parseInt(txt_quantidade.getText());
            int ano = Integer.parseInt(txt_ano.getText());
            int prateleira = Integer.parseInt(txt_prateleira.getText());
            resultado = true;
        } catch (Exception e) {
            resultado = false;
        }

        if (!txt_isbn.getText().equals("") && !txt_titulo.getText().equals("") && autor != null && area != null && !txt_edicao.getText().equals("") 
                && editora != null && !txt_paginas.getText().equals("") && !txt_quantidade.equals("") && !txt_ano.getText().equals("") && resultado) {

            return true;
        } else {
            return false;
        }
    }

    private void habilitar(boolean state) { // Método que habilita ou desabilita a possibilidade de edição dos campos
        txt_isbn.requestFocus();
        txt_isbn.setEditable(state);
        txt_titulo.setEditable(state);
        lbl_autor.setEnabled(state);
        lbl_area.setEnabled(state);
        txt_edicao.setEditable(state);
        lbl_editora.setEnabled(state);
        txt_paginas.setEditable(state);
        txt_quantidade.setEditable(state);
        txt_ano.setEditable(state);
        txt_armario.setEditable(state);
        txt_prateleira.setEditable(state);
        btn_selecionar.setVisible(state);
    }
    
    public void setAutor(Autor autor) {
        this.autor = autor;
        lbl_autor.setText(autor.getNome());
    }
    
    public void setArea(Area area) {
        this.area = area;
        lbl_area.setText(area.getNome());
    }
    
    public void setEditora(Editora editora) {
        this.editora = editora;
        lbl_editora.setText(editora.getNome());
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
        panel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_isbn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_titulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_edicao = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_paginas = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_ano = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_armario = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_prateleira = new javax.swing.JTextField();
        lbl_autor = new javax.swing.JLabel();
        lbl_area = new javax.swing.JLabel();
        lbl_editora = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbl_img = new javax.swing.JLabel();
        btn_selecionar = new javax.swing.JPanel();
        label_btn2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_editar = new javax.swing.JPanel();
        label_btn = new javax.swing.JLabel();
        btn_excluir = new javax.swing.JPanel();
        label_btn1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(237, 250, 251));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("ISBN");

        txt_isbn.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Título");

        txt_titulo.setForeground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Autor");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Área");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Edição");

        txt_edicao.setForeground(new java.awt.Color(51, 51, 51));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Editora");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Páginas");

        txt_paginas.setForeground(new java.awt.Color(51, 51, 51));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Quantidade");

        txt_quantidade.setForeground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Ano");

        txt_ano.setForeground(new java.awt.Color(51, 51, 51));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Armário");

        txt_armario.setForeground(new java.awt.Color(51, 51, 51));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Prateleira");

        txt_prateleira.setForeground(new java.awt.Color(51, 51, 51));

        lbl_autor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_autor.setForeground(new java.awt.Color(51, 51, 51));
        lbl_autor.setText("<Selecione>");
        lbl_autor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_autor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_autorMouseClicked(evt);
            }
        });

        lbl_area.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_area.setForeground(new java.awt.Color(51, 51, 51));
        lbl_area.setText("<Selecione>");
        lbl_area.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_areaMouseClicked(evt);
            }
        });

        lbl_editora.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_editora.setForeground(new java.awt.Color(51, 51, 51));
        lbl_editora.setText("<Selecione>");
        lbl_editora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_editora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_editoraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel11)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_edicao)
                            .addComponent(txt_paginas, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbl_editora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_armario, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_prateleira, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_isbn, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                .addComponent(txt_titulo)
                                .addComponent(lbl_autor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_area, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_autor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_area, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_edicao, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_editora, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_paginas, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_prateleira, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_armario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(465, 326));

        jScrollPane1.setBorder(null);

        lbl_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(lbl_img);

        btn_selecionar.setBackground(new java.awt.Color(227, 6, 19));
        btn_selecionar.setMaximumSize(new java.awt.Dimension(230, 40));
        btn_selecionar.setMinimumSize(new java.awt.Dimension(230, 40));
        btn_selecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_selecionarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_selecionarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_selecionarMouseExited(evt);
            }
        });

        label_btn2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        label_btn2.setForeground(new java.awt.Color(255, 255, 255));
        label_btn2.setText("Selecionar Imagem");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bookriddle/imagens/white_cam_small.png"))); // NOI18N

        javax.swing.GroupLayout btn_selecionarLayout = new javax.swing.GroupLayout(btn_selecionar);
        btn_selecionar.setLayout(btn_selecionarLayout);
        btn_selecionarLayout.setHorizontalGroup(
            btn_selecionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_selecionarLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_btn2)
                .addGap(33, 33, 33))
        );
        btn_selecionarLayout.setVerticalGroup(
            btn_selecionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(btn_selecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_selecionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_editar.setBackground(new java.awt.Color(227, 6, 19));
        btn_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_editarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_editarMouseExited(evt);
            }
        });

        label_btn.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        label_btn.setForeground(new java.awt.Color(255, 255, 255));
        label_btn.setText("Editar");

        javax.swing.GroupLayout btn_editarLayout = new javax.swing.GroupLayout(btn_editar);
        btn_editar.setLayout(btn_editarLayout);
        btn_editarLayout.setHorizontalGroup(
            btn_editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_editarLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(label_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );
        btn_editarLayout.setVerticalGroup(
            btn_editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        btn_excluir.setBackground(new java.awt.Color(227, 6, 19));
        btn_excluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_excluirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_excluirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_excluirMouseExited(evt);
            }
        });

        label_btn1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        label_btn1.setForeground(new java.awt.Color(255, 255, 255));
        label_btn1.setText("Excluir");

        javax.swing.GroupLayout btn_excluirLayout = new javax.swing.GroupLayout(btn_excluir);
        btn_excluir.setLayout(btn_excluirLayout);
        btn_excluirLayout.setHorizontalGroup(
            btn_excluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_excluirLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(label_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48))
        );
        btn_excluirLayout.setVerticalGroup(
            btn_excluirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void btn_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editarMouseClicked
        if (action == ACTION_UPDATE) { // Condição para caso o botão estiver como "Editar" na hora do clique
            label_btn.setText(SALVAR);
            habilitar(true);
            action = ACTION_INSERT; // Botão "Editar" se torna "Salvar"
        } else { // condição para caso o botão estiver como "Salvar" na hora do clique
            label_btn.setText(EDITAR);
            habilitar(false);
            action = ACTION_UPDATE; // Botão "Salvar" se torna "Editar"

            if (validarCampos()) { // Verificando se todos os campos foram preenchidos
                livro.setIsbn(txt_isbn.getText());
                livro.setTitulo(txt_titulo.getText());
                livro.setAutorId(autor.getId());
                livro.setAreaId(area.getId());
                livro.setEdicao(txt_edicao.getText());
                livro.setEditoraId(editora.getId());
                livro.setPaginas(Integer.parseInt(txt_paginas.getText()));
                livro.setArmario(txt_armario.getText());
                livro.setPrateleira(txt_prateleira.getText());
                livro.setAno(Integer.parseInt(txt_ano.getText()));
                livro.setQuantidade(Integer.parseInt(txt_quantidade.getText()));

                if (resposta != ACTION_ALTERACAO) { // Se alguma imagem foi selecionada, a url do livro é alterada
                    livro.setUrl(destination_path);
                }

                LivroDao dao = new LivroDao(livro);
                boolean resultado = dao.editar();

                if (resultado) {
                    JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso.");
                }

                if (!temp_url.equals("null") && resposta != ACTION_ALTERACAO) { // Se havia imagem, a mesma será apagada
                    File file = new File(System.getProperty("user.dir") + "/" + temp_url);
                    file.delete();  // Deletando a imagem antiga do diretório de imagens "Livros"
                    temp_url = null;
                }

                if (destination_path != null) { // A transferência da imagem só será feita caso alguma imagem tenha sido selecionada
                    it.transferirImagem(); // Duplicando e transferindo a imagem para a pasta "Livros" do projeto
                    System.out.println("Duplicando a imagem");
                    temp_url = livro.getUrl();
                }
                resposta = ACTION_ALTERACAO;
                destination_path = null;

                busca.setList(dao.buscarTodos("where status_livro != 0 order by titulo"));
                dao.closeConnection();
            } else {
                JOptionPane.showMessageDialog(null, "Verifique se os campos foram digitados corretamente");
            }
        }
    }//GEN-LAST:event_btn_editarMouseClicked

    private void btn_editarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editarMouseEntered
        btn_editar.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_btn_editarMouseEntered

    private void btn_editarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editarMouseExited
        btn_editar.setBackground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_editarMouseExited

    private void btn_selecionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_selecionarMouseClicked
        if (state_image == ACTION_SELECIONAR) { // Condição caso o botão estiver como "Selecionar Imagem" na hora do clique
            try {
                JFileChooser chooser = new JFileChooser(); // Criando um seletor de imagem
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "jpeg", "png");  //Cria um filtro
                chooser.setFileFilter(filter);  //Altera o filtro do JFileChooser
                resposta = chooser.showOpenDialog(btn_selecionar); // Pegando a resposta do seletor de imagem

                if (resposta == JFileChooser.APPROVE_OPTION) { // Caso retorne com alguma imagem, ela deverá ser mostrada no Frame
                    label_btn2.setText(REMOVER);
                    state_image = ACTION_REMOVER; // o botão "Selecionar Imagem" vira "Remover Imagem"
                    imagem_selecionada = chooser.getSelectedFile(); //Pegando a imagem selecionada
                    img = new ImageIcon(imagem_selecionada.getAbsolutePath()); // Pegando o caminho da imagem selecionada
                    lbl_img.setIcon(img);
                    it = new ImageTransfer(imagem_selecionada, imagem_selecionada.getAbsolutePath());
                    destination_path = (it.getDestinationPath()); // Gerando o diretório destino
                } else {
                    chooser.cancelSelection();
                    destination_path = null;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao selecionar a imagem, tente novamente");
                e.printStackTrace();
                destination_path = null;
            }
        } else { // Condição caso o botão estiver como "Remover Imagem" na hora do clique
            label_btn2.setText(SELECIONAR);
            lbl_img.setIcon(null);
            state_image = ACTION_SELECIONAR; // Botão se "Remover Imagem" se torna "Selecionar Imagem"
            imagem_selecionada = null;
            destination_path = null;
            resposta = 5; // Definindo que houve alteração na imagem
        }
    }//GEN-LAST:event_btn_selecionarMouseClicked

    private void btn_selecionarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_selecionarMouseEntered
        btn_selecionar.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_btn_selecionarMouseEntered

    private void btn_selecionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_selecionarMouseExited
        btn_selecionar.setBackground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_selecionarMouseExited

    private void btn_excluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseClicked
        int result = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este livro?");

        if (result == 0) {
            LivroDao dao = new LivroDao(livro);
            dao.excluir();
            busca.setList(dao.buscarTodos("where status_livro != 0 order by titulo"));
            dao.closeConnection();
        }
    }//GEN-LAST:event_btn_excluirMouseClicked

    private void btn_excluirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseEntered
        btn_excluir.setBackground(new Color(255, 0, 0));
    }//GEN-LAST:event_btn_excluirMouseEntered

    private void btn_excluirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_excluirMouseExited
        btn_excluir.setBackground(new Color(227, 6, 19));
    }//GEN-LAST:event_btn_excluirMouseExited

    private void lbl_autorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_autorMouseClicked
        new FrmSelecionarAutor(this).setVisible(true);
    }//GEN-LAST:event_lbl_autorMouseClicked

    private void lbl_areaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_areaMouseClicked
        new FrmSelecionarArea(this).setVisible(true);
    }//GEN-LAST:event_lbl_areaMouseClicked

    private void lbl_editoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_editoraMouseClicked
        new FrmSelecionarEditora(this).setVisible(true);
    }//GEN-LAST:event_lbl_editoraMouseClicked

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
            java.util.logging.Logger.getLogger(FrmEditarLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEditarLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEditarLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEditarLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEditarLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_editar;
    private javax.swing.JPanel btn_excluir;
    private javax.swing.JPanel btn_selecionar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_btn;
    private javax.swing.JLabel label_btn1;
    private javax.swing.JLabel label_btn2;
    private javax.swing.JLabel lbl_area;
    private javax.swing.JLabel lbl_autor;
    private javax.swing.JLabel lbl_editora;
    private javax.swing.JLabel lbl_img;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField txt_ano;
    private javax.swing.JTextField txt_armario;
    private javax.swing.JTextField txt_edicao;
    private javax.swing.JTextField txt_isbn;
    private javax.swing.JTextField txt_paginas;
    private javax.swing.JTextField txt_prateleira;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JTextField txt_titulo;
    // End of variables declaration//GEN-END:variables
}
