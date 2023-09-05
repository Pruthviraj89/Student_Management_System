import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame {
	Container c;
	JLabel SignUp,EnterUsername,EnterPassword,RepeatPassword;
	JTextField txtUsername;
	JPasswordField txtPassword,txtRepeatPassword;
	JButton btnSubmit,btnLogin;
	
    public SignUp() {
        c = getContentPane();
        c.setLayout(null);
	
	SignUp=new JLabel("Sign Up");
	EnterUsername=new JLabel("Enter Username");
	txtUsername=new JTextField();
	EnterPassword=new JLabel("Enter Password");
	txtPassword=new JPasswordField();
	RepeatPassword=new JLabel("Repeat Password");
	txtRepeatPassword=new JPasswordField();
	btnSubmit=new JButton("Sign Up");
	btnLogin=new JButton("Login");

	Font f= new Font("Calibri",Font.BOLD,25);
	SignUp.setFont(f);
	EnterUsername.setFont(f);
	txtUsername.setFont(f);
	EnterPassword.setFont(f);
	txtPassword.setFont(f);
	RepeatPassword.setFont(f);
	txtRepeatPassword.setFont(f);
	btnSubmit.setFont(f);
	btnLogin.setFont(f);

	SignUp.setBounds(350, 50, 300, 50);
	EnterUsername.setBounds(250, 100, 300, 50);
	txtUsername.setBounds(250, 150, 300, 50);
	EnterPassword.setBounds(250, 200, 300, 50);
	txtPassword.setBounds(250, 250, 300, 50);
	RepeatPassword.setBounds(250, 300, 300, 50);
	txtRepeatPassword.setBounds(250, 350, 300, 50);
	btnSubmit.setBounds(250, 420, 300, 50);
	btnLogin.setBounds(250, 480, 300, 50);


	c.add(SignUp);
	c.add(EnterUsername);
	c.add(txtUsername);
	c.add(EnterPassword);
	c.add(txtPassword);
	c.add(RepeatPassword);
	c.add(txtRepeatPassword);
	c.add(btnSubmit);
	c.add(btnLogin);

	
	ActionListener a = (ae) -> {
		if(ae.getSource() == btnSubmit){

			String Username=txtUsername.getText();
			String Password=txtPassword.getText();
			String rPassword=txtRepeatPassword.getText();
			Password=Password.trim();
			rPassword=rPassword.trim();
			
			if(Username.trim().length()==0  ){
				JOptionPane.showMessageDialog(c,"  Username cannot be empty");
				txtUsername.setText("");
				txtUsername.requestFocus();
			}else if(!(Username.length()>2)){
				JOptionPane.showMessageDialog(c,"Enter a valid Username,username should be more that 2 length");
				txtUsername.setText("");
				txtUsername.requestFocus();
			}else if(!Username.trim().matches( "[A-Za-z]+"  )){
				JOptionPane.showMessageDialog(c," Enter a valid Username, Username should Contain only Alphabets");
				txtUsername.setText("");
				txtUsername.requestFocus();
			}else if(!Password.trim().matches( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$" )){
				JOptionPane.showMessageDialog(c,"Password should Contain atleast 8 characters,One digit,One Uppercase,One LowerCase,One Special Character");
				txtPassword.setText("");
				txtPassword.requestFocus();
				txtRepeatPassword.setText("");
				txtRepeatPassword.requestFocus();
			}else if(! Password.matches(rPassword) ){
				JOptionPane.showMessageDialog(c,"Password Does not match ");
				txtPassword.setText("");
				txtPassword.requestFocus();
				txtRepeatPassword.setText("");
				txtRepeatPassword.requestFocus();
			}else if(Password.trim().length()==0){
				JOptionPane.showMessageDialog(c,"  Password cannot be empty");
				txtPassword.setText("");
				txtPassword.requestFocus();
			}else if(rPassword.trim().length()==0){
				JOptionPane.showMessageDialog(c," Repeat Password cannot be empty");
				txtRepeatPassword.setText("");
				txtRepeatPassword.requestFocus();
			}else{
				try{
					boolean exists=false;
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					String url="jdbc:mysql://localhost:3306/sms";
					Connection con= DriverManager.getConnection(url,"root","Pmane@89");
					String sql="select username from register where username=?";
					PreparedStatement pst= con.prepareStatement(sql);
					pst.setString(1,Username);
					ResultSet resultSet= pst.executeQuery();


					while(resultSet.next()){
						exists=true;
						break;
					};
					if(!exists){
						sql="insert into register values(?,?)";
						pst= con.prepareStatement(sql);
						pst.setString(1,Username);						
						pst.setString(2,Password);
						pst.executeUpdate();
						pst.close();
						con.close();
						
						txtUsername.setText("");
						txtUsername.requestFocus();
						txtPassword.setText("");
						txtPassword.requestFocus();
						txtRepeatPassword.setText("");
						txtRepeatPassword.requestFocus();
						JOptionPane.showMessageDialog(c, "Registered Sucessfull");
						Login login = new Login();
						dispose();
						
					}else{
						JOptionPane.showMessageDialog(c, "Username already Registered");
						pst.close();
						con.close();
						txtUsername.setText("");
						txtUsername.requestFocus();
						txtPassword.setText("");
						txtPassword.requestFocus();
						txtRepeatPassword.setText("");
						txtRepeatPassword.requestFocus();
					}
					
					
					
					
					
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(c, "issue "+e);
				}
			}		
			
		}if(ae.getSource()==btnLogin){
		Login login = new Login();
		dispose();
			
		};
	};
	btnSubmit.addActionListener(a);
	btnLogin.addActionListener(a);


        
        setTitle("Register");
        setSize(800, 600);
	setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}