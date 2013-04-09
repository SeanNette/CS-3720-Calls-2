/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Algorithms.BasicAlgorithm;
import Broker.PhysicianBroker;
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
        BasicAlgorithm ba = new BasicAlgorithm(physicians, shifts);
        shifts = ba.generateSchedule(month, year);
        //update shifts in database
        PhysicianBroker.getPhysicianBroker().saveShifts(shifts);
        
    }
    
    private void generatePhysicians()
    {
        physicians = new ArrayList();
        //grab physicians from database
        physicians = PhysicianBroker.getPhysicianBroker().getCurrentPhysicians(month, year);
        
        for(int i = 0; i < physicians.size(); i++)
        {
            System.out.println(physicians.get(i).toString());
        }
        
        /*Physician p1 = new Physician(1, 0, 0);
        Physician p2 = new Physician(2, 0, 0);
        Physician p3 = new Physician(3, 0, 0);
        Physician p4 = new Physician(4, 0, 0);
        Physician p5 = new Physician(5, 0, 0);
        Physician p6 = new Physician(6, 0, 0);
        //Physician p7 = new Physician(7, 0, 0);
        //Physician p8 = new Physician(8, 0, 0);
        //Physician p9 = new Physician(9, 0, 0);
        //Physician p10 = new Physician(10, 0, 0);
        //Physician p11 = new Physician(11, 0, 0);
        //Physician p12 = new Physician(12, 0, 0);
        //Physician p13 = new Physician(13, 0, 0);
        physicians.add(p1);
        physicians.add(p2);
        physicians.add(p3);
        physicians.add(p4);
        physicians.add(p5);
        physicians.add(p6);
        /*physicians.add(p7);
        physicians.add(p8);
        physicians.add(p9);
        physicians.add(p10);
        physicians.add(p11);
        physicians.add(p12);
        physicians.add(p13);*/
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
