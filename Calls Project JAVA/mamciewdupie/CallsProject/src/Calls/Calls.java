package Calls;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Interface.Interface;

/**
 *
 * @author Wojg
 */
public class Calls {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*DatabaseBroker connection = new DatabaseBroker();
         PhysicianBroker pBroker = PhysicianBroker.getPhysicianBroker();
         // use connection from pool
         Connection connect = connection.getConnectionFromPool();
         // when done put connection back into pool 

         Physician p = new Physician(4, "KUPAPPDJKS", "Zborowski", "2012-01-01", "2012-01-01", "2012-01-01", "address", "4033138935");

         pBroker.doPhysician(3,p, connect);


         connection.returnConnectionToPool(connect); */
        Interface inter = new Interface();
        inter.createWindow();

    }
}