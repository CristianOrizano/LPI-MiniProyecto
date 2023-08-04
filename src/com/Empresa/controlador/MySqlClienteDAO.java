package com.Empresa.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Empresa.entidades.Cliente;
import com.Empresa.interfaces.ClienteDAO;
import com.mysql.cj.protocol.Resultset;
import com.utils.MySqlConexion;

public class MySqlClienteDAO implements ClienteDAO{

	@Override
	public ArrayList<Cliente> BuscarCliente(String no) {
	    ArrayList<Cliente> lista = new ArrayList<Cliente>(); 
		//
	    Connection cn =null;
	    CallableStatement cstm=null;
	    ResultSet rs = null;
	    //
	    try {
			cn = MySqlConexion.getConexion();
			String sql="call Buscar_cliente(?)";
            cstm= cn.prepareCall(sql);
            //pasar parametros
            cstm.setString(1, no);
            //ejecutar
            rs = cstm.executeQuery();
            //while
            while(rs.next()) {
            	Cliente cl = new Cliente();
            	cl.setCodi(rs.getInt(1));
            	cl.setNombre(rs.getString(2));
            	cl.setApellido(rs.getString(3));
            	cl.setDni(rs.getInt(4));
            	cl.setSexo(rs.getString(5));
            	//
            	lista.add(cl);
            	
            }
            
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(cstm != null)cstm.close();
				if(rs !=null) rs.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	    
	    
		
		return lista ;
	}

	@Override
	public int insert(Cliente bean) {
		int salida=-1;
		//
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="insert into tb_cliente values(?,?,?,?,?)";
			pstm= cn.prepareStatement(sql);
			//pasar parametros
			pstm.setInt(1, bean.getCodi());
			pstm.setString(2, bean.getNombre());
			pstm.setString(3, bean.getApellido());
			pstm.setInt(4, bean.getDni());
			pstm.setString(5, bean.getSexo());
			//ejecutar
			salida= pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn !=null)cn.close();
				if(pstm !=null)pstm.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return salida;
	}

	@Override
	public int update(Cliente bean) {
		
		int salida=-1;
		//
		Connection cn=null;
		CallableStatement cstm=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="call Actualizar_cliente(?,?,?,?,?)";
			cstm= cn.prepareCall(sql);
			//pasar parametros
			cstm.setString(1, bean.getNombre());
			cstm.setString(2, bean.getApellido());
			cstm.setInt(3, bean.getDni());
			cstm.setString(4, bean.getSexo());
			cstm.setInt(5, bean.getCodi());
			//ejecutar
			salida= cstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn !=null)cn.close();
				if(cstm !=null)cstm.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return salida;
	}

	@Override
	public int delete(int cod) {
		int salida=-1;
		//
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="delete from tb_cliente where cod_cli =?";
			pstm= cn.prepareStatement(sql);
			//pasar parametros
			pstm.setInt(1, cod);

			//ejecutar
			salida= pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn !=null)cn.close();
				if(pstm !=null)pstm.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return salida;
	}

	@Override
	public int correla() {
		int salida=0;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="select max(cod_cli)+1 from tb_cliente;";
			pstm= cn.prepareStatement(sql);
			//pasar parametros
			//ejecutar
			rs= pstm.executeQuery();
			//bucle
			if(rs.next()) {
				salida= rs.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn !=null)cn.close();
				if(pstm !=null)pstm.close();
				if(rs !=null)rs.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return salida;
	}

	@Override
	public ArrayList<Cliente> lisALl() {
		ArrayList<Cliente> lista= new ArrayList<Cliente>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="select * from tb_cliente";
			pstm= cn.prepareStatement(sql);
			//pasar parametros
			//ejecutar
			rs= pstm.executeQuery();
			//bucle
			while(rs.next()) {
				Cliente c= new Cliente();
				c.setCodi(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setApellido(rs.getString(3));
				c.setDni(rs.getInt(4));
				c.setSexo(rs.getString(5));
				//
				lista.add(c);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn !=null)cn.close();
				if(pstm !=null)pstm.close();
				if(rs !=null)rs.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		return lista;
	}

	@Override
	public Cliente buscar(int cod) {
		Cliente cl=null;
		//
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="select * from tb_cliente where cod_cli=?;";
			pstm= cn.prepareStatement(sql);
			//pasar parametros
			pstm.setInt(1, cod);
			//ejecutar
			rs= pstm.executeQuery();
			//bucle
			if(rs.next()) {
				 cl= new Cliente();
				cl.setCodi(rs.getInt(1));
				cl.setNombre(rs.getString(2));
				cl.setApellido(rs.getString(3));
				cl.setDni(rs.getInt(4));
				cl.setSexo(rs.getString(5));
				//
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn !=null)cn.close();
				if(pstm !=null)pstm.close();
				if(rs !=null)rs.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		
		
	    return cl;
	}

	@Override
	public ArrayList<Cliente> consulta(int filtro, String criterio) {
		ArrayList<Cliente> lista= new ArrayList<Cliente>();
		Connection cn=null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="call sp_ConsultaCliente(?,?)";
			cstm= cn.prepareCall(sql);
			//pasar parametros
			cstm.setInt(1, filtro);
			cstm.setString(2, criterio);
			//ejecutar
			rs= cstm.executeQuery();
			//bucle
			while(rs.next()) {
				Cliente c= new Cliente();
				c.setCodi(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setApellido(rs.getString(3));
				c.setDni(rs.getInt(4));
				c.setSexo(rs.getString(5));
				//
				lista.add(c);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(cn !=null)cn.close();
				if(cstm !=null)cstm.close();
				if(rs !=null)rs.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		return lista;
	}
	
	

}
