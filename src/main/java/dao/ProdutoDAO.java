package dao;

import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Produto;
import repository.ProdutoRepository;

/**
 *
 * @author luizportel4
 */
public class ProdutoDAO implements ProdutoRepository{
    public static List<Produto> produtos = new ArrayList<>();
    static int valid = 0;
    public ProdutoDAO(){
        if (valid == 0)   
            populateProdutos();
       
        valid++;
    }
    @Override
    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
    @Override
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void removeProduto(int i){
        produtos.remove(i);
    }
    public void removeProduto(Produto prod){
        produtos.remove(prod);
    }

//    private void populateProdutos() { //caso queira adicionar algo, copie isso: new Produto("", 1000.00, "", "", 0),
//        Produto[] produtos = {
//            new Produto("Processador", 800.00, "Processador intel quase derretido do uso.", "CPUs", 30),
//            new Produto("Placa de vídeo", 3500.00, "Placa de vídeo que roda campo minado.", "GPUs", 31),
//            new Produto("Monitor", 1000.00, "Tela que machuca o olho", "Periféricos", 567),
//            new Produto("Teclado gamer", 300.00, "Teclado que não tem todas as teclas que vc precisa, porém brilha.", "Periféricos", 64),
//            new Produto("Mouse Gamer", 600.00, "Mouse que faz tudo que um mouse normal faz, só que brilha", "Periféricos", 873),
//            new Produto("Cadeira Gamer", 1200.00, "Cadeira bonita que machuca as costas", "Escritório", 23),
//            new Produto("Cadeira de Escritório", 2000, "Cadeira feia que machuca as costas A LONGO PRAZO", "Escritório", 15),
//        };
//        for (Produto p : produtos) {
//            this.produtos.add(p);
//        }
//    }

    private void populateProdutos() { //caso queira adicionar algo, copie isso: new Produto("", 1000.00, "", "", 0),
        Produto[] produtos = {
            new Produto("Processador", 800.00, "Processador intel quase derretido do uso.", new Categoria(1,"teste"), 30),
            new Produto("Placa de vídeo", 3500.00, "Placa de vídeo que roda campo minado.", new Categoria(2,"teste"), 31),
            new Produto("Monitor", 1000.00, "Tela que machuca o olho", new Categoria(3,"teste"), 567),
            new Produto("Teclado gamer", 300.00, "Teclado que não tem todas as teclas que vc precisa, porém brilha.", new Categoria(4,"teste"), 64),
            new Produto("Mouse Gamer", 600.00, "Mouse que faz tudo que um mouse normal faz, só que brilha", new Categoria(5,"teste"), 873),
            new Produto("Cadeira Gamer", 1200.00, "Cadeira bonita que machuca as costas", new Categoria(6,"teste"), 23),
            new Produto("Cadeira de Escritório", 2000, "Cadeira feia que machuca as costas A LONGO PRAZO", new Categoria(7,"teste"), 15),
        };
        for (Produto p : produtos) {
            this.produtos.add(p);
        }
   }
}
