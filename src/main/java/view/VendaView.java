package view;

import exceptions.NaoSelecionadoException;
import model.Produto;
import javax.swing.JTextField;
import model.Cliente;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Nicolas Debacher
 */
public class VendaView extends javax.swing.JFrame {

    public VendaView() {
        initComponents();
        decoracao();
    }

    public void exibirTela() {
        this.setVisible(true);
    }

    //---------------------------Metodos pra adicionar ações nos botões---------------------------//
    public void addActionBotaoPesquisarProduto(ActionListener acao) {
        btPesquisarProduto.addActionListener(acao);
    }

    public void addActionBotaoAdicionarProduto(ActionListener acao) {
        btAdicionar.addActionListener(acao);
    }

    public void addActionBotaoRemoverProduto(ActionListener acao) {
        btRemover.addActionListener(acao);
    }

    public void addActionBotaoFecharPedido(ActionListener acao) {
        btFecharPedido.addActionListener(acao);
    }

    public void addActionBotaoPesquisarCliente(ActionListener acao) {
        btPesquisarCliente.addActionListener(acao);
    }

    public void addActionBotaoCancelarPedido(ActionListener acao) {
        btCancelar.addActionListener(acao);
    }

    public void addActionBotaoFinalizarVenda(ActionListener acao) {
        btConcluir.addActionListener(acao);
    }

    public void addActionBotaoClienteNovo(ActionListener acao) {
        btClienteNovo.addActionListener(acao);
    }

    public void addActionRbDinheiro(ActionListener acao) {
        rbDinheiro.addActionListener(acao);
    }

    public void addActionRbCredito(ActionListener acao) {
        rbCredito.addActionListener(acao);
    }

    public void addActionRbDebito(ActionListener acao) {
        rbDebito.addActionListener(acao);
    }

    public void addActionRbBoleto(ActionListener acao) {
        rbBoletoBancario.addActionListener(acao);
    }

    //---------------------------Metodos que só a tela retorna---------------------------//

    public void mostrarNomeVendedor(String vendedorNome) {
        lbVendedorAtual.setText(vendedorNome);
    }
    

    public Cliente getClienteSelecionado() {
        return ltClientes.getSelectedValue();
    }
    
    public int retornaValorIndexListaProduto(){
        return ltProdutos.getFirstVisibleIndex();
    }
    
    public int retornaValorIndexListaCliente(){
        return ltClientes.getFirstVisibleIndex();
    }
    
    public Produto getProdutoTabela() throws NaoSelecionadoException {
        if (ltProdutos.isSelectionEmpty()) {
            throw new NaoSelecionadoException("lista produto");
        }
        return ltProdutos.getSelectedValue();
    }
    
    public void mostrarResultado(Produto p) {
        DefaultListModel<Produto> listaProdutos = new DefaultListModel();
        listaProdutos.addElement(p);
        ltProdutos.setModel(listaProdutos);
    }

    public void mostrarResultado(Cliente c) {
        DefaultListModel<Cliente> listaClientes = new DefaultListModel();
        listaClientes.addElement(c);
        ltClientes.setModel(listaClientes);
    }

    public void ChangeStateCBParcelas(boolean state) {
        cbParcelas.setEnabled(state);
        cbParcelas.setSelectedIndex(0);
    }

    public boolean boletoBancarioSelecionado() {
        return rbBoletoBancario.isSelected();
    }

    public boolean creditoSelecionado() {
        return rbCredito.isSelected();
    }

    public boolean debitoSelecionado() {
        return rbDebito.isSelected();
    }

    public boolean dinheiroSelecionado() {
        return rbDinheiro.isSelected();
    }

    public int getQuantidadeProduto() {
        return Integer.parseInt(spQuantidade.getValue().toString());
    }

    public int getLinhasTotal() {
        return tbProdutos.getRowCount();
    }

    public String getValorTotalCompra(int linha) {
        return tbProdutos.getValueAt(linha, 3).toString();
    }

    public String getCPF() {
        return tfCPF.getText();
    }

    public String getCodigoProduto() {
        return tfCodigo.getText();
    }

    public String getNomeCliente() {
        return tfNomeCliente.getText();
    }

    public String getNomeProduto() {
        return tfNomeProduto.getText();
    }

