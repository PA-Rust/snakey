package InterfazGrafica;

import java.awt.BorderLayout;

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

public class CrearUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField clave;
	private JTextField idNuevoReg;
	private JPasswordField claveConfirmatoria;
	private JTextField nombreNuevoReg;
	private JDialog yo;

	public CrearUsuario(JFrame padre) {

		yo = this;
		yo.setModal(true);
		setTitle("Crear nuevo usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE lo que hace es solo cerrar la ventana
															// secundaria y no todas las demas
		setLocationRelativeTo(null);

		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblIdUsuario = new JLabel("ID Usuario");

		JLabel lblClave = new JLabel("Clave");

		clave = new JPasswordField();

		idNuevoReg = new JTextField();
		idNuevoReg.setColumns(10);

		JLabel lblClaveConfirmar = new JLabel("Confirmar Clave");

		claveConfirmatoria = new JPasswordField();

		nombreNuevoReg = new JTextField();
		nombreNuevoReg.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Jugador nuevoJugador = new Jugador();

				nuevoJugador.setNombreDeUsuario(nombreNuevoReg.getText());
				nuevoJugador.setUsuarioId(idNuevoReg.getText());

				char[] arrayDeChars = clave.getPassword();
				String clave = new String(arrayDeChars);
				char[] arrayDeChars2 = claveConfirmatoria.getPassword();
				String claveConfirmatoria = new String(arrayDeChars2);

				if (nuevoJugador.getNombreDeUsuario().length() == 0 || nuevoJugador.getUsuarioId().length() == 0
						|| clave.length() == 0 || claveConfirmatoria.length() == 0) // campos incompletos
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(30)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblClaveConfirmar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblClave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblIdUsuario, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(nombreNuevoReg, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(idNuevoReg, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(clave, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(claveConfirmatoria, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(btnAceptar)
						.addPreferredGap(ComponentPlacement.RELATED, 266, Short.MAX_VALUE).addComponent(btnCancelar)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(19).addGap(12)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblIdUsuario).addComponent(
						idNuevoReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nombreNuevoReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClave))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(claveConfirmatoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClaveConfirmar))
				.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE).addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(btnAceptar).addComponent(btnCancelar))
				.addContainerGap()));
		panel.setLayout(gl_panel);
	}
}
