package webscrape;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Rectangle;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class UtrGui {

	private JFrame frame;
	private JTextField txtNovak;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UtrGui window = new UtrGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UtrGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(SystemColor.desktop);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Verdana", Font.BOLD, 16));
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Get Top 1000 ATP");
		btnNewButton.setBounds(new Rectangle(5, 5, 5, 5));
		btnNewButton.setBackground(SystemColor.controlText);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 16));
		btnNewButton.setBorder(new LineBorder(SystemColor.activeCaptionText, 3, true));
		
		txtNovak = new JTextField();
		txtNovak.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNovak.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Player:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setToolTipText("Novak Djokovic");
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(SystemColor.desktop);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UtrGui.class.getResource("/images/Webp.net-resizeimage (4).png")));
		
		JTextPane textPane = new JTextPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNovak, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(231)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNovak, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
							.addGap(97)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}
