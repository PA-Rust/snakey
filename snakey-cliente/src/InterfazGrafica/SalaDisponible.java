package InterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Sala;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class SalaDisponible extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JFrame yo;
	JList<String> listSalasDisponibles;
	Sala salaCreada;
	CrearNuevaSala crearNuevaSala;
	ArrayList<Sala> salas;
	DefaultListModel<String> modelo;

	public SalaDisponible() {
		salas = new ArrayList<>();
		setTitle("Salas Disponibles");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		yo = this;
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.PREFERRED_SIZE, 327, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE));

		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearNuevaSala = new CrearNuevaSala(yo);
				crearNuevaSala.setResizable(false);
				crearNuevaSala.setLocationRelativeTo(null);
				crearNuevaSala.setVisible(true);
			}
		});

		JLabel lbldisponible = new JLabel("\u00BFDisponible?");
		lbldisponible.setHorizontalAlignment(SwingConstants.CENTER);
		lbldisponible.setFont(new Font("Stencil", Font.PLAIN, 20));

		JLabel lblRta = new JLabel("RTA");
		lblRta.setHorizontalAlignment(SwingConstants.CENTER);
		lblRta.setFont(new Font("Stencil", Font.PLAIN, 20));

		JLabel lblSalas = new JLabel("SALAS");
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalas.setFont(new Font("Stencil", Font.PLAIN, 20));

		listSalasDisponibles = new JList();
		modelo = new DefaultListModel();
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(listSalasDisponibles, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblSalas, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRta, GroupLayout.PREFERRED_SIZE, 147,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lbldisponible, GroupLayout.PREFERRED_SIZE, 147,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.LEADING,
								gl_panel.createSequentialGroup().addComponent(btnUnirse)
										.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
										.addComponent(btnCrearSala)))
				.addGap(10)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(11)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lbldisponible, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSalas, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblRta, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(listSalasDisponibles, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnUnirse)
								.addComponent(btnCrearSala))
						.addContainerGap()));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	public void agregarALista(Sala nuevaSala) {
		this.modelo.addElement(nuevaSala.getNombreSala());
		this.salas.add(nuevaSala);
		this.listSalasDisponibles.setModel(modelo);
	}
}
