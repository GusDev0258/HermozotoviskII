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
     telaInicial.adicionarAcaoAoItemMenuCadastrarCategoria(e -> {
     new CadastroCategoriaController(new CadastroCategoriaView(), null).exibirTela();
     });
     telaInicial.adicionarAcaoAoItemMenuCadastrarProduto(e ->{
     new CadastroProdutoController(new CadastroProdutoView(), null).abrirTela();
     });
     telaInicial.adicionarAcaoAoItemMenuRelatorioCliente(e -> {
     new RelatorioClienteController(new RelatorioClienteView()).abrirTela();
     });
     telaInicial.adicionarAcaoAoItemMenuRelatorioProduto(e ->{
     new RelatorioProdutoController(new RelatorioProdutoView()).exibirTela();
     });
     telaInicial.adicionarAcaoAoItemMenuRemoverCliente(e -> {
         new RemoverClienteController().exibirTela();
     });
  }
  
  public void abrirCadastroDeCliente(){
      CadClienteView cadClienteView= new CadClienteView();
      cadClienteView.abrirTela();
  }
//  public void abrirRelatorioProdutoView(){
//      RelatorioProdutoView rpv = new RelatorioProdutoView();
//      rpv.abrirTela();
//  }
  public void abrirRelatorioVendasView(){
      RelatorioVendasView relatorioVendasView = new RelatorioVendasView();
      relatorioVendasView.abrirTela();
  }

  public void abrirRemoverProdutoView(){
      RemoverProdutoView removerProdutoView = new RemoverProdutoView();
      removerProdutoView.exibirTela();
  }
//  public void abrirVendaView(){
//      VendaView vv = new VendaView();
//      vv.abrirTela();
//  }
  
  public void exibirTela(){
      telaInicial.exibirTela();
  }
}
