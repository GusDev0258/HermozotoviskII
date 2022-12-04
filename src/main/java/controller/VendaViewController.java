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
 * @author ndeba
 */
public class VendaViewController {
    
    Funcionario vendedor;
    VendaView tela;
    ProdutoDAO produtoDao = new ProdutoDAO();
    ClienteDAO clienteDao = new ClienteDAO();
    
    public VendaViewController(Funcionario vendedor) {
        this.tela = new VendaView(vendedor);
        this.vendedor = vendedor;
        inicializarBotoes();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------                                     Inicialização                                   -------------------------//
    
    public void exbirTela(){
        tela.exibirTela();
    }
    
    public void inicializarBotoes(){
        
        tela.addActionBotaoPesquisarProduto(e -> {pesquisarProduto();});
        
        tela.addActionBotaoAdicionarProduto(e -> {});
        
        tela.addActionBotaoRemoverProduto(e -> {});
        
        tela.addActionBotaoFecharPedido(e -> {});
        
        tela.addActionBotaoPesquisarCliente(e ->{});
        
        tela.addActionBotaoCancelarPedido(e -> {});
        
        tela.addActionBotaoFinalizarVenda(e -> {});
    }

//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------                                 Manipulação da tabela                               -------------------------//
    
    
    
    
    
    
    
//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//-------------------------Metodos de pesquisa-------------------------//
    
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
    
    public void pesquisarProdutoPorNome(String nome) {
        for (Produto p : produtoDao.getProdutos()) {
            if(p.getNome().toLowerCase().contains(nome.toLowerCase()))
                mostrarResultado(p);
        }
    }
    
    public void pesquisarProdutoPorCodigo(String codigo) {
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
           mensagem("");
        }
    }
    
    public void pesquisarProduto(){
        
    }
    
    public void pesquisarClientePorNome(String nome) {
        for (Cliente c : clienteDao.getClientes()) {
            if(c.getNome().toLowerCase().contains(nome.toLowerCase()))
                mostrarResultado(c);
        }
    }

 
    public void pesquisarClientePorCPF(String CPF) {
        Set<Cliente> clientes = new HashSet<>();
        clientes.addAll(clienteDao.getClientes());

        for (Cliente c : clientes) {
            if (c.getCPF().contains(CPF)) {
                mostrarResultado(c);
            }
        }
    }

//-------------------------                                         end                                         -------------------------//
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //   
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
//--------------------                                             Utilities                                         --------------------//
    
    public void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(tela, mensagem);
    }

    
    public void limpaCampo(JTextField textField) {
        textField.setText("");
    }

    public void limpaCampo(JTextArea textArea) {
        textArea.setText("");
    } 
}
