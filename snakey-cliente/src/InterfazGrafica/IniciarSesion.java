package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas;
import Comunicacion.ManejadorDeRespuestas.EscuchadorLogin;
import Comunicacion.Requests.LoginRequest;
import Comunicacion.Responses.LoginResponse;

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

public class IniciarSesion extends JFrame implements EscuchadorLogin {
	private static final long serialVersionUID = 1L;
	static ImageIcon fondo;
	private JPanel contentPane;
	private JPasswordField passClaveUsuario;
	private JTextField textTxtNombre;
	private JFrame yo;
	private JButton btnIniciar;
	private JButton btnCrearUsuario;
	private JPanel panel;
	private JLabel lblClave;
	private JLabel lblUsuario;
	private GroupLayout gl_panel;
	
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
		ManejadorDeRespuestas.getInstancia().setEscuchadorLogin(this);
		setResizable(false);
		setVisible(true);
		setBackground(Color.BLUE);
		setLocationRelativeTo(null);
		setTitle("Inicio de sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		btnCrearUsuario = new JButton("Crear Usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearUsuario nuevoUsuario = new CrearUsuario(yo);
				nuevoUsuario.setLocationRelativeTo(null);
				nuevoUsuario.setResizable(false);
				nuevoUsuario.setVisible(true);
			}
		});

		passClaveUsuario = new JPasswordField();
		passClaveUsuario.setToolTipText("password");
		

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);

		lblClave = new JLabel("Clave");
		lblClave.setHorizontalAlignment(SwingConstants.CENTER);

		btnIniciar = new JButton("Iniciar Sesion");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jugador jugador = new Jugador();
				jugador.setNombreDeUsuario(textTxtNombre.getText());
				
				/* Obtengo la clave del usuario para luego consultarlo en la BD */

				char[] arrayDeChars = passClaveUsuario.getPassword();
				String clave = new String(arrayDeChars);
				jugador.setClaveDeUsuario(clave);

				if (jugador.getNombreDeUsuario().trim().length() == 0
						|| jugador.getClaveDeUsuario().trim().length() == 0) {/* Valido los datos */
					JOptionPane.showMessageDialog(null, "Datos Incompletos", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {

					String nombreUsuario = textTxtNombre.getText().toLowerCase();
					btnIniciar.setText("enviando..");
					textTxtNombre.setEnabled(false);
					passClaveUsuario.setEnabled(false);
					btnCrearUsuario.setEnabled(false);
					btnIniciar.setEnabled(false);
					Enviable loginRequest = new LoginRequest(nombreUsuario, clave);
					HiloCliente.getInstance().enviarMensaje(loginRequest);
				}

			}
		});

		textTxtNombre = new JTextField();
		textTxtNombre.setColumns(10);

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnCrearUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
								.addComponent(btnIniciar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
								.addComponent(passClaveUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
								.addComponent(textTxtNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
								.addComponent(lblClave, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
							.addGap(16))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(44)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textTxtNombre, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblClave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passClaveUsuario, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
					.addComponent(btnIniciar)
					.addGap(18)
					.addComponent(btnCrearUsuario)
					.addGap(41))
		);
		manejarPanel();
		panel.setLayout(gl_panel);

	}
	
	public void manejarPanel() {
	}

	@Override
	public void notificarLoginResponse(LoginResponse loginResponse) {
		if (!loginResponse.getSuccess()) {
			JOptionPane.showMessageDialog(this, loginResponse.getMensaje(), "Error, ingreso fallido", JOptionPane.ERROR_MESSAGE);
			textTxtNombre.setEnabled(true);
			passClaveUsuario.setEnabled(true);
			btnCrearUsuario.setEnabled(true);
			btnIniciar.setEnabled(true);
			btnIniciar.setText("Iniciar Sesion");
			return;
		}
		SalasDisponibles disponibles= new SalasDisponibles(this);
		disponibles.setLocationRelativeTo(null);
		disponibles.setResizable(false);
		
	}
}
