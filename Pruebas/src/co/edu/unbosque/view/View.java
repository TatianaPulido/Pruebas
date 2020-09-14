package co.edu.unbosque.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private PanelJuego panelJuego; // Se declara un objeto de tipo PanelJuego
	private Panel_Inicio panelInicio; // Se declara un objeto de tipo Panel_Inicio
	private PanelResultado panelResultado; // Se declara un objeto de tipo PanelResultado

	/**
	 * 
	 * Este es el metodo constructor, el cual se le asigna la inicialización de los
	 * atributos, objetos y forma de organización del JFrame. De esta manera el
	 * objecto es creado con un valor inicial. Este método se llama automaticamente
	 * cuando se crea el objeto.<b>post</b> se genera la ventana con el panel
	 * seleccionado .<br>
	 */
	public View() {
		setTitle("SABELO-TODO");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);

		inicializarComponentes();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Este es el metodo inicializarComponentes, el cual se inicianilizan los
	 * paneles de esta manera cada Jpanel se crea con una ubicacion y se agrega al
	 * JFrame.
	 */
	private void inicializarComponentes() {

		panelJuego = new PanelJuego();
		panelJuego.setBounds(0, 0, 1000, 670);
		getContentPane().add(panelJuego);
		panelJuego.setBackground(Color.WHITE);

		panelInicio = new Panel_Inicio();
		panelInicio.setBounds(0, 0, 1000, 670);
		getContentPane().add(panelInicio);
		panelInicio.setBackground(Color.WHITE);

		panelResultado = new PanelResultado();
		panelResultado.setBounds(0, 0, 1000, 670);
		getContentPane().add(panelResultado);
		panelResultado.setBackground(Color.WHITE);
	}

	/**
	 * Este metodo es llamado para mostrar algun tipo de mensaje <b>post</b>Se debe
	 * llamar el metodo y dar un valor a los parametros para asignar un mensaje,
	 * titulo del mensaje y tipo.<br>
	 * 
	 * @param mensaje Es el mensaje que se recibe del controlador y con este se
	 *                muestra el JOption deseado != "".
	 * @param titulo  Este es el titulo que se le da al mensaje.
	 * @param tipo    Es el tipo de mensaje que queremos escojer.
	 */
	public void mostrarMensaje(String mensaje, String titulo, int tipo) {
		if (tipo == 1) {
			JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);

		} else if (tipo == 2) {
			JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.WARNING_MESSAGE);

		}
	}

	/**
	 * Este metodo devuelve el valor del PanelJuego. <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @return panelJuego Este parametro representa el nuevo valor del panel.
	 */
	public PanelJuego getPanelJuego() {
		return panelJuego;
	}

	/**
	 * Este metodo establece el valor del PanelJuego. <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @param panelJuego Este parametro representa el nuevo valor del panel.
	 */
	public void setPanelJuego(PanelJuego panelJuego) {
		this.panelJuego = panelJuego;
	}

	/**
	 * Este metodo devuelve el valor del Panel_Inicio. <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @return panelInicio Este parametro representa el nuevo valor del panel.
	 */
	public Panel_Inicio getPanelInicio() {
		return panelInicio;
	}

	/**
	 * Este metodo establece el valor del Panel_Inicio. <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @param panelInicio Este parametro representa el nuevo valor del panel.
	 */
	public void setPanelInicio(Panel_Inicio panelInicio) {
		this.panelInicio = panelInicio;
	}

	/**
	 * Este metodo devuelve el valor del PanelResultado. <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @return panelResultado Este parametro representa el nuevo valor del panel.
	 */
	public PanelResultado getPanelResultado() {
		return panelResultado;
	}

	/**
	 * Este metodo establece el valor del PanelResultado. <b>post</b>Se fija un
	 * nuevo valor al atributo.<br>
	 * 
	 * @param panelResultado Este parametro representa el nuevo valor del panel.
	 */
	public void setPanelResultado(PanelResultado panelResultado) {
		this.panelResultado = panelResultado;
	}

}
