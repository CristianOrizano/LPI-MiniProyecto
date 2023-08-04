package com.Empresa.entidades;

public class Factura {
	
	public int codifactura,codiEmpl,codiCliente;
	public String fecha;
	public double total;
	
	public int getCodifactura() {
		return codifactura;
	}
	public void setCodifactura(int codifactura) {
		this.codifactura = codifactura;
	}
	public int getCodiEmpl() {
		return codiEmpl;
	}
	public void setCodiEmpl(int codiEmpl) {
		this.codiEmpl = codiEmpl;
	}
	public int getCodiCliente() {
		return codiCliente;
	}
	public void setCodiCliente(int codiCliente) {
		this.codiCliente = codiCliente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

}
