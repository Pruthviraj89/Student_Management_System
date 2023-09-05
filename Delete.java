import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Delete extends JFrame {
	Container c;
	JLabel rollno, Delete;
	JTextField txtRollno;
	JButton btnDelete, btnBack;

	public Delete() {
		c = getContentPane();
		c.setLayout(null);
		Delete = new JLabel("Delete");
		rollno = new JLabel("Roll No");
		txtRollno = new JTextField();
		btnDelete = new JButton("Delete");
		btnBack = new JButton("Back");

		Font f = new Font("Calibri", Font.BOLD, 25);
		Delete.setFont(f);
		rollno.setFont(f);
		txtRollno.setFont(f);
		btnDelete.setFont(f);
		btnBack.setFont(f);

		Delete.setBounds(350, 50, 150, 50);
		rollno.setBounds(150, 100, 300, 50);
		txtRollno.setBounds(250, 100, 300, 50);
		btnDelete.setBounds(250, 200, 300, 50);
		btnBack.setBounds(250, 300, 300, 50);

		c.add(Delete);
		c.add(rollno);
		c.add(txtRollno);
		c.add(btnDelete);
		c.add(btnBack);

		ActionListener a = (ae) -> {
			if (ae.getSource() == btnBack) {
				Sms home = new Sms();
				dispose();
			}
			if (ae.getSource() == btnDelete) {
				try {
					String rollno = txtRollno.getText();
					rollno = rollno.trim();
					if (rollno.length() == 0) {
						txtRollno.setText("");
						txtRollno.requestFocus();
						JOptionPane.showMessageDialog(c, "Fileds cannot be empty");
					} else if (!rollno.matches("[0-9]+")) {
						txtRollno.setText("");
						txtRollno.requestFocus();
						JOptionPane.showMessageDialog(c, "rollno should contain only Numbers ");
					} else {
						boolean exists = false;
						DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
						String url = "jdbc:mysql://localhost:3306/sms";
						Connection con = DriverManager.getConnection(url, "root", "Pmane@89");
						String sql = "select rollno from student where rollno=?";
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setString(1, rollno);
						ResultSet resultSet = pst.executeQuery();

						while (resultSet.next()) {
							exists = true;
							break;
						}
						;

						if (exists) {
							sql = "delete from student where rollno=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, rollno);
							pst.executeUpdate();
							pst.close();
							con.close();

							txtRollno.setText("");
							txtRollno.requestFocus();

							JOptionPane.showMessageDialog(c, "Deleted Sucessfully");

						} else {
							
							pst.close();
							con.close();

							txtRollno.setText("");
							txtRollno.requestFocus();
							JOptionPane.showMessageDialog(c, "Roll Number Does not exists");
						}

					}

				} catch (NumberFormatException e) {
					txtRollno.setText("");
					txtRollno.requestFocus();
					JOptionPane.showMessageDialog(c, "rollno should contain only Numbers ");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(c, "error :" + e);
				}
			}
		};

		btnBack.addActionListener(a);
		btnDelete.addActionListener(a);

		setTitle("Delete");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}