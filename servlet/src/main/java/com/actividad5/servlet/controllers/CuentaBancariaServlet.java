package com.actividad5.servlet.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activida5.servlet.domain.Cuentasbancaria;
import com.activida5.servlet.domain.Propietario;
import com.actividad5.servlet.service.CuentasBancariasService;
import com.actividad5.servlet.service.PropietarioService;

/**
 * Servlet implementation class CuentaBancariaServlet
 */
@WebServlet(name="Cuentabancaria" , urlPatterns="/Cuentabancaria")
public class CuentaBancariaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   @EJB    
   CuentasBancariasService ctaservice;
   @EJB
   PropietarioService proservice;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String estado =null;
			String numcuenta = request.getParameter("numcuenta");
			String saldo = request.getParameter("saldo");
			String dni = request.getParameter("dni");
			Propietario prop = proservice.findbydni(dni);
			if (prop != null) {
				Cuentasbancaria cta = new Cuentasbancaria();
				cta.setNumcuenta(numcuenta);
				cta.setSaldo(Double.parseDouble(saldo));
				cta.setPropietario(prop);
				ctaservice.save(cta );
				estado = "OK";
			}else
			{
				estado = "PROP_NO_EXISTE";
			}
			response_page(out,estado);	
		}
	}
	
	
	private void response_page(PrintWriter out ,String estado) {
		 out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet InsertarActividad</title>");
         out.println("<link rel=\"stylesheet\" href=\"testEJBStyle.css\" />");
         out.println("</head>");
         out.println("<body>");
         if ("OK".equals(estado)) {
        	 out.println("Cuenta Creada Correctamente");
         }else if("PROP_NO_EXISTE".equals(estado)) {
        	 out.println("Propietario no existe");
         }
		 out.println("<form action=\"cuentabancaria.html\" method=\"POST\">"
                 + "Volver a la pagina inicial"
                 + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                 + "</form>");
         out.println("</body>");
         out.println("</html>");
         
		
	}

}
