package Calls;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Controller.HolidayController;
import Controller.Scheduler;
import Interface.Interface;
import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Wojg
 */
public class Calls {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Calls Project");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Interface inter = new Interface();
                inter.createMainWindow();
                inter.setOpaque(true);

                frame.setContentPane(inter);
                frame.getContentPane().setBackground(new Color(102, 102, 102));
                frame.setBounds(200, 200, 1000, 800);
                frame.setVisible(true);


                
                /*HolidayController hc = new HolidayController();
                hc.generateHolidays(2013);*/
            }
        });
        
    }
}