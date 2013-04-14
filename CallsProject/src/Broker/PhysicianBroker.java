package Broker;

import Container.Physician;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dariusz, Sean, Wojeich
 */
public class PhysicianBroker
{
    
    private static PhysicianBroker pBroker = null;
    DatabaseBroker connection = new DatabaseBroker();
    
    private PhysicianBroker()
    {
    }
    
    public static PhysicianBroker getPhysicianBroker()
    {
        if (pBroker == null)
        {
            pBroker = new PhysicianBroker();
        }
        return pBroker;
    }
    
    public void modifyPhysician(int choice, Object o)
    {
        Connection connect = connection.getConnectionFromPool();
        Physician p = (Physician) o;
        String SQL = null;
        try
        {
            
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
                    
                    connection.returnConnectionToPool(connect);
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
                    
                    connection.returnConnectionToPool(connect);
                    break;
                
                case 3:
                    SQL = "call deletePhysician(?);";
                    CallableStatement csDel = connect.prepareCall(SQL);
                    
                    csDel.setInt(1, p.getEmployeeId());
                    csDel.execute();
                    csDel.close();
                    
                    connection.returnConnectionToPool(connect);
                    break;
                default:
                    System.out.print("default");
            }
            
        } catch (Exception e)
        {
            System.out.print("Error: " + e);
        }
    }

    // This one returns as P object.
    public Physician objectPhysician(int pID)
    {
        Physician p = null;
        
        try
        {
            Connection connect = connection.getConnectionFromPool();
            String SQL = "call selectPhysician(?);";
            CallableStatement cs = connect.prepareCall(SQL);
            
            cs.setInt(1, pID);
            cs.execute();
            
            ResultSet rs = cs.getResultSet();
            
            rs.next();
            
            p = new Physician(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8));
            
            
            cs.close();
            System.out.println(p.getFirstName());
        } catch (SQLException e)
        {
            System.out.println("Error: " + e);
        }
        
        return p;
        
    }
    
    public ArrayList<Physician> getAllPhysicians() 
    {
        ArrayList<Physician> phys = new ArrayList<>();
        
        
        try 
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM physician");
            
            while (rs.next())
            {
                Physician p = new Physician(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8));
                phys.add(p);
            }
            connection.returnConnectionToPool(connect);
        } 
        
        catch (SQLException err)
        {
            System.out.println("ShowPhysician error: " + err);
        }
        
        return phys;
    }
    
    // returns the default table model with all the rows from the database
    public DefaultTableModel getTableData()
    {
        DefaultTableModel dm = new DefaultTableModel();
        try
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM physician");
            ResultSetMetaData md = rs.getMetaData();
            int num_cols = md.getColumnCount();
            String c[] = new String[num_cols];
            
            for (int i = 0; i < num_cols; i++)
            {
                c[i] = md.getColumnName(i + 1);
                dm.addColumn(c[i]);
            }
            
            Object row[] = new Object[num_cols];
            while (rs.next())
            {
                for (int i = 0; i < num_cols; i++)
                {
                    row[i] = rs.getString(i + 1);
                }
                dm.addRow(row);
            }
            
            connection.returnConnectionToPool(connect);
        } catch (SQLException err)
        {
            System.out.println("ShowPhysician error: " + err);
        }
        
        return dm;
    }
    
    public ArrayList<Physician> getCurrentPhysicians(int m, int y)
    {
        ArrayList<Physician> physicians = new ArrayList();
        Connection connect = connection.getConnectionFromPool();
        String monthString;
        if(m < 10)
        {
            monthString = "0" + Integer.toString(m);
        }
        else
        {
            monthString = Integer.toString(m);
        }
        String date = "'" + Integer.toString(y) + "-" + monthString + "-01'";
        try
        {
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM physician "
                    + "WHERE End_Employment_Date is NULL "
                    + "OR End_Employment_Date >= " + date);
            
            while (rs.next())
            {
                Physician p = new Physician(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8));
                physicians.add(p);
            }
            for (int i = 0; i < physicians.size(); i++)
            {
                int tempM = m;
                int tempY = y;
                for(int j=0; j < 3; j++)
                {
                    tempM = tempM - j;
                    if(tempM > 1)
                    {
                        tempM--;
                    }
                    else
                    {
                        tempY--;
                        tempM = 12;
                    }
                    
                    rs = stmt.executeQuery("SELECT Hours_Worked FROM hours "
                            + "WHERE Employee_ID = " + physicians.get(i).getEmployeeId()
                            + " AND Year = " + tempY + " AND Month = " + tempM);
                    if (rs.next())
                    {
                        physicians.get(i).setPreviousHours(physicians.get(i).getPreviousHours() + rs.getInt(1));
                    } 
                }
            }
            
            connection.returnConnectionToPool(connect);
        } catch (SQLException ex)
        {
            System.out.println("Get current physicians error: " + ex);
        }        
        
        
        return physicians;
    }
    
    public boolean updatePhysicianHours(int m, int y, ArrayList<Physician> phys)
    {
        try
        {
            Connection connect = connection.getConnectionFromPool();
            String SQL = "call updateHours(?,?,?,?);";
            CallableStatement cs = connect.prepareCall(SQL);
            
            for(int i = 0; i < phys.size(); i++)
            {
                cs.setInt(1, phys.get(i).getEmployeeId());
                cs.setInt(2, m);
                cs.setInt(3, y);
                cs.setInt(4, phys.get(i).getCurHours());
                cs.execute();
            }
                    
            cs.close();
                    
            connection.returnConnectionToPool(connect);
            
            return true;
        } catch (SQLException ex)
        {
            System.out.println("Update hours error: " + ex);
            return false;
        }
    }
}