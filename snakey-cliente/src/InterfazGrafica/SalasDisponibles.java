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
import java.awt.Color;
import java.awt.SystemColor;

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
		listSalasDisponibles.setBounds(12, 12, 344, 307);
		listSalasDisponibles.setBackground(SystemColor.menu);
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.setBounds(12, 375, 94, 26);
		JPanel panel = new JPanel();
		JLabel lblEstado = new JLabel("");
		lblEstado.setBounds(277, 337, 79, 64);
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(12, 337, 94, 26);
		
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
		btnUnirse.setBounds(112, 375, 153, 26);
		btnUnirse.setEnabled(false);
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = listSalasDisponibles.getSelectedIndex();
				if (selectedIndex == -1) {
					JOptionPane.showMessageDialog(null, "No seleccionaste ninguna sala.", "ERROR", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(!salas.get(selectedIndex).getTieneClave()){
					llamarARequestDeIngreso(selectedIndex,null);
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
					
					if (!salas.get(selectedIndex).getJugando() && !salas.get(selectedIndex).getSalaLlena()) {
						btnUnirse.setEnabled(true);
					}
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
		panel.setLayout(null);
		panel.add(listSalasDisponibles);
		panel.add(btnCrearSala);
		panel.add(btnUnirse);
		panel.add(btnRefresh);
		panel.add(lblEstado);
		
		JButton btnConfigurarTeclas = new JButton("Configurar Teclas");
		btnConfigurarTeclas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConfigDialog dialog = new ConfigDialog();
					dialog.setLocationRelativeTo(dialog.getParent());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnConfigurarTeclas.setBounds(112, 337, 153, 26);
		panel.add(btnConfigurarTeclas);
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
		HiloCliente.getInstance().enviarMensaje(new CrearSalaRequest(sala.getNombreSala(), sala.getCantJugadores(), sala.getClaveSala()));
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
