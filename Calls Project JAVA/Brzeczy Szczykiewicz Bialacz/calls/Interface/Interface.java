/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calls.Interface;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author Wojg
 */
public class Interface extends JFrame {
   
    private JTabbedPane tabbedPane;
    
    private JPanel physicianPanel;
    private JPanel calendarPanel;
    private JPanel reportsPanel;
    
    public Interface() {
        setTitle("Calls Project");
        setSize(650,650);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);
        
        createPanel1();
        createPanel2();
        createPanel3();
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Physician",physicianPanel);
        tabbedPane.addTab("Calendar",calendarPanel);
        tabbedPane.addTab("Reports",reportsPanel);
        
        topPanel.add(tabbedPane, BorderLayout.CENTER);
    }
        
    public void createPanel1() {
        physicianPanel = new JPanel();
        physicianPanel.setLayout(new BorderLayout());
               
        JTable table = new JTable(10,10);
        table.setSize(250, 250);
        JScrollPane scrollPane = new JScrollPane(table);
        physicianPanel.add(scrollPane, BorderLayout.CENTER);
        
    }
    
    public void createPanel2() {
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new BorderLayout());
        JLabel label1 = new JLabel("Page 2");
        calendarPanel.add(label1);
    }
    
    public void createPanel3() {
        reportsPanel = new JPanel();
        reportsPanel.setLayout(new BorderLayout());
        JLabel label1 = new JLabel("Page 3");
        reportsPanel.add(label1);
    }
}
