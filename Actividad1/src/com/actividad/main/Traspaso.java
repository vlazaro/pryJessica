package com.actividad.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.actividad.pojo.Operacion;
import com.actividad.pojo.OperacionesBancarias;

public class Traspaso {

	static OperacionesBancarias operacionesBancarias = new OperacionesBancarias();
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		// 1. Leer fichero operaciones.txt

		leerFicheroTxt();
		// 2. del punto pasarlo a xml
		
		escribirFicheroXML();

		
		
		// 3. guardar "lista de Operacion" en un fichero operaciones.xml

	}

	private static void escribirFicheroXML() {
		File file = new File("D:\\EstudiosJessica\\Linkia4\\workspace\\Actividad1\\Ficheros\\operaciones.xml");
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(OperacionesBancarias.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(operacionesBancarias, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
				
	}

	private static void leerFicheroTxt() throws IOException, ParseException {
		String archivo = "D:\\EstudiosJessica\\Linkia4\\workspace\\Actividad1\\Ficheros\\operaciones.txt";
		String cadena;
		List<Operacion> operaciones = new ArrayList<Operacion>();
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		int contador = 1;

		Operacion optmp = null;
		while ((cadena = b.readLine()) != null) {
			optmp = crearOperacion(optmp, cadena,contador);
			contador++;
			if (contador == 5) {
				operaciones.add(optmp);
				contador = 1;
			}
		}
		operacionesBancarias.setOperacion(operaciones);
		b.close();
	}

	private static Operacion crearOperacion(Operacion optmp, String cadena, int contador) throws ParseException {
		Operacion op = null;
		if (cadena.startsWith("@")) {
			op = new Operacion();
			splitCadenaFirstLine(op,cadena);
			
		} else {
			op = optmp;
			switch (contador) {
			case 2:
				op.setPropietario(cadena);
				break;
			case 3:
				String[] valor = cadena.split("-");
				op.setTipo(valor[0]);
				op.setCantidad(valor[1]);
				break;
			case 4:
				op.setSaldo(cadena);
				break;
			}
		}
		return op;
	}
	
	private static void splitCadenaFirstLine(Operacion op,String cadena) throws ParseException {
		String[] valores = cadena.split(" ");
		op.setFechahora( valores[1] + " " + valores[2]);
		op.setNumcuenta(valores[3]);
	}

}
