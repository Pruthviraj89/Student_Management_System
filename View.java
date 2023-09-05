
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class View extends JFrame{
	Container c;
	TextArea area;
    	JButton btnBack;
	public View(){
		c = getContentPane();
       		c.setLayout(null);
	
		area = new TextArea();
        	area.setEditable(false);
       	 	btnBack = new JButton("Back");
        	Font f = new Font("Calibri", Font.BOLD, 25);
        	area.setFont(f);
        	btnBack.setFont(f);
        	area.setBounds(50, 50, 680, 300);
        	btnBack.setBounds(250, 380, 300, 50);
        	c.add(area);
        	c.add(btnBack);



		try {
            	DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            	String url = "jdbc:mysql://localhost:3306/sms";
            	Connection con = DriverManager.getConnection(url, "root", "Pmane@89");
            	String sql = "select * from student";
            	PreparedStatement pst = con.prepareStatement(sql);
           	 ResultSet rs = pst.executeQuery();
            	while (rs.next()) {
                	area.append("Roll No : "+rs.getString(1)+ ",  Name : "+rs.getString(2)+",  Marks : "+rs.getString(3)+"\n");

            	}
        	} catch (Exception e) {
           		JOptionPane.showMessageDialog(c, "issue"+e);
        	}


		ActionListener a= (ae)->{
			if(ae.getSource()==btnBack){
			 Sms home= new Sms();
			dispose();
			};
		};
		btnBack.addActionListener(a);
		




		setTitle("View");
        	setSize(800, 600);
		setResizable(false);
        	setLocationRelativeTo(null);
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setVisible(true);




		
	}

}