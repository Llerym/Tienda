/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data   //Generar por dejabo los set y get
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    private String descripcion;
    private String detalle;
    private int existencias;
    private String rutaImagen;
    private double precio;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String descripcion, String detalle, int exitencias, String rutaImagen, double precio, boolean activo) {

        this.descripcion = descripcion;
        this.detalle = detalle;
        this.existencias = existencias;
        this.rutaImagen = rutaImagen;
        this.precio = precio;
        this.activo = activo;
    }

    


}
