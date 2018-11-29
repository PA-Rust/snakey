package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalaActual extends JFrame {

	private JPanel contentPane;
	JFrame yo;
	Jugador jugadorAAgregar;
	JList<String> listaJugadores;
	DefaultListModel<String> modelo;
	JButton btnIniciarPartida;
	JButton btnEspectearPartida;
	
	public SalaActual(Jugador jugador, Sala sala, JFrame framePadre) {
		framePadre.dispose();
		this.jugadorAAgregar = jugador;
		yo = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		listaJugadores = new JList();
		modelo = new DefaultListModel();
		this.modelo.addElement(jugadorAAgregar.getNombreDeUsuario());
		this.listaJugadores.setModel(modelo);
		
		JLabel lblNewLabel = new JLabel(sala.getNombreSala());
		
		btnIniciarPartida = new JButton("Iniciar partida");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				///deberia permitirme iniciar partida solo si soy el propietario
				if(jugador.getNombreDeUsuario().equals(sala.getJugadorPropietario().getNombreDeUsuario())) {
					///inicia partida
					System.out.println("iniciando partida");
					btnEspectearPartida.setEnabled(true); ///habilito el boton de espectear
				}
				else
					System.out.println("No se tienen permisos para iniciarla");
			}
		});
		
		btnEspectearPartida = new JButton("Espectear partida");
		btnEspectearPartida.setEnabled(false);
		btnEspectearPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///deberia permitirme espectearla solo si ya inicio
				
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
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
		panel.setLayout(gl_panel);
	}
}
