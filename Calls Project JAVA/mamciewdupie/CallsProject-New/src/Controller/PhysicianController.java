/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Broker.*;
import Container.Physician;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Dariusz
 */
public class PhysicianController {

    // used for validating input from interface and sending it after to the broker.
    public boolean workPhysician(int choice, String firstName, String lastName,
            String bDate, String sDate, String eDate, String Address, String phoneNumber) throws SQLException {
        //test for empty string - work in progress
        if (!"".equals(firstName)) {

            DatabaseBroker connection = new DatabaseBroker();
            PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();
            // use connection from pool
            Connection connect = connection.getConnectionFromPool();
            
            // Create physician object if validation complete and pass it to pBroker to add
            Physician p = new Physician(0, firstName, lastName, bDate, sDate, eDate, Address, phoneNumber);
            
            pBroker.addPhysician(1, p, connect);
            
            // test for selectPhysician function - works. 
            //pBroker.objectPhysician(id, connect);


            connection.returnConnectionToPool(connect);
            return true;
        } else {
            return false;
        }
    }
}
