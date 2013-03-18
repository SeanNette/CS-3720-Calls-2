/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Container.Physician;
import Container.Shift;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Sean
 */
public class Scheduler
{
    private ArrayList<Physician> physicians;
    private ArrayList<Shift> shifts;
    private int month, year;
    
    public Scheduler(int m, int y)
    {
        this.month = m;
        this.year = y;
        generatePhysicians();
        generateShifts();
    }
    
    private void generatePhysicians()
    {
        //grab physicians from database
    }
    
    private void generateShifts()
    {
        Calendar cal = new GregorianCalendar(year, month - 1, 1);
        int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String yearString = Integer.toString(year);
        String monthString = Integer.toString(month);
        //generate shifts
        shifts = new ArrayList();
        for(int i = 1; i <= numDays; i++)
        {
            cal.set(Calendar.DAY_OF_MONTH, i);
            String date = yearString + "-" + monthString + "-" + Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
            
            int type = 0;
            //check if shift already in calendar as a holiday and if it is set shift = that info
            if(cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7 || cal.get(Calendar.DAY_OF_WEEK) == 6)
                type = 1;
            Shift s = new Shift(date, type);
            shifts.add(s);
        }
    }
}
