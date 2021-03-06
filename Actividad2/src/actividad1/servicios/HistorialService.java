package actividad1.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JTable;

import com.mysql.jdbc.Statement;

import actividad1.domain.CuentaBancaria;
import actividad1.domain.Historial;
import actividad1.domain.Operacion;
import actividad1.domain.Propietario;

public class HistorialService {

	private JTable tblOperaciones;
	Connection conn = null;

	public HistorialService(Connection conn) {
		super();
		this.conn = conn;
	}

	public void insertarHistorialOperacion(Operacion op) throws SQLException {

		String query = "INSERT INTO historial(tipoEvento,fechahoraevento,id_cuentabancaria) VALUES(?,?,?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, op.getTipo());
		Timestamp timestamp = new Timestamp(new Date().getTime());
		pst.setTimestamp(2, timestamp);
		pst.setInt(3, op.getId_cuenta());
		pst.executeUpdate();
		pst.close();

	}

	public void insertarHistorialLogin(Propietario prop) throws SQLException {

		String query = "INSERT INTO historial(tipoEvento,fechahoraevento,dni_propietario) VALUES(?,?,?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, "L");

		Timestamp timestamp = new Timestamp(new Date().getTime());
		pst.setTimestamp(2, timestamp);

		pst.setString(3, prop.getDni());
		pst.executeUpdate();
		pst.close();

	}

	public void consultarHistorialByCtaBancaria(Historial historial) throws SQLException {

		String query = "select * from historial where id_cuentabancaria=?" + historial.getId_cuentabancaria();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		Historial hist = new Historial(query, null, 0);
		while (rs.next()) {
			hist.setId_cuentabancaria(rs.getInt("id_cuentabancaria"));

		}

	}

	public ResultSet consultaFechaHoraUltimoInicioSesio( String dniPropietario) throws SQLException {
		String query = "SELECT * FROM HISTORIAL WHERE dni_propietario ='"+ dniPropietario + "'  and fechahoraevento = (select max(fechahoraevento)  from historial " + 
				"where dni_propietario ='"+ dniPropietario + "')";
		
		Statement st= (Statement) conn.createStatement();
		ResultSet rs= st.executeQuery(query);
		return rs;
		
	}

}
