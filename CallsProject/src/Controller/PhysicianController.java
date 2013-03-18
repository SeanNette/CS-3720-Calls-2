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
public class PhysicianController
{

    public String workPhysician(int choice, int id, String firstName, String lastName,
            String bDate, String sDate, String eDate, String Address, String phoneNumber)
    {
        StringBuilder sb = new StringBuilder();
        firstName = firstName.trim();
        lastName = lastName.trim();

        if (firstName.isEmpty())
        {
            sb.append("\nMissing First Name");

        }
        if (lastName.isEmpty())
        {
            sb.append("\nMissing Last Name");

        }
        if (bDate.isEmpty())
        {
            sb.append("\nMissing Date of Birth");

        }
        if (sDate.isEmpty())
        {
            sb.append("\nMissing Start E date");

        }
        if (Address.isEmpty())
        {
            sb.append("\nMissing Address");

        }
        if (sb.toString().isEmpty())
        {
            PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();
            // use connection from pool
            // when done put connection back into pool 
            Physician p = new Physician(0, firstName, lastName, bDate, sDate, eDate, Address, phoneNumber);

            pBroker.addPhysician(choice, p);



            return "Success";
        } else
        {
            return sb.toString();
        }
    }

    // Delete function
    public String workPhysician(int choice, int ID)
    {
        PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();
        Physician p = new Physician(ID, null, null, null, null, null, null, null);

        pBroker.addPhysician(choice, p);
        
        return "Deleted";
    }

    // makes the getTableData useable for the physican window
    public DefaultTableModel tableData()
    {
        PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();


        DefaultTableModel dtm = pBroker.getTableData();


        return dtm;
    }
}
