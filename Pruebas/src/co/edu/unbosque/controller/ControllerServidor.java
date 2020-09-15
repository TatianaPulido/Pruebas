package co.edu.unbosque.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

				in = new DataInputStream(cliente.getInputStream());

				String continua = "";
				do {
					
					if(!continua.equals("Terminamos")){
					int indice = (int) (Math.random() * 60 + 1);
					out.writeObject(archivoPreguntas.preguntas(indice));
					}
					continua = in.readUTF();
				} while (!continua.equals("Terminamos"));
				out.close();
				in.close();
				cliente.close();
				System.out.println("Conexion Cerrada en servidor");

			}
		} catch (IOException e) {
			System.out.println("SERVIDOR:  Error en la conexión");
		}

	}

}
