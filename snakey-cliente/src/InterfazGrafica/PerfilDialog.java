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
	public PerfilDialog(int partidasGanadas, int partidasPerdidas ,int puntos,String nombreUsuario) {
		
		yo = this;
		yo.setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel = new JLabel(nombreUsuario);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		JLabel lblPartidasPerdidas = new JLabel("Partidas Perdidas :");
		lblPartidasPerdidas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPartidasPerdidas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPartidasGanadas = new JLabel("Partidas Ganadas :");
		lblPartidasGanadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPartidasGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPuntajeAcumulado = new JLabel("Puntaje Acumulado :");
		lblPuntajeAcumulado.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntajeAcumulado.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelGanadas = new JLabel(Integer.toString(partidasGanadas));
		labelGanadas.setHorizontalAlignment(SwingConstants.CENTER);
		labelGanadas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel labelPerdidas = new JLabel(Integer.toString(partidasPerdidas));
		labelPerdidas.setHorizontalAlignment(SwingConstants.CENTER);
		labelPerdidas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel labelAcumulado = new JLabel(Integer.toString(puntos));
		labelAcumulado.setHorizontalAlignment(SwingConstants.CENTER);
		labelAcumulado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblPartidasGanadas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblPartidasPerdidas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
								.addComponent(lblPuntajeAcumulado, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
							.addGap(104)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(labelGanadas, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPerdidas, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelAcumulado, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addGap(328))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPartidasGanadas, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelGanadas, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPartidasPerdidas, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPerdidas, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPuntajeAcumulado, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelAcumulado, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
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
			/*{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}*/
		}
	}
}
}
