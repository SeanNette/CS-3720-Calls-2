/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Calendar.CalendarMonth;
import Calendar.MainPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Dariusz
 */
public class CalendarWindow {
    public CalendarWindow() {
        
    }
    
     public JPanel createCalendarWindow() {
        // offset window by 10 pixels all around      //top,left,bottom,right
        Border paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);        
        
        // regular black line border
        Border blackline = BorderFactory.createLineBorder(Color.black);
        
        // field panel border
        Border fieldPanelBorder = BorderFactory.createCompoundBorder(
                                    BorderFactory.createTitledBorder("Calendar Data Shit"),
                                    BorderFactory.createEmptyBorder(5,5,5,5));
        
        Border empty = null;
        
        JPanel calendarWindow = new JPanel(new GridBagLayout());        
        calendarWindow.setBorder(paneEdge);
        
        GridBagConstraints c = new GridBagConstraints();
        
        /** Calendar Panel location *******************************************/
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;  
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        calendarWindow.add(calendarPanel(blackline), c);
        /**********************************************************************/
        
        /** Field Panel location **********************************************/
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        calendarWindow.add(fieldPanel(fieldPanelBorder), c);        
        /**********************************************************************/
        
        /** Button Panel location *********************************************/
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.HORIZONTAL;
        // CHANGE BLACKLINE BORDER TO empty so the button panel does not have a border
        calendarWindow.add(buttonPanel(blackline), c);
        /**********************************************************************/
        
        return calendarWindow;
        
         
     }
     
     private JPanel calendarPanel(Border border) {
        JPanel calendarPanel = new JPanel();
        MainPanel mp = new MainPanel();      
        calendarPanel.setBorder(border);
        calendarPanel.add(mp.createMainCalendarPanel());
        return calendarPanel;         
     }
     
     private JPanel fieldPanel(Border border) {
         JPanel fieldPanel = new JPanel();
         fieldPanel.setBorder(border);
         return fieldPanel;
     }
     
     private JPanel buttonPanel(Border border){
         JPanel buttonPanel = new JPanel();
         buttonPanel.setBorder(border);
         return buttonPanel;
     }
}
