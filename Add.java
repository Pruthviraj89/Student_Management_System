import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Add extends JFrame{
	Container c;
	JLabel Rollno,Name,Marks;
	JTextField txtRollno,txtName,txtMarks;
	JButton btnSave,btnBack;
	public Add(){
		c = getContentPane();
       		c.setLayout(null);

		Rollno=new JLabel("Roll No");
		txtRollno=new JTextField();
		Name=new JLabel("Name");
		txtName=new JTextField();
		Marks=new JLabel("Marks");
		txtMarks=new JTextField();
		btnSave=new JButton("Save");
		btnBack=new JButton("Back");

		Font f=new Font("Calibri",Font.BOLD,25);

		Rollno.setFont(f);
		txtRollno.setFont(f);
		Name.setFont(f);
		txtName.setFont(f);
		Marks.setFont(f);
		txtMarks.setFont(f);
		btnSave.setFont(f);
		btnBack.setFont(f);


		Rollno.setBounds(150, 50, 300, 50);
		txtRollno.setBounds(250, 50, 300, 50);
		Name.setBounds(150, 150, 100, 50);
		txtName.setBounds(250, 150, 300, 50);
		Marks.setBounds(150, 250, 300, 50);
		txtMarks.setBounds(250, 250, 300, 50);
		btnSave.setBounds(250, 350, 300, 50);
		btnBack.setBounds(250, 420, 300, 50);

		c.add(Rollno);
		c.add(txtRollno);
		c.add(Name);
		c.add(txtName);
		c.add(Marks);
		c.add(txtMarks);
		c.add(btnSave);
		c.add(btnBack);

		ActionListener a=(ae)->{
			if(ae.getSource()==btnBack){
		
				Sms home=new Sms();
				dispose();
			}
			if(ae.getSource()==btnSave){

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

					if(!exists){
						sql="insert into student values(?,?,?)";
						pst= con.prepareStatement(sql);
						pst.setString(1,rollno);						
						pst.setString(2,name);
						pst.setString(3,mark);
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
						JOptionPane.showMessageDialog(c, "Already Added");
						pst.close();
						con.close();
						
						txtRollno.setText("");
						txtRollno.requestFocus();
						txtName.setText("");
						txtName.requestFocus();
						txtMarks.setText("");
						txtMarks.requestFocus();
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
		btnSave.addActionListener(a);





	setTitle("Add");
        setSize(800, 600);
	setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);




		
	}

}
