/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.DAL;
import model.LivrosDAO;
import model.MultasDAO;
import model.UsuarioDAO;

/**
 *
 * @author musle
 */
public class FrmMostraLivro extends javax.swing.JDialog {

    /**
     * Creates new form FrmCadUser
     */
    public FrmMostraLivro(java.awt.Frame parent, boolean modal, LivrosDAO livro, UsuarioDAO user, String operacao) {
        
        super(parent, modal);
        
        initComponents();
        
        this.setResizable(false);
        
        rootPane.setDefaultButton(btn1);
        
        setLivro(livro);
        setUser(user);
        setOperacao(operacao);
        
        preencheCampos();
        
        if(getOperacao().equals("Empréstimos"))
            operacaoEmprestimo();
        else if(getOperacao().equals("Reservas"))
            operacaoReserva();
        
    }
    
    private LivrosDAO livro;
    private UsuarioDAO user;
    private String operacao;

    public LivrosDAO getLivro() {
        return livro;
    }

    public void setLivro(LivrosDAO livro) {
        this.livro = livro;
    }

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

    public JComboBox getCbUser() {
        return cbUser;
    }

    public void setCbUser(JComboBox cbUser) {
        this.cbUser = cbUser;
    }
    
    private void apagaData(){
        lblData.setVisible(false);
        txtData.setVisible(false);
    }
    
    private void operacaoReserva(){
        btn2.setVisible(false);
        apagaData();
    }
    
    private void operacaoEmprestimo(){
        btn1.setText("Devolver");
        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-return-sm.png")));            
        btn2.setText("Renovar");
        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-again-sm.png")));
        apagaData();
    }
    
    private void preencheCampos(){
        txtNome.setText(getLivro().getNome());
        txtAutor.setText(getLivro().getAutor());
        txtEditora.setText(getLivro().getEditora());
        txtISBN.setText(getLivro().getIsbn());
        txtDataPub.setText(Integer.toString(getLivro().getAno()));
        txtEdicao.setText(Integer.toString(getLivro().getEdicao()));
        atualizaComboUsuarios();
    }
    
