import java.awt.BorderLayout;
import java.awt.Cursor;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	
	private static Statement statement;
	private static Connection conn;
	private JTextField newUsernameField;
	private JPasswordField newPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "root", "boydavid");
					statement = conn.createStatement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		JPanel createAccountPanel = new JPanel();
		createAccountPanel.setBackground(Color.ORANGE);
		
		setTitle("Login potangina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.ORANGE);
		loginPanel.setForeground(Color.BLACK);
		contentPane.add(loginPanel, "LoginPanel");
		loginPanel.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameField.setBounds(75, 208, 227, 33);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameLbl.setBounds(123, 170, 125, 27);
		loginPanel.add(usernameLbl);
		
		lblNewLabel = new JLabel("Password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(123, 252, 125, 27);
		loginPanel.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(75, 292, 227, 33);
		loginPanel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernameField.getText();
				char[] password = passwordField.getPassword();
				// di pa ni mo gana giatay ----------------------------------
				String str = "select * from user where username = '" + username + "'";
				System.out.println(str);
				try {
					ResultSet rSet = statement.executeQuery(str);
					CardLayout layout = (CardLayout) (contentPane.getLayout());
					layout.show(contentPane, "MainPanel");
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("no user found or password is wrong wtf");
				}
			}
		});
		loginButton.setBounds(116, 378, 146, 33);
		loginPanel.add(loginButton);
		
		JLabel createAccountLink = new JLabel("No account? Create one!");
		createAccountLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout layout = (CardLayout) (contentPane.getLayout());
				layout.show(contentPane, "CreateAccountPanel");
			}
		});
		createAccountLink.setHorizontalAlignment(SwingConstants.CENTER);
		createAccountLink.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createAccountLink.setBounds(75, 422, 227, 29);
		createAccountLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginPanel.add(createAccountLink);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.ORANGE);
		contentPane.add(mainPanel, "MainPanel");
		mainPanel.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome ! -----");
		welcomeLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		welcomeLabel.setBounds(100, 11, 167, 32);
		mainPanel.add(welcomeLabel);
		
		JLabel moneyLabel = new JLabel("Money:");
		moneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		moneyLabel.setBounds(29, 107, 153, 32);
		mainPanel.add(moneyLabel);
		
		JButton addMoneyButton = new JButton("Add money");
		addMoneyButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addMoneyButton.setBounds(100, 265, 160, 52);
		mainPanel.add(addMoneyButton);
		
		
		contentPane.add(createAccountPanel, "CreateAccountPanel");
		createAccountPanel.setLayout(null);
		
		newUsernameField = new JTextField();
		newUsernameField.setText("");
		newUsernameField.setBounds(70, 95, 236, 37);
		createAccountPanel.add(newUsernameField);
		newUsernameField.setColumns(10);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(70, 218, 236, 37);
		createAccountPanel.add(newPasswordField);
		newPasswordField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Enter username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(125, 57, 130, 27);
		createAccountPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(125, 179, 130, 27);
		createAccountPanel.add(lblNewLabel_2);
		
		JButton createNewButton = new JButton("Sign up");
		createNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sqlInsert = "insert into user values ('" + newUsernameField.getText() + "', '" + newPasswordField.getPassword() + "')";
				try {
					System.out.println(sqlInsert);
					int r = statement.executeUpdate(sqlInsert);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		createNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		createNewButton.setBounds(114, 324, 151, 43);
		createAccountPanel.add(createNewButton);
	}
}
