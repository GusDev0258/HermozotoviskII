/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProdutoDAO;
import exceptions.ProdutoException;
import view.RemoverProdutoView;

/**
 *
 * @author gusdev0258
 */
public class RemoverProdutoController {
   RemoverProdutoView removerProdutoEstoque;
   ProdutoDAO pDAO = new ProdutoDAO();

    public RemoverProdutoController(RemoverProdutoView removerProdutoEstoque) {
        this.removerProdutoEstoque = removerProdutoEstoque;
        iniciaBotoes();
    }
   
   public void iniciaBotoes(){
       removerProdutoEstoque.adicionarAcaoAoBotaoRemover(e -> remocao());
   }
   
   private void remocao(){
       boolean removeProduto = removerProdutoEstoque.returnConfirmation();
       if(removeProduto){
           try{
               removerProduto();
               removerProdutoEstoque.showMessage("Produto removido com sucesso!", "Produto Removido");
           }catch(ProdutoException ex){
               removerProdutoEstoque.showWarnMessage(ex.getMessage(), "Erro na procura do produto");
           }
       }else{
           removerProdutoEstoque.showMessage("Operacao Cancelada", "Operacao Cancelada");
       }
   }
   
   
   private void removerProduto() throws ProdutoException{
       int codigo = removerProdutoEstoque.getCodigo();
       boolean removido = false;
       for(int i = 0; i < pDAO.getProdutos().size(); i++){
            if(pDAO.getProdutos().get(i).getCodigo() == codigo){
                pDAO.removeProduto(i);
                removido = true;
            }
        }
       if(removido == false){
           throw new ProdutoException("Produto não encontrado no sistema");
       }
   
    }
   public void exibirTela(){
       removerProdutoEstoque.exibirTela();
   }
}
