package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelResultado extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnVolveraJugar;
	private JTextArea txtA_VictoriaDerrota;

	public PanelResultado() {
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
		ImageIcon imagenFondo = new ImageIcon("imagenes\\Resultado.png");
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

		btnVolveraJugar = new JButton();
		btnVolveraJugar.setBounds(178, 510, 638, 65);
		btnVolveraJugar.setFont(new Font("Bell MT", Font.PLAIN, 17));
		btnVolveraJugar.setOpaque(false);
		btnVolveraJugar.setBackground(new Color(0, 0, 0, 0));
		btnVolveraJugar.setBorderPainted(false);
		add(btnVolveraJugar);

		txtA_VictoriaDerrota = new JTextArea();
		txtA_VictoriaDerrota.setBounds(150, 250, 693, 192);
		txtA_VictoriaDerrota.setEditable(true);
		txtA_VictoriaDerrota.setFont(new Font("Bell MT", Font.PLAIN, 45));
		txtA_VictoriaDerrota.setOpaque(false);
		txtA_VictoriaDerrota.setBackground(new Color(0, 0, 0, 0));
		txtA_VictoriaDerrota.setForeground(Color.WHITE);
		txtA_VictoriaDerrota.setLineWrap(true);
		txtA_VictoriaDerrota.setWrapStyleWord(true);
		add(txtA_VictoriaDerrota);

	}

	/**
	 * Este metodo devuelve el valor del JButton. <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @return Retorna el atributo en formato de JButton.
	 */
	public JButton getBtnVolveraJugar() {
		return btnVolveraJugar;
	}

	/**
	 * Este metodo establece el valor del JButton <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @param btnVolveraJugar Este parametro representa el nuevo valor que va a
	 *                        tener el boton agregar. != "".
	 */
	public void setBtnVolveraJugar(JButton btnVolveraJugar) {
		this.btnVolveraJugar = btnVolveraJugar;
	}

	/**
	 * Este metodo devuelve el valor del JTextArea. <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @return Retorna el atributo en formato de JTextArea.
	 */
	public JTextArea getTxtA_VictoriaDerrota() {
		return txtA_VictoriaDerrota;
	}

	/**
	 * Este metodo establece el valor del JTextArea <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @param txtA_VictoriaDerrota Este parametro representa el nuevo valor que va a
	 *                             tener el boton agregar. != "".
	 */
	public void setTxtA_VictoriaDerrota(JTextArea txtA_VictoriaDerrota) {
		this.txtA_VictoriaDerrota = txtA_VictoriaDerrota;
	}

}
