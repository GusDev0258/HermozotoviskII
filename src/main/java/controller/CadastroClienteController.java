/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDAO;
import model.Cliente;
import repository.ClienteRepository;
import view.CadClienteView;

/**
 *
 * @author gusdev0258
 */
public class CadastroClienteController {
    CadClienteView cadClienteView;
    Cliente clienteModel;

    public CadastroClienteController(CadClienteView cadClienteView, Cliente clienteModel) {
        this.cadClienteView = cadClienteView;
        this.clienteModel = clienteModel;
        iniciaBotoes();
    }
    
    
    
    public void iniciaBotoes(){
        cadClienteView.adicionarAcaoAoBotaoCadastrar(e -> cadastrarCliente());
    }
    
    
    public void cadastrarCliente(){
        Cliente cliente = cadClienteView.getDadosCliente();
        
        ClienteRepository clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente);
        cadClienteView.limpaCampos();
        cadClienteView.exibirMensagem("Cliente cadastrado com sucesso");
        
    }
}
