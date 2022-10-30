import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class record extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table_order ;
	private JButton btn_back;
	private DefaultTableModel model_order;	
	private String username;
	private List<order> recordList;
	
	public record( String username){																	
		 
		this.username=username;
		ImageIcon bgd = new ImageIcon("image/record_bgd.jpg");
		String column1[]= {"Order_id","Total(RM)","Payment(RM)","Balance(RM)","Address" };				
		Object[][] row1 ={};

		setResizable(false);
		setSize(627, 454);
		setTitle("Pizza Ordering System");
		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(10, 22, 601, 340);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 581, 318);
		panel.add(scrollPane);
		
		model_order=new DefaultTableModel(row1,column1);
		table_order = new JTable(model_order);
		scrollPane.setViewportView(table_order);
		
		btn_back = new JButton("Back");
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_back.setBounds(10, 373, 125, 33);
		getContentPane().add(btn_back);
		btn_back.addActionListener(this);
		
		JLabel lbl_bgd = new JLabel();
		lbl_bgd.setIcon(bgd);
		lbl_bgd.setBounds(0, 0, 621, 425);
		getContentPane().add(lbl_bgd);
		
		displayOrder();
	}
	
	public void actionPerformed(ActionEvent evt) {
		
	if(evt.getSource()==btn_back) { 
			new home(username); 													
			dispose(); 													
			 }
}
		
	private void displayOrder() {									
		
		 int size;
		 
		 recordList = new ArrayList<order>();
		
			 try {													
				 recordList=SQL.getAllRecord(username);										
				 }
			 
			 catch (SQLException e) {e.printStackTrace();}
			 
			 size=recordList.size();												
			 
			 for (int i=0;i<size;i++) {												
				 model_order.addRow(new Object[] 	
						{ 	recordList.get(i).order_id, 
							recordList.get(i).sum,
							recordList.get(i).payment,
							recordList.get(i).balance,
							recordList.get(i).address
						});
				};
		
	}
}
