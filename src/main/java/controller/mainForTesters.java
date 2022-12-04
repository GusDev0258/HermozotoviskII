package controller;

import model.Vendedor;

/**
 *
 * @author ndeba
 */
public class mainForTesters {
    
    public static void main(String[] args) {
        Vendedor v = new Vendedor("Arlindo", "12", "123");
        VendaViewController main = new VendaViewController(v);
        
        main.exbirTela();
    }
    
}
