package Function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String custName;

	public DeleteCustomer(JFrame frame) {
		super(frame, "Delete Customer", true);
		setBounds(100, 100, 684, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JComboBox comboBox = new JComboBox();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					custName = (String) comboBox.getSelectedItem();
					custName = custName.substring(0, custName.indexOf(" ")); 
					System.out.println(custName);
				}
			});
			comboBox.setBounds(138, 55, 505, 20);
			contentPanel.add(comboBox);
			comboBox.addItem(null);	
			try {
			      Connection con = null;
			      Statement st = null;
			      ResultSet rs = null;
				
		          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
		          st = (Statement) con.createStatement();
			    String s = "select customerId, customerName from customer";
			    rs = (ResultSet) st.executeQuery(s);
			
				 while(rs.next())
			        {
					 comboBox.addItem(rs.getString(1)+" - "+rs.getString(2));
			        }
			}catch(Exception e){}
		}
		{
			JLabel lblCustomerName = new JLabel("Customer Name:");
			lblCustomerName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblCustomerName.setBounds(10, 61, 101, 14);
			contentPanel.add(lblCustomerName);
		}
		{
			JButton button = new JButton("Delete");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Customer id: "+custName);
					
			         try{
			             theQuery("delete from customer where customerId='"+custName+"' ");
			         }
			         catch(Exception ex){}
				dispose();
				}
			});
			button.setActionCommand("OK");
			button.setBounds(232, 126, 126, 43);
			contentPanel.add(button);
		}
		{
			JButton button = new JButton("Cancel");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setActionCommand("Cancel");
			button.setBounds(368, 126, 115, 43);
			contentPanel.add(button);
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
