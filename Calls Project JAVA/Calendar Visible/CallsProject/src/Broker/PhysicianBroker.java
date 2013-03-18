package Broker;

import Container.Physician;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dariusz
 */
public class PhysicianBroker {

    private static PhysicianBroker pBroker = null;
    DatabaseBroker connect = new DatabaseBroker();

    private PhysicianBroker() {
    }

    public static PhysicianBroker getPhysicianBroker() {
        if (pBroker == null) {
            pBroker = new PhysicianBroker();
        }
        return pBroker;
    }

    public void addPhysician(int choice, Object o, Connection connect) {
        Physician p = (Physician) o;
        String SQL = null;
        try {

            switch (choice) {
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

        } catch (Exception e) {
            System.out.print("Error: " + e);
        }
    }

    // This one returns as P object.
    public Physician objectPhysician(int pID, Connection connect) {
        Physician p = null;

        try {
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
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return p;

    }
    
    // returns the default table model with all the rows from the database
    public DefaultTableModel getTableData(Connection connect) {
        DefaultTableModel dm = new DefaultTableModel();
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM physician");
            ResultSetMetaData md = rs.getMetaData();
            int num_cols = md.getColumnCount();
            String c[] = new String[num_cols];
            
            for (int i = 0; i < num_cols; i ++) {                
                c[i] = md.getColumnName(i+1);
                dm.addColumn(c[i]);
            }
           
            Object row[] = new Object[num_cols];
            while(rs.next()) {                 
                for (int i = 0; i < num_cols; i ++) {                    
                    row[i] = rs.getString(i+1);                                       
                }
                dm.addRow(row);
           }   
        } catch (SQLException err) {
            System.out.println("ShowPhysician error: " + err);
        }
        return dm;
    }
}
