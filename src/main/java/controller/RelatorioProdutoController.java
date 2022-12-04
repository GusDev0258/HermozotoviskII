/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Produto;
import view.RelatorioProdutoView;

/**
 *
 * @author gusdev0258
 */
public class RelatorioProdutoController {
    RelatorioProdutoView relatorioDeProdutos;

    public RelatorioProdutoController(RelatorioProdutoView relatorioDeProdutos) {
        this.relatorioDeProdutos = relatorioDeProdutos;
        iniciaBotoes();
    }
    
    public void iniciaBotoes(){
        relatorioDeProdutos.adicionarAcaoAoBotaoBuscarCategoria(e -> buscarProdutosPorCategoria());
        relatorioDeProdutos.adicionarAcaoAoBotaoBuscarNome(e -> buscarProdutosPorNome());
    }
    
    public void buscarProdutosPorNome(){
            try{
            String nomeBuscado = relatorioDeProdutos.getProdutoBuscado();
            List<Produto> prods = buscarProdutos(nomeBuscado);
            if(prods != null){
            relatorioDeProdutos.limpaResultado();
            for(Produto p: prods){
               relatorioDeProdutos.atribuirValorAoTextAreaProdutos(p.toString() + "\n"); 
            }
            relatorioDeProdutos.limpaBusca();
        }
        }catch(NullPointerException ex){
            relatorioDeProdutos.exibirMensagem("Produto não consta no sistema");
        }
    }
    
    public List<Produto> buscarProdutos(String produtoNome){
        List<Produto> produtinhos = new ArrayList<>();
        for(Produto prod: ProdutoDAO.produtos){
            if(prod.getNome().contains(produtoNome)){
                produtinhos.add(prod);
            }
        }
        return produtinhos;
    }
    
    public void buscarProdutosPorCategoria(){
        getCategoriaBuscada();
        for(Produto prod: ProdutoDAO.produtos){
            try{
            if(prod.getCategoria() == getCategoriaBuscada()){
                relatorioDeProdutos.atribuirValorAoTextAreaCategorias(prod.toString() + "\n");
                }
            }catch(NullPointerException ex){
                relatorioDeProdutos.exibirMensagem("Categoria não encontrada!");
            }
        }
        relatorioDeProdutos.limpaResultado();
    }
     public Categoria getCategoriaBuscada(){
        for(Categoria cat: CategoriaDAO.categorias){
            try{
            if(cat.getNome().equals(relatorioDeProdutos.getCategoria())){
                return cat;
                }
            }catch(NullPointerException ex){
                relatorioDeProdutos.exibirMensagem("Categoria não encontrada!");
            }
        }
        return null;
    }
     
     public void exibirTela(){
         relatorioDeProdutos.abrirTela();
     }
  }

