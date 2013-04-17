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
    private JPanel tablePanel = new JPanel(new BorderLayout());
    private JLabel labelPath;
    private JTextField textPath;
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

        physicianWindow.add(leftPanel(blackline), BorderLayout.WEST/*, c*/);
        physicianWindow.add(rightPanel(fieldPanelBorder), BorderLayout.CENTER/*, c*/);
        return physicianWindow;
    }

    private JPanel leftPanel(Border border) {
        JPanel lpanel = new JPanel();
        lpanel.add(tablePanel(border));
        return lpanel;
    }

    private JPanel rightPanel(Border border) {
        JPanel rpanel = new JPanel(new GridLayout(2, 1));

        Border fieldPanelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Manipulate Table Data"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        rpanel.add(fieldPanel(fieldPanelBorder)/*,BorderLayout.CENTER*/);
        Border daysOffPanelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Days Off"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
  //      rpanel.add(daysOffPanel(daysOffPanelBorder)/*,BorderLayout.SOUTH*/);
        return rpanel;
    }

    private JPanel tablePanel(Border border) {
        PhysicianController phys = new PhysicianController();

        // create table model
        model = phys.tableData();
        tablePanel.setBorder(border);
        return tablePanel;
    }

    private JPanel fieldPanel(Border border) {

        JPanel fieldPanel = new JPanel(new GridBagLayout());

        // set the border
        fieldPanel.setBorder(border);

        GridBagConstraints c = new GridBagConstraints();

        // sets spaces between labels and textfields to 2 pixels
        c.insets = new Insets(2, 2, 2, 2);
        //  fieldPanel.setLayout(gridbag);

        /**
         * ** First Name *****************************************************
         */
        labelPath = new JLabel("Path for file: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        // sets 
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelPath, c);

        textPath = new JTextField(16);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        allTextFields.add(textPath);
        fieldPanel.add(textPath, c);
        
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 2;
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
        addButton = new JButton("Run");

        allButtons.add(addButton);
        buttonPanel.add(addButton/*, c*/);

        buttonPanel.setBorder(border);

        return buttonPanel;
    }


