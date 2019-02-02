/*
 * STAFF Class
 */
package Staff_Totals;

/**
 *
 * @author Roberto Gomez
 */
public class Staff_Totals {
    

    private int Total_Employees;
    private double Total_Monthly_Salary;
            
    public Staff_Totals(int ptotalemployees, Double ptotalmonthlysalary ){
        this.Total_Employees = ptotalemployees;
        this.Total_Monthly_Salary = ptotalmonthlysalary;

    }
    
    public int gettotalemployees()
    {
        return Total_Employees;
    }
    
    public Double gettotalmonthlysalary()
    {
        return Total_Monthly_Salary;
    }
    

}
