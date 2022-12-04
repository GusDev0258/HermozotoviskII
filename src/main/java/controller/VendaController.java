package controller;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Cliente;
import model.Funcionario;
import model.Produto;
import view.VendaView;

/**
 *
 * @author Nicolas Debacher
 */
public class VendaController {
    
    Funcionario vendedor;
    VendaView tela;
    ProdutoDAO produtoDao = new ProdutoDAO();
    ClienteDAO clienteDao = new ClienteDAO();
    
    public VendaController(Funcionario vendedor) {
        this.tela = new VendaView(vendedor);
        this.vendedor = vendedor;
        inicializarBotoes();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------                                     INICIALIZAÇÃO                                   -------------------------//
    
    public void exibirTela(){
        tela.exibirTela();
    }
    
    public void inicializarBotoes(){
        
        tela.addActionBotaoPesquisarProduto(e -> {pesquisarProduto();});
        
        tela.addActionBotaoAdicionarProduto(e -> {adicionarProdutoTabela();});
        
        tela.addActionBotaoRemoverProduto(e -> {removerProdutoTabela();});
        
        tela.addActionBotaoFecharPedido(e -> {fecharPedido();});
        
        tela.addActionBotaoPesquisarCliente(e ->{pesquisarCliente();});
        
        tela.addActionBotaoCancelarPedido(e -> {cancelarVenda();});
        
        tela.addActionBotaoFinalizarVenda(e -> {finalizarVenda();});
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
    
    private void pesquisarProdutoPorCodigo(String codigo) {
        try {
            Integer pCodigo = Integer.parseInt(codigo);
            
            Map<Integer, Produto> produtos = produtoDao.getProdutos().stream()
                .collect(Collectors.toMap(Produto::getCodigo, produto -> produto));
            
            for(Integer cod : produtos.keySet()){
               Produto p = produtos.get(cod);
                if(p.getCodigo() == pCodigo)
                    mostrarResultado(p);
            }
            
        }catch(NumberFormatException e){
           mensagem("Utilize números na sua pesquisa!");
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
        
    }
    
    private void removerProdutoTabela(){
        
    }
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------                                 MANIPULAÇÃO DA VENDA                                -------------------------//
    
    private void fecharPedido(){
        
    }
    
    private void cancelarVenda(){
        
    }
    
    private void finalizarVenda(){
    
    }
    
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- // 
//-------------------------                                      VALIDAÇÕES                                     -------------------------//

    



//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //       
//-------------------------                                      UTILIDADES                                     -------------------------//
    
    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(tela, mensagem);
    }
    
    private void limpaCampo(JTextField textField) {
        textField.setText("");
    }

    private void limpaCampo(JTextArea textArea) {
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

 }  
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //  
//---------------------------------------------------------------------------------------------------------------------------------------//
































//--------------------                                             Bloco                                             --------------------//




//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
