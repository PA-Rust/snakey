package InterfazGrafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class SalasDisponibles extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JFrame yo;

	JList<String> listSalasDisponibles;
	DefaultListModel<String> modelo;
	ArrayList<Sala> salas;

	Sala salaCreada;
	CrearNuevaSala crearNuevaSala;
	SalaActual salaActual;
	JButton btnUnirse;

	@SuppressWarnings({ "rawtypes", "unchecked" }) // suprime warning de modelo - 57 -
	public SalasDisponibles(Jugador jugador) {
		salas = new ArrayList<>();
		setTitle("Salas Disponibles");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new PanelDeFondo("fondoSalaDisponible.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		yo = this;
		modelo = new DefaultListModel();

		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearNuevaSala = new CrearNuevaSala(yo, jugador);
				crearNuevaSala.setResizable(false);
				crearNuevaSala.setLocationRelativeTo(null);
				crearNuevaSala.setVisible(true);
			}
		});

		JLabel lblRta = new JLabel("RTA");
		lblRta.setHorizontalAlignment(SwingConstants.CENTER);
		lblRta.setFont(new Font("Stencil", Font.PLAIN, 20));

		btnUnirse = new JButton("Unirse");
		btnUnirse.setEnabled(false);
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (listSalasDisponibles.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "No seleccionaste ninguna sala", "ERROR",
							JOptionPane.WARNING_MESSAGE);

				} else {
					salaActual = new SalaActual(jugador, salas.get(listSalasDisponibles.getSelectedIndex()));
					salaActual.setLocationRelativeTo(null);
					yo.dispose();
				}
			}
		});

		JLabel lbldisponible = new JLabel("\u00BFDisponible?");
		lbldisponible.setHorizontalAlignment(SwingConstants.CENTER);
		lbldisponible.setFont(new Font("Stencil", Font.PLAIN, 20));

		listSalasDisponibles = new JList();
		listSalasDisponibles.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listSalasDisponibles.setVisibleRowCount(10);
		listSalasDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblSalas = new JLabel("SALAS");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Stencil", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(listSalasDisponibles, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnUnirse)
										.addPreferredGap(ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
										.addComponent(btnCrearSala))
								.addComponent(lblRta, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
								.addComponent(lbldisponible, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
								.addComponent(lblSalas, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(lblSalas, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(listSalasDisponibles, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(lbldisponible, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(lblRta, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnCrearSala)
						.addComponent(btnUnirse))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

	}

	public void agregarALista(Sala nuevaSala) {
		this.modelo.addElement(nuevaSala.getNombreSala());
		this.salas.add(nuevaSala);
		this.listSalasDisponibles.setModel(modelo);
		btnUnirse.setEnabled(true);
	}
}
