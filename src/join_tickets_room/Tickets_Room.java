
/*
 * TICKETS ROOM Class
 * JOIN TICKETS ROOM LOCATIONS
 */

package join_tickets_room;

/**
 *
 * @author Roberto Gomez
 */

public class Tickets_Room {

    private String roomType;
    private Double adult;
    private Double child;
    private Double senior;
            

    public Tickets_Room(String proomtype, Double padult, Double pchild, Double psenior){
        this.roomType = proomtype;
        this.adult = padult;
        this.child = pchild;
        this.senior = psenior;        
    }

    public String getroomtype()
    {
        return roomType;
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