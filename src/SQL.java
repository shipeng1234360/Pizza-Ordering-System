import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class SQL {
	
	private static Connection con=null;																					
	private static Statement stmt=null;																					
	private static ResultSet rs=null;																					
	private static PreparedStatement ps = null;																		
	
	public static void connect() throws SQLException {
		con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza?useSSL=false&&allowPublicKeyRetrieval=true", "root","");	
	
	}

	public static boolean verifyUser(String username , String password) throws SQLException {
		connect();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM customer ");
		
			while(rs.next()) {

				if((username.equals(rs.getString("username"))&&(password.equals(rs.getString("password"))))) {
					JOptionPane.showMessageDialog(null, "Successfully login","Login completed",JOptionPane.PLAIN_MESSAGE);	
					 con.close();
					return true;}
				}	
		
		JOptionPane.showMessageDialog(null, "Please fill in the correct login details!!!","Login failed",JOptionPane.ERROR_MESSAGE);	
		con.close();
		return false;
		
	}

	public static int getOrderID() throws SQLException {																
		
		connect();												
		
		int order_id;
		stmt = con.createStatement();
		rs = stmt.executeQuery("select MAX(order_id) FROM orders");	
		rs.next();
		order_id=rs.getInt(1);
		con.close();
		return order_id;
	}
	
	public  static List<customer> selectCustomer(String username) throws SQLException {

		connect();
		
		 stmt = con.createStatement();
		 rs = stmt.executeQuery("Select * FROM customer where username='"+ username +"'");
		 
		 List<customer> temp = new ArrayList<customer>();
		 
		 while(rs.next()) {
			 
			 customer data = new customer(1);			
			 data.username=rs.getString(1);
			 data.address=rs.getString(4);
			 data.contact=rs.getString(3);
			 data.name=rs.getString(2);
			 data.membership=rs.getString(5);
			 data.ap=rs.getString(6);
			 data.password=rs.getString(7);
		
			temp.add(data);								
		 }
		 con.close();									
		 return temp;
	}
	
	public static void updateCustomer(String username ,String name, String address, String contact_no ,String password)  throws SQLException {
		
		connect();
		ps=con.prepareStatement("update customer set name=?,contact=?, address=? ,password=? where username=?");		
		ps.setString(1, name);
		ps.setString(2, contact_no);
		ps.setString(3, address);
		ps.setString(4, password);
		ps.setString(5, username);
		ps.executeUpdate();
		con.close();
	}
	
	public static void recordOrders(double sum, double payment, double balance, String address,String username) throws SQLException {					
		
		connect();
		
		ps=con.prepareStatement( "insert into orders (total,payment,balance,address,username) values(?,?,?,?,?)");	
		ps.setDouble(1,sum);
		ps.setDouble(2,payment);
		ps.setDouble(3,balance);
		ps.setString(4,address);
		ps.setString(5,username);
		ps.executeUpdate();
		
		con.close();
	
	}
	
	
	public static String getAddress(String username) throws SQLException {																
		
		connect();												
		
		String address;
		stmt = con.createStatement();
		rs = stmt.executeQuery("select address FROM customer where username='"+username+"'");	
		rs.next();
		address=rs.getString(1);
		con.close();
		return address;
	}
	
	
	public static  List<order> getAllRecord(String username) throws SQLException {															
	
	connect();
	
	List<order> recordList = new ArrayList<order>();														
	 
	stmt = con.createStatement();
	 rs = stmt.executeQuery("Select * FROM orders where username = '" + username +"'");
	 
	 while(rs.next()) {
		 
		 order data = new order(1);																			
		 
		 data.order_id=rs.getInt(1);						
		 data.sum=rs.getDouble(2); 
		 data.payment=rs.getDouble(3);
		 data.balance=rs.getDouble(4);
		 data.address=rs.getString(5);
		 recordList.add(data);																			
		
	 }
	 con.close();																							
	 return recordList;
	}

	public static void addpoints(String username, int points)  throws SQLException {
		
		connect();
			ps=con.prepareStatement("update customer set accumulated_points = accumulated_points + ? where username = ?" );	
			ps.setInt(1, points);
			ps.setString(2, username);
			ps.executeUpdate();
			con.close();
		}

	public static String checkMember(String username) throws SQLException {		
		
			connect();												
			String status;
			stmt = con.createStatement();
			rs = stmt.executeQuery("select membership FROM customer where username ='" + username+"'" );	
			rs.next();
			status=rs.getString(1);
			con.close();
			return status;
		}

	

}	
	
			