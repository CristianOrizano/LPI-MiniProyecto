package com.Empresa.interfaces;

import java.util.ArrayList;

import com.Empresa.entidades.Cliente;

public interface ClienteDAO {
	//consulta 
	public ArrayList<Cliente> BuscarCliente(String no);
	//insert
	public int insert(Cliente bean);
	//update
	public int update(Cliente bean);
	//delete
	public int delete(int cod);
	//correlatiivo
	public int correla();
	//listar
	public ArrayList<Cliente> lisALl();
	//consultar que el codigo no se repita
	public Cliente buscar(int cod);
	//consulta cliente
	public ArrayList<Cliente> consulta(int filtro,String criterio);

}
