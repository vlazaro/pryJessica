package com.autentia.demo.ejb;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Este es un saludador listillo y educado. Antes de añadir un nuevo mensaje de saludo a la base de datos o de dar un saludo
 * sacado de la base de datos (por si otro saludador menos educado a metido palabros en la base de datos) , consulta con el
 * moderador para ver si es politicamente correcto.
 */
@Stateless
public class PoliteSmartGreeterBean implements Greeter {

	private static final Random random;
	static {
		final Calendar calendar = Calendar.getInstance();
		final long millis = calendar.getTimeInMillis();
		random = new Random(millis);
	}

	@PersistenceContext
	private EntityManager em = null;

	@EJB
	private GreetingModerator moderator = null;

	public String sayHi() {
		final List<Greeting> greetings = em.createQuery("from Greeting").getResultList();

		String message = Greeting.DEFAULT_MESSAGE;

		if (greetings.size() > 0) {
			for (int chance = 0; chance < 3; chance++) {
				final int randomMessage = random.nextInt(greetings.size());
				final String storedMessage = greetings.get(randomMessage).getMessage();

				if (moderator.isValid(storedMessage)) {
					message = storedMessage;
					break;
				}
			}
		}

		return message;
	}

	public void addGreeting(String message) throws InvalidGreetingMessageException {
		if (message == null) {
			return;
		}

		if (!moderator.isValid(message)) {
			throw new InvalidGreetingMessageException("The greeting message is not enough polite!!!");
		}
		
		final Greeting greeting = new Greeting();
		greeting.setMessage(message);

		em.persist(greeting);
	}
}
