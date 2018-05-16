package com.autentia.demo.ejb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * EJB de entidad que representa un saludo. Se guarda en la base de datos mediante JPA.
 */
@Entity
public class Greeting {

	public static final String DEFAULT_MESSAGE = "Hi !!!    Hello World !!!";
	
	private Integer id = null;

	private String message = null;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