/*    private class MouseListen extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            
            clicked = true;
            pcCal.getNextButton().setEnabled(true);
            pcCal.getPrevButton().setEnabled(true);
            pcCal.getUpdateButton().setEnabled(true);
            
            selectedRowIndex = table.getSelectedRow();

            int columnCount = table.getColumnCount();
            
            clearAllFields();

            textFieldFname.setText(model.getValueAt(selectedRowIndex, 1).toString());
            textFieldLname.setText(model.getValueAt(selectedRowIndex, 2).toString());
            textFieldBdate.setText(model.getValueAt(selectedRowIndex, 3).toString());
            textFieldSdate.setText(model.getValueAt(selectedRowIndex, 4).toString());
            String edate;
            try {
                edate = model.getValueAt(selectedRowIndex, 5).toString();
                textFieldEdate.setText(edate);
            } catch (Exception ex) {
                System.out.println("End Employment Date exception handled.");
            }
            textFieldAddress.setText(model.getValueAt(selectedRowIndex, 6).toString());
            textFieldPhone.setText(model.getValueAt(selectedRowIndex, 7).toString());

            allButtons.get(0).setEnabled(false);
            allButtons.get(1).setEnabled(true);
            allButtons.get(2).setEnabled(true);
            allButtons.get(3).setEnabled(true);
            
            empID = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
          
            displayDaysOff();
        }
    }

    public void displayDaysOff() 
    {
        PhysicianController pc = new PhysicianController();
        ArrayList<DaysOff> daysOffDates = new ArrayList();
        daysOffDates = pc.daysOff(empID);
        pcCal.clearLabelsForMonth();
        pcCal.addLabelsForMonth();
        if (daysOffDates.size() > 0)
        {
            for (int i = 0; i < daysOffDates.size(); i++) {
                
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM, YYYY");
                if (pcCal.getMonth().equals(sdf.format(daysOffDates.get(i).getDayOff().getTime()))) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("d");
                    int day = Integer.parseInt(sdf1.format(daysOffDates.get(i).getDayOff().getTime()));
                    pcCal.getDayList().get(day + pcCal.getGapMonth() - 1).setBackground(Color.blue);
                    pcCal.getDayList().get(day + pcCal.getGapMonth() - 1).repaint();
                }                
            }
        }               
    }

    private void clearAllFields() {
        textFieldFname.setText(null);
        textFieldLname.setText(null);
        textFieldAddress.setText(null);
        textFieldBdate.setText(null);
        textFieldEdate.setText(null);
        textFieldPhone.setText(null);
        textFieldSdate.setText(null);
    }

    private void setAllButtons() {
        allButtons.get(0).setEnabled(true);
        allButtons.get(1).setEnabled(false);
        allButtons.get(2).setEnabled(false);
        allButtons.get(3).setEnabled(false);
    }
/*
    private class ButtonsListen implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String done = "";
            String[] yn = {"Yes", "No"};
            int choice = 0; // 1 - Add, 2 - Del, 3 - Update
            String fname = textFieldFname.getText();
            String lname = textFieldLname.getText();
            String bdate = textFieldBdate.getText();
            String sdate = textFieldSdate.getText();
            String edate = textFieldEdate.getText();
            String address = textFieldAddress.getText();
            String phone = textFieldPhone.getText();


            PhysicianController phys = new PhysicianController();
            ShiftController shit = new ShiftController();


            if (e.getSource() == addButton) {
                try {
                    choice = 1;
                    done = phys.workPhysician(choice, 0, fname, lname, bdate, sdate, edate, address, phone);
                    redraw();

                } catch (Exception ex) {
                }

            } else if (e.getSource() == saveButton) {

                try {
                    String id = table.getValueAt(selectedRowIndex, 0).toString();
                    choice = 2;
                    done = phys.workPhysician(choice, Integer.parseInt(id), fname, lname, bdate, sdate, edate, address, phone);
                    redraw();
                } catch (Exception ex) {
                }
            } else if (e.getSource() == deleteButton) {
                String id = table.getValueAt(selectedRowIndex, 0).toString();
                choice = 3;
                int resp = JOptionPane.showOptionDialog(
                        null,
                        "Are you sure you want to delete '" + fname + "'?",
                        "Delete Physician",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        yn,
                        "Quit");
                if (resp == 0) {
                    done = phys.workPhysician(choice, Integer.parseInt(id));
                    clearAllFields();
                    setAllButtons();
                } else {
                    done = "Cancelled";
                    return;
                }
                redraw();

            } else if (e.getSource() == clearButton) {
                clearAllFields();
                setAllButtons();
                return;
            }

            JOptionPane.showMessageDialog(null, done);
        }

        private void redraw() {
            PhysicianController phys = new PhysicianController();

            tablePanel.remove(scrollPane);
            model = phys.tableData();

            table = new JTable(model);
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(3).setMinWidth(0);
            table.getColumnModel().getColumn(3).setMaxWidth(0);
            table.getColumnModel().getColumn(4).setMinWidth(0);
            table.getColumnModel().getColumn(4).setMaxWidth(0);
            table.getColumnModel().getColumn(5).setMinWidth(0);
            table.getColumnModel().getColumn(5).setMaxWidth(0);
            table.getColumnModel().getColumn(6).setMinWidth(0);
            table.getColumnModel().getColumn(6).setMaxWidth(0);
            table.addMouseListener(new PhysicianWindow.MouseListen());
            scrollPane = new JScrollPane(table);
            // set the size of the scroll pane

            //  scrollPane.setPreferredSize(new Dimension(720, 150));
            tablePanel.add(scrollPane);
            tablePanel.validate();
            tablePanel.repaint();

        }
  */

}