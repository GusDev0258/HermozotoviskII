/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Funcionario;
import model.Pessoa;
import model.Vendedor;
import repository.FuncionarioRepository;

/**
 *
 * @author luizportel4
 */
public class FuncionarioDAO implements FuncionarioRepository {
    public static List<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioDAO() {
        this.popularAdmin();
        this.popularVendedor();
    }

    @Override
    public void addFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    @Override
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
     
    public Funcionario buscaFuncionario(String cpf, String senha) {
        for (Funcionario funcionario : funcionarios) {
            if(cpf.equals(funcionario.getCPF()) && funcionario.checarSenha(senha)){
                return funcionario;
            }
        }
        return null;
    }    
  
    private void popularAdmin() {
        Admin[] admins = {
            new Admin("Gustavo", "01", "123"),
            new Admin("Luiz", "02", "123"),
            new Admin("Nicolas", "03", "123")
        };
        for (Admin admin : admins) {
            funcionarios.add(admin);
        }
    }
    
    private void popularVendedor() {
        Vendedor[] vendors = {
            new Vendedor("Josias", "11", "123"),
            new Vendedor("Geroncia", "12", "123"),
        };
        for (Vendedor v : vendors) {
            funcionarios.add(v);
        }
    }
}
