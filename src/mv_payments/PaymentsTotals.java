/*
 * PAYMENT TOTALS Class
 * PAYMENTS Entity
 */
package mv_payments;

/**
 *
 * @author Roberto Gomez
 */
public class PaymentsTotals {
    
    private int totalCount;
    private double minPayment;   
    private double maxPayment;
    private double avgPayment;
    
    public PaymentsTotals(int ptotalcount, double pminpayment, double pmaxpayment, double pavgpayment){
        this.totalCount  = ptotalcount;
        this.minPayment = pminpayment;
        this.maxPayment = pmaxpayment;
        this.avgPayment = pavgpayment;
    }
    
    public int gettotalcount()
    {
        return totalCount;
    }
    
     public double getminpayment()
    {
        return minPayment;
    }   
    
     public double getmaxpayment()
    {
        return maxPayment;
    } 
     
     public double getavgpayment()
    {
        return avgPayment;
    }      
       
}

