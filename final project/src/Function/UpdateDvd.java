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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UpdateDvd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textTitle;
	private JTextField textRate;
	private String dvdName;
	private int available = 0;

	public UpdateDvd(JFrame frame) {
		super(frame, "Update Dvds", true);
		getContentPane().setLayout(null);

		setBounds(100, 100, 725, 301);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JComboBox comboBox = new JComboBox();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					dvdName = (String) comboBox.getSelectedItem();
					dvdName = dvdName.substring(0, dvdName.indexOf(" ")); 
					System.out.println(dvdName);
				}
			});
			comboBox.setBounds(138, 11, 505, 20);
			contentPanel.add(comboBox);
			comboBox.addItem(null);	
			try {
			      Connection con = null;
			      Statement st = null;
			      ResultSet rs = null;
				
		          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
		          st = (Statement) con.createStatement();
			    String s = "select dvdId, dvdName, rentalRate from dvds";
			    rs = (ResultSet) st.executeQuery(s);
			
				 while(rs.next())
			        {
					 comboBox.addItem(rs.getString(1)+" - "+rs.getString(2)+" (Rm "+rs.getString(3)+")");
			        }
			}catch(Exception e){}
		}
		{
			JLabel lblDvdid = new JLabel("Dvd Id:");
			lblDvdid.setHorizontalAlignment(SwingConstants.TRAILING);
			lblDvdid.setBounds(10, 17, 101, 14);
			contentPanel.add(lblDvdid);
		}
		{
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
			         try{
			             theQuery("UPDATE dvds SET dvdName = '"+textTitle.getText()+"', rentalRate ='"+textRate.getText()+"', status = '"+available+"' WHERE dvdId = '"+dvdName+"'; ");
			         }
			         catch(Exception ex){}
				dispose();
				}
			});
			btnUpdate.setActionCommand("OK");
			btnUpdate.setBounds(235, 208, 126, 43);
			contentPanel.add(btnUpdate);
		}
		{
			JButton button = new JButton("Cancel");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setActionCommand("Cancel");
			button.setBounds(371, 208, 115, 43);
			contentPanel.add(button);
		}
		{
			JLabel lblDvdTitle = new JLabel("Dvd Title:");
			lblDvdTitle.setHorizontalAlignment(SwingConstants.TRAILING);
			lblDvdTitle.setBounds(10, 53, 101, 14);
			contentPanel.add(lblDvdTitle);
		}
		
		textTitle = new JTextField();
		textTitle.setBounds(138, 50, 505, 20);
		contentPanel.add(textTitle);
		textTitle.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatus.setBounds(10, 132, 101, 14);
		contentPanel.add(lblStatus);
		
		JCheckBox chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxAvailable.isSelected() == true)
				{
					available = 1;
				}
				else if(chckbxAvailable.isSelected() == false)
				{
					available = 0;
				}
			}
		});
		chckbxAvailable.setBounds(138, 128, 97, 23);
		contentPanel.add(chckbxAvailable);
		{
			textRate = new JTextField();
			textRate.setColumns(10);
			textRate.setBounds(138, 91, 126, 20);
			contentPanel.add(textRate);
		}
		{
			JLabel lblRentalRate = new JLabel("Rental Rate:");
			lblRentalRate.setHorizontalAlignment(SwingConstants.TRAILING);
			lblRentalRate.setBounds(10, 94, 101, 14);
			contentPanel.add(lblRentalRate);
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

