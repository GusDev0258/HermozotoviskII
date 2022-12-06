/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;
import repository.ProdutoRepository;
import view.CadastroProdutoView;

/**
 *
 * @author gusdev0258
 */
public class CadastroProdutoController {
    CadastroProdutoView cadProdutoView;
    Produto produto;

    public CadastroProdutoController(CadastroProdutoView cadProdutoView, Produto produto) {
        this.cadProdutoView = cadProdutoView;
        this.produto = produto;
        iniciaBotoes();
    }
    
        public void iniciaBotoes(){
            cadProdutoView.adicionarAcaoAoBotaoCadastrar(e -> cadastraProduto());
        }
    
        private void cadastraProduto(){
        String nome = cadProdutoView.getNomeProduto();
        int quantidade = cadProdutoView.getQuantidadeProduto();
        String descricao = cadProdutoView.getDescricaoProduto();
        Categoria categoriaProduto = cadProdutoView.getCategoriaProduto();
        Double preco = cadProdutoView.getPrecoProduto();
        ProdutoRepository produtoDAO = new ProdutoDAO();
        
        Produto aux = null;
        for(int i = 0; i < produtoDAO.getProdutos().size(); i++){
            if(produtoDAO.getProdutos().get(i).getNome().equals(nome)){
                aux = produtoDAO.getProdutos().get(i);
            }
        }
        
        if(aux != null){
        cadProdutoView.mostrarAviso("Produto já existe!","Produto repetido");
        cadProdutoView.limpaCampos();
        }else{
        Produto produto;
        if (cadProdutoView.getQuantidadeProduto() == 0)
            produto = new Produto(nome, preco, descricao, categoriaProduto);
        else
            produto = new Produto(nome, preco, descricao, categoriaProduto, quantidade);
        produtoDAO.addProduto(produto);
        
        cadProdutoView.geraModel();
        cadProdutoView.mostrarAviso("Produto Criado com Sucesso!","Produto cadastrado");
        cadProdutoView.limpaCampos();
        cadProdutoView.generateList();
        }
        }
        
        public void abrirTela(){
            cadProdutoView.abrirTela();
        }
}
