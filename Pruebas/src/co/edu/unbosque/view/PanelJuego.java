package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelJuego extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblCronometro;
	private JTextArea txtA_Pregunta;
	private JButton btnAyuda50;
	private JButton[] btnOpciones;

	/**
	 * Este es el metodo constructor el cual se le asigna la inicialización de los
	 * atributos y objectos. De esta manera el objecto es creado con un valor
	 * inicial. Este método se llama automaticamente cuando se crea el objeto.
	 * 
	 */
	public PanelJuego() {
		setLayout(null);
		inicializarComponentes();
		setVisible(false);
	}

	/**
	 * Este es el metodo Gráfico el cual se le asigna la inicialización de los
	 * atributos y objectos que asignan el fondo al panel . De esta manera el
	 * objecto es creado con un valor inicial. Este método se llama automaticamente
	 * cuando se crea el objeto en la ventana .
	 * 
	 * @param g se agregar una imagen al fondo del panel con las respectivas
	 *          propiedades != "".
	 */
	public void paint(Graphics g) {
		ImageIcon imagenFondo = new ImageIcon("imagenes\\Juego.png");
		g.drawImage(imagenFondo.getImage(), 0, 0, imagenFondo.getIconWidth(), imagenFondo.getIconHeight(), null);

		setOpaque(false);
		super.paint(g);
	}

	/**
	 * Este es el metodo inicializarComponentes el cual se inicianilizan los
	 * componentes De esta manera cada componente se crea con una ubicacion y se
	 * agrega el panel. Este método se llama automaticamente cuando se crea el
	 * objeto de la ventana que lo contiene . <b>post</b>se hacen visibles los
	 * componentes del panel.<br>
	 */
	private void inicializarComponentes() {

		lblCronometro = new JLabel("00", SwingConstants.CENTER);
		lblCronometro.setFont(new Font("Tahoma", Font.PLAIN, 70));
		lblCronometro.setBounds(793, 40, 115, 115);
		lblCronometro.setOpaque(false);
		lblCronometro.setBackground(new Color(0, 0, 0, 0));
		add(lblCronometro);

		txtA_Pregunta = new JTextArea();
		txtA_Pregunta.setEditable(false);
		txtA_Pregunta.setFont(new Font("Bell MT", Font.PLAIN, 38));
		txtA_Pregunta.setBounds(88, 200, 810, 195);
		txtA_Pregunta.setOpaque(false);
		txtA_Pregunta.setForeground(Color.WHITE);
		txtA_Pregunta.setBackground(new Color(0, 0, 0, 0));
		txtA_Pregunta.setLineWrap(true);
		txtA_Pregunta.setWrapStyleWord(true);
		add(txtA_Pregunta);

		btnAyuda50 = new JButton();
		btnAyuda50.setBounds(572, 45, 115, 115);
		btnAyuda50.setOpaque(false);
		btnAyuda50.setBackground(new Color(0, 0, 0, 0));
		btnAyuda50.setBorderPainted(false);
		add(btnAyuda50);
		btnOpciones = new JButton[4];
		for (int i = 0; i < 4; i++) {
			btnOpciones[i] = new JButton();
			btnOpciones[i].setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnOpciones[i].setOpaque(false);
			btnOpciones[i].setBorderPainted(false);
			btnOpciones[i].setBackground(new Color(0, 0, 0, 0));
			add(btnOpciones[i]);
		}
		btnOpciones[0].setBounds(84, 445, 354, 78);
		btnOpciones[1].setBounds(550, 445, 354, 78);
		btnOpciones[2].setBounds(84, 558, 354, 78);
		btnOpciones[3].setBounds(550, 558, 354, 78);

	}

	/**
	 * Este metodo devuelve el valor del JLabel. <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @return Retorna el atributo en formato de JLabel.
	 */
	public JLabel getLblCronometro() {
		return lblCronometro;
	}

	/**
	 * Este metodo establece el valor del JLabel <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @param lblCronometro Este parametro representa el nuevo valor que va a tener
	 *                      el boton agregar. != "".
	 */
	public void setLblCronometro(JLabel lblCronometro) {
		this.lblCronometro = lblCronometro;
	}

	/**
	 * Este metodo devuelve el valor del JButton. <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @return Retorna el atributo en formato de JButton.
	 */
	public JButton getBtnAyuda50() {
		return btnAyuda50;
	}

	/**
	 * Este metodo establece el valor del JButton <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @param btnAyuda50 Este parametro representa el nuevo valor que va a tener el
	 *                   boton agregar. != "".
	 */
	public void setBtnAyuda50(JButton btnAyuda50) {
		this.btnAyuda50 = btnAyuda50;
	}

	/**
	 * @return the txtA_Pregunta
	 */
	public JTextArea getTxtA_Pregunta() {
		return txtA_Pregunta;
	}

	/**
	 * @param txtA_Pregunta the txtA_Pregunta to set
	 */
	public void setTxtA_Pregunta(JTextArea txtA_Pregunta) {
		this.txtA_Pregunta = txtA_Pregunta;
	}

	/**
	 * @return the btnOpciones
	 */
	public JButton[] getBtnOpciones() {
		return btnOpciones;
	}

	/**
	 * @param btnOpciones the btnOpciones to set
	 */
	public void setBtnOpciones(JButton[] btnOpciones) {
		this.btnOpciones = btnOpciones;
	}

}
