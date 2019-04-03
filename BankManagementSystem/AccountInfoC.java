import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AccountInfoC extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb4,lb5,lb6;
	private JTextField tf1;
	private JButton bt1,bt2,bt3;
	private JPanel panel;
	private String cusName="",phnNo="",accNo="";
	private double balance=0;
	private EmployeeHome ch;
	public AccountInfoC(EmployeeHome ch)
	{
		super("Account Info C");
		this.ch=ch;
		
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		panel.setLayout(null);
		
		lb1=new JLabel("Account ID:  ");
		lb1.setBounds(100,100,150,40);
		panel.add(lb1);
		
		tf1=new JTextField();
		tf1.setBounds(210,100,150,40);
		panel.add(tf1);
		
		bt3=new JButton("Find");
		bt3.setBounds(370,100,80,40);
		bt3.addActionListener(this);
		panel.add(bt3);
		
		
		
		
		lb2=new JLabel("Customer Name:"+cusName);
		lb2.setBounds(100,150,170,40);
		panel.add(lb2);
		
		lb4=new JLabel("Balance   :  "+balance);
		lb4.setBounds(100,200,150,40);
		panel.add(lb4);
		
		lb5=new JLabel("Phone No: "+phnNo);
		lb5.setBounds(100,250,150,40);
		panel.add(lb5);
		
		lb6=new JLabel("Account No: "+accNo);
		lb6.setBounds(100,300,150,40);
		panel.add(lb6);
		
		
		bt1=new JButton("Back");
		bt1.setBounds(150,350,80,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Logout");
		bt2.setBounds(250,350,80,40);
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
		
		else if(ae.getSource().equals(bt3))
		{
			findInfo();
		}
		
		else{}
		
		
	}
	
	
	public void findInfo()
	{
		String query1 = "SELECT `balance` FROM `account` where `accountNumber`=(SELECT `accountNumber` from `customer` where `accountid`='"+tf1.getText()+"');";     
        String query2="SELECT * from `customer` where `accountid`='"+tf1.getText()+"'";
		Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query1);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query1);//getting result
			System.out.println("results received");
			
			boolean flag=false;	
			while(rs.next())
			{
				
				balance=rs.getDouble("balance");
				flag=true;
				
				
				lb4.setText("Balance   :  "+balance);
				
				
			}
			rs = st.executeQuery(query2);
			while(rs.next())
			{
				accNo=rs.getString("AccountNumber");
				cusName=rs.getString("customerName");
				phnNo=rs.getString("phoneNumber");
				lb6.setText("Account No: "+accNo);
				flag=true;
				lb2.setText("Customer Name:"+cusName);
				lb5.setText("Phone No: "+"0"+phnNo);
			}
			
			if(flag==false)
			{
				JOptionPane.showMessageDialog(this, "Not Found!!");
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		
	}
}