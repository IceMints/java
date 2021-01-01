
/*
 * Project 			: PayrollArray
 * Class			: PayrollApplication
 * Description		: This project calculates the pay of the employee based on the rate and the
 * 					  hours worked inputed by the user. The project will also calculate the number
 * 					  of employees paid, total pay calculated, average pay calculated.  
 *  				  This class contains the interface, validates, retrieves input from the user
 *  				  displays output
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class PayrollApplication extends JFrame
	implements ActionListener
{
	/***************************************************
	 * Declare objects and fields (instance variables)
	 ****************************************************/
	JLabel companyLabel = new JLabel("A B C   Company");
	JTextField nameTextField = new JTextField(15);
	JTextField hoursTextField = new JTextField(15);
	JTextField rateTextField = new JTextField(15);
	JButton displayButton = new JButton("Display Pay        ");
	JButton summaryButton = new JButton("Display Summary");
	JButton clearButton = new JButton("  Clear textfields     ");
	JTextArea outputTextArea = new JTextArea(20, 30);
	JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
	JPanel mainPanel = new JPanel();
	/***************************************************
	 * Create color and font objects
	 ****************************************************/
	Font companyFont = new Font("Times New Roman", Font.BOLD, 24);
	Color tealColor = new Color(0,128, 128);
	JLabel empCountLabel = new JLabel();
	/***************************************************
	 * Create array of 5 objects of Payroll 
	 ****************************************************/
	Payroll employeePayroll[] = new Payroll[5];
	/***************************************************
	 * Create an integer that index counter for the array 
	 ****************************************************/
	int countEmployeeInteger = 0;
	
	/***************************************************
	 * the main method will create an object of itself and 
	 * set the default close operation
	 ****************************************************/
	 
	public static void main(String[] args) 
	{
		
		PayrollApplication abcPayrollApplication = new PayrollApplication();
		abcPayrollApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end of main method
	
	/**********************************************************
	 * the constructor will call methods to set up the panel 
	 * and frame, and set the listeners
	 *********************************************************/
	//
	public PayrollApplication()
	{
		//call a method to set up the components
		super("Payroll");
		addComponents();
		addListeners();
		
	}//end of the constructor
	
	/**********************************************************
	 * this method will add the components to the panel, add the
	 * panel to the frame, and set the frame properties
	 * *********************************************************/
	public void addComponents()
	{
		
		//Set up the UI components
		summaryButton.setEnabled(false);
		
		mainPanel.add(companyLabel);
		mainPanel.add(new JLabel("Name:     "));
		mainPanel.add(nameTextField);
		mainPanel.add(new JLabel("Hours:     "));
		mainPanel.add(hoursTextField);
		mainPanel.add(new JLabel("Rate:       "));
		mainPanel.add(rateTextField);
		mainPanel.add(displayButton);
		mainPanel.add(summaryButton);
		mainPanel.add(clearButton);
		mainPanel.add(outputScrollPane);
		mainPanel.add(empCountLabel);
		this.add(mainPanel);
		companyLabel.setFont(companyFont);
		companyLabel.setForeground(tealColor);
		mainPanel.setBackground(Color.CYAN);
		
		
		//set defaults for JFrame display
		setSize(400, 400);
		setLocation(100, 100);
		setVisible(true);
		
	}//end of addComponents
	
	/****************************************************************
	* this method will add the listeners to the appropriate components
	 **************************************************************/
	public void addListeners()
	{
		//set the listeners
		displayButton.addActionListener(this);
		rateTextField.addActionListener(this);
		clearButton.addActionListener(this);
		summaryButton.addActionListener(this);
		
	}//end of addListeners
	
	/************************************************************
	 * the actionListener will be called automatically
	 * when an action gets triggered
	 * this method calls either the displayResults method or the
	 * clearTextFields method
	 ************************************************************/
	
	public void actionPerformed(ActionEvent thisEvent)
	{
		
		if (thisEvent.getSource() == displayButton || thisEvent.getSource() == rateTextField)
		{
			//calls the getInput to retrieve information and create the
			//employee object element of the array 
				getInput();
		}
		else if(thisEvent.getSource() == summaryButton)
		{
			//displays the summary of all the employees and a report
			summary();
		}
		else if(thisEvent.getSource() == clearButton)
		{
			//clears the text fields
			clearTextFields();
		}
		
	}
	/************************************************************
	 * the getInput method will retrieve the items
	 * retrieve the name, retrieve the rate and hours within the try/catch
	 * ensures that all the array objects are not full
	 * Creates an object of Payroll
	 * calls the displayPay method to display the name and pay
	 * Enables the summary button
	 * clearTextFields method
	 ************************************************************/
		
	public void getInput()
	{
		//Declare needed local variables and objects
		String nameString;
		int hoursInteger = 0;
		float rateFloat = 0.0f;
		
		
		nameString = nameTextField.getText();
		
		//Retrieve and convert the hours and rate
		try
		{
			hoursInteger = Integer.parseInt(hoursTextField.getText());
			

			try
			{
				rateFloat = Float.parseFloat(rateTextField.getText());
				
				if(countEmployeeInteger < employeePayroll.length)
				{
					
					employeePayroll[countEmployeeInteger] = new  Payroll(nameString,rateFloat, hoursInteger);
					displayPay( );
					
				
					 summaryButton.setEnabled(true);
					 
					 clearTextFields();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No more employees");
					clearTextFields();
				}
				
				
				
				
				
			}//end of inner try

			catch(NumberFormatException exception)
			{
				JOptionPane.showMessageDialog(null, "Rate must be an integer");
				rateTextField.selectAll();
				rateTextField.requestFocus();
		
			}

		}//end of outer try
		
		catch(NumberFormatException exception)
		{
			JOptionPane.showMessageDialog(null, "Hours must be an integer");
			hoursTextField.selectAll();
			hoursTextField.requestFocus();
		
		}
	}
	/************************************************************
	 * the displayPay method will display the employee name and pay
	 * the count for the index array is incremented
	 * clearTextFields method
	 ************************************************************/
	public void displayPay()
	{
		DecimalFormat currencyDecimalFormat = new DecimalFormat("$#,##0.00");
		//Display the result in the text area
		outputTextArea.setText("Employee name: " + employeePayroll[countEmployeeInteger].getName());
		outputTextArea.append("\nThe  pay is: " + currencyDecimalFormat .format(employeePayroll[countEmployeeInteger].getPay()));
		
		countEmployeeInteger++;
		
		
	}//end of displayResults
	
	/************************************************************
	 * the summary method will display the total pay, total employees 
	 * and average pay in a dialog box
	 * summary of the all the employees in the text area
	 ************************************************************/
	public void summary()
	{
		//instantiate the object again
		Payroll myPayroll = new Payroll();
		DecimalFormat currencyDecimalFormat = new DecimalFormat("$#,##0.00");
		
		float totalPayFloat = myPayroll.getTotalPay();
		 int totalEmployeesInteger = myPayroll.getEmpCount();
		float averagePayFloat = myPayroll.getAveragePay();
		
		JOptionPane.showMessageDialog(null, 
				"Total Pay   : " + currencyDecimalFormat.format(totalPayFloat) + '\n' +
				"Total Employees   : " + totalEmployeesInteger + '\n' +
				"Average Pay   : " + currencyDecimalFormat.format(averagePayFloat) + '\n' );
		
		outputTextArea.setText("Summary by Employees\n");

		for (int countInteger = 0; countInteger < countEmployeeInteger; countInteger++)
		{
			outputTextArea.append(
					"Name:  " + employeePayroll[countInteger].getName() + 
					":\t\t" + "Pay:  " + currencyDecimalFormat.format(employeePayroll[countInteger].getPay()) + '\n');
		}

		
	}
	
	/************************************************************
	 * the clear text fields method will clear the textfields and
	 * put the focus back in the name
	 * 
	 ************************************************************/
	public void clearTextFields()
	{
		
		nameTextField.setText("");
		rateTextField.setText("");
		hoursTextField.setText("");
		nameTextField.requestFocus();
	}//end of clearTextFields

}//end of PayrollApplication class











