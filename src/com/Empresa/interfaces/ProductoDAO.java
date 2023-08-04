package com.Empresa.interfaces;

import java.util.ArrayList;

import com.Empresa.entidades.Producto;

public interface ProductoDAO {
	
	//consulta
	public ArrayList<Producto> BuscarProducto(String pro);

}
