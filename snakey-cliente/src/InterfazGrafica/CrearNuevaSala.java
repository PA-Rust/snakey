package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Commons.Sala;
public class CrearNuevaSala extends JDialog {

	private JPanel contentPane;
	private JTextField txtNombreDeSala;
	private JDialog yo;
	private Sala nuevasala;

	public  CrearNuevaSala(JFrame padre,Jugador jugador) {

		yo = this;
		setModal(true);
		setTitle("Crear Sala");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblNombreDeLa = new JLabel("Nombre de La sala");
		lblNombreDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeLa.setFont(new Font("Stencil", Font.PLAIN, 18));

		JLabel lblCantidadDeJugadores = new JLabel("CANTIDAD DE JUGADORES");
		lblCantidadDeJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadDeJugadores.setFont(new Font("Stencil", Font.PLAIN, 18));

		txtNombreDeSala = new JTextField();
		txtNombreDeSala.setColumns(10);

		JComboBox<?> comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "1 jugador", "2 jugadores", "3 jugadores", "4 jugadores" }));

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNombreDeSala.getText().trim().length() == 0) { /* Valido que el nombre tenga algo valido */
					JOptionPane.showMessageDialog(null, "Datos Incompletos", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					nuevasala = new Sala(txtNombreDeSala.getText(),comboBox.getSelectedIndex() + 1,jugador);/* agrego el 1 ya que va de 0-3 */
					((SalasDisponibles) padre).llamarARequest(nuevasala);
					yo.dispose(); // cierro el frame
					setModal(false);
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cancelar la creacion de la sala?",
						"Cancelar nueva sala", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					yo.dispose();
					//padre.setEnabled(true);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(btnAceptar)
						.addPreferredGap(ComponentPlacement.RELATED, 188, Short.MAX_VALUE).addComponent(btnCancelar)
						.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblNombreDeLa, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE).addContainerGap())
				.addGroup(Alignment.TRAILING,
						gl_panel.createSequentialGroup().addContainerGap()
								.addComponent(lblCantidadDeJugadores, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup().addGap(107)
						.addComponent(txtNombreDeSala, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE).addGap(109))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup().addGap(109)
						.addComponent(comboBox, 0, 148, Short.MAX_VALUE).addGap(107)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(6).addComponent(lblNombreDeLa).addGap(11)
				.addComponent(txtNombreDeSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(lblCantidadDeJugadores, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE).addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(btnAceptar).addComponent(btnCancelar))
				.addContainerGap()));
		panel.setLayout(gl_panel);
	}
}
