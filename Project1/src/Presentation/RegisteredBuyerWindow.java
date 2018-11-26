package Presentation;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JButton;
import SharedObjects.Document;
import SharedObjects.PaymentInfo;
import SharedObjects.PaymentType;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

public class RegisteredBuyerWindow implements ListSelectionListener {

	//buyer window
	JFrame frame;
	DefaultListModel<Document> documentsListModel;
	JList<Document> documentsList;
	DefaultListModel<Document> promotionListModel;
	JList<Document> promotionList;
	JButton orderButton;
	JButton unsubButton;
	
	//orderWindow
	JFrame orderFrame;
	JTextField paymentField;
	JButton confirmButton;
	JRadioButton rdbtnCash;
	JRadioButton rdbtnDebit;
	JRadioButton rdbtnCredit;
	ButtonGroup group;
	
	public RegisteredBuyerWindow() {
		initialize();
		orderButton.setEnabled(false);
		documentsList.addListSelectionListener(this);
		promotionList.addListSelectionListener(this);
	}
	
	
	
	public void addActionListener(ActionListener e) {
		orderButton.addActionListener(e);
		unsubButton.addActionListener(e);
	}

	private void initialize() {
		frame = new JFrame("Registerd Buyer");
		frame.setBounds(100, 100, 741, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		
		JPanel rightPannel = new JPanel();
		frame.getContentPane().add(rightPannel);
		rightPannel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 31));
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		rightPannel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel documentsPanel = new JPanel();
		tabbedPane.addTab("Documents", new ImageIcon(RegisteredBuyerWindow.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Bullets-Black.png")), documentsPanel, null);
		documentsListModel = new DefaultListModel<Document>();
		documentsPanel.setLayout(new BorderLayout(0, 0));
		documentsList = new JList<Document>(documentsListModel);
		documentsList.setFont(new Font("Tahoma", Font.PLAIN, 31));
		documentsPanel.add(documentsList);
		
		JPanel promotionListPanel = new JPanel();
		tabbedPane.addTab("Promotions", new ImageIcon(RegisteredBuyerWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")), promotionListPanel, null);
		
		promotionListModel = new DefaultListModel<Document>();
		promotionListPanel.setLayout(new BorderLayout(0, 0));
		promotionList = new JList<Document>(promotionListModel);
		promotionListPanel.add(promotionList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 51, 51));
		rightPannel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		orderButton = new JButton("Order");
		orderButton.setForeground(new Color(255, 255, 255));
		orderButton.setOpaque(false);
		orderButton.setFocusPainted(false);
		orderButton.setContentAreaFilled(false);
		orderButton.setBorderPainted(false);
		panel_1.add(orderButton);
		
		JPanel leftPannel = new JPanel();
		frame.getContentPane().add(leftPannel);
		leftPannel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 51, 51));
		leftPannel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		unsubButton = new JButton("un-subscribe");
		unsubButton.setForeground(new Color(255, 255, 255));
		unsubButton.setOpaque(false);
		unsubButton.setFocusPainted(false);
		unsubButton.setContentAreaFilled(false);
		unsubButton.setBorderPainted(false);
		panel_2.add(unsubButton);
		
		JPanel panel = new JPanel();
		leftPannel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("C:\\Users\\Lucas\\Pictures\\Camera Roll\\book-cover-poster-template-d0003e2ab393fa2c982a0de15a607486.jpg"));
		panel.add(label, BorderLayout.CENTER);
		
		JLabel descriptionLabel = new JLabel("New label");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(descriptionLabel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	public void updateDocumentsListModel(ArrayList<Document> docs) {
		for(Document e: docs) {
			documentsListModel.addElement(e);
		}
	}
	
	public void updatePromotionsListModel(ArrayList<Document> docs) {
		for(Document e: docs) {
			promotionListModel.addElement(e);
		}
	}
	
	public Document getSelectedDoc() {
		if(documentsSelected) {
			if(documentsList.getSelectedIndex() > -1)
				return documentsListModel.getElementAt(documentsList.getSelectedIndex());
			return null;
		}
		else if(promotionSelected) {
			if(promotionList.getSelectedIndex() > -1)
				return promotionListModel.getElementAt(promotionList.getSelectedIndex());
			return null;
		}
		else
			return null;
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
		PaymentInfo info = new PaymentInfo(-1, type, new Date(), doc); //need to include sum
		return info;
	}
	
	boolean documentsSelected = false;
	boolean promotionSelected = false;
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == documentsList) {
			documentsSelected = true;
			promotionSelected = false;
			if(documentsList.getSelectedIndex() > -1)
				orderButton.setEnabled(true);
		}
		else if (e.getSource() == promotionList) {
			documentsSelected = false;
			promotionSelected = true;
			if(promotionList.getSelectedIndex() > -1)
				orderButton.setEnabled(true);
		}
	}
}
