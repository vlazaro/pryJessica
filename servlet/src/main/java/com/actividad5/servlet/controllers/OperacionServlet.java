package com.actividad5.servlet.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activida5.servlet.domain.Cuentasbancaria;
import com.activida5.servlet.domain.Operacione;
import com.actividad5.servlet.service.CuentasBancariasService;
import com.actividad5.servlet.service.HistorialService;
import com.actividad5.servlet.service.OperacionesService;


/**
 * Servlet implementation class OperacionServlet
 */
@WebServlet(name="Operacion" , urlPatterns="/Operacion")
public class OperacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	OperacionesService opservice;
	@EJB
	CuentasBancariasService ctaservice;
	@EJB
	HistorialService histservice;
	
	
   
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String numcuenta =  request.getParameter("numcuenta");
		String cantidad = request.getParameter("cantidad");
		String estado,codigo ;
		
		Operacione op = new Operacione();
		op.setCantidad(Double.parseDouble(cantidad));
		Cuentasbancaria cuentabancaria = ctaservice.getbyNumCuenta(numcuenta);
		if (cuentabancaria != null) {
			op.setCuentasbancaria(cuentabancaria );
			op.setFechahora(new Date());
			String tipo;
			if (Double.parseDouble(cantidad) >= 0) {
				tipo = "I";
			}else {
				tipo = "E";
			}
			op.setTipo(tipo);
			op.setSaldoactualizado(Double.parseDouble(cantidad));
			opservice.save(op);
			estado = "Opearación creada correctamente";
			codigo = "OK";
			
			histservice.saveHistorialOperacion(cuentabancaria.getPropietario(), op);
			
		}else {
			estado = "Cuenta no Existe";
			codigo = "KO";
		}
		
	      Mensaje mensaje = new Mensaje();
		  mensaje.setMensaje(estado);
		  mensaje.setCodigo(codigo);
		  RequestDispatcher view = request.getRequestDispatcher("/mensaje.jsp");
		  request.setAttribute("mensaje", mensaje);
	      view.forward(request, response);
		
		
		
	}
	
	

}
