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
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
public class CrearNuevaSala extends JDialog {

	private JPanel contentPane;
	private JTextField txtNombreDeSala;
	private JDialog yo;
	private Sala nuevasala;
	private JTextField claveSala;

	public  CrearNuevaSala(JFrame padre) {

		yo = this;
		setModal(true);
		setTitle("Crear Sala");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
						
								JLabel lblNombreDeLa = new JLabel("Nombre de La sala");
								lblNombreDeLa.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombreDeLa.setFont(new Font("Stencil", Font.PLAIN, 18));
												
												JLabel label = new JLabel("");
										
												txtNombreDeSala = new JTextField();
												txtNombreDeSala.setColumns(10);
														JLabel lblClaveopcional = new JLabel("Clave (opcional)");
														lblClaveopcional.setHorizontalAlignment(SwingConstants.CENTER);
														
														lblClaveopcional.setFont(new Font("Stencil", Font.PLAIN, 18));
												
												claveSala = new JTextField();
												claveSala.setColumns(10);
												
												JLabel label_1 = new JLabel("");
												
												JLabel label_2 = new JLabel("");
														
														JLabel label_3 = new JLabel("");
														
																JLabel lblCantidadDeJugadores = new JLabel("Cantidad de Jugadores");
																lblCantidadDeJugadores.setHorizontalAlignment(SwingConstants.CENTER);
																lblCantidadDeJugadores.setFont(new Font("Stencil", Font.PLAIN, 18));
												
														JComboBox<?> comboBox = new JComboBox();
														comboBox.setModel(
																new DefaultComboBoxModel(new String[] { "1 jugador", "2 jugadores", "3 jugadores", "4 jugadores" }));
														
																JButton btnAceptar = new JButton("Aceptar");
																btnAceptar.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {

																		if (txtNombreDeSala.getText().trim().length() == 0) { /* Valido que el nombre tenga algo valido */
																			JOptionPane.showMessageDialog(null, "Datos Incompletos", "ERROR", JOptionPane.ERROR_MESSAGE);
																		} else {
																			nuevasala = new Sala(txtNombreDeSala.getText(),comboBox.getSelectedIndex() + 1, new Jugador("provisorio", "provisorio"), claveSala.getText());
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
																		}
																	}
																});
														
														JLabel label_4 = new JLabel("");
														
														JLabel label_5 = new JLabel("");
														GroupLayout gl_contentPane = new GroupLayout(contentPane);
														gl_contentPane.setHorizontalGroup(
															gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createSequentialGroup()
																	.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
																			.addGroup(gl_contentPane.createSequentialGroup()
																				.addGap(76)
																				.addComponent(label))
																			.addGroup(gl_contentPane.createSequentialGroup()
																				.addContainerGap()
																				.addComponent(lblCantidadDeJugadores)
																				.addPreferredGap(ComponentPlacement.RELATED)
																				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																					.addComponent(label_2)
																					.addComponent(label_1)
																					.addComponent(label_3)))
																			.addComponent(claveSala, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
																			.addComponent(txtNombreDeSala, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
																			.addComponent(lblNombreDeLa, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
																			.addComponent(lblClaveopcional, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
																			.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																		.addGroup(gl_contentPane.createSequentialGroup()
																			.addComponent(btnAceptar)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(btnCancelar)))
																	.addContainerGap())
														);
														gl_contentPane.setVerticalGroup(
															gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createSequentialGroup()
																	.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_contentPane.createSequentialGroup()
																			.addComponent(lblNombreDeLa)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(label)
																			.addGroup(gl_contentPane.createSequentialGroup()
																				.addComponent(txtNombreDeSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.RELATED))
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(lblClaveopcional)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(claveSala, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_contentPane.createSequentialGroup()
																					.addGap(21)
																					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																						.addComponent(label_2)
																						.addComponent(label_1)
																						.addComponent(label_3)))
																				.addGroup(gl_contentPane.createSequentialGroup()
																					.addPreferredGap(ComponentPlacement.RELATED)
																					.addComponent(lblCantidadDeJugadores))))
																		.addGroup(gl_contentPane.createSequentialGroup()
																			.addGap(138)
																			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																				.addComponent(btnAceptar)
																				.addComponent(btnCancelar))))
																	.addContainerGap(62, Short.MAX_VALUE))
														);
														contentPane.setLayout(gl_contentPane);
	}
}
