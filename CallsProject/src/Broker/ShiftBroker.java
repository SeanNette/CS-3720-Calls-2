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

/**
 *
 * @author Sean Nette
 */
public class ShiftBroker {

    private static ShiftBroker sBroker = null;
    DatabaseBroker connection = new DatabaseBroker();

    private ShiftBroker() {
    }

    public static ShiftBroker getShiftBroker() {
        if (sBroker == null) {
            sBroker = new ShiftBroker();
        }
        return sBroker;
    }

    /**
     *
     * @param shifts
     * @return
     */
    public boolean saveShifts(ArrayList<Shift> shifts) {
        try {
            Connection connect = connection.getConnectionFromPool();
            String SQL = "call addShift(?,?,?,?);";
            CallableStatement cs = connect.prepareCall(SQL);

            for (int i = 0; i < shifts.size(); i++) {
                cs.setInt(1, shifts.get(i).getEmployeeID());
                cs.setString(2, shifts.get(i).getDate());
                cs.setInt(3, shifts.get(i).getType());
                cs.setString(4, shifts.get(i).getComments());
                cs.execute();
            }

            cs.close();

            connection.returnConnectionToPool(connect);

            return true;
        } catch (SQLException ex) {
            System.out.println("Save shifts error: " + ex);
            return false;
        }
    }

    public int getLastPhysician(int m, int y) {
        int id = -1;
        if (m > 1) {
            m--;
        } else {
            y--;
            m = 12;
        }
        Calendar cal = new GregorianCalendar(y, m - 1, 1);
        String lastDay = Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String yearString = Integer.toString(y);
        String monthString;
        if (m < 10) {
            monthString = "0" + Integer.toString(m);
        } else {
            monthString = Integer.toString(m);
        }
        String date = "'" + yearString + "-" + monthString + "-" + lastDay + "'";
        try {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Employee_ID FROM shift "
                    + "WHERE Shift_Date = " + date);

            while (rs.next()) {
                id = rs.getInt(1);
            }
            connection.returnConnectionToPool(connect);
        } catch (SQLException err) {
            System.out.println("LastPhysican error: " + err);
        }

        return id;
    }

    public boolean isDayOff(String date, int physID) {
        System.out.println(date);
        try {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM daysoff "
                    + "WHERE day_Off = '" + date + "' AND Employee_ID = " + physID);

            if (rs.next()) {
                connection.returnConnectionToPool(connect);
                System.out.println("day off");
                return true;
            }
            connection.returnConnectionToPool(connect);
            return false;
        } catch (SQLException err) {
            System.out.println("Dayoff error: " + err);
            return false;
        }
    }

    public ArrayList<Shift> getAllShifts() {
        ArrayList<Shift> shiftList = new ArrayList<>();

        try {
            Connection connect = connection.getConnectionFromPool();
            Statement stmt;
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shift");

            while (rs.next()) {
                Shift s = new Shift(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                shiftList.add(s);
            }
            connection.returnConnectionToPool(connect);

        } catch (SQLException err) {
            System.out.println("ShowPhysician error: " + err);
        }
        
        return shiftList;
    }
}
