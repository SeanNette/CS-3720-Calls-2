/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Container;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class DaysOff 
{
    
    private int days_ID,Employee_ID;
    private Date day_Off;
    
    public DaysOff(int days_ID, int Employee_ID, Date day_Off)
    {
        this.days_ID = days_ID;
        this.Employee_ID = Employee_ID;
        this.day_Off = day_Off;
    }

    public int getDaysID()
    {
        return days_ID;
    }
    
    public int getEmployee_ID()
    {
        return Employee_ID;
    }
    
    public Date getDayOff()
    {
        return day_Off;
    }
    
    public void setDaysID(int days_ID)
    {
        this.days_ID = days_ID;
    }
    
    public void setEmployee_ID(int employee_ID)
    {
        this.Employee_ID = employee_ID;
    }
    
    public void setDayOff(Date dayoff)
    {
        this.day_Off = dayoff;
    }
}
