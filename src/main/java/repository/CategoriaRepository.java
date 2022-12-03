/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.Categoria;

/**
 *
 * @author gusdev0258
 */
public interface CategoriaRepository {
    List<Categoria> retornarCategorias();
    void adicionarCategoria(Categoria categoria);
    void removerCategoria(Categoria categoria);
}
