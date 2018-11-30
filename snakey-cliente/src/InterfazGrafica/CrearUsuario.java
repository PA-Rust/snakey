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
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class CrearUsuario extends JDialog implements EscuchadorRegister {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passClave;
	private JPasswordField passClaveConfirmatoria;
	private JTextField textNombreNuevoReg;
	private JDialog yo;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private GroupLayout gl_panel;
	private JPanel panel;
	private JLabel lblClave;
	private JLabel lblClaveConfirmar;
	private JLabel lblNombre;
	
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

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		lblClave = new JLabel("Clave");

		passClave = new JPasswordField();

		lblClaveConfirmar = new JLabel("Confirmar Clave");

		passClaveConfirmatoria = new JPasswordField();

		textNombreNuevoReg = new JTextField();
		textNombreNuevoReg.setColumns(10);

		lblNombre = new JLabel("Nombre");
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textNombreNuevoReg.getText().isEmpty() || passClave.getPassword().length == 0 || passClaveConfirmatoria.getPassword().length == 0) 
					JOptionPane.showMessageDialog(yo, "Datos incompletos");
					else if(!Arrays.equals(passClave.getPassword(), passClaveConfirmatoria.getPassword())) {
						JOptionPane.showMessageDialog(yo, "Campos de clave inconsistentes");
					}
					else {
						char[] arrayDeChars = passClave.getPassword();
						String nombreUsuario = textNombreNuevoReg.getText().toLowerCase();
						String clave = new String(arrayDeChars);
						textNombreNuevoReg.setEnabled(false);
						passClave.setEnabled(false);
						CrearUsuario.this.passClaveConfirmatoria.setEnabled(false);
						btnAceptar.setText("Enviando request..");
						btnCancelar.setEnabled(false);
						btnAceptar.setEnabled(false);
						Enviable registerRequest = new RegisterRequest(nombreUsuario, clave);
						HiloCliente.getInstance().enviarMensaje(registerRequest);
					}
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
		gl_panel = new GroupLayout(panel);
		manejarPanels();
		panel.setLayout(gl_panel);
	}
	
	public void manejarPanels() {
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
							.addComponent(textNombreNuevoReg, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addComponent(passClave, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addComponent(passClaveConfirmatoria, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
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
							.addComponent(textNombreNuevoReg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNombre))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(passClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblClave))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(passClaveConfirmatoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblClaveConfirmar))
						.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAceptar)
							.addComponent(btnCancelar))
						.addContainerGap())
			);
	}
	
	@Override
	public void notificarRegisterResponse(RegisterResponse registerResponse) {
		if (!registerResponse.getSuccess()) {
			JOptionPane.showMessageDialog(this, registerResponse.getMensaje(), "Error al registrar Usuario", JOptionPane.ERROR_MESSAGE);
			// TODO(ernesto): Volver a habilitar los inputs del formulario.
			textNombreNuevoReg.setEnabled(true);
			CrearUsuario.this.passClave.setEnabled(true);
			CrearUsuario.this.passClaveConfirmatoria.setEnabled(true);
			btnCancelar.setEnabled(true);
			btnAceptar.setEnabled(true);
			btnAceptar.setText("Aceptar");
			return;
		}
		JOptionPane.showMessageDialog(this, registerResponse.getMensaje(), "Usuario creado correctamente!", JOptionPane.INFORMATION_MESSAGE);
		yo.dispose();
	}
}
