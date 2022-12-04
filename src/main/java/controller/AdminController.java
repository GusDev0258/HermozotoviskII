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
  CadastroCategoriaController ccc;
  CadastroProdutoController cpc;

    public AdminController(AdminView telaInicial) {
        this.telaInicial = telaInicial;
        this.ccc = ccc;
        this.cpc = cpc;
        iniciaBotoes();
    }

  public void iniciaBotoes(){
     telaInicial.adicionarAcaoAoItemMenuCadastrarCategoria(e -> {
     new CadastroCategoriaController(new CadastroCategoriaView(), null).abrirTelaCadastroCategoria();
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
  }
  
  public void abrirCadastroDeCliente(){
      CadClienteView cclv= new CadClienteView();
      cclv.abrirTela();
  }
//  public void abrirRelatorioProdutoView(){
//      RelatorioProdutoView rpv = new RelatorioProdutoView();
//      rpv.abrirTela();
//  }
  public void abrirRelatorioVendasView(){
      RelatorioVendasView rvv = new RelatorioVendasView();
      rvv.abrirTela();
  }
  public void abrirRemoverClienteView(){
      RemoverClienteView rclv = new RemoverClienteView();
      rclv.abrirTela();
  }
  public void abrirRemoverProdutoView(){
      RemoverProdutoView rpv = new RemoverProdutoView();
      rpv.abrirTela();
  }
//  public void abrirVendaView(){
//      VendaView vv = new VendaView();
//      vv.abrirTela();
//  }
  
  public void abrirTela(){
      telaInicial.abrirTela();
  }
}
