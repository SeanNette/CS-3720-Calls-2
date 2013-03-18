/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

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
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import sun.misc.GC;
import sun.util.calendar.Gregorian;

/**
 *
 * @author Wojg
 */
public class MainPanel extends JPanel {

    private JPanel mainCalendarPanel;
    private JPanel topCalendarPanel;
    private JPanel calendarPanel;
    private JPanel headerPanel;
    private JPanel nextButtonPanel,prevButtonPanel,dateLabelPanel;
    
    private JPanel[][] dayPanels;
    private JPanel[][] dayNumPanel;
    
    private JLabel[][] dayNums;
    private JLabel[] header;
    
    private JButton previous, next;
    private JLabel monthLabel;
    
    
    String str_date = "";
    int i = 0;

    public MainPanel() {
    }

    public JPanel createMainCalendarPanel() {
        
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        mainCalendarPanel = new JPanel(new GridBagLayout());
        
        topCalendarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 170, 5));

        GridBagConstraints c = new GridBagConstraints();

        previous = new JButton("Previous");

        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recompute(--i);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recompute(++i);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        monthLabel = new JLabel(str_date);
        
        nextButtonPanel = new JPanel();
        prevButtonPanel = new JPanel();
        dateLabelPanel = new JPanel();
        
        nextButtonPanel.add(next);
        dateLabelPanel.add(monthLabel);
        prevButtonPanel.add(previous);
        
        topCalendarPanel.add(prevButtonPanel);
        topCalendarPanel.add(dateLabelPanel);
        topCalendarPanel.add(nextButtonPanel);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,50,0,50);
        c.fill = GridBagConstraints.BOTH;
        mainCalendarPanel.add(topCalendarPanel, c);

        String[] day_of_week = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        headerPanel = new JPanel(new GridLayout(0, 7));
        header = new JLabel[7];
        for (int i = 0; i < day_of_week.length; i++) {
            header[i] = new JLabel(day_of_week[i], JLabel.CENTER);
            header[i].setBorder(raisedBevel);
            header[i].setPreferredSize(new Dimension(80, 20));
            headerPanel.add(header[i]);
        }
        c.gridx = 0;
        c.gridy = 1;
        mainCalendarPanel.add(headerPanel, c);

        calendarPanel = new JPanel(new GridLayout(6, 7));
        dayPanels = new JPanel[6][7];
        dayNums = new JLabel[6][7];
        dayNumPanel = new JPanel[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                dayPanels[i][j] = new JPanel(new BorderLayout());
                dayPanels[i][j].setBorder(raisedBevel);
                dayPanels[i][j].setPreferredSize(new Dimension(80, 80));               
                dayNums[i][j] = new JLabel(); 
                dayNums[i][j].setHorizontalAlignment(JLabel.RIGHT);
                dayNums[i][j].setVerticalAlignment(JLabel.TOP);                
                dayPanels[i][j].add(dayNums[i][j]);                
                calendarPanel.add(dayPanels[i][j]);
            }
        }
        
        c.gridx = 0;
        c.gridy = 2;
        mainCalendarPanel.add(calendarPanel, c);

        recompute(0);

        return mainCalendarPanel;
    }

    public void recompute(int value) {
        
        Calendar calendar = Calendar.getInstance();
        
        // add month to calendar object
        calendar.add(Calendar.MONTH, value);
        
        // convert current timestamp to a number
        long milli_month = calendar.getTimeInMillis();
        
        // creates a date object with converted date
        Date d = new Date(milli_month);
        
        // for converting date object to a readable format
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM, YYYY");
        str_date = sdf.format(d);
        
        // sets the month label
        monthLabel.setText(str_date);
        
        // number of days in the current month
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // sets the calendar to today's month
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        
        // calculates where the month begins - gapMonth equals the weekDay(0-Sun,etc)
        int gapMonth = calendar.get(Calendar.DAY_OF_WEEK);

        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        
        // assign all panels to have a raised bevel
        for (int i = 0; i < 6;i++){
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
}
