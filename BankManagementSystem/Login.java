import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener
{
	private JButton bt1,bt2;
	private JLabel lb1,lb2,background,Font;
	private JTextField text;
	private JPasswordField passField;
	private JPanel panel;
	
	
	public Login()
	{
		super("Login");
		this.setSize(550,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		
		panel.setLayout(null);
		lb1=new JLabel("User ID:");
		lb1.setForeground(Color.DARK_GRAY);
        lb1.setBounds(82, 104, 123, 27);
		panel.add(lb1);
		
		lb2= new JLabel("Password:");
		lb2.setForeground(Color.DARK_GRAY);
		lb2.setBounds(82, 163, 123, 27);
		panel.add(lb2);
		
		text=new JTextField();
		text.setBounds(235, 104, 108, 27);
		panel.add(text);
		
		passField=new JPasswordField();
		passField.setBounds(235, 163, 108, 27);
		panel.add(passField);
		
		bt1=new JButton("LOGIN");
		bt1.setBounds(148, 229, 108, 33);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("CLOSE");
		bt2.setBounds(408, 406, 89, 23);
		bt2.addActionListener(this);
		panel.add(bt2);
	
		this.add(panel);
		
		ImageIcon img =new ImageIcon("picture3.jpg");
		background =new JLabel("",img,JLabel.CENTER);
		background.setBounds(0, 0, 534, 461);
		panel.add(background);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(bt1.getText()))
		{
			checkLogin();
		}
		else if(text.equals(bt2.getText()))
		{
			System.exit(0);
		}
		else{}
	}
	
	public void checkLogin()
	{
		String query = "SELECT `accountId`, `password`, `status` FROM `login`;";     
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
			
			boolean flag = false;			
			while(rs.next())
			{
                String userId = rs.getString("accountId");
                String password = rs.getString("password");
				int status = rs.getInt("status");
				
				if(userId.equals(text.getText()) && password.equals(passField.getText()))
				{
					flag=true;
					if(status==1)
					{
						EmployeeHome eh = new EmployeeHome(userId);
						eh.setVisible(true);
						this.setVisible(false);
					}
					else if(status==0)
					{
						CustomerHome ch = new CustomerHome(userId);
						ch.setVisible(true);
						this.setVisible(false);
					}
					else{}
				}
			}
			if(!flag)
			{
				JOptionPane.showMessageDialog(this,"Invalid ID or Password"); 
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}
	
	
}