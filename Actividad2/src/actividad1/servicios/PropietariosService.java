package actividad1.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import actividad1.domain.Propietario;

public class PropietariosService {
	
	Connection conn=null;
	
	public PropietariosService(Connection conn) {
		super();
		this.conn = conn;
	}
	

	public void insertarPropietario(Propietario propietario) {
		try {
			String query ="INSERT INTO propietarios VALUES (?,?,?,?,?,?)";
			PreparedStatement pst1= conn.prepareStatement(query);
			pst1.setString(1,propietario.getDni());
			pst1.setString(2,propietario.getUsuario());
			pst1.setString(3, propietario.getNombre());
			pst1.setString(4,propietario.getPrimerapellido() );
			pst1.setString(5, propietario.getSegundoapellido());
			pst1.setString(6,propietario.getNumerosecreto() );
			
			pst1.executeUpdate();
			pst1.close();
			
		}
		catch(SQLException ex) {
			
			System.out.println("Error: al ingresar un nuevo propietario");
			ex.printStackTrace();
			
		}
	}

	public void modificarPropietario(Propietario propietario) throws SQLException {
		String query= "UPDATE propietarios set usuario= ?, nombre=?, primerapellido=?, "
				+ "segundoapellido=?,numerosecreto=? ,dni=? where dni=?";
		PreparedStatement pst= conn.prepareStatement(query);
		pst.setString(1, propietario.getUsuario());
		pst.setString(2, propietario.getNombre());
		pst.setString(3, propietario.getPrimerapellido());
		pst.setString(4, propietario.getSegundoapellido());
		pst.setString(5, propietario.getNumerosecreto());
		pst.setString(6, propietario.getDni());
		pst.setString(7, propietario.getDni());
		pst.executeUpdate();
		pst.close();
	
		
		
		
	}
	
	public void eliminarPropietario(Propietario propietario) throws SQLException {
		
		String query= "delete from propietarios where dni='"+ propietario.getDni()+"'";
		Statement st= (Statement) conn.createStatement();
		st.executeUpdate(query);
		st.close();
		
	}
	
	public Propietario consultarPropietarioByDni(Propietario prop) throws SQLException {
		String query= "Select * from propietarios where dni ='"+ prop.getDni()+ "'";
		Statement st= (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		Propietario propietario = new Propietario();
		while(rs.next()) {
			propietario.setNombre(rs.getString("nombre"));
			propietario.setPrimerapellido(rs.getString("primerapellido"));
			propietario.setSegundoapellido(rs.getString("segundoapellido"));
			propietario.setUsuario(rs.getString("usuario"));
			propietario.setNumerosecreto(rs.getString("numerosecreto"));
			propietario.setDni(rs.getString("dni"));
						
		}
		return propietario;
	}
	
	public Propietario consultarPropietarioByUsuarioPassword(Propietario prop) throws SQLException {
		String query= "Select * from propietarios where usuario ='"+ prop.getUsuario()+ "' and numerosecreto='" + prop.getNumerosecreto() + "'";
		Statement st= (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		Propietario propietario = new Propietario();
		while(rs.next()) {
			propietario.setNombre(rs.getString("nombre"));
			propietario.setPrimerapellido(rs.getString("primerapellido"));
			propietario.setSegundoapellido(rs.getString("segundoapellido"));
			propietario.setUsuario(rs.getString("usuario"));
			propietario.setNumerosecreto(rs.getString("numerosecreto"));
			propietario.setDni(rs.getString("dni"));
						
		}
		return propietario;
	}
	
	
	public boolean validarUsuarioPassword(String usuario,String password) throws SQLException {
		String query= "select * from propietarios where usuario = '" + usuario + "' and numerosecreto = '" +  password + "'";
		Statement st= (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		if (rs.next()) {
			return true;
		}
		return false;
	}
	
}
