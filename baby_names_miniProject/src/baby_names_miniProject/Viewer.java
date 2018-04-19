package baby_names_miniProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Viewer implements ActionListener, FocusListener {

	BabyBirths bb = new BabyBirths(); //object
	
	JFrame frame;	
	JFrame infoFrame; //Frame contains information about program, author and contact
	
	JTextField nameField;
	JTextField yearField;
	JTextField newYearField;
	JTextField genderField;
	
	JTextArea nameInYear;
	
	Viewer() {
		
		JFrame frame = new JFrame("Baby Names By Year");
		JFrame infoFrame = new JFrame("About");
	

		JTextField nameField = new JTextField("Enter Your Name", 8);
		JTextField yearField = new JTextField("Enter Birth Year", 8);
		JTextField newYearField = new JTextField("Enter Year to Examine", 8);
		JTextField genderField = new JTextField("Enter Your Gender", 8);
		
		JButton enterButton = new JButton("Analyze");
		JButton infoButton = new JButton("About This App");

		String desc = "Please fill in the text fields.";
		
		String about = "This is one of the Coursera mini-projects. It finds what a persons name "
				+ "would be if they were born in another year, based on the ranking(popularity) of "
				+ "their name in the year they were born. User can check what name they would of "
				+ "had in years from 1880 to 2014. CSV files are from the US Government. \n\nAuthor :"
				+ " Zev Yirmiyahu \nContact: zy@zevyirmiyahu.com";
		
		JLabel title = new JLabel("Name Analysis");
		JLabel description = new JLabel(desc);
				
		JTextArea aboutArea = new JTextArea(about, 20, 20); //info about the program
		JTextArea nameInYear = new JTextArea("");
		
		Box box = Box.createVerticalBox(); //create box to put components

		//Set button colors
		enterButton.setForeground(Color.WHITE);
		enterButton.setBackground(Color.DARK_GRAY);
		infoButton.setForeground(Color.WHITE);
		infoButton.setBackground(Color.DARK_GRAY);

		//Set Font
		Font standardFont = new Font("Arial", Font.PLAIN, 20);
		title.setFont(new Font("Arial", Font.BOLD, 40));
		description.setFont(new Font("Arial", Font.PLAIN, 18));
		
		enterButton.setFont(standardFont);
		infoButton.setFont(standardFont);
		
		nameField.setFont(standardFont);
		yearField.setFont(standardFont);
		newYearField.setFont(standardFont);
		genderField.setFont(standardFont);
		
		//Set JTextArea
		aboutArea.setEditable(false);
		aboutArea.setLineWrap(true);
		aboutArea.setWrapStyleWord(true);
		aboutArea.setBorder(null);
		aboutArea.setBackground(Color.GRAY);
		aboutArea.setFont(new Font("Arial", Font.BOLD, 17));
		
		nameInYear.setEditable(false);
		nameInYear.setLineWrap(true);
		nameInYear.setWrapStyleWord(true);
		nameInYear.setBorder(null);
		nameInYear.setBackground(Color.GRAY);
		nameInYear.setFont(new Font("Arial", Font.BOLD, 20));
		
		//Add actionListeners
		nameField.addFocusListener(this);
		yearField.addFocusListener(this);
		newYearField.addFocusListener(this);
		genderField.addFocusListener(this);
		
		enterButton.addActionListener(this);
		infoButton.addActionListener(this);
		
		box.add(Box.createVerticalStrut(30)); //spacing
		box.add(nameField);
		box.add(Box.createVerticalStrut(8)); //spacing
		box.add(yearField);
		box.add(Box.createVerticalStrut(8)); //spacing
		box.add(newYearField);
		box.add(Box.createVerticalStrut(8)); //spacing
		box.add(genderField);
		box.add(Box.createVerticalStrut(13)); //spacing

		box.add(enterButton);
		box.add(Box.createVerticalStrut(10)); //spacing
		box.add(infoButton);
				
		//Add components to JFrame
		frame.add(title);
		frame.add(description);
		
		frame.add(box);
		
		frame.add(nameInYear);
		
		infoFrame.add(aboutArea);

		//Set JFrame
		frame.setSize(400, 400);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		infoFrame.setSize(300, 300);
		infoFrame.setResizable(false);
		infoFrame.getContentPane().setBackground(Color.GRAY);
		infoFrame.setLocationRelativeTo(frame);
		infoFrame.setLayout(new FlowLayout());
		infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		infoFrame.setVisible(false);

		//Set to global for actionListener
		this.infoFrame = infoFrame;
		this.frame = frame;
		
		this.nameField = nameField;
		this.yearField = yearField;
		this.newYearField = newYearField;
		this.genderField = genderField;
		
		this.nameInYear = nameInYear;
		
	}
	
	public void clearTextField(ActionEvent ae) {
		if(ae.getActionCommand().equals("Enter Your Name")) {
			nameField.setText("");
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		clearTextField(ae);
		
		if(ae.getActionCommand().equals("Analyze")) {
			try {
				
				String name = nameField.getText();
				String gender = genderField.getText();
				
				int birthYear = Integer.parseInt(yearField.getText());
				int newYear = Integer.parseInt(newYearField.getText());
								
				String str = bb.whatIsNameInYear(name, birthYear, newYear, gender);
				nameInYear.setText(""); //clears text each time
				nameInYear.setText(str); //set answer
				
			} catch(NumberFormatException e) {
				nameInYear.setText("Invalid, year must be an integer.");
				System.err.println("uh-oh!");
				return;
			} catch(Exception e) {
				nameInYear.setText("");
				nameInYear.setText("ERROR: Invalid input.");
			}
		}
		else if(ae.getActionCommand().equals("About This App")) {
			infoFrame.setVisible(true);
			System.out.println("working");
		}
	}
	
	// Clears JTextField instructions
	public void focusGained(FocusEvent fe) {
		
		if(fe.getComponent().equals(nameField) && nameField.getText().equals("Enter Your Name")) {
			nameField.setText("");
		}
		else if(fe.getComponent().equals(yearField) && yearField.getText().equals("Enter Birth Year")) {
			yearField.setText("");
		}
		else if(fe.getComponent().equals(newYearField) && newYearField.getText().equals("Enter Year to Examine")) {
			newYearField.setText("");
		}
		else if(fe.getComponent().equals(genderField) && genderField.getText().equals("Enter Your Gender")) {
			genderField.setText("");
		}
		
	}

	//Writes JTextField instructions back if left blank
	public void focusLost(FocusEvent fe) {
		
		if(fe.getComponent().equals(nameField) && nameField.getText().equals("")) {
			nameField.setText("Enter Your Name");
		}
		else if(fe.getComponent().equals(yearField) && yearField.getText().equals("")) {
			yearField.setText("Enter Birth Year");
		}
		else if(fe.getComponent().equals(newYearField) && newYearField.getText().equals("")) {
			newYearField.setText("Enter Year to Examine");
		}
		else if(fe.getComponent().equals(genderField) && genderField.getText().equals("")) {
			genderField.setText("Enter Your Gender");
		}
		
	}
}
