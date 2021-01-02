
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.FacesContext;

/*
CISP24 PROJECT
Data Manipulation with the Database
 */

/**
 *
 * @author Gigi Hui & David Han
 */
public class DatabaseOperation {
    public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
    
    
    //method to establish a database connection
    public static Connection getConnection(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String db_url = "jdbc:mysql://localhost:3307/employees?zeroDateTimeBehavior=convertToNull",
                    db_userName = "root",
                    db_password = "Kct#125!";
            connObj = DriverManager.getConnection (db_url, db_userName, db_password);
        }
        catch(Exception sqlException)
        {
            sqlException.printStackTrace();
        }
        return connObj;
    }
        
        //method to fetch employee records from database
      public static ArrayList<EmployeeBean> getEmployeesListFromDB(){
          ArrayList<EmployeeBean> employeesList = new ArrayList<EmployeeBean>();
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employee "
                      + "INNER JOIN departments ON employee.department_id = departments.department_id "
                      + "INNER JOIN projects ON employee.project_id = projects.project_id "
                      + "ORDER BY last_name, first_name");
              while (resultSetObj.next())
              {
                  EmployeeBean empObj = new EmployeeBean();
                  empObj.setEmployeeID(resultSetObj.getInt("employee_id"));
                  empObj.setFirstName(resultSetObj.getString("first_name"));
                  empObj.setLastName(resultSetObj.getString("last_name"));
                  empObj.setSalary(resultSetObj.getDouble("salary"));
                  empObj.setPosition(resultSetObj.getString("position"));
                  empObj.setAddress1(resultSetObj.getString("address1"));
                  empObj.setAddress2(resultSetObj.getString("address2"));
                  empObj.setCity(resultSetObj.getString("city"));
                  empObj.setState(resultSetObj.getString("state"));
                  empObj.setZipCode(resultSetObj.getString("zipcode"));
                  empObj.setDepartmentID(resultSetObj.getInt("department_id"));
                  empObj.setDepartmentName(resultSetObj.getString("department_name"));
                  empObj.setProjectID(resultSetObj.getInt("project_id"));
                  empObj.setProjectName(resultSetObj.getString("project_name"));
                  employeesList.add(empObj);
              }
              System.out.println("Total Records Fetched: " + employeesList.size());
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return employeesList;
      }
      
        //method to fetch department records from database
      public static ArrayList<EmployeeBean> getDepartmentsListFromDB(){
          ArrayList<EmployeeBean> departmentsList = new ArrayList<EmployeeBean>();
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.departments ORDER BY department_name");
              while (resultSetObj.next())
              {
                  EmployeeBean empObj = new EmployeeBean();
                  empObj.setDepartmentID(resultSetObj.getInt("department_id"));
                  empObj.setDepartmentName(resultSetObj.getString("department_name"));
                  departmentsList.add(empObj);
              }
              System.out.println("Total Records Fetched: " + departmentsList.size());
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return departmentsList;
      }
      
        //method to fetch project records from database
      public static ArrayList<EmployeeBean> getProjectsListFromDB(){
          ArrayList<EmployeeBean> projectsList = new ArrayList<EmployeeBean>();
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.projects ORDER BY project_name");
              while (resultSetObj.next())
              {
                  EmployeeBean empObj = new EmployeeBean();
                  empObj.setProjectID(resultSetObj.getInt("project_id"));
                  empObj.setProjectName(resultSetObj.getString("project_name"));
                  projectsList.add(empObj);
              }
              System.out.println("Total Records Fetched: " + projectsList.size());
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return projectsList;
      }
      
     
      //method used to save new employee record(s) in database
      public static String saveEmployeeDetailsInDB (EmployeeBean newEmployeeObj){
          int saveResult = 0;
          String navigationResult = "";
          try{
              pstmt = getConnection().prepareStatement("INSERT INTO employees.employee"
                      + "(first_name, last_name, salary, position,"
                      + "address1, address2, city, state, zipcode, project_id, department_id) "
                      + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
              pstmt.setString(1, newEmployeeObj.getFirstName());
              pstmt.setString(2, newEmployeeObj.getLastName());
              pstmt.setDouble(3, newEmployeeObj.getSalary());
              pstmt.setString(4, newEmployeeObj.getPosition());
              pstmt.setString(5, newEmployeeObj.getAddress1());
              pstmt.setString(6, newEmployeeObj.getAddress2());
              pstmt.setString(7, newEmployeeObj.getCity());
              pstmt.setString(8, newEmployeeObj.getState());
              pstmt.setString(9, newEmployeeObj.getZipCode());
              pstmt.setInt(10, newEmployeeObj.getProjectID());
              pstmt.setInt(11, newEmployeeObj.getDepartmentID());
              
             saveResult = pstmt.executeUpdate();
             
             connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          if (saveResult !=0)
          {
              navigationResult = "employeesList.xhtml?faces-redirect=true";
          }
          else
          {
              navigationResult = "createEmployee.xhtml?faces-redirect=true";
          }
          return navigationResult;
      }
      
       //method used to save new project record(s) in database
      public static String saveProjectDetailsInDB (EmployeeBean newProjectObj){
          int saveResult = 0;
          String navigationResult = "";
          try{
              pstmt = getConnection().prepareStatement("INSERT INTO employees.projects (project_name) values (?)");
              pstmt.setString(1, newProjectObj.getProjectName());
              
             saveResult = pstmt.executeUpdate();
             
             connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          if (saveResult !=0)
          {
              navigationResult = "projectsList.xhtml?faces-redirect=true";
          }
          else
          {
              navigationResult = "createProject.xhtml?faces-redirect=true";
          }
          return navigationResult;
      }
      
       //method used to save new department record(s) in database
      public static String saveDepartmentDetailsInDB (EmployeeBean newDepartmentObj){
          int saveResult = 0;
          String navigationResult = "";
          try{
              pstmt = getConnection().prepareStatement("INSERT INTO employees.departments (department_name) values (?)");
              pstmt.setString(1, newDepartmentObj.getDepartmentName());
             
              
             saveResult = pstmt.executeUpdate();
             
             connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          if (saveResult !=0)
          {
              navigationResult = "departmentsList.xhtml?faces-redirect=true";
          }
          else
          {
              navigationResult = "createDepartment.xhtml?faces-redirect=true";
          }
          return navigationResult;
      }
      
      //method used to edit employee record in database
      public static String editEmployeeRecordInDB(int employeeID)
      {
          EmployeeBean editRecord = null;
          System.out.println("editEmployeeRecordInDB() : Employee ID: " + employeeID);
          
          //setting the employee details in session
          Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
          
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.employee where employee_ID = " + employeeID);
              if (resultSetObj != null){
                  resultSetObj.next();
                  editRecord = new EmployeeBean();
                  editRecord.setEmployeeID(resultSetObj.getInt("employee_id"));
                  editRecord.setFirstName(resultSetObj.getString("first_name"));
                  editRecord.setLastName(resultSetObj.getString("last_name"));
                  editRecord.setSalary(resultSetObj.getDouble("salary"));
                  editRecord.setPosition(resultSetObj.getString("position"));
                  editRecord.setAddress1(resultSetObj.getString("address1"));
                  editRecord.setAddress2(resultSetObj.getString("address2"));
                  editRecord.setCity(resultSetObj.getString("city"));
                  editRecord.setState(resultSetObj.getString("state"));
                  editRecord.setZipCode(resultSetObj.getString("zipcode"));
                  editRecord.setDepartmentID(resultSetObj.getInt("department_id"));
                  editRecord.setProjectID(resultSetObj.getInt("project_id"));
              }
              sessionMapObj.put ("editRecordObj", editRecord);
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return "/editEmployee.xhtml?faces-redirect=true";
      }
      
       //method used to edit project record in database
      public static String editProjectRecordInDB(int projectID)
      {
          EmployeeBean editRecord = null;
          System.out.println("editProjectRecordInDB() : Project ID: " + projectID);
          
          //setting the project details in session
          Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
          
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.projects where project_ID = " + projectID);
              if (resultSetObj != null){
                  resultSetObj.next();
                  editRecord = new EmployeeBean();
                  editRecord.setProjectID(resultSetObj.getInt("project_id"));
                  editRecord.setProjectName(resultSetObj.getString("project_name"));
                  
              }
              sessionMapObj.put ("editRecordObj", editRecord);
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return "/editProject.xhtml?faces-redirect=true";
      }
      
      //Method used to edit project record in database with projectName as parameter
      public static String editProjectRecordInDB(String projectName)
      {
          EmployeeBean editRecord = null;
          System.out.println("editProjectRecordInDB() : Project Name: " + projectName);
          
          //setting the project details in session
          Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
          
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.projects where project_name = " + projectName);
              if (resultSetObj != null){
                  resultSetObj.next();
                  editRecord = new EmployeeBean();
                  editRecord.setProjectID(resultSetObj.getInt("project_id"));
                  editRecord.setProjectName(resultSetObj.getString("project_name"));
                  
              }
              sessionMapObj.put ("editRecordObj", editRecord);
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return "/editProject.xhtml?faces-redirect=true";
      }
      
      
      
       //method used to edit department record in database
      public static String editDepartmentRecordInDB(int departmentID)
      {
          EmployeeBean editRecord = null;
          System.out.println("editDepartmentRecordInDB() : Department ID: " + departmentID);
          
          //setting the department details in session
          Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
          
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.departments where department_ID = " + departmentID);
              if (resultSetObj != null){
                  resultSetObj.next();
                  editRecord = new EmployeeBean();
                  editRecord.setDepartmentID(resultSetObj.getInt("department_id"));
                  editRecord.setDepartmentName(resultSetObj.getString("department_name"));
                  
              }
              sessionMapObj.put ("editRecordObj", editRecord);
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return "/editDepartment.xhtml?faces-redirect=true";
      }
      
      //method used to edit department record in database
      //@param departmentName
      public static String editDepartmentRecordInDB(String departmentName)
      {
          EmployeeBean editRecord = null;
          System.out.println("editDepartmentRecordInDB() : Department Name: " + departmentName);
          
          //setting the department details in session
          Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
          
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.departments where department_name = " + departmentName);
              if (resultSetObj != null){
                  resultSetObj.next();
                  editRecord = new EmployeeBean();
                  editRecord.setDepartmentID(resultSetObj.getInt("department_id"));
                  editRecord.setDepartmentName(resultSetObj.getString("department_name"));
                  
              }
              sessionMapObj.put ("editRecordObj", editRecord);
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return "/editDepartment.xhtml?faces-redirect=true";
      }
      
      
      
      //method use to update employee record in database
      public static String updateEmployeeDetailsInDB(EmployeeBean updateEmployeeObj){
          try{
              pstmt = getConnection().prepareStatement("UPDATE employees.employee set first_name=?, last_name=?,"
                      + "salary=?, position=?, address1=?, address2=?, city=?, state=?, zipcode=?, department_id=?, project_id=? WHERE employee_id = ?");
              pstmt.setString(1, updateEmployeeObj.getFirstName());
              pstmt.setString(2, updateEmployeeObj.getLastName());
              pstmt.setDouble(3, updateEmployeeObj.getSalary());
              pstmt.setString(4, updateEmployeeObj.getPosition());
              pstmt.setString(5, updateEmployeeObj.getAddress1());
              pstmt.setString(6, updateEmployeeObj.getAddress2());
              pstmt.setString(7, updateEmployeeObj.getCity());
              pstmt.setString(8, updateEmployeeObj.getState());
              pstmt.setString(9, updateEmployeeObj.getZipCode());
              pstmt.setInt(10, updateEmployeeObj.getDepartmentID());
              pstmt.setInt(11, updateEmployeeObj.getProjectID());
              pstmt.setInt(12, updateEmployeeObj.getEmployeeID());
              pstmt.executeUpdate();
              connObj.close();
          }
              
          catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return "/employeesList.xhtml?faces-redirect=true";
      }
      
       //method use to update project record in database
      public static String updateProjectDetailsInDB(EmployeeBean updateProjectObj){
          try{
              pstmt = getConnection().prepareStatement("UPDATE employees.projects set project_name=? WHERE project_id = ?");
              pstmt.setString(1, updateProjectObj.getProjectName());
              pstmt.setInt(2, updateProjectObj.getProjectID());
             
              pstmt.executeUpdate();
              connObj.close();
          }
              
          catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return "/projectsList.xhtml?faces-redirect=true";
      }
      
        //method use to update department record in database
      public static String updateDepartmentDetailsInDB(EmployeeBean updateDepartmentObj){
          try{
              pstmt = getConnection().prepareStatement("UPDATE employees.departments set department_name=? WHERE department_id = ?");
              pstmt.setString(1, updateDepartmentObj.getDepartmentName());
              pstmt.setInt(2, updateDepartmentObj.getDepartmentID());
              pstmt.executeUpdate();
              connObj.close();
          }
              
          catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return "/departmentsList.xhtml?faces-redirect=true";
      }
      
     
      
      //method use to delete employee record from database
      public static String deleteEmployeeRecordInDB(int employeeID){
         try
          {
              pstmt = getConnection().prepareStatement("delete from employees.employee where employee_id =" + employeeID);
              pstmt.executeUpdate();
              connObj.close();
          }
          catch(Exception sqlException)
          {
		sqlException.printStackTrace();
	  }
		return "/employeesList.xhtml?faces-redirect=true";
      }
      
       //method use to delete project record from database
      public static String deleteProjectRecordInDB(int projectID){
         try
          {
              pstmt = getConnection().prepareStatement("delete from employees.projects where project_id =" + projectID);
              pstmt.executeUpdate();
              connObj.close();
          }
          catch(Exception sqlException)
          {
		sqlException.printStackTrace();
	  }
		return "/projectsList.xhtml?faces-redirect=true";
      }
      
       //method use to delete department record from database
      public static String deleteDepartmentRecordInDB(int departmentID){
         try
          {
              pstmt = getConnection().prepareStatement("delete from employees.departments where department_id =" + departmentID);
              pstmt.executeUpdate();
              connObj.close();
          }
          catch(Exception sqlException)
          {
		sqlException.printStackTrace();
	  }
		return "/departmentsList.xhtml?faces-redirect=true";
      }
      
      
      
        //method use to check password from database
        public static boolean checkPassword(String un, String pwd)
      {
          boolean checkBoolean = false;
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employees.users "
                      + "WHERE user_name = \"" + un + "\";");
              if(resultSetObj.next())
              {
                if(resultSetObj.getString("password").equals(pwd))
                    checkBoolean = true;
              }
            
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          
          return checkBoolean;
      }
    
        //method used for project searches
        public static ArrayList<EmployeeBean> getProjectResultFromDB(){
          ArrayList<EmployeeBean> projectResult = new ArrayList<EmployeeBean>();
          try
          {
              stmtObj = getConnection().createStatement();
              resultSetObj = stmtObj.executeQuery("SELECT * FROM employee "
                      + "INNER JOIN departments ON employee.department_id = departments.department_id "
                      + "INNER JOIN projects ON employee.project_id = projects.project_id WHERE project_name = 'TURFY'"
                      + "ORDER BY last_name, first_name");
              while (resultSetObj.next())
              {
                  EmployeeBean projObj = new EmployeeBean();
                  projObj.setEmployeeID(resultSetObj.getInt("employee_id"));
                  projObj.setFirstName(resultSetObj.getString("first_name"));
                  projObj.setLastName(resultSetObj.getString("last_name"));
                  projObj.setSalary(resultSetObj.getDouble("salary"));
                  projObj.setPosition(resultSetObj.getString("position"));
                  projObj.setAddress1(resultSetObj.getString("address1"));
                  projObj.setAddress2(resultSetObj.getString("address2"));
                  projObj.setCity(resultSetObj.getString("city"));
                  projObj.setState(resultSetObj.getString("state"));
                  projObj.setZipCode(resultSetObj.getString("zipcode"));
                  projObj.setDepartmentID(resultSetObj.getInt("department_id"));
                  projObj.setDepartmentName(resultSetObj.getString("department_name"));
                  projObj.setProjectID(resultSetObj.getInt("project_id"));
                  projObj.setProjectName(resultSetObj.getString("project_name"));
                  projectResult.add(projObj);
              }
             
              connObj.close();
          }
          catch(Exception sqlException)
          {
              sqlException.printStackTrace();
          }
          return projectResult;
      }

        //method used for department searches
        public static ArrayList<EmployeeBean> getDepartmentResultFromDB(){
       ArrayList<EmployeeBean> departmentResult = new ArrayList<EmployeeBean>();
           try
               {
                 stmtObj=getConnection().createStatement();   
                 resultSetObj = stmtObj.executeQuery("SELECT * FROM employee "
                      + "INNER JOIN departments ON employee.department_id = departments.department_id "
                      + "INNER JOIN projects ON employee.project_id = projects.project_id WHERE department_name = 'Admin'"
                      + "ORDER BY last_name, first_name");
                  while (resultSetObj.next())
                       {
                            EmployeeBean depObj = new EmployeeBean();
                            depObj.setEmployeeID(resultSetObj.getInt("employee_id"));
                            depObj.setFirstName(resultSetObj.getString("first_name"));
                            depObj.setLastName(resultSetObj.getString("last_name"));
                            depObj.setDepartmentName(resultSetObj.getString("department_name"));
                            departmentResult.add(depObj);
                        }
                  System.out.println("Total Records Fetched: " + departmentResult.size());
                  connObj.close();
                }
            catch(Exception sqlException)
                {
                sqlException.printStackTrace();
                }
            return departmentResult;
        }
  
}
