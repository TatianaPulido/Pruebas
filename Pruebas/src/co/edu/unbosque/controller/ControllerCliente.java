package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class ControllerCliente extends Thread implements ActionListener  {

	private View gui;
	private Cuestionario cuestionario;
	private Jugador jugador;
	private Socket socket;

	public ControllerCliente() {
		gui = new View();
		actionListener(this);
		ObjectInputStream in;
		DataOutputStream out;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			socket = new Socket(ip, 5000);
			in = new ObjectInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			cuestionario = (Cuestionario) in.readObject();
			gui.getPanelJuego().getTxtA_Pregunta()
					.setText(cuestionario.getPregunta());
			String[] listaRespuestas = new String[4];
			listaRespuestas = cuestionario.getRespuestas();
			// Mezclar lista de respuestas
			List<String> lista = Arrays.asList(listaRespuestas);
			Collections.shuffle(lista);
			for (int i = 0; i < listaRespuestas.length; i++) {
				listaRespuestas[i] = lista.get(i).toString();
				gui.getPanelJuego().getBtnOpciones()[i]
						.setText(listaRespuestas[i]);
			}

			socket.close();
		} catch (IOException e) {
			System.out.println("CLIENTE:  Error en la conexión");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
				jugador = new Jugador(nombreJugador, 0);
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
public String juego(){
	String salida="";
	
	return salida;
}
	public void puntaje() {
		String resSele = cuestionario.getRespuestaSeleccionada();
		String resCorrecta = cuestionario.getRespuestaCorrecta();
		if (resCorrecta.equals(resSele)) {
			JOptionPane.showMessageDialog(null, "Correcto");
			jugador.setPuntuacion(jugador.getPuntuacion() + 3);
			JOptionPane.showMessageDialog(null, jugador.getPuntuacion());
		} else {
			JOptionPane.showMessageDialog(null, "Incorrecto");
			if (jugador.getPuntuacion() >= 2) {
				jugador.setPuntuacion(jugador.getPuntuacion() - 2);
				JOptionPane.showMessageDialog(null, jugador.getPuntuacion());
			} else {
				jugador.setPuntuacion(0);
				JOptionPane.showMessageDialog(null, jugador.getPuntuacion());
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
		gui.getPanelJuego().getBtnAyuda50().setEnabled(false);
	}
}
