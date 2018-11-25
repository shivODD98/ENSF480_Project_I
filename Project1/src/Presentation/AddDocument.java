package Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;

public class AddDocument {

	JFrame frmAddDocument;
	JTextField titleField;
	JTextField authorField;
	JTextField descriptionField;
	JRadioButton bookRadio;
	JRadioButton magazineRadio;
	JRadioButton journalRadio;
	JButton createButton;
	private JLabel lblPrice;
	private JTextField textField;



	public AddDocument() {
		initialize();
	}

	private void initialize() {
		frmAddDocument = new JFrame();
		frmAddDocument.setResizable(false);
		frmAddDocument.setTitle("Add Document");
		frmAddDocument.setBounds(100, 100, 630, 534);
		frmAddDocument.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddDocument.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(21, 21, 92, 26);
		frmAddDocument.getContentPane().add(lblTitle);
		
		titleField = new JTextField();
		titleField.setBounds(21, 53, 579, 32);
		frmAddDocument.getContentPane().add(titleField);
		titleField.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author name");
		lblAuthor.setBounds(21, 101, 146, 26);
		frmAddDocument.getContentPane().add(lblAuthor);
		
		authorField = new JTextField();
		authorField.setBounds(21, 133, 267, 32);
		frmAddDocument.getContentPane().add(authorField);
		authorField.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(21, 190, 92, 26);
		frmAddDocument.getContentPane().add(lblType);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 222, 579, 32);
		frmAddDocument.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 3, 0, 0));
		
		bookRadio = new JRadioButton("Book");
		panel.add(bookRadio);
		
		magazineRadio = new JRadioButton("Magazine");
		panel.add(magazineRadio);
		
		journalRadio = new JRadioButton("Journal");
		panel.add(journalRadio);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(21, 325, 579, 78);
		frmAddDocument.getContentPane().add(descriptionField);
		descriptionField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(21, 289, 186, 26);
		frmAddDocument.getContentPane().add(lblDescription);
		
		createButton = new JButton("Create");
		createButton.setBorderPainted(false);
		createButton.setForeground(new Color(255, 255, 255));
		createButton.setBackground(new Color(255, 51, 51));
		createButton.setBounds(213, 427, 141, 35);
		frmAddDocument.getContentPane().add(createButton);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(335, 101, 92, 26);
		frmAddDocument.getContentPane().add(lblPrice);
		
		textField = new JTextField();
		textField.setBounds(333, 133, 267, 32);
		frmAddDocument.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
