/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Container.Physician;
import Container.Shift;
import Controller.PhysicianController;
import Controller.Scheduler;
import Controller.ShiftController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
    private JLabel legendLabels = new JLabel();
    private ArrayList<Physician> physicianList;
    private JPanel mainCalendarPanel, calendarPanel, cbuttonPanel;
    private ArrayList<JLabel> headerList;
    private ArrayList<JPanel> dayList;
    private ArrayList<JLabel> dayNums;
    private ArrayList<JLabel> physList;
    private ArrayList<Shift> shiftList;
    private String currentName;
    private String selectedMonth;
    private JButton next, previous;
    private JLabel monthLabel;
    private String monthText, panelName, panelSize;
    String[] day_of_week =
    {
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };
    private int daysInMonth, startDays;
    private int gapMonth;
    private Calendar cal;
    private int month, year;
    private static int m = 1;

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

        // cd = new CalendarController(2, 2013, "Scheduling Calendar", "normal");
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
        
        //   System.out.print("Current month: " + cd.getMonth());

        // run algorithm, display callendar

    }

    public JPanel createCalendarWindow()
    {
        /**
         * Calendar Panel location *********************************************
         */
        calendarWindow.add(calendarPanel(), BorderLayout.CENTER);
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

    private JPanel calendarPanel()
    {
        mainCalendarPanel = new JPanel(new BorderLayout());

        headerList = new ArrayList<>();
        dayList = new ArrayList<>();
        dayNums = new ArrayList<>();

        c = new GridBagConstraints();

        mainCalendarPanel.add(buttonPanel(), BorderLayout.NORTH);
        mainCalendarPanel.add(showCalendar(), BorderLayout.CENTER);

        return mainCalendarPanel;
    }

    public JPanel showCalendar()
    {
        calendarPanel = new JPanel(new GridBagLayout());

        for (int i = 0; i < day_of_week.length; i++)
        {
            headerList.add(new JLabel(day_of_week[i], JLabel.CENTER));
            headerList.get(i).setBorder(BorderFactory.createRaisedBevelBorder());
            set_gridBagConstraints(i, 1, 1, 1, 1.0, 0.1, GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST);
            calendarPanel.add(headerList.get(i), c);
        }

        for (int j = 0; j < 42; j++)
        {
            dayList.add(new JPanel(new BorderLayout()));
            dayList.get(j).setBorder(BorderFactory.createLoweredBevelBorder());
            dayList.get(j).addMouseListener(new dayPanelMouseListener());
            set_gridBagConstraints(j % 7, ((j / 7) + 2), 1, 1, 1.0, 1.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
            calendarPanel.add(dayList.get(j), c);
        }

        createShiftBlock();
        setGenerateButton();
        addPhysicians();
        addLabelsForStartMonth();
        return calendarPanel;
    }

    public JPanel buttonPanel()
    {
        cbuttonPanel = new JPanel(new FlowLayout());

        next = new JButton("Next");
        previous = new JButton("Previous");

        cbuttonPanel.add(previous);
        previous.addActionListener(new ButtonListener());

        //   buttonPanel.add(Box.createRigidArea(new Dimension(190, 0)));

        cal = new GregorianCalendar();

        cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.YEAR, 2013);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date monthYear = cal.getTime();

        DateFormat sdf = new SimpleDateFormat("MMMM, YYYY");

        gapMonth = cal.get(Calendar.DAY_OF_WEEK) - 1;
        startDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH)+1;

        monthText = sdf.format(monthYear);
        monthLabel = new JLabel(monthText);

        cbuttonPanel.add(monthLabel);

        cbuttonPanel.add(next);
        next.addActionListener(new ButtonListener());
        change.addActionListener(new ButtonListener());
        // setGenerateButton();
        return cbuttonPanel;
    }

    public void clearLabelsForMonth()
    {
        for (int i = 0; i < daysInMonth; i++)
        {
            dayList.get(i + gapMonth).setBorder(BorderFactory.createLoweredBevelBorder());
            dayList.get(i + gapMonth).removeAll();
            // dayList.get(i + gapMonth).remove(dayNums.get(i));
            //  dayList.get(i + gapMonth).remove(physList.get(i));
            dayList.get(i + gapMonth).setBackground(null);
            dayList.get(i + gapMonth).repaint();
        }
    }
    
    
    public void addLabelsForStartMonth()
    {
        for (int i = 0; i < startDays-1; i++)
        {
            dayNums.add(new JLabel(Integer.toString(i + 1)));
            dayNums.get(i).setVerticalAlignment(JLabel.TOP);
            dayNums.get(i).setHorizontalAlignment(JLabel.RIGHT);
            dayList.get(i + gapMonth).add(dayNums.get(i), BorderLayout.NORTH);
            dayList.get(i + gapMonth).setBorder(BorderFactory.createRaisedBevelBorder());
            dayList.get(i + gapMonth).setBackground(Color.white);
            dayList.get(i + gapMonth).repaint();
        }
        daysInMonth = startDays + 2;
    }

    public void addLabelsForMonth()
    {
        for (int i = 0; i < daysInMonth; i++)
        {
            dayNums.add(new JLabel(Integer.toString(i + 1)));
            dayNums.get(i).setVerticalAlignment(JLabel.TOP);
            dayNums.get(i).setHorizontalAlignment(JLabel.RIGHT);
            dayList.get(i + gapMonth).add(dayNums.get(i), BorderLayout.NORTH);
            dayList.get(i + gapMonth).setBorder(BorderFactory.createRaisedBevelBorder());
            dayList.get(i + gapMonth).setBackground(Color.white);
            dayList.get(i + gapMonth).repaint();
        }
    }

    public ArrayList<Shift> createShiftBlock()
    {
        shiftList = new ArrayList<>();

        ShiftController sc = new ShiftController();

        shiftList = sc.getShiftObject(monthText);

        return shiftList;
    }

    // adding a physician to the calendar block
    public void addPhysicians()
    {
        physList = new ArrayList<>();
        String name;
        PhysicianController pc = new PhysicianController();
        int i = 0;
        physicianList = pc.getFirstAndLastname();
        HashMap colorMap = new HashMap();
        Random rand = new Random(22);
         
        for(int j = 0; j < physicianList.size(); j++)
        {
            int r = rand.nextInt(100);
            r += 135;
            int g = rand.nextInt(100);
            g += 135;
            int b = rand.nextInt(100);
            b += 135;
            int[] rgb = new int[3];
            rgb[0] = r;
            rgb[1] = g;
            rgb[2] = b;
            colorMap.put(physicianList.get(j).getEmployeeId(), rgb);
        }
        
        while (i < shiftList.size())
        {
            name = pc.findPhysicianByID(shiftList.get(i).getEmployeeID());

            physList.add(new JLabel(name));
            int[] rgb;
            rgb = (int[]) colorMap.get(shiftList.get(i).getEmployeeID());
            physList.get(i).setBackground(new Color(rgb[0], rgb[1], rgb[2]));
            physList.get(i).setOpaque(true);
            physList.get(i).setVerticalAlignment(JLabel.CENTER);
            physList.get(i).setHorizontalAlignment(JLabel.LEFT);
            dayList.get(i + gapMonth).add(physList.get(i), BorderLayout.SOUTH);
            dayList.get(i + gapMonth).setBorder(BorderFactory.createRaisedBevelBorder());
            dayList.get(i + gapMonth).setBackground(Color.WHITE);
            dayList.get(i + gapMonth).repaint();
            i++;
        }
    }

    public void setFieldName(String name)
    {
        //  System.out.println("name? " + cd.getCurrentName());
        currPhysTextField.setText(name);
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


        legendPanel.removeAll();

        legendPanel.add(legendLabel);

        Random rand = new Random(22);
        for (int i = 0; i < physicianList.size(); i++)
        {
            int r = rand.nextInt(100);
            r += 135;
            int g = rand.nextInt(100);
            g += 135;
            int b = rand.nextInt(100);
            b += 135;
            legendLabels = new JLabel(physicianList.get(i).getFirstName() + " "
                    + physicianList.get(i).getLastName() + " ---- "
                    + sc.numberOfDaysWorked(physicianList.get(i).getEmployeeId(), monthText));
            legendLabels.setOpaque(true);
            legendLabels.setBackground(new Color(r, g, b));
            legendPanel.add(legendLabels);
        }
        legendPanel.revalidate();
        legendPanel.validate();
        legendPanel.repaint();


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
        if (shiftList.size() > 0)
        {
            generate.setEnabled(false);
        } else
        {
            generate.setEnabled(true);
        }

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

    // function to set constraints on component
    private void set_gridBagConstraints(int left_right, int top_bottom, int gridwidth, int gridheight, double width, double height, int fill, int anchor)
    {
        c.fill = fill;
        c.anchor = anchor;
        c.weightx = width;
        c.weighty = height;
        c.gridx = left_right;
        c.gridy = top_bottom;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
    }

    private class dayPanelMouseListener extends MouseAdapter
    {

        @Override
        public void mouseClicked(MouseEvent e)
        {

            int panelPressed = dayList.indexOf(e.getSource());

            if (panelPressed < gapMonth || panelPressed > gapMonth + daysInMonth - 1)
            {
                dayList.get(panelPressed).setBackground(null);
            } else
            {
                System.out.println(panelPressed - gapMonth);

                if (dayList.get(panelPressed).getBackground() == Color.red)
                {
                    dayList.get(panelPressed).setBackground(Color.white);
                } else
                {

                    String[] d = monthText.split(",");


                    selectedMonth = (d[0] + " " + (panelPressed - gapMonth + 1) + "," + d[1]);
                    setFieldName(physList.get(panelPressed - gapMonth).getText());
                    System.out.println("DAY? " + selectedMonth);
                    //   cw.setFieldName();

                }
            }
        }
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
                String[] tokens = monthText.split(delims);

                month = sc.convertMonth(tokens[0]);

                System.out.println("CURRENT MONTH = " + month);
                Scheduler s = new Scheduler(month, Integer.parseInt(tokens[1]));
                //   setGenerateButton();
<<<<<<< HEAD
               // redraw();
=======
                //redraw();
>>>>>>> 6ccdb8af256bde588486a70b844f0a412efb35de
            } else if (e.getSource() == next)
            {
                int i=0;
                i++;
                clearLabelsForMonth();
                ++m;
                Calendar cals = new GregorianCalendar();
                cals = Calendar.getInstance();
                cals.set(Calendar.DATE, 1);
                cals.set(Calendar.MONTH, Calendar.MONTH + m);
                cals.set(Calendar.YEAR, 2013);

                cals.set(Calendar.DAY_OF_MONTH, 1);

                DateFormat df = new SimpleDateFormat("MMMM, YYYY");

                gapMonth = cals.get(Calendar.DAY_OF_WEEK) - 1;
                daysInMonth = cals.getActualMaximum(Calendar.DAY_OF_MONTH);
                monthText = df.format(cals.getTime());
                monthLabel.setText(monthText);
                
                System.out.print("Days in Month: " + daysInMonth + " Current Selected" + Calendar.MONTH
                       + " Next month should be +1 from current month" + (Calendar.MONTH + m));
                
                createShiftBlock();
                setGenerateButton();
                addPhysicians();
                legendPanel();
                addLabelsForMonth();

            } else if (e.getSource() == previous)
            {
                clearLabelsForMonth();
                --m;
                Calendar cal = new GregorianCalendar();
                cal = Calendar.getInstance();
                cal.set(Calendar.DATE, 1);
                cal.set(Calendar.MONTH, Calendar.MONTH + m);
                cal.set(Calendar.YEAR, 2013);

                cal.set(Calendar.DAY_OF_MONTH, 1);

                DateFormat df = new SimpleDateFormat("MMMM, YYYY");

                gapMonth = cal.get(Calendar.DAY_OF_WEEK) - 1;
                daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                monthText = df.format(cal.getTime());
                monthLabel.setText(monthText);
                System.out.print("NEW MONTH: " + monthText);
                
                createShiftBlock();
                setGenerateButton();
                addPhysicians();
                legendPanel();
                addLabelsForMonth();
            }
            else if (e.getSource() == change)
            {
                System.out.println("dskhdakjh");
            }
        }

        public void redraw()
        {
            calendarWindow.removeAll();
            calendarWindow.add(createCalendarWindow(),BorderLayout.CENTER);
            calendarWindow.validate();
            calendarWindow.repaint();
        }
    }
}