package com.Empresa.interfaces;

import java.util.ArrayList;

import com.Empresa.entidades.ConsultaXEmple;
import com.Empresa.entidades.Distrito;
import com.Empresa.entidades.Trabajador;

public interface TrabajdorDAO {
	
	//interfas de los metodos
	//todo los metodo tienen int porq si ejecuta
	//bien devuelve uno sino -1 y ese error lo controlo
	
	//insert
	public int insert(Trabajador bean);
	//update
	public int update(Trabajador bean);
	//delete
	public int delet(int cod);
	//listar
	public ArrayList<Trabajador> listAll();
	//correlativo
	public int codiEmple();
	//metodo login y contraseña
	public Trabajador Iniciarsecion(String login,String contraseña);
    //consulta por apellido
	public ArrayList<Trabajador> ConsultaXNombre(String no);
	//consulta validando el codigo
	public Trabajador ConsultaCodi(int c);
	//consulta con rbuton
	public ArrayList<Trabajador> ConTrabajador(int tipo,String filtro);
	//consulta
	public ArrayList<ConsultaXEmple> lisAll();
	//para lista distrito
	public ArrayList<Distrito> listDis();

}
