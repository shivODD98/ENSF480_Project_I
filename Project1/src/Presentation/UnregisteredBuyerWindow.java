package Presentation;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import SharedObjects.Document;
import SharedObjects.PaymentInfo;
import SharedObjects.PaymentType;
import SharedObjects.User;

import java.awt.Font;

public class UnregisteredBuyerWindow implements ListSelectionListener {

	JFrame frame;
	DefaultListModel<Document> documentsListModel;
	JList<Document> documentsList;
	JButton orderButton;
	JButton registerButton;

	//orderWindow
	JFrame orderFrame;
	JTextField paymentField;
	JButton confirmButton;
	JRadioButton rdbtnCash;
	JRadioButton rdbtnDebit;
	JRadioButton rdbtnCredit;
	ButtonGroup group;
	
	//register window
	 JFrame registerFrame;
	 JTextField firstnameField;
	 JTextField lastnameField;
	 JTextField emailField;
	 JTextField passwordField;
	 JButton registerButtonConfirm;


	public UnregisteredBuyerWindow() {
		initialize();
		orderButton.setEnabled(false);
		documentsList.addListSelectionListener(this);
	}
	
	public void updateDocumentsListModel(ArrayList<Document> docs) {
		for(Document e: docs) {
			documentsListModel.addElement(e);
		}
	}
	
	public void setActionListener(ActionListener e) {
		orderButton.addActionListener(e);
		registerButton.addActionListener(e);
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
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		leftPannel.add(lblNewLabel, BorderLayout.NORTH);
		
		documentsListModel = new DefaultListModel<Document>();
		documentsList = new JList<Document>(documentsListModel);
		documentsList.setFont(new Font("Tahoma", Font.PLAIN, 31));
		leftPannel.add(documentsList, BorderLayout.CENTER);
		
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
		
		JPanel panel_1 = new JPanel();
		rightPannel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Lucas\\Pictures\\Camera Roll\\book-cover-poster-template-d0003e2ab393fa2c982a0de15a607486.jpg"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		frame.setVisible(true);
	}

	public void createOrderWindow(String title, Double price) {
		orderFrame = new JFrame("Order");
		orderFrame.setBounds(100, 100, 550, 359);
		orderFrame.getContentPane().setLayout(null);
		
		group = new ButtonGroup();
		
		JLabel placeOrderLabel = new JLabel("Place Order For "+title);
		placeOrderLabel.setBounds(0, 0, 524, 26);
		placeOrderLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
		group.add(rdbtnCash);
		panel.add(rdbtnCash);
		
		rdbtnDebit = new JRadioButton("Debit");
		rdbtnDebit.setBounds(199, 78, 95, 35);
		group.add(rdbtnDebit);
		panel.add(rdbtnDebit);
		
		rdbtnCredit = new JRadioButton("Credit");
		rdbtnCredit.setBounds(341, 78, 201, 35);
		group.add(rdbtnCredit);
		panel.add(rdbtnCredit);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setBounds(45, 21, 406, 26);
		panel.add(lblPaymentMethod);
		
		JLabel lblPaymentDetails = new JLabel("payment details");
		lblPaymentDetails.setBounds(45, 140, 289, 26);
		panel.add(lblPaymentDetails);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(0, 253, 524, 35);
		orderFrame.getContentPane().add(confirmButton);
		
		JLabel priceLabel = new JLabel("Price: $"+String.format("%.2f", price));
		priceLabel.setBounds(311, 21, 162, 26);
		panel.add(priceLabel);
		
		orderFrame.setVisible(true);
	}

	
	public void createRegisterWindow() {
		registerFrame = new JFrame();
		registerFrame.setBounds(100, 100, 662, 500);
		registerFrame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(83, 86, 169, 26);
		registerFrame.getContentPane().add(lblFirstName);
		
		firstnameField = new JTextField();
		firstnameField.setBounds(83, 116, 186, 32);
		registerFrame.getContentPane().add(firstnameField);
		firstnameField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(342, 86, 128, 26);
		registerFrame.getContentPane().add(lblLastName);
		
		lastnameField = new JTextField();
		lastnameField.setBounds(342, 116, 186, 32);
		registerFrame.getContentPane().add(lastnameField);
		lastnameField.setColumns(10);
		
		JLabel lblEmailAddress = new JLabel("Username");
		lblEmailAddress.setBounds(83, 179, 254, 26);
		registerFrame.getContentPane().add(lblEmailAddress);
		
		emailField = new JTextField();
		emailField.setBounds(83, 212, 445, 32);
		registerFrame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(83, 281, 186, 26);
		registerFrame.getContentPane().add(lblPassword);
		
		passwordField = new JTextField();
		passwordField.setBounds(83, 313, 445, 32);
		registerFrame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setBounds(252, 21, 92, 26);
		registerFrame.getContentPane().add(lblRegister);
		
		registerButtonConfirm = new JButton("Register");
		registerButtonConfirm.setBackground(new Color(255, 51, 51));
		registerButtonConfirm.setForeground(new Color(255, 255, 255));
		registerButtonConfirm.setBounds(230, 373, 141, 35);
		registerFrame.getContentPane().add(registerButtonConfirm);
		registerFrame.setVisible(true);
	}

	public Document getSelectedDoc() {
		if(documentsList.getSelectedIndex() > -1)
			return documentsListModel.getElementAt(documentsList.getSelectedIndex());
		return null;
	}
	
	public PaymentInfo createOrder(Document doc) {
		PaymentType type;
		if(rdbtnCash.isSelected())
			type = PaymentType.Cash;
		else if(rdbtnCredit.isSelected())
			type = PaymentType.Credit;
		else if(rdbtnDebit.isSelected())
			type = PaymentType.Debit;
		else 
			return null;
		PaymentInfo info = new PaymentInfo(-1, type, new Date(), doc);
		return info;
	}

	public User registerUser() {
		if(firstnameField.getText() == "" || lastnameField.getText() == "" || emailField.getText() == "")
			return null;
		
		return new User(-1, firstnameField.getText(), lastnameField.getText(), emailField.getText(), null);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == this.documentsList) {
			if(documentsList.getSelectedIndex() > -1)
				orderButton.setEnabled(true);
		}
	}

}
