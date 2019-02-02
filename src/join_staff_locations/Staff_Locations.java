
/*
 * STAFF LOCATIONS Class
 * JOIN STAFF LOCATIONS
 */

package join_staff_locations;


/**
 *
 * @author Roberto Gomez
 */

public class Staff_Locations {

    private String firstName;

    private String lastName;

    private String locationName;

            

    public Staff_Locations(String pfirstname, String plastname, String plocationname){
        this.firstName = pfirstname;
        this.lastName = plastname;
        this.locationName = plocationname;
    }

    public String getfirstname()
    {
        return firstName;
    }

    public String getlastname()
    {
        return lastName;
    }

    public String getlocationname()
    {
        return locationName;
    }  

}