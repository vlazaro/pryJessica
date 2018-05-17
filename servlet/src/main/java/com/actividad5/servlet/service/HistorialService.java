package com.actividad5.servlet.service;

import java.util.Date;

import javax.ejb.Stateless;

import com.activida5.servlet.domain.Historial;
import com.activida5.servlet.domain.Operacione;
import com.activida5.servlet.domain.Propietario;
@Stateless
public class HistorialService extends BaseHibernate {
	
	public void save(Historial prop) {
		super.save(prop);
	}
	public void delete(Historial prop) {
		super.delete(prop);
		
	}
	
	public void saveHistorialLogin(Propietario prop) {
		Historial hist = new Historial();
		hist.setDniPropietario(prop.getDni());
		hist.setFechahoraevento(new Date());
		hist.setTipoevento("L");
		save(hist);
		
		
	}
	
	public void saveHistorialOperacion( Propietario prop,Operacione ope) {
		Historial hist = new Historial();
		hist.setDniPropietario(prop.getDni());
		hist.setFechahoraevento(new Date());
		
		double cantidad = ope.getCantidad();
		String tipo ;
		if(cantidad > 0) {
			tipo = "I";
		}else {
			tipo = "E";
		}
		hist.setTipoevento(tipo);
		save(hist);
	}
	
	
	
	

}
