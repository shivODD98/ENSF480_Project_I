package Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class OrderWindow {

	JFrame orderFrame;
	JTextField paymentField;
	JButton confirmButton;
	JRadioButton rdbtnCash;
	JRadioButton rdbtnDebit;
	JRadioButton rdbtnCredit;
	private JLabel lblPrice;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderWindow window = new OrderWindow();
					window.orderFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public OrderWindow() {
		initialize("");
	}


	private void initialize(String title) {
		orderFrame = new JFrame();
		orderFrame.setBounds(100, 100, 550, 359);
		orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		orderFrame.getContentPane().setLayout(null);
		
		JLabel placeOrderLabel = new JLabel("Place Order For "+title);
		placeOrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		placeOrderLabel.setBounds(0, 0, 524, 26);
		orderFrame.getContentPane().add(placeOrderLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 26, 524, 227);
		orderFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		paymentField = new JTextField();
		paymentField.setBounds(45, 174, 428, 32);
		panel.add(paymentField);
		paymentField.setColumns(10);
		
		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(67, 79, 92, 35);
		panel.add(rdbtnCash);
		
		rdbtnDebit = new JRadioButton("Debit");
		rdbtnDebit.setBounds(199, 78, 95, 35);
		panel.add(rdbtnDebit);
		
		rdbtnCredit = new JRadioButton("Credit");
		rdbtnCredit.setBounds(341, 78, 201, 35);
		panel.add(rdbtnCredit);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setBounds(45, 21, 226, 26);
		panel.add(lblPaymentMethod);
		
		JLabel lblPaymentDetails = new JLabel("payment details");
		lblPaymentDetails.setBounds(45, 140, 289, 26);
		panel.add(lblPaymentDetails);
		
		lblPrice = new JLabel("Price: $30");
		lblPrice.setBounds(311, 21, 162, 26);
		panel.add(lblPrice);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(0, 253, 524, 35);
		orderFrame.getContentPane().add(confirmButton);
	}
}
