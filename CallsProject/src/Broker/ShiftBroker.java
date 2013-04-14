/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Broker;

import Container.Shift;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dariusz
 */
public class ShiftBroker {
    private static ShiftBroker sBroker = null;
    DatabaseBroker connection = new DatabaseBroker();
    
    private ShiftBroker()
    {
    }
    
    public static ShiftBroker getShiftBroker()
    {
        if (sBroker == null)
        {
            sBroker = new ShiftBroker();
        }
        return sBroker;
    }
    
    /**
     *
     * @param shifts
     * @return
     */
    public boolean saveShifts(ArrayList<Shift> shifts)
    {
       try
       {
            Connection connect = connection.getConnectionFromPool();
            String SQL = "call addShift(?,?,?,?);";
            CallableStatement cs = connect.prepareCall(SQL);
            
            for(int i = 0; i < shifts.size(); i++)
            {
                cs.setInt(1, shifts.get(i).getEmployeeID());
                cs.setString(2, shifts.get(i).getDate());
                cs.setInt(3, shifts.get(i).getType());
                cs.setString(4, shifts.get(i).getComments());
                cs.execute();
            }
                    
            cs.close();
                    
            connection.returnConnectionToPool(connect);
            
            return true;
        } catch (SQLException ex)
        {
            System.out.println("Save shifts error: " + ex);
            return false;
        }
    }

}
