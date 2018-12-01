package Juego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Commons.Avatar;
import Commons.Direccion;
import Commons.Jugador;
import Commons.Partida;
import Commons.Taunt;
import Comunicacion.HiloCliente;
import Comunicacion.ManejadorDeRespuestas;
import Comunicacion.ManejadorDeRespuestas.EscuchadorEstadoPartida;
import Comunicacion.ManejadorDeRespuestas.EscuchadorPartidaFinalizada;
import Comunicacion.ManejadorDeRespuestas.EscuchadorTaunt;
import Comunicacion.Notifications.EstadoPartidaNotification;
import Comunicacion.Notifications.InputNotification;
import Comunicacion.Notifications.JuegoFinalizadoNotification;
import Comunicacion.Notifications.TauntNotification;
import InterfazGrafica.SalaActual;
import Misc.RutaImagen;
import InterfazGrafica.ConfigDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FrameJuego extends JFrame
		implements EscuchadorEstadoPartida, EscuchadorPartidaFinalizada, EscuchadorTaunt {
	private static final long serialVersionUID = 5255914042969054269L;
	private PanelJuego panelJuego;
	private Partida partida;
	private JPanel panelDatos;

	private Taunt tauntActual;
	private Jugador jugadorTauntActual;
	private int up, left, right, down, taunt1, taunt2, taunt3, taunt4;

	public FrameJuego(Partida partida, SalaActual padre) {
		ManejadorDeRespuestas.getInstancia().setEscuchadorEstadoPartida(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorPartidaFinalizada(this);
		ManejadorDeRespuestas.getInstancia().setEscuchadorTaunt(this);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				padre.requestDeCerrar();
			}
		});
		try {
			leerArchivoConfig();
		} catch (IOException e1) {
			this.down = KeyEvent.VK_DOWN;
			this.left = KeyEvent.VK_LEFT;
			this.right = KeyEvent.VK_RIGHT;
			this.up = KeyEvent.VK_UP;

			this.taunt1 = KeyEvent.VK_W;
			this.taunt2 = KeyEvent.VK_A;
			this.taunt3 = KeyEvent.VK_S;
			this.taunt4 = KeyEvent.VK_D;
			e1.printStackTrace();

		}
		this.partida = partida;
		setLocationRelativeTo(padre);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1000, 600);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == up) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Direccion.arriba));
				} else if (e.getKeyCode() == down) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Direccion.abajo));
				} else if (e.getKeyCode() == left) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Direccion.izquierda));
				} else if (e.getKeyCode() == right) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Direccion.derecha));
				} else if (e.getKeyCode() == taunt1) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Taunt.GG));
				} else if (e.getKeyCode() == taunt2) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Taunt.INSULTAR));
				} else if (e.getKeyCode() == taunt3) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Taunt.LLORAR));
				} else if (e.getKeyCode() == taunt4) {
					HiloCliente.getInstance().enviarMensaje(new InputNotification(Taunt.REIRSE));
				}
			}
		});

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 328, 328, 328, 0 };
		gridBagLayout.rowHeights = new int[] { 561, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		panelJuego = new PanelJuego();
		panelJuego.setBackground(Color.black);
		panelJuego.setBounds(0, 0, 550, 550);
		panelJuego.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		panelJuego.setLayout(new BorderLayout(0, 0));

		GridBagConstraints gbc_panelJuegoContainer = new GridBagConstraints();
		gbc_panelJuegoContainer.gridwidth = 2;
		gbc_panelJuegoContainer.fill = GridBagConstraints.BOTH;
		gbc_panelJuegoContainer.insets = new Insets(0, 0, 0, 5);
		gbc_panelJuegoContainer.gridx = 0;
		gbc_panelJuegoContainer.gridy = 0;

		getContentPane().add(panelJuego, gbc_panelJuegoContainer);

		panelDatos = new JPanel();
		panelDatos.setBackground(Color.DARK_GRAY);
		panelDatos.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.insets = new Insets(0, 0, 0, 5);
		gbc_panelDatos.gridx = 2;
		gbc_panelDatos.gridy = 0;
		getContentPane().add(panelDatos, gbc_panelDatos);
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
	}

	private void leerArchivoConfig() throws IOException {
		Scanner sc = new Scanner(new File(ConfigDialog.archivoConfig));
		this.up = sc.nextInt();
		this.left = sc.nextInt();
		this.down = sc.nextInt();
		this.right = sc.nextInt();
		this.taunt1 = sc.nextInt();
		this.taunt2 = sc.nextInt();
		this.taunt3 = sc.nextInt();
		this.taunt4 = sc.nextInt();
		sc.close();

	}

	public class PanelJuego extends JPanel {
		private static final long serialVersionUID = 1L;
		private Map<Avatar, Image> imagenes;
		final int TAM_BLOQUE = 550 / 50;

		public PanelJuego() {
			imagenes = new HashMap<Avatar, Image>();
			for (Avatar avatar : Avatar.values()) {
				imagenes.put(avatar, new ImageIcon(RutaImagen.get("sprites/" + avatar.getSprite())).getImage());
			}
		}

		@Override
		public void paint(Graphics graphics) {
			super.paint(graphics);
			actualizar(graphics);
		}

		public void actualizar(Graphics g) {
			final int TAM_GRILLA = 50;
			int altoBloque = (int) (panelJuego.getSize().getHeight() / TAM_GRILLA);
			int anchoBloque = (int) (panelJuego.getSize().getWidth() / TAM_GRILLA);
			partida.getMapa().dibujar(g, imagenes, altoBloque, anchoBloque);
		}
	}

	@Override
	public void notificarEstadoPartida(EstadoPartidaNotification estadoPartidaNotification) {
		panelDatos.removeAll();

		JLabel lblTimerStatic = new JLabel("Timer");
		lblTimerStatic.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTimerStatic.setForeground(Color.WHITE);
		lblTimerStatic.setHorizontalAlignment(SwingConstants.CENTER);
		panelDatos.add(lblTimerStatic);

		JLabel lblTimer = new JLabel(Integer.toString(estadoPartidaNotification.getPartida().getTimer()));
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		panelDatos.add(lblTimer);

		for (Jugador jugador : estadoPartidaNotification.getPartida().getJugadores()) {
			JLabel lbl = new JLabel(jugador.getNombreDeUsuario() + " - " + jugador.getPuntajeActual());
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
			lbl.setForeground(jugador.getAvatar().getColor());
			panelDatos.add(lbl);
		}

		if (tauntActual != null && jugadorTauntActual != null) {
			JLabel labelTaunt = new JLabel(tauntActual.getMensaje());
			labelTaunt.setForeground(jugadorTauntActual.getAvatar().getColor());
			labelTaunt.setFont(new Font("Tahoma", Font.BOLD, 60));
			panelDatos.add(labelTaunt);
		}

		panelDatos.revalidate();

		partida = estadoPartidaNotification.getPartida();

		panelJuego.repaint();
		panelDatos.repaint();
		panelJuego.revalidate();
	}

	@Override
	public void notificarPartidaFinalizada(JuegoFinalizadoNotification juegoFinalizadoNotification) {
		JOptionPane.showMessageDialog(this, "Partida Finalizada", "La partida termino!",
				JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}

	@Override
	public void notificarNuevaTaunt(TauntNotification tauntNotification) {
		tauntActual = tauntNotification.getTaunt();
		jugadorTauntActual = tauntNotification.getJugador();
	}
}
