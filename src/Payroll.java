
/*
 * Project 			: PayrollArray
 * Class			: Payroll
 * Description		: This project calculates the pay of the employee based on the rate and the
 * 					  hours worked inputed by the user. The project will also calculate the number
 * 					  of employees paid, total pay calculated, average pay calculated.  
 *  				  This class contains the calculates the pay, total pay and average 
 *  				  of each employee
 *  				  
 */

public class Payroll 
{
	/***************************************************
	 * Declare private instance and class variables
	 ****************************************************/
	private int hoursInteger;
	private float rateFloat, payFloat;
	private String nameString;
	
	//class variables
	private static int empCountInteger;
	private static float totalPayFloat;
	
	
	/***************************************************
	 * Overloaded constructors
	 ****************************************************/
	public Payroll()
	{
		
	}
	
	public Payroll(String nameNewString, float rateValueFloat, int hoursValueInteger)
	{
		//constructor
		setName(nameNewString);
		setHours(hoursValueInteger);
		setRate(rateValueFloat);
		calculatePay();
		
	}
	
	/***************************************************
	 * setName method to set public variable to private
	 ****************************************************/
	private void setName(String nameNewString)
	{
		//public variables to private
		nameString = nameNewString;
	}
	
	/***************************************************
	 * setHours method to set public variable to private
	 ****************************************************/
	private void setHours(int hoursNewInteger)
	{
		//Check for business rules
		//public variables to private
		
			hoursInteger = hoursNewInteger;
		
	}
	
	/***************************************************
	 * setRate method to set public variable to private
	 ****************************************************/
	private void setRate(float rateNewFloat)
	{
		//public variables to private
		rateFloat = rateNewFloat;
	}
	
	/***************************************************
	 * calculate method calculates the employee pay
	 * the total employee pay, total quantity, and average pay
	 ****************************************************/
	private void calculatePay()
	{
		//calculate the pay of the employee
		//total employees 
		//total of all employees pay
		
			payFloat = rateFloat * hoursInteger;
			empCountInteger++;
			totalPayFloat += payFloat;
			
	}
	/***************************************************
	 * getName to return the name of the employee
	 ****************************************************/
		public String getName()
	{
		return nameString;
	}
		/***************************************************
		 * getPay to return the pay of the employee
		 ****************************************************/
	public float getPay()
	{
		
		return payFloat;
	}
	/***************************************************
	 * getTotalPay to return the pay of all the employee
	 ****************************************************/
	public float getTotalPay()
	{
		return totalPayFloat;
	}
	/***************************************************
	 * getAveragePay to return the average pay of the employees
	 ****************************************************/
	public float getAveragePay()
	{
		return totalPayFloat/empCountInteger;
	}
	/***************************************************
	 * getEmpCount to return the number of  employees
	 ****************************************************/
	 public int getEmpCount()
	{
		//Comment
		return empCountInteger;
	}
}







