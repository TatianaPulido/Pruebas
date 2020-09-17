package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JOptionPane;

import co.edu.unbosque.model.Cuestionario;
import co.edu.unbosque.model.Jugador;
import co.edu.unbosque.view.View;

public class ControllerCliente implements ActionListener {

	private View gui;
	private Cuestionario cuestionario;
	private Jugador jugador;
	private Socket socket;

	private ObjectInputStream in;
	private DataOutputStream out;

	Timer tim = new Timer();

	public ControllerCliente() {
		gui = new View();
		actionListener(this);
		iniciarJuego();

	}

	public void iniciarJuego() {
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
			System.out.println("CLIENTE:  Error en la conexi�n");
		}
	}

	/**
	 * El m�todo es invocado cuando ocurre una acci�n. <b>pre</b> Se debieron
	 * determinar los objetos que implementan el ActionListener desde la clase view.
	 * <br>
	 * <b>post</b> Un objeto que implementa el ActionListener adquiere el
	 * determinado ActionEvent cuando ocurra el evento. <br>
	 * 
	 * @param evento Representa un evento generado por un componente, que en su
	 *               mayor�a son botones o combobox. El evento pasa por todos los
	 *               objetos que tienen registrado un ActionListener, y as� poder
	 *               obtener un evento y generarlo. e != null, e != " ".
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource().equals(gui.getPanelInicio().getBtnJugar())) {

			String nombreJugador = gui.getPanelInicio().getTxtNombreUsuario().getText();
			if (!nombreJugador.isEmpty() && !nombreJugador.equals(null)) {
				gui.getPanelInicio().setVisible(false);
				gui.getPanelJuego().setVisible(true);
				jugador.setNombre(nombreJugador);
				jugador.setPuntuacion(0);
				contarTimer();

			} else {
				JOptionPane.showMessageDialog(null, "Ingrese su nombre para continuar");
			}

		} else if (evento.getSource().equals(gui.getPanelJuego().getBtnOpciones()[0])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego().getBtnOpciones()[0].getText());
			puntaje();
			contarTimer();

		} else if (evento.getSource().equals(gui.getPanelJuego().getBtnOpciones()[1])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego().getBtnOpciones()[1].getText());
			puntaje();
			contarTimer();

		} else if (evento.getSource().equals(gui.getPanelJuego().getBtnOpciones()[2])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego().getBtnOpciones()[2].getText());
			puntaje();
			contarTimer();

		} else if (evento.getSource().equals(gui.getPanelJuego().getBtnOpciones()[3])) {
			cuestionario.setRespuestaSeleccionada(gui.getPanelJuego().getBtnOpciones()[3].getText());
			puntaje();
			contarTimer();

		} else if (evento.getSource().equals(gui.getPanelJuego().getBtnAyuda50())) {
			ayuda();

		} else if (evento.getSource().equals(gui.getPanelResultado().getBtnVolveraJugar())) {
			gui.getPanelResultado().setVisible(false);
			gui.getPanelInicio().setVisible(true);
			gui.getPanelInicio().getBtnJugar().setText(null);
			finalizarPartida();
			iniciarJuego();

		}
	}

	public void actionListener(ControllerCliente controller) {
		gui.getPanelInicio().getBtnJugar().addActionListener(controller);
		for (int i = 0; i < 4; i++) {
			gui.getPanelJuego().getBtnOpciones()[i].addActionListener(controller);
		}
		gui.getPanelJuego().getBtnAyuda50().addActionListener(controller);
		gui.getPanelResultado().getBtnVolveraJugar().addActionListener(controller);
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
//			contarTimer();

		} else if (gui.getPanelJuego().getLblCronometro().getText().equals("00")) {

			JOptionPane.showMessageDialog(null, "Se acabo el tiempo");

		} else {

			JOptionPane.showMessageDialog(null, "Incorrecto");

			if (jugador.getPuntuacion() >= 2) {
				jugador.setPuntuacion(jugador.getPuntuacion() - 2);

			} else {

				jugador.setPuntuacion(0);
			}
//			contarTimer();

		}

		for (int i = 0; i < 4; i++) {
			gui.getPanelJuego().getBtnOpciones()[i].setEnabled(true);
		}

		gui.getPanelJuego().getBtnAyuda50().setEnabled(true);

		if (jugador.getNumeroPreguntas() <= 5) {

			try {

				out.writeUTF("Respondi");
//				contarTimer();

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
		while (aux < 2) {
			int num = rand.nextInt(4);
			int num2 = rand.nextInt(4);
			System.out.println(num);
			if (!opciones[num].equals(cuestionario.getRespuestaCorrecta()) && num != num2
					&& (!opciones[num2].equals(cuestionario.getRespuestaCorrecta()))) {
				gui.getPanelJuego().getBtnOpciones()[num].setEnabled(false);
				gui.getPanelJuego().getBtnOpciones()[num2].setEnabled(false);
				aux = aux + 2;
			}
		}
		cuestionario.setAyuda(true);
		gui.getPanelJuego().getBtnAyuda50().setEnabled(false);
	}

	public void recibirPregunta() {
		String[] listaRespuestas = new String[4];
		try {
			cuestionario = (Cuestionario) in.readObject();
			gui.getPanelJuego().getTxtA_Pregunta().setText(cuestionario.getPregunta());
			listaRespuestas = cuestionario.getRespuestas();

			// Mezclar lista de respuestas
			List<String> lista = Arrays.asList(listaRespuestas);
			Collections.shuffle(lista);
			for (int i = 0; i < listaRespuestas.length; i++) {
				listaRespuestas[i] = lista.get(i).toString();
				gui.getPanelJuego().getBtnOpciones()[i].setText(listaRespuestas[i]);
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

	public void contarTimer() {

		String timer;
		int seg;

		TimerTask task = new TimerTask() {

			int seg = 20;
			String timer = String.valueOf(seg);

			@Override
			public void run() {

				gui.getPanelJuego().getLblCronometro().setText(Integer.toString(seg));

				if (seg > 9) {
					gui.getPanelJuego().getLblCronometro().setText(Integer.toString(seg));

				} else if (seg >= 0 && seg < 10) {

					gui.getPanelJuego().getLblCronometro().setText("0" + Integer.toString(seg));

				}

				seg--;
				if (seg < 00) {
					tim.cancel();
					puntaje();

				}

//				for (int i = 0; i < gui.getPanelJuego().getBtnOpciones().length; i++) {
//					if (!gui.getPanelJuego().getBtnOpciones()[i].isEnabled()) {
//						tim.cancel();
//						puntaje();
//					}
//				}

			}
		};
		tim.schedule(task, 10, 1000);

	}

}
