package com.autentia.demo.ejb;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Este es un saludador listillo. Se acuerda de todos los nuevos mensajes de saludo que le vamos diciendo, y nos saluda con
 * uno de ellos al azar.
 */
@Stateless
public class SmartGreeterBean implements Greeter {

	private static final Random random;
	static {
		final Calendar calendar = Calendar.getInstance();
		final long millis = calendar.getTimeInMillis();
		random = new Random(millis);
	}

	@PersistenceContext
	private EntityManager em;

	public String sayHi() {
		final List<Greeting> greetings = em.createQuery("from Greeting").getResultList();

		String message = Greeting.DEFAULT_MESSAGE;

		if (greetings.size() > 0) {
			final int randomMessage = random.nextInt(greetings.size());
			message = greetings.get(randomMessage).getMessage();
		}

		return message;
	}

	public void addGreeting(String message) throws InvalidGreetingMessageException {
		if (message == null) {
			return;
		}

		final Greeting greeting = new Greeting();
		greeting.setMessage(message);

		em.persist(greeting);
	}
}
