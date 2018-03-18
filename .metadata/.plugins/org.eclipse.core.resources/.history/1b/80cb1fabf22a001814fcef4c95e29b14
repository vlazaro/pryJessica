package actividad1.servicios;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import actividad1.domain.CuentaBancaria;
import actividad1.domain.Operacion;

public class OperacionesService {

	Connection conn = null;

	public OperacionesService(Connection conn) {
		super();
		this.conn = conn;
	}

	public void insertarOperacion(Operacion operacion) {

		try {

			String query = "INSERT INTO  operaciones (fechahora,tipo,cantidad,saldoactualizado,id_cuentabancaria )"
					+ " VALUES(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(query);
			Timestamp timestamp = new Timestamp(new Date().getTime());

			pst.setTimestamp(1, timestamp);

			String tipo;
			if (operacion.getCantidad() >= 0) {
				tipo = "I";
			} else {
				tipo = "E";
			}

			pst.setString(2, tipo);
			pst.setDouble(3, operacion.getCantidad());

			Operacion opPrevia = recupearOperacionPrevia(operacion.getId_cuenta());
			Double saldoActual = obtenerSaldoActualizado(opPrevia, operacion.getId_cuenta(), tipo,
					operacion.getCantidad());

			pst.setDouble(4, saldoActual);
			pst.setInt(5, operacion.getId_cuenta());

			pst.executeUpdate();
			pst.close();

			operacion.setTipo(tipo);
			HistorialService historial = new HistorialService(conn);
			historial.insertarHistorialOperacion(operacion);

		} catch (SQLException e) {
			System.out.println("Error: al ingresar una nueva operación");
			e.printStackTrace();

		}
	}

	public void modificarOperaciones(Operacion operaciones) throws SQLException {
		String query = "UPDATE operaciones set tipo=?, cantidad=?, where id_cuenta=?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, operaciones.getTipo());
		pst.setDouble(2, operaciones.getCantidad());
		pst.setInt(3, operaciones.getId_cuenta());
		pst.executeUpdate();
		pst.close();
		conn.close();

	}

	public ResultSet listarOperacionesRojo(String nroCuenta) throws SQLException {
		String query = "SELECT o.* FROM operaciones o, cuentasbancarias c where o.saldoactualizado <0 and o.id_cuentabancaria = c.id and c.numcuenta =" + nroCuenta;
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public ResultSet listarCuentasEnRojo() throws SQLException {
		String query = "SELECT c.* ,o.saldoactualizado FROM operaciones o , cuentasbancarias c  where o.id_cuentabancaria = c.id " + 
				"and o.saldoactualizado <0";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	public ResultSet listarOperaciones(String nroCuenta) throws SQLException {
		String query = "SELECT o.* FROM operaciones o, cuentasbancarias c where o.id_cuentabancaria = c.id and c.numcuenta =" + nroCuenta;
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	public void eliminarOperaciones(Operacion operaciones) throws SQLException {
		String query = "delete from operaciones where num_cuenta=? " + operaciones.getId_cuenta();
		Statement st = (Statement) conn.createStatement();
		st.close();
		conn.close();

	}

	@SuppressWarnings("unused")
	// Consulta de operaciones por id
	private Operacion consultarOperacionByIdOperacion(Operacion operaciones) throws SQLException {

		String query = "select *from operaciones where id=?" + operaciones.getId();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		Operacion op = new Operacion();

		while (rs.next()) {

			op.setFecha_hora(rs.getDate("fechahora"));
			op.setTipo(rs.getString("tipo"));
			op.setCantidad(rs.getDouble("cantidad"));
			op.setId_cuenta(rs.getInt("id_cuentabancaria"));
			op.setSaldoActualizado(rs.getDouble("saldoactualizado"));
		}
		return op;
	}

	private Operacion consultarOperacionByIdCuenta(Operacion operaciones) throws SQLException {

		String query = "select *from operaciones where id_cuentabancaria=?" + operaciones.getId_cuenta();
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		Operacion op = new Operacion();
		while (rs.next()) {
			op.setFecha_hora(rs.getDate("fechahora"));
			op.setTipo(rs.getString("tipo"));
			op.setCantidad(rs.getDouble("cantidad"));
			op.setId_cuenta(rs.getInt("id_cuentabancaria"));
			op.setSaldoActualizado(rs.getDouble("saldoactualizado"));
		}
		return op;
	}

	// Consulta de todas las operaciones:

	private List<Operacion> consultarListaoperaciones() throws SQLException {

		String query = "select *from operaciones ";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		List<Operacion> operaciones = new ArrayList<Operacion>();

		while (rs.next()) {
			Operacion op = new Operacion();
			op.setFecha_hora(rs.getDate("fechahora"));
			op.setTipo(rs.getString("tipo"));
			op.setCantidad(rs.getDouble("cantidad"));
			op.setId_cuenta(rs.getInt("id_cuentabancaria"));
			op.setSaldoActualizado(rs.getDouble("saldoactualizado"));
			operaciones.add(op);

		}

		return operaciones;

	}

	private Operacion recupearOperacionPrevia(int idCuenta) throws SQLException {
		String query = "SELECT * FROM operaciones where id_cuentabancaria =" + idCuenta + " and "
				+ "fechahora = (SELECT MAX(fechahora) FROM operaciones where id_cuentabancaria = " + idCuenta + ")";
		Statement st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		Operacion op = new Operacion();
		while (rs.next()) {
			op.setId(rs.getInt("id"));
			op.setFecha_hora(rs.getDate("fechahora"));
			op.setTipo(rs.getString("tipo"));
			op.setCantidad(rs.getDouble("cantidad"));
			op.setId_cuenta(rs.getInt("id_cuentabancaria"));
			op.setSaldoActualizado(rs.getDouble("saldoactualizado"));

		}
		return op;
	}

	private CuentaBancaria recuperarCuenta(int idCuenta) throws SQLException {
		CuentaBancariaService ctaservice = new CuentaBancariaService(conn);
		CuentaBancaria ctabancaria = ctaservice.consultarCuenta(idCuenta);
		return ctabancaria;
	}

	private Double obtenerSaldoActualizado(Operacion opPrevia, int cuenta, String tipo, Double cantidad)
			throws SQLException {
		Double saldoActual = 0.0;
		if (opPrevia.getId() != 0) {
			Double saldoPrevio = opPrevia.getSaldoActualizado();
			saldoActual = saldoPrevio + cantidad;

		} else {
			CuentaBancaria cta = recuperarCuenta(cuenta);
			Double saldoInicial = cta.getSaldo();
			saldoActual = saldoInicial + cantidad;

		}
		return saldoActual;

	}
}
