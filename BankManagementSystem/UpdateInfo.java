import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateInfo extends JFrame implements ActionListener
{
	private JLabel lb1,lb2;
	private JTextField tf1,tf2;
	private JButton bt1,bt2,bt3,bt4;
	private String accid;
	private CustomerHome ch;
	private String cusName,cusPhone;
	private JPanel panel;
	public UpdateInfo(String accid, CustomerHome ch)
	{
		super("Customer Home");
		this.accid=accid;
		this.ch=ch;
		this.setSize(550,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		panel.setLayout(null);
		
		findInfo(accid);
		
		lb1=new JLabel("Name :");
		lb1.setBounds(80,40,150,40);		
		panel.add(lb1);
		
		tf1=new JTextField(cusName);
		tf1.setBounds(240,40,150,40);
		panel.add(tf1);
		
		lb2=new JLabel("Phone :");
		lb2.setBounds(80,100,150,40);
		
		panel.add(lb2);
		
		tf2=new JTextField("0"+cusPhone);
		tf2.setBounds(240,100,150,40);
		panel.add(tf2);
		
		bt1=new JButton("Change Password");
		bt1.setBounds(80,160,150,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Back");
		bt2.setBounds(240,210,150,40);
		bt2.addActionListener(this);
		panel.add(bt2);
		
		bt3=new JButton("Logout");
		bt3.setBounds(80,210,150,40);
		bt3.addActionListener(this);
		panel.add(bt3);
		
		bt4=new JButton("Update");
		bt4.setBounds(240,160,150,40);
		bt4.addActionListener(this);
		panel.add(bt4);
		
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(bt2))
		{
			ch.setVisible(true);
			this.setVisible(false);
			
		}
		else if(ae.getSource().equals(bt3))
		{
			Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
			
		}
		else if(ae.getSource().equals(bt4))
		{
			updateDB(accid);
			
			ch.setVisible(true);
			this.setVisible(false);
			
		}
		else if(ae.getSource().equals(bt1))
		{
			PasswordChange p=new PasswordChange(accid,this);
			p.setVisible(true);
			this.setVisible(false);
			
		}
		
		else{}
		
		
	}
	
	public void findInfo(String id)
	{
		String query = "SELECT * from `customer` where `accountid`='"+id+"';";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			
				
			while(rs.next())
			{
				cusName=rs.getString("customerName");
				cusPhone=rs.getString("phoneNumber");			
				
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
	
	public void updateDB(String id)
	{
		
		String query = "UPDATE customer set customerName='"+tf1.getText()+"',phoneNumber='"+tf2.getText()+"' WHERE AccountId='"+id+"'";	
        Connection con=null;//for connection
        Statement st = null;//for query execution
		System.out.println(query);
		
		try
		{
			double x=Double.parseDouble(tf2.getText());
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.executeUpdate(query);//getting result
			
			JOptionPane.showMessageDialog(this,"Succesfuly Updated"); 
			
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"No Character Allow in phone number"); 
        }
		
	}
	
}