package com.actividad5.servlet.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activida5.servlet.domain.Cuentasbancaria;
import com.activida5.servlet.domain.Operacione;
import com.actividad5.servlet.service.CuentasBancariasService;
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
	
   
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String numcuenta =  request.getParameter("numcuenta");
		String cantidad = request.getParameter("cantidad");
		String estado ;
		
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
			estado = "OK";	
		}else {
			estado = "KO";
		}
		try (PrintWriter out = response.getWriter()) {
			response_page(out , estado);
		}
			
		
		
		
	}
	
	private void response_page(PrintWriter out ,String estado) {
		 out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Operaciones</title>");
        out.println("<link rel=\"stylesheet\" href=\"testEJBStyle.css\" />");
        out.println("</head>");
        out.println("<body>");
        if ("OK".equals(estado)) {
       	 out.println("Operacion creada Correctamente");
        }else if("KO".equals(estado)) {
       	 out.println("Cuenta no existe");
        }
		 out.println("<form action=\"operaciones.html\" method=\"POST\">"
                + "Volver a la pagina inicial"
                + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                + "</form>");
        out.println("</body>");
        out.println("</html>");
        
		
	}


}
