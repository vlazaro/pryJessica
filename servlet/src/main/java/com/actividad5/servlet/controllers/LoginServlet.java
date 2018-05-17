package com.actividad5.servlet.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activida5.servlet.domain.Propietario;
import com.actividad5.servlet.service.HistorialService;
import com.actividad5.servlet.service.PropietarioService;

/**
 * Servlet implementation class Login
 */
@WebServlet(name="Login" , urlPatterns="/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    PropietarioService propservice;
	@EJB
	HistorialService histservice;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String mensaje,codigo;
		Propietario prop = propservice.findbyusuariopassword(usuario, password);
		RequestDispatcher view ;
			if (prop != null) {
				mensaje = "Bienvenido usuario ";
				codigo = "OK";
				view = request.getRequestDispatcher("/menu.jsp");
				histservice.saveHistorialLogin(prop);
				
			}else {
				mensaje = "Usuario invalido ";
				codigo = "KO";
				view = request.getRequestDispatcher("/mensaje.jsp");
			}
			
			  Mensaje mens = new Mensaje();
			  mens.setMensaje(mensaje);
			  mens.setCodigo(codigo);
			  request.setAttribute("mensaje", mens);
		      view.forward(request, response);
	}
	
//	private void response_page(PrintWriter out ,String estado) {
//		out.println("<!DOCTYPE html>");
//        out.println("<html>");
//        out.println("<head>");
//        out.println("<title>Login</title>");
//        out.println("<link rel=\"stylesheet\" href=\"testEJBStyle.css\" />");
//        out.println("</head>");
//        out.println("<body>");
//        if ("OK".equals(estado)) {
//       	 out.println("Bienvenido !!");
//        }else if("KO".equals(estado)) {
//       	 out.println("Propietario no existe");
//        }
//		 out.println("<form action=\"login.html\" method=\"POST\">"
//                + "Volver a la pagina inicial"
//                + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
//                + "</form>");
//        out.println("</body>");
//        out.println("</html>");
//        
//		
//	}
	

}
