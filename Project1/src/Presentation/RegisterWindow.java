package Presentation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;

public class RegisterWindow {

	 JFrame registerFrame;
	 JTextField firstnameField;
	 JTextField lastnameField;
	 JTextField emailField;
	 JTextField passwordField;


	public RegisterWindow() {
		initialize();
	}

	private void initialize() {
		registerFrame = new JFrame();
		registerFrame.setBounds(100, 100, 662, 500);
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblEmailAddress = new JLabel("Email Address");
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
		
		JButton registerButton = new JButton("Register");
		registerButton.setBackground(new Color(255, 51, 51));
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setBounds(230, 373, 141, 35);
		registerFrame.getContentPane().add(registerButton);
	}

}
