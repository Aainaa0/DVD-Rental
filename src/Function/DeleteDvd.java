package Function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteDvd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String dvdName;

	public DeleteDvd(JFrame frame) {
		super(frame, "Delete Dvd", true);
		
		setBounds(100, 100, 684, 251);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				dvdName = (String) comboBox.getSelectedItem();
				dvdName = dvdName.substring(0, dvdName.indexOf(" ")); 
				System.out.println(dvdName);
			}
		});
		comboBox.setBounds(138, 51, 505, 20);
		contentPanel.add(comboBox);
		comboBox.addItem(null);	
		try {
		      Connection con = null;
		      Statement st = null;
		      ResultSet rs = null;
			
	          con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
	          st = (Statement) con.createStatement();
		    String s = "select dvdId, dvdName from dvds";
		    rs = (ResultSet) st.executeQuery(s);
		
			 while(rs.next())
		        {
				 comboBox.addItem(rs.getString(1)+" - "+rs.getString(2));
		        }
		}catch(Exception e){}
		
		JLabel label = new JLabel("Dvd Name:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(10, 57, 101, 14);
		contentPanel.add(label);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Dvd id: "+dvdName);
				
		         try{
		             theQuery("delete from dvds where dvdId='"+dvdName+"' ");
		         }
		         catch(Exception ex){}
			dispose();
			}
		});
		btnDelete.setActionCommand("OK");
		btnDelete.setBounds(232, 122, 126, 43);
		contentPanel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setActionCommand("Cancel");
		btnCancel.setBounds(368, 122, 115, 43);
		contentPanel.add(btnCancel);
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
