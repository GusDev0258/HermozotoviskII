/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import repository.CategoriaRepository;

/**
 *
 * @author gusdev0258
 */
public class CategoriaDAO implements CategoriaRepository{
    public static List<Categoria> categorias = new ArrayList<>();
    @Override
    public List<Categoria> retornarCategorias() {
        return categorias;
    }

    @Override
    public void adicionarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    @Override
    public void removerCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }
    
}
