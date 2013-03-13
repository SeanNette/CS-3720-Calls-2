package Broker;

import Container.Physician;
import java.sql.*;

/**
 *
 * @author Dariusz
 */
public class PhysicianBroker 
{

    private static PhysicianBroker pBroker = null;
    DatabaseBroker connect = new DatabaseBroker();

    private PhysicianBroker() 
    {
        
    }

    public static PhysicianBroker getPhysicianBroker() 
    {
        if (pBroker == null) {
            pBroker = new PhysicianBroker();
        }
        return pBroker;
    }

    
    
    public void addPhysician(int choice, Object o, Connection connect) 
    {
        Physician p = (Physician) o;
        String SQL = null;
        try {
    
            switch (choice) 
            {
                case 1:
                    SQL = "call addPhysician(?,?,?,?,?,?,?);";
                    CallableStatement cs = connect.prepareCall(SQL);
                    //SQL
                    cs.setString(1, p.getFirstName());
                    cs.setString(2, p.getLastName());
                    cs.setString(3, p.getBirthDate());
                    cs.setString(4, p.getStartDate());
                    cs.setString(5, p.getEndDate());
                    cs.setString(6, p.getAddress());
                    cs.setString(7, p.getPhoneNumber());
                    
                    cs.execute();

                    cs.close();
                    break;
                    
                case 2:
                    SQL = "call updatePhysician(?,?,?,?,?,?,?,?);";
                    CallableStatement csUp = connect.prepareCall(SQL);

                    csUp.setInt(1, p.getEmployeeId());
                    csUp.setString(2, p.getFirstName());
                    csUp.setString(3, p.getLastName());
                    csUp.setString(4, p.getBirthDate());
                    csUp.setString(5, p.getStartDate());
                    csUp.setString(6, p.getEndDate());
                    csUp.setString(7, p.getAddress());
                    csUp.setString(8, p.getPhoneNumber());

                    csUp.execute();

                    csUp.close();
                    break;
                    
                case 3:
                    SQL = "call deletePhysician(?);";
                    CallableStatement csDel = connect.prepareCall(SQL);
                    
                    csDel.setInt(1, p.getEmployeeId());
                    csDel.execute();
                    csDel.close();
                    break;
                default:
                    System.out.print("default");
            }
             
        } catch (Exception e) 
        {
            System.out.print("Error: " + e);
        }

    }
}
