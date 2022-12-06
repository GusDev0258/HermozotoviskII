/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.AdminView;
import view.CadClienteView;
import view.CadastroCategoriaView;
import view.CadastroProdutoView;
import view.RelatorioClienteView;
import view.RelatorioProdutoView;
import view.RelatorioVendasView;
import dao.ClienteDAO;
import view.RemoverProdutoView;

/**
 *
 * @author gusdev0258
 */
public class AdminController implements Controller {
  AdminView telaInicial;
  CadastroCategoriaController cadCategoriaController;
  CadastroProdutoController cadProdutoController;

    public AdminController() {
        if (telaInicial == null)
            telaInicial = new AdminView();
        this.cadCategoriaController = cadCategoriaController;
        this.cadProdutoController = cadProdutoController;
        inicializarBotoes();
        popularClientes();
    }
    
       private void popularClientes() {
           ClienteDAO clienteDAO = new ClienteDAO();
           clienteDAO.populateCliente();
       }

  public void inicializarBotoes(){
     telaInicial.adicionarAcaoAoItemMenuCadastrarCategoria(e -> abrirCadastroCategoria());
     
     telaInicial.adicionarAcaoAoItemMenuCadastrarProduto(e -> abrirCadastroProduto());
     
     telaInicial.adicionarAcaoAoItemMenuRelatorioCliente(e -> abrirRelatorioCliente());
     
     telaInicial.adicionarAcaoAoItemMenuRelatorioProduto(e -> abrirRelatorioProduto());
     
     telaInicial.adicionarAcaoAoItemMenuRemoverCliente(e -> abrirRemoverCliente());
     
     telaInicial.adicionarAcaoAoItemMenuRelatorioVenda(e -> abrirRelatorioVendas());
     
     telaInicial.adicionarAcaoAoItemMenuRemoverProduto(e -> abrirRemoverProduto());
  }
  
  public void abrirCadastroCategoria(){
      new CadastroCategoriaController(new CadastroCategoriaView(), null).exibirTela();
  }
  
  public void abrirCadastroProduto(){
      new CadastroProdutoController(new CadastroProdutoView(), null).abrirTela();
  }
  
  public void abrirRelatorioCliente(){
      new RelatorioClienteController(new RelatorioClienteView()).abrirTela();
  }
  
  public void abrirRelatorioProduto(){
      new RelatorioProdutoController(new RelatorioProdutoView()).exibirTela();
  }
  
  public void abrirRemoverCliente(){
      new RemoverClienteController().exibirTela();
  }
  
  public void abrirRelatorioVendas(){
      new RelatorioVendaController(new RelatorioVendasView()).exibirTela();
  }
  
  public void abrirRemoverProduto(){
      new RemoverProdutoController(new RemoverProdutoView()).exibirTela();
  }
  
  public void exibirTela(){
      telaInicial.exibirTela();
  }
}
