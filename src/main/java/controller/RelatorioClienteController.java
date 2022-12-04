/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Cliente;
import view.RelatorioClienteView;

/**
 *
 * @author gusdev0258
 */
public class RelatorioClienteController {
    RelatorioClienteView relatorioClienteView;
    Cliente cliente;

    public RelatorioClienteController(RelatorioClienteView relatorioClienteView) {
        this.relatorioClienteView = relatorioClienteView;
        iniciaLista();
    }
    
    public void iniciaLista(){
        relatorioClienteView.populaListaDeClientes();
    }
    
    public void abrirTela(){
        relatorioClienteView.abrirTela();
    }
}
