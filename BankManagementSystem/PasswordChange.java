import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class PasswordChange extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb3;
	private JButton bt1,bt2,bt3;
	private JPasswordField pf1,pf2,pf3;
	private JPanel panel;
	private String accid;
	private UpdateInfo ui;
	private String pass;
	public PasswordChange(String accid,UpdateInfo ui)
	{
		super("Password Change");
		this.setSize(500,500);
		this.accid=accid;
		this.ui=ui;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		panel.setLayout(null);
		
		lb1=new JLabel("Old Password:");
		lb1.setBounds(100,100,250,40);
		panel.add(lb1);
		
		pf1=new JPasswordField();
		pf1.setBounds(350,100,100,40);
		panel.add(pf1);
		
		lb3=new JLabel("New Password:");
		lb3.setBounds(100,150,150,40);
		panel.add(lb3);
		
		pf2=new JPasswordField();
		pf2.setBounds(350,200,100,40);
		panel.add(pf2);
		
		lb2=new JLabel("Confirm Password:");
		lb2.setBounds(100,200,150,40);
		panel.add(lb2);
		
		pf3=new JPasswordField();
		pf3.setBounds(350,150,100,40);
		panel.add(pf3);
		
		bt1=new JButton("Back");
		bt1.setBounds(150,300,80,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Logout");
		bt2.setBounds(250,300,80,40);
		bt2.addActionListener(this);
		panel.add(bt2);
		
		bt3=new JButton("Update");
		bt3.setBounds(250,250,80,40);
		bt3.addActionListener(this);
		panel.add(bt3);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(bt2))
		{
			Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
			
		}
		else if(ae.getSource().equals(bt1))
		{
			
			ui.setVisible(true);
			this.setVisible(false);
			
		}
		else if(ae.getSource().equals(bt3))
		{
			findInfo(accid);
			System.out.println(pass);
			
			if((pf1.getText().equals(pass))&&(pf2.getText().equals(pf3.getText())))
			{
				updateDB(accid);
				JOptionPane.showMessageDialog(this,"Succesfuly Updated"); 
				ui.setVisible(true);
			    this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Password not Matched"); 
				
			}
			
			
		}
		
	}
	
	public void findInfo(String id)
	{
		String query = "SELECT * from `login` where `accountid`='"+id+"';";     
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
				pass=rs.getString("password");			
				
			}
			System.out.println(pass);
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
	public void updateDB(String id)
	{
		
		String query = "UPDATE login set password='"+pf2.getText()+"' WHERE AccountId='"+id+"'";	
        Connection con=null;//for connection
        Statement st = null;//for query execution
		System.out.println(query);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.executeUpdate(query);//getting result
			
			
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
	}
}