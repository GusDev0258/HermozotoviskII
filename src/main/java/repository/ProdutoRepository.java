package repository;

import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author luizportel4
 */
public interface ProdutoRepository {
    
    public void addProduto(Produto produto);
    
    public List<Produto> getProdutos();
}
