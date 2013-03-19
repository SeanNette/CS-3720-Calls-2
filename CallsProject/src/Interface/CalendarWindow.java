/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dariusz
 */
public class CalendarWindow {

    private JPanel mainCalendarPanel;
    private JPanel topCalendarPanel;
    private JPanel calendarPanel;
    private JPanel headerPanel;
    private JPanel[][] dayPanels = new JPanel[6][7];
    private JLabel[][] dayNums = new JLabel[6][7];
    private JLabel[] header;
    private String str_date /*= ""*/;
    private JButton previous, next;
    private JLabel monthLabel = new JLabel();
    private int i = 0;
    private Calendar calendar;
    
    private JLabel currPhysLabel, newPhysLabel, no_of_hoursLabel;
    private JTextField currPhysTextField, no_of_hoursTextField;
    
    private JComboBox newPhysCombo;
    
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
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        Border legendBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Legend"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        Border empty = null;

        JPanel calendarWindow = new JPanel(/*new GridBagLayout()*/new BorderLayout());
        calendarWindow.setBorder(paneEdge);

        // GridBagConstraints c = new GridBagConstraints();

        /**
         * Calendar Panel location ******************************************
         */
        /* c.anchor = GridBagConstraints.WEST;
         c.gridx = 0;
         c.gridy = 0;  
         c.fill = GridBagConstraints.BOTH;
         c.weightx = 1.0;
         c.weighty = 1.0;*/
        calendarWindow.add(calendarPanel(blackline), BorderLayout.CENTER);
        /**
         * *******************************************************************
         */
        /**
         * Field Panel location *********************************************
         */
        /*     c.fill = GridBagConstraints.NONE;
         c.gridx = 0;
         c.gridy = 1;
         c.weightx = 0;
         c.weighty = 0;
         c.fill = GridBagConstraints.HORIZONTAL;*/
        calendarWindow.add(fieldPanel(fieldPanelBorder), BorderLayout.SOUTH);
        /**
         * *******************************************************************
         */
        /**
         * Button Panel location ********************************************
         */
        /*     c.gridx = 0;
         c.gridy = 2;
         c.anchor = GridBagConstraints.EAST;
         c.fill = GridBagConstraints.HORIZONTAL;
         // CHANGE BLACKLINE BORDER TO empty so the button panel does not have a border*/
        calendarWindow.add(buttonPanel(blackline), BorderLayout.NORTH);
        /**
         * *******************************************************************
         */
        calendarWindow.add(legendPanel(legendBorder), BorderLayout.WEST);
        