    private TableModel getModel() {
        return tbProdutos.getModel();
    }

    public void removerLinhaSelecionada() {
        ((DefaultTableModel) getModel()).removeRow(tbProdutos.getSelectedRow());
    }

    public void setValorTotal(double valorTotalDaCompra) {
        tfTotal.setText("R$" + valorTotalDaCompra);
    }

    public int getLinhaSelecionada() {
        return tbProdutos.getSelectedRow();
    }

    public Cliente retornaClienteSelecionado() {
        return ltClientes.getSelectedValue();
    }

    public ButtonModel retornaFormaDePagamentoSelecionada() {
        return bgFormasDePagamento.getSelection();
    }

    public double getValorTotal() {
        return Double.parseDouble(tfTotal.getText());
    }

    public void limparTFNomeProduto() {
        limpaCampo(tfNomeProduto);
    }

    public void limparTFCodigo() {
        limpaCampo(tfCodigo);
    }

    public String getColunaNome(int linhaAtual) {
        byte colunaNome = 0;
        return (String) tbProdutos.getValueAt(linhaAtual, colunaNome);
    }

    public int getColunaCodigo(int linhaAtual) {
        byte colunaCodigo = 1;
        return (int) tbProdutos.getValueAt(linhaAtual, colunaCodigo);
    }

    public int getColunaQuantidade(int linhaAtual) {
        byte colunaQuantidade = 2;
        return (int) tbProdutos.getValueAt(linhaAtual, colunaQuantidade);

    }

    public Double getColunaPreco(int linhaAtual) {
        byte colunaPreco = 3;
        return (Double) tbProdutos.getValueAt(linhaAtual, colunaPreco);
    }

    private void setColunaQuantidade(Object value, int linhaAtual) {
        byte colunaQuantidade = 2;
        tbProdutos.setValueAt(value, linhaAtual, colunaQuantidade);
    }

    private void setColunaPreco(Object value, int linhaAtual) {
        byte colunaPreco = 3;
        tbProdutos.setValueAt(value, linhaAtual, colunaPreco);
    }

    public String getParcelas() {
        return (String) cbParcelas.getSelectedItem();
    }

