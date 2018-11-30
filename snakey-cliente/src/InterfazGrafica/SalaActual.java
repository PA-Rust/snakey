package InterfazGrafica;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas.EscuchadorCambioSala;
import Comunicacion.ManejadorDeRespuestas.EscuchadorJuegoComenzo;
import Comunicacion.Notifications.CambioSalaNotification;
import Comunicacion.Notifications.JuegoIniciadoNotification;
import Comunicacion.Requests.IniciarPartidaRequest;
import Juego.FrameJuego;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SalaActual extends JFrame implements EscuchadorJuegoComenzo,EscuchadorCambioSala{

	private static final long serialVersionUID = 6003787470428682570L;
	private JPanel contentPane;
	private JButton btnIniciarPartida;
	private JPanel panel;
	private JButton btnEspectear;
	private JPanel panel_jugadores;
	
	public SalaActual(Sala sala,JFrame frameParent) {
		
		setSize(450, 300);
		setLocationRelativeTo(frameParent);
		setResizable(false);
		setVisible(true);
		frameParent.dispose();
		setTitle(sala.getNombreSala());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	    panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		btnIniciarPartida = new JButton("Iniciar partida");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HiloCliente.getInstance().enviarMensaje(new IniciarPartidaRequest());
			}
		});
		
		btnEspectear = new JButton("Espectear");
		btnEspectear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		panel_jugadores = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(65)
							.addComponent(btnIniciarPartida)
							.addGap(79)
							.addComponent(btnEspectear))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(29)
							.addComponent(panel_jugadores, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addComponent(panel_jugadores, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIniciarPartida)
						.addComponent(btnEspectear))
					.addContainerGap())
		);
		panel.add(panel_jugadores);
		panel.setLayout(gl_panel);
		repintarJPanel(sala.getJugadores());
	}
	
	public void repintarJPanel(ArrayList<Jugador> jugadores) {
		System.out.println("repintando");
		for(Jugador jugador :jugadores) {
			JLabel jLabel = new JLabel(jugador.getNombreDeUsuario());
			jLabel.setForeground(jugador.getAvatar().getColor());
			panel_jugadores.add(jLabel);
		}
	}
	
	@Override
	public void notificarJuegoComenzo(JuegoIniciadoNotification juegoIniciadoNotification) {	
		new FrameJuego(juegoIniciadoNotification.getPartida(), this);
	}

	@Override
	public void notificarCambioSala(CambioSalaNotification cambioSalaNotification) {
		panel_jugadores.removeAll();
		repintarJPanel(cambioSalaNotification.getSala().getJugadores());			
	}
}
