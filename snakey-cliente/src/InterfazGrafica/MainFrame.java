package InterfazGrafica;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Comunicacion.EstadosAplicacion;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 8907217604004146181L;
	private JPanel contentPane;
	private JTextField textIp;
	private JTextField textPort;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("Conexion con Servidor");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 82);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblServerIp = new JLabel("Server IP");
		contentPane.add(lblServerIp);
		
		textIp = new JTextField();
		textIp.setText("127.0.0.1");
		contentPane.add(textIp);
		textIp.setColumns(10);
		
		JLabel lblServerPort = new JLabel("Server Port");
		contentPane.add(lblServerPort);
		
		textPort = new JTextField();
		textPort.setText("3000");
		contentPane.add(textPort);
		textPort.setColumns(10);
		
		JButton btnConectarse = new JButton("Conectarse");
		btnConectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EstadosAplicacion.getInstancia().setServerIP(textIp.getText());
				EstadosAplicacion.getInstancia().setServerPuerto(Integer.parseInt(textPort.getText()));
				IniciarSesion iniciarSesion = new IniciarSesion();
				iniciarSesion.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		contentPane.add(btnConectarse);
	}

}
