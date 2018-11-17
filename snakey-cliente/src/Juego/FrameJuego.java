package Juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultFocusManager;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import Commons.Avatar;
import Commons.Direccion;
import Commons.Jugador;
import Commons.Partida;
import javafx.scene.input.KeyCode;

public class FrameJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private PanelJuego panelJuego;
	private Partida partida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameJuego frame = new FrameJuego(new Jugador[] {
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
		this.partida = new Partida(jugadores);
		panelJuego = new PanelJuego(partida);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					partida.getMapa().getViboritas().get(0)
							.cambiarDireccion(Direccion.arriba);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					partida.getMapa().getViboritas().get(0)
							.cambiarDireccion(Direccion.abajo);
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					partida.getMapa().getViboritas().get(0)
							.cambiarDireccion(Direccion.izquierda);
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					partida.getMapa().getViboritas().get(0)
							.cambiarDireccion(Direccion.derecha);
				}
			}
		});
		panelJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelJuego.setLayout(new BorderLayout(0, 0));
		setContentPane(panelJuego);
	}
}
