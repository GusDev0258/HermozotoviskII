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
    CadastroProdutoView cpv;
    Produto prod;

    public CadastroProdutoController(CadastroProdutoView cpv, Produto prod) {
        this.cpv = cpv;
        this.prod = prod;
        iniciaBotoes();
    }
    
        public void iniciaBotoes(){
            cpv.adicionarAcaoAoBotaoCadastrar(e -> cadastraProduto());
        }
    
        public void cadastraProduto(){
        String nome = cpv.getNomeProduto();
        int quantidade = cpv.getQuantidadeProduto();
        String descricao = cpv.getDescricaoProduto();
        Categoria categoriaProduto = cpv.getCategoriaProduto();
        Double preco = cpv.getPrecoProduto();
        ProdutoRepository pDAO = new ProdutoDAO();
        
        Produto aux = null;
        for(int i = 0; i < pDAO.getProdutos().size(); i++){
            if(pDAO.getProdutos().get(i).getNome().equals(nome)){
                aux = pDAO.getProdutos().get(i);
            }
        }
        
        if(aux != null){
        cpv.exibeMensagem("Produto já existe!");
        cpv.limpaCampos();
        }else{
        Produto prod;
        if (cpv.getQuantidadeProduto() == 0)
            prod = new Produto(nome, preco, descricao, categoriaProduto);
        else
            prod = new Produto(nome, preco, descricao, categoriaProduto, quantidade);
        pDAO.addProduto(prod);
        
        cpv.geraModel();
        cpv.exibeMensagem("Produto Criado com Sucesso!");
        cpv.limpaCampos();
        cpv.generateList();
        }
        }
        
        public void abrirTela(){
            cpv.abrirTela();
        }
}
