/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.CalendarController;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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

/**
 *
 * @author Wojg
 */
public class PhysicianWindow extends JPanel {

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane = new JScrollPane();
    private JPanel tablePanel = new JPanel(new BorderLayout());
    private JLabel labelFname;
    private JTextField textFieldFname;
    private JLabel labelLname;
    private JTextField textFieldLname;
    private JLabel labelBdate;
    private JTextField textFieldBdate;
    private JLabel labelSdate;
    private JTextField textFieldSdate;
    private JLabel labelEdate;
    private JTextField textFieldEdate;
    private JLabel labelAddress;
    private JTextField textFieldAddress;
    private JLabel labelPhone;
    private JTextField textFieldPhone;
    private JLabel daysOffLabel;
    private JTextField textFieldDaysOff;
    private JButton addButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    private ArrayList<JTextField> allTextFields = new ArrayList<JTextField>();
    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
    private int selectedRowIndex;
    private CalendarController cc;

    public PhysicianWindow() {
        
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

        // initialize constraints for grid bag layout
        //   GridBagConstraints c = new GridBagConstraints();

        /*
         * Table Panel location *********************************************
         */
        /*  c.anchor = GridBagConstraints.WEST;
         c.gridx = 0;
         c.gridy = 0;
         c.fill = GridBagConstraints.BOTH;
         c.weightx = 1.0;
         c.weighty = 1.0;*/
        physicianWindow.add(leftPanel(blackline), BorderLayout.WEST/*, c*/);
        /*
         * ******************************************************************
         */
        /*
         * Field Panel location *********************************************
         */
        /* c.fill = GridBagConstraints.NONE;
         c.gridx = 0;
         c.gridy = 1;
         c.weightx = 0;
         c.weighty = 0;
         c.fill = GridBagConstraints.HORIZONTAL;*/
        physicianWindow.add(rightPanel(fieldPanelBorder), BorderLayout.CENTER/*, c*/);
        /*
         * *******************************************************************
         */
        /*
         * Button Panel location ********************************************
         */
        /* c.gridx = 0;
         c.gridy = 2;
         c.anchor = GridBagConstraints.EAST;
         c.fill = GridBagConstraints.HORIZONTAL;*/
        //    physicianWindow.add(buttonPanel(empty),BorderLayout.SOUTH/*, c*/);
        /*
         * *******************************************************************
         */
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
        rpanel.add(daysOffPanel(daysOffPanelBorder)/*,BorderLayout.SOUTH*/);
        return rpanel;
    }

