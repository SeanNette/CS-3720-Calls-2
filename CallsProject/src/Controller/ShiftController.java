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
    
    
    public ArrayList<Shift> getShiftObject(String date)
    {
        String month="null";
        int year;
        int m = 0;
                
        String delims = ", ";
        String[] tokens = date.split(delims);
        
        month = tokens[0];
        year = Integer.parseInt(tokens[1]);
        
        if(month.equals("January"))
            m = 1;
        if(month.equals("Febuary"))
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
        
        shifts = new ArrayList<>();
        ShiftBroker sb = ShiftBroker.getShiftBroker();
        
        shifts = sb.getAllShiftsForCurrentMonth(m,year);
        
       for (int i = 0; i < shifts.size(); i++)
        {
            System.out.print("Employee ID: " + shifts.get(i).getEmployeeID()
                    + " Date Working: " + shifts.get(i).getDate() + "\n");
        } 
        
        return shifts;        
    }
}