    public void exibirMensagem(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    private boolean produtoExcedeEstoque(Integer quantidadeAnterior, Produto produto) {
        return quantidadeAnterior + getQuantidadeProduto() > produto.getQuantidade();
    }

    public boolean validarItemSelecionado(Produto item, int quantidade) {
        DefaultTableModel model = (DefaultTableModel) getModel();

        for (int linhaAtual = 0; linhaAtual < getLinhasTotal(); linhaAtual++) {
            if (item.getNome().equals(tbProdutos.getValueAt(linhaAtual, 0))) {
                Integer quantidadeAnterior = getColunaQuantidade(linhaAtual);
                if (produtoExcedeEstoque(quantidadeAnterior, item)) {
                    exibirMensagem("Produto excedente da quantidade em estoque",
                            "Falha na Operação");
                } else {
                    setColunaQuantidade(quantidadeAnterior + quantidade, linhaAtual);
                    Double novoPreco = item.getPreco() * getColunaQuantidade(linhaAtual);
                    setColunaQuantidade(novoPreco, linhaAtual);
                    return false;
                }
            }
        }
        model.addRow(new Object[]{item.getNome(), item.getCodigo(), quantidade, item.getPreco() * quantidade});
        return true;
    }

    public void exibirError(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void setVendedorAtual(String nome) {
        lbVendedorAtual.setText(nome);
    }

    public void limparTodosOsCampos() {
        tfCPF.setText("");
        tfCodigo.setText("");
        tfNomeCliente.setText("");
        tfNomeProduto.setText("");
    }

    public boolean retornaOpcao() {
        if (mostraOpcao() == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    public int mostraOpcao() {
        int cancelar = JOptionPane.showConfirmDialog(null, "Deseja cancelar a compra atual?", "Cancelar Compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return cancelar;
    }

    private void limpaCampo(JTextField textField) {
        textField.setText("");
    }

    private void limpaCampo(JTextArea textArea) {
        textArea.setText("");
    }

    //--------------------------------------------------------------------------------------------//
    private void decoracao() {
        getContentPane().setBackground(Color.decode("#3f3f46"));
        rbBoletoBancario.setBackground(Color.decode("#3f3f46"));
        rbCredito.setBackground(Color.decode("#3f3f46"));
        rbDebito.setBackground(Color.decode("#3f3f46"));
        rbDinheiro.setBackground(Color.decode("#3f3f46"));
        rbBoletoBancario.setForeground(Color.decode("#fafaf9"));
        rbCredito.setForeground(Color.decode("#fafaf9"));
        rbDebito.setForeground(Color.decode("#fafaf9"));
        rbDinheiro.setForeground(Color.decode("#fafaf9"));
        btAdicionar.setBackground(Color.decode("#38bdf8"));
        btAdicionar.setForeground(Color.decode("#fafaf9"));
        btCancelar.setBackground(Color.decode("#38bdf8"));
        btCancelar.setForeground(Color.decode("#fafaf9"));
        btConcluir.setBackground(Color.decode("#38bdf8"));
        btConcluir.setForeground(Color.decode("#fafaf9"));
        btFecharPedido.setBackground(Color.decode("#38bdf8"));
        btFecharPedido.setForeground(Color.decode("#fafaf9"));
        btPesquisarCliente.setBackground(Color.decode("#38bdf8"));
        btPesquisarCliente.setForeground(Color.decode("#fafaf9"));
        btPesquisarProduto.setBackground(Color.decode("#38bdf8"));
        btPesquisarProduto.setForeground(Color.decode("#fafaf9"));
        btRemover.setBackground(Color.decode("#38bdf8"));
        btRemover.setForeground(Color.decode("#fafaf9"));
        lbVendedorAtual.setForeground(Color.decode("#fafaf9"));
        jLabel1.setForeground(Color.decode("#fafaf9"));
        jLabel10.setForeground(Color.decode("#fafaf9"));
        jLabel11.setForeground(Color.decode("#fafaf9"));
        jLabel12.setForeground(Color.decode("#fafaf9"));
        jLabel2.setForeground(Color.decode("#fafaf9"));
        jLabel3.setForeground(Color.decode("#fafaf9"));
        jLabel4.setForeground(Color.decode("#fafaf9"));
        jLabel5.setForeground(Color.decode("#fafaf9"));
        jLabel6.setForeground(Color.decode("#fafaf9"));
        jLabel7.setForeground(Color.decode("#fafaf9"));
        jLabel8.setForeground(Color.decode("#fafaf9"));
        jLabel9.setForeground(Color.decode("#fafaf9"));

        tfCPF.setBackground(Color.decode("#f3f4f6"));
        tfNomeProduto.setBackground(Color.decode("#f3f4f6"));
        tfNomeCliente.setBackground(Color.decode("#f3f4f6"));
        tfNomeProduto.setBackground(Color.decode("#f3f4f6"));
        tfCodigo.setBackground(Color.decode("#f3f4f6"));
        tfTotal.setBackground(Color.decode("#f3f4f6"));
        tfTotal.setForeground(Color.decode("#18181b"));
        tfNomeProduto.setForeground(Color.decode("#18181b"));
        tfCodigo.setForeground(Color.decode("#18181b"));
        tfCPF.setForeground(Color.decode("#18181b"));
        tfNomeCliente.setForeground(Color.decode("#18181b"));
        lbVendedorAtual.setForeground(Color.decode("#fafaf9"));
        tbProdutos.setBackground(Color.decode("#f3f4f6"));
        tbProdutos.setForeground(Color.decode("#18181b"));
        spQuantidade.setBackground(Color.decode("#f3f4f6"));
        spQuantidade.setForeground(Color.decode("#18181b"));
        cbParcelas.setBackground(Color.decode("#f3f4f6"));
        cbParcelas.setForeground(Color.decode("#18181b"));

        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setTitle("Realizar Venda");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        bgFormasDePagamento = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tfNomeProduto = new javax.swing.JTextField();
        tfCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btPesquisarProduto = new javax.swing.JButton();
        tfTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btAdicionar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ltClientes = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        ltProdutos = new javax.swing.JList<>();
        btRemover = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbProdutos = new javax.swing.JTable();
        rbDinheiro = new javax.swing.JRadioButton();
        btFecharPedido = new javax.swing.JButton();
        rbCredito = new javax.swing.JRadioButton();
        rbDebito = new javax.swing.JRadioButton();
        spQuantidade = new javax.swing.JSpinner();
        rbBoletoBancario = new javax.swing.JRadioButton();
        btConcluir = new javax.swing.JButton();
        cbParcelas = new javax.swing.JComboBox<>();
        btPesquisarCliente = new javax.swing.JButton();
        lbVendedorAtual = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        tfCPF = new javax.swing.JTextField();
        tfNomeCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btClienteNovo = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hermozostovisky\n");
        setLocation(new java.awt.Point(0, 0));
        setState(1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Cod. Produto:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Al Informática");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Pesquisar produtos");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nome:");

        btPesquisarProduto.setText("Pesquisar");

        tfTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Total :");

        btAdicionar.setText("Adicionar");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Carrinho");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Inserir dados do cliente");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("CPF");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Nome");

        jScrollPane3.setViewportView(ltClientes);

        jScrollPane4.setViewportView(ltProdutos);

        btRemover.setText("Remover");

        tbProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Código", "Quantidade", "Preço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbProdutos);

        bgFormasDePagamento.add(rbDinheiro);
        rbDinheiro.setText("Dinheiro (à vista)");

        btFecharPedido.setText("Fechar pedido");

        bgFormasDePagamento.add(rbCredito);
        rbCredito.setText("Cartão de Crédito");

        bgFormasDePagamento.add(rbDebito);
        rbDebito.setText("Cartão Débito");

        spQuantidade.setValue(1);

        bgFormasDePagamento.add(rbBoletoBancario);
        rbBoletoBancario.setText("Boleto Bancário");

        btConcluir.setText("FINALIZAR VENDA");

        cbParcelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1x (à vista)", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", "11x", "12x" }));

        btPesquisarCliente.setText("Pesquisar");

        lbVendedorAtual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbVendedorAtual.setText("Vendedor(a): null");

        btCancelar.setText("CANCELAR VENDA");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Finalizar Compra");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Cliente novo?");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Método de Pagamento");

