/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.CalendarController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
public class CalendarWindow 
{    
    private JPanel calendarWindow, fieldPanel, sidePanel, buttonPanel, legendPanel;
    private JButton previous, next, change, generate, use;
    private JLabel monthLabel;
      
    private JLabel currPhysLabel, newPhysLabel, no_of_hoursLabel, commentsLabel;
    private JTextField currPhysTextField, no_of_hoursTextField;
    
    private JComboBox newPhysCombo;
    private JScrollPane scrollPane;
    private JTextArea comments;
        
    private Border paneEdge, blackline, fieldPanelBorder, legendBorder, empty;
    private GridBagConstraints c;    
    
    CalendarController cd;
    
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
        sidePanel.setLayout(new BoxLayout(sidePanel,BoxLayout.Y_AXIS));
        sidePanel.setBorder(paneEdge);
        
        buttonPanel = new JPanel();
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(blackline);
        
        legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBorder(legendBorder);
        legendPanel.setPreferredSize(new Dimension(100, 500));
        
        cd = new CalendarController(2,2013, "Scheduling Calendar", "normal");
        c = new GridBagConstraints();
        
        monthLabel = new JLabel(cd.getMonth());
        
        currPhysLabel = new JLabel("Current Physician: ");
        currPhysTextField = new JTextField(12);
        newPhysLabel = new JLabel("New Physician: ");
        newPhysCombo = new JComboBox();
        no_of_hoursLabel = new JLabel("Hours: ");
        no_of_hoursTextField = new JTextField(4);
        comments = new JTextArea(5,18);
        scrollPane = new JScrollPane(comments);
        commentsLabel = new JLabel("Comments: ");
        
        change = new JButton("Change");
        generate = new JButton("Generate");
        use = new JButton("Use");
        previous = new JButton("Previous");
        next = new JButton("Next"); 
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
        calendarWindow.add(sidePanel(),BorderLayout.EAST);
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
         * ** Current Physician ************************************************
         */        
        addComponent(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,currPhysLabel);
        currPhysTextField.setEditable(false);
        addComponent(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,currPhysTextField);       
        /**
         * *******************************************************************
         */
        
        /**
         * ** New Physician ****************************************************
         */        
        addComponent(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,newPhysLabel);        
        addComponent(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,newPhysCombo);
        /*
         * *******************************************************************
         */
        
        /*
         * ** Number of Hours **************************************************
         */        
        addComponent(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,no_of_hoursLabel); 
        no_of_hoursTextField.setEditable(false);
        addComponent(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,no_of_hoursTextField);      
        /*
         * *********************************************************************
         */
        
        /*
         * ** Comments *********************************************************
         */  
        addComponent(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,commentsLabel);
        addComponent(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST,fieldPanel,scrollPane);       
        /*
         * *********************************************************************
         */
        
        /*
         * ** Change Button ****************************************************
         */        
        addComponent(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.EAST,fieldPanel,change);          
        /*
         * *********************************************************************
         */
   
        return fieldPanel;
    }
    
    private JPanel sidePanel()
    {
        
        sidePanel.add(generate);
      //  generate.addActionListener(new ButtonListener()); 
        
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        sidePanel.add(use);
      //  use.addActionListener(new ButtonListener());
        
        return sidePanel;
    }
    
    private JPanel legendPanel() 
    {
        
        int no_of_phys = 5;
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
        }
        
        return legendPanel;
    }
    
    private void addComponent(int x, int y , int gw, int gh, 
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
        
        cont.add(comp,c);        
    }
    
}