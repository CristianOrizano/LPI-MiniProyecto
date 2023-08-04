package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConexion {
	
	//getConexion es el nombre
	//el metodo me devuelve un connection
	//haci que declaro un objeto del mismo tipo
	
	public static Connection getConexion() {
		Connection co = null;
		try {
			//variables
			String url,login,clave;
		//ruta del driver que es la conexion
		Class.forName("com.mysql.cj.jdbc.Driver");
		//nombre de la base de datos  
		//jdbc:mysql:// asi siempre se empieza porq es formato jdbc
		//localhost es el nombre de tu maquina
		url= "jdbc:mysql://localhost/Empresa?serverTimezone=UTC";
		login="root";
		clave="mysql";
		 co = DriverManager.getConnection(url,login,clave);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return co;
		
		
	}
	
	
	
}
