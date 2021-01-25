package Function;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AddCustomer extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextField textPhone;

	public AddCustomer(JFrame frame) {
		super(frame, "Add Customer", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textName = new JTextField();
			textName.setColumns(10);
			textName.setBounds(158, 36, 247, 20);
			contentPanel.add(textName);
		}
		{
			JLabel lblFullName = new JLabel("Full name:");
			lblFullName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblFullName.setBounds(30, 39, 101, 14);
			contentPanel.add(lblFullName);
		}
		{
			JLabel lblPhoneNumber = new JLabel("Phone number:");
			lblPhoneNumber.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPhoneNumber.setBounds(30, 79, 101, 14);
			contentPanel.add(lblPhoneNumber);
		}
		{
			textPhone = new JTextField();
			textPhone.setText("");
			textPhone.setColumns(10);
			textPhone.setBounds(158, 76, 247, 20);
			contentPanel.add(textPhone);
		}
		{
			JLabel lblAddress = new JLabel("Address:");
			lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAddress.setBounds(30, 120, 101, 14);
			contentPanel.add(lblAddress);
		}
		
		JTextArea textAddress = new JTextArea();
		textAddress.setLineWrap(true);
		textAddress.setBounds(158, 107, 247, 80);
		contentPanel.add(textAddress);
		{
			JButton btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			         try{
			             theQuery("insert into customer (customerName, phoneNumber , address) values('"+textName.getText()+"','"+textPhone.getText()+"','"+textAddress.getText()+"')");
			         }
			         catch(Exception ex){}
					dispose();
					
				}
			});
			btnSubmit.setActionCommand("OK");
			btnSubmit.setBounds(248, 227, 83, 23);
			contentPanel.add(btnSubmit);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setActionCommand("Cancel");
			btnCancel.setBounds(341, 227, 83, 23);
			contentPanel.add(btnCancel);
		}
	}
	
	  public void theQuery(String query){
	      Connection con = null;
	      Statement st = null;
	      try{
	          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
	          st = (Statement) con.createStatement();
	          st.executeUpdate(query);
	          JOptionPane.showMessageDialog(null,"Query Executed");
	      }catch(Exception ex){
	          JOptionPane.showMessageDialog(null,ex.getMessage());
	      }
	  }
}
