package actividad1.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import actividad1.domain.CuentaBancaria;

public class CuentaBancariaService {
	
	Connection conn=null;
	
	public CuentaBancariaService(Connection conn) {
		super();
		this.conn = conn;
	}

	public void insertarCuenta(CuentaBancaria cuenta) {
		try {
		// Crea un Statement para realizar una consulta SQL INSER con parametros
			String query = "INSERT INTO cuentasbancarias (numcuenta,saldo,dni_propietario) VALUES (?,?,?)";
			PreparedStatement pst= conn.prepareStatement(query);
			pst.setString(1,cuenta.getNum_cuenta());
			pst.setDouble(2, cuenta.getSaldo());
			pst.setString(3, cuenta.getId_propietario());
			pst.executeUpdate();
			pst.close();
		}
		catch (SQLException ex) {
			System.out.println("Error : al ingresar una nueva cuenta bancaria");
			ex.printStackTrace();
		}
	
	}
	
	public void modificarCuenta(CuentaBancaria cuenta) throws SQLException {
		String query="UPDATE cuentasbancarias  set saldo=? where dni_propietario =? and numcuenta=?";
		PreparedStatement pst= conn.prepareStatement(query);
		pst.setDouble(1, cuenta.getSaldo());
		pst.setString(2, cuenta.getId_propietario());
		pst.setString(3, cuenta.getNum_cuenta());
		pst.executeUpdate();
		pst.close();
			
	}		
	public void eliminarCuenta(CuentaBancaria cuenta) throws SQLException {
		
		String query="delete from cuentasbancarias where numcuenta = " + cuenta.getNum_cuenta()+ ";" ;
		Statement st = (Statement) conn.createStatement();
		st.executeUpdate(query);
		st.close();
		conn.close();
		
				
		
	}
	
	public List<CuentaBancaria> consultarCuentas(CuentaBancaria numcuenta) throws SQLException{
		String query= "Select * from cuentasbancarias where dni_propietario = " + numcuenta.getId_propietario() +";";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		
		List<CuentaBancaria>cuentas= new ArrayList<CuentaBancaria>();
		
		while(rs.next()) {
			
			CuentaBancaria cuenta = new CuentaBancaria();
			cuenta.setNum_cuenta(rs.getString("numcuenta"));
			cuenta.setSaldo(rs.getDouble("saldo"));
			//cuenta.setId_propietario(rs.getString("dni_propietario"));
			
			cuentas.add(cuenta);
		}
		return cuentas;

		
	}

	public CuentaBancaria consultarCuenta(String numcuenta) throws SQLException {
		String query= "Select * from cuentasbancarias where numcuenta = '" + numcuenta+"'";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		
		CuentaBancaria cuenta = new CuentaBancaria();
		while(rs.next()) {
			
			cuenta.setId(rs.getInt("id"));
			cuenta.setNum_cuenta(rs.getString("numcuenta"));
			cuenta.setSaldo(rs.getDouble("saldo"));
			cuenta.setId_propietario(rs.getString("dni_propietario"));
		}
		return cuenta;
		
	}
	
	public ResultSet consultarCuentaResultSet(String numcuenta) throws SQLException {
		String query= "Select * from cuentasbancarias where numcuenta = '" + numcuenta+"'";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		return rs;
		
	}
	
	
	
	public CuentaBancaria consultarCuenta(int idCuenta) throws SQLException {
		String query= "Select * from cuentasbancarias where id= " + idCuenta;
		Statement st = (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		
		CuentaBancaria cuenta = new CuentaBancaria();
		while(rs.next()) {
			cuenta.setNum_cuenta(rs.getString("numcuenta"));
			cuenta.setSaldo(rs.getDouble("saldo"));
			cuenta.setId_propietario(rs.getString("dni_propietario"));
		}
		return cuenta;
		
	}
	
	
	
	
	
	
	
}
