
/*
 * SHOWTIMES ROOM Class
 * JOIN Showtimes Room
 */

package join_transactions_promotions;

/**
 *
 * @author Roberto Gomez
 */

public class Transactions_Promotions {
    private String transactionDate;
    private String transactionTime;
    private String promotionName;
    private String discount;

            

    public Transactions_Promotions(String ptransactiondate, String ptransactiontime, String ppromotionname, String pdiscount){
        this.transactionDate = ptransactiondate;
        this.transactionTime = ptransactiontime;
        this.promotionName = ppromotionname;
        this.discount =  pdiscount;
    }

    public String gettransactiondate()
    {
        return transactionDate;
    }

    public String gettransactiontime()
    {
        return transactionTime;
    }    
    
    public String getpromotionname()
    {
        return promotionName;
    }

    public String getdiscount()
    {
        return discount;
    }  
    
}