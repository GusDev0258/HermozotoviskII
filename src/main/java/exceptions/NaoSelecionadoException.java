/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author luizportel4
 */
public class NaoSelecionadoException extends Exception {
    public NaoSelecionadoException(String campo) {
        super("campo " + campo + " não foi selecionado!");
    }
    
}
