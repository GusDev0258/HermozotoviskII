/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import repository.PessoaRepository;

/**
 *
 * @author luizportel4
 */
public abstract class Pessoa implements PessoaRepository {
    protected String nome;
    protected String cpf;
    protected String senha;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public abstract String getNome();

    @Override
    public abstract String getCPF();

    @Override
    public abstract void setNome(String nome);
}
