package InterfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas;
import Comunicacion.ManejadorDeRespuestas.EscuchadorCrearSala;
import Comunicacion.ManejadorDeRespuestas.EscuchadorEntrarSala;
import Comunicacion.ManejadorDeRespuestas.EscuchadorSalas;
import Comunicacion.Requests.CrearSalaRequest;
import Comunicacion.Requests.GetSalasRequest;
import Comunicacion.Requests.UnirseSalaRequest;
import Comunicacion.Responses.CrearSalaResponse;
import Comunicacion.Responses.GetSalasResponse;
import Comunicacion.Responses.UnirseSalaResponse;

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
import javax.swing.JOptionPane;

public class SalasDisponibles extends JFrame implements EscuchadorSalas,EscuchadorCrearSala,EscuchadorEntrarSala{

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

	public SalasDisponibles(Jugador jugador, JFrame frameParent) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorSalas(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorCrearSala(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorEntrarSala(this);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		frameParent.dispose();
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

		btnUnirse = new JButton("Unirse");
		btnUnirse.setEnabled(false);
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (listSalasDisponibles.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "No seleccionaste ninguna sala", "ERROR", JOptionPane.WARNING_MESSAGE);
				}
				else {
					HiloCliente.getInstance().enviarMensaje(new UnirseSalaRequest(salas.get(listSalasDisponibles.getSelectedIndex())));
				}
			}
		});

		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearNuevaSala = new CrearNuevaSala(yo,jugador);
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
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HiloCliente.getInstance().enviarMensaje(new GetSalasRequest());
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(listSalasDisponibles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSalas, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRta, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbldisponible, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnUnirse)
							.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
							.addComponent(btnCrearSala)))
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(46)
					.addComponent(btnRefresh)
					.addContainerGap(202, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbldisponible, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalas, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRta, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(listSalasDisponibles, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnRefresh)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUnirse)
						.addComponent(btnCrearSala))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
	
	public void mostrarSala() {
		for(int i=0;i<salas.size();i++) {
			modelo.add(i, salas.get(i).getNombreSala());
		}
				
		this.listSalasDisponibles.setModel(modelo);
		btnUnirse.setEnabled(true);
	}
	
	public void llamarARequest(Sala sala) {
		HiloCliente.getInstance().enviarMensaje(new CrearSalaRequest(sala.getNombreSala(), sala.getCantJugadores()));
	}
	
	@Override
	public void notificarSalasResponse(GetSalasResponse salaResponse) {
		salas = salaResponse.getSalas();
		mostrarSala();
	}

	@Override
	public void notificarCrearSalaResponse(CrearSalaResponse crearSalaResponse) {
		if(!crearSalaResponse.getSuccess()) {
			JOptionPane.showMessageDialog(this, crearSalaResponse.getTipoMensaje(), "Error,no se pudo crear sala", JOptionPane.ERROR_MESSAGE);
			return;
		}
		new SalaActual(crearSalaResponse.getSala(), this);
	}

	@Override
	public void notificarUnirseSalaResponse(UnirseSalaResponse unirseSalaResponse) {
		if(!unirseSalaResponse.getSuccess()) {
			JOptionPane.showMessageDialog(this, unirseSalaResponse.getTipoMensaje(), "Error,no se pudo ingresar a sala", JOptionPane.ERROR_MESSAGE);
			return;
		}
		new SalaActual(unirseSalaResponse.getSala(), this);	
	}
}
