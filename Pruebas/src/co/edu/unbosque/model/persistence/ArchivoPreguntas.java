package co.edu.unbosque.model.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import co.edu.unbosque.model.Cuestionario;

public class ArchivoPreguntas {
	private Properties archivo;

	public ArchivoPreguntas() throws IOException {

		try {
			archivo=new Properties();
			InputStream is = new FileInputStream(
					".\\data\\Preguntas.properties");
			archivo.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Cuestionario preguntas(int indicePregunta) {
		String pregunta = archivo.getProperty("pregunta" + indicePregunta);
		String respuestaCorrecta = archivo.getProperty("pregunta"
				+ indicePregunta + ".respuestaCorrecta");
		String[] respuestas = new String[4];
		respuestas[0] =respuestaCorrecta;
		respuestas[1] = archivo.getProperty("pregunta" + indicePregunta
				+ ".respuesta1");
		respuestas[2] = archivo.getProperty("pregunta" + indicePregunta
				+ ".respuesta2");
		respuestas[3] = archivo.getProperty("pregunta" + indicePregunta
				+ ".respuesta3");
		Cuestionario cuestionario = new Cuestionario(respuestas, pregunta,
				respuestaCorrecta);
		return cuestionario;
	}

	/**
	 * @return the archivo
	 */
	public Properties getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Properties archivo) {
		this.archivo = archivo;
	}

}
