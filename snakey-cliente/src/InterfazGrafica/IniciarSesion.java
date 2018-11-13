package InterfazGrafica;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class IniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ImageIcon fondo;
	JFrame yo;
	PanelDeFondo iniciaarSesion;
	private JPasswordField claveUsuario;
	private JTextField txtNombre;
	private JLabel lblNewLabel;
	private JLabel lblClave;

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
		yo = null;

		setTitle("Inicio de sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new PanelDeFondo("fondoIniciarSesion.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

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

					SalasDisponibles disponible = new SalasDisponibles(jugador);
					disponible.setLocationRelativeTo(null);
					disponible.setResizable(false);
					disponible.setVisible(true);
					dispose();
				}

			}
		});

		JButton btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CrearUsuario nuevoUsuario = new CrearUsuario(yo);
				nuevoUsuario.setLocationRelativeTo(null);
				nuevoUsuario.setVisible(true);
				nuevoUsuario.setResizable(false);

			}
		});

		claveUsuario = new JPasswordField();
		claveUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		claveUsuario.setToolTipText("password");

		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setText("usuario");
		txtNombre.setColumns(10);

		lblNewLabel = new JLabel("USUARIO");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblClave = new JLabel("CLAVE");
		lblClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblClave.setForeground(Color.BLACK);
		lblClave.setFont(new Font("Stencil", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(claveUsuario, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addComponent(lblClave, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 354,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 111,
												GroupLayout.PREFERRED_SIZE)
										.addGap(132)
										.addComponent(btnCrearUsuario, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblClave, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(claveUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE).addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(btnIniciar).addComponent(btnCrearUsuario))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

	}
}
