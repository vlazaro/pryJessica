package com.autentia.demo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.autentia.demo.ejb.Greeter;
import com.autentia.demo.ejb.InvalidGreetingMessageException;


public class GreeterServlet extends HttpServlet {

	private static final long serialVersionUID = 3031665322987189909L;

	private Greeter greeter = null;

	/**
	 * Al crear el servlet se hace el lookup del EJB.
	 * Para el ejemplo tenemos disponibles los siguientes EJBs:
	 * <ul>
	 * 	<li>DummyGreeterBean/local : Saludador sin memoria.</li>
	 * 	<li>SmartGreeterBean/local : Saludador listillo.</li>
	 * 	<li>PoliteSmartGreeterBean/local : Saludador listillo y educado.</li>
	 * </ul>
	 */
	public GreeterServlet() {
		final Context context;
        try {
            context = new InitialContext();
			greeter = (Greeter)context.lookup("PoliteSmartGreeterBean/local");

		} catch (NamingException e) {
            e.printStackTrace();
        }
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		final PrintWriter out = res.getWriter();

		final String newGreeting = req.getParameter("addGreeting");
		
		try {
			greeter.addGreeting(newGreeting);
			out.println(greeter.sayHi());
			
		} catch (InvalidGreetingMessageException e) {
			out.println("El saludador no admite ese mensaje de saludo. El saludador dice: " + e.getMessage());
		}
		
	}
}
