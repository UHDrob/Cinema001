
/*
 * SHOWTIMES MOVIES Class
 * JOIN Showtimes Movies
 */

package join_shotimes_movies;

/**
 *
 * @author Roberto Gomez
 */

public class Showtimes_Movies {
    private String showtimeDate;
    private String showtimeTime;
    private String movietitle;
    private String rating;

            

    public Showtimes_Movies(String pshowtimedate, String pshowtimetime, String pmovietitle, String prating){
        this.showtimeDate = pshowtimedate;
        this.showtimeTime = pshowtimetime;
        this.movietitle = pmovietitle;
        this.rating =  prating;
    }

    public String getshowtimedate()
    {
        return showtimeDate;
    }

    public String getshowtimetime()
    {
        return showtimeTime;
    }

    public String getmovietitle()
    {
        return movietitle;
    }  

    public String getrating()
    {
        return rating;
    }      
    
    
}