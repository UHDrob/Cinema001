
/*
 * Showtimes Class
 * Showtimes Entity
 */

package mv_showtimes;

/**
 *
 * @author Roberto Gomez
 */

public class Showtimes {    
    private int showtimeID;
    private String showtimeDate;
    private int moviesidFK;
    private int roomidFK;
    private String showtimeTime;


    public Showtimes(int pshowtimeid, String pshowtimedate, int pmoviesidfk, int proomidfk, String pshowtimetime){

        this.showtimeID = pshowtimeid;
        this.showtimeDate = pshowtimedate;
        this.moviesidFK = pmoviesidfk;
        this.roomidFK = proomidfk;
        this.showtimeTime = pshowtimetime;
    }

    public int getshowtimeid()
    {
        return showtimeID;
    }

    public String getshowtimedate()
    {
        return showtimeDate;
    }

    public int getmoviesidfk()
    {
        return moviesidFK;
    }

    public int getroomidfk()
    {
        return roomidFK;
    }   
    
    public String getshowtimetime()
    {
        return showtimeTime;
    }

}