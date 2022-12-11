package controller;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import exceptions.NaoSelecionadoException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
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
        
        tela.mostrarNomeVendedor(vendedor.getNome());
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
    

    
    private void pesquisarProdutoPorNome(String nome) {
        for (Produto produto : produtoDao.getProdutos()) {
            if(produto.getNome().toLowerCase().contains(nome.toLowerCase()))
              tela.mostrarResultado(produto);
        }
    }
    
    private void pesquisarProdutoPorCodigo(String codigo){
        try{
            Integer codigoProduto = Integer.parseInt(codigo);
            
            Map<Integer, Produto> produtos = produtoDao.getProdutos().stream()
                .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));
            
            for(Integer cod : produtos.keySet()){
               Produto produto = produtos.get(cod);
                if(produto.getCodigo() == codigoProduto)
                    tela.mostrarResultado(produto);
            }
        }catch(NumberFormatException e){
            tela.exibirMensagem("Insira somente numeros na pesquisa por codigo", "Falha na Operação");
        }
    }
    
    private void pesquisarClientePorNome(String nome) {
        for (Cliente cliente : clienteDao.getClientes()) {
            if(cliente.getNome().toLowerCase().contains(nome.toLowerCase()))
                tela.mostrarResultado(cliente);
        }
    }

    private void pesquisarClientePorCPF(String CPF) {
        Set<Cliente> clientes = new HashSet<>();
        clientes.addAll(clienteDao.getClientes());

        for (Cliente cliente : clientes) {
            if (cliente.getCPF().contains(CPF)) {
                tela.mostrarResultado(cliente);
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
                if(tela.retornaValorIndexListaProduto() == -1)
                    pesquisarProdutoPorNome(tela.getNomeProduto());
            }
            else 
                tela.exibirMensagem("Nenhum valor inserido!", "Falha na Operação");
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
            if(tela.retornaValorIndexListaCliente() == -1)
                pesquisarClientePorNome(tela.getNomeCliente());
        }
        else
            tela.exibirMensagem("Nenhum valor inserido!", "Falha na Operação");
    }
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //    
//-------------------------                                 MANIPULAÇÃO DA TABELA                               -------------------------//
    
    private void adicionarProdutoTabela(){
        try {
            Produto item = tela.getProdutoTabela();

            int quantidade = tela.getQuantidadeProduto();
            if (quantidade <= 0 || quantidade > item.getQuantidade()) {
                tela.exibirMensagem("Quantidade invalida ou excedente", "Falha na Operação");
            } else {
                tela.validarItemSelecionado(item,quantidade);
                atualizarTotal();

                tela.limparTFNomeProduto();
                tela.limparTFCodigo();
            }
        } catch (NaoSelecionadoException ex) {
            tela.exibirMensagem(ex.getMessage(), "Falha na Operação");
        }
    }
    
    
    
    private void removerProdutoTabela(){
        try{
            acaoRemover();
        }catch(NaoSelecionadoException ex){
            tela.exibirMensagem(ex.getMessage(), "Falha na Operação");
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
            Double preco = tela.getColunaPreco(linhaAtual);

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
                tela.exibirMensagem("Nenhum produto Selecionado!", "Falha na Operação");
    
            } else {
                tela.exibirMensagem("Pedido gerado com sucesso", "Falha na Operação");
            }
            }catch(NullPointerException e){
                tela.exibirMensagem("Algum produto excede a capacidade disponível, verifique a disponibilidade e refaça o pedido.",
                                    "Falha na Operação");
            }
        }
    }
    
    private void cancelarVenda(){
        
        if (pedido.isEmpty()) {
            tela.exibirMensagem("nenhum pedido feito", "Falha na Operação");
        } else {
            if (tela.retornaOpcao()) {
                devolverProdutos();
                pedido = new ArrayList<>();
                tela.limparTodosOsCampos();
            } else {
                tela.exibirMensagem("Operação Cancelada", "Action: Operação Cancelada");
            }
        }
    }
    
    private Venda gerarVenda(List<ItemProduto> pedido){
        if (pedido == null) {
            tela.exibirMensagem("Compra inválida!"
                    + "\nPreencha corretamente todos os campos!", "Compra inválida");
            return null;
        }

        Cliente cliente = tela.getClienteSelecionado();
        String formaDePagamento = getFormaDePagemento() + " - " + tela.getParcelas();
        Venda venda = new Venda((Vendedor) vendedor, cliente, pedido, formaDePagamento);
        return venda;
    }
    
    private void finalizarVenda(){
        if (tela.retornaClienteSelecionado() == null) {
            tela.exibirError("Compra inválida!"
                    + "\nPreencha corretamente todos os campos!", "Compra inválida");
        } else if (tela.retornaFormaDePagamentoSelecionada() == null) {
            tela.exibirError("Compra inválida!"
                    + "\nPreencha corretamente todos os campos!", "Compra inválida");
        } else {
            
            try {
                Venda venda = gerarVenda(pedido);
                vendaDao.addVenda(venda);
                tela.exibirMensagem("Compra Realizada com Sucesso!", "Compra Concluida");
                
            } catch (NullPointerException ex) {
                tela.exibirMensagem("Operacao falhou", "Falha na Operação");
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
    
    private void abrirTelaCadastroCliente(){
        CadClienteView cadClienteView = new CadClienteView();
        new CadastroClienteController(cadClienteView, null).exibirTela();
    }
    
    private void disableCb(){
        if(tela.dinheiroSelecionado() || tela.debitoSelecionado())
        tela.ChangeStateCBParcelas(false);
    }
    
    private void enableCb(){
        tela.ChangeStateCBParcelas(true);
    }
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //       
//-------------------------                                      UTILIDADES                                     -------------------------//
    
    
    
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
    
 }  
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //  
//---------------------------------------------------------------------------------------------------------------------------------------//