        btClienteNovo.setBackground(new java.awt.Color(102, 102, 102));
        btClienteNovo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btClienteNovo.setForeground(new java.awt.Color(51, 51, 51));
        btClienteNovo.setText("+");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbVendedorAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(174, 174, 174)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                                .addComponent(btPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(spQuantidade))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addComponent(tfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(274, 274, 274)
                                            .addComponent(btPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(btClienteNovo))
                                                .addComponent(jLabel10))))
                                    .addGap(29, 29, 29)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(rbCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(rbDebito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(rbBoletoBancario)
                                            .addComponent(rbDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel11)
                                        .addComponent(cbParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(btFecharPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(491, 491, 491)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(60, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btConcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(415, 415, 415)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVendedorAtual))
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jLabel6)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btFecharPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addGap(8, 8, 8)
                .addComponent(jLabel7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btClienteNovo))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbDinheiro)
                                .addGap(10, 10, 10)
                                .addComponent(rbCredito)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbDebito)
                                .addGap(6, 6, 6)
                                .addComponent(rbBoletoBancario)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(btConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(906, 982));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgFormasDePagamento;
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btClienteNovo;
    private javax.swing.JButton btConcluir;
    private javax.swing.JButton btFecharPedido;
    private javax.swing.JButton btPesquisarCliente;
    private javax.swing.JButton btPesquisarProduto;
    private javax.swing.JButton btRemover;
    private javax.swing.JComboBox<String> cbParcelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbVendedorAtual;
    private javax.swing.JList<Cliente> ltClientes;
    private javax.swing.JList<Produto> ltProdutos;
    private javax.swing.JRadioButton rbBoletoBancario;
    private javax.swing.JRadioButton rbCredito;
    private javax.swing.JRadioButton rbDebito;
    private javax.swing.JRadioButton rbDinheiro;
    private javax.swing.JSpinner spQuantidade;
    private javax.swing.JTable tbProdutos;
    private javax.swing.JTextField tfCPF;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfNomeProduto;
    private javax.swing.JTextField tfTotal;
    // End of variables declaration//GEN-END:variables

}
