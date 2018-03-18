package com.orm.principal;

import com.orm.configuracion.AppConfig;
import com.orm.entity.Propietario;
import com.orm.service.PropietariosService;

public class Test {
	
	
	public static void main(String[] args) {
		
		AppConfig conf = new AppConfig();
		PropietariosService propService = new PropietariosService(conf.buildSessionFactory().openSession());
		
		Propietario prop = new Propietario();
		prop.setDni("51164312F");
		prop.setNombre("VICTOR");
		prop.setPrimerapellido("LAZARO");
		prop.setSegundoapellido("VASQUEZ");
		prop.setUsuario("vlazaro");
		prop.setNumerosecreto("0000");
		
		
		propService.insertarPropietario(prop);
		
		
		
		
		
		
	}

}
