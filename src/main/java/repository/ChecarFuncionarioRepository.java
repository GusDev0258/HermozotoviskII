/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author luizportel4
 */
public interface ChecarFuncionarioRepository extends PessoaRepository {
    public boolean checarSenha(String senha);
}
