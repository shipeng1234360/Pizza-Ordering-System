import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

public class home extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_user,btn_logout,btn_order,btn_record;
	private String username;
	
	public home(String username){															
	
		ImageIcon img = new ImageIcon("image/bgd.png");
		this.username=username;
		setVisible(true);
		setSize(627, 454);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Pizza Ordering System");													
		setBackground(new Color(32, 178, 170));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.light"));
		panel.setLayout(null);
		getContentPane().add(panel);
		
		btn_order = new JButton();
		btn_order.setText("Order");
		btn_order.setBounds(242, 193, 118, 49);
		btn_order.addActionListener(this);
		panel.add(btn_order);
		
		btn_user = new JButton();
		btn_user.setText("User Details");
		btn_user.setBounds(242, 111, 118, 49);
		btn_user.addActionListener(this);
		panel.add(btn_user);
		
		btn_logout = new JButton();
		btn_logout.setText("Log out");
		btn_logout.setBounds(242, 351, 118, 49);
		btn_logout.addActionListener(this);
		panel.add(btn_logout);
		
		btn_record = new JButton();
		btn_record.setText("Record");
		btn_record.setBounds(242, 272, 118, 49);
		btn_record.addActionListener(this);
		panel.add(btn_record);
		
		JLabel lbl_bgd = new JLabel();
		lbl_bgd.setIcon(img);
		lbl_bgd.setBounds(0, 0, 621,425);
		panel.add(lbl_bgd);
				
	}

	public void actionPerformed(ActionEvent evt) {
		
		 if(evt.getSource()==btn_user) { new customer(username); dispose(); }			
		 if(evt.getSource()==btn_order) { new order(username); dispose(); }	
		 if(evt.getSource()==btn_record) { new record(username); dispose(); }	
		 if(evt.getSource()==btn_logout) { new login(); dispose(); }
	}
}
