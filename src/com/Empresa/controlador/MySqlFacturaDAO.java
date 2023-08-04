package com.Empresa.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Empresa.entidades.Detalle_factura;
import com.Empresa.entidades.Factura;
import com.Empresa.interfaces.FacturaDAO;
import com.utils.MySqlConexion;

public class MySqlFacturaDAO implements FacturaDAO{

	@Override
	public int factura(Factura bean, ArrayList<Detalle_factura> lista) {
		int salida=-1;
		//
		Connection cn= null;
		CallableStatement cstm=null, cstmde=null;
		//no por q nohay select ResultSet rs=null;
		
		try {
			cn= MySqlConexion.getConexion();
			//como son insert se trabaja con exeuteupdate pero eso
			//se confirma en automatico ,entonces desabilitar la confirmacion
			cn.setAutoCommit(false);//el autocomit por defecto de executeupdate
			String sqlfa="call Registrar_factura(?,?,?,?,?)";
			cstm= cn.prepareCall(sqlfa);
			//parametro
			cstm.setInt(1, bean.getCodifactura());
			cstm.setString(2, bean.getFecha());
			cstm.setInt(3, bean.getCodiEmpl());
			cstm.setDouble(4, bean.getTotal());
			cstm.setInt(5,bean.getCodiCliente());
			//ejecutar
			salida = cstm.executeUpdate();
			//luego proceder a grabar el detalle
			String sqldet="call Inserta_detalle(?,?,?)";
			for(Detalle_factura d:lista) {
				cstmde=cn.prepareCall(sqldet);
				cstmde.setInt(1, d.getNumfactura());
				cstmde.setInt(2, d.getCodiProducto());
				cstmde.setInt(3, d.getCantidad());
				//ejecutar
				salida += cstmde.executeUpdate();
				
			}
			//confirmar transaccion
			cn.commit();
			
		} catch (SQLException e) {
			try {
				//anular todo los insert
			     cn.rollback();	
			} catch (SQLException m) {
				m.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				
				if(cstm !=null)cstm.close();
				if(cn !=null) cn.close();
					
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return salida;
	}

	@Override
	public int numboleta() {
		int co=0;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn= MySqlConexion.getConexion();
			
			String sql="select max(num_fact)+1 from Factura;";
			pstm= cn.prepareStatement(sql);
			rs= pstm.executeQuery();
			//si hay datos
			if(rs.next()) {
				co= rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstm != null)pstm.close();
				if(cn != null)cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		return co;
	}
	

}
