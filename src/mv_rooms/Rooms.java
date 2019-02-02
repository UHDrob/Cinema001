/*
 * ROOMS Class
 * ROOMS Entity
 */
package mv_rooms;

/**
 *
 * @author Roberto Gomez
 */
public class Rooms {
    
    private int roomID;
    private String capacity;
    private String roomType;
    private String size;

            
    public Rooms(int proomid, String pcapacity, String proomtype, String psize){
        this.roomID = proomid;
        this.capacity = pcapacity;
        this.roomType = proomtype;
        this.size = psize;

    }
    
    public int getroomid()
    {
        return roomID;
    }
    
    public String getcapacity()
    {
        return capacity;
    }
    
    public String getroomtype()
    {
        return roomType;
    }
    
    public String getsize()
    {
        return size;
    }
   
}

