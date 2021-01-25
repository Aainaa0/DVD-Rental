package Function;

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

public class ViewCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

	public ViewCustomer(JFrame frame) {
		super(frame, "View Customers", true);
		setBounds(100, 100, 756, 451);
		getContentPane().setLayout(null);
		contentPanel.setBounds(5, 205, 1, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		jtbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		cnt.setLayout(new FlowLayout(FlowLayout.LEFT));
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Phone");
        model.addColumn("Address");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","");
            PreparedStatement pstm = (PreparedStatement) con.prepareStatement("SELECT * FROM customer");
            ResultSet Rs = (ResultSet) pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4)});
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
