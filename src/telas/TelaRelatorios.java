package telas;
//importa toda a biblioteca do BD mas não é recomendo pq prejudica o desempenho

import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaRelatorios extends javax.swing.JFrame {
    DefaultTableModel defTable;

    public TelaRelatorios() {
        initComponents();
        txtNome.setVisible(false);
        lblNome.setVisible(false);
        txtCategoria.setVisible(false);
        lblCategoria.setVisible(false);
        defTable = (DefaultTableModel) tblProdutos.getModel();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTipoRelatorio = new javax.swing.JLabel();
        cmbTipoRelatorio = new javax.swing.JComboBox<>();
        lblNome = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCategoria = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblTipoRelatorio.setText("TIpos Relatorios");
        getContentPane().add(lblTipoRelatorio);
        lblTipoRelatorio.setBounds(30, 50, 100, 40);

        cmbTipoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Geral", "Por categoria", "Por Nome" }));
        cmbTipoRelatorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoRelatorioItemStateChanged(evt);
            }
        });
        cmbTipoRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoRelatorioActionPerformed(evt);
            }
        });
        cmbTipoRelatorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoRelatorioKeyPressed(evt);
            }
        });
        getContentPane().add(cmbTipoRelatorio);
        cmbTipoRelatorio.setBounds(150, 60, 200, 30);

        lblNome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNome.setText("Nome");
        getContentPane().add(lblNome);
        lblNome.setBounds(400, 35, 80, 40);

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblCategoria.setText("Categoria");
        getContentPane().add(lblCategoria);
        lblCategoria.setBounds(400, 40, 90, 30);

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });
        getContentPane().add(txtNome);
        txtNome.setBounds(500, 40, 180, 30);

        txtCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCategoriaKeyPressed(evt);
            }
        });
        getContentPane().add(txtCategoria);
        txtCategoria.setBounds(500, 40, 180, 30);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        btnBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarKeyPressed(evt);
            }
        });
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(530, 100, 120, 40);

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Categoria", "Preço"
            }
        ));
        jScrollPane1.setViewportView(tblProdutos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 220, 700, 240);

        setSize(new java.awt.Dimension(787, 572));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //Cria uma Variavel do tipo string e pega a infomação do combo box
        String tipoRelatorio;
        tipoRelatorio = cmbTipoRelatorio.getSelectedItem().toString();
        //Inicia a comunicação com BD
        try {
            Connection conexao;
            PreparedStatement st;
            ResultSet resultado;
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancopadaria", "root", "admin123");
            // Verifica o tipo de relatório no combo box
            if (tipoRelatorio.equalsIgnoreCase("Geral")) {
                st = conexao.prepareStatement("SELECT * FROM cadastrar_produtos");
                resultado = st.executeQuery();
                //verifica se o resultado tem produtos
                while (resultado.next()) {
                    //exibir os dados na tabela
                    Object[] linha = {resultado.getString("codigo"), resultado.getString("nome"), resultado.getString("categoria"), resultado.getString("preço")};
                    defTable.addRow(linha);
                }

            } else if (tipoRelatorio.equalsIgnoreCase("Por categoria")) {
                st = conexao.prepareStatement("SELECT * FROM cadastrar_produtos WHERE categoria LIKE?");
                st.setString(1, "%" + txtCategoria.getText() + "%");
                resultado = st.executeQuery();
                while (resultado.next()) {
                    Object[] linha = {resultado.getString("codigo"), resultado.getString("nome"), resultado.getString("categoria"), resultado.getString("preço")};
                    defTable.addRow(linha);
                }

            } else if (tipoRelatorio.equalsIgnoreCase("Por nome")) {
                st = conexao.prepareStatement("SELECT * FROM cadastrar_produtos WHERE nomeLIKE?");
                st.setString(1, "%" + txtNome.getText() + "%");
                resultado = st.executeQuery();
                while (resultado.next()) {
                    Object[] linha = {resultado.getString("codigo"), resultado.getString("nome"), resultado.getString("categoria"), resultado.getString("preço")};
                    defTable.addRow(linha);
                }

            }
            //Verifica os erros possiveis no progama
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Você não tem o driver na biblioteca " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Algum parâmetro do BD está incorreto" + ex.getMessage());
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cmbTipoRelatorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoRelatorioItemStateChanged
        String tipo;
        tipo = cmbTipoRelatorio.getSelectedItem().toString();
        if (tipo.equalsIgnoreCase("Geral")) {
            txtNome.setVisible(false);
            lblNome.setVisible(false);
            txtCategoria.setVisible(false);
            lblCategoria.setVisible(false);
            defTable.setRowCount(0);

        } else if (tipo.equalsIgnoreCase("Por categoria")) {
            txtNome.setVisible(false);
            lblNome.setVisible(false);
            txtCategoria.setVisible(true);
            lblCategoria.setVisible(true);
            defTable.setRowCount(0);
        } else if (tipo.equalsIgnoreCase("Por nome")) {
            txtCategoria.setVisible(false);
            lblCategoria.setVisible(false);
            txtNome.setVisible(true);
            lblNome.setVisible(true);
            defTable.setRowCount(0);
        }


    }//GEN-LAST:event_cmbTipoRelatorioItemStateChanged

    private void cmbTipoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoRelatorioActionPerformed

    private void btnBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarKeyPressed

    }//GEN-LAST:event_btnBuscarKeyPressed

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnBuscar.doClick(); //Método que tienes que crearte
        }
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnBuscar.doClick(); //Método que tienes que crearte
        }
    }//GEN-LAST:event_txtCategoriaKeyPressed

    private void cmbTipoRelatorioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoRelatorioKeyPressed
        String tipo;
        tipo = cmbTipoRelatorio.getSelectedItem().toString();
        if (tipo.equalsIgnoreCase("Geral")) {
            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                btnBuscar.doClick(); //Método que tienes que crearte
            }
        }
    }//GEN-LAST:event_cmbTipoRelatorioKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRelatorios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cmbTipoRelatorio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTipoRelatorio;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
