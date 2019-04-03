import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AccountInfo extends JFrame implements ActionListener
{
	private JLabel lb1,lb2;
	private JButton bt1,bt2;
	private JPanel panel;
	private String accid,acc;
	private double balance;
	private CustomerHome ch;
	public AccountInfo(String accid,CustomerHome ch)
	{
		super("Account Info");
		this.ch=ch;
		this.accid=accid;
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		panel.setLayout(null);
		findInfo(accid);
		
		lb1=new JLabel("Account Number:  "+acc);
		lb1.setBounds(100,100,250,40);
		panel.add(lb1);
		
		lb2=new JLabel("Balance   :  "+balance);
		lb2.setBounds(100,200,150,40);
		panel.add(lb2);
		
		bt1=new JButton("Back");
		bt1.setBounds(150,300,80,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Logout");
		bt2.setBounds(250,300,80,40);
		bt2.addActionListener(this);
		panel.add(bt2);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(bt1))
		{
			ch.setVisible(true);
			this.setVisible(false);
			
		}
		else if(ae.getSource().equals(bt2))
		{
			Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
			
		}
		else{}
		
		
	}
	
	
	public void findInfo(String id)
	{
		String query = "SELECT `accountNumber`, `balance` FROM `account` where `accountNumber`=(SELECT `accountNumber` from `customer` where `accountid`='"+id+"');";     
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
				acc=rs.getString("AccountNumber");
				balance=rs.getDouble("balance");			
				
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		
	}
}