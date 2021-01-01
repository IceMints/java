/**
 * Project:		Test Grades GUI Array of Objects<br>
 * Description:	This project takes the input of student name and 3 test scores, averages the scores<br>
 * 				and displays the course grade.
 */

public class StudentTestGrades 
{
  //Declare private instance and class variables
  private String studentNameString;
  private int studentTestScore1Integer, studentTestScore2Integer, studentTestScore3Integer, totalScoresInteger;
  private float averageGradeFloat;
  private char courseGradeChar;
  private final int NUMBER_TESTS_INTEGER = 3, TEN_INTEGER = 10;
  
  private final char A_CHAR = 'A', B_CHAR = 'B', C_CHAR = 'C', D_CHAR = 'D', F_CHAR = 'F';
  
  

//Constructors


/**
 * Overload constructors
 */
  public StudentTestGrades() 
  {
    this("Not Given", 0, 0, 0);
  }
  
  public StudentTestGrades(String newNameString, int newGrade1Intger, int newGrade2Intger, int newGrade3Intger )
  {
	  
      setName(newNameString);
      setGrade1(newGrade1Intger);
      setGrade2(newGrade2Intger);
      setGrade3(newGrade3Intger);
      calculateGrades();      
  }
  
  /**
   * set methods to set public variable to private
   */

  private void setName(String newNameString)
  {
	  studentNameString = newNameString;
  }
  
  private void setGrade1(int newGrade1Integer)
  {
	  studentTestScore1Integer = newGrade1Integer;
  }
  private void setGrade2(int newGrade2Integer)
  {
	  studentTestScore2Integer = newGrade2Integer;
  }
  private void setGrade3(int newGrade3Integer)
  {
	  studentTestScore3Integer = newGrade3Integer;
  }
  
  /**
   * calculate method calculates total test scores, then find the average of the 3 test scores
   */
  private void calculateGrades()
  {
	  totalScoresInteger = studentTestScore1Integer + studentTestScore2Integer + studentTestScore3Integer;
	  averageGradeFloat = totalScoresInteger/ NUMBER_TESTS_INTEGER;
	  findCourseGrade();
  }
  
  /**
   * Using the average calculations of the test scores to find the course grade letter equivalent
   * using the findCourseGrade method
   */
  
  private void findCourseGrade()
  {
	  int gradeInteger;
	  
	  gradeInteger = (int) (averageGradeFloat/TEN_INTEGER);
	  	   
	  switch (gradeInteger)
	  {
	  case 10:
	  case 9:
		  courseGradeChar = A_CHAR;
		  break;
	  case 8:
		  courseGradeChar = B_CHAR;
		  break;  
	  case 7:
		  courseGradeChar = C_CHAR;
		  break; 
	  case 6:
		  courseGradeChar = D_CHAR;
		  break; 
	  default:
		  courseGradeChar = F_CHAR;
	  
	  }
	  
  }
  
  //get methods to retrieve values
  public String getName()
  {
	  return studentNameString;
  }
  
  public char getCourseGrade()
  {
	  return courseGradeChar;
  }
  
  public int getTestScore1()
  {
	  return studentTestScore1Integer;
  }
  
  public int getTestScore2()
  {
	  return studentTestScore2Integer;
  }
  
  public int getTestScore3()
  {
	  return studentTestScore3Integer;
  }
  
  public float getAverage()
  {
	  return averageGradeFloat;
  }
 
}

