package InterfazGrafica;

import javax.swing.JPanel;
import javax.print.DocFlavor.URL;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.sun.prism.Image;

import Commons.Jugador;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelIniciaarSesion extends JPanel{
	private JPasswordField claveUsuario;
	private JTextField txtNombre;
	ImageIcon imagenFondo;
	private String fondoDireccion;
	int x,y;
	
	public PanelIniciaarSesion() {
		//fondo=fondoDireccion;
		JFrame yo = null;
        this.x = this.getWidth();
        this.y = this.getHeight();
        this.setSize(x, y);	
		//java.net.URL direccion = getClass().getResource(fondo);
	    //image = new ImageIcon(direccion).getImage();
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnIniciar = new JButton("Iniciar Sesion");
		btnIniciar.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				Jugador jugador = new Jugador();
				jugador.setNombreDeUsuario(txtNombre.getText());

				/* Obtengo la clave del usuario para luego consultarlo en la BD */

				char[] arrayDeChars = claveUsuario.getPassword();
				String clave = new String(arrayDeChars);
				jugador.setClaveUsuario(clave);

				if (jugador.getNombreDeUsuario().trim().length() == 0
						|| jugador.getClaveUsuario().trim().length() == 0) {/* Valido los datos */
					JOptionPane.showMessageDialog(null, "Datos Incompletos", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {

					/* BUSCAR EN BASE DE DATOS Y CONSULTAR */

				//	dispose();
					SalasDisponibles disponible = new SalasDisponibles(jugador);
					disponible.setLocationRelativeTo(null);
					disponible.setResizable(false);
					disponible.setVisible(true);

				}
			}
		});
		
		JButton btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				CrearUsuario nuevoUsuario = new CrearUsuario(yo);
				nuevoUsuario.setVisible(true);
				nuevoUsuario.setLocationRelativeTo(null);
				nuevoUsuario.setResizable(false);

			}
		});
		
		claveUsuario = new JPasswordField();
		claveUsuario.setToolTipText("password");
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		txtNombre = new JTextField();
		txtNombre.setText("usuario");
		txtNombre.setColumns(10);
		
		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblClave.setFont(new Font("Dialog", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(136)
							.addComponent(btnCrearUsuario, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(claveUsuario, Alignment.LEADING)
							.addComponent(lblUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtNombre, Alignment.LEADING)
							.addComponent(lblClave, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(44)
					.addComponent(lblClave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(claveUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIniciar)
						.addComponent(btnCrearUsuario))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

	}
	
	public void paint(Graphics g){

	imagenFondo=new ImageIcon(getClass().getResource("fondo.jpeg"));
	g.drawImage(imagenFondo.getImage(),0,0,getSize().width,getSize().height,this);
	setOpaque(false);
	super.paint(g);
		
	}
	

}
