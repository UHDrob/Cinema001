
/*
 * ORDERS TICKETS Class
 * JOIN Showtimes Room
 */

package join_orders_tickets;

/**
 *
 * @author Roberto Gomez
 */

public class Orders_Tickets {
    private int orderID;
    private int qty_Adult;
    private int qty_Child;
    private int qty_Senior;
    private Double Adult;
    private Double Child;
    private Double Senior;

            

    public Orders_Tickets(int porderid, int pqty_adult, Double padult, int pqty_child, Double pchild, int pqty_senior, Double psenior){
        this.orderID = porderid;
        this.qty_Adult = pqty_adult;
        this.Adult = padult;        
        this.qty_Child =  pqty_child;
        this.Child = pchild;        
        this.qty_Senior = pqty_senior;
        this.Senior = psenior;
    }

    public int getorderid()
    {
        return orderID;
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
    
    public Double getadult()
    {
        return Adult;
    }

    public Double getchild()
    {
        return Child;
    }  
 
    public Double getsenior()
    {
        return Senior;
    }      
     
}