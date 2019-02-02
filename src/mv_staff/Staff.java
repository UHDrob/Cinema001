/*
 * STAFF Class
 */
package mv_staff;

/**
 *
 * @author Roberto Gomez
 */
public class Staff {
    
    private int employeeID;
    private int locationidFK;
    private String firstName;
    private String lastName;
    private String employeeTitle;
    private String employeeAddress;

    private double salary;
    private String username;
    private String password;
    private byte[] staffPhoto;
            
    public Staff(int pemployeeid, int plocationidFK, String pfirstname, String plastname, String pemployeetitle, 
            String pemployeeaddress,  
            int psalary, String pusername, String ppassword, byte[] pstaffphoto){
        this.employeeID = pemployeeid;
        this.locationidFK = plocationidFK;
        this.firstName = pfirstname;
        this.lastName = plastname;
        this.employeeTitle = pemployeetitle;
        this.employeeAddress = pemployeeaddress;

        this.salary = psalary;
        this.username = pusername;
        this.password = ppassword;
        this.staffPhoto = pstaffphoto;
    }
    
    public int getemployeeid()
    {
        return employeeID;
    }
    
    public int getlocationidFK()
    {
        return locationidFK;
    }
    
    public String getfirstname()
    {
        return firstName;
    }
    
    public String getlastname()
    {
        return lastName;
    }
    
    public String getemployeetitle()
    {
        return employeeTitle;
    }
    
    public String getemployeeaddress()
    {
        return employeeAddress;
    }
    

        
    public double getsalary()
    {
        return salary;
    }
    
    public String getusername()
    {
        return username;
    }
    
    public String getpassword()
    {
        return password;
    }
    
    public byte[] getstaffphoto()
    {
        return staffPhoto;
    }
}
