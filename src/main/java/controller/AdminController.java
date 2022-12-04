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
import view.RemoverClienteView;
import view.RemoverProdutoView;
import view.VendaView;

/**
 *
 * @author gusdev0258
 */
public class AdminController {
  AdminView telaInicial;
  CadastroCategoriaController cadCategoriaController;
  CadastroProdutoController cadProdutoController;

    public AdminController() {
        if (telaInicial == null)
            telaInicial = new AdminView();
        this.cadCategoriaController = cadCategoriaController;
        this.cadProdutoController = cadProdutoController;
        iniciaBotoes();
    }

  public void iniciaBotoes(){
     telaInicial.adicionarAcaoAoItemMenuCadastrarCategoria(e -> {
     new CadastroCategoriaController(new CadastroCategoriaView(), null).abrirTelaCadastroCategoria();
     });
     telaInicial.adicionarAcaoAoItemMenuCadastrarProduto(e ->{
     new CadastroProdutoController(new CadastroProdutoView(), null).abrirTela();
     });
  }
  
//  public void abrirCadastroDeCategoria(){
//      new CadastroCategoriaController(new CadastroCategoriaView(), null).abrirTelaCadastroCategoria();
//  }
//  public void abrirCadastroDeProduto(){
//      CadastroProdutoView cpv = new CadastroProdutoView();
//      cpv.abrirTela();
//  }
  public void abrirCadastroDeCliente(){
      CadClienteView cadClienteView= new CadClienteView();
      cadClienteView.abrirTela();
  }
  public void abrirRelatorioClienteView(){
      RelatorioClienteView rcv = new RelatorioClienteView();
      rcv.abrirTela();
  }
//  public void abrirRelatorioProdutoView(){
//      RelatorioProdutoView rpv = new RelatorioProdutoView();
//      rpv.abrirTela();
//  }
  public void abrirRelatorioVendasView(){
      RelatorioVendasView relatorioVendasView = new RelatorioVendasView();
      relatorioVendasView.abrirTela();
  }
  public void abrirRemoverClienteView(){
      RemoverClienteView removerClienteView = new RemoverClienteView();
      removerClienteView.abrirTela();
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
