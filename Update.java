import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Update extends JFrame{
	Container c;
	JLabel Rollno,name,marks,Update;
	JTextField txtRollno,txtName,txtMarks;
	JButton btnUpdate,btnBack;
	public Update(){
		c = getContentPane();
       		c.setLayout(null);

		Update=new JLabel("Update");
;		Rollno=new JLabel("Roll No");
		txtRollno=new JTextField();
		name=new JLabel("Name");
		txtName=new JTextField();
		marks=new JLabel("Marks");
		txtMarks=new JTextField();
		btnUpdate=new JButton("Update");
		btnBack=new JButton("Back");

		Font f=new Font("Calibri",Font.BOLD,25);

		Update.setFont(f);
		Rollno.setFont(f);
		txtRollno.setFont(f);
		name.setFont(f);
		txtName.setFont(f);
		marks.setFont(f);
		txtMarks.setFont(f);
		btnUpdate.setFont(f);
		btnBack.setFont(f);

		Update.setBounds(350,50,100,50);
		Rollno.setBounds(150, 100, 300, 50);
		txtRollno.setBounds(250, 100, 300, 50);
		name.setBounds(150, 200, 200, 50);
		txtName.setBounds(250, 200, 300, 50);
		marks.setBounds(150, 300, 300, 50);
		txtMarks.setBounds(250, 300, 300, 50);
		btnUpdate.setBounds(250, 400, 300, 50);
		btnBack.setBounds(250, 480, 300, 50);


		c.add(Update);
		c.add(Rollno);
		c.add(txtRollno);
		c.add(name);
		c.add(txtName);
		c.add(marks);
		c.add(txtMarks);
		c.add(btnUpdate);
		c.add(btnBack);

		ActionListener a=(ae)->{
			if(ae.getSource()==btnBack){
		
				Sms home=new Sms();
				dispose();
			}
			if(ae.getSource()==btnUpdate){



				
				try{

				String rollno=txtRollno.getText();
				String name=txtName.getText();
				String mark=txtMarks.getText();
				
				rollno=rollno.trim();
				name=name.trim();
				mark=mark.trim();
				
				
				int newMark=Integer.parseInt(mark);
			

				if(name.length()==0){
						txtName.setText("");
						txtName.requestFocus();
						JOptionPane.showMessageDialog(c,"Name cannot be empty");
				}else if(rollno.length()==0){
					txtRollno.setText("");
						txtRollno.requestFocus();
						JOptionPane.showMessageDialog(c,"Roll No cannot be empty");
				}else if(mark.length()==0){	
						txtMarks.setText("");
						txtMarks.requestFocus();
					JOptionPane.showMessageDialog(c,"Marks cannot be empty");
				}else if(!(name.length()>2)){
					txtName.setText("");
					txtName.requestFocus();
					JOptionPane.showMessageDialog(c,"name length should not be less than two");
				}else if(!name.matches("[A-Za-z]+")){
					txtName.setText("");
					txtName.requestFocus();
					JOptionPane.showMessageDialog(c,"Enter valid name");
				}else if(!(rollno.matches("[0-9]+")||mark.matches("[0-9-]+"))){
					txtRollno.setText("");
					txtRollno.requestFocus();
					txtMarks.setText("");
					txtMarks.requestFocus();
					JOptionPane.showMessageDialog(c,"rollno and marks should contain only Numbers ");
				}else if(!((newMark > 0 || newMark==0)&& (newMark<100 ||newMark==100))){
					txtMarks.setText("");
					txtMarks.requestFocus();
					JOptionPane.showMessageDialog(c,"Marks should range within or equal to  0 to 100");
				}else{

					boolean exists=false;
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url="jdbc:mysql://localhost:3306/sms";
					Connection con= DriverManager.getConnection(url,"root","Pmane@89");
					String sql="select rollno from student where rollno=?";
					PreparedStatement pst= con.prepareStatement(sql);
					pst.setString(1,rollno);
					ResultSet resultSet= pst.executeQuery();

					while(resultSet.next()){
						exists=true;
						break;
					};

					if(exists){
						sql="update student set name=?, marks=? where rollno=?";
						pst= con.prepareStatement(sql);
						pst.setString(1,name);						
						pst.setString(2,mark);
						pst.setString(3,rollno);
						pst.executeUpdate();
						pst.close();
						con.close();
						
						txtRollno.setText("");
						txtRollno.requestFocus();
						txtName.setText("");
						txtName.requestFocus();
						txtMarks.setText("");
						txtMarks.requestFocus();
						JOptionPane.showMessageDialog(c, "Added Sucessfull");
						
					}else{
						
						pst.close();
						con.close();
						txtRollno.setText("");
						txtRollno.requestFocus();
						txtName.setText("");
						txtName.requestFocus();
						txtMarks.setText("");
						txtMarks.requestFocus();
						JOptionPane.showMessageDialog(c, "Roll Number Does not exists");
						
					}

					


				}
				}catch(NumberFormatException e){
					txtRollno.setText("");
					txtRollno.requestFocus();
					txtMarks.setText("");
					txtMarks.requestFocus();
					JOptionPane.showMessageDialog(c,"rollno and marks should contain only Numbers ");
				}catch(Exception e){
					JOptionPane.showMessageDialog(c,"error :"+e);
				}


			
			}
		};
		btnBack.addActionListener(a);
		btnUpdate.addActionListener(a);
	





		setTitle("Update");
        	setSize(800, 600);
		setResizable(false);
        	setLocationRelativeTo(null);
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setVisible(true);




		
	}

}