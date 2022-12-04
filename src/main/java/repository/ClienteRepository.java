/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import exceptions.ClienteException;
import java.util.List;
import model.Cliente;

/**
 *
 * @author luizportel4
 */
public interface ClienteRepository {
    public void adicionarCliente(Cliente cliente);
    public List<Cliente> getClientes();
    public Cliente getClienteCPF(String cpf);
    public void removerCliente(Cliente cliente) throws ClienteException;
}
