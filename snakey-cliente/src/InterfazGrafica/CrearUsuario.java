package InterfazGrafica;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Comunicacion.Enviable;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas;
import Comunicacion.ManejadorDeRespuestas.EscuchadorRegister;
import Comunicacion.Requests.RegisterRequest;
import Comunicacion.Responses.RegisterResponse;

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

public class CrearUsuario extends JDialog implements EscuchadorRegister {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField clave;
	private JPasswordField claveConfirmatoria;
	private JTextField nombreNuevoReg;
	private JDialog yo;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public CrearUsuario(JFrame padre) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorRegister(this);
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

		JLabel lblClave = new JLabel("Clave");

		clave = new JPasswordField();

		JLabel lblClaveConfirmar = new JLabel("Confirmar Clave");

		claveConfirmatoria = new JPasswordField();

		nombreNuevoReg = new JTextField();
		nombreNuevoReg.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] arrayDeChars = clave.getPassword();
				String nombreUsuario = nombreNuevoReg.getText();
				String clave = new String(arrayDeChars);
				Enviable registerRequest = new RegisterRequest(nombreUsuario, clave);
				HiloCliente.getInstance().enviarMensaje(registerRequest);
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cancelar el registro?",
						"Cancelar registro", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					yo.dispose();
				}

			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblClaveConfirmar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblClave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(nombreNuevoReg, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(clave, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(claveConfirmatoria, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAceptar)
					.addPreferredGap(ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
					.addComponent(btnCancelar))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nombreNuevoReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClave))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(claveConfirmatoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClaveConfirmar))
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}

	@Override
	public void notificarRegisterResponse(RegisterResponse registerResponse) {
		if (!registerResponse.getSuccess()) {
			JOptionPane.showMessageDialog(this, registerResponse.getMensaje(), "Error al registrar Usuario", JOptionPane.ERROR_MESSAGE);
			// TODO(ernesto): Volver a habilitar los inputs del formulario.
			return;
		}
		JOptionPane.showMessageDialog(this, registerResponse.getMensaje(), "Usuario creado correctamente!", JOptionPane.INFORMATION_MESSAGE);
	}
}
