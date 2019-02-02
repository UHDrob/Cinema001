
/*
 * SHOWTIMES ROOM Class
 * JOIN Showtimes Room
 */

package join_showtimes_room;

/**
 *
 * @author Roberto Gomez
 */

public class Showtimes_Room {
    private String showtimeDate;
    private String showtimeTime;
    private String roomtype;
    private String capacity;

            

    public Showtimes_Room(String pshowtimedate, String pshowtimetime, String proomtype, String pcapacity){
        this.showtimeDate = pshowtimedate;
        this.showtimeTime = pshowtimetime;
        this.roomtype = proomtype;
        this.capacity =  pcapacity;
    }

    public String getshowtimedate()
    {
        return showtimeDate;
    }

    public String getshowtimetime()
    {
        return showtimeTime;
    }

    public String getroomtype()
    {
        return roomtype;
    }  

    public String getcapacity()
    {
        return capacity;
    }      
    
    
}