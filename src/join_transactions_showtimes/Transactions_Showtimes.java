
/*
 * TRANSACTIONS SHOWTIMES Class
 * JOIN Showtimes Room
 */

package join_transactions_showtimes;


/**
 *
 * @author Roberto Gomez
 */

public class Transactions_Showtimes {
    private String transactionDate;
    private String transactionTime;
    private String showtimeDate;
    private String showtimeTime;

            

    public Transactions_Showtimes(String ptransactiondate, String ptransactiontime, String pshowtimedate, String pshowtimetime){
        this.transactionDate = ptransactiondate;
        this.transactionTime = ptransactiontime;
        this.showtimeDate = pshowtimedate;
        this.showtimeTime =  pshowtimetime;
    }

    public String gettransactiondate()
    {
        return transactionDate;
    }

    public String gettransactiontime()
    {
        return transactionTime;
    }    
    
    public String getshowtimedate()
    {
        return showtimeDate;
    }

    public String getshowtimetime()
    {
        return showtimeTime;
    }  
    
}