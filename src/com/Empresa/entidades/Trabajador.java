package com.Empresa.entidades;

public class Trabajador {
	
	//atributos de tabla trabajador
	public int codEmpl,hijos,codDistrito;
    public String nombre,apellido;
    public double sueldo;
    //para el select concatenado
    public String nomDistrito;
    
    
	public String getNomDistrito() {
		return nomDistrito;
	}
	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}
	public int getCodEmpl() {
		return codEmpl;
	}
	public void setCodEmpl(int codEmpl) {
		this.codEmpl = codEmpl;
	}
	public int getHijos() {
		return hijos;
	}
	public void setHijos(int hijos) {
		this.hijos = hijos;
	}
	public int getCodDistrito() {
		return codDistrito;
	}
	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
    
	
	
}