        calendarWindow.add(sidePanel(blackline),BorderLayout.EAST);
        return calendarWindow;
    }

    private JPanel calendarPanel(Border border) {       
        mainCalendarPanel = new JPanel(new GridBagLayout());
        mainCalendarPanel.setBorder(border);
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        
        GridBagConstraints c = new GridBagConstraints();
       
        String[] day_of_week = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        headerPanel = new JPanel(new GridLayout(0, 7));
        header = new JLabel[7];
        for (int i = 0; i < day_of_week.length; i++) {
            header[i] = new JLabel(day_of_week[i], JLabel.CENTER);
            header[i].setBorder(raisedBevel);
            header[i].setPreferredSize(new Dimension(90, 20));
            headerPanel.add(header[i]);
        }
        c.gridx = 0;
        c.gridy = 0;
        mainCalendarPanel.add(headerPanel, c);

        calendarPanel = new JPanel(new GridLayout(6, 7));
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                dayPanels[i][j] = new JPanel(new BorderLayout());
                dayPanels[i][j].setBorder(raisedBevel);
                dayPanels[i][j].setPreferredSize(new Dimension(90, 80));               
                dayNums[i][j] = new JLabel(); 
                dayNums[i][j].setHorizontalAlignment(JLabel.RIGHT);
                dayNums[i][j].setVerticalAlignment(JLabel.TOP);                
                dayPanels[i][j].add(dayNums[i][j]);                
                calendarPanel.add(dayPanels[i][j]);
            }
        }
             
        c.gridx = 0;
        c.gridy = 1;
        
        recompute(0);   
        mainCalendarPanel.add(calendarPanel, c);        
                
        return mainCalendarPanel;
    }

    private JPanel fieldPanel(Border border) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setBorder(border);
        
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        // sets spaces between labels and textfields to 2 pixels
        c.insets = new Insets(2, 2, 2, 2);
        fieldPanel.setLayout(gridbag);

        /**
         * ** Current Physician ************************************************
         */
        currPhysLabel = new JLabel("Current Physician: ");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        // sets 
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.BOTH;
        fieldPanel.add(currPhysLabel, c);

        currPhysTextField = new JTextField(16);
        currPhysTextField.setEditable(false);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
      //  currPhysTextField.add(currPhysTextField);
        fieldPanel.add(currPhysTextField, c);
        /**
         * *******************************************************************
         */
        /**
         * ** New Physician ****************************************************
         */
        newPhysLabel = new JLabel("New Physician: ");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(newPhysLabel, c);
        
        newPhysCombo = new JComboBox();
        // create function to show all physicians in db and populate combox box with their first names
        c.gridx =0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
       // allTextFields.add(textFieldLname);
        fieldPanel.add(newPhysCombo, c);
        /*
         * *******************************************************************
         */
        /*
         * ** Number of Hours **************************************************
         */
        no_of_hoursLabel = new JLabel("Number of Hours: ");
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        fieldPanel.add(no_of_hoursLabel, c);

        no_of_hoursTextField = new JTextField(16);
        no_of_hoursTextField.setEditable(false);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
       // allTextFields.add(textFieldBdate);
        fieldPanel.add(no_of_hoursTextField, c);
        /*
         * *******************************************************************
         */
        
        /*
         * ** Comments *********************************************************
         */
        JTextArea comments = new JTextArea(5,10);
        JScrollPane scrollPane = new JScrollPane(comments);
       // c.fill = GridBagConstraints.HORIZONTAL;
        //c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
       
        c.fill = GridBagConstraints.HORIZONTAL;
        fieldPanel.add(scrollPane,c);
        /*
         * *******************************************************************
         */
        return fieldPanel;
    }
    
    private JPanel sidePanel(Border border) {
        
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel,BoxLayout.Y_AXIS));
        sidePanel.setBorder(border);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton generateButton = new JButton("Generate");
        JButton useButton = new JButton("Use");
        
        sidePanel.add(generateButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(useButton);
        return sidePanel;
    }
    
    private JPanel buttonPanel(Border border) {
        JPanel buttonPanel = new JPanel(new FlowLayout(/*FlowLayout.CENTER, 170, 5)*/));

        buttonPanel.setBorder(border);

        previous = new JButton("Previous");
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recompute(--i);
            }
        });

        next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recompute(++i);
            }
        });
        
        Calendar calendar = Calendar.getInstance();
        long milli_month = calendar.getTimeInMillis();
        
        str_date = setDateText(milli_month);
        monthLabel = new JLabel(str_date);

        buttonPanel.add(previous);
        buttonPanel.add(Box.createRigidArea(new Dimension(190, 0)));
        buttonPanel.add(monthLabel);
        buttonPanel.add(Box.createRigidArea(new Dimension(190, 0)));
        buttonPanel.add(next);

        return buttonPanel;
    }

    private JPanel legendPanel(Border border) {
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBorder(border);
        legendPanel.setPreferredSize(new Dimension(100, 500));  
        
        int no_of_phys = 5;
        for (int i = 0; i < no_of_phys; i++) 
        {     
            //JPanel physPanels = new JPanel();
            JLabel legendLabels = new JLabel("Physician " + i);
            legendLabels.setOpaque(true);
            
            Random rand = new Random();
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
           // System.out.println(r + " " + g + " " + " " + b);
           // legendLabels.setBorder(border);
            
            //physPanels.add(legendLabels);
            legendLabels.setBackground(new Color(r, g, b));
            //physPanels.setBackground(new Color(r, g, b));
            legendPanel.add(legendLabels);
        }
        
        return legendPanel;
    }

    public void recompute(int value) {

        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, value);
        
        long milli_month = calendar.getTimeInMillis();
        String test = setDateText(milli_month);
                
        monthLabel.setText(test);
        
        // number of days in the current month
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // sets the calendar to today's month
        calendar.set(Calendar.DAY_OF_MONTH, 0);

        // calculates where the month begins - gapMonth equals the weekDay(0-Sun,etc)
        int gapMonth = calendar.get(Calendar.DAY_OF_WEEK);

        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();

        // assign all panels to have a raised bevel
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                dayPanels[i][j].setBorder(raisedBevel);
            }
        }

        // get rid of any text in the beginning of the month
        for (int i = 0; i < gapMonth; i++) {
            dayPanels[0][i].setBorder(loweredBevel);
            dayNums[0][i].setText("");
        }

        // set each label with corresponding day number
        for (int i = 1; i <= daysInMonth; i++) {
            JLabel l = dayNums[(gapMonth + i - 1) / 7][(gapMonth + i - 1) % 7];
            l.setText(Integer.toString(i));
        }

        // remove text from label for the end of the month and have a lowered bevel
        for (int i = gapMonth + daysInMonth; i < 6 * 7; i++) {
            dayNums[(i) / 7][(i) % 7].setText("");
            dayPanels[(i) / 7][(i) % 7].setBorder(loweredBevel);
        }
    }

    public String setDateText(long milli_month) {
        
        // creates a date object with converted date
        Date d = new Date(milli_month);

        // for converting date object to a readable format
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM, YYYY");
        str_date = sdf.format(d);
        
        return str_date;
    }
}
