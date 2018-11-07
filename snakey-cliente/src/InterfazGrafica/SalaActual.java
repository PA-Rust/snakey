package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SalaActual extends JFrame {

	private JPanel contentPane;
	private JList<String> listaJugadores;
	private DefaultListModel<String> modelo;
	private ArrayList<Jugador> jugadores ;
	
	public SalaActual(Jugador jugador) {
		jugadores = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addComponent(listaJugadores, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(193, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(70, Short.MAX_VALUE)
					.addComponent(listaJugadores, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		panel.setLayout(gl_panel);
		

		listaJugadores = new JList();
		modelo = new DefaultListModel();
	}
	
	public void agregarJugador(Jugador nuevoJugador){
		this.modelo.addElement(nuevoJugador.getNombreDeUsuario());
		this.jugadores.add(nuevoJugador);
		this.listaJugadores.setModel(modelo);
	}
}
