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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

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
	String clave;

	public SalasDisponibles(JFrame frameParent) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorSalas(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorCrearSala(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorEntrarSala(this);

		yo = this;
		modelo = new DefaultListModel<String>();
		listSalasDisponibles = new JList();
		JButton btnCrearSala = new JButton("Crear Sala");
		JPanel panel = new JPanel();
		JLabel lblEstado = new JLabel("");
		JButton btnRefresh = new JButton("Refrezcar Salas");
		GroupLayout gl_panel = new GroupLayout(panel);
		
		HiloCliente.getInstance().enviarMensaje(new GetSalasRequest());
		
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		frameParent.dispose();
		salas = new ArrayList<>();
		setTitle("Salas Disponibles");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(154, Short.MAX_VALUE))
		);

		btnUnirse = new JButton("Unirse");
		btnUnirse.setEnabled(false);
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = listSalasDisponibles.getSelectedIndex();
				if (selectedIndex == -1) {
					JOptionPane.showMessageDialog(null, "No seleccionaste ninguna sala", "ERROR", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(salas.get(selectedIndex).getClaveSala()==null){
					llamarARequestDeIngreso(selectedIndex,"null");
				}else {
					clave = JOptionPane.showInputDialog("Ingresar clave","");
					llamarARequestDeIngreso(selectedIndex,clave);
				}
				
			}
		});

		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearNuevaSala = new CrearNuevaSala(yo);
				crearNuevaSala.setResizable(false);
				crearNuevaSala.setLocationRelativeTo(null);
				crearNuevaSala.setVisible(true);
			}
		});

		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Stencil", Font.PLAIN, 20));
		listSalasDisponibles.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				btnUnirse.setEnabled(true);
				int selectedIndex = listSalasDisponibles.getSelectedIndex();
				if (selectedIndex != -1) {
					lblEstado.setText(
						salas.get(selectedIndex).getCantJugadoresActual()
						+ " / " +
						salas.get(selectedIndex).getCantJugadores()
					);
					btnUnirse.setEnabled(true);
				} else {
					lblEstado.setText("");
					btnUnirse.setEnabled(false);
				}
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HiloCliente.getInstance().enviarMensaje(new GetSalasRequest());
			}
		});

		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(listSalasDisponibles, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnCrearSala, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUnirse, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
								.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 253, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEstado, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listSalasDisponibles, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnRefresh)
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCrearSala)
								.addComponent(btnUnirse)))
						.addComponent(lblEstado, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
					.addGap(162))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
	
	public void mostrarSala() {
		modelo.removeAllElements();
		
		for (int i = 0; i < salas.size(); i++) {
			modelo.add(i, salas.get(i).getNombreSala());
		}

		this.listSalasDisponibles.setModel(modelo);
	}
	
	public void llamarARequestDeIngreso(int indice, String clave) {
		HiloCliente.getInstance().enviarMensaje(new UnirseSalaRequest(salas.get(indice),clave));
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
