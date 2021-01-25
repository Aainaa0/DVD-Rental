package Function;
import java.awt.BorderLayout;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.xml.validation.Validator;
import javax.swing.event.ChangeEvent;

public class AddDvd extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textDvdName;
	private JTextField textRentalRate;
	private int available = 0;

	public AddDvd(JFrame frame) {
		super(frame, "Add Dvd", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDvdName = new JLabel("Dvd Name:");
		lblDvdName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDvdName.setBounds(29, 44, 101, 14);
		contentPanel.add(lblDvdName);
		
		JLabel lblRentalRate = new JLabel("Rental rate (RM):");
		lblRentalRate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRentalRate.setBounds(29, 84, 101, 14);
		contentPanel.add(lblRentalRate);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatus.setBounds(29, 125, 101, 14);
		contentPanel.add(lblStatus);
		
		textDvdName = new JTextField();
		textDvdName.setBounds(157, 41, 247, 20);
		contentPanel.add(textDvdName);
		textDvdName.setColumns(10);
		
		textRentalRate = new JTextField();
		textRentalRate.setText("");
		textRentalRate.setBounds(157, 81, 75, 20);
		contentPanel.add(textRentalRate);
		textRentalRate.setColumns(10);
		
		JCheckBox chckbxAvailable = new JCheckBox("Available",true);
		chckbxAvailable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
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
		chckbxAvailable.setBounds(157, 121, 97, 23);
		contentPanel.add(chckbxAvailable);
		{
			JButton btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(textDvdName.getText().contentEquals("")||textRentalRate.getText().contentEquals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill up all form");
					}
					else
					{
				         try{
				             theQuery("insert into dvds (dvdName, rentalRate, status) values('"+textDvdName.getText()+"','"+textRentalRate.getText()+"','"+available+"')");
				         }
				         catch(Exception ex){}
						dispose();
					}

				}

			});
			
			btnSubmit.setBounds(248, 227, 83, 23);
			contentPanel.add(btnSubmit);
			btnSubmit.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSubmit);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setBounds(341, 227, 83, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
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
