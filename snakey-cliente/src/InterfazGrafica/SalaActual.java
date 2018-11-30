package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas;
import Comunicacion.ManejadorDeRespuestas.EscuchadorCambioSala;
import Comunicacion.ManejadorDeRespuestas.EscuchadorJuegoComenzo;
import Comunicacion.Notifications.CambioSalaNotification;
import Comunicacion.Notifications.JuegoIniciadoNotification;
import Comunicacion.Requests.IniciarPartidaRequest;
import Juego.FrameJuego;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalaActual extends JFrame implements EscuchadorCambioSala, EscuchadorJuegoComenzo {
	private JPanel contentPane;
	private JList<String> listaJugadores;
	private DefaultListModel<String> modelo;
	private JButton btnIniciarPartida;
	private JButton btnEspectearPartida;
	private JLabel lblNewLabel;
	private GroupLayout gl_panel;
	private JPanel panel;
	
	public SalaActual(Sala sala, JFrame framePadre) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorCambioSala(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorJuegoComenzo(this);
		setSize(450, 300);
		setLocationRelativeTo(framePadre);
		setResizable(false);
		setVisible(true);
		framePadre.dispose();
		setTitle(sala.getNombreSala());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		listaJugadores = new JList();
		modelo = new DefaultListModel();
//		this.modelo.addElement(jugadorAAgregar.getNombreDeUsuario());
//		this.listaJugadores.setModel(modelo);
		
		lblNewLabel = new JLabel(sala.getNombreSala());
		
		btnIniciarPartida = new JButton("Iniciar partida");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HiloCliente.getInstance().enviarMensaje(
					new IniciarPartidaRequest()
				);
			}
		});
		
		btnEspectearPartida = new JButton("Espectear partida");
		btnEspectearPartida.setEnabled(false);
		btnEspectearPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///deberia permitirme espectearla solo si ya inicio
				
			}
		});
		
		gl_panel = new GroupLayout(panel);
		manejarPanel();
		panel.setLayout(gl_panel);
	}
	
	public void manejarPanel() {
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(listaJugadores, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(180, Short.MAX_VALUE))
					.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
						.addContainerGap(188, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addGap(75))
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(86)
						.addComponent(btnIniciarPartida)
						.addGap(60)
						.addComponent(btnEspectearPartida)
						.addContainerGap(90, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(6)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(listaJugadores, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnIniciarPartida)
							.addComponent(btnEspectearPartida))
						.addGap(21))
			);
	}

	@Override
	public void notificarCambioSala(CambioSalaNotification cambioSalaNotification) {
		// TODO(toti): Hacer que se actualice la lista de jugadores en la sala.
	}

	@Override
	public void notificarJuegoComenzo(JuegoIniciadoNotification juegoIniciadoNotification) {
		new FrameJuego(juegoIniciadoNotification.getPartida(), this);
	}
}
