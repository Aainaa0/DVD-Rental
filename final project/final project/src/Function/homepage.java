package Function;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class homepage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public homepage(JFrame frame) {
		
		super("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManageDvds = new JButton("Manage Dvds");
		btnManageDvds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageDvds page = new ManageDvds(frame);
				page.setLocationRelativeTo(frame);
				page.setVisible(true);
			}
			
		});
		btnManageDvds.setBounds(108, 22, 205, 43);
		contentPane.add(btnManageDvds);
		
		JButton btnManageCustomer = new JButton("Manage Customers");
		btnManageCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageCustomers page = new ManageCustomers(frame);
				page.setLocationRelativeTo(frame);
				page.setVisible(true);
				
			}
		});
		btnManageCustomer.setBounds(108, 76, 205, 43);
		contentPane.add(btnManageCustomer);
		
		JButton btnManageRental = new JButton("Manage Rentals");
		btnManageRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageRentals page = new ManageRentals(frame);
				page.setLocationRelativeTo(frame);
				page.setVisible(true);
			}
		});
		btnManageRental.setBounds(108, 130, 205, 43);
		contentPane.add(btnManageRental);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(108, 184, 205, 43);
		contentPane.add(btnExit);
	}
}
