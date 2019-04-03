import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class OwnInfoE extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb3,lb4;
	private JButton bt1,bt2;
	private JPanel panel;
	private String accName;
	private String phoneNumber;
	private String accid,role;
	private double sal;
	private EmployeeHome ch;
	public OwnInfoE(String accid,EmployeeHome ch)
	{
		super("Own InfoE");
		this.ch=ch;
		this.accid=accid;
		this.setSize(550,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		panel.setLayout(null);
		findInfo(accid);
		lb1=new JLabel("Account Name:  "+accName);
		lb1.setBounds(100,100,250,40);
		panel.add(lb1);
		
		lb2=new JLabel("Phone Number:  "+"0"+phoneNumber);
		lb2.setBounds(100,150,250,40);
		panel.add(lb2);
		
		lb3=new JLabel("Role:  "+role);
		lb3.setBounds(100,200,250,40);
		panel.add(lb3);
		
		lb4=new JLabel("Salary:  "+sal);
		lb4.setBounds(100,250,250,40);
		panel.add(lb4);
		
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
		String query = "SELECT * from `employee` where `accountid`='"+id+"';";     
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
				accName=rs.getString("empName");
				phoneNumber=rs.getString("phoneNumber");
				role=rs.getString("role");
				sal=rs.getDouble("salary");
				
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
}