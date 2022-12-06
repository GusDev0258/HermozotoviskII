/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import repository.ClienteRepository;

/**
 *
 * @author luizportel4
 */
public class ClienteDAO implements ClienteRepository{
    public static List<Cliente> clientes = new ArrayList<>();   

    public ClienteDAO() {
        
    }
       
    @Override
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public Cliente getClienteCPF(String cpf) {
        for (Cliente cliente : clientes) {
            if(cliente.getCPF().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
    
    @Override
    public void removerCliente(Cliente cliente){
        clientes.remove(cliente);
    }
    
    public void populateCliente() {
        Cliente[] clientesS = {
            new Cliente("João Silva", "2112", "Rua IX"),
            new Cliente("João Silva", "2223", "Rua X"),
            new Cliente("Geroncio Gerundido Geraldo Gerarmino da Silva", "7632", "Rua XI")
        };
        for (Cliente cliente : clientesS) {
            clientes.add(cliente);
        }
    }
    
}
