import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class order extends JFrame implements ActionListener , ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonGroup bg = new ButtonGroup();
	private JButton btn_add, btn_remove, btn_checkout ,btn_back;
	private JTable table_cart;
	private JScrollPane cart;	
	private JLabel lbl_total_value, lbl_balance_value, lbl_ordersID_value;
	private JTextPane tf_payment;
	private JSpinner spinner;
	private DefaultTableModel model_cart;																									
	private JRadioButton btn_small, btn_medium, btn_large ;
	private JCheckBox cb_pepperoni, cb_sausage, cb_mushroom;	
	protected String username,address, size,topping;
	protected  int quantity, row , order_id ;
	protected  double  total, sum , balance , payment, price_per_unit, amount, topping_fees;
	
	pizza p = new pizza();
	/**
	 * @wbp.parser.constructor
	 */

	public order(String username) {																	
		ImageIcon bgd = new ImageIcon("image/order_bgd.jpg");
		this.username=username;
		String column[]= {"Item","Price(RM)","Quantity","Total (RM)"};			
		Object[][] row ={};													
		
		size="Small";
		topping="";
		amount=8;
		
		setResizable(false);
		setLocation(300, 120);
		setTitle("Pizza Ordering System");
		setVisible(true);
		setSize(831, 528);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	
		cart = new JScrollPane();
		cart.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		cart.setBounds(305, 22, 505, 233);
		getContentPane().add(cart);
			
		model_cart=new DefaultTableModel(row,column);
		table_cart = new JTable(model_cart);
		cart.setViewportView(table_cart);
		
		btn_back = new JButton("Back");
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_back.setBounds(20, 431, 103, 40);
		btn_back.addActionListener(this);
		getContentPane().add(btn_back);
		
		btn_add = new JButton("Add");
		btn_add.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_add.setBounds(318, 329, 109, 40);
		btn_add.addActionListener(this);
		getContentPane().add(btn_add);
		
		btn_remove = new JButton("Remove");
		btn_remove.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_remove.setBounds(318, 380, 109, 40);
		btn_remove.addActionListener(this);
		getContentPane().add(btn_remove);
		
		btn_checkout = new JButton("Checkout");
		btn_checkout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_checkout.setBounds(318, 431, 109, 40);
		btn_checkout.addActionListener(this);
		getContentPane().add(btn_checkout);
		
		lbl_total_value = new JLabel();
		lbl_total_value.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_total_value.setText("0");
		lbl_total_value.setBounds(673, 276, 152, 30);
		getContentPane().add(lbl_total_value);
		
		tf_payment = new JTextPane();
		tf_payment.setBounds(668, 335, 56, 30);
		getContentPane().add(tf_payment);
		
		lbl_balance_value = new JLabel();
		lbl_balance_value.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_balance_value.setText("0");
		lbl_balance_value.setBounds(673, 402, 211, 30);
		getContentPane().add(lbl_balance_value);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		spinner.setBounds(385, 282, 29, 20);
		getContentPane().add(spinner);
		
		JLabel lbl_total = new JLabel("Total       (RM)     :");
		lbl_total.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_total.setBounds(535, 273, 128, 37);
		getContentPane().add(lbl_total);
		
		JLabel lbl_payment = new JLabel("Payment (RM)    :");
		lbl_payment.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_payment.setBounds(535, 331, 121, 37);
		getContentPane().add(lbl_payment);
		
		JLabel lbl_balance = new JLabel("Balance   (RM)    :");
		lbl_balance.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_balance.setBounds(535, 399, 121, 37);
		getContentPane().add(lbl_balance);
			
		JLabel lbl_quantity = new JLabel("Quantity");
		lbl_quantity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_quantity.setBounds(325, 273, 62, 37);
		getContentPane().add(lbl_quantity);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(315, 263, 495, 20);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(294, 11, 42, 460);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(535, 397, 275, 61);
		getContentPane().add(separator_2);
		
		JLabel lbl_orderID = new JLabel("order ID :");
		lbl_orderID.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_orderID.setBounds(308, 5, 62, 14);
		getContentPane().add(lbl_orderID);
		
	
		try {order_id=SQL.getOrderID()+1;} catch (SQLException e) {e.printStackTrace();}
			
		lbl_ordersID_value = new JLabel(Integer.toString(order_id));
		lbl_ordersID_value.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_ordersID_value.setBounds(370, 5, 103, 14);
		getContentPane().add(lbl_ordersID_value);
		
		JLabel lbl_size = new JLabel("Pizza Size");
		lbl_size.setForeground(new Color(255, 127, 80));
		lbl_size.setFont(new Font("Script MT Bold", Font.PLAIN, 25));
		lbl_size.setBounds(20, 22, 133, 30);
		getContentPane().add(lbl_size);
		
		JLabel lbl_topping = new JLabel("Toppings  ");
		lbl_topping.setForeground(new Color(255, 127, 80));
		lbl_topping.setFont(new Font("Script MT Bold", Font.PLAIN, 25));
		lbl_topping.setBounds(20, 208, 275, 30);
		getContentPane().add(lbl_topping);
		
		cb_pepperoni = new JCheckBox("Pepperoni      - RM2");
		cb_pepperoni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cb_pepperoni.setBounds(26, 254, 237, 20);
		cb_pepperoni.addItemListener(this);
		getContentPane().add(cb_pepperoni);
		
		cb_sausage = new JCheckBox("Sausage        - RM2");
		cb_sausage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cb_sausage.setBounds(26, 294, 237, 20);
		cb_sausage.addItemListener(this);
		getContentPane().add(cb_sausage);
		
		cb_mushroom = new JCheckBox("Mushroom     - RM2");
		cb_mushroom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cb_mushroom.setBounds(26, 334, 237, 20);
		cb_mushroom.addItemListener(this);
		getContentPane().add(cb_mushroom);
		
		btn_small = new JRadioButton("Small             - RM8");
		btn_small.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_small.setSelected(true);
		btn_small.setBounds(20, 72, 243, 20);
		btn_small.addActionListener(this);
		getContentPane().add(btn_small);
		
		btn_medium = new JRadioButton("Medium          - RM10");
		btn_medium.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_medium.setBounds(20, 106, 243, 20);
		btn_medium.addActionListener(this);
		getContentPane().add(btn_medium);
		
		btn_large = new JRadioButton("Large             - RM12");
		btn_large.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_large.setBounds(20, 143, 243, 20);
		btn_large.addActionListener(this);
		getContentPane().add(btn_large);
		
		bg.add(btn_small);
		bg.add(btn_medium);
		bg.add(btn_large);
		
		JLabel lbl_bgd = new JLabel();
		lbl_bgd.setIcon(bgd);
		lbl_bgd.setBounds(0, 0, 825, 499);
		getContentPane().add(lbl_bgd);
		
		
	
	}
	
	public order(int a) {}
	
	public void actionPerformed(ActionEvent evt) {	
		
		if(evt.getSource()==btn_small) {
			p.setPrice(8);
			amount=p.getPrice();
			p.setSize("Small");
			size=p.getSize();
		}
		
		else if(evt.getSource()==btn_medium) {
			p.setPrice(10);
			amount=p.getPrice();
			p.setSize("Medium");
			size=p.getSize();
		}
		
		else if(evt.getSource()==btn_large) {
			p.setPrice(12);
			amount=p.getPrice();
			p.setSize("Large");
			size=p.getSize();
		}

	if(evt.getSource()==btn_back) { dispose();	new home(username); }				
		 
	if(evt.getSource()==btn_add) {												
		
		quantity=(Integer.parseInt(spinner.getValue().toString()));
		total=(quantity * (amount+topping_fees));
			model_cart.addRow(new Object[] 										
				{ 
					size+topping,
					amount+topping_fees,
					quantity,
					total
				});
			
		sum+=total;
		lbl_total_value.setText(Double.toString(sum));

		}
	
		
	else if(evt.getSource()==btn_remove) {									
			
			row=table_cart.getSelectedRow();
			
			sum= sum - (Double)table_cart.getValueAt(row, 3);				
			model_cart.removeRow(table_cart.getSelectedRow());
			
			lbl_total_value.setText(Double.toString(sum));					
		}
			

	else if(evt.getSource()==btn_checkout) {								
			
			payment =Double.parseDouble (tf_payment.getText().toString());		
			balance= payment - sum ;											
			
				if(balance>=0) {												
					lbl_balance_value.setText(Double.toString(balance));	
					record();													
					}
				else  {															
					JOptionPane.showMessageDialog(null, "Amount of payment is insufficiant","Payment failed",JOptionPane.ERROR_MESSAGE);
					balance=0.0;											
				}		
		}

	

	}

	public void itemStateChanged(ItemEvent arg0) { 
	
	     if(cb_sausage.isSelected()) {
	    	p.setTopping_fees(2);
			topping_fees=p.getTopping_fees();
			p.setTopping("(S)");
			topping=p.getTopping();
		}
		
		 if(cb_pepperoni.isSelected()) {
			p.setTopping_fees(2);
			topping_fees=p.getTopping_fees();
			p.setTopping("(P)");
			topping=p.getTopping();
	}
	
		 if(cb_mushroom.isSelected()) {
			p.setTopping_fees(2);
			topping_fees=p.getTopping_fees();
			p.setTopping("(M)");
			topping=p.getTopping();
	}
		  if((cb_pepperoni.isSelected() && cb_mushroom.isSelected())  )  {
			p.setTopping_fees(4);
			topping_fees=p.getTopping_fees();
			p.setTopping("(P+M)");
			topping=p.getTopping();
		}
		 if ((cb_sausage.isSelected() && cb_pepperoni.isSelected())) {
			p.setTopping_fees(4);
			topping_fees=p.getTopping_fees();
			p.setTopping("(S+P)");
			topping=p.getTopping();

		}
		 if( (cb_sausage.isSelected() && cb_mushroom.isSelected())) {
			p.setTopping_fees(4);
			topping_fees=p.getTopping_fees();
			p.setTopping("(S+M)");
			topping=p.getTopping();

		}
		 
		if(cb_sausage.isSelected()&& cb_mushroom.isSelected()&&cb_pepperoni.isSelected()) {
			p.setTopping_fees(6);
			topping_fees=p.getTopping_fees();
			p.setTopping("(S+P+M)");
			topping=p.getTopping();
		}
		
		if(cb_sausage.isSelected()==false && cb_mushroom.isSelected()==false&&cb_pepperoni.isSelected()==false) {
			p.setTopping_fees(0);
			topping_fees=p.getTopping_fees();
			p.setTopping("");
			topping=p.getTopping();
		}

	}



	private void record() {		

		String status="yes";
		try {
			
		SQL.recordOrders(sum,payment,balance,SQL.getAddress(username),username);
		if(status.equals(SQL.checkMember(username))){
			SQL.addpoints(username, (int)(sum/1));
		}
			
		JOptionPane.showMessageDialog(null, "Payment Successful","Payment Completed",JOptionPane.PLAIN_MESSAGE);
		reset();																
		} catch (SQLException e) {}	
	}
	

	private void reset() {															
		
		while(table_cart.getRowCount()!=0) {							
			model_cart.removeRow(0);													
		}
		
		sum=0.0;															
		balance=0.0;
		spinner.setValue(1);
		tf_payment.setText(null);
		order_id++;
		lbl_balance_value.setText(Double.toString(balance));	
		lbl_ordersID_value.setText(Integer.toString(order_id));
		lbl_total_value.setText(Double.toString(sum));
	}
	}

