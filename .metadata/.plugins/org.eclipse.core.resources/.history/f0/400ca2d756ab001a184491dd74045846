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

public class ManageRentals extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public ManageRentals(JFrame frame, String userId) {
		super(frame, "Add Customer", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnAdd = new JButton("Create Rental");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CreateRental page = new CreateRental(frame, userId);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnAdd.setBounds(160, 47, 132, 40);
			contentPanel.add(btnAdd);
		}
		{
			JButton btnUpdate = new JButton("Update Rental");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UpdateRental page = new UpdateRental(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnUpdate.setBounds(160, 98, 132, 40);
			contentPanel.add(btnUpdate);
		}
		{
			JButton btnView = new JButton("View Rentals");
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewRental page = new ViewRental(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnView.setBounds(160, 149, 132, 40);
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
