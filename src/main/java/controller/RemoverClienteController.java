/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDAO;
import exceptions.ClienteException;
import model.Cliente;
import view.RemoverClienteView;

/**
 *
 * @author gusdev0258
 */
public class RemoverClienteController implements Controller {

    RemoverClienteView removerClienteView;
    Cliente clienteModel;
    ClienteDAO clienteDAO;

    public RemoverClienteController() {
        if (removerClienteView == null) {
            removerClienteView = new RemoverClienteView();
        }
        inicializarBotoes();
    }
    
    @Override
    public void exibirTela() {
        removerClienteView.exibirTela();
    }
    
    @Override
    public void inicializarBotoes() {
        acaoRemover();
    }

    private void acaoRemover() {
        removerClienteView.adicionarAcaoRemover(e -> {
            boolean confirmarRemocao = removerClienteView.confirmarRemocao();
            if (confirmarRemocao) {
                try {
                remover();
                removerClienteView.mostrarAviso("Cliente removido do banco de dados", "Cliente Removido");
                }catch (ClienteException err){
                    removerClienteView.mostrarAviso(err.getMessage(), "Cliente não encontrado");
                }
                
            } else {
                removerClienteView.mostrarAviso("Operação cancelada", "Operação cancelada");
            }
        });
    }

    private void remover() throws ClienteException {
        clienteDAO = new ClienteDAO();
        String CPF = removerClienteView.getCPF();
        boolean removido = false;
            for (int i = 0; i < clienteDAO.getClientes().size(); i++) {
                Cliente cliente = clienteDAO.getClientes().get(i);
                if (cliente.getCPF().equals(CPF)) {
                    clienteDAO.removerCliente(cliente);
                    removido = true;
                }
            }
        if (!removido)
            throw new ClienteException("Cliente não existe");
    }
    
}
