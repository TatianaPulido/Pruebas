package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import co.edu.unbosque.model.Cuestionario;
import co.edu.unbosque.model.Jugador;
import co.edu.unbosque.view.View;
public class ControllerCliente  implements ActionListener {

	private View gui;
	private Cuestionario cuestionario;
	private Jugador jugador;
	private Socket socket;

	private ObjectInputStream in;
	private DataOutputStream out;

	public ControllerCliente() {
		gui = new View();
		actionListener(this);
		jugador = new Jugador();
		try {

			InetAddress ip = InetAddress.getLocalHost();
			socket = new Socket(ip, 5000);
			in = new ObjectInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			int numPreguntas = jugador.getNumeroPreguntas();
			while (numPreguntas <= 5) {
				jugador.setNumeroPreguntas(numPreguntas);
				recibirPregunta();
				numPreguntas++;
			}
			finalizarPartida();
		} catch (IOException e) {
			System.out.println("CLIENTE:  Error en la conexión");
		}

	}

	/**
	 * El método es invocado cuando ocurre una acción. <b>pre</b> Se debieron
	 * determinar los objetos que implementan el ActionListener desde la clase
	 * view. <br>
	 * <b>post</b> Un objeto que implementa el ActionListener adquiere el
	 * determinado ActionEvent cuando ocurra el evento. <br>
	 * 
	 * @param evento
	 *            Representa un evento generado por un componente, que en su
	 *            mayoría son botones o combobox. El evento pasa por todos los
	 *            objetos que tienen registrado un ActionListener, y así poder
	 *            obtener un evento y generarlo. e != null, e != " ".
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(gui.getPanelInicio().getBtnJugar())) {

			String nombreJugador = gui.getPanelInicio().getTxtNombreUsuario()
					.getText();
			if (!nombreJugador.isEmpty()) {
				gui.getPanelInicio().setVisible(false);
				gui.getPanelJuego().setVisible(true);
				jugador.setNombre(nombreJugador);
				jugador.setPuntuacion(0);
			} else {
				JOptionPane.showMessageDialog(null,
						"Ingrese su nombre para continuar");
			}

		} else if (evento.getSource().equals(
				gui.getPanelJuego().getBtnOpciones()[0])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego()
					.getBtnOpciones()[0].getText());
			puntaje();

		} else if (evento.getSource().equals(
				gui.getPanelJuego().getBtnOpciones()[1])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego()
					.getBtnOpciones()[1].getText());
			puntaje();

		} else if (evento.getSource().equals(
				gui.getPanelJuego().getBtnOpciones()[2])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego()
					.getBtnOpciones()[2].getText());
			puntaje();

		} else if (evento.getSource().equals(
				gui.getPanelJuego().getBtnOpciones()[3])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego()
					.getBtnOpciones()[3].getText());
			puntaje();

		} else if (evento.getSource().equals(
				gui.getPanelJuego().getBtnAyuda50())) {
			ayuda();

		} else if (evento.getSource().equals(
				gui.getPanelResultado().getBtnVolveraJugar())) {
			gui.getPanelResultado().setVisible(false);
			gui.getPanelInicio().setVisible(true);
		}
	}

	public void actionListener(ControllerCliente controller) {
		gui.getPanelInicio().getBtnJugar().addActionListener(controller);
		for (int i = 0; i < 4; i++) {
			gui.getPanelJuego().getBtnOpciones()[i]
					.addActionListener(controller);
		}
		gui.getPanelJuego().getBtnAyuda50().addActionListener(controller);
		gui.getPanelResultado().getBtnVolveraJugar()
				.addActionListener(controller);
	}

	public void puntaje() {
		int anterior = jugador.getPuntuacion();
		String resSele = cuestionario.getRespuestaSeleccionada();
		String resCorrecta = cuestionario.getRespuestaCorrecta();
		Boolean ayuda = cuestionario.getAyuda();
		if (resCorrecta.equals(resSele)) {
			if (ayuda) {
				jugador.setPuntuacion(jugador.getPuntuacion() + 1);
			} else {

				jugador.setPuntuacion(jugador.getPuntuacion() + 3);
			}
			JOptionPane.showMessageDialog(null, "Correcto");
		} else {
			JOptionPane.showMessageDialog(null, "Incorrecto");

			if (jugador.getPuntuacion() >= 2) {
				jugador.setPuntuacion(jugador.getPuntuacion() - 2);

			} else {
				jugador.setPuntuacion(0);
			}
		}
		for (int i = 0; i < 4; i++) {
			gui.getPanelJuego().getBtnOpciones()[i].setEnabled(true);
		}
		gui.getPanelJuego().getBtnAyuda50().setEnabled(true);
		if (jugador.getNumeroPreguntas() <= 5) {
			try {
				out.writeUTF("Respondi");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void ayuda() {
		Random rand = new Random(System.nanoTime());

		String[] opciones = new String[4];
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = gui.getPanelJuego().getBtnOpciones()[i].getText();
		}

		int aux = 0;
		while (aux <= 2) {
			int num = rand.nextInt(4);
			if (!opciones[num].equals(cuestionario.getRespuestaCorrecta())) {
				gui.getPanelJuego().getBtnOpciones()[num].setEnabled(false);
				aux++;
			}
		}
		cuestionario.setAyuda(true);
		gui.getPanelJuego().getBtnAyuda50().setEnabled(false);
	}

	public void recibirPregunta() {
		String[] listaRespuestas = new String[4];
		try {
			cuestionario = (Cuestionario) in.readObject();
			gui.getPanelJuego().getTxtA_Pregunta()
					.setText(cuestionario.getPregunta());
			listaRespuestas = cuestionario.getRespuestas();

			// Mezclar lista de respuestas
			List<String> lista = Arrays.asList(listaRespuestas);
			Collections.shuffle(lista);
			for (int i = 0; i < listaRespuestas.length; i++) {
				listaRespuestas[i] = lista.get(i).toString();
				gui.getPanelJuego().getBtnOpciones()[i]
						.setText(listaRespuestas[i]);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public void finalizarPartida() {
		try {
			out.writeUTF("Terminamos");
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Conexion cerrada en cliente");

		gui.getPanelJuego().setVisible(false);
		gui.getPanelResultado().setVisible(true);
		String texto = "Tu puntaje es de " + jugador.getPuntuacion();
		gui.getPanelResultado().getTxtA_VictoriaDerrota().setText(texto);
	}

}
