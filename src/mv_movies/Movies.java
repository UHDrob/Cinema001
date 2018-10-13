/*
 * Movies Class
 * Movie Entity
 * Able to add, update, delete a record from the MV_MOVIES table
 */
package mv_movies;

/**
 *
 * @author Roberto Gomez
 */
public class Movies {
    
    private int movieID;
    private String movieTitle;
    private String releaseDate;
    private String rating;
    private String category;
    private String runningTime;
    private String director;
    private String movieCast;
    private byte[] picture;
            
    public Movies(int pmovieid, String pmovietitle, String preleasedate, String prating, 
            String pcategory, String prunningtime, String pdirector, String pmoviecast, byte[] pimg){
        this.movieID = pmovieid;
        this.movieTitle = pmovietitle;
        this.releaseDate = preleasedate;
        this.rating = prating;
        this.category = pcategory;
        this.runningTime = prunningtime;
        this.director = pdirector;
        this.movieCast = pmoviecast;
        this.picture = pimg;
    }
    
    public int getmovieid()
    {
        return movieID;
    }
    
    public String getmovietitle()
    {
        return movieTitle;
    }
    
    public String getreleasedate()
    {
        return releaseDate;
    }
    
    public String getrating()
    {
        return rating;
    }
    
    public String getcategory()
    {
        return category;
    }
    
    public String getrunningtime()
    {
        return runningTime;
    }
    
    public String getdirector()
    {
        return director;
    }
    
    public String getmoviecast()
    {
        return movieCast;
    }
    
    public byte[] getImage()
    {
        return picture;
    }
}
