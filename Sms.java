import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



public class Sms extends JFrame{
	Container c;
	JButton btnAdd,btnView,btnUpdate,btnDelete;

	public Sms(){
		c = getContentPane();
       		c.setLayout(null);
	
	btnAdd=new JButton("Add");
	btnView=new JButton("View");
	btnUpdate=new JButton("Update");
	btnDelete=new JButton("Delete");
	
	Font f=new Font("Calibri",Font.BOLD,25);
	btnAdd.setFont(f);
	btnView.setFont(f);
	btnUpdate.setFont(f);
	btnDelete.setFont(f);
	
	btnAdd.setBounds(250, 70, 300, 50);
	btnView.setBounds(250, 130, 300, 50);
	btnUpdate.setBounds(250, 190, 300, 50);
	btnDelete.setBounds(250, 250, 300, 50);
	
	c.add(btnAdd);
	c.add(btnView);
	c.add(btnUpdate);
	c.add(btnDelete);



	ActionListener a= (ae)->{
		if(ae.getSource()==btnView){
			View v= new View();
			dispose();
		}
		if(ae.getSource()==btnAdd){
			Add add =new Add();
			dispose();
		}
		if(ae.getSource()==btnUpdate){
			Update update=new Update();
			dispose();
		}
		if(ae.getSource()==btnDelete){
			Delete delete=new Delete();
			dispose();
		}
	};


	btnView.addActionListener(a);
	btnAdd.addActionListener(a);
	btnUpdate.addActionListener(a);
	btnDelete.addActionListener(a);

	setTitle("Home Page");
        setSize(800, 600);
	setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	
	
	}
}



































