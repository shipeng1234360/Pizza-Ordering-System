import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class login extends JFrame implements ActionListener {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JButton btn_login;
private JTextField tf_username;
private JPasswordField pf_password;
private boolean status=false;

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				login login = new login();													
				login.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}


public login() {																		
	
	ImageIcon login_icon  = new ImageIcon("image/login_icon.png");
	ImageIcon bgd = new ImageIcon("image/login_bgd.jpg");
	
	setSize( 395, 412);
	setTitle("Pizza Ordering System");
	setLocationRelativeTo(null);
	setVisible(true);
	
	JPanel panel = new JPanel();
	panel.setBounds(53, 138, 279, 222);	
	panel.setLayout(null);
	add(panel);
	
	JLabel username = new JLabel("Username");
	username.setBounds(21, 11, 117, 34);
	panel.add(username);
	
	JLabel password = new JLabel("Password");
	password.setBounds(21, 79, 76, 26);
	panel.add(password);
	
	tf_username = new JTextField();
	tf_username.setBounds(21, 42, 242, 26);
	tf_username.setColumns(10);
	panel.add(tf_username);

	pf_password = new JPasswordField();
	pf_password.setBounds(21, 106, 242, 26);
	panel.add(pf_password);
	
	btn_login = new JButton("login");
	btn_login.setBounds(66, 162, 136, 34);
	btn_login.addActionListener(this);
	panel.add(btn_login);
	
	JLabel lbl_logo_icon = new JLabel();
	lbl_logo_icon.setIcon(login_icon);
	lbl_logo_icon.setBounds(130, 11, 120, 120);
	getContentPane().add(lbl_logo_icon);

	JLabel lbl_bgd = new JLabel();
	lbl_bgd.setIcon(bgd);
	lbl_bgd.setBounds(0, 0, 379, 373);
	getContentPane().add(lbl_bgd);


	}

public void actionPerformed(ActionEvent evt) {
	
		if(evt.getSource()==btn_login) {														

			String password = new String(pf_password.getPassword());
			
			try {
				status=SQL.verifyUser(tf_username.getText(),password);								
			} catch (SQLException e) {	
				e.printStackTrace();
			}
			
		if (status==true) 
		{	new home(tf_username.getText());																					
			dispose();
			
		}	
		
		else {
			pf_password.setText(null);															
			tf_username.setText(null);
			}
		}		




}
}



