package actividad1;


import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import actividad1.domain.CuentaBancaria;
import actividad1.domain.Historial;
import actividad1.domain.Operacion;
import actividad1.domain.Propietario;
import actividad1.servicios.CuentaBancariaService;
import actividad1.servicios.HistorialService;
import actividad1.servicios.OperacionesService;
import actividad1.servicios.PropietariosService;

public class OperacionesBancoJDBC {

	public static void main(String[] args) throws SQLException {
		
		AppConfig gestor = new AppConfig();
		PropietariosService pservice=new PropietariosService(gestor.getConn());
		CuentaBancariaService ctaservice = new CuentaBancariaService(gestor.getConn());
		OperacionesService opservice = new OperacionesService(gestor.getConn());
	
		
		
		//Creacion de Propietario:
//		Propietario propietario = new Propietario();
//		propietario.setDni("57846821R");
//		propietario.setUsuario("mramirez");
//		propietario.setNombre("Maria");
//		propietario.setPrimerapellido("Ramirez");
//		propietario.setSegundoapellido("Escobar");
//		propietario.setNumerosecreto("23456");
		//pservice.insertarPropietario(propietario);
		
		//pservice.modificarPropietario(propietario);
		
//		boolean existe = pservice.validarUsuarioPassword("victor", "ppepe");
		
		
		//CREACION DE NUEVA CUENTA
//		CuentaBancaria cuenta = new CuentaBancaria();
//		cuenta.setId(101);
//		cuenta.setNum_cuenta("210-48-100258");
//		cuenta.setSaldo(1200);
//		cuenta.setId_propietario("57846821R");
		//ctaservice.insertarCuenta(cuenta);
		
		
		Operacion op = new Operacion();
		op.setCantidad(1000.00);
		op.setId_cuenta(1);
		
		opservice.insertarOperacion(op);
		
		

		
		
		
		
		gestor.conn.close();
		

	}

	
	


}
