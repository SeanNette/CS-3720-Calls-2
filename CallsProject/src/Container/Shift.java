package Container;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dariusz
 */
public class Shift {
    private String date;
    private int employeeID;
    private String comments;
    private int type;

    public Shift(String date, int type)
    {
        this.date = date;
        this.type = type;
    }

    public Shift(String date, int employeeID, String comments, int type)
    {
        this.date = date;
        this.employeeID = employeeID;
        this.comments = comments;
        this.type = type;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }
    
    public String monthToString()
    {
        String s;
        String delims = "-";
        String[] tokens = this.date.split(delims);
        
        s = tokens[1];
        return s;
    }

    @Override
    public String toString()
    {
        return "Shift{" + "date=" + date + ", employeeID=" + employeeID + ", type=" + type + '}';
    }
    
    
}