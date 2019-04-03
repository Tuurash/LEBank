import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LastTransaction extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb3;
	private JButton bt1,bt2;
	private JPanel panel;
	private String accNo,type,accId;
	private double amount=0;
	private CustomerHome ch;
	public LastTransaction(String accId,CustomerHome ch)
	{
		super("Account Info");
		this.setSize(500,500);
		this.ch=ch;
		this.accId=accId;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		panel.setLayout(null);
		findInfo(accId);
		
		lb1=new JLabel("Transaction Type:  "+type);
		lb1.setBounds(100,150,250,40);
		panel.add(lb1);
		
		lb2=new JLabel("Amount   :  "+amount);
		lb2.setBounds(100,200,150,40);
		panel.add(lb2);
		
		lb3=new JLabel("Send to  :  "+accNo);
		lb3.setBounds(100,100,150,40);
		panel.add(lb3);
		
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
		String query = "SELECT * from `Transaction` where `accountNumber`=(select `accountNumber` from Customer where accountId='"+id+"');";     
        String query2="SELECT * from Transaction2 where `accountNumber`=(select `accountNumber` from Customer where accountId='"+id+"');";
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
				amount=rs.getDouble("amount");
				type=rs.getString("Transactiontype");


			}
			rs=st.executeQuery(query2);
			while(rs.next())
			{
				
				accNo=rs.getString("SendTo");


			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
}