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
    RelatorioProdutoView relatorioProdutoView;

    public RelatorioProdutoController(RelatorioProdutoView relatorioProdutoView) {
        this.relatorioProdutoView = relatorioProdutoView;
        iniciaBotoes();
    }
    
    public void iniciaBotoes(){
        relatorioProdutoView.adicionarAcaoAoBotaoBuscarCategoria(e -> buscarProdutosPorCategoria());
        relatorioProdutoView.adicionarAcaoAoBotaoBuscarNome(e -> buscarProdutosPorNome());
    }
    
    public void buscarProdutosPorNome(){
            try{
            String nomeBuscado = relatorioProdutoView.getProdutoBuscado();
            List<Produto> prods = buscarProdutos(nomeBuscado);
            if(prods != null){
            relatorioProdutoView.limpaResultado();
            for(Produto p: prods){
               relatorioProdutoView.atribuirValorAoTextAreaProdutos(p.toString() + "\n"); 
            }
            relatorioProdutoView.limpaBusca();
        }
        }catch(NullPointerException ex){
            relatorioProdutoView.mostrarAviso("Produto não consta no sistema", "Produto não encontrado");
        }
    }
    
    public List<Produto> buscarProdutos(String produtoNome){
        List<Produto> produtos = new ArrayList<>();
        for(Produto produto: ProdutoDAO.produtos){
            if(produto.getNome().contains(produtoNome)){
                produtos.add(produto);
            }
        }
        return produtos;
    }
    
    public void buscarProdutosPorCategoria(){
        getCategoriaBuscada();
        for(Produto produto: ProdutoDAO.produtos){
            try{
            if(produto.getCategoria() == getCategoriaBuscada()){
                relatorioProdutoView.atribuirValorAoTextAreaCategorias(produto.toString() + "\n");
                }
            }catch(NullPointerException ex){
                relatorioProdutoView.mostrarAviso("Categoria não encontrada!", "Categoria não encontrada");
            }
        }
        relatorioProdutoView.limpaResultado();
    }
     public Categoria getCategoriaBuscada(){
        for(Categoria categoria: CategoriaDAO.categorias){
            try{
            if(categoria.getNome().equals(relatorioProdutoView.getCategoria())){
                return categoria;
                }
            }catch(NullPointerException ex){
                relatorioProdutoView.mostrarAviso("Categoria não encontrada!", "Categoria não encontrada");
            }
        }
        return null;
    }
     
     public void exibirTela(){
         relatorioProdutoView.abrirTela();
     }
  }

