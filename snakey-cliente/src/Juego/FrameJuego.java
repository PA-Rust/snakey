package Juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import Commons.Avatar;
import Commons.Jugador;
import Commons.Partida;

public class FrameJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private PanelJuego panelJuego;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameJuego frame = new FrameJuego(new Jugador[] {
						new Jugador(),
						new Jugador(),
						new Jugador(),
						new Jugador(),
					});
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameJuego(Jugador[] jugadores) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		jugadores[1].setAvatar(Avatar.VIBORITA_AMARILLA);
		jugadores[2].setAvatar(Avatar.VIBORITA_AZUL);
		jugadores[3].setAvatar(Avatar.VIBORITA_NARANJA);
		panelJuego = new PanelJuego(new Partida(jugadores));
		panelJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelJuego.setLayout(new BorderLayout(0, 0));
		setContentPane(panelJuego);
	}

}
