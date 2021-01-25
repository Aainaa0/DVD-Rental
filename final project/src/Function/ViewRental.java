package Function;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class ViewRental extends JDialog {
	private JTable table;

	
	public ViewRental(JFrame frame) {
		super(frame,"",true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			DefaultTableModel model = new DefaultTableModel();
			table = new JTable(model);
			
	        model.addColumn("Id");
	        model.addColumn("CustId");
	        model.addColumn("CustName");
	        model.addColumn("DvdId");
	        model.addColumn("DvdTitle");
	        model.addColumn("Completed?");
	        model.addColumn("Date completed");
	        model.addColumn("UserId");
	        model.addColumn("Username");
	        model.addColumn("Start date");
	        model.addColumn("End date");
			

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
	            PreparedStatement pstm = (PreparedStatement) con.prepareStatement("SELECT r.rentalId, c.customerId, c.customerName, d.dvdId, d.dvdName, r.completedStatus, r.dateCompleted, r.userid, u.username, r.dateStart, r.dateEnd FROM rental r, customer c, dvds d, user u WHERE c.customerId = r.customerId AND d.dvdId = r.dvdId AND r.userid=u.userid");
	            ResultSet Rs = (ResultSet) pstm.executeQuery();
	            while(Rs.next()){
	            	
	            	if(Rs.getString(6).contentEquals("1"))
	            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),"Yes",Rs.getString(7),Rs.getString(8),Rs.getString(9),Rs.getString(10),Rs.getString(11)});
	            	else if(Rs.getString(6).contentEquals("0"))
	            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),"No",Rs.getString(7),Rs.getString(8),Rs.getString(9),Rs.getString(10),Rs.getString(11)});
	            	      	
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
				
				scrollPane.setViewportView(table);
			}
		}
}
	
	


