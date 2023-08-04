package com.Empresa.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Empresa.entidades.Cliente;
import com.Empresa.entidades.Producto;
import com.Empresa.entidades.Trabajador;
import com.Empresa.interfaces.ClienteDAO;
import com.Empresa.interfaces.ProductoDAO;
import com.mysql.cj.MysqlConnection;
import com.utils.MySqlConexion;

public class MySqlProductoDAO implements ProductoDAO{

	@Override
	public ArrayList<Producto> BuscarProducto(String pro) {
		
		ArrayList<Producto> lista = new ArrayList<Producto>();
		//
		Connection cn =null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		//
		try {
			//1
			cn = MySqlConexion.getConexion();
			//2
			String sql="call Buscar_Productod(?);";
			//3
			cstm= cn.prepareCall(sql);
			//4
			cstm.setString(1, pro);
			//5
			rs=cstm.executeQuery();
			//6 while
			while(rs.next()) {
				Producto tr = new Producto();
				tr.setCodigo(rs.getInt(1));
				tr.setNombre(rs.getString(2));
				tr.setPrecio(rs.getDouble(3));
				
				lista.add(tr);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null)rs.close();
				if(cstm !=null)cstm.close();
				if(cn !=null)cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		return lista;
	}


}
