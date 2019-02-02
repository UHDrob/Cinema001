
/*
 * SHOWTIMES ROOM Class
 * JOIN Showtimes Room
 */

package join_transactions_payments;

/**
 *
 * @author Roberto Gomez
 */

public class Transactions_Payments {
    private String transactionDate;
    private String transactionTime;
    private String paymentType;
    private Double paymentAmount;

            

    public Transactions_Payments(String ptransactiondate, String ptransactiontime, String ppaymenttype, Double ppaymentamount){
        this.transactionDate = ptransactiondate;
        this.transactionTime = ptransactiontime;
        this.paymentType = ppaymenttype;
        this.paymentAmount =  ppaymentamount;
    }

    public String gettransactiondate()
    {
        return transactionDate;
    }

    public String gettransactiontime()
    {
        return transactionTime;
    }    
    
    public String getpaymenttype()
    {
        return paymentType;
    }

    public Double getpaymentamount()
    {
        return paymentAmount;
    }  
    
}