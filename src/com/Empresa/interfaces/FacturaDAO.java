package com.Empresa.interfaces;

import java.util.ArrayList;

import com.Empresa.entidades.Detalle_factura;
import com.Empresa.entidades.Factura;

public interface FacturaDAO {
	public int factura(Factura bean,ArrayList<Detalle_factura> lista);
	public int numboleta();

}
