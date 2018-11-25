package Presentation;
import SharedObjects.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.SwingConstants;
/**
 * This class iherits Jrame and is the loginWindow GUI for the Client 
 */
public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 8873001050468067183L;
	/**
	 * JPanels for LoginWindow
	 */
	private JPanel contentPane;
	/**
	 * JTextFields for LoginWindow
	 */
	private JTextField usernameTxt, emailTxt, newUsernameTxt;
	/**
	 * JPasswordField for LoginWindow
	 */
	private JPasswordField passwordTxt, newPasswordTxt, newPasswordCheckTxt;
	/**
	 * JBUtton for LoginWindow
	 */
	private JButton loginButton, createAccount, createButton;
	/**
	 * JFrame to create a new Account 
	 */
	private JFrame createAccountWindow;
	/**
	 * Radio Buttons for LoginWindow
	 */
	private JRadioButton rdbtnStudent, rdbtnProfessor;


	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public LoginWindow() throws IOException {
		super("D3L");

		//Image icon = new ImageIcon(this.getClass().getResource("/if_login_59582.png")).getImage();
		//setIconImage(icon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(535, 382);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel labelLogin = new JLabel("Login");
		labelLogin.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
		labelLogin.setBounds(66, 17, 121, 51);
		contentPane.add(labelLogin);
		
		JLabel lblaccount = new JLabel("Dont have an account? ");
		lblaccount.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));
		lblaccount.setBounds(66, 302, 205, 20);
		contentPane.add(lblaccount);
		
		JLabel labelUsername = new JLabel("Username (Lastname)");
		labelUsername.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		labelUsername.setBounds(66, 96, 176, 20);
		contentPane.add(labelUsername);
		
		usernameTxt = new JTextField();
		usernameTxt.setToolTipText("Enter your last name.");
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameTxt.setBounds(66, 120, 405, 26);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JLabel lablePassword = new JLabel("Password");
		lablePassword.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		lablePassword.setBounds(66, 176, 89, 20);
		contentPane.add(lablePassword);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordTxt.setBounds(66, 198, 405, 26);
		contentPane.add(passwordTxt);
		
		loginButton = new JButton("Login");
		loginButton.setForeground(new Color(240, 248, 255));
		loginButton.setBackground(new Color(70, 130, 180));
		loginButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		loginButton.setBounds(66, 261, 405, 29);
		contentPane.add(loginButton);

		
		JLabel loginIcon = new JLabel("");
		//Image doorIcon = new ImageIcon(this.getClass().getResource("/if_Login_in_85205.png")).getImage();
		//loginIcon.setIcon(new ImageIcon(doorIcon));
		loginIcon.setBounds(148, 7, 94, 73);
		contentPane.add(loginIcon);
		
		JLabel schulichIcon = new JLabel("");
		schulichIcon.setBounds(309, 7, 205, 104);
		//Image schulich = new ImageIcon(this.getClass().getResource("/01schulich.png")).getImage();
		//Image dimg = schulich.getScaledInstance(schulichIcon.getWidth(), schulichIcon.getHeight(), Image.SCALE_SMOOTH);
		//ImageIcon thisImg = new ImageIcon(dimg);
		//schulichIcon.setIcon(thisImg);
		contentPane.add(schulichIcon);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 529, 342);
		//Image backgroundIcon = new ImageIcon(this.getClass().getResource("/images.jpg")).getImage();
		//Image dimg2 = backgroundIcon.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		//ImageIcon thisImg2 = new ImageIcon(dimg2);
		
		createAccount = new JButton("Continue Un-registered");
		createAccount.setHorizontalAlignment(SwingConstants.LEFT);
		createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		createAccount.setBorderPainted(false);
		createAccount.setForeground(SystemColor.textHighlight);
		createAccount.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));
		createAccount.setContentAreaFilled(false);
		createAccount.setBounds(251, 298, 250, 29);
		contentPane.add(createAccount);
		//background.setIcon(thisImg2);
		contentPane.add(background);
		setVisible(true);

		
	}
	
	/**
	 * Sets the ActionsListerners for the Components of the LoginWindow
	 * @param e is the Action Listener
	 */
	public void setLoginButtonListener(ActionListener e)
	{
		loginButton.addActionListener(e);
		createAccount.addActionListener(e);
	}
	/**
	 * Getter for loginButton button
	 * @return loginButton
	 */
	public JButton getLoginButton() { return loginButton; }
	/**
	 * Getter for createAccount button
	 * @return createAccount
	 */
	public JButton getCreateAccount() {return createAccount;}
	
	/**
	 * Getter for createButton button
	 * @return createButton
	 */
	public JButton getCreateButton() {return createButton;}
	
	/**
	 * Getter for createAccount button
	 * @return createAccount
	 */
	public JFrame getCreateAccountWindow() {return createAccountWindow;}
	
	/**
	 * Gets the the data text fields from the LoginWindow and creates and instance of LoginInfo
	 * @return LogInfo
	 */
	public LoginInfo getLoginInfo()
	{
		return new LoginInfo(usernameTxt.getText(), passwordTxt.getText());
	}
	
	/**
	 * Gets the the data text fields from the Create Account GUI and creates and instance of Account
	 * @return createAccount
	 */
