package Juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Avatar;
import Commons.Direccion;
import Commons.Jugador;
import Commons.Partida;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas.EscuchadorEstadoPartida;
import Comunicacion.Notifications.EstadoPartidaNotification;
import Comunicacion.Notifications.InputNotification;
import Misc.RutaImagen;

public class FrameJuego extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private PanelJuego panelJuego;
	private Partida partida;

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

	public FrameJuego(Partida partida) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		panelJuego = new PanelJuego(partida);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					HiloCliente.getInstance()
						.enviarMensaje(new InputNotification(Direccion.arriba));
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					HiloCliente.getInstance()
						.enviarMensaje(new InputNotification(Direccion.abajo));
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					HiloCliente.getInstance()
						.enviarMensaje(new InputNotification(Direccion.izquierda));
				}else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					HiloCliente.getInstance()
						.enviarMensaje(new InputNotification(Direccion.derecha));
				}
			}
		});
		panelJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelJuego.setLayout(new BorderLayout(0, 0));
		setContentPane(panelJuego);
	}
	
	public class PanelJuego extends JPanel implements EscuchadorEstadoPartida  {
		private static final long serialVersionUID = 1L;
		
		private Partida partida;
		private Map<Avatar, Image> imagenes;
		
		public PanelJuego(Partida partida) {
			imagenes = new HashMap<Avatar, Image>();
			for (Avatar avatar: Avatar.values()) {
				imagenes.put(avatar, new ImageIcon(RutaImagen.get("sprites/" + avatar.getSprite())).getImage());
			}
			this.partida = partida;
		}
		
		public Partida getPartida() {
			return partida;
		}
		
		@Override
		public void paint(Graphics graphics) {
			super.paint(graphics);
			actualizar(graphics);
		}
		
		public void actualizar(Graphics g) {
			int blockSize = this.getSize().width / partida.getMapa().getAncho();
			partida.getMapa().dibujar(g, imagenes, 600, blockSize);
		}

		@Override
		public void notificarEstadoPartida(EstadoPartidaNotification estadoPartidaNotification) {
			partida = estadoPartidaNotification.getPartida();
			Graphics g = getGraphics();
			if (g != null)
				paintComponent(g);
			else repaint();
		}
	}
}
