package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ImageIcon fondo;
	private JPasswordField ClaveUsuario;
	private JTextField txtNombre;
	JFrame yo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion();
					frame.setResizable(false);// no lo puedo renderizar
					frame.setVisible(true); // visible al usuario
					frame.setLocationRelativeTo(null); // para que se posisione en el medio de la pantalla

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public IniciarSesion() {
		setTitle("Inicio de sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JButton btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CrearUsuario nuevoUsuario = new CrearUsuario(yo);
				nuevoUsuario.setVisible(true);
				nuevoUsuario.setLocationRelativeTo(null);
				nuevoUsuario.setResizable(false);

			}
		});

		ClaveUsuario = new JPasswordField();
		ClaveUsuario.setToolTipText("password");

		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblClave.setFont(new Font("Stencil", Font.PLAIN, 20));

		JButton btnIniciar = new JButton("Iniciar Sesion");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Jugador jugador = new Jugador();
				jugador.setNombreDeUsuario(txtNombre.getText());

				/* Obtengo la clave del usuario para luego consultarlo en la BD */

				char[] arrayDeChars = ClaveUsuario.getPassword();
				String clave = new String(arrayDeChars);
				jugador.setClaveDeUsuario(clave);

				if (jugador.getNombreDeUsuario().trim().length() == 0
						|| jugador.getClaveDeUsuario().trim().length() == 0) {/* Valido los datos */
					JOptionPane.showMessageDialog(null, "Datos Incompletos", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {

					/* BUSCAR EN BASE DE DATOS Y CONSULTAR */

					dispose();
					SalasDisponibles disponible = new SalasDisponibles(jugador);
					disponible.setLocationRelativeTo(null);
					disponible.setResizable(false);
					disponible.setVisible(true);

				}

			}
		});

		txtNombre = new JTextField();
		txtNombre.setText("usuario");
		txtNombre.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Stencil", Font.PLAIN, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(136)
							.addComponent(btnCrearUsuario, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(ClaveUsuario, Alignment.LEADING)
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
					.addComponent(ClaveUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIniciar)
						.addComponent(btnCrearUsuario)))
		);
		panel.setLayout(gl_panel);

	}
}
