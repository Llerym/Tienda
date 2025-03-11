/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import java.util.List;
import com.tienda.domain.Producto;

public interface ProductoService {

    public List<Producto> getProductos(boolean activos);
    
    public Producto getProducto(Producto categoria);
    
    public void save(Producto categoria);
    
    public void delete(Producto categoria);
}
