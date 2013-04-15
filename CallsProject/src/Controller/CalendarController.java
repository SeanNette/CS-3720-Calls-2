/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Container.Shift;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Asus
 */
public class CalendarController extends JPanel {
    private JPanel mainCalendarPanel, calendarPanel, buttonPanel;
    private ArrayList<JLabel> headerList;
    private ArrayList<JPanel> dayList;    
    private ArrayList<JLabel> dayNums;
    private ArrayList<JLabel> physList;
    private ArrayList<Shift> shiftList = new ArrayList<>();
           
    String[] physicianName = {"John Smith", "Darek Zborowski"};
    private JButton next, previous;
    private JLabel monthLabel;
    private String monthText, panelName, panelSize;
    
    String[] day_of_week = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private int daysInMonth;
    private int gapMonth;
      
    private GridBagConstraints c;
    private Calendar cal;
    
    private int month, year;
    
    private static int m = 0;
       
    public CalendarController(int m, int y, String name, String size) {
        this.month = m;        
        this.year = y;
        this.panelName = name;
        this.panelSize = size;
        
        mainCalendarPanel = new JPanel(new BorderLayout());
                
        buttonPanel = new JPanel(new FlowLayout());
        calendarPanel = new JPanel(new GridBagLayout()); 
                 
        headerList = new ArrayList<>();
        dayList = new ArrayList<>();
        dayNums = new ArrayList<>();
        physList = new ArrayList<>();
        
        if (panelSize != "small") 
        {
            next = new JButton("Next");
            previous = new JButton("Previous");
        }
        
        else 
        {
            next = new JButton(">");
            previous = new JButton("<");
        }
        
        c = new GridBagConstraints();  
        
        cal = new GregorianCalendar();                
      
        cal = Calendar.getInstance();
        cal.set(Calendar.DATE,1);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.YEAR,year);
        
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date monthYear = cal.getTime();  

        DateFormat sdf = new SimpleDateFormat("MMMM, YYYY"); 
        
