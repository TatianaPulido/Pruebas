package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.Arrays;

public class Cuestionario implements Serializable{
	private String[] respuestas ;
	private String pregunta;
	private String respuestaCorrecta;
	private String respuestaSeleccionada;
	private Boolean ayuda; 
	public Cuestionario(String[] respuestas, String pregunta,
			String respuestaCorrecta) {
		this.respuestas = new String [4];
		this.respuestas = respuestas;
		this.pregunta = pregunta;
		this.respuestaCorrecta = respuestaCorrecta;
		respuestaSeleccionada=null;
		this.ayuda=false;
	}
	/**
	 * @return the respuestas
	 */
	public String[] getRespuestas() {
		return respuestas;
	}
	/**
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}
	/**
	 * @return the respuestaCorrecta
	 */
	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}
	/**
	 * @param respuestas the respuestas to set
	 */
	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}
	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	/**
	 * @param respuestaCorrecta the respuestaCorrecta to set
	 */
	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	/**
	 * @return the respuestaSeleccionada
	 */
	public String getRespuestaSeleccionada() {
		return respuestaSeleccionada;
	}
	/**
	 * @param respuestaSeleccionada the respuestaSeleccionada to set
	 */
	public void setRespuestaSeleccionada(String respuestaSeleccionada) {
		this.respuestaSeleccionada = respuestaSeleccionada;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cuestionario [respuestas=" + Arrays.toString(respuestas)
				+ ", pregunta=" + pregunta + ", respuestaCorrecta="
				+ respuestaCorrecta + "]";
	}
	/**
	 * @return the ayuda
	 */
	public Boolean getAyuda() {
		return ayuda;
	}
	/**
	 * @param ayuda the ayuda to set
	 */
	public void setAyuda(Boolean ayuda) {
		this.ayuda = ayuda;
	}
	
}
