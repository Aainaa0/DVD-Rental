package Function;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageCustomers extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public ManageCustomers(JFrame frame) {
		super(frame, "Manage Customers", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnAdd = new JButton("Add Customer");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddCustomer page = new AddCustomer(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
					
				}
			});
			btnAdd.setBounds(153, 23, 162, 40);
			contentPanel.add(btnAdd);
		}
		{
			JButton btnUpdate = new JButton("Update Customer");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UpdateCustomer page = new UpdateCustomer(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnUpdate.setBounds(153, 74, 162, 40);
			contentPanel.add(btnUpdate);
		}
		{
			JButton btnDelete = new JButton("Delete Customer");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DeleteCustomer page = new DeleteCustomer(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnDelete.setBounds(153, 125, 162, 40);
			contentPanel.add(btnDelete);
		}
		{
			JButton btnView = new JButton("View Customers");
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewCustomer page = new ViewCustomer(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnView.setBounds(153, 176, 162, 40);
			contentPanel.add(btnView);
		}
		{
			JButton btnReturn = new JButton("Return");
			btnReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnReturn.setBounds(10, 227, 89, 23);
			contentPanel.add(btnReturn);
		}
	}

}
