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

    public String workPhysician(int choice, String firstName, String lastName,
            String bDate, String sDate, String eDate, String Address, String phoneNumber)
    {
        StringBuilder sb = new StringBuilder();
        firstName = firstName.trim();
        lastName = lastName.trim();

        if (firstName.isEmpty())
        {
            sb.append("Missing First Name");
            return sb.toString();
        }
        if (lastName.isEmpty())
        {
            sb.append("Missing Last Name");
            return sb.toString();
        }
        if (bDate.isEmpty())
        {
            sb.append("Missing Date of Birth");
            return sb.toString();
        }
        if (sDate.isEmpty())
        {
            sb.append("Missing Start E date");
            return sb.toString();
        } else
        {
            PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();
            // use connection from pool
            // when done put connection back into pool 
            Physician p = new Physician(0, firstName, lastName, bDate, sDate, eDate, Address, phoneNumber);

            pBroker.addPhysician(1, p);



            return "Success";
        }
    }

    public void workPhysician(int choice, int ID)
    {
        PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();

        pBroker.addPhysician(choice, ID);

    }

    // makes the getTableData useable for the physican window
    public DefaultTableModel tableData()
    {
        PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();


        DefaultTableModel dtm = pBroker.getTableData();


        return dtm;
    }
}
