package actividad1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import actividad1.domain.CuentaBancaria;
import actividad1.domain.Propietario;

public class AppConfig {
	
 // Abrir conexion con la base de datos:
	Connection conn=null;
	public AppConfig() {
		
		try {
			
			String url  = "jdbc:mysql://localhost:3306/operacionesbancarias";
			String user =  "root";
			String password= "root";
			
			conn= DriverManager.getConnection(url,user,password);
			if(conn != null) {
				
				System.out.println("Conectado a Operaciones Bancarias");
								
			}
			} catch(SQLException ex) {
				System.out.println("Error : La direcci�n no es valida o el usuario o la clave" );
				ex.printStackTrace();
				
			}
	}
	public Connection getConn() {
		return conn;
	}
	
}
	