    private JPanel tablePanel(Border border) {
        PhysicianController phys = new PhysicianController();

        // create table model
        model = phys.tableData();

        // create the upperpanel where the table will sit and set the layout to
        // BorderLayout - North,South,East,West,Center 
        //JPanel tablePanel = new JPanel();

        // create the table with the current model
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
        // on edit of table cell returns data in clicked cell (may or may not be needed)
        /*table.getModel().addTableModelListener(new TableModelListener()
         {
         @Override
         public void tableChanged(TableModelEvent e)
         {
         int row = e.getFirstRow();
         int col = e.getColumn();
         TableModel model = (TableModel) e.getSource();
         String colName = model.getColumnName(col);
         Object data = model.getValueAt(row, col);

         System.out.println(data.toString());
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
         });*/
        table.addMouseListener(new MouseListen());
        /*table.addMouseListener(
                
         new MouseListener()
         {
         // mouse click listener on table
         @Override
         public void mouseClicked(MouseEvent e)
         {
         selectedRowIndex = table.getSelectedRow();

         int columnCount = table.getColumnCount();
         System.out.println(columnCount);
         for (int i = 0; i < columnCount - 1; i++)
         {
         allTextFields.get(i).setText(model.getValueAt(selectedRowIndex, i + 1).toString());
         }
         System.out.println(table.getValueAt(selectedRowIndex, 0).toString());

         allButtons.get(0).setEnabled(false);
         allButtons.get(1).setEnabled(true);
         allButtons.get(2).setEnabled(true);
         allButtons.get(3).setEnabled(true);
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         // added by netbeans
         @Override
         public void mousePressed(MouseEvent e)
         {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         @Override
         public void mouseReleased(MouseEvent e)
         {
         // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
         // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         @Override
         public void mouseExited(MouseEvent e)
         {
         // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
         }); */


        // make the table scrollable
        scrollPane = new JScrollPane(table);
        // set the size of the scroll pane
        //  scrollPane.setPreferredSize(new Dimension(720, 150));
        // add the scrollpane with the table to the upperpanel
        tablePanel.add(scrollPane);
        // set the border for the panel
        tablePanel.setBorder(border);

        // add the upperpanel to the container (container for all the swing components)
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
        labelFname = new JLabel("First Name: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        // sets 
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelFname, c);

        textFieldFname = new JTextField(16);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        allTextFields.add(textFieldFname);
        fieldPanel.add(textFieldFname, c);
        /**
         * *******************************************************************
         */
        /**
         * ** Last Name ******************************************************
         */
        labelLname = new JLabel("Last Name: ");
        c.gridx = 1;
        c.gridy = 0;
        //  c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelLname, c);

        textFieldLname = new JTextField(16);
        c.gridx = 1;
        c.gridy = 1;
        //  c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        allTextFields.add(textFieldLname);
        fieldPanel.add(textFieldLname, c);
        /*
         * *******************************************************************
         */
        /*
         * ** Birth Date *****************************************************
         */
        labelBdate = new JLabel("Birth Date: ");
        c.gridx = 0;
        c.gridy = 4;
        //    c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelBdate, c);

        textFieldBdate = new JTextField(16);
        c.gridx = 0;
        c.gridy = 5;
        //    c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        allTextFields.add(textFieldBdate);
        fieldPanel.add(textFieldBdate, c);
        /**
         * *******************************************************************
         */
        /**
         * ** Start Date *****************************************************
         */
        labelSdate = new JLabel("Hire Date: ");
        c.gridx = 0;
        c.gridy = 6;
        //     c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelSdate, c);

        textFieldSdate = new JTextField(16);
        c.gridx = 0;
        c.gridy = 7;
        //   c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        allTextFields.add(textFieldSdate);
        fieldPanel.add(textFieldSdate, c);
        /**
         * *******************************************************************
         */
        /**
         * ** End Date *******************************************************
         */
        labelEdate = new JLabel("Fired Date: ");
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelEdate, c);

