
 /**
 * Project:		Student Test Grades GUI Array of Objects
 * Class:		ArrayOfStudents
 * Description:	This project takes the input of student name and 3 test scores, averages the scores
 * 				and displays the course grade.
 * 				Creates the student object
 * 				Display single student test scores, the average and course grade by giving the name
 * 				Display all students name, test scores, the average and course grade 
 */

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class StudentTestGradesArray extends JFrame implements ActionListener
{
	/**
	 * Declare objects and fields (instance variables)
	 */
	
	//Created a Label for the title and font
	JLabel appLabel = new JLabel("Student Course Grade Calculator");
	Font appFont = new Font("Arial", Font.BOLD, 35);
	
	
	/*
	 * Create array of 5 objects of student
	 */

	 StudentTestGrades[] grades = new StudentTestGrades[5]; 
	   
	/*
	 * Create the components for the interface that will take name, 3 test scores
	 * 3 buttons one that will create the student object, display a single student grade, display all grades
	 */
	
	JPanel mainPanel = new JPanel();
    JTextField nameTextField = new JTextField(40);
    JTextField testScore1TextField = new JTextField(5);
    JTextField testScore2TextField = new JTextField(5);
    JTextField testScore3TextField = new JTextField(5);
    
    JButton createStudentButton = new JButton("  Add Student  ");
    JButton displayStudentGradeButton = new JButton("  Display Student Grade  ");
    JButton displayAllGradesButton = new JButton("  Display all Grades  ");
    
    JTextArea outputTextArea = new JTextArea(20,50);
	JScrollPane outputScrollPane = new JScrollPane(outputTextArea); 
    
	/*
	 * referenceInteger will keep the number of objects that are created in the array
	 */
    int referenceInteger = -1;		
    
    /*
     * Created an object of the Student Test Grades Array class and set default close operation on the JFrame
     */

	public static void main(String[] args) 
	{
		
			// created an object of myGrades and allowed to close the JFrame
			StudentTestGradesArray myGrades = new StudentTestGradesArray();
			myGrades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end of main method
			
			/**
			 * Call super to create the title of the Frame<br>
			 * Call the designPanel that will add the components to the mainPanel<br>
			 * Call the addListeners that will add listeners to the objects<br>
			 * Add mainPanel to the JFrame<br>
			 * Add the size and the visibility to the JFrame<br>
			 */
			
			public StudentTestGradesArray()
			{
				super("Grade Calculator");
				//call the method to build the interface on the mainPanel
				designPanel();
				//call the method to add the listeners to the object
				addListeners();
				
				//set defaults for JFrame display
				add(mainPanel);
				setSize(615,515);
				setVisible(true);	
				
			}//end of constructor
			
			/**
			 * this method will add the components to the panel, add the panel
			 * to the frame, and set the frame properties
			 */
			
			public void designPanel()
			{
				displayStudentGradeButton.setEnabled(false);
				displayAllGradesButton.setEnabled(false);
				mainPanel.add(appLabel);
				mainPanel.add(new JLabel("Your Name: "));
				mainPanel.add(nameTextField);
				mainPanel.add(new JLabel("Grade of Test 1: "));
				mainPanel.add(testScore1TextField);
				mainPanel.add(new JLabel("Grade of Test 2: "));
				mainPanel.add(testScore2TextField);
				mainPanel.add(new JLabel("Grade of Test 3: "));
				mainPanel.add(testScore3TextField);
				mainPanel.add(createStudentButton);
				mainPanel.add(displayStudentGradeButton);
				mainPanel.add(displayAllGradesButton);
				mainPanel.add(outputScrollPane);
				
				appLabel.setFont(appFont);
				mainPanel.setBackground(Color.CYAN);
				
				
			}//end of designPanel
			
			/**
			 * this method will add the listeners to the appropriate designPanel components
			 */
			public void addListeners()
			{
				createStudentButton.addActionListener(this);
				displayStudentGradeButton.addActionListener(this);
				displayAllGradesButton.addActionListener(this);
			}//end of addListeners
			
			/**
			 * the actionListener will be called automatically
			 * when an action gets triggered
			 */
			public void actionPerformed(ActionEvent event)
			{
				Object sourceObject = event.getSource();
				
				 if(sourceObject == createStudentButton)
				{
					if(validation())
					{
						//create the student object element of the array
						createStudent();
					}
				}
				else if(sourceObject == displayStudentGradeButton)
				{
					//displays the grades of a single student with average and course grade
					displayStudentGrade();
				}
				else if(sourceObject == displayAllGradesButton)
				{
					//displays the summary of all the students with average and course grade
					displayAllGrades();
				}
			}//end of actionPerformed
			
			/**
			 * the createStudent method will retrieve the grades
			 * Creates an object of StudentTestGrades
			 * and limit the objects of students created to 5
			 */
			public void createStudent()
			{
				String nameString;
			    int testScore1Integer, testScore2Integer, testScore3Integer;
				// get input
				nameString = nameTextField.getText();
				testScore1Integer = Integer.parseInt(testScore1TextField.getText());
				testScore2Integer = Integer.parseInt(testScore2TextField.getText());
				testScore3Integer = Integer.parseInt(testScore3TextField.getText());
				try
				{
					referenceInteger++;
					if(referenceInteger < grades.length)
					{	
						grades[referenceInteger] = new StudentTestGrades(nameString, testScore1Integer, testScore2Integer, testScore3Integer);
						clearTextFields();
						displayStudentGradeButton.setEnabled(true);
						displayAllGradesButton.setEnabled(true);
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Cannot add more students");
					}
				}
				catch(ArrayIndexOutOfBoundsException error)
				{
					JOptionPane.showMessageDialog(null, "Cannot add more students");
				}
				
			}//end createStudent

			/**
			 * This will display a single students' scores, the average and course grade when it
			 * finds the name
			 */
			public void displayStudentGrade()
			{
				String nameString;
				int findInteger;
				
				nameString = nameTextField.getText();
				
				if(!nameString.equals(""))
				{
					findInteger = checkStudent();
					
					if( findInteger != -1)
					{
						outputTextArea.setText(
								"Name: " + grades[findInteger].getName() + "\n" +
						        "Test Score 1:    " + grades[findInteger].getTestScore1() + "\n" +
						        "Test Score 2:    " + grades[findInteger].getTestScore2() + "\n" +
						        "Test Score 3:    " + grades[findInteger].getTestScore3() + "\n" +
						        "Average:         " + grades[findInteger].getAverage() + "\n" +
						        "Course Grade:    " + grades[findInteger].getCourseGrade() + "\n" );
					}
					nameTextField.setText("");
					nameTextField.requestFocus();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please enter a name");
					nameTextField.requestFocus();
				}
				
			}//end displayStudentGrade
			
			/**
			 * this method will display a summary of the 5 objects of student
			 */
			public void displayAllGrades()
			{
				outputTextArea.setText("Name\t" + "Test Score 1\t" + "Test Score 2\t" + "Test Score 3\t"
			                            + "Average\t"  + "Course Grade\t" +"\n");
				for(int indexInteger = 0; indexInteger <= referenceInteger; indexInteger++)
				{
					outputTextArea.append(
							" " + grades[indexInteger].getName() + "\t" + 
							" " +grades[indexInteger].getTestScore1() + "\t" +
							" " +grades[indexInteger].getTestScore2() + "\t" +
							" " +grades[indexInteger].getTestScore3() + "\t" +
							" " +grades[indexInteger].getAverage() + "\t" +
							" " +grades[indexInteger].getCourseGrade() + "\n" );
				}
			}//end displayAllGrades
			
			/**
			 * this method checks for the name in the array
			 */
			public int checkStudent()
			{
				String nameString, arrayNameString;
				boolean foundBoolean = false;
				int indexInteger = 0;
				
				nameString = nameTextField.getText();
				
				while(!foundBoolean && indexInteger <= referenceInteger)
				{
					arrayNameString = grades[indexInteger].getName();
					if(nameString.equalsIgnoreCase(arrayNameString))
					{
						foundBoolean = true;
					}
					else
					{
						indexInteger++;
					}
				}
				
				if(foundBoolean)
				{
					return indexInteger;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "There is no record with this name.");
					return -1;
				}
				
				
				
			}//end checkStudent
			
			/**
			 * this method checks to make sure integers are enter for the score textfields
			 */
			public boolean validation()
			{
				boolean validateBoolean = false;
				if(!nameTextField.getText().equals(""))
				{
					try
					{
						if(Integer.parseInt(testScore1TextField.getText())>=0)
						{
							try
							{
								if(Integer.parseInt(testScore2TextField.getText())>=0)
								{
									try
									{
										if(Integer.parseInt(testScore3TextField.getText())>=0)
										{
										   validateBoolean = true;
										}
									}
									catch(NumberFormatException error)
									{
										JOptionPane.showMessageDialog(null, "Please enter a test score 3");
										testScore3TextField.selectAll();
										testScore3TextField.requestFocus();
									}
								}
							}
							catch(NumberFormatException error)
							{
								JOptionPane.showMessageDialog(null, "Please enter a test score 2");
								testScore2TextField.selectAll();
								testScore2TextField.requestFocus();
							}
						}
					}
					catch(NumberFormatException error)
					{
						JOptionPane.showMessageDialog(null, "Please enter a test score1");
						testScore1TextField.selectAll();
						testScore1TextField.requestFocus();
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please enter a name");
					nameTextField.requestFocus();
				}
				
				return validateBoolean;
			}//end validation
			
			/**
			 * this method clears the text fields and refocus the cursor back to the name 
			 * field and ready to instantiate another object of student
			 */
			public void clearTextFields()
			{
				nameTextField.setText("");
				testScore1TextField.setText("");
				testScore2TextField.setText("");
				testScore3TextField.setText("");
				nameTextField.requestFocus();
			}//end clearTextFields


	}//end StudentTestGradesArray class
