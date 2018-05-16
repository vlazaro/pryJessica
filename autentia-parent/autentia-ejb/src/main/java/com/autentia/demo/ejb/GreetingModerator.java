package com.autentia.demo.ejb;

import javax.ejb.Local;

/**
 * Interfaz para el moderador de saludos. Este se encarga de validar si los nuevos mensajes de saludo son politicamente
 * correctos ;)
 */
@Local
public interface GreetingModerator {

	public boolean isValid(String message);

}
