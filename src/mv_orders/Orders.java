/*
 * ORDERS Class
 * Orders Entity
 */
package mv_orders;

/**
 *
 * @author Roberto Gomez
 */
public class Orders {
    
    private int orderID;
    private int qty_adult;
    private int qty_senior;
    private int qty_child;
    private int ticketid_FK;

            
    public Orders(int porderid, int pqty_adult, int pqty_child, int pqty_senior, int pticketidfk){
        this.orderID = porderid;
        this.qty_adult = pqty_adult;
        this.qty_child = pqty_child;
        this.qty_senior = pqty_senior;
        this.ticketid_FK = pticketidfk;
    }
    
    public int getorderid()
    {
        return orderID;
    }
    
    public int getqtyadult()
    {
        return qty_adult;
    }
    
    public int getqtychild()
    {
        return qty_child;
    }
    
    public int getqtysenior()
    {
        return qty_senior;
    }
   
    public int getticketidfk()
    {
        return ticketid_FK;
    }    
    
}

