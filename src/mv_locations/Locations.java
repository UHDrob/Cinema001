
/*
 * Locations Class
 * Locations Entity
 */

package mv_locations;

/**
 *
 * @author Roberto Gomez
 */

public class Locations {

    private int locationID;
    private String locationName;
    private String address;
    private String locationPhone;

    public Locations(int plocationid, String plocationname, String paddress, String plocationphone){

        this.locationID = plocationid;
        this.locationName = plocationname;
        this.address = paddress;
        this.locationPhone = plocationphone;
    }

    public int getlocationid()
    {
        return locationID;
    }

    public String getlocationname()
    {
        return locationName;
    }
    
    public String getaddress()
    {
        return address;
    }

    public String getlocationphone()
    {
        return locationPhone;
    }

}