package view;

import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dao.ClienteDAO;
import model.Cliente;
import controller.Controller;
import java.awt.Color;
import java.awt.event.ActionListener;
import model.Admin;
import model.Funcionario;


/**
 *
 * @author Gustavo
 */
public class AdminView extends javax.swing.JFrame implements Controller {
    
    private List<Categoria> categorias;
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    /**    
    /**
     * Creates new form MainView
     */
    public AdminView(){
        initComponents();
        this.setTitle("LGC - HermosoStovisk");
        decoracao();
    }
    
    public void exibirTela(){
        this.setVisible(true);
    }
    
    
    public List<Categoria> getCategorias(){
        return this.categorias;
    }  
    public ProdutoDAO getProdutoDAO() {
        return this.produtoDAO;
    }
    @Override
    public void limpaCampo(JTextField textField){
        textField.setText("");
    }
     
    @Override
    public void limpaCampo(JTextArea textArea){
        textArea.setText("");
    }
    public void adicionarAcaoAoItemMenuCadastrarProduto(ActionListener acao){
        miCadastrarProduto.addActionListener(acao);
    }
    public void adicionarAcaoAoItemMenuCadastrarCategoria(ActionListener acao){
        miCadastrarCategoria.addActionListener(acao);
    }
    public void adicionarAcaoAoItemMenuRelatorioCliente(ActionListener acao){
        miRelatorioCliente.addActionListener(acao);
    }
    public void adicionarAcaoAoItemMenuRelatorioProduto(ActionListener acao){
        miRelatorioProdutos.addActionListener(acao);
    }
    public void adicionarAcaoAoItemMenuRelatorioVenda(ActionListener acao){
        miRelatorioVenda.addActionListener(acao);
    }
    public void adicionarAcaoAoItemMenuRemoverProduto(ActionListener acao){
        miRemoverProduto.addActionListener(acao);
    }
    public void adicionarAcaoAoItemMenuRemoverCliente(ActionListener acao){
        miRelatorioCliente.addActionListener(acao);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbBoasVindas = new javax.swing.JLabel();
        lbMensagem = new javax.swing.JLabel();
        lbMensagem1 = new javax.swing.JLabel();
        lbImg = new javax.swing.JLabel();
        mbMain = new javax.swing.JMenuBar();
        mCadastro = new javax.swing.JMenu();
        miCadastrarProduto = new javax.swing.JMenuItem();
        miCadastrarCategoria = new javax.swing.JMenuItem();
        mRelatorio = new javax.swing.JMenu();
        miRelatorioVenda = new javax.swing.JMenuItem();
        miRelatorioCliente = new javax.swing.JMenuItem();
        miRelatorioProdutos = new javax.swing.JMenuItem();
        mRemover = new javax.swing.JMenu();
        miRemoverCliente = new javax.swing.JMenuItem();
        miRemoverProduto = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(63, 63, 70));

        lbBoasVindas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbBoasVindas.setForeground(new java.awt.Color(250, 250, 249));
        lbBoasVindas.setText("Olá,");

        lbMensagem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMensagem.setText("Bem-Vindo(a) Ao Hermozotovisk,");

        lbMensagem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMensagem1.setText("Seu Gerenciador de Estoque Favorito!");

        lbImg.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lbImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        lbImg.setText("Hermozotoviski");

        mCadastro.setText("Cadastrar");

        miCadastrarProduto.setText("Cadastrar Produtos");
        mCadastro.add(miCadastrarProduto);

        miCadastrarCategoria.setText("Cadastrar Categorias");
        mCadastro.add(miCadastrarCategoria);

        mbMain.add(mCadastro);

        mRelatorio.setText("Relatórios");

