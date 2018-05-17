package com.actividad5.servlet.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activida5.servlet.domain.Propietario;
import com.actividad5.servlet.service.PropietarioService;

/**
 * Servlet implementation class PropietarioServlet
 */
@WebServlet(name = "Propietario", urlPatterns = "/Propietario")
public class PropietarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PropietarioService propservice;

	public PropietarioServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String dni = request.getParameter("dni");
		String nombre= request.getParameter("nombre");
		String primerapellido= request.getParameter("primerapellido");
		String segundoapellido= request.getParameter("segundoapellido");
		String usuario= request.getParameter("usuario");
		String numerosecreto= request.getParameter("numerosecreto");
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Propietario pro = new Propietario();
			propservice =new PropietarioService();
			pro.setDni(dni);
			pro.setNombre(nombre);
			pro.setPrimerapellido(primerapellido);
			pro.setSegundoapellido(segundoapellido);
			pro.setUsuario(usuario);
			pro.setNumerosecreto(numerosecreto);
			propservice.save(pro);	
			response_page(out,"OK");
		}
		
	}
	private void response_page(PrintWriter out ,String estado) {
		 out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Insertar Propietario</title>");
        out.println("<link rel=\"stylesheet\" href=\"testEJBStyle.css\" />");
        out.println("</head>");
        out.println("<body>");
        if ("OK".equals(estado)) {
       	 out.println("Propietario Creado Correctamente");
        }else if("PROP_NO_EXISTE".equals(estado)) {
       	 out.println("Propietario no existe");
        }
		 out.println("<form action=\"propietario.html\" method=\"POST\">"
                + "Volver a la pagina inicial"
                + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                + "</form>");
        out.println("</body>");
        out.println("</html>");
        
		
	}
}
