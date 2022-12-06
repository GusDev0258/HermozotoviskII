/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.VendaDAO;
import exceptions.VendaNaoEncontradaException;
import java.util.Collections;
import java.util.List;
import model.Venda;
import repository.VendaRepository;
import view.RelatorioVendasView;

/**
 *
 * @author gusdev0258
 */
public class RelatorioVendaController {
    
    RelatorioVendasView relatorioDeVendas;
    VendaRepository vendaDAO = new VendaDAO();
    Venda venda;

    public RelatorioVendaController(RelatorioVendasView relatorioDeVendas) {
        this.relatorioDeVendas = relatorioDeVendas;
        populaVendas();
        inicializarBotoes();
    }
    
    public void inicializarBotoes(){
        relatorioDeVendas.adicionarAcaoAoBotaoPesquisar(e -> resultadoPesquisa());
    }
    
    
    private void populaVendas(){
        String totalVendas = "";
        List<Venda> vendas = this.vendaDAO.getVendas();
        Collections.sort(vendas, venda);
        for(Venda vend : vendaDAO.getVendas()){
            relatorioDeVendas.atribuirValorAListaDeVendas(vend.toString());
            totalVendas += vend.getValorTotal();
        }
        relatorioDeVendas.atribuirValorAoTotalDeVendas(totalVendas);
    }
    private Venda retornaVendaPesquisada()throws VendaNaoEncontradaException{
         int codigo = relatorioDeVendas.getCodigoPesquisado();
        for(Venda vend: vendaDAO.getVendas()){
            if(codigo == vend.getCodigo()){
                return vend;        
                }
            }
        return null;
        }
    
    private void resultadoPesquisa(){
        try{
        if(retornaVendaPesquisada() != null){    
           retornaVendaPesquisada();
           relatorioDeVendas.atribuirValorAListaDeVendasPesquisada(retornaVendaPesquisada().toString());
        }
        }catch(VendaNaoEncontradaException ex){
            relatorioDeVendas.showWarnMessage("Venda não declarada", "Venda nao encontrada");
        }
    }
    
    public void exibirTela(){
        relatorioDeVendas.abrirTela();
    }
}
