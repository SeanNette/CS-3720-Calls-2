/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Calendar.PhysicianCalendar;
import Container.DaysOff;
import Container.Physician;
import Controller.PhysicianController;
import Controller.ShiftController;
import Reports.PdfCreator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Wojg
 */
public class SettingWindow extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private JPanel fieldPanel;
    private JPanel tablePanel = new JPanel(new BorderLayout());
    private JLabel labelPath;
    private JTextField textPath;
    private JScrollPane scrollPanel;
    private JButton addButton;
    private ArrayList<JTextField> allTextFields = new ArrayList<JTextField>();
    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
    private int selectedRowIndex;
    private ArrayList<Physician> plist;
    private PhysicianCalendar pcCal;
    private static int empID;
    private static boolean clicked = false;

    public SettingWindow() 
    {
    }

    public int getEmployeeID() 
    {
        return empID;
    }
    
    public boolean getClicked() 
    {
        return clicked;
    }

    public JPanel createPhysicianWindow() {
        // offset window by 10 pixels all around      //top,left,bottom,right
        Border paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // regular black line border
        Border blackline = BorderFactory.createLineBorder(Color.black);

        // field panel border
        Border fieldPanelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Manipulate Table Data"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        Border empty = null;

        JPanel physicianWindow = new JPanel(new BorderLayout());
        physicianWindow.setBorder(paneEdge);

       // physicianWindow.add(leftPanel(blackline), BorderLayout.NORTH/*, c*/);
        physicianWindow.add(reportsPanel(fieldPanelBorder), BorderLayout.NORTH/*, c*/);
        physicianWindow.add(tablePanel(null),BorderLayout.CENTER);
        return physicianWindow;
    }

  /*  private JPanel leftPanel(Border border) {
        JPanel lpanel = new JPanel();
        lpanel.add(tablePanel(border));
        return lpanel;
    }*/

    private JPanel reportsPanel(Border border) {
        JPanel rpanel = new JPanel(new BorderLayout());

        Border fieldPanelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Reports"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        rpanel.add(fieldPanel(fieldPanelBorder)/*,BorderLayout.CENTER*/);
        
        return rpanel;
    }

    private JPanel tablePanel(Border border) {
        PhysicianController phys = new PhysicianController();
        
        // create table model
        model = phys.reportsData();
        table = new JTable(model);
        scrollPanel = new JScrollPane(table);
        tablePanel.add(scrollPanel);
        
       /* table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);*/
        
        tablePanel.setBorder(border);
        return tablePanel;
    }

    private JPanel fieldPanel(Border border) {

        fieldPanel = new JPanel(new GridBagLayout());

        // set the border
        fieldPanel.setBorder(border);

        GridBagConstraints c = new GridBagConstraints();

        // sets spaces between labels and textfields to 2 pixels
        c.insets = new Insets(2, 2, 2, 2);
        //  fieldPanel.setLayout(gridbag);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        fieldPanel.add(buttonPanel(null), c);

        return fieldPanel;
    }
    private ArrayList<Physician> getAllPhys()
    {
        ArrayList<Physician> pList= new ArrayList();
        PhysicianController pc = new PhysicianController();
        
        pList = pc.getFirstAndLastname();
        return pList;
    }

    private JPanel buttonPanel(Border border) {
        // create button panel and set align to right
        JPanel buttonPanel = new JPanel(new FlowLayout()/* GridBagLayout()*/);
        // setborder 
        buttonPanel.setBorder(border);
        addButton = new JButton("Save Report");
        addButton.addActionListener(new ButtonListener()); 
        
        allButtons.add(addButton);
        
        buttonPanel.add(addButton/*, c*/);

        buttonPanel.setBorder(border);

        return buttonPanel;
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource() == addButton) 
            {
                JFileChooser jfc = new JFileChooser();
                jfc.setSelectedFile(new File("reports.pdf")); 
                int returnVal = jfc.showSaveDialog(fieldPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File file = jfc.getSelectedFile();
                    PdfCreator pdf = new PdfCreator();
                    pdf.generateReports(file);
                }
            }
        }
    }

}