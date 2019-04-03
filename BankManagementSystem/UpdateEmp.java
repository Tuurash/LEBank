import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmp extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb3,lb4,lb5;
	private JTextField tf2,tf3,tf4,tf5;
	private JButton bt1,bt2,bt3,bt4;
	private JTextField tf1;
	private JPanel panel;
	private String accName="";
	private String phoneNumber="";
	private String accid="",role="";
	private double sal;
	private EmployeeHome ch;
	public UpdateEmp(EmployeeHome ch)
	{
		super("Update Employee");
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
		

		
		
		lb2=new JLabel("Role:");
		lb2.setBounds(100,150,250,40);
		panel.add(lb2);

		tf2=new JTextField(role);
		tf2.setBounds(210,150,200,40);
		panel.add(tf2);
		
		lb3=new JLabel("Salary:");
		lb3.setBounds(100,200,250,40);
		panel.add(lb3);

		tf3=new JTextField(""+sal);
		tf3.setBounds(210,200,200,40);
		panel.add(tf3);
		
		bt1=new JButton("Back");
		bt1.setBounds(100,300,80,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Logout");
		bt2.setBounds(200,300,80,40);
		bt2.addActionListener(this);
		panel.add(bt2);

		bt4=new JButton("Update");
		bt4.setBounds(300,300,80,40);
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
			updateFromDB();
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
				role=rs.getString("role");
				sal=rs.getDouble("salary");
				
				
				tf2.setText(role);
				tf3.setText(""+sal);
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

	public void updateFromDB()
	{
		double c;
		String newId = tf1.getText();
        try
		{
			c=Double.parseDouble(tf3.getText());
			String query1 = "Update employee set role='"+tf2.getText()+"',salary='"+c+"' WHERE accountId='"+newId+"';";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query1);
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