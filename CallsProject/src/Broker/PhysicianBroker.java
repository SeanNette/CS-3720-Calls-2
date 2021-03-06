package Broker;

import Container.DaysOff;
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
    
    public void modifyPhysician(int choice, Object o, int w, int we, int h)
    {
        Connection connect = connection.getConnectionFromPool();
        Physician p = (Physician) o;
        String SQL = null;
        int id=0;
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
                    
                    Statement stmt,stmt1;
                    stmt = connect.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT Employee_ID FROM Physician WHERE first_name ='"+
                            p.getFirstName()+"' AND Last_Name='"+p.getLastName()+"';");
                    while(rs.next())
                    {
                        id=rs.getInt(1);
                    }
                    
                    stmt1 = connect.createStatement();
                    
                    stmt1.execute("INSERT INTO initialdays (Employee_ID,weekdays,weekend_days,holidays)"
                            + "VALUES("+id+","+w+","+we+","+h+")");   
                    
                    
                    
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
                    
                  //  System.out.println("ID OF EMP IN BROKER: " + empID + " " + w + " " + we + " " + h);
                    Statement stmt3;
                    stmt3 = connect.createStatement();
                    stmt3.execute("UPDATE initialdays SET weekdays= " + w + ", weekend_days = " + we +
                            ", holidays = " + h + " WHERE Employee_ID = " + p.getEmployeeId() + ";");
                    /*(Employee_ID,weekdays,weekend_days,holidays)"
                            + "VALUES("+p.getEmployeeId()+","+w+","+we+","+h+")");*/   
                  
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
    public String objectPhysician(int pID)
    {
        String s=null;        
        try
        {
            Connection connect = connection.getConnectionFromPool();
            String SQL = "call selectPhysician(?);";
            CallableStatement cs = connect.prepareCall(SQL);
            
            cs.setInt(1, pID);
            cs.execute();
            
            ResultSet rs = cs.getResultSet();
            rs.next();
            s = rs.getString(2) + " " + rs.getString(3);
            
            cs.close();
            connection.returnConnectionToPool(connect);
        } catch (SQLException e)
        {
            System.out.println("Error: " + e);
        }
        
        return s;
        
    }
    
    public ArrayList<Physician> getAllPhysiciansNames() 
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
                        rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6), 
                        rs.getString(7), rs.getString(8));
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
    
    public DefaultTableModel getReportsData()
    {
        DefaultTableModel dm = new DefaultTableModel();
        try
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.first_name, p.last_name, i.weekdays as Initial_Weekdays,\n" +
"i.weekend_days as Initial_Weekend_Days, i.holidays as Initial_Holidays,\n" +
"(Select count(*) FROM shift WHERE shift.day_type = 0 AND shift.Employee_ID\n" +
"= p.Employee_ID) as Weekdays,\n" +
"(Select count(*) FROM shift WHERE shift.day_type = 1 AND shift.Employee_ID\n" +
"= p.Employee_ID) as Weekend_Days,\n" +
"(Select count(*) FROM shift WHERE shift.day_type = 2 AND shift.Employee_ID\n" +
"= p.Employee_ID) as Holidays\n" +
"FROM physician as p, initialdays as i\n" +
"WHERE p.Employee_ID = i.Employee_ID;");
            ResultSetMetaData md = rs.getMetaData();
            int num_cols = md.getColumnCount();
            String c[] = new String[num_cols];
          //  System.out.println("reportsData: " + c[0]);
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
    
    public ArrayList<DaysOff> getDaysOff(int employeeID)
    {
        ArrayList<DaysOff> daysOff = new ArrayList();
        Connection connect = connection.getConnectionFromPool();
        
        try
        {
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM daysoff "
                    + "WHERE Employee_ID = " + employeeID);
            
            while (rs.next())
            {               
               DaysOff doff = new DaysOff(rs.getInt(1),rs.getInt(2),rs.getDate(3));
               daysOff.add(doff); 
            }            
            
            connection.returnConnectionToPool(connect);
            
            
        } catch (SQLException ex)
        {
            System.out.println("Get current physicians error: " + ex);
        }        
        
        return daysOff;
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
    
    public boolean addDaysOffToDB(int empID, ArrayList<String> oDates)
    {
        try
        {
            
            Connection connect = connection.getConnectionFromPool();
            String SQL = "INSERT INTO daysoff (Days_ID, Employee_ID, day_Off) VALUES (?,?,?) ";
            CallableStatement cs = connect.prepareCall(SQL);
            
            for (int i = 0; i < oDates.size(); i ++)
            {       
                cs.setInt(1,0);
                cs.setInt(2, empID);
                cs.setString(3, oDates.get(i));
                cs.execute();
            }
                    
            cs.close();
                    
            connection.returnConnectionToPool(connect);
            
            return true;
        }
        
        catch (Exception e)
        {
            
        }
        
        return false;
    }
    
    public void delDaysOffFromDB(int empID, String d)
    {
        try
        {
            
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            stmt.execute("DELETE FROM daysoff WHERE Employee_ID = '" + empID + "' AND day_Off = '" + d + "'");            
                    
            connection.returnConnectionToPool(connect);
          
        }
        
        catch (Exception e)
        {
            
        }
    }
    public String getIniDays(int id)
    {
        String s = null;
        int w=0, wk=0, h=0;
        try
        {            
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM initialdays WHERE Employee_ID = '"+id+"';");
            
            while(rs.next())
            {
                w = rs.getInt(2);
                wk = rs.getInt(3);
                h = rs.getInt(4);
            }
            connection.returnConnectionToPool(connect);
            s = w + ", " + wk + ", " + h +", ";
            
        } catch (SQLException ex)
        {
            Logger.getLogger(PhysicianBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
            return s;
    }
}