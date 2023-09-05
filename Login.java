import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Login extends JFrame{
	Container c;
	JLabel Login,EnterUsername,EnterPassword;
	JTextField txtUsername;
	JPasswordField txtPassword; 
	JButton btnSignUp,btnSubmit;

	public Login(){
	c = getContentPane();
        c.setLayout(null);
		
	Login=new JLabel("Login");
	EnterUsername=new JLabel("Enter Username");
	txtUsername=new JTextField();
	EnterPassword=new JLabel("Enter Password");
	txtPassword=new JPasswordField();
	btnSubmit=new JButton("Login");
	btnSignUp=new JButton("Sign Up");

	Font f= new Font("Calibri",Font.BOLD,25);
	Login.setFont(f);
	EnterUsername.setFont(f);
	txtUsername.setFont(f);
	EnterPassword.setFont(f);
	txtPassword.setFont(f);
	btnSubmit.setFont(f);
	btnSignUp.setFont(f);


	Login.setBounds(350, 25, 300, 50);
	EnterUsername.setBounds(250, 100, 300, 50);
	txtUsername.setBounds(250, 150, 300, 50);
	EnterPassword.setBounds(250, 200, 300, 50);
	txtPassword.setBounds(250, 250, 300, 50);
	btnSubmit.setBounds(250, 330, 300, 50);
	btnSignUp.setBounds(250, 400, 300, 50);




	c.add(Login);
	c.add(EnterUsername);
	c.add(txtUsername);
	c.add(EnterPassword);
	c.add(txtPassword);
	c.add(btnSubmit);
	c.add(btnSignUp);




	ActionListener a=(ae)->{
		if(ae.getSource()==btnSignUp){
			SignUp signup=new SignUp();
			dispose();
		}if(ae.getSource()==btnSubmit){
			String Username=txtUsername.getText();
			String Password=txtPassword.getText();
			Username=Username.trim();
			Password=Password.trim();
			
			if(Username.length()==0){
			
			txtUsername.setText("");
			txtUsername.requestFocus();
			JOptionPane.showMessageDialog(c,"  Username cannot be empty");
			}else if(Password.length()==0){
			
			txtPassword.setText("");
			txtPassword.requestFocus();
			JOptionPane.showMessageDialog(c,"  Password cannot be empty");
			}else{
				try{
					boolean exists=false;
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url="jdbc:mysql://localhost:3306/sms";
					Connection con= DriverManager.getConnection(url,"root","Pmane@89");
					String sql="select * from register where username=? and password=?";
					PreparedStatement pst= con.prepareStatement(sql);
					pst.setString(1,Username);
					pst.setString(2,Password);
					ResultSet resultSet= pst.executeQuery();


					while(resultSet.next()){
						exists=true;
						break;
					};

					if(exists){
					Sms main = new Sms();
					dispose();
					}else{
						txtUsername.setText("");
						txtUsername.requestFocus();
						txtPassword.setText("");
						txtPassword.requestFocus();
						JOptionPane.showMessageDialog(c, "Invalid Username or Password");
					}
					

				}catch(Exception e){
					JOptionPane.showMessageDialog(c, "issue "+e);
				}


			
			}			

				
			
			}	
	};
	btnSignUp.addActionListener(a);
	btnSubmit.addActionListener(a);

	setTitle("Login");
        setSize(800, 600);
	setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


	}


}