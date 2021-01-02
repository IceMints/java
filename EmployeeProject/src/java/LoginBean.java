
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *This is the login class to manage the user name and passwords
 * 
 * @author David Han & Gigi Hui
 */

@ManagedBean
@RequestScoped
public class LoginBean
{
    private String username;
    private String password;

    /**@return the username*/
    public String getUsername() {
        return username;
    }

    /**@set the username*/
    public void setUsername(String username) {
        this.username = username;
    }

    /**@get the password*/
    public String getPassword() {
        return password;
    }

    /**@set the password*/
    public void setPassword(String password) {
        this.password = password;
    }
    //method used to validate login
    public String login()
    {
        if(DatabaseOperation.checkPassword(this.username, this.password))
            return "Success";
        else
            return "Fail";
    }
}

