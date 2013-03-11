/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calls;

import java.sql.Connection;

/**
 *
 * @author Wojg
 */
public class Calls {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               
        DatabaseBroker connection = new DatabaseBroker();        
        // use connection from pool
        Connection connect = connection.getConnectionFromPool();        
        // when done put connection back into pool 
        connection.returnConnectionToPool(connect);  
        
        connection.getPhysician();
        
    }
}
