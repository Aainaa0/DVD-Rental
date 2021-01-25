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
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.DriverManager;
import java.awt.event.ItemEvent;

public class UpdateRental extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String ID = null;
	private int paid = 0;
	private int completed = 0;

	public UpdateRental(JFrame frame) {
		super(frame, "Add Customer", true);
		setBounds(100, 100, 829, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JComboBox comboBox = new JComboBox();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					ID = (String) comboBox.getSelectedItem();
					ID = ID.substring(0, ID.indexOf(" ")); 
					System.out.println(ID);
				}
			});
			comboBox.setBounds(207, 29, 473, 20);
			contentPanel.add(comboBox);
			comboBox.addItem(null);	
			try {
			      Connection con = null;
			      Statement st = null;
			      ResultSet rs = null;
				
		          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
		          st = (Statement) con.createStatement();
			    String s = "SELECT r.rentalId, c.customerId, c.customerName, d.dvdId, d.dvdName FROM rental r, customer c, dvds d WHERE c.customerId = r.customerId AND d.dvdId = r.dvdId";
			    rs = (ResultSet) st.executeQuery(s);
			
				 while(rs.next())
			        {
					 comboBox.addItem(rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getString(3)+" / "+rs.getString(4)+" - "+rs.getString(5));
			        }
			}catch(Exception e){}
		}
		{
			JLabel lblNewLabel = new JLabel("Rental Id:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel.setBounds(31, 32, 130, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JCheckBox chckbxPaid = new JCheckBox("Paid");
			chckbxPaid.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chckbxPaid.isSelected() == true)
					{
						paid = 1;
					}
					else if(chckbxPaid.isSelected() == false)
					{
						paid = 0;
					}
				}
			});
			chckbxPaid.setBounds(207, 72, 97, 23);
			contentPanel.add(chckbxPaid);
		}
		{
			JCheckBox chckbxCompleted = new JCheckBox("Completed");
			chckbxCompleted.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(chckbxCompleted.isSelected() == true)
					{
						completed = 1;
					}
					else if(chckbxCompleted.isSelected() == false)
					{
						completed = 0;
					}
				}
			});
			chckbxCompleted.setBounds(207, 109, 97, 23);
			contentPanel.add(chckbxCompleted);
		}
		{
			JLabel lblStatus = new JLabel("Status:");
			lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
			lblStatus.setBounds(31, 76, 130, 14);
			contentPanel.add(lblStatus);
		}
		{
			JButton button = new JButton("Update");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
			             theQuery("UPDATE rental SET paidStatus = '"+paid+"', completedStatus = '"+completed+"' WHERE rentalId = '"+ID+"'; ");
			         }
			         catch(Exception ex){}
				dispose();
				}
			});
			button.setActionCommand("OK");
			button.setBounds(207, 185, 126, 43);
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
			button.setBounds(343, 185, 115, 43);
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
