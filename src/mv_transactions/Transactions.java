/*
 * Transactions Class
 * Transactions Entity
 * 
 */
package mv_transactions;

/**
 *
 * @author Roberto Gomez
 */
public class Transactions {
    
    private int transactionID;
    private String transactionDate;
    private String transactionTime;
    private int promotionidFK;
    private int showtimeidFK;
    private int paymentidFK;
    private int locationidFK;
    private int orderidFK;

            
    public Transactions(int ptransactionid, String ptransactionDate, String ptransactionTime, int ppromotionidfk, 
            int pshowtimeidfk, int ppaymentidfk, int plocationidfk, int porderidfk){
        this.transactionID = ptransactionid;
        this.transactionDate = ptransactionDate;
        this.transactionTime = ptransactionTime;
        this.promotionidFK = ppromotionidfk;
        this.showtimeidFK = pshowtimeidfk;
        this.paymentidFK = ppaymentidfk;
        this.locationidFK = plocationidfk;
        this.orderidFK = porderidfk;

    }
    
    public int gettransactionid()
    {
        return transactionID;
    }
    
    public String gettransactiondate()
    {
        return transactionDate;
    }
    
    public String gettransactiontime()
    {
        return transactionTime;
    }
    
    public int getpromotionidfk()
    {
        return promotionidFK;
    }
    
    public int getshowtimeidfk()
    {
        return showtimeidFK;
    }
    
    public int getpaymentidfk()
    {
        return paymentidFK;
    }
    
    public int getlocationidfk()
    {
        return locationidFK;
    }
    
    public int getorderidfk()
    {
        return orderidFK;
    }
    
}
