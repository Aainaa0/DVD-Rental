package Function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UpdateCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String custName;
	private JTextField textName;
	private JTextField textPhone;

	public UpdateCustomer(JFrame frame) {
		super(frame, "Update Customer", true);
		
		setBounds(100, 100, 726, 356);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				custName = (String) comboBox.getSelectedItem();
				custName = custName.substring(0, custName.indexOf(" ")); 
				System.out.println(custName);
			}
		});
	
		comboBox.setBounds(165, 30, 505, 20);
		contentPanel.add(comboBox);
		comboBox.addItem(null);	
		try {
		      Connection con = null;
		      Statement st = null;
		      ResultSet rs = null;
			
	          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
	          st = (Statement) con.createStatement();
		    String s = "select customerId, customerName, phoneNumber from customer";
		    rs = (ResultSet) st.executeQuery(s);
		
			 while(rs.next())
		        {
				 comboBox.addItem(rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getString(3));
		        }
		}catch(Exception e){}
		
		
		
		JLabel lblCustomerid = new JLabel("CustomerId:");
		lblCustomerid.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCustomerid.setBounds(37, 33, 101, 14);
		contentPanel.add(lblCustomerid);
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCustomerName.setBounds(37, 73, 101, 14);
		contentPanel.add(lblCustomerName);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPhone.setBounds(37, 116, 101, 14);
		contentPanel.add(lblPhone);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setActionCommand("Cancel");
		button_1.setBounds(406, 263, 115, 43);
		contentPanel.add(button_1);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAddress.setBounds(37, 157, 101, 14);
		contentPanel.add(lblAddress);
		
		textName = new JTextField();
		textName.setBounds(165, 70, 505, 20);
		contentPanel.add(textName);
		textName.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setBounds(165, 113, 505, 20);
		contentPanel.add(textPhone);
		textPhone.setColumns(10);
		
		JTextArea textAdd = new JTextArea();
		textAdd.setBounds(165, 152, 505, 73);
		contentPanel.add(textAdd);
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
		             theQuery("UPDATE customer SET customerName = '"+textName.getText()+"', phoneNumber ='"+textPhone.getText()+"', address ='"+textAdd.getText()+"' WHERE customerId = '"+custName+"' ");
		         }
		         catch(Exception ex){}
			dispose();
				
			}
		});
		button.setActionCommand("OK");
		button.setBounds(270, 263, 126, 43);
		contentPanel.add(button);
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
