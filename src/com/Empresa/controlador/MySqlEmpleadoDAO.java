package com.Empresa.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Empresa.entidades.Empleado;
import com.Empresa.interfaces.ClienteDAO;
import com.Empresa.interfaces.EmpleadoDAO;
import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import com.utils.MySqlConexion;

public class MySqlEmpleadoDAO implements EmpleadoDAO{

	@Override
	public int insert(Empleado bean) {
		int salida=-1;
		//
		Connection cn=null;
		PreparedStatement pstm=null;
		//
		try {
			cn= MySqlConexion.getConexion();
			String sql=("insert into emple values(?,?,?)");
			//
			pstm=cn.prepareStatement(sql);
			//
			pstm.setInt(1,bean.getCodigo());
			pstm.setString(2, bean.getFecha());
			pstm.setString(3, bean.getNombre());
			//ejecutar
			salida= pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm !=null)pstm.close();
				if(cn !=null)cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return salida;
	}

	@Override
	public ArrayList<Empleado> lisAll() {
		ArrayList<Empleado> lista= new ArrayList<Empleado>();
		//
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		//
		try {
			cn= MySqlConexion.getConexion();
			String sql=("select * from emple");
			//
			pstm=cn.prepareStatement(sql);
			//parametros
			//ejecutar
			rs= pstm.executeQuery();
			//while
			while(rs.next()) {
				Empleado e = new Empleado();
				
				e.setCodigo(rs.getInt(1));
				e.setFecha(rs.getString(2));
				e.setNombre(rs.getString(3));
				//adicionar
				lista.add(e);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null)rs.close();
				if(pstm !=null)pstm.close();
				if(cn !=null)cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return lista;
	}
	
	
	

}