        textFieldEdate = new JTextField(16);
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        allTextFields.add(textFieldEdate);
        fieldPanel.add(textFieldEdate, c);
        /**
         * *******************************************************************
         */
        /**
         * ** Address ********************************************************
         */
        labelAddress = new JLabel("Address: ");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelAddress, c);

        textFieldAddress = new JTextField(16);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        allTextFields.add(textFieldAddress);
        fieldPanel.add(textFieldAddress, c);
        /**
         * *******************************************************************
         */
        /**
         * ** Phone **********************************************************
         */
        labelPhone = new JLabel("Phone: ");
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(labelPhone, c);

        textFieldPhone = new JTextField(16);
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        allTextFields.add(textFieldPhone);
        fieldPanel.add(textFieldPhone, c);
        /**
         * *******************************************************************
         */
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        fieldPanel.add(buttonPanel(null), c);

        return fieldPanel;
    }

    private JPanel buttonPanel(Border border) {
        // create button panel and set align to right
        JPanel buttonPanel = new JPanel(new FlowLayout()/* GridBagLayout()*/);
        // setborder 
        buttonPanel.setBorder(border);

        /* GridBagLayout gridbag = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
         c.fill = GridBagConstraints.NONE;
         c.anchor = GridBagConstraints.EAST;
         c.insets = new Insets(2, 2, 2, 2);
         buttonPanel.setLayout(gridbag);
         */
        addButton = new JButton("Add");
        // addButton.setBorder(buttonEdge);
/*        c.fill = GridBagConstraints.NONE;

         c.gridx = 1;
         c.gridy = 0;
         c.anchor = GridBagConstraints.EAST;*/
        addButton.addActionListener(new ButtonsListen());

        allButtons.add(addButton);
        buttonPanel.add(addButton/*, c*/);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        deleteButton = new JButton("Delete");
        //   deleteButton.setBorder(buttonEdge);
     /*   c.fill = GridBagConstraints.NONE;
         c.gridx = 2;
         c.gridy = 0;
         c.anchor = GridBagConstraints.EAST;*/
        deleteButton.addActionListener(new ButtonsListen());
        deleteButton.setEnabled(false);
        allButtons.add(deleteButton);
        buttonPanel.add(deleteButton/*, c*/);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        saveButton = new JButton("Save");
        //    saveButton.setBorder(buttonEdge);
    /*    c.fill = GridBagConstraints.NONE;
         c.gridx = 3;
         c.gridy = 0;*/
        saveButton.addActionListener(new ButtonsListen());
        saveButton.setEnabled(false);
        allButtons.add(saveButton);
        buttonPanel.add(saveButton/*, c*/);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        clearButton = new JButton("Clear");
        //    saveButton.setBorder(buttonEdge);
    /*    c.fill = GridBagConstraints.NONE;
         c.gridx = 0;
         c.gridy = 0;
         c.anchor = GridBagConstraints.WEST;
         c.insets = new Insets(0, 2, 0, 474);*/
        clearButton.addActionListener(new ButtonsListen());
        clearButton.setEnabled(false);
        allButtons.add(clearButton);

        buttonPanel.add(clearButton/*, c*/);

        buttonPanel.setBorder(border);

        return buttonPanel;
    }

    private JPanel daysOffPanel(Border border) {
        JPanel dPanel = new JPanel(new BorderLayout());
        JPanel showOff = new JPanel(new FlowLayout());
        dPanel.setBorder(border);
        cc = new CalendarController(2,2013,"Days Off", "small");
        daysOffLabel = new JLabel("Days Off: ");

        showOff.add(daysOffLabel);

        textFieldDaysOff = new JTextField(16);

        showOff.add(textFieldDaysOff);

        JButton addOff = new JButton("Save");
        showOff.add(addOff);

        dPanel.add(cc.calendarPanel(null), BorderLayout.CENTER);
        dPanel.add(showOff, BorderLayout.SOUTH);

        
        return dPanel;
    }

    private class MouseListen extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            selectedRowIndex = table.getSelectedRow();

            int columnCount = table.getColumnCount();
            System.out.println(columnCount);
            for (int i = 0; i < columnCount - 1; i++) {
                allTextFields.get(i).setText(model.getValueAt(selectedRowIndex, i + 1).toString());
            }
            System.out.println(table.getValueAt(selectedRowIndex, 0).toString());

            allButtons.get(0).setEnabled(false);
            allButtons.get(1).setEnabled(true);
            allButtons.get(2).setEnabled(true);
            allButtons.get(3).setEnabled(true);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            int empID = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            
            PhysicianController pc = new PhysicianController();
            ArrayList<Date> daysOff = new ArrayList();
            daysOff = pc.daysOff(empID);
            
            System.out.println(daysOff);
        }
    }

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
                } else {
                    done = "Cancelled";
                    return;
                }
                redraw();

            } else if (e.getSource() == clearButton) {
                textFieldFname.setText(null);
                textFieldLname.setText(null);
                textFieldAddress.setText(null);
                textFieldBdate.setText(null);
                textFieldEdate.setText(null);
                textFieldPhone.setText(null);
                textFieldSdate.setText(null);

                allButtons.get(0).setEnabled(true);
                allButtons.get(1).setEnabled(false);
                allButtons.get(2).setEnabled(false);
                allButtons.get(3).setEnabled(false);
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
            table.addMouseListener(new MouseListen());
            scrollPane = new JScrollPane(table);
            // set the size of the scroll pane

            scrollPane.setPreferredSize(new Dimension(720, 150));
            tablePanel.add(scrollPane);
            tablePanel.validate();
            tablePanel.repaint();

        }
    }
}