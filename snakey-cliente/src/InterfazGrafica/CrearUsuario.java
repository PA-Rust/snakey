package InterfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Commons.Jugador;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class CrearUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField clave;
	private JTextField txtIdNuevoReg;
	private JPasswordField claveConfirmatoria;
	private JDialog yo;
	private JLabel lblIdUsuario;
	private JLabel lblNombre;
	private JTextField txtNombreNuevoReg;

	public CrearUsuario(JFrame padre) {

		yo = this;
		yo.setModal(true);
		setTitle("Crear nuevo usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE lo que hace es solo cerrar la ventana
															// secundaria y no todas las demas
		setLocationRelativeTo(null);

		setBounds(100, 100, 383, 371);
		contentPane = new PanelDeFondo("fondoCrearUsuario.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblClaveConfirmar = new JLabel("Confirmar Clave");
		lblClaveConfirmar.setForeground(Color.WHITE);
		lblClaveConfirmar.setFont(new Font("Stencil", Font.ITALIC, 12));
		lblClaveConfirmar.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Jugador nuevoJugador = new Jugador();

				nuevoJugador.setNombreDeUsuario(txtNombreNuevoReg.getText());
				// nuevoJugador.setUsuarioId(txtIdNuevoReg.getText());

				char[] arrayDeChars = clave.getPassword();
				String clave = new String(arrayDeChars);
				char[] arrayDeChars2 = claveConfirmatoria.getPassword();
				String claveConfirmatoria = new String(arrayDeChars2);

				if (nuevoJugador.getNombreDeUsuario().trim().length() == 0
				/* (ADRI) txtIdNuevoReg no lo pongo en ningun lado por ahora, solo confirmo que no este vacio*/
					|| txtIdNuevoReg.getText().trim().length() == 0 
					|| clave.trim().length() == 0
					|| claveConfirmatoria.trim().length() == 0) // campos incompletos
					JOptionPane.showMessageDialog(null, "Datos Incompletos", "ERROR", JOptionPane.WARNING_MESSAGE);
				else {

					if (claveConfirmatoria.equals(clave)) {
						System.out.println("SON IGUALES");
						nuevoJugador.setClaveUsuario(clave);
						yo.dispose();
						/// VOLCAR A LA BASE DE DATOS

					} else {
						JOptionPane.showMessageDialog(null, "No se ingreso correctamente la contraseña", "ERROR",
								JOptionPane.WARNING_MESSAGE);
					}
					
					/*luego hay que comprobar en la base de datos si el ID es correcto*/
					
					
				}
			}
		});
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cancelar el registro?",
						"Cancelar registro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					yo.dispose();
				}

			}
		});

		claveConfirmatoria = new JPasswordField();

		clave = new JPasswordField();

		txtIdNuevoReg = new JTextField();
		txtIdNuevoReg.setColumns(10);

		JLabel lblClave = new JLabel("CLAVE");
		lblClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblClave.setForeground(Color.WHITE);
		lblClave.setFont(new Font("Stencil", Font.ITALIC, 12));

		lblIdUsuario = new JLabel("ID DE USUARIO");
		lblIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdUsuario.setForeground(Color.WHITE);
		lblIdUsuario.setFont(new Font("Stencil", Font.ITALIC, 12));

		lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Stencil", Font.ITALIC, 12));

		txtNombreNuevoReg = new JTextField();
		txtNombreNuevoReg.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(11)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(clave, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblClave, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)))
								.addGap(22))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblClaveConfirmar, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(claveConfirmatoria, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 326,
										Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtNombreNuevoReg, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
												.addComponent(txtIdNuevoReg, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
												.addComponent(lblIdUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														326, Short.MAX_VALUE)
												.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 326,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(Alignment.LEADING,
														gl_contentPane.createSequentialGroup().addComponent(btnAceptar)
																.addPreferredGap(ComponentPlacement.RELATED, 180,
																		Short.MAX_VALUE)
																.addComponent(btnCancelar)))))
								.addGap(21)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(lblIdUsuario, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(txtIdNuevoReg, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(txtNombreNuevoReg, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblClave, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE).addGap(7)
				.addComponent(clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblClaveConfirmar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(claveConfirmatoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnAceptar)
						.addComponent(btnCancelar))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}
}
