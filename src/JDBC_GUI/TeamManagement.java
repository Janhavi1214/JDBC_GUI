package JDBC_GUI;

//GUI
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
//Event Handle
import java.awt.event.*;
//JDBC
import java.sql.*;
public class TeamManagement extends JFrame implements ActionListener{

	//GUI components
	JTextField tfName, tfAge, tfGender, tfCourse, tfYear, tfEmail, tfPhone, tfAddress, tfGpa;
	
	JButton btnInsert, btnFetch, btnUpdate, btnDelete, btnOrderBy;
	
	JTable table;
	
	DefaultTableModel model;
	
	JTextField tfSearch1, tfSearch2;

	JComboBox<String> cbSearchType;
	
	JButton btnSearch;
	
	//JDBC
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	TeamManagement(){
		setTitle("Team Management System");
		setSize(900, 600);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Top panel for the form
		JPanel formPanel = new JPanel(new GridLayout(10,2,5,5));
		
		formPanel.add(new JLabel("Name"));
		tfName = new JTextField();
		formPanel.add(tfName);
		
		formPanel.add(new JLabel("Age"));
		tfAge = new JTextField();
		formPanel.add(tfAge);
		
		formPanel.add(new JLabel("Gender"));
		tfGender = new JTextField();
		formPanel.add(tfGender);
		
		formPanel.add(new JLabel("Course"));
		tfCourse = new JTextField();
		formPanel.add(tfCourse);
		
		formPanel.add(new JLabel("Year"));
		tfYear = new JTextField();
		formPanel.add(tfYear);
		
		formPanel.add(new JLabel("Email"));
		tfEmail = new JTextField();
		formPanel.add(tfEmail);
		
		formPanel.add(new JLabel("Phone"));
		tfPhone = new JTextField();
		formPanel.add(tfPhone);
		
		formPanel.add(new JLabel("Address"));
		tfAddress = new JTextField();
		formPanel.add(tfAddress);
		
		formPanel.add(new JLabel("Gpa"));
		tfGpa = new JTextField();
		formPanel.add(tfGpa);
		
		//Buttons
		btnInsert = new JButton("Insert");
		btnFetch = new JButton("Fetch");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnOrderBy = new JButton("Order By");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(btnInsert);
		buttonPanel.add(btnFetch);
		buttonPanel.add(btnUpdate);
		buttonPanel.add(btnDelete);
		buttonPanel.add(btnOrderBy);
		
		cbSearchType = new JComboBox<>(new String[] {"WHERE Gpa >" , "LIKE Name", "BETWEEN Gpa"});
		
		tfSearch1 = new JTextField(10);
		tfSearch2 = new JTextField(10);
		
		btnSearch = new JButton("Search");
		
		JPanel searchPanel = new JPanel();
		searchPanel.add(new JLabel("Search Type"));
		searchPanel.add(cbSearchType);
		searchPanel.add(tfSearch1);
		searchPanel.add(tfSearch2);
		searchPanel.add(btnSearch);
		
		add(searchPanel, BorderLayout.SOUTH);
		
		formPanel.add(buttonPanel);
		
		add(formPanel, BorderLayout.NORTH);
		
		//Table
		model = new DefaultTableModel();
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		//Events generation
		btnInsert.addActionListener(this);
		btnFetch.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnOrderBy.addActionListener(this);
		
		
		//Database connection
		try {
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/my_new_db", "root", "janhavi@2004");
			
			stmt = con.createStatement();
			JOptionPane.showMessageDialog(this, "Connected to Database");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "ERROR : " + e.getMessage());
			e.printStackTrace();
		}
		
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		try {
			if(ae.getSource() == btnInsert) {
				String q = "INSERT INTO my_team_ois "
				        + "(name, age, gender, course, year, email, phone, address, gpa) VALUES ("
				        + "'" + tfName.getText() + "', "
				        + tfAge.getText() + ", "
				        + "'" + tfGender.getText() + "', "
				        + "'" + tfCourse.getText() + "', "
				        + "'" + tfYear.getText() + "', "
				        + "'" + tfEmail.getText() + "', "
				        + "'" + tfPhone.getText() + "', "
				        + "'" + tfAddress.getText() + "', "
				        + tfGpa.getText()
				        + ")";
				
				stmt.executeUpdate(q);
				}
			/*CREATE TABLE my_team_ois(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(20),
    course VARCHAR(100),
    year INT,
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    gpa DECIMAL(3,2));*/
			
			else if(ae.getSource() == btnFetch) {
				rs = stmt.executeQuery("SELECT * FROM my_team_ois");
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int cols = rsmd.getColumnCount();
				model.setRowCount(0);
				model.setColumnCount(0);
				
				for(int i=1;i<=cols;i++) {
					model.addColumn(rsmd.getColumnName(i));
				}
				while(rs.next()) {
					Object[] row = new Object[cols];
					
					for(int i=1; i<=cols;i++) {
						row[i-1] = rs.getObject(i);
					}
					model.addRow(row);
				}
			}
			
			else if(ae.getSource() == btnUpdate) {
				String q = "UPDATE my_team_ois SET Gpa ="
						+ tfGpa.getText() + 
						" WHERE name = '" + tfName.getText() + 
						"';";
				stmt.executeUpdate(q);
				
				JOptionPane.showMessageDialog(this, "Updated");
			}
			else if(ae.getSource() == btnDelete) {
				String q = "DELETE FROM my_team_ois"
						+ " WHERE name = '" + tfName.getText() + "';";
				
				stmt.executeUpdate(q);
				JOptionPane.showMessageDialog(this, "Record Deleted");
				
			}
			else if(ae.getSource() == btnOrderBy) {
				rs = stmt.executeQuery("SELECT * FROM"
						+ " my_team_ois ORDER BY Gpa DESC;");
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int cols = rsmd.getColumnCount();
				model.setRowCount(0);
				model.setColumnCount(0);
				
				for(int i=1;i<=cols;i++) {
					model.addColumn(rsmd.getColumnName(i));
				}
				while(rs.next()) {
					Object[] row = new Object[cols];
					
					for(int i=1; i<=cols;i++) {
						row[i-1] = rs.getObject(i);
					}
					model.addRow(row);
				}
			}
			else if(ae.getSource() == btnSearch) {
				String type = (String)cbSearchType.getSelectedItem();
				String q = "";
				
				if(type.equals("WHERE Gpa >")) {
					q = "SELECT * FROM my_team_ois "
							+ " WHERE Gpa > " + tfSearch1.getText();
				}
				else if(type.equals("LIKE Name")) {
					q = "SELECT * FROM my_team_ois WHERE "
					        + "name LIKE '%" + tfSearch1.getText() + "%'";
				}
				else if(type.equals("BETWEEN Gpa")) {
					q = "SELECT * FROM my_team_ois WHERE "
							+ " Gpa BETWEEN " 
							+ tfSearch1.getText() + " AND " 
							+ tfSearch2.getText() + ";"; 
				}
				rs = stmt.executeQuery(q);
				
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int cols = rsmd.getColumnCount();
				model.setRowCount(0);
				model.setColumnCount(0);
				
				for(int i=1;i<=cols;i++) {
					model.addColumn(rsmd.getColumnName(i));
				}
				while(rs.next()) {
					Object[] row = new Object[cols];
					
					for(int i=1; i<=cols;i++) {
						row[i-1] = rs.getObject(i);
					}
					model.addRow(row);
				}
			}

		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		
		new TeamManagement();
	}

}
