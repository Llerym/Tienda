/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tienda.service.impl;

/**
 *
 * @author llech
 */
@Service

public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    rpivate CategoriaDao categoriaDao;
    Public List<Categoria> getCategorias(boolean activos){
        var lista = categoriaDAo.findAll();
        
        if(activos){
            lista.removeif(c-> !c.isActuve()):
        }
        
        return lista;
    }
}

