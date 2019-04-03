import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
public class CreatEmp extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
	private JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	private JButton bt1,bt2,bt3,bt4;
	private JPanel panel;
	private EmployeeHome eh;
	private Random r=new Random();
	public CreatEmp(EmployeeHome eh)
	{
		super("Creat Employee");
		this.eh=eh;
		this.setSize(550,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel= new JPanel();
		panel.setLayout(null);
		
		lb1=new JLabel("Account Name:");
		lb1.setBounds(100,100,150,40);
		panel.add(lb1);
		
		lb2=new JLabel("Account Id:");
		lb2.setBounds(100,150,150,40);
		panel.add(lb2);
		
		lb3=new JLabel("Phone Number:");
		lb3.setBounds(100,200,150,40);
		panel.add(lb3);
		lb6=new JLabel(" 0");
		lb6.setBounds(240,200,20,40);
		lb6.setOpaque(true);
		lb6.setBackground(Color.GREEN);
		panel.add(lb6);
		
		lb4=new JLabel("Role:");
		lb4.setBounds(100,250,150,40);
		panel.add(lb4);
		
		lb5=new JLabel("Password:");
		lb5.setBounds(100,300,150,40);
		panel.add(lb5);

		lb7=new JLabel("Salary");
		lb7.setBounds(100,350,150,40);
		panel.add(lb7);
		
		tf1=new JTextField();
		tf1.setBounds(260,100,150,40);
		panel.add(tf1);
		
		tf2=new JTextField();
		tf2.setBounds(260,150,150,40);
		panel.add(tf2);
		
		tf3=new JTextField();
		tf3.setBounds(260,200,150,40);
		panel.add(tf3);
		
		tf4=new JTextField();
		tf4.setBounds(260,250,150,40);
		panel.add(tf4);
		
		tf5=new JTextField();
		tf5.setBounds(260,300,150,40);
		tf5.setEnabled(false);
		panel.add(tf5);

		tf6=new JTextField();
		tf6.setBounds(260,350,150,40);
		panel.add(tf6);
		
		bt1=new JButton("Creat");
		bt1.setBounds(110,400,80,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Back");
		bt2.setBounds(210,400,80,40);
		bt2.addActionListener(this);
		panel.add(bt2);
		
		bt3=new JButton("Logout");
		bt3.setBounds(310,400,80,40);
		bt3.addActionListener(this);
		panel.add(bt3);
		bt4=new JButton("Genarate");
		bt4.setBounds(420,300,100,40);
		bt4.addActionListener(this);
		panel.add(bt4);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(bt1))
		{
			insertIntoDB();
			
		}
		
		else if(ae.getSource().equals(bt2))
		{
			eh.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource().equals(bt3))
		{
			Login o=new Login();
			o.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource().equals(bt4))
		{
			
			long x=r.nextInt(10000000);
			tf5.setText(""+x);
		}
		

	}
	public void insertIntoDB()
	{
		String newId = tf2.getText();
		String newPass = tf5.getText();
		String eName = tf1.getText();
		
		String role=tf4.getText();
		
		int status = 1;
		
		
		String query2 = "INSERT INTO Login VALUES ('"+newId+"','"+newPass+"',"+status+");";
		//System.out.println(query1);
		System.out.println(query2);
        try
		{

			String y=Integer.toString(r.nextInt(1000000));
			String a="";
			double x=Double.parseDouble(tf3.getText());
			double z=Double.parseDouble(tf6.getText());
			String phnNo = lb6.getText()+tf3.getText();

			String query1="INSERT into employee VALUES ('"+newId+"','"+eName+"','"+phnNo+"','"+role+"','"+z+"');";

			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query1);
			stm.execute(query2);
			
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
			eh.setVisible(true);
			this.setVisible(false);
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
    }	
	
}