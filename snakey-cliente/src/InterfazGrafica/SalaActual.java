package InterfazGrafica;

import java.awt.Color;
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
import javax.swing.SwingConstants;
import java.awt.Font;

public class SalaActual extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	JFrame yo;
	Jugador jugadorAAgregar;
	DefaultListModel<String> modelo;
	private JLabel lblNombreDeLaSala;
	private JLabel lblJugadores;
	private JList listaJugadores;
	private JButton btnIniciarPartida;
	private JButton btnEspectarPartida;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SalaActual(Jugador jugador, Sala sala) {
		setTitle("Sala actual");
		this.jugadorAAgregar = jugador;
		yo = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 299);
		contentPane = new PanelDeFondo("fondoSalaActual.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setContentPane(contentPane);
		setVisible(true);
		modelo = new DefaultListModel();
		this.modelo.addElement(jugadorAAgregar.getNombreDeUsuario());

		lblNombreDeLaSala = new JLabel(sala.getNombreSala());
		lblNombreDeLaSala.setForeground(Color.WHITE);
		lblNombreDeLaSala.setFont(new Font("Tekton Pro", Font.BOLD | Font.ITALIC, 17));
		lblNombreDeLaSala.setHorizontalAlignment(SwingConstants.CENTER);

		lblJugadores = new JLabel("Jugadores");
		lblJugadores.setForeground(Color.WHITE);

		listaJugadores = new JList();
		modelo = new DefaultListModel();
		this.modelo.addElement(jugadorAAgregar.getNombreDeUsuario());
		this.listaJugadores.setModel(modelo);

		btnIniciarPartida = new JButton("Iniciar partida");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/// deberia permitirme iniciar partida solo si soy el propietario
				if (jugador.getNombreDeUsuario().equals(sala.getJugadorPropietario().getNombreDeUsuario())) {
					/// inicia partida
					System.out.println("iniciando partida");
					btnEspectarPartida.setEnabled(true); /// habilito el boton de espectear
				} else
					System.out.println("No se tienen permisos para iniciarla");
			}
		});

		btnEspectarPartida = new JButton("Espectear partida");
		btnEspectarPartida.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnIniciarPartida, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblJugadores, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
				.addComponent(btnEspectarPartida, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
				.addComponent(listaJugadores, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
				.addComponent(lblNombreDeLaSala, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblNombreDeLaSala, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblJugadores).addGap(10)
						.addComponent(listaJugadores, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnIniciarPartida)
								.addComponent(btnEspectarPartida))));
		contentPane.setLayout(gl_contentPane);
	}
}
