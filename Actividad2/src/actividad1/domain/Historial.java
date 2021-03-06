package actividad1.domain;

import java.sql.Date;

public class Historial {

	private int id;
	private String tipoEvento;
	private Date fecha_horaevento;
	private int id_cuentabancaria;
	private String dni_propietario;
	
	
	public Historial(String tipoEvento, Date fecha_horaevento, int id_cuentabancaria) {
		super();
		this.tipoEvento = tipoEvento;
		this.fecha_horaevento = fecha_horaevento;
		this.id_cuentabancaria = id_cuentabancaria;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public Date getFecha_horaevento() {
		return fecha_horaevento;
	}
	public void setFecha_horaevento(Date fecha_horaevento) {
		this.fecha_horaevento = fecha_horaevento;
	}
	public int getId_cuentabancaria() {
		return id_cuentabancaria;
	}
	public void setId_cuentabancaria(int id_cuentabancaria) {
		this.id_cuentabancaria = id_cuentabancaria;
	}

	public String getDni_propietario() {
		return dni_propietario;
	}

	public void setDni_propietario(String dni_propietario) {
		this.dni_propietario = dni_propietario;
	}
	
	
	
}
