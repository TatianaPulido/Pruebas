package co.edu.unbosque.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import co.edu.unbosque.model.persistence.ArchivoPreguntas;

public class ControllerServidor {
	private ArchivoPreguntas archivoPreguntas;

	public ControllerServidor() {

		ServerSocket servidor;
		Socket cliente;
		DataInputStream in;
		ObjectOutputStream out;
		try {
			archivoPreguntas = new ArchivoPreguntas();
			servidor = new ServerSocket(5000);
			int i = 0;
			while (true) {

				cliente = servidor.accept();

				out = new ObjectOutputStream(cliente.getOutputStream());
				int indice = (int) (Math.random() * 60 + 1);
				out.writeObject(archivoPreguntas.preguntas(indice));

				in = new DataInputStream(cliente.getInputStream());

				String continua = in.readUTF();
				System.out.println(continua);
				
				ArrayList<Integer> numeros = new ArrayList<Integer>();
				numeros.add(indice);

				int indice2 = (int) (Math.random() * 60 + 1);

				if (indice2 != indice) {
					out.writeObject(archivoPreguntas.preguntas(indice2));
				}

				int indice3 = (int) (Math.random() * 60 + 1);

				if (indice3 != indice2 && indice3 != indice) {
					out.writeObject(archivoPreguntas.preguntas(indice3));
				}

				int indice4 = (int) (Math.random() * 60 + 1);

				if (indice4 != indice3 && indice4 != indice2 && indice4 != indice) {
					out.writeObject(archivoPreguntas.preguntas(indice4));
				}

				int indice5 = (int) (Math.random() * 60 + 1);

				if (indice5 != indice4 && indice5 != indice3 && indice5 != indice2 && indice5 != indice) {
					out.writeObject(archivoPreguntas.preguntas(indice5));
				}

				continua = in.readUTF();
				System.out.println(continua);
				System.out.println("Cinco preguntas respondidas");

			}
		} catch (IOException e) {
			System.out.println("SERVIDOR:  Error en la conexión");

		}

	}

}
