/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.tienda.domain;
import jakarta.persitence.*;
import lombok.Data;


/**
 *
 * @author llech
 */


@Data// set y get 
@Entity
@Table(name = "categoria")
public class categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@id
	@GegeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_categoria")
	private Long idCategoria;
	private String descripcion;
	private string rutaImagen;
	private boolean activo;

	public Categoria(){
	
	}


	public Categoria(String descripcion, String rutaImagen, boolean activo){
		this.descripcion = descripcion
		
	}


}
