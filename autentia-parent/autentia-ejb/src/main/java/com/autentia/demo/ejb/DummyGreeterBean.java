package com.autentia.demo.ejb;

import javax.ejb.Stateless;

/**
 * Saludador que no es capaz de recordar nada, sólo sabe dar un saludo.
 */
@Stateless
public class DummyGreeterBean implements Greeter, GreeterRemote {

	public String sayHi() {
		return "Hi !!!    Hello World !!!";
	}

	public void addGreeting(String message) throws InvalidGreetingMessageException {
		// I can't remember more greetings  :'(
	}
}
