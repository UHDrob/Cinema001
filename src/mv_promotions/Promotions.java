/*
 * Promotion Class
 * Promotions Entity
 * 
 */

package mv_promotions;

/**
 *
 * @author Roberto Gomez
 */

public class Promotions {
    private int promotionID;
    private String promotionName;
    private String description;
    private Double discount;  

          

    public Promotions(int ppromotionid, String ppromotionname, String pdescription, Double pdiscount){
        this.promotionID = ppromotionid;
        this.promotionName = ppromotionname;
        this.description = pdescription;
        this.discount = pdiscount;      
 
    }

    
    public int getpromotionid()
    {
        return promotionID;
    }
    
    public String getpromotionname()
    {
        return promotionName;
    }

    public String getdescription()
    {
        return description;
    }

    /**
     *
     * @return
     */
    public Double getdiscount()
    {
        return discount;
    }
    


}