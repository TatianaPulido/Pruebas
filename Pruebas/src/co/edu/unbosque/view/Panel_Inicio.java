package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_Inicio extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnJugar;
	private JTextField txtNombreUsuario;

	public Panel_Inicio() {
		setLayout(null);
		inicializarComponentes();
		setVisible(true);
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
		ImageIcon imagenFondo = new ImageIcon("imagenes\\Inicio.png");
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

		btnJugar = new JButton();
		btnJugar.setBounds(300, 482, 400, 100);
		btnJugar.setOpaque(false);
		btnJugar.setBackground(new Color(0, 0, 0, 0));
		add(btnJugar);

		txtNombreUsuario = new JTextField("");
		txtNombreUsuario.setBounds(188, 358, 619, 70);
		txtNombreUsuario.setOpaque(false);
		txtNombreUsuario.setBackground(new Color(0, 0, 0, 0));
		txtNombreUsuario.setBorder(null);
		txtNombreUsuario.setFont(new Font("Bell MT", Font.PLAIN, 32));
		add(txtNombreUsuario);

	}

	/**
	 * Este metodo devuelve el valor del JButton. <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @return Retorna el atributo en formato de JButton.
	 */
	public JButton getBtnJugar() {
		return btnJugar;
	}

	/**
	 * Este metodo establece el valor del JButton <b>post</b>Se fija un nuevo valor
	 * al atributo.<br>
	 * 
	 * @param btnJugar Este parametro representa el nuevo valor que va a tener el
	 *                 boton agregar. != "".
	 */
	public void setBtnJugar(JButton btnJugar) {
		this.btnJugar = btnJugar;
	}

	/**
	 * Este metodo devuelve el valor del JTextField. <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @return Retorna el atributo en formato de JTextField.
	 */
	public JTextField getTxtNombreUsuario() {
		return txtNombreUsuario;
	}

	/**
	 * Este metodo establece el valor del JTextField <b>post</b>Se fija un nuevo
	 * valor al atributo.<br>
	 * 
	 * @param txtNombreUsuario Este parametro representa el nuevo valor que va a
	 *                         tener el boton agregar. != "".
	 */
	public void setTxtNombreUsuario(JTextField txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}

}
