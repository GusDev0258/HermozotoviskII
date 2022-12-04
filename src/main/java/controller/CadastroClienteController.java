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
public class CadastroClienteController implements Controller {
    CadClienteView cadClienteView;
    Cliente clienteModel;

    public CadastroClienteController(CadClienteView cadClienteView, Cliente clienteModel) {
        this.cadClienteView = cadClienteView;
        this.clienteModel = clienteModel;
        inicializarBotoes();  
    }
    
    @Override
    public void inicializarBotoes() {
        cadClienteView.adicionarAcaoAoBotaoCadastrar(e -> cadastrarCliente());
    }
    
    @Override
    public void exibirTela() {
        cadClienteView.exibirTela();
    }
    
    public void cadastrarCliente(){
        Cliente cliente = cadClienteView.getDadosCliente();
        
        ClienteRepository clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente);
        cadClienteView.limpaCampos();
        cadClienteView.exibirMensagem("Cliente cadastrado com sucesso");
        
    }

}
