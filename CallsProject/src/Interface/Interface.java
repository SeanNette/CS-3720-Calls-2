/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

/**
 *
 * @author Wojg
 */
public class Interface extends JPanel {
   
    public Interface() {
        super(new GridLayout(1,0));       
    }
    
    public void createMainWindow() {
        Border paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);  
        
        // create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // set the border to be a black line
        tabbedPane.setBorder(paneEdge);
        
        // create a new physician window object
        PhysicianWindow pw = new PhysicianWindow();
        CalendarWindow cw = new CalendarWindow();
        SettingWindow sw = new SettingWindow();
        JPanel physicianPanel = pw.createPhysicianWindow();
        JPanel calendarPanel = cw.createCalendarWindow();
        JPanel report = sw.createPhysicianWindow();
      //  physicianPanel.setBackground(Color.gray);
      //  calendarPanel.setBackground(Color.lightGray);
        tabbedPane.addTab("Physician",physicianPanel);
        tabbedPane.addTab("Calendar",calendarPanel);
        tabbedPane.addTab("Report",report);
        add(tabbedPane);
    } 
    
}
