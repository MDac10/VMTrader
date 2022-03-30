/**
 * @author Megan Da Costa, Vito Wong, Xiaoyun Bonato, Sin Hong Ching Ingrid
 * @course 2212
 * @project Cryptocoin trader platform - final project deliverable **/
package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * LoginUI is designed to show the user a login page that they can type their username and password into, it will validate them based on a login text file with valid logins**/
public class LoginUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static LoginUI instanceLogin;
	
	private static JLabel user, pass;
	private static JTextField userInput;
	private static JPasswordField passInput;
	private static JButton logButton, resetButton;
	private static JCheckBox showPass;
	
	/**
	 * @return - an instance of the LoginUI page**/
	public static LoginUI getLogInstance() {
		if (instanceLogin == null)
			instanceLogin = new LoginUI();

		return instanceLogin;
	}
	
	/**
	 * Login page user interface; shows all design functions to create the login gui**/
	private LoginUI() {
		
		
		// Set window title
		super("Login page");
		
		JPanel panel = new JPanel();
		
		//Username label
		user = new JLabel("Username: ");
		user.setBounds(200, 200, 100, 40);
		panel.add(user);
		
		//Username text field positioned right beside its label
		userInput = new JTextField(20);
		userInput.setBounds(300, 200, 300, 40);
		panel.add(userInput);
		
		//Password label
		pass = new JLabel("Password: ");
		pass.setBounds(200, 250, 100, 40);
		panel.add(pass);
		
		//Password text field positioned right beside its label
		passInput = new JPasswordField(50);
		passInput.setBounds(300, 250, 300, 40);
		panel.add(passInput);
		
		//Login button
		logButton = new JButton("Submit");
		logButton.setBounds(300, 300, 80, 25);
		logButton.addActionListener(this); //of button is pushed its action listener is called
		panel.add(logButton);
		
		//Reset user input
		resetButton = new JButton("Reset");
		resetButton.setBounds(400, 300, 80, 25);
		resetButton.addActionListener(this); //if button is pushed its action listener is called
		panel.add(resetButton);
		
		//Show password
		showPass = new JCheckBox("Show Password");
		showPass.setBounds(300, 325, 130, 40);
		showPass.addActionListener(this); //if check box is checked its action listener is called
		panel.add(showPass);
		
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(900, 600));
		getContentPane().add(panel, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = LoginUI.getLogInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * @param String username - the username that the user has input into the respective text box
	 * @param String password - the password that the user has input into the respective text box
	 * @return a boolean indicating if the username and password are validated/match in the file containing valid logins**/
	public static boolean confirmPass(String username, String password) {
		String userCheck = "";
		String passCheck = "";
		boolean confirm = false;
		
		try {
			Scanner scan = new Scanner(new File("logins.txt")); //This is the file containing all valid logins
			scan.useDelimiter("[,\n]"); //Delimits by a ','
			
			while(scan.hasNext() && !confirm) {
				userCheck = scan.next(); //left side of the comma in file will contain all valid usernames
				passCheck = scan.next(); //right side of the comma in file will contain all corresponding valid passwords
				
				if(username.trim().equals(userCheck.trim()) && password.trim().equals(passCheck.trim())) {
					confirm = true;
				}
  			}
			
			scan.close();
			
		} catch(Exception e) {
			System.out.printf("An error occurred while trying to confirm username-password match...");
		}
		
		return confirm;
	}
	
	/**
	 * @param ActionEvent e - from the buttons/checkbox clicked in the login gui**/
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean valid;
		String userLogin = userInput.getText(); //takes the username that user typed into text box
		String passLogin = passInput.getText(); //takes the password that user typed into text box
		
		String command = e.getActionCommand();
		
		if ("Submit".equals(command)) {
			
			valid = confirmPass(userLogin, passLogin); //If submit is clicked, the password and username are validated
			
			if(valid) {
				
				JOptionPane.showMessageDialog(this, "Login successful...");
				
				//Opens MainUI on a successful login
				JFrame homeframe = MainUI.getInstance();
				homeframe.setSize(900, 600);
				homeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				homeframe.pack();
				homeframe.setVisible(true);
				
			} else {
				
				JOptionPane.showMessageDialog(this, "Username or Password is incorrect.");
				System.exit(0);
			}
			
			
		} else if ("Reset".equals(command)) { 
			//if reset is clicked then the text boxes will be changed to blank spaces
			userInput.setText("");
			passInput.setText("");
			
		} else if ("Show Password".equals(command)) {
			
			//if show password is checked off then the password will be shown, else it will be replaced by '*'
			if(showPass.isSelected()) {
				
				passInput.setEchoChar((char) 0);
				
			} else {
				
				passInput.setEchoChar('*');
				
			}
		}
		
	}

	
}
