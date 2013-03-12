/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calls.DatabaseBroker;

/**
 *
 * @author Wojg
 */

import calls.Interface.Interface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
//import java.sql.Statement;

public class DatabaseBroker 
{   
    String host = "jdbc:mysql://localhost:3306/callsdb";
    String username = "root";
    String password = "P@$$w0rd";
    
    Queue<Connection> connectionPool = new LinkedList<>();
    
    // constructor
    /**
     *
     */
    public DatabaseBroker() {          
        initializePool();
    }
    
    public DatabaseBroker(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
        initializePool();
    }
    
    // adds to pool
    private void initializePool() {
        addToPool();
    }
    
    // if pool is not maximum value set and add a new connection
    private void addToPool() {
        while (!checkIfPoolFull()) {
            //System.out.println("Not FULL.");
            connectionPool.add(createNewConnection());
        }
        System.out.println("Pool allocated to maximum capacity " + connectionPool.size() + "." );
    }
    
    // creates the new connection
    private Connection createNewConnection() {
        Connection connect = null;
        try {            
            connect = DriverManager.getConnection(host, username, password);            
            //System.out.println("Connection established."); 
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return connect;
    }
    
    // set maximum pool and check if that is reached
    private boolean checkIfPoolFull() {
        final int MAX_POOL = 5;
        if (connectionPool.size() < MAX_POOL) {
            return false;
        }
        return true;
    }
    
    // use connection from pool
    public Connection getConnectionFromPool() {
        Connection connect = null;
        if (connectionPool.size() > 0) {
            connect = connectionPool.poll();
            System.out.println("Removed connection from pool" + " " + connectionPool.size());
        }    
        
        else 
            System.out.println("Max pool size reached" + " " + connectionPool.size());
        return connect;
    }
    
    // once connection used put back in to pool
    public void returnConnectionToPool(Connection connect) {
        connectionPool.add(connect);
        System.out.println("Added connection back to pool " + connectionPool.size());
    } 
    
    /**
     *
     */
    public void showPhysicians(Connection con) {
        Vector data = new Vector();
        Vector header = new Vector();        
        
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM physician");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
           
            for (int i = 1; i <= columns; i ++) {                
                header.add(metaData.getColumnName(i));
            }
           
            Vector row = new Vector(columns);
            while(resultSet.next()) {                 
                for (int i = 1; i <= columns; i ++) {                    
                    row.add(resultSet.getObject(i));                                        
                }
                data.add(row);
           }           
        } catch (SQLException err) {
            System.out.println("ShowPhysician error: " + err);
        }
      
    }    
}
