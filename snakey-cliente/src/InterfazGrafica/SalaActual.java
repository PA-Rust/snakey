package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.Escuchador;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas;
import Comunicacion.ManejadorDeRespuestas.EscuchadorCambioSala;
import Comunicacion.ManejadorDeRespuestas.EscuchadorJuegoComenzo;
import Comunicacion.ManejadorDeRespuestas.EscuchadorUsuario;
import Comunicacion.Notifications.CambioSalaNotification;
import Comunicacion.Notifications.JuegoIniciadoNotification;
import Comunicacion.Requests.GetProfileRequest;
import Comunicacion.Requests.IniciarPartidaRequest;
import Comunicacion.Requests.QuitSalaRequest;
import Comunicacion.Responses.GetProfileResponse;
import Juego.FrameJuego;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.BoxLayout;

public class SalaActual extends JFrame implements EscuchadorJuegoComenzo,EscuchadorCambioSala,EscuchadorUsuario{

	private static final long serialVersionUID = 6003787470428682570L;
	private JPanel contentPane;
	private JButton btnIniciarPartida;
	private JPanel panel;
	private JPanel panelJugadores;
	private GroupLayout groupLayout;
	private Sala sala;
	private JFrame yo;
	private FrameJuego frameJuego;
	
	public SalaActual(Sala sala, JFrame frameParent) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorCambioSala(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorJuegoComenzo(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorUsuario(this);
		
		yo = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
			 @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				 	requestDeCerrar();
			    }
		});
		this.sala = sala;
		setSize(234, 293);
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
		
		panelJugadores = new JPanel();
		
		JLabel lblJugadores = new JLabel("Jugadores");
		lblJugadores.setFont(new Font("Tahoma", Font.BOLD, 16));
		JButton btnNewButton = new JButton("Configurar Teclas");
		btnNewButton.addActionListener(new ActionListener() {
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
		
		groupLayout = new GroupLayout(panel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelJugadores, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIniciarPartida)
						.addComponent(lblJugadores))
					.addContainerGap(227, Short.MAX_VALUE))
					.addGap(65)
					.addComponent(btnIniciarPartida)
					.addGap(79)
					.addComponent(btnEspectear)
					.addContainerGap(110, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(panelJugadores, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblJugadores)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelJugadores, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnIniciarPartida)
					.addContainerGap(46, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelJugadores, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIniciarPartida)
						.addComponent(btnEspectear))
					.addContainerGap())
		);
		panel.add(panelJugadores);
		panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));
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
			jLabel.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
			jLabel.setForeground(jugador.getAvatar().getColor());
			jLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
			jLabel.addMouseListener(new MouseAdapter()   {   
		        public void mouseClicked(MouseEvent e)   
		        {   
		              JPanel j = new JPanel();
		              try {
		            	 HiloCliente.getInstance().enviarMensaje(new GetProfileRequest(jugador.getNombreDeUsuario()));		      			
		      		} catch (Exception e1) {
		      			e1.printStackTrace();
		      		}
		        }   
		});
		
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

	@Override
	public void notificarUsuarioResponse(GetProfileResponse profileResponse) {		
			PerfilDialog dialog = new PerfilDialog(
					profileResponse.getJugador().getPartidasGanadas(),
					profileResponse.getJugador().getPuntajeAcumulado(),
					profileResponse.getJugador().getManzanitasComidasTotales(),
					profileResponse.getJugador().getNombreDeUsuario());
			dialog.setLocationRelativeTo(this);
  			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
  			dialog.setVisible(true);
	}
}
