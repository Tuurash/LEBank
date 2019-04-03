import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class Transaction extends JFrame implements ActionListener
{
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6;
	private JTextField tf1,tf2,tf3;
	private JButton bt1,bt2,bt3,bt4;
	private JComboBox combo;
	private JPanel panel;
	private EmployeeHome ch;
	private String acc,comboText;
	private double balance;
	private boolean flag;
	public Transaction(EmployeeHome ch)
	{
		super("Transaction");
		this.ch=ch;
		this.setSize(550,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel =new JPanel();
		panel.setLayout(null);

		lb1=new JLabel("Account Number:");
		lb1.setBounds(100,100,150,40);
		panel.add(lb1);

		tf1=new JTextField();
		tf1.setBounds(260,100,150,40);
		panel.add(tf1);

		bt1=new JButton("Find");
		bt1.setBounds(200,200,100,40);
		bt1.addActionListener(this);
		panel.add(bt1);

		lb2=new JLabel("Type of Transaction:");
		lb2.setBounds(100,150,150,40);
		panel.add(lb2);

		String s[]={"Chose","Deposit","Withdraw","Send"};

		combo = new JComboBox(s);
		combo.setBounds(260, 150, 150, 40);
		panel.add(combo);

		lb3=new JLabel("Balance:");
		lb3.setBounds(100,250,150,40);
		panel.add(lb3);

		lb4=new JLabel("");
		lb4.setBounds(260,250,150,40);
		panel.add(lb4);

		lb5=new JLabel("Ammount:");
		lb5.setBounds(100,300,150,40);
		panel.add(lb5);

		tf2=new JTextField();
		tf2.setBounds(260,300,150,40);
		tf2.setEnabled(false);
		panel.add(tf2);

		lb6=new JLabel("Send to:");
		lb6.setBounds(100,350,150,40);
		panel.add(lb6);
		tf3=new JTextField();
		tf3.setBounds(260,350,150,40);
		tf3.setEnabled(false);
		panel.add(tf3);


		bt2=new JButton("OK");
		bt2.setBounds(100,400,100,40);
		bt2.addActionListener(this);

		panel.add(bt2);

		bt3=new JButton("Back");
		bt3.setBounds(210,400,100,40);
		bt3.addActionListener(this);
		panel.add(bt3);

		bt4=new JButton("Logout");
		bt4.setBounds(320,400,100,40);
		bt4.addActionListener(this);
		panel.add(bt4);

		this.add(panel);
	
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(bt3))
		{
			ch.setVisible(true);
			this.setVisible(false);
			
		}
		else if(ae.getSource().equals(bt4))
		{
			Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
			
		}

		else if(ae.getSource().equals(bt1))
		{

			findInfo(tf1.getText());
			lb4.setText(""+balance);
			comboText = combo.getSelectedItem().toString();

			if(comboText.equals("Deposit")||comboText.equals("Withdraw"))
			{
				tf2.setEnabled(true);
			}
			else if(comboText.equals("Send"))
			{
				tf2.setEnabled(true);
				tf3.setEnabled(true);
			}
			else{}


		}

		else if(ae.getSource().equals(bt2))
		{

			if(comboText.equals("Withdraw"))
			{
				double x=Double.parseDouble(tf2.getText());
				if(x<balance){
				balance=balance-x;
				updateDB();
				JOptionPane.showMessageDialog(this, "Success !!!");
				ch.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not enough Balance !!!");
			}

			}

			else if(comboText.equals("Deposit"))
			{
				double x=Double.parseDouble(tf2.getText());
				balance=balance+x;
				updateDB();
				JOptionPane.showMessageDialog(this, "Success !!!");
				ch.setVisible(true);
				this.setVisible(false);

			}
			else if(comboText.equals("Send"))
			{
			
				double x=Double.parseDouble(tf2.getText());
			if(x<balance){
				balance=balance-x;
				updateDB(tf3.getText());
				
				ch.setVisible(true);
				this.setVisible(false);
			}

			else
			{
				JOptionPane.showMessageDialog(this, "Not enough Balance !!!");
			}
			}
			else{}
			
		}


		else{}
		
		
	}

	public void findInfo(String id)
	{
		String query = "SELECT `accountNumber`, `balance` FROM `account` where `accountNumber`='"+id+"'";     
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
			
			flag=false;	
			while(rs.next())
			{
				flag=true;
				
				balance=rs.getDouble("balance");

							
				
			}
			if(flag==false)
			{
			JOptionPane.showMessageDialog(this, "Not found !!!");
			ch.setVisible(true);
			this.setVisible(false);
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
			

        }
		
		
	}

	public void updateDB()
	{
		
		String query = "UPDATE Transaction set transactionType='"+comboText+"',amount='"+tf2.getText()+"',date=sysdate() where accountNumber='"+tf1.getText()+"'";
		String query1="UPDATE account set balance='"+balance+"' where accountNumber='"+tf1.getText()+"'";	
        String query2="UPDATE Transaction2 set SendTo='"+tf3.getText()+"' where accountNumber='"+tf1.getText()+"';";
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
			st.executeUpdate(query1);
			st.executeUpdate(query2);
			
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
	}

	public void updateDB(String sentId)
	{
		String y="Deposit";
		String query = "UPDATE Transaction set transactionType='"+comboText+"',amount='"+tf2.getText()+"',date=sysdate() where accountNumber='"+tf1.getText()+"'";
		String query1="UPDATE account set balance='"+balance+"' where accountNumber='"+tf1.getText()+"'";
		String query4="UPDATE Transaction2 set SendTo='"+tf3.getText()+"' where accountNumber='"+tf1.getText()+"';";
		

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
			findInfo(sentId);
			String query2 = "UPDATE Transaction set transactionType='"+y+"' , amount='"+tf2.getText()+"',date=sysdate() where accountNumber='"+sentId+"'";
			String query3="UPDATE account set balance='"+(balance+Double.parseDouble(tf2.getText()))+"' where accountNumber='"+sentId+"'";
			st.executeUpdate(query2);//getting result
			st.executeUpdate(query3);
			if(flag==true){
			findInfo(tf1.getText());
			st.executeUpdate(query);//getting result
			st.executeUpdate(query1);
			st.executeUpdate(query4);
			JOptionPane.showMessageDialog(this, "Success !!!");
			
			}
			
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
	}
}