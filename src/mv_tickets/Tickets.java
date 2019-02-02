
/*
 * TICKETS Class
 * TICKETS Entity
 *
 */

package mv_tickets;

/**
 *
 * @author Roberto Gomez
 */

public class Tickets {

    private int ticketID;
    private int roomidFK;
    private Double adult;
    private Double child;
    private Double senior;


    public Tickets(int pticketid, int proomidfk, Double padult, Double pchild, Double psenior){

        this.ticketID = pticketid;
        this.roomidFK = proomidfk;
        this.adult = padult;
        this.child = pchild;
        this.senior = psenior;
    }

    public int getticketid()
    {
        return ticketID;
    }

    public int getroomidfk()
    {
        return roomidFK;
    }

    public Double getadult()
    {
        return adult;
    }

    public Double getchild()
    {
        return child;
    }

    public Double getsenior()
    {
        return senior;
    }    
    

   

}