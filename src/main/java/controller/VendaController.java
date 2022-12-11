package controller;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import exceptions.NaoSelecionadoException;
import exceptions.ValorInvalidoException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Funcionario;
import model.ItemProduto;
import model.Produto;
import model.Venda;
import model.Vendedor;
import view.CadClienteView;
import view.VendaView;

/**
 *
 * @author Nicolas Debacher
 */
public class VendaController implements Controller{
    
    Funcionario vendedor;
    VendaView tela;
    ProdutoDAO produtoDao = new ProdutoDAO();
    ClienteDAO clienteDao = new ClienteDAO();
    VendaDAO vendaDao = new VendaDAO();
    
    
    private List<ItemProduto> pedido = new ArrayList<>();
    
    public VendaController(Funcionario vendedor) {
        this.tela = new VendaView();
        this.vendedor = vendedor;
        
        mostrarNomeVendedor();
        inicializarBotoes();
        atualizarTotal();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------                                     INICIALIZAÇÃO                                   -------------------------//
    
    @Override
    public void exibirTela(){
        tela.exibirTela();
    }
    
    @Override
    public void inicializarBotoes(){
        
        //Botoes
        tela.addActionBotaoFecharPedido     (e -> {fecharPedido();});
        tela.addActionBotaoCancelarPedido   (e -> {cancelarVenda();});
        tela.addActionBotaoFinalizarVenda   (e -> {finalizarVenda();});
        tela.addActionBotaoPesquisarProduto (e -> {pesquisarProduto();});
        tela.addActionBotaoPesquisarCliente (e -> {pesquisarCliente();});
        tela.addActionBotaoRemoverProduto   (e -> {removerProdutoTabela();});
        tela.addActionBotaoAdicionarProduto (e -> {adicionarProdutoTabela();});
        tela.addActionBotaoClienteNovo      (e -> {abrirTelaCadastroCliente();});
        
        //Radio Buttons
        tela.addActionRbCredito  (e -> {enableCb();});
        tela.addActionRbBoleto   (e -> {enableCb();});
        tela.addActionRbDinheiro (e -> {disableCb();});
        tela.addActionRbDebito   (e -> {disableCb();});
    }

//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------                                 METODOS DE PESQUISA                                 -------------------------//
    
    private void mostrarResultado(Produto p){
        DefaultListModel<Produto> listaProdutos = new DefaultListModel();
        listaProdutos.addElement(p);
        tela.getLtProdutos().setModel(listaProdutos);
    }

    private void mostrarResultado(Cliente c) {
        DefaultListModel<Cliente> listaClientes = new DefaultListModel();
        listaClientes.addElement(c);
        tela.getLtClientes().setModel(listaClientes);
    }
    
    private void pesquisarProdutoPorNome(String nome) {
        for (Produto p : produtoDao.getProdutos()) {
            if(p.getNome().toLowerCase().contains(nome.toLowerCase()))
                mostrarResultado(p);
        }
    }
    
    private void pesquisarProdutoPorCodigo(String codigo){
        try{
            Integer pCodigo = Integer.parseInt(codigo);
            
            Map<Integer, Produto> produtos = produtoDao.getProdutos().stream()
                .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));
            
            for(Integer cod : produtos.keySet()){
               Produto p = produtos.get(cod);
                if(p.getCodigo() == pCodigo)
                    mostrarResultado(p);
            }
        }catch(NumberFormatException e){
            mensagem("Insira somente numeros na pesquisa por codigo");
        }
    }
    
    private void pesquisarClientePorNome(String nome) {
        for (Cliente c : clienteDao.getClientes()) {
            if(c.getNome().toLowerCase().contains(nome.toLowerCase()))
                mostrarResultado(c);
        }
    }

    private void pesquisarClientePorCPF(String CPF) {
        Set<Cliente> clientes = new HashSet<>();
        clientes.addAll(clienteDao.getClientes());

        for (Cliente c : clientes) {
            if (c.getCPF().contains(CPF)) {
                mostrarResultado(c);
            }
        }
    }
    
    private void pesquisarProduto(){
    
            if(!campoNomeProdutoVazio() && campoCodigoVazio()){
                pesquisarProdutoPorNome(tela.getNomeProduto());
            }
            else if (campoNomeProdutoVazio() && !campoCodigoVazio()){
                pesquisarProdutoPorCodigo(tela.getCodigoProduto());
            }
            else if(!campoNomeProdutoVazio() && !campoCodigoVazio()){
                pesquisarProdutoPorCodigo(tela.getCodigoProduto());
                if(tela.getLtProdutos().getFirstVisibleIndex() == -1)
                    pesquisarProdutoPorNome(tela.getNomeProduto());
            }
            else 
                mensagem("Nenhum valor inserido!");
    }
    
    private void pesquisarCliente(){
        
         if(!campoNomeClienteVazio() && campoCPFVazio()){
            pesquisarClientePorNome(tela.getNomeCliente());
        }
        else if (campoNomeClienteVazio() && !campoCPFVazio()){
            pesquisarClientePorCPF(tela.getCPF());
        }
        else if(!campoNomeClienteVazio() && !campoCPFVazio()){
             pesquisarClientePorCPF(tela.getCPF());
            if(tela.getLtClientes().getFirstVisibleIndex() == -1)
                pesquisarClientePorNome(tela.getNomeCliente());
        }
        else
            mensagem("Nenhum valor inserido!");
    }
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //    
//-------------------------                                 MANIPULAÇÃO DA TABELA                               -------------------------//
    
    private void adicionarProdutoTabela(){
        try {
            Produto item = getProdutoTabela();

            int quantidade = tela.getQuantidadeProduto();
            if (quantidade <= 0 || quantidade > item.getQuantidade()) {
                mensagem("Quantidade invalida ou excedente");
                System.out.println("Quantidade invalida ou excedente");
            } else {
                validarItemSelecionado(item, quantidade);
                atualizarTotal();

                limpaCampo(tela.getTfNomeProduto());
                limpaCampo(tela.getTfCodigo());
            }
        } catch (NaoSelecionadoException ex) {
            mensagem(ex.getMessage());
        }
    }
    
    private Produto getProdutoTabela() throws NaoSelecionadoException {
        if (tela.getLtProdutos().isSelectionEmpty()) {
            throw new NaoSelecionadoException("lista produto");
        }
        return tela.getLtProdutos().getSelectedValue();
    }
    
    private void removerProdutoTabela(){
        try{
            acaoRemover();
        }catch(NaoSelecionadoException ex){
            mensagem(ex.getMessage());
        atualizarTotal();
        }
    }
    
    private void atualizarTotal() {
        
        Double valorTotalDaCompra = 0.0;
        for (int linha = 0; linha < tela.getLinhasTotal(); linha++) {
            valorTotalDaCompra += Double.parseDouble(tela.getValorTotalCompra(linha));
        }
        tela.setValorTotal(valorTotalDaCompra);
    }
    
    private void acaoRemover() throws NaoSelecionadoException{
        
        if(tela.getLinhaSelecionada() == -1)
            throw new NaoSelecionadoException("lista produto");
        else 
            tela.removerLinhaSelecionada();
    }
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------                                 MANIPULAÇÃO DA VENDA                                -------------------------//
    
    private List<ItemProduto> gerarPedido() {
        List<ItemProduto> pedidoGerado = new ArrayList<>();

        for (int linhaAtual = 0; linhaAtual < tela.getLinhasTotal(); linhaAtual++) {

            String nome = tela.getColunaNome(linhaAtual);
            Integer codigo = tela.getColunaCodigo(linhaAtual);
            int quantidade = tela.getColunaQuantidade(linhaAtual);
            Double preco = (Double) tela.getColunaPreco(linhaAtual);

            ItemProduto item = new ItemProduto(nome, codigo, preco, quantidade);

            Produto prod = buscarProdutoPorNome(nome);
            if (prod.getQuantidade() < item.getQuantidade()) {
                return null;
            }

            pedidoGerado.add(item);
            prod.setQuantidade(prod.getQuantidade() - quantidade);
        }

        return pedidoGerado;
    }
    
    
    private void fecharPedido(){
        try{
        devolverProdutos();
        }
        catch(NullPointerException e){}
        
        finally{
            pedido = gerarPedido();
            
            try{
            if (pedido.isEmpty()) {
                mensagem("Nenhum produto Selecionado!");
    
            } else {
                mensagem("Pedido gerado com sucesso");
            }
            }catch(NullPointerException e){
                mensagem("Algum produto excede a capacidade disponível, verifique a disponibilidade e refaça o pedido.");
            }
        }
    }
    
    private void cancelarVenda(){
        
        if (pedido.isEmpty()) {
            mensagem("nenhum pedido feito");
        } else {
            int cancelar = JOptionPane.showConfirmDialog
        (tela, "Deseja cancelar a compra atual?", "Cancelar Compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (cancelar == JOptionPane.YES_OPTION) {
                devolverProdutos();
                pedido = new ArrayList<>();
                limparTodosOsCampos();
            } else {
                JOptionPane.showMessageDialog(tela, "Operação Cancelada", "Action: Operação Cancelada", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private Venda gerarVenda(List<ItemProduto> pedido){
        if (pedido == null) {
            JOptionPane.showMessageDialog(tela, "Compra inválida!"
                    + "\nPreencha corretamente todos os campos!", "Compra inválida", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Cliente cliente = tela.getLtClientes().getSelectedValue();
        String formaDePagamento = getFormaDePagemento() + " - " + tela.getParcelas();
        Venda venda = new Venda((Vendedor) vendedor, cliente, pedido, formaDePagamento);
        return venda;
    }
    
    private void finalizarVenda(){
        if (tela.getLtClientes().getSelectedValue() == null) {
            JOptionPane.showMessageDialog(tela, "Compra inválida!"
                    + "\nPreencha corretamente todos os campos!", "Compra inválida", JOptionPane.ERROR_MESSAGE);
        } else if (tela.getBgFormasDePagamento().getSelection() == null) {
            JOptionPane.showMessageDialog(tela, "Compra inválida!"
                    + "\nPreencha corretamente todos os campos!", "Compra inválida", JOptionPane.ERROR_MESSAGE);
        } else {
            
            try {
                Venda venda = gerarVenda(pedido);
                vendaDao.addVenda(venda);
                JOptionPane.showMessageDialog(tela, "Compra Realizada com Sucesso!", "Compra Concluida", JOptionPane.WARNING_MESSAGE);
                System.out.println("Foi");
                
            } catch (NullPointerException ex) {
                mensagem("Operacao falhou");
                System.out.println("falhou");
            }
        }
        
    }
    
    private String getFormaDePagemento() {
       
        if (tela.dinheiroSelecionado()) {
            return "Dinheiro (à vista)";
        } else if (tela.creditoSelecionado()) {
            return "Cartão de Crédito";
        } else if (tela.debitoSelecionado()) {
            return "Cartão de Débito";
        }
        else
            return "Boleto Bancário";
    }
    
    private void devolverProdutos(){
        for (ItemProduto item : pedido) {
            Produto prod = buscarProdutoPorNome(item.getNome());
            prod.setQuantidade(prod.quantidade + item.getQuantidade());
        }
    }
    
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- // 
//-------------------------                                      VALIDAÇÕES                                     -------------------------//

 private boolean validarItemSelecionado(Produto item, int quantidade){
     DefaultTableModel model = (DefaultTableModel) tela.getTbProdutos().getModel();
        for (int i = 0; i < tela.getLinhasTotal(); i++) {
            if (item.getNome().equals(tela.getTbProdutos().getValueAt(i, 0))) {
                Integer quantidadeAnterior = Integer.parseInt(tela.getTbProdutos().getValueAt(i, 2).toString());
                if (quantidadeAnterior + tela.getQuantidadeProduto() > item.getQuantidade()) {
                    mensagem("Produto excedente da quantidade em estoque");
                    return false;
 
                } else {
                    tela.getTbProdutos().setValueAt(quantidadeAnterior + quantidade, i, 2);
                    Double novoPreco = item.getPreco() * Integer.parseInt(tela.getTbProdutos().getValueAt(i, 2).toString());
                    tela.getTbProdutos().setValueAt(novoPreco, i, 3);
                    return false;
                }
            }
        }
        model.addRow(new Object[]{item.getNome(), item.getCodigo(), quantidade, item.getPreco() * quantidade});
        return true;
    }
 
    private void mostrarNomeVendedor(){
        tela.getLbVendedorAtual().setText(vendedor.getNome());  
    }
    
    private void abrirTelaCadastroCliente(){
        CadClienteView cadClienteView = new CadClienteView();
        new CadastroClienteController(cadClienteView, null).exibirTela();
    }
    
    private void disableCb(){
        if(tela.dinheiroSelecionado() || tela.debitoSelecionado())
        tela.getCbParcelas().setEnabled(false);
        tela.getCbParcelas().setSelectedIndex(0);
    }
    
    private void enableCb(){
        tela.getCbParcelas().setEnabled(true);
    }
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //       
//-------------------------                                      UTILIDADES                                     -------------------------//
    
    public void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(tela, mensagem);
    }
    
    public void limpaCampo(JTextField textField) {
        textField.setText("");
    }

    public void limpaCampo(JTextArea textArea) {
        textArea.setText("");
    }
    
    private boolean campoCodigoVazio() {
        return tela.getCodigoProduto().isBlank();
    }

    private boolean campoNomeProdutoVazio() {
        return tela.getNomeProduto().isBlank();
    }

    private boolean campoCPFVazio() {
        return tela.getCPF().isBlank();
    }

    private boolean campoNomeClienteVazio(){
        return tela.getNomeCliente().isBlank();
    }

    private void limparTodosOsCampos(){
        tela.getTfNomeCliente().setText("");
        tela.getTfCPF().setText("");
        tela.getTfCodigo().setText("");
        tela.getTfNomeProduto().setText("");
    }
    
    public Produto buscarProdutoPorNome(String nome){
        for (Produto p : produtoDao.getProdutos()) {
            if(p.getNome().equals(nome))
                return p;
        }
        return null;
    }
    
    public Produto buscarProdutoPorCodigo(int codigo){
        for (Produto p : produtoDao.getProdutos()) {
            if(p.getCodigo() == codigo)
                return p;
        }
        return null;
    }

    public Cliente buscarClientePorNome(String nome) {
        for (Cliente c : clienteDao.getClientes()){
            if(c.getNome().equals(nome))
                return c;
        }
        return null;
    }

    public Cliente buscarClientePorCPF(String CPF) {
        for (Cliente c : clienteDao.getClientes()) {
            if(c.getCPF().equals(CPF))
                return c;
        }
        return null;
    }
    
 }  
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //  
//---------------------------------------------------------------------------------------------------------------------------------------//