        gapMonth = cal.get(Calendar.DAY_OF_WEEK) - 1;
        daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        monthText = sdf.format(monthYear);   
        monthLabel = new JLabel(monthText);     
    }
      
    public JPanel showCalendar() 
    {        
        for (int i = 0; i < day_of_week.length; i ++) 
        {
            headerList.add(new JLabel(day_of_week[i], JLabel.CENTER)); 
            headerList.get(i).setBorder(BorderFactory.createRaisedBevelBorder());     
            set_gridBagConstraints(i, 1, 1, 1, 1.0, 0.1, GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST);
            calendarPanel.add(headerList.get(i),c);
        }
        
        for (int j = 0; j < 42; j++)
        {
            dayList.add(new JPanel(new BorderLayout()));
            dayList.get(j).setBorder(BorderFactory.createLoweredBevelBorder());          
            dayList.get(j).addMouseListener(new dayPanelMouseListener());
            set_gridBagConstraints(j%7,((j/7)+2), 1, 1, 1.0, 1.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
            calendarPanel.add(dayList.get(j),c);        
        }
        
        createShiftBlock();
        addPhysicians();        
        addLabelsForMonth();
        return calendarPanel;
    }
       
    public JPanel buttonPanel() 
    {
        if (!panelSize.equals("small"))
        {            
            buttonPanel.add(previous);
            previous.addActionListener(new calendarButtonListener());

         //   buttonPanel.add(Box.createRigidArea(new Dimension(190, 0)));

            buttonPanel.add(monthLabel);

         //   buttonPanel.add(Box.createRigidArea(new Dimension(190, 0)));

            buttonPanel.add(next);
            next.addActionListener(new calendarButtonListener());
        }
        
        else 
        {
            buttonPanel.add(previous);
            previous.addActionListener(new calendarButtonListener());
            buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonPanel.add(monthLabel);
            buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            buttonPanel.add(next);
            next.addActionListener(new calendarButtonListener());
        }
        
        return buttonPanel;
    }   
    
    public JPanel calendarPanel(Border border) 
    {
        mainCalendarPanel.add(buttonPanel(),BorderLayout.NORTH);
        mainCalendarPanel.add(showCalendar(),BorderLayout.CENTER);
    
        return mainCalendarPanel;
    }
    
    public void clearLabelsForMonth() 
    {           
        for (int i = 0; i < daysInMonth; i++) 
        {             
            dayList.get(i+gapMonth).setBorder(BorderFactory.createLoweredBevelBorder());
            dayList.get(i+gapMonth).remove(dayNums.get(i));
            dayList.get(i+gapMonth).setBackground(null);
            dayList.get(i+gapMonth).repaint();
        }
    }
        
    public void addLabelsForMonth()
    {      
        for (int i = 0; i < daysInMonth; i++) 
        {     
            if (gapMonth == 0)
                gapMonth = 7;
            dayNums.add(new JLabel(Integer.toString(i+1)));
            dayNums.get(i).setVerticalAlignment(JLabel.TOP);
            dayNums.get(i).setHorizontalAlignment(JLabel.RIGHT);
            dayList.get(i+gapMonth).add(dayNums.get(i),BorderLayout.NORTH);
            dayList.get(i+gapMonth).setBorder(BorderFactory.createRaisedBevelBorder());
            dayList.get(i+gapMonth).setBackground(Color.white);
            dayList.get(i+gapMonth).repaint();
        }      
    }
    
    public void createShiftBlock()
    {
        ShiftController sc = new ShiftController();
        
        
        shiftList = sc.getShiftObject();
        
        
        
    }
    
    // adding a physician to the calendar block
    public void addPhysicians()
    {
        for (int i = 0; i < daysInMonth; i++)
        {
            if (gapMonth == 0)
                gapMonth = 7;            
            physList.add(new JLabel(physicianName[1]));
            physList.get(i).setVerticalAlignment(JLabel.CENTER);
            physList.get(i).setHorizontalAlignment(JLabel.CENTER);
            
            dayList.get(i+gapMonth).add(physList.get(i),BorderLayout.SOUTH);
            dayList.get(i+gapMonth).setBorder(BorderFactory.createRaisedBevelBorder());
            dayList.get(i+gapMonth).setBackground(Color.white);
            dayList.get(i+gapMonth).repaint();
            
        }
    }
        
    public JPanel getMainPanel() 
    {
        return calendarPanel;
    }
    
    public void setCurrentMonth(int m)
    {
        month = m;
    }
    
    public int getCurrentMonth()
    {
        return month;
    }
    
    public void setDayLabelText(int i, String day)
    {
        dayNums.get(i).setText(day);
    }
    
    public void setGapMonth(int gap)
    {
        gapMonth = gap;
    }
    
    public int getGapMonth()
    {
        return gapMonth;
    }
    
    public void setDaysInMonth(int numDays)
    {
        daysInMonth = numDays;
    }
    
    public int getDaysInMonth() 
    {
        return daysInMonth;
    }
        
    public void setMonth(String m)
    {
        monthText = m;
    }
    
    public String getMonth() 
    {
        return monthText;
    }
    
    public ArrayList getDayList() 
    {
        return dayList;
    }
    
    // function to set constraints on component
    private void set_gridBagConstraints(int left_right, int top_bottom , int gridwidth, int gridheight, double width, double height, int fill, int anchor)
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
            if (panelName == "Days Off") 
            {
                int panelPressed = dayList.indexOf(e.getSource());

                if (panelPressed < gapMonth || panelPressed > gapMonth + daysInMonth - 1)
                {
                    dayList.get(panelPressed).setBackground(null);
                }
                else 
                {
                    if (dayList.get(panelPressed).getBackground() == Color.red)
                    {
                        dayList.get(panelPressed).setBackground(Color.white);                   
                    }
                    else 
                    {
                        dayList.get(panelPressed).setBackground(Color.red);                   
                        String[] d = monthText.split(",");                    
                        System.out.println(d[0] + " " + (panelPressed-gapMonth + 1) + "," + d[1]);                   
                    }
                }
            }
            else if (panelName == "Scheduling Calendar") 
            {
                // clicked on the white space.
                // bring the physician thats in this position
                // push it to 
                System.out.println("Scheduling Calendar");
            }
        }
    }   
    
    private class calendarButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == next)
            {
                clearLabelsForMonth();
                ++m;
                Calendar cal = new GregorianCalendar();
                cal = Calendar.getInstance();
                cal.set(Calendar.DATE,1);
                cal.set(Calendar.MONTH,Calendar.MONTH+m);
                cal.set(Calendar.YEAR,2013);

                cal.set(Calendar.DAY_OF_MONTH, 1);

                DateFormat df = new SimpleDateFormat("MMMM, YYYY");

                gapMonth = cal.get(Calendar.DAY_OF_WEEK) - 1;
                daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                monthText = df.format(cal.getTime());
                monthLabel.setText(monthText);

                addLabelsForMonth();             
            }
            else if (e.getSource() == previous) 
            {  
                clearLabelsForMonth();
                --m;
                Calendar cal = new GregorianCalendar();
                cal = Calendar.getInstance();
                cal.set(Calendar.DATE,1);
                cal.set(Calendar.MONTH,Calendar.MONTH+m);
                cal.set(Calendar.YEAR,2013);

                cal.set(Calendar.DAY_OF_MONTH, 1);

                DateFormat df = new SimpleDateFormat("MMMM, YYYY");
                
                gapMonth = cal.get(Calendar.DAY_OF_WEEK) - 1;
                daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                monthText = df.format(cal.getTime());
                monthLabel.setText(monthText);

                addLabelsForMonth();                   
            }
        }
    }
}
