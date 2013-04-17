/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Broker;

import Container.Shift;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sean Nette
 */
public class ShiftBroker
{

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

            for (int i = 0; i < shifts.size(); i++)
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

    public int getLastPhysician(int m, int y)
    {
        int id = -1;
        if (m > 1)
        {
            m--;
        } else
        {
            y--;
            m = 12;
        }
        Calendar cal = new GregorianCalendar(y, m - 1, 1);
        String lastDay = Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String yearString = Integer.toString(y);
        String monthString;
        if (m < 10)
        {
            monthString = "0" + Integer.toString(m);
        } else
        {
            monthString = Integer.toString(m);
        }
        String date = "'" + yearString + "-" + monthString + "-" + lastDay + "'";
        try
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Employee_ID FROM shift "
                    + "WHERE Shift_Date = " + date);

            while (rs.next())
            {
                id = rs.getInt(1);
            }
            connection.returnConnectionToPool(connect);
        } catch (SQLException err)
        {
            System.out.println("LastPhysican error: " + err);
        }

        return id;
    }

    public boolean isDayOff(String date, int physID)
    {
        try
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM daysoff "
                    + "WHERE day_Off = '" + date + "' AND Employee_ID = " + physID);

            if (rs.next())
            {
                connection.returnConnectionToPool(connect);
                return true;
            }
            connection.returnConnectionToPool(connect);
            return false;
        } catch (SQLException err)
        {
            System.out.println("Dayoff error: " + err);
            return false;
        }
    }

    public ArrayList<Shift> getAllShiftsForCurrentMonth(int month, int year)
    {
        ArrayList<Shift> shiftList = new ArrayList<>();

        String date = "'" + Integer.toString(year) + "-"
                + Integer.toString(month);

        try
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shift WHERE Shift_Date >= "
                    + date + "-01' AND Shift_Date <= " + date + "-31'");

            while (rs.next())
            {
                Shift s = new Shift(rs.getString(1), rs.getInt(2), rs.getString(4), rs.getInt(3));
             //   System.out.println(s);
                shiftList.add(s);
            }
            connection.returnConnectionToPool(connect);

        } catch (SQLException err)
        {
            System.out.println("ShowPhysician error: " + err);
        }

        return shiftList;
    }

    public boolean isHoliday(String date)
    {
        try
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM holidays "
                    + "WHERE holiday = '" + date + "'");

            if (rs.next())
            {
                connection.returnConnectionToPool(connect);
                return true;
            }
            connection.returnConnectionToPool(connect);
            return false;
        } catch (SQLException err)
        {
            System.out.println("Holiday error: " + err);
            return false;
        }
    }

    public String countDays(int id, int month, int year)
    {
        String date = Integer.toString(year) + "-"
                + Integer.toString(month);
        String s = null;
        try
        {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt1, stmt2, stmt3;
            stmt1 = connect.createStatement();
            stmt2 = connect.createStatement();
            stmt3 = connect.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT Day_Type, COUNT(*) FROM "
                    + "shift WHERE Employee_ID = '" + id + "' AND Day_Type = '0' "
                    + "AND Shift_Date >= '" + date + "-01' AND "
                    + "Shift_Date <= '" + date + "-31';");
            while (rs1.next())
            {
                ResultSet rs2 = stmt2.executeQuery("SELECT Day_Type, COUNT(*) FROM "
                        + "shift WHERE Employee_ID = '" + id + "' AND Day_Type = '1' "
                        + "AND Shift_Date >= '" + date + "-01' AND "
                        + "Shift_Date <= '" + date + "-31';");
                while (rs2.next())
                {
                    ResultSet rs3 = stmt3.executeQuery("SELECT Day_Type, COUNT(*) FROM "
                            + "shift WHERE Employee_ID = '" + id + "' AND Day_Type = '2' "
                            + "AND Shift_Date >= '" + date + "-01' AND "
                            + "Shift_Date <= '" + date + "-31';");
                    while (rs3.next())
                    {
                        s = Integer.toString(rs1.getInt(2)) + ", " + Integer.toString(rs2.getInt(2))
                                + ", " + Integer.toString(rs3.getInt(2));
                    }

                }

            }

            connection.returnConnectionToPool(connect);

        } catch (SQLException ex)
        {
            Logger.getLogger(ShiftBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;

    }

    public void updateShiftPhysician(int id, String date, String text)
    {
        Connection connect = connection.getConnectionFromPool();
        Statement stmt;
        try
        {
            stmt = connect.createStatement();
            stmt.execute("UPDATE shift SET Employee_ID ='" + id 
                    + "' , Comments = '" + text + "' WHERE Shift_Date = '" + date + "';");
            
            connection.returnConnectionToPool(connect);

        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ShiftBroker.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public boolean checkIfWeekend(String initialDate, int counter)
    {
        String newDate;
        int day,result = 0; 
        String delims = "-";
        String[] tokens = initialDate.split(delims);
        
        day = Integer.parseInt(tokens[2]) + counter;
        newDate = tokens[0] + "-" + tokens[1] + "-" + day;
        
        Connection connect = connection.getConnectionFromPool();
        Statement stmt;
        try
        {
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT day_type FROM shift WHERE Shift_Date = '"+newDate+"';");
            
            while(rs.next())
            {
            result = rs.getInt(1);
            }
            
            connection.returnConnectionToPool(connect);
            
            if(result == 1 || result == 2)
            {
                return true;
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(ShiftBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean addHoliday(String date)
    {
        Connection connect = connection.getConnectionFromPool();
        try {
            
            Statement stmt;
            stmt = connect.createStatement();
            
            stmt.execute("INSERT INTO holidays "
                    + "VALUES('" + date + "')");

            
            connection.returnConnectionToPool(connect);
            return true;
        } catch (SQLException err) {
            System.out.println("Holiday error: " + err);
            connection.returnConnectionToPool(connect);
            return false;
        }
    }
}
