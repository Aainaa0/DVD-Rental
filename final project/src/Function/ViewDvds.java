package Function;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ViewDvds extends JDialog {

	private final JPanel contentPanel = new JPanel();
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    
	public ViewDvds(JFrame frame) {
		super(frame, "View Dvds", true);
		setBounds(100, 100, 756, 451);
		getContentPane().setLayout(null);
		contentPanel.setBounds(5, 205, 1, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Dvd Id");
        model.addColumn("Dvd Title");
        model.addColumn("Rental Rate");
        model.addColumn("Status");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
            PreparedStatement pstm = (PreparedStatement) con.prepareStatement("SELECT * FROM dvds");
            ResultSet Rs = (ResultSet) pstm.executeQuery();
            while(Rs.next()){
            	
            	System.out.println(Rs.getString(4));
            	if(Rs.getString(4).contentEquals("1"))
            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),"available"});
            	else
            		model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),"unavailable"});
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
