/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Calendar.CalendarController;
import Container.Physician;
import Controller.PhysicianController;
import Controller.Scheduler;
import Controller.ShiftController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Dariusz
 */
public class CalendarWindow
{


    private JPanel calendarWindow, fieldPanel, sidePanel, buttonPanel, legendPanel;
    private JButton change, generate;
    private JLabel currPhysLabel, newPhysLabel, no_of_hoursLabel, commentsLabel;
    private JTextField currPhysTextField, no_of_hoursTextField;
    private JComboBox newPhysCombo;
    private JScrollPane scrollPane;
    private JTextArea comments;
    private Border paneEdge, blackline, fieldPanelBorder, legendBorder, empty;
    private GridBagConstraints c;
    PhysicianController pc = new PhysicianController();
    CalendarController cd;
    private ArrayList<Physician> physicianList;
    

    public CalendarWindow()
    {
        // offset window by 10 pixels all around      //top,left,bottom,right
        paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // regular black line border
        blackline = BorderFactory.createLineBorder(Color.black);

        // field panel border
        fieldPanelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Calendar Data Shit"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        legendBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Legend"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        empty = null;

        calendarWindow = new JPanel(new BorderLayout());
        calendarWindow.setBorder(paneEdge);

        fieldPanel = new JPanel(new GridBagLayout());
        fieldPanel.setBorder(fieldPanelBorder);

        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(paneEdge);

        buttonPanel = new JPanel();
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(blackline);

        legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBorder(legendBorder);
        legendPanel.setPreferredSize(new Dimension(250, 500));

        cd = new CalendarController(2, 2013, "Scheduling Calendar", "normal");
        c = new GridBagConstraints();



        currPhysLabel = new JLabel("Current Physician: ");
        currPhysTextField = new JTextField(12);
        newPhysLabel = new JLabel("New Physician: ");
        no_of_hoursLabel = new JLabel("Hours: ");
        no_of_hoursTextField = new JTextField(4);
        comments = new JTextArea(5, 18);
        scrollPane = new JScrollPane(comments);
        commentsLabel = new JLabel("Comments: ");

        change = new JButton("Change");
        generate = new JButton("Generate");
        generate.addActionListener(new ButtonListener());
        System.out.print("Current month: " + cd.getMonth());

        // run algorithm, display callendar
    }
    
    public void setFieldName()
    {
        System.out.println("name? " + cd.getCurrentName());
        currPhysTextField.setText(cd.getCurrentName());
    }

    public JPanel createCalendarWindow()
    {
        /**
         * Calendar Panel location *********************************************
         */
        calendarWindow.add(cd.calendarPanel(empty), BorderLayout.CENTER);
        /**
         * *********************************************************************
         */
        /**
         * Field Panel location ************************************************
         */
        calendarWindow.add(fieldPanel(), BorderLayout.SOUTH);
        /**
         * *********************************************************************
         */
        /**
         * Button Panel location ***********************************************
         */
        //     calendarWindow.add(cc.buttonPanel(), BorderLayout.NORTH);
        /**
         * *********************************************************************
         */
        /**
         * Legend Panel location ***********************************************
         */
        calendarWindow.add(legendPanel(), BorderLayout.WEST);
        /**
         * *********************************************************************
         */
        /**
         * SidePanel Panel location ********************************************
         */
        calendarWindow.add(sidePanel(), BorderLayout.EAST);
        /**
         * *********************************************************************
         */
        return calendarWindow;
    }

    private JPanel fieldPanel()
    {
        // sets spaces between labels and textfields to 2 pixels
        c.insets = new Insets(2, 2, 2, 2);

        /**
         * ** Current Physician
         * ************************************************
         */
        addComponent(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, currPhysLabel);
        currPhysTextField.setEditable(false);
        addComponent(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, currPhysTextField);
        /**
         * *******************************************************************
         */
        /**
         * ** New Physician
         * ****************************************************
         */
        addComponent(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, newPhysLabel);
        addComponent(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, populateScroll());
        /*
         * *******************************************************************
         */

        /*
         * ** Number of Hours **************************************************
         */
        addComponent(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, no_of_hoursLabel);
        no_of_hoursTextField.setEditable(false);
        addComponent(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, no_of_hoursTextField);
        /*
         * *********************************************************************
         */

        /*
         * ** Comments *********************************************************
         */
        addComponent(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, commentsLabel);
        addComponent(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST, fieldPanel, scrollPane);
        /*
         * *********************************************************************
         */

        /*
         * ** Change Button ****************************************************
         */
        addComponent(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.EAST, fieldPanel, change);
        /*
         * *********************************************************************
         */

        return fieldPanel;
    }

    private JPanel sidePanel()
    {

        sidePanel.add(generate);


        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        return sidePanel;
    }
    private JComboBox populateScroll()
    {
        newPhysCombo = new JComboBox();
        
        physicianList = pc.getFirstAndLastname();
        for (int i = 0; i < physicianList.size(); i++)
        {            
            newPhysCombo.addItem(physicianList.get(i));
        }
      // put to change listener  
        Physician si = (Physician) newPhysCombo.getSelectedItem();
              
        System.out.print("PHYS ID: " + si.getEmployeeId());
        return newPhysCombo;
        
    }

    private JPanel legendPanel()
    {
        
        ShiftController sc = new ShiftController();
        
        physicianList = pc.getFirstAndLastname();
        JLabel legendLabel = new JLabel("Name                   "
                + ": Week   Wknd   Holiday");
        legendPanel.add(legendLabel);
        for (int i = 0; i < physicianList.size(); i++)
        {
            JLabel legendLabels = new JLabel(physicianList.get(i).getFirstName() + " " 
                    + physicianList.get(i).getLastName() + " ---- "
                    + sc.numberOfDaysWorked(physicianList.get(i).getEmployeeId()));
            
            legendPanel.add(legendLabels);
        }
        
        // call calendarController for the list
        /*
        for (int i = 0; i < no_of_phys; i++)
        {
            JLabel legendLabels = new JLabel("Physician " + i);
            legendLabels.setOpaque(true);

            Random rand = new Random();
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            legendLabels.setBackground(new Color(r, g, b));
            legendPanel.add(legendLabels);
        } */

        return legendPanel;
    }

    public void setGenerateButton()
    {
        if(cd.isScheduled())
            generate.setEnabled(false);
        else
            generate.setEnabled(true);
        
    }
    private void addComponent(int x, int y, int gw, int gh,
            double w, double h, int fill, int anchor,
            Container cont, Component comp)
    {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = gw;
        c.gridheight = gh;
        c.weightx = w;
        c.weighty = h;
        c.fill = fill;
        c.anchor = anchor;

        cont.add(comp, c);
    }

    private class ButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == generate)
            {
                int month = 0;
                ShiftController sc = new ShiftController();

                String delims = ", ";
                String[] tokens = cd.getMonth().split(delims);

                month = sc.convertMonth(tokens[0]);

                System.out.println("CURRENT MONTH = " + month);
                Scheduler s = new Scheduler(month, Integer.parseInt(tokens[1]));
                setGenerateButton();
                //redraw();
            }
        }

        public void redraw()
        {
            
            calendarWindow.remove(cd.calendarPanel(empty));
            calendarWindow.add(cd.calendarPanel(empty), BorderLayout.CENTER);
            calendarWindow.validate();
            calendarWindow.repaint();
        }
    }
    
}