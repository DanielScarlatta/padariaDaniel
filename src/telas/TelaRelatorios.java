package telas;
//importa toda a biblioteca do BD mas não é recomendo pq prejudica o desempenho

import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaRelatorios extends javax.swing.JFrame {

    public TelaRelatorios() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTipoRelatorio = new javax.swing.JLabel();
        cmbTipoRelatorio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        getContentPane().add(cmbTipoRelatorio);
        cmbTipoRelatorio.setBounds(150, 60, 200, 30);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("nome");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(400, 40, 80, 25);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Categoria");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(400, 80, 90, 30);
        getContentPane().add(txtNome);
        txtNome.setBounds(500, 40, 180, 30);
        getContentPane().add(txtCategoria);
        txtCategoria.setBounds(500, 80, 180, 30);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(530, 130, 120, 40);

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
            DefaultTableModel defTable;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancopadaria", "root", "admin123");
            // Verifica o tipo de relatório no combo box
            if (tipoRelatorio.equalsIgnoreCase("Geral")) {
                st = conexao.prepareStatement("SELECT * FROM cadastrar_produtos");
                resultado = st.executeQuery();
                //verifica se o resultado tem produtos
                defTable = (DefaultTableModel) tblProdutos.getModel();
                defTable.setRowCount(0);
                while (resultado.next()) {
                    //exibir os dados na tabela
                    Object[] linha = {resultado.getString("codigo"), resultado.getString("nome"), resultado.getString("categoria"), resultado.getString("preço")};
                    defTable.addRow(linha);
                }

            } else if (tipoRelatorio.equalsIgnoreCase("Por categoria")) {
                st = conexao.prepareStatement("SELECT * FROM cadastrar_produtos WHERE categoria=?");
                st.setString(1, txtCategoria.getText());
                resultado = st.executeQuery();
                defTable = (DefaultTableModel) tblProdutos.getModel();
                defTable.setRowCount(0);
                while (resultado.next()) {
                    Object[] linha = {resultado.getString("codigo"), resultado.getString("nome"), resultado.getString("categoria"), resultado.getString("preço")};
                    defTable.addRow(linha);
                }

            } else if (tipoRelatorio.equalsIgnoreCase("Por nome")) {
                st = conexao.prepareStatement("SELECT * FROM cadastrar_produtos WHERE nome=?");
                st.setString(1, txtNome.getText());
                resultado = st.executeQuery();
                defTable = (DefaultTableModel) tblProdutos.getModel();
                defTable.setRowCount(0);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTipoRelatorio;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
