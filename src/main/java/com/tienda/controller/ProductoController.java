package com.tienda.controller;

import com.tienda.domain.Producto;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Producto")
public class ProductoController {
    
    
    @Autowired
    private ProductoService ProductoService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        
        var lista = ProductoService.getProductos(false);
        
        model.addAttribute("Productos", lista); 
        
        return "/Producto/listado"; 
    }
    
    @PostMapping("/guardar")
    public String ProductoGuardar(Producto Producto) {  
        ProductoService.save(Producto);
        return "redirect:/Producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String ProductoEliminar(Producto Producto) {
        ProductoService.delete(Producto);
        return "redirect:/Producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String ProductoModificar(Producto Producto, Model model) {
        Producto = ProductoService.getProducto(Producto);
        model.addAttribute("Producto", Producto);
        return "redirect:/Producto/listado";
    }
    
    
}