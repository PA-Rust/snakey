package InterfazGrafica;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import Comunicacion.Requests.QuitSalaRequest;
import Juego.FrameJuego;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalaActual extends JFrame implements EscuchadorJuegoComenzo,EscuchadorCambioSala{

	private static final long serialVersionUID = 6003787470428682570L;
	private JPanel contentPane;
	private JButton btnIniciarPartida;
	private JPanel panel;
	private JButton btnEspectear;
	private JPanel panelJugadores;
	private GroupLayout groupLayout;
	private Sala sala;
	private JFrame yo;
	private FrameJuego frameJuego;
	
	public SalaActual(Sala sala, JFrame frameParent) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorCambioSala(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorJuegoComenzo(this);
		yo = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
			 @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				 	requestDeCerrar();
			    }
		});
		this.sala = sala;
		setSize(450, 300);
		setLocationRelativeTo(frameParent);
		setResizable(false);
		setVisible(true);
		setTitle(this.sala.getNombreSala());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		frameParent.dispose();
		
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
		
		panelJugadores = new JPanel();
		groupLayout = new GroupLayout(panel);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(65)
							.addComponent(btnIniciarPartida)
							.addGap(79)
							.addComponent(btnEspectear))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(panelJugadores, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addComponent(panelJugadores, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIniciarPartida)
						.addComponent(btnEspectear))
					.addContainerGap())
		);
		panel.add(panelJugadores);
		panel.setLayout(groupLayout);
		
		repintarJPanel();
	}
	
	public void finalizarPartida() {
		frameJuego = null;
	}
	
	public void requestDeCerrar() {
		if (frameJuego != null) {
			frameJuego.dispose();
		}
		HiloCliente.getInstance().enviarMensaje( new QuitSalaRequest());
    	new SalasDisponibles(yo);
	}
	
	public void repintarJPanel() {
		for (Jugador jugador: this.sala.getJugadores()) {
			JLabel jLabel = new JLabel(jugador.getNombreDeUsuario());
			jLabel.setForeground(jugador.getAvatar().getColor());
			panelJugadores.add(jLabel);
		}
		panelJugadores.revalidate();
	}
	
	@Override
	public void notificarJuegoComenzo(JuegoIniciadoNotification juegoIniciadoNotification) {	
		frameJuego = new FrameJuego(juegoIniciadoNotification.getPartida(), this);
	}

	@Override
	public void notificarCambioSala(CambioSalaNotification cambioSalaNotification) {
		if (cambioSalaNotification.getSala().getJugadorPropietario() == null) {
			if (frameJuego != null) {
				frameJuego.dispose();
			}
			JOptionPane.showMessageDialog(this, "La sala se cerrara porque el propietario cerro la sala.", "Propietario cerro sala", JOptionPane.INFORMATION_MESSAGE);
	    	new SalasDisponibles(yo);
		} else {
			panelJugadores.removeAll();
			panelJugadores.repaint();
			panel.setLayout(groupLayout);
			this.sala = cambioSalaNotification.getSala();
			repintarJPanel();
		}			
	}
}
