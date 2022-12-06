/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CategoriaDAO;
import model.Categoria;
import repository.CategoriaRepository;
import view.CadastroCategoriaView;

/**
 *
 * @author gusdev0258
 */
public class CadastroCategoriaController implements Controller {
    CadastroCategoriaView cadCategoriaView;
    Categoria categoria;

    public CadastroCategoriaController(CadastroCategoriaView ccv, Categoria categoria) {
        this.cadCategoriaView = ccv;
        this.categoria = categoria;
        inicializarBotoes();
    }
    
    @Override
    public void inicializarBotoes(){
        cadCategoriaView.adicionarAcaoAoBotaoCadastrar(e -> CadastrarCategoria());
    }
    
    public void CadastrarCategoria(){
          try {
            int codigo = cadCategoriaView.getCodigo();
            String nome = cadCategoriaView.getNome();
            Categoria cat = new Categoria(codigo, nome);
            CategoriaRepository categoriaDAO = new CategoriaDAO();
            categoriaDAO.adicionarCategoria(cat);
            cadCategoriaView.limpaCampos();
            cadCategoriaView.mostrarAviso("Categoria Cadastrada Com Sucesso!","Categoria cadastrada");

        } catch (NumberFormatException err) {
            cadCategoriaView.limpaCampos();
            cadCategoriaView.mostrarAviso("Informe apenas números ao cadastrar a categoria","Informe números");
        }
    }
    
    @Override
    public void exibirTela(){
        cadCategoriaView.abrirTela();
    }
}
