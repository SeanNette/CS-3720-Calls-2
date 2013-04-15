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
    
    
    public ArrayList<Shift> getShiftObject()
    {
        shifts = new ArrayList<>();
        ShiftBroker sb = ShiftBroker.getShiftBroker();
        
        shifts = sb.getAllShifts();
        
      /* for (int i = 0; i < shifts.size(); i++)
        {
            System.out.print("Employee ID: " + shifts.get(i).getEmployeeID()
                    + " Date Working: " + shifts.get(i).getDate() + "\n");
        } */
        
        return shifts;        
    }
}
