package Function;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class test {

	private JFrame frame;
	private JTextField textUsername;
	private JPasswordField textPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
						Connection con = null;
						try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
						if (!con.isClosed())
							System.out.println("Successfully connected to MySQL server...");
						} 
						catch(Exception e) 
						{
							System.err.println("Exception: " + e.getMessage());
						} 
						finally {
						try 
						{
							if (con != null)
							con.close();
						} 
						catch(SQLException e) {}
						}
	
					test window = new test();
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public test() {
		initialize();
	}

	private void initialize() {
		frame =  new JFrame("Dvds Rental Management System");
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDvdsRentalManagement = new JLabel("Dvds Rental Management System");
		lblDvdsRentalManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDvdsRentalManagement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDvdsRentalManagement.setBounds(106, 11, 232, 52);
		frame.getContentPane().add(lblDvdsRentalManagement);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(106, 74, 232, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(106, 130, 232, 14);
		frame.getContentPane().add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(106, 99, 232, 20);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(106, 155, 232, 20);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		

		
		
		JButton btnNewButton = new JButton("Log in");
		
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(30, 144, 255));
		try {
//			JRootPane rootPane = SwingUtilities.getRootPane(btnNewButton); 
//			rootPane.setDefaultButton(btnNewButton);
			frame.getRootPane().setDefaultButton(btnNewButton);
		}catch(Exception e){
			
		}
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				      Connection con = null;
				      Statement st = null;
				      ResultSet rs = null;
				      
			          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
			          st = (Statement) con.createStatement();
				    String s = "SELECT * FROM user WHERE username = '"+textUsername.getText()+"' AND password = '"+textPassword.getText()+"' ";
				    rs = (ResultSet) st.executeQuery(s);
				
					 if(rs.next())
				        {
							JOptionPane.showMessageDialog(null,"Welcome, admin.");
						 	frame.dispose();
							homepage home = new homepage(frame);
							home.setLocationRelativeTo(null);
							home.setVisible(true);
				        }
					 else
					 {
							if(textUsername.getText().contentEquals("")||textPassword.getText().contentEquals(""))
							{
								JOptionPane.showMessageDialog(null, "Please fill up all form");
							}
							else
								JOptionPane.showMessageDialog(null, "Incorrect username and password combination.");
					 }

					

					
						
					
					 
				}catch(Exception e){}
				
				

			}
		});
		btnNewButton.setBounds(106, 206, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textPassword.setText("");
				textUsername.setText("");
				textUsername.grabFocus();
			}
		});
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(255, 165, 0));
		btnClear.setBounds(249, 206, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnHelp = new JButton("");
		try {	
			Image img = new ImageIcon(this.getClass().getResource("C:\\Users\\anist\\eclipse-workspace\\frame test\\img\\help.jpg")).getImage().getScaledInstance(28, 28,Image.SCALE_DEFAULT);
			btnHelp.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			System.out.println("image icon cannot be loaded.");
		}
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Developed by:\nAainaa Nabilah binti Rohaizad\nElle Aliz binti Aminuddin\nGion Min Ming\nNur Alis Sophia binti Suhaimi");
			}
		});

		btnHelp.setBackground(new Color(255, 255, 255));

		btnHelp.setBounds(393, 11, 31, 31);
		frame.getContentPane().add(btnHelp);
		
	}
	  public void theQuery(String query){
	      Connection con = null;
	      Statement st = null;
	      try{
	          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
	          st = (Statement) con.createStatement();
	          st.executeUpdate(query);
	          JOptionPane.showMessageDialog(null,"Query Executed","Alert",JOptionPane.WARNING_MESSAGE);
	      }catch(Exception ex){
	          JOptionPane.showMessageDialog(null,ex.getMessage());
	      }
	  }
}
