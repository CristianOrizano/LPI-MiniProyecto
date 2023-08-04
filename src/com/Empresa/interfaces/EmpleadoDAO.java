package com.Empresa.interfaces;

import java.util.ArrayList;

import com.Empresa.entidades.Empleado;

public interface EmpleadoDAO {
	public int insert(Empleado bean);
	//listado
	public ArrayList<Empleado> lisAll();

}
