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
    RelatorioClienteView rcl;
    Cliente cliente;

    public RelatorioClienteController(RelatorioClienteView rcl) {
        this.rcl = rcl;
        iniciaLista();
    }
    
    public void iniciaLista(){
        rcl.populaListaDeClientes();
    }
    
    public void abrirTela(){
        rcl.abrirTela();
    }
}
