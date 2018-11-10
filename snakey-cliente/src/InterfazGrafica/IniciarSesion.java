package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class IniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ImageIcon fondo;
	JFrame yo;
	PanelIniciaarSesion iniciaarSesion;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IniciarSesion frame = new IniciarSesion();
					frame.setResizable(false);// no lo puedo renderizar
					frame.setVisible(true); // visible al usuario
					frame.setLocationRelativeTo(null); // para que se posisione en el medio de la pantalla

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public IniciarSesion() {
		setTitle("Inicio de sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		iniciaarSesion = new PanelIniciaarSesion();
		iniciaarSesion.setVisible(true);
		contentPane.add(iniciaarSesion);
	}
}
