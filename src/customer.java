import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class customer extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tf_contact, tf_password, tf_name,tf_address;;													
	private JButton btn_cancel, btn_save;
	private JLabel lbl_membership_value,lbl_ap_value,lbl_username_value;
	protected String username,address ,contact, name, membership, ap,password;
	protected List<customer> data;
	
	/**
	 * @wbp.parser.constructor
	 */
	
	public customer(String username) { 
		try {
			data= SQL.selectCustomer(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.username=username;
		
		ImageIcon bgd = new ImageIcon("image/customer_bgd.jpg");
		
		setTitle("Pizza Ordering System");
		setSize(451,491);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);	
		
		JPanel panel= new JPanel();	
		panel.setLayout(null);
		getContentPane().add(panel);	
			
		JLabel lbl_username = new JLabel("Username    :");
		lbl_username.setForeground(Color.WHITE);
		lbl_username.setBounds(20, 25, 195, 31);
		lbl_username.setFont(new Font("Agency FB", Font.BOLD, 30));
		panel.add(lbl_username);
			
		JLabel lbl_name = new JLabel("Name           :");
		lbl_name.setForeground(Color.WHITE);
		lbl_name.setBounds(20, 72, 195, 31);
		lbl_name.setFont(new Font("Agency FB", Font.BOLD, 30));
		panel.add(lbl_name);

		JLabel lbl_contact_no = new JLabel("Contact No   :");
		lbl_contact_no.setForeground(Color.WHITE);
		lbl_contact_no.setBounds(20, 120, 195, 31);
		lbl_contact_no.setFont(new Font("Agency FB", Font.BOLD, 30));
		panel.add(lbl_contact_no);
	
		JLabel lbl_address = new JLabel("Address       :");
		lbl_address.setForeground(Color.WHITE);
		lbl_address.setBounds(20, 170, 195, 31);
		lbl_address.setFont(new Font("Agency FB", Font.BOLD, 30));
		panel.add(lbl_address);
		
		tf_contact = new JTextField();
		tf_contact.setBounds(154, 120, 247, 31);
		tf_contact.setColumns(10);
		panel.add(tf_contact);
		
		tf_password = new JTextField();
		tf_password.setBounds(154, 349, 247, 31);
		tf_password.setColumns(10);
		panel.add(tf_password);
			
		JLabel lbl_password = new JLabel("Password    :");
		lbl_password.setForeground(Color.WHITE);
		lbl_password.setBounds(20, 349, 195, 31);
		lbl_password.setFont(new Font("Agency FB", Font.BOLD, 30));
		panel.add(lbl_password);
		
		lbl_username_value = new JLabel(username);
		lbl_username_value.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_username_value.setOpaque(true);
		lbl_username_value.setBackground(Color.LIGHT_GRAY);
		lbl_username_value.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_username_value.setForeground(Color.BLACK);
		lbl_username_value.setBounds(154, 28, 247, 25);
		panel.add(lbl_username_value);
			
		btn_save = new JButton("Save");
		btn_save.setBounds(221, 420, 88, 31);
		panel.add(btn_save);
		btn_save.addActionListener(this);
		
		btn_cancel = new JButton("Cancel");
		btn_cancel.setBounds(319, 420, 88, 31);
		panel.add(btn_cancel);
		btn_cancel.addActionListener(this);
		
		tf_name = new JTextField();
		tf_name.setColumns(10);
		tf_name.setBounds(154, 67, 247, 31);
		panel.add(tf_name);
		
		tf_address = new JTextField();
		tf_address.setColumns(10);
		tf_address.setBounds(154, 170, 247, 31);
		panel.add(tf_address);
		
		JLabel lbl_membership = new JLabel("Membership :");
		lbl_membership.setForeground(Color.WHITE);
		lbl_membership.setFont(new Font("Agency FB", Font.BOLD, 30));
		lbl_membership.setBounds(20, 227, 195, 31);
		panel.add(lbl_membership);
		
		JLabel lbl_ap = new JLabel("Accumulated_points :");
		lbl_ap.setForeground(Color.WHITE);
		lbl_ap.setFont(new Font("Agency FB", Font.BOLD, 30));
		lbl_ap.setBounds(20, 289, 195, 31);
		panel.add(lbl_ap);
		
		lbl_membership_value = new JLabel("<dynamic>");
		lbl_membership_value.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_membership_value.setOpaque(true);
		lbl_membership_value.setBackground(Color.LIGHT_GRAY);
		lbl_membership_value.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_membership_value.setForeground(Color.BLACK);
		lbl_membership_value.setBounds(154, 227, 247, 31);
		panel.add(lbl_membership_value);
		
		lbl_ap_value = new JLabel("<dynamic>");
		lbl_ap_value.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ap_value.setOpaque(true);
		lbl_ap_value.setBackground(Color.LIGHT_GRAY);
		lbl_ap_value.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_ap_value.setForeground(Color.BLACK);
		lbl_ap_value.setBounds(223, 289, 178, 31);
		panel.add(lbl_ap_value);
		
		tf_name.setText(data.get(0).name);
		tf_contact.setText(data.get(0).contact);
		tf_password.setText(data.get(0).password);
		tf_address.setText(data.get(0).address);
		lbl_membership_value.setText(data.get(0).membership);
		lbl_ap_value.setText(data.get(0).ap);
		lbl_username_value.setText(data.get(0).username);
		
		JLabel lbl_bgd = new JLabel();
		lbl_bgd.setBounds(0, 0, 445, 462);
		lbl_bgd.setIcon(bgd);
		panel.add(lbl_bgd);
	}
	
	public customer(int a) {};

	public void actionPerformed(ActionEvent evt) {
		
		if(evt.getSource()==btn_cancel) {dispose(); new home(username);}							
		
		if(evt.getSource()==btn_save) {									
			
	
			
			try {
				SQL.updateCustomer(username, tf_name.getText(), tf_address.getText(),tf_contact.getText(), tf_password.getText());	
				JOptionPane.showMessageDialog(null, "Updated successfully","Updated Message",JOptionPane.INFORMATION_MESSAGE);
				
						
			} 
			 catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	
}



	}

