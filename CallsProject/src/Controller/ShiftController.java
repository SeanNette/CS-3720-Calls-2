/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Container.Shift;
import Broker.ShiftBroker;
import Container.Physician;
import java.util.ArrayList;

/**
 *
 * @author Dariusz
 */
public class ShiftController {
    private ArrayList<Shift> shifts;
    
    public int convertMonth(String month)
    {
        int m = 0;
        if(month.equals("January"))
            m = 1;
        if(month.equals("February"))
            m = 2;
        if(month.equals("March"))
            m = 3;
        if(month.equals("April"))
            m = 4;
        if(month.equals("May"))
            m = 5;
        if(month.equals("June"))
            m = 6;
        if(month.equals("July"))
            m = 7;
        if(month.equals("August"))
            m = 8;
        if(month.equals("September"))
            m = 9;
        if(month.equals("October"))
            m = 10;
        if(month.equals("November"))
            m = 11;
        if(month.equals("December"))
            m = 12;
        
        return m;
    }
    
    public ArrayList<Shift> getShiftObject(String date)
    {
        String month="null";
        int year;
        int m = 0;
                
        String delims = ", ";
        String[] tokens = date.split(delims);
        
        month = tokens[0];
        m = convertMonth(month);
        year = Integer.parseInt(tokens[1]);
        
        
        shifts = new ArrayList<>();
        ShiftBroker sb = ShiftBroker.getShiftBroker();
        
        shifts = sb.getAllShiftsForCurrentMonth(m,year);
        
   /*    for (int i = 0; i < shifts.size(); i++)
        {
            System.out.print("Employee ID: " + shifts.get(i).getEmployeeID()
                    + " Date Working: " + shifts.get(i).getDate() + "\n");
        } */ 
        
        return shifts;        
    }
    
    public String numberOfDaysWorked(int id, String date)
    {
        String month="null";
        int year;
        int m = 0;
                
        String delims = ", ";
        String[] tokens = date.split(delims);
        
        month = tokens[0];
        m = convertMonth(month);
        year = Integer.parseInt(tokens[1]);
        
        String s = null;
        ShiftBroker sb = ShiftBroker.getShiftBroker();
     //   System.out.println("ID " +id+ " M " + m+ " YEAR " +year);
        s = sb.countDays(id,m,year);
        
        return s;
        
        
    }
    public void SetNewShift(int id, String date, String text, int type)
    {        
        boolean next=true, previous=true;
        int counter=0;
        String newDate=null;
        
        int day,result = 0; 
        String delims = "-";
        String[] tokens = date.split(delims);
        
        
        
        ShiftBroker sb = ShiftBroker.getShiftBroker();
        
        sb.updateShiftPhysician(id, date, text);
        
        if(type==0)
        {
            next=false;
            previous=false;
            return;
        }
        
        while(next)
        {
            while(previous)
            {
                counter++;
                if(sb.checkIfWeekend(date,counter))
                {
                    day = Integer.parseInt(tokens[2]) + counter;
                    newDate = tokens[0] + "-" + tokens[1] + "-" + day;
                    // add counter to date
                    //System.out.println("COUNTER++ : "+ id + " " + newDate + " " );
                    sb.updateShiftPhysician(id, newDate, text);
                }
                else
                {
                    counter = 0;
                    previous = false;
                }
            }
            
            counter--;
            if(sb.checkIfWeekend(date, counter))
            {
                day = Integer.parseInt(tokens[2]) + counter;
                newDate = tokens[0] + "-" + tokens[1] + "-" + day;
                sb.updateShiftPhysician(id, newDate, text);
            }
            else
                next=false;
        }
    }
}