//	public Account getNewUser()
//	{
//		if(!newPasswordTxt.getText().equals(newPasswordCheckTxt.getText())){
//			JOptionPane.showMessageDialog(this, "Passwords Dont Match", null, JOptionPane.ERROR_MESSAGE);
//			newPasswordTxt.setText("");
//			newPasswordCheckTxt.setText("");
//			return null;
//		}
//		else {
//			StringTokenizer s = new StringTokenizer(newUsernameTxt.getText());
//			String first = s.nextToken();
//			String last = s.nextToken();
//			char type = ' ';
//			if(rdbtnStudent.isSelected())
//				type = 's';
//			else if(rdbtnProfessor.isSelected())
//				type = 'p';
//			else {
//				JOptionPane.showMessageDialog(this, "Please select a type", null, JOptionPane.ERROR_MESSAGE);
//				return null;
//			}
//			return new Account(first, last, emailTxt.getText(), newPasswordTxt.getText(), type);
//		}
//			
//	}
//	
	/**
	 * Opens a dialog box that idnciates inccorect login information
	 */
	public void incorrectLogin()
	{
		JOptionPane.showMessageDialog(this, "Incorrect Login Information","Error", JOptionPane.ERROR_MESSAGE);
		usernameTxt.setText("");
		passwordTxt.setText("");
	}
	
	/**
	 * Creates the Create Account GUI
	 */
//public void createAccountWindow()
//	{
//		createAccountWindow = new JFrame();
//		Image icon = new ImageIcon(this.getClass().getResource("/if_login_59582.png")).getImage();
//		createAccountWindow.setIconImage(icon);
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		createAccountWindow.setSize(630, 548);
//		createAccountWindow.setLocationRelativeTo(this);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		createAccountWindow.setContentPane(contentPane);
//		createAccountWindow.setResizable(false);
//		contentPane.setLayout(null);
//		
//		JLabel labelCreateAccount = new JLabel("Create An Account");
//		labelCreateAccount.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
//		labelCreateAccount.setBounds(66, 17, 315, 51);
//		contentPane.add(labelCreateAccount);
//		
//		JLabel labelUsername = new JLabel("Name (First Last)");
//		labelUsername.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
//		labelUsername.setBounds(66, 96, 176, 20);
//		contentPane.add(labelUsername);
//		
//		newUsernameTxt = new JTextField();
//		newUsernameTxt.setToolTipText("Enter your last name.");
//		newUsernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		newUsernameTxt.setBounds(66, 120, 506, 26);
//		contentPane.add(newUsernameTxt);
//		newUsernameTxt.setColumns(10);
//		
//		JLabel lableNewPassword = new JLabel("Password");
//		lableNewPassword.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
//		lableNewPassword.setBounds(66, 176, 89, 20);
//		contentPane.add(lableNewPassword);
//		
//		newPasswordTxt = new JPasswordField();
//		newPasswordTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		newPasswordTxt.setBounds(66, 198, 506, 26);
//		contentPane.add(newPasswordTxt);
//		
//		createButton = new JButton("Create");
//		createButton.setForeground(new Color(240, 248, 255));
//		createButton.setBackground(new Color(70, 130, 180));
//		createButton.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
//		createButton.setBounds(66, 446, 506, 29);
//		contentPane.add(createButton);
//		Image doorIcon = new ImageIcon(this.getClass().getResource("/if_Login_in_85205.png")).getImage();
//		
//		JLabel schulichIcon = new JLabel("");
//		schulichIcon.setBounds(420, 7, 189, 93);
//		Image schulich = new ImageIcon(this.getClass().getResource("/01schulich.png")).getImage();
//		Image dimg = schulich.getScaledInstance(schulichIcon.getWidth(), schulichIcon.getHeight(), Image.SCALE_SMOOTH);
//		ImageIcon thisImg = new ImageIcon(dimg);
//		schulichIcon.setIcon(thisImg);
//		contentPane.add(schulichIcon);
//		
//		JLabel background = new JLabel("");
//		background.setBounds(0, 0, 624, 508);
//		Image backgroundIcon = new ImageIcon(this.getClass().getResource("/images.jpg")).getImage();
//		Image dimg2 = backgroundIcon.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
//		ImageIcon thisImg2 = new ImageIcon(dimg2);
//		
//		JLabel lblRetypePassword = new JLabel("Re-Type Password ");
//		lblRetypePassword.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
//		lblRetypePassword.setBounds(66, 254, 176, 20);
//		contentPane.add(lblRetypePassword);
//		
//		newPasswordCheckTxt = new JPasswordField();
//		newPasswordCheckTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		newPasswordCheckTxt.setBounds(66, 280, 506, 26);
//		contentPane.add(newPasswordCheckTxt);
//		
//		JLabel lblEmailAddress = new JLabel("Email Address");
//		lblEmailAddress.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
//		lblEmailAddress.setBounds(66, 337, 176, 20);
//		contentPane.add(lblEmailAddress);
//		
//		emailTxt = new JTextField();
//		emailTxt.setToolTipText("Enter your last name.");
//		emailTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		emailTxt.setColumns(10);
//		emailTxt.setBounds(66, 363, 506, 26);
//		contentPane.add(emailTxt);
//		
//		rdbtnStudent = new JRadioButton("Student");
//		rdbtnStudent.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
//		rdbtnStudent.setContentAreaFilled(false);
//		rdbtnStudent.setBounds(176, 401, 155, 29);
//		contentPane.add(rdbtnStudent);
//		
//		rdbtnProfessor = new JRadioButton("Professor");
//		rdbtnProfessor.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
//		rdbtnProfessor.setContentAreaFilled(false);
//		rdbtnProfessor.setBounds(322, 401, 155, 29);
//		contentPane.add(rdbtnProfessor);
//		
//		ButtonGroup group = new ButtonGroup();
//		group.add(rdbtnProfessor);
//		group.add(rdbtnStudent);
//		background.setIcon(thisImg2);
//		contentPane.add(background);
//		createAccountWindow.setVisible(true);
//	}
	
}
