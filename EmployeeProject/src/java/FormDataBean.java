
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ForDataBean class is used to generate the menu item for department
 * and projects
 */

/**
 *
 * @author David Han & Gigi Hui
 */

@ManagedBean (name = "formDataBean")
@RequestScoped

public class FormDataBean
{
    private String departmentName;
    private String projectName;
    private List<String> departmentName_List = new ArrayList<>();
    private List<String> projectName_List = new ArrayList<>();
    
    private final Connection connection = DatabaseOperation.getConnection();
    
    /**getDeptName returns an array of department name
     * @return departmentName_List**/
    public List<String> getDepartmentName_List(){
        try{
            PreparedStatement ps = null;
            ps = connection.prepareStatement("SELECT * FROM employees.departments");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                departmentName_List.add(rs.getString("department_name"));
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return departmentName_List;
    }
    
    public List<String> getProjectName_List(){
        try{
            PreparedStatement ps = null;
            ps = connection.prepareStatement("SELECT * FROM employees.projects");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                projectName_List.add(rs.getString("project_name"));
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return projectName_List;
    }
    
    @PostConstruct
    public void init(){
        
    }
    
    /**Constructor that does nothing**/
    public FormDataBean(){
        //Do nothing
    }
    
    /**Method that returns the department name
     * @return departmentName**/
    public String getDepartmentName(){
        return departmentName;
    }
    /**Method that sets the department name
     * @param departmentName**/
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
    /**Method that sets the department name list
     * @param departmentName_List**/
    public void setDepartmentName_List(List<String> departmentName_List){
        this.departmentName_List = departmentName_List;
    }
    
    /**Method that returns the project name
     * @return projectName**/
    public String getProjectName(){
        return projectName;
    }
    /**Method that sets the project name
     * @param projectName**/
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }
    /**Method that sets the project name list
     * @param projectName_List**/
    public void setProjectName_List(List<String> projectName_List){
        this.projectName_List = projectName_List;
    }
}