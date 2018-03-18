package com.actividad.sax;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.actividad.pojo.Operacion;

public class Lector {

	static SAXParser parser;
	static File ficheroXML;
	static ManejadorSAX sh;

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

		File f = new File("Ficheros", "operaciones.xml");
		if (!f.exists()) {
			System.out.println("Fichero " + f.getName() + " no existe");
		} else {
			System.out.println("-------------------");

			  SAXParserFactory factory = SAXParserFactory.newInstance();
		        SAXParser saxParser = factory.newSAXParser();
		        ManejadorSAX handler = new ManejadorSAX();
		        saxParser.parse("Ficheros/operaciones.xml", handler);
		        List<Operacion> list = handler.getOperaciones();
		        for (Operacion op : list) {
		            System.out.println("Propietario  : " + op.getPropietario());
		            System.out.println("Fecha Hora   : " + op.getFechahora());
		            System.out.println("Numero Cuenta: " + op.getNumcuenta());
		            System.out.println("Cantidad     : " + op.getCantidad());
		            System.out.println("Saldo        : " + op.getSaldo());
		            System.out.println("Tipo         : " + op.getTipo());
		            
		            System.out.println("---------------------------------------------------------------------------------------------");
		        }
		}
	}
}
