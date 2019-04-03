import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeInfo extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb3,lb4,lb5;
	private JButton bt1,bt2,bt3,bt4;
	private JTextField tf1;
	private JPanel panel;
	private String accName="";
	private String phoneNumber="";
	private String accid="",role="";
	private double sal;
	private EmployeeHome ch;
	public EmployeeInfo(EmployeeHome ch)
	{
		super("Employee Info");
		this.ch=ch;
		
		this.setSize(550,500);
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
		
		lb5=new JLabel("Account Name:  "+accName);
		lb5.setBounds(100,150,250,40);
		panel.add(lb5);
		
		lb2=new JLabel("Phone Number:  "+phoneNumber);
		lb2.setBounds(100,200,250,40);
		panel.add(lb2);
		
		lb3=new JLabel("Role:  "+role);
		lb3.setBounds(100,250,250,40);
		panel.add(lb3);
		
		lb4=new JLabel("Salary:  "+sal);
		lb4.setBounds(100,300,250,40);
		panel.add(lb4);
		
		bt1=new JButton("Back");
		bt1.setBounds(100,350,80,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Logout");
		bt2.setBounds(200,350,80,40);
		bt2.addActionListener(this);
		panel.add(bt2);

		bt4=new JButton("Delete");
		bt4.setBounds(300,350,80,40);
		bt4.addActionListener(this);
		panel.add(bt4);
		
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
			findInfo(tf1.getText());
			
		}
		else if(ae.getSource().equals(bt4))
		{
			deleteFromDB();
			ch.setVisible(true);
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
			
			boolean flag=false;	
			while(rs.next())
			{
				flag=true;
				accName=rs.getString("empName");
				phoneNumber=rs.getString("phoneNumber");
				role=rs.getString("role");
				sal=rs.getDouble("salary");
				
				lb5.setText("Account Name:  "+accName);
				lb2.setText("Phone Number:  "+"0"+phoneNumber);
				lb3.setText("Role:  "+role);
				lb4.setText("Salary:  "+sal);
			}
			if(flag==false)
			{
				JOptionPane.showMessageDialog(this,"Not Found!!!"); 
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}

	public void deleteFromDB()
	{
		String newId = tf1.getText();
		String query1 = "DELETE from employee WHERE accountId='"+newId+"';";
		String query2 = "DELETE from login WHERE accountId='"+newId+"';";
		System.out.println(query1);
		System.out.println(query2);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query1);
			stm.execute(query2);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
			
			
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
	}
}