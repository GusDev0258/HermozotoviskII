/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CategoriaDAO;
import model.Categoria;
import repository.CategoriaRepository;
import utils.Limpador;
import utils.Mensagem;
import view.CadastroCategoriaView;

/**
 *
 * @author gusdev0258
 */
public class CadastroCategoriaController {
    CadastroCategoriaView ccv;
    Categoria categoria;

    public CadastroCategoriaController(CadastroCategoriaView ccv, Categoria categoria) {
        this.ccv = ccv;
        this.categoria = categoria;
        iniciaBotoes();
    }
    
    public void iniciaBotoes(){
        ccv.adicionarAcaoAoBotaoCadastrar(e -> CadastrarCategoria());
    }
    
    public void CadastrarCategoria(){
          try {
            int codigo = ccv.getCodigo();
            String nome = ccv.getNome();
            Categoria cat = new Categoria(codigo, nome);
            CategoriaRepository categoriaDAO = new CategoriaDAO();
            categoriaDAO.adicionarCategoria(cat);
            ccv.limpaCampos();
            ccv.exibirMensagem("Categoria Cadastrada Com Sucesso!");

        } catch (NumberFormatException err) {
            ccv.limpaCampos();
            ccv.exibirMensagem("Informe apenas números ao cadastrar a categoria");
        }
    }
    
    public void abrirTelaCadastroCategoria(){
        ccv.abrirTela();
    }
}
