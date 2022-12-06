/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author gusdev0258
 */
public class ProdutoException extends Exception{

    public ProdutoException(String message) {
        super("Produto nao encontrado");
    }
    
}
