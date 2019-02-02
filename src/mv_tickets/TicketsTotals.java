/*
 * TICKETS TOTALS Class
 * TICKETS Entity
 */
package mv_tickets;

/**
 *
 * @author Roberto Gomez
 */
public class TicketsTotals {
    
  
    private int totalCount;
    private double avgAdult;
    private double avgChild;
    private double avgSenior;
    
    public TicketsTotals(int ptotalcount, double pavgadult, double pavgchild, double pavgsenior){

        this.totalCount  = ptotalcount;
        this.avgAdult = pavgadult;
        this.avgChild = pavgchild;
        this.avgSenior = pavgsenior;

    }
    
    public int gettotalcount()
    {
        return totalCount;
    }
    
     public double getavgadult()
    {
        return avgAdult;
    }   
    
      public double getavgchild()
    {
        return avgChild;
    }    

       public double getavgsenior()
    {
        return avgSenior;
    }      
      
      
      
}

