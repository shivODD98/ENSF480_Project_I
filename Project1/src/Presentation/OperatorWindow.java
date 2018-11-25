package Presentation;

import SharedObjects.Document;
import SharedObjects.DocumentType;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OperatorWindow implements ListSelectionListener{

	//Operator
	private JFrame operatorFrame;
	JButton moveToPromotionButton;
	JButton addButton;
	JButton deleteButton;
	JButton updateDocument;
	DefaultListModel<Document> documentsListModel;
	JList<Document> documentsList;
	DefaultListModel<Document> promotionListModel;
	JList<Document> promotionList;
	
	
	//add
	JFrame frmAddDocument;
	JTextField titleField;
	JTextField authorField;
	JTextField descriptionField;
	JRadioButton bookRadio;
	JRadioButton magazineRadio;
	JRadioButton journalRadio;
	JButton createButton;
	JTextField priceField;
    ButtonGroup typeGroupe = new ButtonGroup();

	

	public void addActionListener(ActionListener e)
	{
		moveToPromotionButton.addActionListener(e);
		addButton.addActionListener(e);
		deleteButton.addActionListener(e);
		updateDocument.addActionListener(e);
	}
	
	public void addListListener(ListSelectionListener l) {
		documentsList.addListSelectionListener(l);
		promotionList.addListSelectionListener(l);
	}	

	public OperatorWindow() {
		createWindow();
		disableTopButtons();
		moveToPromotionButton.setEnabled(false);
	}

	public void disableTopButtons() {
		deleteButton.setEnabled(false);
		updateDocument.setEnabled(false);
	}
	
	public void enableTopButtons() {
		deleteButton.setEnabled(true);
		updateDocument.setEnabled(true);
	}
	
	public void createWindow() {
		operatorFrame = new JFrame("Operator Window");
		operatorFrame.setVisible(true);
		operatorFrame.setBounds(100, 100, 636, 540);
		operatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		operatorFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPannel = new JPanel();
		bottomPannel.setBackground(new Color(255, 51, 51));
		operatorFrame.getContentPane().add(bottomPannel, BorderLayout.SOUTH);
		
		moveToPromotionButton = new JButton("Add To Promotion List");
		moveToPromotionButton.setForeground(new Color(255, 255, 255));
		moveToPromotionButton.setFocusPainted(false);
		moveToPromotionButton.setContentAreaFilled(false);
		moveToPromotionButton.setBorderPainted(false);
		moveToPromotionButton.setOpaque(false);
		bottomPannel.add(moveToPromotionButton);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 51, 51));
		operatorFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		topPanel.add(panel);
		
		addButton = new JButton("Add Document");
		addButton.setContentAreaFilled(false);
		addButton.setBorderPainted(false);
		addButton.setFocusPainted(false);
		addButton.setOpaque(false);
		addButton.setForeground(new Color(255, 255, 255));
		panel.add(addButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		topPanel.add(panel_1);
		
		deleteButton = new JButton("Delete Document");
		deleteButton.setForeground(new Color(255, 255, 255));
		deleteButton.setFocusPainted(false);
		deleteButton.setOpaque(false);
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorderPainted(false);
		panel_1.add(deleteButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		topPanel.add(panel_2);
		
		updateDocument = new JButton("Update Document");
		updateDocument.setForeground(new Color(255, 255, 255));
		updateDocument.setFocusPainted(false);
		updateDocument.setOpaque(false);
		updateDocument.setContentAreaFilled(false);
		updateDocument.setBorderPainted(false);
		panel_2.add(updateDocument);
		
		JPanel middlePannel = new JPanel();
		operatorFrame.getContentPane().add(middlePannel, BorderLayout.CENTER);
		middlePannel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel leftPanel = new JPanel();
		middlePannel.add(leftPanel);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDocuments = new JLabel("Documents List");
		lblDocuments.setFont(new Font("Tahoma", Font.PLAIN, 30));
		leftPanel.add(lblDocuments, BorderLayout.NORTH);
		
		documentsListModel = new DefaultListModel<Document>();
		documentsList = new JList<Document>(documentsListModel);
		documentsList.setFont(new Font("Tahoma", Font.PLAIN, 31));
		documentsList.setBorder(new LineBorder(new Color(100, 100, 100), 1, true));
		documentsList.addListSelectionListener(this);
		leftPanel.add(documentsList, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		middlePannel.add(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPromotion = new JLabel("Promotion List");
		lblPromotion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		rightPanel.add(lblPromotion, BorderLayout.NORTH);
		
		promotionListModel = new DefaultListModel<Document>();
		promotionList = new JList<Document>(promotionListModel);
		promotionList.setFont(new Font("Tahoma", Font.PLAIN, 31));
		promotionList.setBorder(new LineBorder(SystemColor.windowBorder, 1, true));
		promotionList.addListSelectionListener(this);
		rightPanel.add(promotionList, BorderLayout.CENTER);
	}
	
	public void updateDocumentsListModel(ArrayList<Document> docs) {
		documentsListModel.removeAllElements();
		for(Document doc:docs) {
			documentsListModel.addElement(doc);
		}
	}
	
	public void updatePromotionListModel(ArrayList<Document> docs) {
		promotionListModel.removeAllElements();
		for(Document doc:docs) {
			promotionListModel.addElement(doc);
		}
	}
	
	public Document addToPromotionList() {
		
		if(documentsList.getSelectedIndex() > -1) {
			Document doc = documentsListModel.getElementAt(documentsList.getSelectedIndex());
			if(promotionListModel.contains(doc))
				return null;
			else {
				promotionListModel.addElement(doc);
				moveToPromotionButton.setEnabled(false);
				return doc;
			}
		}
		return null;
	}
	
	public Document removeFromPromotionList() {
		if(promotionList.getSelectedIndex() > -1) {
			Document doc = promotionListModel.getElementAt(promotionList.getSelectedIndex());
			promotionListModel.removeElementAt(promotionList.getSelectedIndex());
			moveToPromotionButton.setEnabled(false);
			return doc;
		}
		return null;
	}
	
	public Document removeFromList() {
		if(isDocumentsSelected()) {
			if(documentsList.getSelectedIndex() > -1) {
				Document doc = documentsListModel.getElementAt(documentsList.getSelectedIndex());
				documentsListModel.removeElementAt(documentsList.getSelectedIndex());
				promotionListModel.removeElement(doc);
				return doc;
			}
			return null;
		}
		else
			return null;
	}
	
	public void createAddWindow() {
		frmAddDocument = new JFrame();
		frmAddDocument.setResizable(false);
		frmAddDocument.setTitle("Add Document");
		frmAddDocument.setBounds(100, 100, 630, 534);
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
		typeGroupe.add(bookRadio);
		
		magazineRadio = new JRadioButton("Magazine");
		typeGroupe.add(magazineRadio);
		panel.add(magazineRadio);
		
		journalRadio = new JRadioButton("Journal");
		typeGroupe.add(journalRadio);
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
		createButton.setBounds(236, 427, 141, 35);
		frmAddDocument.getContentPane().add(createButton);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(335, 101, 92, 26);
		frmAddDocument.getContentPane().add(lblPrice);
		
		priceField = new JTextField();
		priceField.setBounds(333, 133, 267, 32);
		frmAddDocument.getContentPane().add(priceField);
		priceField.setColumns(10);
		
		frmAddDocument.setVisible(true);
	}

	public Document createDocument() {
		DocumentType type;
		if(bookRadio.isSelected()) 
			type = DocumentType.Book;
		else if(magazineRadio.isSelected()) 
			type = DocumentType.Magazine;	
		
		else if(journalRadio.isSelected()) 
			type = DocumentType.Journal;
		else
			return null;
		
		frmAddDocument.dispose();
		
		return new Document(-1, titleField.getText(), authorField.getText(), null, descriptionField.getText(), type, Double.parseDouble(priceField.getText()));
	}
	
	public void modifyDocument() {
		if(isDocumentsSelected() && documentsList.getSelectedIndex() > -1) {
			Document doc = documentsListModel.getElementAt(documentsList.getSelectedIndex());
			titleField.setText(doc.getTitle());
			authorField.setText(doc.getAuthor());
			descriptionField.setText(doc.getDescription());
			switch(doc.getType()) {
			case Book:
				bookRadio.setSelected(true);
			case Magazine:
				magazineRadio.setSelected(true);
			case Journal:
				journalRadio.setSelected(true);
			}
		}
	}
	
	private boolean documentsSelected = false;
	private boolean promotionListSelected = false;
	
	public boolean isPromotionListSeleted() {return promotionListSelected;}
	public boolean isDocumentsSelected() {return documentsSelected;}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == promotionList) {
			disableTopButtons();
			moveToPromotionButton.setEnabled(true);
			documentsList.clearSelection();
			moveToPromotionButton.setText("Remove From Promotion List");
			documentsSelected = false;
			promotionListSelected = true;
		}
		else if(e.getSource() == documentsList) {
			enableTopButtons();
			moveToPromotionButton.setEnabled(true);
			promotionList.clearSelection();
			moveToPromotionButton.setText("Add To Promotion List");
			documentsSelected = true;
			promotionListSelected = false;
		}
	}
	
}
