package com.Empresa.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Empresa.entidades.ConsultaXEmple;
import com.Empresa.entidades.Distrito;
import com.Empresa.entidades.Trabajador;
import com.Empresa.interfaces.TrabajdorDAO;
import com.mysql.cj.protocol.Resultset;
import com.utils.MySqlConexion;

  //como esta clase va a implementar lo de la interfas TrabajadorDAO pues implementamos
   public class MySqlTrabajadorDAO implements TrabajdorDAO{

	@Override
	public int insert(Trabajador bean) {
		//como el metodo es un entero entonces debo retornar un entero
		//declaro en -1 para un posible error luego durante el proceso
		//si todo es correcto cambia a positivo1
       int resultado=-1;	
     //coneccion para llamar getconexion de utils
     // cn Almacena la conexion
       Connection cn=null;
     //prepare..  clase q permite trabajar con instruciones sql Como inser,update,delete,
     //resulset manipula select
     PreparedStatement pstm=null;
     try {
    	 //PASO 1: obtener conexión a BD
			cn=MySqlConexion.getConexion();
			//PASO 2: instrucción sql
			String sql ="insert into Trabajador value(?,?,?,?,?,?)"; // ?---> parámetros
			//PASO 3: crear objeto "pstm" y enviar la variable "sql"
			pstm=cn.prepareStatement(sql);
			//PASO 4: parámetros "?" (asignar valor)
			//darle valor a los paraetros
			pstm.setInt(1, bean.getCodEmpl());//le paso cdiEmpl al primer parametro
			pstm.setString(2,bean.getNombre());
    	    pstm.setString(3,bean.getApellido());
    	    pstm.setDouble(4,bean.getSueldo());
    	    pstm.setInt(5,bean.getHijos());
    	    pstm.setInt(6,bean.getCodDistrito());
    	  //PASO 5: ejecutar
    	    //insert,delete,update lo ejecuto con exexuteUpdate
    	    //pra select exuteQuery
    	    //si todo es correcto retorna 1
    	    resultado= pstm.executeUpdate();
			
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			//esto es para liberar recursos
			//si es null es porq esta abierto (hay q cerrar)
			if(pstm !=null) pstm.close();
			if(cn != null) cn.close();
				
		} catch (SQLException a) {
			a.printStackTrace();
		}
	}
		
		
		
		return resultado;
	}
    //-----------------------------------------------
	@Override
	public int update(Trabajador bean) {
		 int resultado= -1;
		Connection cn = null;
		PreparedStatement pstm=null;
		try {
			//1:paso conexion
			cn =MySqlConexion.getConexion();
			//2:paso la sentencia sql
			String sql = "update Trabajador set nom_empl=?,ape_empl=?,sul_empl=?,hij_empl=?,cod_dis=? where cod_Emple=?";
			//3:paso crear objeto pstm y pasar la sentecia sql
			pstm = cn.prepareStatement(sql);
			//4:darle valor alos parametros (?)
			pstm.setString(1,bean.getNombre());
			pstm.setString(2,bean.getApellido());
			pstm.setDouble(3,bean.getSueldo());
			pstm.setInt(4,bean.getHijos());
			pstm.setInt(5,bean.getCodDistrito());
			pstm.setInt(6,bean.getCodEmpl());
			//5:ejecutar
			resultado=pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null)pstm.close();
				if(cn!=null)cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		 
		 
		 
		 
		 
		return resultado;
	}
   
	//------------------------------------------------
	@Override
	public int delet(int cod) {
        int resultado= -1;		
		Connection cn = null;
		PreparedStatement pstm=null;
		try {
			//paso1 conexion
			cn = MySqlConexion.getConexion();
			//paso2 sentencia sql
			String sql = "delete from Trabajador where cod_emple=?";
			//paso3 crear objeto pstm y enviar la varible sql
			pstm = cn.prepareStatement(sql);
			//paso 4 asignar valor alos parametros
			pstm.setInt(1,cod);
			//paso 5 ejecutar
			resultado =pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {

				if(pstm !=null)pstm.close();
				if(cn!=null)cn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	public int codiEmple() {
		int cod=0;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			//1 conexiob
			cn=MySqlConexion.getConexion();
			//2 sentecia sql
			String sql=("select max(cod_Emple)+1 from Trabajador;");
			//3 objeto
			pstm= cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			if(rs.next()){
				//porq devuelve un solo valor
				cod=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null)cn.close();
				if(pstm !=null)pstm.close();
				if(cn != null)cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return cod;
	}
	@Override
	public ArrayList<Trabajador> listAll() {
		//como el metodo es de tipo Arraylist
		//debo retornar un arraylist
		ArrayList<Trabajador> lista =new ArrayList<Trabajador>();
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		try {
			//paso1 conexion
			cn = MySqlConexion.getConexion();
			//paso2 sentancia sql
			/*String sql ="select  cod_Emple,nom_empl,ape_empl,sul_empl,hij_empl,concat(di.cod_dis,\"-\",nom_dis)\r\n" + 
					" from  Trabajador as tr join Distrito as di\r\n" + 
					" on tr.cod_dis=di.cod_dis;"; */
			String sql ="select  cod_Emple,nom_empl,ape_empl,sul_empl,hij_empl,nom_dis\r\n" + 
					" from  Trabajador as tr join Distrito as di\r\n" + 
					" on tr.cod_dis=di.cod_dis;";
			//paso3 crear objeto pstm y pasarle el sql
			pstm = cn.prepareStatement(sql);
			//paso4 darle valor alos parametros(no HAY)
			
			//paso5 ejecutar
			//executeQuery xq en el sql hay un select
			//y el select se almacena en el rs
			rs= pstm.executeQuery();
			//6 bucle para reaLIZAR RECORRIDO SOBRE "RS"
			//next moverme en la tabla en una  fila determinada
			//como es un selct que devuelve mas de una fila el recorrido es con while
			//si se ejecuto el select este rs estaria almacenado todo los registros
			
			//el bucle adecuado para trabajar con BD es while
			while(rs.next()) {
				//7 crear objeto de la clase Trabajador 
				//rs saca columnas
				Trabajador tra = new Trabajador();
				tra.setCodEmpl(rs.getInt(1)); //1 es la primera columna q es int
				tra.setNombre(rs.getString(2));
				tra.setApellido(rs.getString(3));
				tra.setSueldo(rs.getDouble(4));
				tra.setHijos(rs.getInt(5));
				tra.setNomDistrito(rs.getString(6));
				lista.add(tra);
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
	@Override
	public Trabajador Iniciarsecion(String login, String contraseña) {
		Trabajador bean = null;
		//
		Connection cn= null;
		CallableStatement cstm=null;
		ResultSet rs= null;
		try {
			//1 conexion
			cn= MySqlConexion.getConexion();
			//2 sentencia sql
            String sql = "call sp_iniciar_secion (?,?);";
            //3 crear objeto y pasarle el sql
            cstm=cn.prepareCall(sql);
            //4 darle valor alos parametros
            cstm.setString(1, login);
            cstm.setString(2, contraseña);
            //5 ejecutar
            rs= cstm.executeQuery();
            //6 bucle(en este caso como el call me devuelve
            //un registro 100% ,debo validar si existe) 
            if(rs.next()) {
            	bean = new Trabajador();
            	
            	bean.setCodEmpl(rs.getInt(1));
            	bean.setNombre(rs.getString(2));
            	bean.setApellido(rs.getString(3));
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
		
		
		
		return bean;
	}
	@Override
	public ArrayList<Trabajador> ConsultaXNombre(String no) {
		ArrayList<Trabajador> lista= new ArrayList<Trabajador>();
		Connection cn = null;
		CallableStatement cstm=null;
		ResultSet rs=null;
		
		try {
			//1
			cn= MySqlConexion.getConexion();
			//2
			String sql="call Consulta_por_nombre(?)";
			//3
			cstm= cn.prepareCall(sql);
			//4
			cstm.setString(1, no);
			//5
			rs=cstm.executeQuery();
			//6 while
			while(rs.next()) {
				Trabajador tr = new Trabajador();
				tr.setCodEmpl(rs.getInt(1));
				tr.setNombre(rs.getString(2));
				tr.setApellido(rs.getString(3));
				tr.setSueldo(rs.getDouble(4));
				tr.setHijos(rs.getInt(5));
				tr.setNomDistrito(rs.getNString(6));
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
	@Override
	public Trabajador ConsultaCodi(int c) {
		
		Trabajador tr= null;
		Connection cn= null;
		PreparedStatement pstm= null;
		ResultSet rs=null;
		try {
			cn= MySqlConexion.getConexion();
			String sql ="select * from Trabajador where cod_Emple =?";
			pstm= cn.prepareStatement(sql);
			//
			pstm.setInt(1, c);
			//
			rs= pstm.executeQuery();
			//
			if(rs.next()) {
				 tr=new Trabajador();
					tr.setCodEmpl(rs.getInt(1));
					tr.setNombre(rs.getString(2));
					tr.setApellido(rs.getString(3));
					tr.setSueldo(rs.getDouble(4));
					tr.setHijos(rs.getInt(5));
					tr.setCodDistrito(rs.getInt(6));
				
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

		return tr;
	}
	@Override
	public ArrayList<Trabajador> ConTrabajador(int tipo, String filtro) {
		ArrayList<Trabajador> lista= new ArrayList<Trabajador>();
		//
		Connection cn = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		try {
			cn= MySqlConexion.getConexion();
			String sql="call ConsultaTrabajador(?,?)";
			cstm=cn.prepareCall(sql);
			//parametros
			cstm.setInt(1, tipo);
			cstm.setString(2, filtro);
			//ejecutar
			rs= cstm.executeQuery();
			//while
			while(rs.next()) {
				Trabajador tr = new Trabajador();
				tr.setCodEmpl(rs.getInt(1));
				tr.setNombre(rs.getString(2));
				tr.setApellido(rs.getString(3));
				tr.setSueldo(rs.getDouble(4));
				tr.setHijos(rs.getInt(5));
				tr.setNomDistrito(rs.getNString(6));
				
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
	@Override
	public ArrayList<ConsultaXEmple> lisAll() {
		ArrayList<ConsultaXEmple> lista = new ArrayList<ConsultaXEmple>();
		//
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		try {
			//1
			cn= MySqlConexion.getConexion();
			//2
			String sql="select  t.cod_Emple,t.nom_empl,t.ape_empl,t.sul_empl, u.login,u.clave from trabajador as t right join usuario as u\r\n" + 
					"on t.cod_Emple=u.cod_Emple";
			//3
			pstm= cn.prepareStatement(sql);
			//4
			
			//5
			rs=pstm.executeQuery();
			//6 while
			while(rs.next()) {
				ConsultaXEmple c = new ConsultaXEmple();
				c.setCodi(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setApellddo(rs.getString(3));
				c.setSueldo(rs.getDouble(4));
				c.setLogin(rs.getString(5));
				c.setClave(rs.getString(6));
				lista.add(c);
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
	@Override
	public ArrayList<Distrito> listDis() {
		
		ArrayList<Distrito> lista = new ArrayList<Distrito>();
		//
		Connection cn = null;
		PreparedStatement pstm=null;
		ResultSet rs= null;
		try {
			//1
			cn= MySqlConexion.getConexion();
			//2
			String sql="select * from Distrito";
			//3
			pstm= cn.prepareStatement(sql);
			//4
			
			//5
			rs=pstm.executeQuery();
			//6 while
			while(rs.next()) {
				Distrito c = new Distrito();
				c.setCodigo(rs.getInt(1));
				c.setDescr(rs.getString(2));;
				lista.add(c);
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
