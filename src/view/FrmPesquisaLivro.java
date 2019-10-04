/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DAL;
import model.LivrosDAO;
import model.UsuarioDAO;

/**
 *
 * @author musle
 */

public class FrmPesquisaLivro extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */

    public FrmPesquisaLivro(UsuarioDAO user, String operacao) {

        initComponents();

        setUser(user);
        setOperacao(operacao);
        
        if(getOperacao().equals("Editar") || getOperacao().equals("Excluir"))
            btnOperacao.setText(getOperacao());
        else
            btnOperacao.setText("Visualizar");

        ajustaTamanhoColuna();

        if(getOperacao().equals("") || getOperacao().equals("Editar") || getOperacao().equals("Excluir"))
            atualizaTableLivro("", "isbn");
        else
            atualizaTableLivro("", "reservado");

    }

    private UsuarioDAO user;
    private String operacao;

    public UsuarioDAO getUser() {
        return user;
    }

    public void setUser(UsuarioDAO user) {
        this.user = user;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLivros = new javax.swing.JTable();
        txtPesquisaLivro = new javax.swing.JTextField();
        btnPesquisaLivro = new javax.swing.JButton();
        btnOperacao = new javax.swing.JButton();
        rdISBN = new javax.swing.JRadioButton();
        rdLivro = new javax.swing.JRadioButton();
        rdAutor = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PESQUISA");
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PESQUISA LIVROS");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-search-lg.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Livro", "Autor", "Qtde."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLivros.setMaximumSize(new java.awt.Dimension(300, 160));
        tblLivros.setMinimumSize(new java.awt.Dimension(300, 160));
        jScrollPane1.setViewportView(tblLivros);

        btnPesquisaLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btn-search-es.png"))); // NOI18N
        btnPesquisaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaLivroActionPerformed(evt);
            }
        });

        btnOperacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-view-sm.png"))); // NOI18N
        btnOperacao.setText("Operação");
        btnOperacao.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnOperacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacaoActionPerformed(evt);
            }
        });

        btnGroup.add(rdISBN);
        rdISBN.setSelected(true);
        rdISBN.setText("ISBN");
        rdISBN.setActionCommand("isbn");

        btnGroup.add(rdLivro);
        rdLivro.setText("Livro");
        rdLivro.setActionCommand("nome");

        btnGroup.add(rdAutor);
        rdAutor.setText("Autor");
        rdAutor.setActionCommand("autor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnOperacao)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(rdISBN)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rdLivro)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rdAutor)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPesquisaLivro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPesquisaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtPesquisaLivro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdISBN)
                    .addComponent(rdLivro)
                    .addComponent(rdAutor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOperacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaLivroActionPerformed
        atualizaTableLivro(txtPesquisaLivro.getText(), btnGroup.getSelection().getActionCommand());
    }//GEN-LAST:event_btnPesquisaLivroActionPerformed

    private void btnOperacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacaoActionPerformed
        if(tblLivros.getSelectedRowCount() > 1){
            JOptionPane.showMessageDialog(this, "Selecione apenas um livro de cada vez para visualização", "Aviso", JOptionPane.WARNING_MESSAGE);
        }else if(tblLivros.getSelectedRowCount() < 1){
            JOptionPane.showMessageDialog(this, "Selecione no mínimo um livro para visualização", "Aviso", JOptionPane.WARNING_MESSAGE);
        }else{
            if(getOperacao().equals("")){
                FrmMostraLivro mostraLivro = new FrmMostraLivro(this, rootPaneCheckingEnabled, (LivrosDAO)tblLivros.getValueAt(tblLivros.getSelectedRow(), 0), getUser(), "");
                mostraLivro.setVisible(true);
            }else{
                FrmManterLivro manterLivro = new FrmManterLivro(this, rootPaneCheckingEnabled, (LivrosDAO)tblLivros.getValueAt(tblLivros.getSelectedRow(), 0), getUser(), getOperacao());
                manterLivro.setVisible(true);
            }                   
        }
        atualizaTableLivro("", "isbn");
    }//GEN-LAST:event_btnOperacaoActionPerformed

    private void mensagemErro(){
        JOptionPane.showMessageDialog(this, "Operação não pode ser efetuada", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void ajustaTamanhoColuna(){
        tblLivros.getColumnModel().getColumn(0).setPreferredWidth(250);
        tblLivros.getColumnModel().getColumn(1).setPreferredWidth(225);
        tblLivros.getColumnModel().getColumn(2).setPreferredWidth(225);
    }

    private void atualizaTableLivro(String pesquisa, String tipo){        
        try {
            ((javax.swing.table.DefaultTableModel) tblLivros.getModel()).setRowCount(0);
            ArrayList<LivrosDAO> lstLivros = DAL.getInstancia().retornaLivro(pesquisa, tipo);
            Integer row = 0;
            if(lstLivros.size() > 0){
                ((javax.swing.table.DefaultTableModel) tblLivros.getModel()).setRowCount(lstLivros.size());
                for (LivrosDAO livro : lstLivros) {
                    tblLivros.getModel().setValueAt(livro, row, 0);
                    tblLivros.getModel().setValueAt(livro.getAutor(), row, 1);
                    tblLivros.getModel().setValueAt(livro.getDisponiveis(), row, 2);
                    row++;
                }
            }else{
                ((javax.swing.table.DefaultTableModel) tblLivros.getModel()).setRowCount(1);
                tblLivros.getModel().setValueAt("Nenhuma linha encontrada", row, 0);    
            }
        } catch (ClassNotFoundException | SQLException ex) {
            mensagemErro();
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

            java.util.logging.Logger.getLogger(FrmPesquisaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {

            java.util.logging.Logger.getLogger(FrmPesquisaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {

            java.util.logging.Logger.getLogger(FrmPesquisaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

            java.util.logging.Logger.getLogger(FrmPesquisaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

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

                new FrmPesquisaLivro(new UsuarioDAO(), new String()).setVisible(true);

            }

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JButton btnOperacao;
    private javax.swing.JButton btnPesquisaLivro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdAutor;
    private javax.swing.JRadioButton rdISBN;
    private javax.swing.JRadioButton rdLivro;
    private javax.swing.JTable tblLivros;
    private javax.swing.JTextField txtPesquisaLivro;
    // End of variables declaration//GEN-END:variables

}
