import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerHome extends JFrame implements ActionListener
{
	private JButton bt1,bt2,bt3,bt4,bt5;
	private JLabel lb1;
	private JPanel panel;
	private String accId;
	public CustomerHome(String accId)
	{
		
		super("Customer Home");
		this.accId=accId;
		this.setSize(550,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		panel.setLayout(null);
		
		lb1=new JLabel("Welcome "+accId);
        lb1.setBounds(50,40,150,30);
		
		panel.add(lb1);
		
		bt1=new JButton("Own Info");
		bt1.setBounds(80,100,150,40);
		bt1.addActionListener(this);
		panel.add(bt1);
		
		bt2=new JButton("Account Info");
		bt2.setBounds(240,100,150,40);
		bt2.addActionListener(this);
		panel.add(bt2);
		
		bt3=new JButton("Update Info");
		bt3.setBounds(80,160,150,40);
		bt3.addActionListener(this);
		panel.add(bt3);
		
		bt4=new JButton("Last Transaction");
		bt4.setBounds(240,160,150,40);
		bt4.addActionListener(this);
		panel.add(bt4);
		
		bt5=new JButton("Logout");
		bt5.setBounds(180,210,100,40);
		bt5.addActionListener(this);
		panel.add(bt5);
		
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(bt1))
		{
			OwnInfo o=new OwnInfo(accId,this);
			o.setVisible(true);
			this.setVisible(false);
		}
		
		else if(ae.getSource().equals(bt2))
		{
			AccountInfo o=new AccountInfo(accId,this);
			o.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource().equals(bt3))
		{
			UpdateInfo o=new UpdateInfo(accId,this);
			o.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource().equals(bt4))
		{
			LastTransaction o=new LastTransaction(accId,this);
			o.setVisible(true);
			this.setVisible(false);
		}
		else if(ae.getSource().equals(bt5))
		{
			Login o=new Login();
			o.setVisible(true);
			this.setVisible(false);
		}

	}
	
	
}