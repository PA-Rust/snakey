package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Commons.Jugador;
import Commons.Sala;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PerfilDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private PerfilDialog yo;

	
	/**
	 * Create the dialog.
	 */
	public PerfilDialog(int partidasGanadas, int puntos, int manzanitas, String nombreUsuario) {
		setTitle("Perfil");
		
		yo = this;
		yo.setModal(true);
		setBounds(100, 100, 425, 223);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel(nombreUsuario);
			lblNewLabel.setBounds(5, 5, 394, 38);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		JLabel lblPartidasGanadas = new JLabel("Partidas Ganadas :");
		lblPartidasGanadas.setBounds(5, 55, 175, 32);
		lblPartidasGanadas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPartidasGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPuntajeAcumulado = new JLabel("Puntaje Acumulado :");
		lblPuntajeAcumulado.setBounds(5, 84, 175, 32);
		lblPuntajeAcumulado.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntajeAcumulado.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel labelGanadas = new JLabel(Integer.toString(partidasGanadas));
		labelGanadas.setBounds(170, 59, 140, 25);
		labelGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		labelGanadas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel labelAcumulado = new JLabel(Integer.toString(puntos));
		labelAcumulado.setBounds(170, 88, 140, 25);
		labelAcumulado.setHorizontalAlignment(SwingConstants.CENTER);
		labelAcumulado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.setLayout(null);
		contentPanel.add(lblNewLabel);
		contentPanel.add(lblPartidasGanadas);
		contentPanel.add(lblPuntajeAcumulado);
		contentPanel.add(labelGanadas);
		contentPanel.add(labelAcumulado);
		
		JLabel lblMazanitasConsumidas = new JLabel("Mazanitas Consumidas");
		lblMazanitasConsumidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMazanitasConsumidas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMazanitasConsumidas.setBounds(5, 113, 175, 32);
		contentPanel.add(lblMazanitasConsumidas);
		
		JLabel lblManzanitas = new JLabel(Integer.toString(manzanitas));
		lblManzanitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblManzanitas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblManzanitas.setBounds(170, 117, 140, 25);
		contentPanel.add(lblManzanitas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						dispose();
					}
				});
			
		}
	}
}
}
