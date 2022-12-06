package exceptions;

/**
 *
 * @author ndeba
 */
public class ValorInvalidoException extends Exception{
    public ValorInvalidoException() {
        super("Insira somente números!");
    }
}
