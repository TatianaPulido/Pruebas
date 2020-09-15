package co.edu.unbosque.model;

public class Jugador {
	private String nombre;
	private int puntuacion;
	private int numeroPreguntas;

	public Jugador(String nombre, int puntuacion) {
		super();
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.numeroPreguntas=0;
	}

	public Jugador() {
		super();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the puntuacion
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param puntuacion
	 *            the puntuacion to set
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return the numeroPreguntas
	 */
	public int getNumeroPreguntas() {
		return numeroPreguntas;
	}

	/**
	 * @param numeroPreguntas the numeroPreguntas to set
	 */
	public void setNumeroPreguntas(int numeroPreguntas) {
		this.numeroPreguntas = numeroPreguntas;
	}

}
