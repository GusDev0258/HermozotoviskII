/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FuncionarioDAO;
import model.Admin;
import model.Funcionario;
import model.Vendedor;
import view.AdminView;
import view.LoginView;

/**
 *
 * @author gusdev0258
 */
public class LoginController {
    private LoginView loginView;
    private FuncionarioDAO funcionarioDAO;
    
    public LoginController() {
        if (loginView == null)
            loginView = new LoginView();
        carregarBotoes();
    }
    
    public void exibirTela() {
        loginView.exibirTela();
    }
    
    public void carregarBotoes() {
        loginView.adicionarAcaoLogin(e -> acaoLogin());
    }
    
    public void acaoLogin() {
        Funcionario funcionario = buscaFuncionario();
        if (funcionario == null) {
            loginView.mostrarMensagem("CPF e/ou senha incorrentos ou o funcionário não existe.");
        } else if (funcionario instanceof Vendedor) {
            VendaController vendaViewController = new VendaController(funcionario);
            vendaViewController.exibirTela();
        } else if (funcionario instanceof Admin) {
            AdminController adminController = new AdminController();
            adminController.exibirTela();
        }
        else {
            loginView.mostrarMensagem("Usuário nâo encontrado");
        }
    }
    
    public Funcionario buscaFuncionario() {
        String CPF = loginView.getCPF();
        String senha = loginView.getSenha();
        funcionarioDAO = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.buscaFuncionario(CPF, senha);
        
        return funcionario;
    }
    
}
