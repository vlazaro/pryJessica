package com.autentia.demo.ejb;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Moderador que compruebas si el mensaje de saludo contiene alguna palabra prohibida.
 */
@Stateless
public class GreetingModeratorBean implements GreetingModerator {

	final private List<String> forbiddenWords = Arrays.asList("caca", "culo", "pedo", "pis");

	public boolean isValid(String message) {
		final String[] words = message.split(" ");
		for (String w : words) {
			if (forbiddenWords.contains(w)) {
				return false;
			}
		}
		return true;
	}
}
