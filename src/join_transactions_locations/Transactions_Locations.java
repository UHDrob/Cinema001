
/*
 * STAFF LOCATIONS Class
 * JOIN STAFF LOCATIONS
 */

package join_transactions_locations;

/**
 *
 * @author Roberto Gomez
 */

public class Transactions_Locations {

    private String transactionDate;
    private String transactionTime;
    private String locationName;

           
    public Transactions_Locations(String ptransactiondate, String ptransactiontime, String plocationname){
        this.transactionDate = ptransactiondate;
        this.transactionTime = ptransactiontime;
        this.locationName = plocationname;
    }

    public String gettransactiondate()
    {
        return transactionDate;
    }

    public String gettransactiontime()
    {
        return transactionTime;
    }

    public String getlocationname()
    {
        return locationName;
    }  

}