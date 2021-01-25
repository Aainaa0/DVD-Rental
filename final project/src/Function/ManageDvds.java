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

public class ManageDvds extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public ManageDvds(JFrame frame) {
		super(frame, "Manage Dvds", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnAddDvd = new JButton("Add Dvd");
			btnAddDvd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AddDvd page = new AddDvd(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnAddDvd.setBounds(159, 26, 132, 40);
			contentPanel.add(btnAddDvd);
		}
		{
			JButton btnUpdateDvd = new JButton("Update Dvd");
			btnUpdateDvd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UpdateDvd page = new UpdateDvd(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnUpdateDvd.setBounds(159, 77, 132, 40);
			contentPanel.add(btnUpdateDvd);
		}
		{
			JButton btnDeleteDvd = new JButton("Delete Dvd");
			btnDeleteDvd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DeleteDvd page = new DeleteDvd(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnDeleteDvd.setBounds(159, 128, 132, 40);
			contentPanel.add(btnDeleteDvd);
		}
		{
			JButton btnViewDvd = new JButton("View Dvds");
			btnViewDvd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ViewDvds page = new ViewDvds(frame);
					page.setLocationRelativeTo(frame);
					page.setVisible(true);
				}
			});
			btnViewDvd.setBounds(159, 179, 132, 40);
			contentPanel.add(btnViewDvd);
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
