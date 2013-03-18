/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Broker.*;
import Container.Physician;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dariusz
 */
public class PhysicianController {

    public boolean workPhysician(int choice, String firstName, String lastName, String bDate, String sDate, String eDate, String Address, String phoneNumber) {
        /*  if (firstName.equals("Darek"))
         {
         return true;
         }
         else
         return false;
         } */

        DatabaseBroker connection = new DatabaseBroker();
      
        PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();
        // use connection from pool
        Connection connect = connection.getConnectionFromPool();
        // when done put connection back into pool 

        Physician p = new Physician(0, firstName, lastName, bDate, sDate, eDate, Address, phoneNumber);

        pBroker.addPhysician(1, p, connect);
        
        connection.returnConnectionToPool(connect);
        return true;
    }
    
    // makes the getTableData useable for the physican window
    public DefaultTableModel tableData() {
        DatabaseBroker connection = new DatabaseBroker();       
        PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();
        
        Connection connect = connection.getConnectionFromPool();
        
        DefaultTableModel dtm = pBroker.getTableData(connect);
        
        connection.returnConnectionToPool(connect);        
        
        return dtm;
    }
}

