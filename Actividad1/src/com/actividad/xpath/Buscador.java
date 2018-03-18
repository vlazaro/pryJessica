package com.actividad.xpath;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Buscador {

	XPathExpression exp;
	Element element;
	static Document XMLDoc;
	static XPath xpath;

	public static void main(String[] args) {

		setXPATH();

		Scanner sc = new Scanner(System.in);

		String condition = "N";
		do {

			System.out.print("Ingrese una consulta :");
			String consulta = sc.next(); // "//operacionesBancarias//operacion//propietario";
			executeQuery(consulta);

			System.out.println("Desea Salir [S|N]:");
			condition = sc.next();
		} while ("N".equals(condition.toUpperCase()));

	}

	private static void setXPATH() {

		try {
			xpath = XPathFactory.newInstance().newXPath();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			XMLDoc = builder.parse(new File("Ficheros\\operaciones.xml"));
		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		}
	}

	private static void executeQuery(String txtconsulta) {
		try {
			NodeList nodos = (NodeList) xpath.evaluate(txtconsulta, XMLDoc, XPathConstants.NODESET);
			for (int i = 0; i < nodos.getLength(); i++) {
				System.out.println(nodos.item(i).getChildNodes().item(0).getNodeValue());
			}
			if (nodos.getLength() == 0) {
				System.out.println("No existen nodos para esa consulta");
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		}
	}
}
