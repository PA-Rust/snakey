package Juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Commons.Avatar;
import Commons.Direccion;
import Commons.Partida;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas;
import Comunicacion.ManejadorDeRespuestas.EscuchadorEstadoPartida;
import Comunicacion.ManejadorDeRespuestas.EscuchadorPartidaFinalizada;
import Comunicacion.Notifications.EstadoPartidaNotification;
import Comunicacion.Notifications.InputNotification;
import Comunicacion.Notifications.JuegoFinalizadoNotification;
import InterfazGrafica.SalaActual;
import Misc.RutaImagen;

public class FrameJuego extends JFrame implements EscuchadorEstadoPartida, EscuchadorPartidaFinalizada {
	private static final long serialVersionUID = 5255914042969054269L;
	private PanelJuego panelJuego;
	private Partida partida;
	private JFrame yo;
	public FrameJuego(Partida partida, JFrame padre) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorEstadoPartida(this);
		yo = this;
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				((SalaActual) padre).requestDeCerrar();
		    }
		});
		this.partida = partida;
		setLocationRelativeTo(padre);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setBackground(Color.GRAY);
		
		panelJuego = new PanelJuego();
		panelJuego.setBackground(Color.black);
		panelJuego.setBounds(0, 0, 550, 550);
		panelJuego.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panelJuego.setLayout(new BorderLayout(0, 0));
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
		setContentPane(panelJuego);
	}
	
	public class PanelJuego extends JPanel {
		private static final long serialVersionUID = 1L;
		private Map<Avatar, Image> imagenes;
		final int TAM_BLOQUE = 550 / 50;
		
		public PanelJuego() {
			imagenes = new HashMap<Avatar, Image>();
			for (Avatar avatar: Avatar.values()) {
				imagenes.put(avatar, new ImageIcon(RutaImagen.get("sprites/" + avatar.getSprite())).getImage());
			}
		}
		
		@Override
		public void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			actualizar(graphics);
		}
		
		public void actualizar(Graphics g) {
			partida.getMapa().dibujar(g, imagenes, TAM_BLOQUE);
		}
	}
	
	@Override
	public void notificarEstadoPartida(EstadoPartidaNotification estadoPartidaNotification) {
		partida = estadoPartidaNotification.getPartida();
		Graphics g = getGraphics();
		if (g != null)
			panelJuego.paintComponent(g);
		panelJuego.repaint();
	}

	@Override
	public void notificarPartidaFinalizada(JuegoFinalizadoNotification juegoFinalizadoNotification) {
		JOptionPane.showMessageDialog(this, "Partida Finalizada", "La partida termino!", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}
