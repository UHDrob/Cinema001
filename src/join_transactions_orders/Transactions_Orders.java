
/*
 * SHOWTIMES ROOM Class
 * JOIN Showtimes Room
 */

package join_transactions_orders;

/**
 *
 * @author Roberto Gomez
 */

public class Transactions_Orders {
    private String transactionDate;
    private String transactionTime;
    private int qty_Adult;
    private int qty_Child;
    private int qty_Senior;

            

    public Transactions_Orders(String ptransactiondate, String ptransactiontime, int pqty_adult, int pqty_child, int pqty_senior){
        this.transactionDate = ptransactiondate;
        this.transactionTime = ptransactiontime;
        this.qty_Adult = pqty_adult;
        this.qty_Child =  pqty_child;
        this.qty_Senior = pqty_senior;
    }

    public String gettransactiondate()
    {
        return transactionDate;
    }

    public String gettransactiontime()
    {
        return transactionTime;
    }    
    
    public int getqty_adult()
    {
        return qty_Adult;
    }

    public int getqty_child()
    {
        return qty_Child;
    }  
 
    public int getqty_senior()
    {
        return qty_Senior;
    }  
}