package trainstation.model;

public class User {
	private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String SSN;
    private String email;
    private String userRole;
    public User(String userName, String firstName, String lastName, String password, String SSN, String email, String userRole) {
    	this.userName = userName;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.password = password;
    	this.SSN = SSN;
    	this.email = email;
    	this.userRole = userRole;
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
    public String getUsername() {
        return userName;
    }
    public void setUsername(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSSN() {
        return SSN;
    }
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserRole() {
    	return userRole;
    }
    public void setUserRole(String userRole) {
    	this.userRole = userRole;
    }
}