    private void atualizaComboUsuarios(){
        try {
            ArrayList<UsuarioDAO> lstUser;
            if(!getOperacao().equals("Empréstimos") && !getOperacao().equals("Reservas")){
                String pesquisa = getUser().getTipo().equals("Aluno") ? Integer.toString(getUser().getId()) : "";
                String tipo = pesquisa.equals("") ? "" : "id";
                lstUser = DAL.getInstancia().retornaUser(pesquisa, tipo);
            }else{
                lstUser = DAL.getInstancia().retornaUser(Integer.toString(getUser().getId()), "id");
            }
            for(UsuarioDAO user : lstUser)
                cbUser.addItem(user);
        } catch (ClassNotFoundException | SQLException ex) {
            mensagemErro();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAutor = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtISBN = new javax.swing.JTextField();
        txtDataPub = new javax.swing.JTextField();
        txtEditora = new javax.swing.JTextField();
        txtEdicao = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        lblData = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##/##/####");
            txtData = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }
        lblUser = new javax.swing.JLabel();
        cbUser = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LIVRO");

        jLabel1.setText("Nome");

        jLabel2.setText("Autor");

        jLabel5.setText("ISBN");

        jLabel7.setText("Ano Publicação");

        jLabel8.setText("Editora");

        jLabel9.setText("Edição");

        txtAutor.setEditable(false);

        txtNome.setEditable(false);

        txtISBN.setEditable(false);

        txtDataPub.setEditable(false);
        txtDataPub.setAutoscrolls(false);

        txtEditora.setEditable(false);

        txtEdicao.setEditable(false);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("LIVRO");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-view-lg.png"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-love-sm.png"))); // NOI18N
        btn2.setText("Reservar");
        btn2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/book-withdraw-sm.png"))); // NOI18N
        btn1.setText("Retirar");
        btn1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        lblData.setText("Data da Operação");

        lblUser.setText("Usuário");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8))
                                        .addGap(59, 59, 59))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAutor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                                    .addComponent(txtISBN, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDataPub, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEditora)
                                    .addComponent(txtNome)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(85, 85, 85)
                                .addComponent(txtEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtData)
                                .addComponent(cbUser, 0, 437, Short.MAX_VALUE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataPub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn2)
                    .addComponent(btn1))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if(getLivro().getDisponiveis() > 0){
            if(btn1.getText().equals("Retirar")){
                try {
                    if(!verificaLivroRetirado() && !verificaMultas() && (!verificaLivroReservado() || (getLivro().getDisponiveis() > 0)) && !verificaMaximoLivros(0) && !dataEmBranco()){
                        try {
                            DAL.getInstancia().retiraLivro(getLivro(), (UsuarioDAO)getCbUser().getSelectedItem(), txtData.getText());
                            mensagemSucesso("Retirada");
                        } catch (ClassNotFoundException | SQLException | ParseException ex) {
                            mensagemErro();
                        }
                        this.dispose();
                    }else if(verificaLivroRetirado()){
                        mensagemJaRetirado();
                    }else if(verificaMultas()){
                        mensagemMultas();
                    }else if(verificaLivroReservado() && (getLivro().getDisponiveis() < 1)){
                        mensagemReservado();
                    }else if(verificaMaximoLivros(0)){
                        mensagemMaximo();
                    }else if(dataEmBranco()){
                        mensagemDataEmBranco();
                    }else{
                        mensagemErro();
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(FrmMostraLivro.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
            }else if(btn1.getText().equals("Devolver")){
                try {
                    DAL.getInstancia().devolveLivro(getLivro(), (UsuarioDAO)getCbUser().getSelectedItem());
                    mensagemSucesso("Devolução");
                    this.dispose();
                } catch (ClassNotFoundException | SQLException | ParseException ex) {
                    mensagemErro();
                }
            }
        }else if(getLivro().getReservado()){
            mensagemIndisponivel();
        }else{
            mensagemApenasReserva();
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        try {
            if(btn2.getText().equals("Reservar")){
                if(!verificaLivroReservado()){
                    DAL.getInstancia().reservaLivro(getLivro(), (UsuarioDAO)getCbUser().getSelectedItem());
                    mensagemSucesso("Reserva");
                }else if(verificaLivroReservado()){
                    mensagemReservado();
                }else{
                    mensagemErro();
                }
                this.dispose();
            }else if(btn2.getText().equals("Renovar")){
                if((!verificaLivroReservado() || (getLivro().getDisponiveis() > 0)) && !verificaMultas() && !verificaMaximoLivros(1) && !dataEmBranco()){
                    DAL.getInstancia().renovaLivro(getLivro(), (UsuarioDAO)getCbUser().getSelectedItem(), txtData.getText());
                }else if(verificaMultas()){
                    mensagemMultas();
                }else if(verificaLivroReservado() && (getLivro().getDisponiveis() < 1)){
                    mensagemReservado();
                }else if(verificaMaximoLivros(1)){
                    mensagemMaximo();
                }else if(dataEmBranco()){
                    mensagemDataEmBranco();
                }else{
                    mensagemErro();
                }
                this.dispose();
            }
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            mensagemErro();
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private Boolean verificaLivroRetirado(){
        for(LivrosDAO livro : ((UsuarioDAO)getCbUser().getSelectedItem()).getLstLivros()){
            if(getLivro().getId() == livro.getId())
                return true;
        }
        return false;
    }
    
    private Boolean verificaLivroReservado(){
        return getLivro().getReservado();
    }
    
    private Boolean verificaMultas() throws ClassNotFoundException, SQLException{
        ArrayList<MultasDAO> multas = new ArrayList<>();
        multas = DAL.getInstancia().getMultas((UsuarioDAO)getCbUser().getSelectedItem());
        if(multas.size() > 0)
            return true;
        else
            return false;
    }
    
    private Boolean verificaMaximoLivros(Integer retirado){
        if(((UsuarioDAO)getCbUser().getSelectedItem()).getLstLivros().size() > 0)
            return ((UsuarioDAO)getCbUser().getSelectedItem()).getLstLivros().size() >= (((UsuarioDAO)getCbUser().getSelectedItem()).getMaxLivros()-retirado);
        else
            return false;
    }
    
    private Boolean dataEmBranco(){
        return txtData.getText().replace("/", "").replace(" ", "").equals("");
    }
    
    private void mensagemSucesso(String operacao){
        if(operacao.equals("Retirada"))
            JOptionPane.showMessageDialog(this, "Livro retirado com sucesso\nDevolução até: " + getDevolucao(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else if(operacao.equals("Reserva"))
            JOptionPane.showMessageDialog(this, "Livro reservado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else if(operacao.equals("Devolução"))
            JOptionPane.showMessageDialog(this, "Livro devolvido com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Livro renovado com sucesso\nNova devolução até: " + getDevolucao(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mensagemErro(){
        JOptionPane.showMessageDialog(this, "Operação não pode ser efetuada", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private void mensagemJaRetirado(){
        JOptionPane.showMessageDialog(this, "Livro já retirado pelo usuário", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private void mensagemMultas(){
        JOptionPane.showMessageDialog(this, "Usuário com multas", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private void mensagemReservado(){
        JOptionPane.showMessageDialog(this, "Livro já reservado por algum usuário", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private void mensagemIndisponivel(){
        JOptionPane.showMessageDialog(this, "Livro sem disponibilidade de retirada, nem reserva", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private void mensagemApenasReserva(){
        JOptionPane.showMessageDialog(this, "Livro sem disponibilidade de retirada, apenas reserva", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private void mensagemMaximo(){
        JOptionPane.showMessageDialog(this, "Usuário com o máximo de livros já retirados", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private void mensagemDataEmBranco(){
        JOptionPane.showMessageDialog(this, "Data da Retirada deve estar preenchida", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    
    private String getDevolucao(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(); 
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(txtData.getText());
        } catch (ParseException ex) {
            mensagemErro();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, getUser().getTempoRetirada());
        return dateFormat.format(c.getTime());
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
            java.util.logging.Logger.getLogger(FrmMostraLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMostraLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMostraLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMostraLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmMostraLivro dialog = new FrmMostraLivro(new javax.swing.JFrame(), true, new LivrosDAO(), new UsuarioDAO(), new String());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JComboBox cbUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDataPub;
    private javax.swing.JTextField txtEdicao;
    private javax.swing.JTextField txtEditora;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}