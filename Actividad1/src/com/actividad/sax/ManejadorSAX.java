package com.actividad.sax;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.actividad.pojo.Operacion;
import com.actividad.pojo.OperacionElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.soap.Text;

import org.xml.sax.Attributes;

class ManejadorSAX extends DefaultHandler {

	private boolean cantidad;
	private boolean numcuenta;
	private boolean saldo;
	private boolean tipo;
	private boolean propietario;
	
	
	private Operacion operacion = new Operacion();
	private List<Operacion> operaciones = new ArrayList<Operacion>();
	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		 if (qName.equals(OperacionElement.CANTIDAD.getName())) {
	            cantidad = true;
	        }
	        if (qName.equals(OperacionElement.NUMCUENTA.getName())) {
	            numcuenta = true;
	        }
	        if (qName.equals(OperacionElement.SALDO.getName())) {
	            saldo = true;
	        }
	        if (qName.equals(OperacionElement.TIPO.getName())) {
	            tipo = true;
	        }
	        if (qName.equals(OperacionElement.PROPIETARIO.getName())) {
	        	
	            propietario = true;
	        }
	        if (qName.equals(OperacionElement.OPERACION.getName())) {
	        	operacion.setFechahora(attributes.getValue("fechahora"));
	        }
	        
		
		
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(OperacionElement.OPERACION.getName())) {
			operaciones.add(operacion);
			operacion = new Operacion();
        }
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (cantidad) {
			operacion.setCantidad(new String(ch, start, length));
			cantidad=false;
			
		}
		if (numcuenta) {
			operacion.setNumcuenta(new String(ch, start, length));
			numcuenta=false;
		}
		if (saldo) {
			operacion.setSaldo(new String(ch, start, length));
			saldo=false;
		}
		if (tipo) {
			operacion.setTipo(new String(ch, start, length));
			tipo=false;
		}
		
		if (propietario) {
			operacion.setPropietario(new String(ch, start, length));
			propietario=false;
		}
		
		
	}
	
	public List<Operacion> getOperaciones() {
        return operaciones;
    }
	
	

	
	
}
