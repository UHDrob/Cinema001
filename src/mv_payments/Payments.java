
/*

 * Payments Class

 * Payments Entity

 */

package mv_payments;


/**
 *
 * @author Roberto Gomez
 */

public class Payments {
    
    private int paymentID;
    private String paymentType;
    private Double paymentAmount;
    private String auth_Number;
    private String card_Number;
    
public Payments(int ppaymentid, String ppaymenttype, Double ppaymentamount, String pauth_number, String pcard_number){
        this.paymentID = ppaymentid;
        this.paymentType = ppaymenttype;
        this.paymentAmount = ppaymentamount;
        this.auth_Number = pauth_number;
        this.card_Number = pcard_number;

    }

    

    public int getpaymentid()
    {
        return paymentID;
    }

    public String getpaymenttype()
    {
        return paymentType;
    }

    public Double getpaymentamount()
    {
        return paymentAmount;
    }
    
    public String getauth_number()
    {
        return auth_Number;
    }

    public String getcard_number()
    {
        return card_Number;
    }

   

}