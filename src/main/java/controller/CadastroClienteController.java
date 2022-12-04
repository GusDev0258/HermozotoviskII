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
    CadClienteView ccv;
    Cliente c;

    public CadastroClienteController(CadClienteView ccv, Cliente c) {
        this.ccv = ccv;
        this.c = c;
        iniciaBotoes();
    }
    
    
    
    public void iniciaBotoes(){
        ccv.adicionarAcaoAoBotaoCadastrar(e -> cadastrarCliente());
    }
    
    
    public void cadastrarCliente(){
        String nome = ccv.getNome();
        String cpf = ccv.getCPF();
        String endereco = ccv.getEndereco();
        Cliente c = new Cliente(nome, cpf, endereco);
        
        ClienteRepository clienteDAO = new ClienteDAO();
        clienteDAO.addCliente(c);
        ccv.limpaCampos();
        ccv.exibirMensagem("Cliente cadastrado com sucesso");
        
    }
}
