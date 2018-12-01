package InterfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class ConfigDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dialog yo;
	public int up,down,rigth,left;
	public static final String archivoConfig= "KEYS_CONFIG";

	public ConfigDialog() {
		yo = this;
		yo.setModal(true);
		setBounds(100, 100, 260, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		try {
			leerArchivoConfig();
		} catch (IOException e2) {
			this.up=KeyEvent.VK_UP;
			this.left=KeyEvent.VK_LEFT;
			this.rigth= KeyEvent.VK_RIGHT;
			this.down= KeyEvent.VK_DOWN;
			e2.printStackTrace();
		}
		
		JButton btnDown = new JButton(KeyEvent.getKeyText(down));
		
		JButton btnLeft = new JButton(KeyEvent.getKeyText(left));
		
		JButton btnRight = new JButton(KeyEvent.getKeyText(rigth));
		
		JButton btnUp = new JButton(KeyEvent.getKeyText(up));
		
		JLabel lblConfiguracionDeControles = new JLabel("Configuracion de controles");
		
		JButton btnS = new JButton("New button");
		
		JButton btnD = new JButton("New button");
		
		JButton btnA = new JButton("New button");
		
		JButton btnW = new JButton("New button");
		btnDown.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	down=e.getKeyCode();
		         btnDown.setText(KeyEvent.getKeyText(down));
		         
		    } 
		});
		btnLeft.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	left=e.getKeyCode();
		         btnLeft.setText(KeyEvent.getKeyText(left));
		         
		    } 
		});
		btnUp.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	up=e.getKeyCode();
		         btnUp.setText(KeyEvent.getKeyText(up));
		         
		    } 
		});
		btnRight.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	rigth=e.getKeyCode();
			btnRight.setText(KeyEvent.getKeyText(rigth));
		         
		    } 
		});
		
		JLabel lblConfiguracionDeChat = new JLabel("Configuracion de chat");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConfiguracionDeControles)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnUp, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(btnLeft, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnRight, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnDown, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
						.addComponent(btnS, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(btnA, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(btnD, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnW, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfiguracionDeChat, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConfiguracionDeControles)
					.addGap(18)
					.addComponent(btnUp)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLeft)
						.addComponent(btnRight))
					.addGap(18)
					.addComponent(btnDown)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(lblConfiguracionDeChat)
					.addGap(18)
					.addComponent(btnW)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnD)
						.addComponent(btnA))
					.addGap(18)
					.addComponent(btnS))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

							try {
								PrintWriter pw = new PrintWriter(new File(archivoConfig));
								pw.println(up);
								pw.println(left);
								pw.println(down);
								pw.println(rigth);
								pw.close();
								dispose();
								
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	private void leerArchivoConfig() throws IOException {
		Scanner sc = new Scanner(new File(archivoConfig));
		//up left down right
		this.up= sc.nextInt();
		this.left= sc.nextInt();
		this.down= sc.nextInt();
		this.rigth= sc.nextInt();
		sc.close();
		
	}
}
