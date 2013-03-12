package calls;

import calls.DatabaseBroker.DatabaseBroker;
import calls.Interface.Interface;

import java.sql.Connection;
import javax.swing.JFrame;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wojg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DatabaseBroker con = new DatabaseBroker();
        Connection connect = con.getConnectionFromPool();
        con.showPhysicians(connect);
        
        Interface mainFrame = new Interface();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    /*    int rows = 10;
        int cols = 5;
        
        JTable table = new JTable(rows,cols);
        JFrame f = new JFrame();
        f.setSize(300,300);
        f.add(new JScrollPane(table));
        f.setVisible(true);
*/
    }
        
}
