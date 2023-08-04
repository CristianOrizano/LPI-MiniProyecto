package com.Empresa.Componente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import com.mysql.cj.protocol.Resultset;
import com.utils.MySqlConexion;

//esta clase (JComboBoxDB)permita heredar de la clase jcomboBox de java
public class JComboBoxDB extends JComboBox {
	
	//sql almacena la sentencia  que tiene JComboBoxDB de java
	public JComboBoxDB(String sql) {
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn= MySqlConexion.getConexion();
			pstm= cn.prepareStatement(sql);
			//ejecutamos
			rs= pstm.executeQuery();
			while(rs.next()) {
				
				//addItem(rs.getInt(1)+"-"+rs.getString(2));
				addItem(rs.getString(2));
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
		
	}

}
