package co.edu.unbosque.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

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
			}
		} catch (IOException e) {
			System.out.println("SERVIDOR:  Error en la conexión");
		}

	}

	
}
