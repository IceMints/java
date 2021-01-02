
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean; 
import javax.faces.bean.RequestScoped;
@ManagedBean(name = "form_data")
@RequestScoped
public class Form_Data {
    
   
    Connection connection = DatabaseOperation.getConnection();
    PreparedStatement ps=null;
    ResultSet rs=null;
     
    private int employeeID; 
    private String projectName;
    private String firstName;
    private String lastName;   
       
      
    public int getEmployeeID() {
        return employeeID;
    }      
    
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
       
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
  
    private ArrayList<Integer> formProjectList=new ArrayList<>();
    private ArrayList<Integer> formDepartmentList = new ArrayList<>();
    private ArrayList<String> formProjectSelection = new ArrayList<>();
    private ArrayList<String> formDepartmentSelection = new ArrayList<>();
    private ArrayList<Object> projectResultList = new ArrayList<>();
  
    //method to Project ID from database
    public ArrayList<Integer>  getProjectFormInfo(){
        try {          
            ps=connection.prepareStatement("select * from employees.projects");     
            rs=ps.executeQuery();
            while(rs.next()){
             
                formProjectList.add(rs.getInt("project_id"));
                //formProjectList.add(rs.getString("project_name"));
            }
            connection.close();
        } 
        catch(Exception sqlException) {
             sqlException.printStackTrace();
        }
        return formProjectList;
    }
    
    //method to get project names from database
     public ArrayList<String>  getProjectFormSelection(){
        try {
            ps=connection.prepareStatement("select * from employees.projects");
            rs=ps.executeQuery();
            while(rs.next()){
                formProjectSelection.add(rs.getString("project_name"));
            }
            connection.close();
            } 
        catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return formProjectSelection;
    }
    
    
    /**method to get department id from database**/
    public ArrayList<Integer> getDepartmentFormInfo(){
        try{
            ps=connection.prepareStatement("select * from employees.departments");
            rs=ps.executeQuery();
            while(rs.next()){
                formDepartmentList.add(rs.getInt("department_id"));
              
            }
            connection.close();
        } 
        catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return formDepartmentList;
    }
    /**method to get department names from database**/
    public ArrayList<String> getDepartmentFormSelection(){
        try{           
            ps=connection.prepareStatement("select * from employees.departments");
            rs=null;
            rs=ps.executeQuery();
            while(rs.next()){    
                formDepartmentSelection.add(rs.getString("department_name"));
            }
            connection.close();
           
        } 
        catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return formDepartmentSelection;
    }
 
    public ArrayList<Object> searchProjectFromDB(String projectName){
        try{
            ps = connection.prepareStatement("SELECT * FROM employee INNER JOIN projects ON employee.project_id = projects.project_id WHERE project_name = " + projectName);
            rs=ps.executeQuery();
            while (rs.next()){
                projectResultList.add(rs.getInt("employee_id"));
                projectResultList.add(rs.getString("first_name"));
                projectResultList.add(rs.getString("last_name"));
            }
            connection.close();
        }
        catch(Exception sqlException)
        {
            sqlException.printStackTrace();
        }
        return projectResultList;
  }
}