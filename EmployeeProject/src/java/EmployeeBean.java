
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;



/*
CISP24 Project
Creating Employee object class to manage employees attributes
 */

/**
 *
 * @author Gigi Hui & David Han
 */

//ManageBean is the manager of UI component
//RequestScoped gets created upon a HTTP request and destroyed when the HTTP is finished
@ManagedBean @RequestScoped
public class EmployeeBean {
    
    
    private int employeeID;
    private String firstName;
    private String lastName;   
    private double salary;
    private String position;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private int departmentID;
    private int projectID;
    private String departmentName;
    private String projectName;

   //declaring array lists
    public ArrayList <EmployeeBean>employeesListFromDB;
    public ArrayList <EmployeeBean>projectsListFromDB;
    public ArrayList <EmployeeBean>departmentsListFromDB;
    public ArrayList <EmployeeBean>projectResultFromDB;
    public ArrayList <EmployeeBean>departmentResultFromDB;
    
    //getters and setters
    public int getEmployeeID() {
        return employeeID;
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
   
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
   
    
    //Annotation is used to execute after a dependency injection is done to perform any initialization
     //Method to avoid multiple calls to the DB for fetching the database Records.
   @PostConstruct
   public void init(){
       employeesListFromDB = DatabaseOperation.getEmployeesListFromDB();
       projectsListFromDB = DatabaseOperation.getProjectsListFromDB();
       departmentsListFromDB = DatabaseOperation.getDepartmentsListFromDB();
       projectResultFromDB=DatabaseOperation.getProjectResultFromDB();
       departmentResultFromDB=DatabaseOperation.getDepartmentResultFromDB();
     
   }
   
   //method used to fetch department records of a specific department
   public ArrayList<EmployeeBean> departmentResult(){
       return departmentResultFromDB;
   }
   
   //method used to fetch records of a specific project
   public ArrayList<EmployeeBean> projectResult(){
       return projectResultFromDB;
   }
   
   //**method used to fetch all records from the employee database
   public ArrayList<EmployeeBean> employeesList(){
       return employeesListFromDB;
   }
   
   //method used to save new employee record
   public String saveEmployeeDetails(EmployeeBean newEmployeeObj){
       return DatabaseOperation.saveEmployeeDetailsInDB(newEmployeeObj);
   }
   //method used to edit employee record
   public String editEmployeeRecord(int employeeID){
       return DatabaseOperation.editEmployeeRecordInDB(employeeID);
   }
   //method used to update employee record
   public String updateEmployeeDetails(EmployeeBean updateEmployeeObj){
       return DatabaseOperation.updateEmployeeDetailsInDB(updateEmployeeObj);
   }
   
   //method used to delete employee record
   public String deleteEmployeeRecord(int employeeID){
       return DatabaseOperation.deleteEmployeeRecordInDB(employeeID);
   }
   
   
   //**method used to fetch all records of the projects database
   public ArrayList<EmployeeBean> projectsList(){
       return projectsListFromDB;
   }
   
   
    //method used to save new project record
   public String saveProjectDetails(EmployeeBean newProjectObj){
       return DatabaseOperation.saveProjectDetailsInDB(newProjectObj);
   }
   
    //method used to edit project record
   public String editProjectRecord(int projectID){
       return DatabaseOperation.editProjectRecordInDB(projectID);
   }
   //method used to update project record
   public String updateProjectDetails(EmployeeBean updateProjectObj){
       return DatabaseOperation.updateProjectDetailsInDB(updateProjectObj);
   }
   
   //method used to delete project record
   public String deleteProjectRecord(int ProjectID){
       return DatabaseOperation.deleteProjectRecordInDB(ProjectID);
   }
   
   
   //**method used to fetch all records of the departments database
   public ArrayList<EmployeeBean> departmentsList(){
       return departmentsListFromDB;
   }
   
    //method used to save new department record
   public String saveDepartmentDetails(EmployeeBean newDepartmentObj){
       return DatabaseOperation.saveDepartmentDetailsInDB(newDepartmentObj);
   }
   
    //method used to edit department record
   public String editDepartmentRecord(int departmentID){
       return DatabaseOperation.editDepartmentRecordInDB(departmentID);
   }
   //method used to update department record
   public String updateDepartmentDetails(EmployeeBean updateDepartmentObj){
       return DatabaseOperation.updateDepartmentDetailsInDB(updateDepartmentObj);
   }
   
   //method used to delete department record
   public String deleteDepartmentRecord(int departmentID){
       return DatabaseOperation.deleteDepartmentRecordInDB(departmentID);
   }
   
 
   
}
