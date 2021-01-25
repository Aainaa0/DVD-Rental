package Function;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class ViewRental extends JDialog {

	private final JPanel contentPanel = new JPanel();
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

	public ViewRental(JFrame frame) {
		super(frame, "View Customers", true);
		setBounds(100, 100, 756, 451);
		getContentPane().setLayout(null);
		contentPanel.setBounds(5, 205, 1, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Id");
        model.addColumn("CustId");
        model.addColumn("CustName");
        model.addColumn("DvdId");
        model.addColumn("DvdTitle");
        model.addColumn("Paid?");
        model.addColumn("Completed?");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
            PreparedStatement pstm = (PreparedStatement) con.prepareStatement("SELECT r.rentalId, c.customerId, c.customerName, d.dvdId, d.dvdName, r.paidStatus, r.completedStatus  FROM rental r, customer c, dvds d WHERE c.customerId = r.customerId AND d.dvdId = r.dvdId");
            ResultSet Rs = (ResultSet) pstm.executeQuery();
            while(Rs.next()){
            	if(Rs.getString(6).contentEquals("1")&&Rs.getString(7).contentEquals("1"))
            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),"true","true"});
            	else if(Rs.getString(6).contentEquals("1")&&Rs.getString(7).contentEquals("0"))
            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),"true","false"});
            	else if(Rs.getString(6).contentEquals("0")&&Rs.getString(7).contentEquals("1"))
            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),"false","true"});
            	else if(Rs.getString(6).contentEquals("0")&&Rs.getString(7).contentEquals("0"))
            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),"false","false"});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JScrollPane pg = new JScrollPane(jtbl);
        pg.setBounds(11, 5, 452, 402);
        cnt.add(pg);
        this.pack();
		
		
	}
	
	

}