        miRelatorioVenda.setText("Relatório de Vendas");
        miRelatorioVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRelatorioVendaActionPerformed(evt);
            }
        });
        mRelatorio.add(miRelatorioVenda);

        miRelatorioCliente.setText("Relatório de Clientes");
        miRelatorioCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRelatorioClienteActionPerformed(evt);
            }
        });
        mRelatorio.add(miRelatorioCliente);

        miRelatorioProdutos.setText("Relatório de Produtos");
        miRelatorioProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRelatorioProdutosActionPerformed(evt);
            }
        });
        mRelatorio.add(miRelatorioProdutos);

        mbMain.add(mRelatorio);

        mRemover.setText("Remover");

        miRemoverCliente.setText("Remover Cliente");
        miRemoverCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRemoverClienteActionPerformed(evt);
            }
        });
        mRemover.add(miRemoverCliente);

        miRemoverProduto.setText("Remover Produto");
        miRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRemoverProdutoActionPerformed(evt);
            }
        });
        mRemover.add(miRemoverProduto);

        mbMain.add(mRemover);

        setJMenuBar(mbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbBoasVindas)
                    .addComponent(lbMensagem1)
                    .addComponent(lbMensagem)
                    .addComponent(lbImg))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lbBoasVindas)
                .addGap(18, 18, 18)
                .addComponent(lbMensagem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMensagem1)
                .addGap(32, 32, 32)
                .addComponent(lbImg)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miRelatorioClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRelatorioClienteActionPerformed
        // TODO add your handling code here:
        RelatorioClienteView relCliente = new RelatorioClienteView();
        relCliente.setVisible(true);
    }//GEN-LAST:event_miRelatorioClienteActionPerformed

    private void miRelatorioProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRelatorioProdutosActionPerformed
        // TODO add your handling code here:
        RelatorioProdutoView relProd = new RelatorioProdutoView(this);
        relProd.setVisible(true);
    }//GEN-LAST:event_miRelatorioProdutosActionPerformed

    private void miRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoverProdutoActionPerformed
        // TODO add your handling code here:

      RemoverProdutoView remProd = new RemoverProdutoView();
      remProd.setVisible(true);

    }//GEN-LAST:event_miRemoverProdutoActionPerformed

    private void miRelatorioVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRelatorioVendaActionPerformed
        // TODO add your handling code here:
        RelatorioVendasView relVendas = new RelatorioVendasView();
        relVendas.setVisible(true);
    }//GEN-LAST:event_miRelatorioVendaActionPerformed

    private void miRemoverClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoverClienteActionPerformed
        // TODO add your handling code here:
        RemoverClienteView remCliente = new RemoverClienteView();
        remCliente.setVisible(true);
    }//GEN-LAST:event_miRemoverClienteActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbBoasVindas;
    private javax.swing.JLabel lbImg;
    private javax.swing.JLabel lbMensagem;
    private javax.swing.JLabel lbMensagem1;
    private javax.swing.JMenu mCadastro;
    private javax.swing.JMenu mRelatorio;
    private javax.swing.JMenu mRemover;
    private javax.swing.JMenuBar mbMain;
    private javax.swing.JMenuItem miCadastrarCategoria;
    private javax.swing.JMenuItem miCadastrarProduto;
    private javax.swing.JMenuItem miRelatorioCliente;
    private javax.swing.JMenuItem miRelatorioProdutos;
    private javax.swing.JMenuItem miRelatorioVenda;
    private javax.swing.JMenuItem miRemoverCliente;
    private javax.swing.JMenuItem miRemoverProduto;
    // End of variables declaration//GEN-END:variables

    @Override
    public Produto buscarProdutoPorNome(String nome) {
        for(Produto p: this.produtoDAO.getProdutos()){
            if(p.getNome().contains(nome)){
                return p;
            }
        }
        return null;
    }

    @Override
    public Produto buscarProdutoPorCodigo(int codigo) {
        throw new UnsupportedOperationException("Not supported."); 
    }



    @Override
    public Cliente buscarClientePorNome(String nome) {
        for (Cliente c : clienteDAO.getClientes()) {
            if(c.getNome().toLowerCase().contains(nome.toLowerCase()))
                return c;
        }
        return null;
    }

    @Override
    public Cliente buscarClientePorCPF(String CPF) {
        for (Cliente c : clienteDAO.getClientes()) {
            if(CPF.equals(c.getCPF()))
                return c;
        }
        return null;
    }

    @Override
    public void mensagem(String mensagem) {
          JOptionPane.showMessageDialog(null, mensagem);
    }
    
    private void decoracao(){
        getContentPane().setBackground(getBackground());
//        lbBoasVindas.setText("Olá " + adm.getNome() + ", Boa noite");
        lbBoasVindas.setForeground(Color.decode("#fafaf9"));
        mbMain.setBackground(Color.decode("#71717a"));
        mCadastro.setForeground(Color.decode("#fafaf9"));
        mRelatorio.setForeground(Color.decode("#fafaf9"));
        mRemover.setForeground(Color.decode("#fafaf9"));
        miCadastrarCategoria.setForeground(Color.decode("#fafaf9"));
        miCadastrarProduto.setForeground(Color.decode("#fafaf9"));
        miRelatorioCliente.setForeground(Color.decode("#fafaf9"));
        miRelatorioProdutos.setForeground(Color.decode("#fafaf9"));
        miRelatorioVenda.setForeground(Color.decode("#fafaf9"));
        miRemoverCliente.setForeground(Color.decode("#fafaf9"));
        miRemoverProduto.setForeground(Color.decode("#fafaf9"));
        miCadastrarCategoria.setBackground(Color.decode("#71717a"));
        miCadastrarProduto.setBackground(Color.decode("#71717a"));
        miRelatorioCliente.setBackground(Color.decode("#71717a"));
        miRelatorioProdutos.setBackground(Color.decode("#71717a"));
        miRelatorioVenda.setBackground(Color.decode("#71717a"));
        miRemoverCliente.setBackground(Color.decode("#71717a"));
        miRemoverProduto.setBackground(Color.decode("#71717a"));
        lbMensagem.setForeground(Color.decode("#fafaf9"));
        lbMensagem1.setForeground(Color.decode("#fafaf9"));
        lbImg.setForeground(Color.decode("#fafaf9"));
    }
}
