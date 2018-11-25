package Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.ImageIcon;

public class UnregisteredBuyerWindow {

	private JFrame frame;
	JList<String> Documentslist;
	JButton orderButton;
	JButton registerButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnregisteredBuyerWindow window = new UnregisteredBuyerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UnregisteredBuyerWindow() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel leftPannel = new JPanel();
		frame.getContentPane().add(leftPannel);
		leftPannel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Documents");
		leftPannel.add(lblNewLabel, BorderLayout.NORTH);
		
		Documentslist = new JList<String>();
		leftPannel.add(Documentslist, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 51, 51));
		leftPannel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		orderButton = new JButton("Order");
		orderButton.setForeground(new Color(255, 255, 255));
		orderButton.setOpaque(false);
		orderButton.setFocusPainted(false);
		orderButton.setContentAreaFilled(false);
		orderButton.setBorderPainted(false);
		panel.add(orderButton);
		
		JPanel rightPannel = new JPanel();
		frame.getContentPane().add(rightPannel);
		rightPannel.setLayout(new BorderLayout(0, 0));
		
		JPanel picPannel = new JPanel();
		rightPannel.add(picPannel, BorderLayout.CENTER);
		picPannel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Lucas\\Pictures\\Camera Roll\\book-cover-poster-template-d0003e2ab393fa2c982a0de15a607486.jpg"));
		picPannel.add(lblNewLabel_1, "4, 3, 7, 16");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 51, 51));
		rightPannel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		registerButton = new JButton("Register");
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setOpaque(false);
		registerButton.setFocusPainted(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setBorderPainted(false);
		panel_2.add(registerButton, BorderLayout.NORTH);
	}

}
