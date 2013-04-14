/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Container.Physician;
import Controller.CalendarController;
import Controller.PhysicianController;
import Controller.PhysicianModelController;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Wojg
 */
public class PhysicianWindow extends JPanel 
{

    private JTable table;
    private JScrollPane scrollPane;
    private JPanel physicianWindow;
    private JLabel labelFname;
    private JTextField textFieldFname;
    private JLabel labelLname;
    private JTextField textFieldLname;
    private JLabel labelBdate;
    private JFormattedTextField textFieldBdate;
    private JLabel labelSdate;
    private JFormattedTextField textFieldSdate;
    private JLabel labelEdate;
    private JFormattedTextField textFieldEdate;
    private JLabel labelAddress;
    private JTextField textFieldAddress;
    private JLabel labelPhone;
    private JFormattedTextField textFieldPhone;
    
    private JButton addButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    
    private int selectedRowIndex;
        
    PhysicianModelController pmc;
    CalendarController cc;   

    public PhysicianWindow() 
    {        
        pmc = new PhysicianModelController();
        cc = new CalendarController(2, 2013, "Days Off", "small");
       
        labelFname = new JLabel("First Name: ");
        textFieldFname = new JTextField(16);
        
        labelLname = new JLabel("Last Name: ");
        textFieldLname = new JTextField(16);
        
        labelBdate = new JLabel("Birth Date: ");
        
        
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        textFieldBdate = new JFormattedTextField(df);
        textFieldBdate.setColumns(10);
        try {
            MaskFormatter bdateFormat = new MaskFormatter("####-##-##");
            bdateFormat.setValidCharacters("0123456789");
            bdateFormat.install(textFieldBdate);
        } catch (ParseException ex) {
            Logger.getLogger(PhysicianWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      //  textFieldBdate = new JTextField(16);
        labelSdate = new JLabel("Start Date: ");
        MaskFormatter edateFormat;
        try {
            edateFormat = new MaskFormatter("####-##-##");
            edateFormat.setValidCharacters("0123456789");
            textFieldSdate = new JFormattedTextField(edateFormat);
            textFieldSdate.setColumns(10);
        } catch (ParseException ex) {
            Logger.getLogger(PhysicianWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        labelEdate = new JLabel("End Date: ");
        MaskFormatter sdateFormat;
        try {
            sdateFormat = new MaskFormatter("####-##-##");
            sdateFormat.setValidCharacters("0123456789");
            textFieldEdate = new JFormattedTextField(sdateFormat);
            textFieldEdate.setColumns(10);
        } catch (ParseException ex) {
            Logger.getLogger(PhysicianWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        labelAddress = new JLabel("Address: ");
        textFieldAddress = new JTextField(16);
        
        labelPhone = new JLabel("Phone: ");
        MaskFormatter phoneNumberFormat;
        try {
            phoneNumberFormat = new MaskFormatter("(###) ###-####");
            phoneNumberFormat.setValidCharacters("0123456789");
            textFieldPhone = new JFormattedTextField(phoneNumberFormat);
            textFieldPhone.setColumns(14);
        } catch (ParseException ex) {
            Logger.getLogger(PhysicianWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
    }

    public JPanel createPhysicianWindow() 
    {        
        physicianWindow = new JPanel(new MigLayout("", "[]15[right]5[grow,fill]5[right]5[grow,fill]5[right]5[grow,fill]"));
        physicianWindow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));   
 
        // create the table with the current model
        table = new JTable(pmc);
        // mouse listener for table rows
        table.addMouseListener(new MouseListen());
        // fills the entire table pane 
        table.setFillsViewportHeight(true);
        // make the table scrollable
        scrollPane = new JScrollPane(table);
        // set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(450, 150));
        
        physicianWindow.add(scrollPane, "spany, growy, wmin 50"); 
        
        physicianWindow.add(labelFname);
        physicianWindow.add(textFieldFname);
        physicianWindow.add(labelLname);
        physicianWindow.add(textFieldLname);
        physicianWindow.add(labelBdate);
        physicianWindow.add(textFieldBdate, "wrap");
        physicianWindow.add(labelSdate);
        physicianWindow.add(textFieldSdate);
        physicianWindow.add(labelEdate);
        physicianWindow.add(textFieldEdate, "wrap");
        physicianWindow.add(labelAddress);
        physicianWindow.add(textFieldAddress, "span 2, wrap");
        physicianWindow.add(labelPhone);
        physicianWindow.add(textFieldPhone, "wrap");
        
        physicianWindow.add(addButton, "tag add, align right, span, split");
        physicianWindow.add(saveButton, "tag save");
        physicianWindow.add(deleteButton, "tag delete");
        physicianWindow.add(clearButton, "tag clear, wrap");  
        
        addButton.addActionListener(new ButtonsListen());
        saveButton.addActionListener(new ButtonsListen());
        saveButton.setEnabled(false);
        deleteButton.addActionListener(new ButtonsListen());
        deleteButton.setEnabled(false);
        clearButton.addActionListener(new ButtonsListen());
        clearButton.setEnabled(false);        
       
        physicianWindow.add(cc.calendarPanel(null),"align left, width 200:200, span");
        
        return physicianWindow;
    }
   
    private class MouseListen extends MouseAdapter 
    {

        @Override
        public void mouseClicked(MouseEvent e) 
        {
            selectedRowIndex = table.getSelectedRow();  
            
            Physician p = pmc.getPhysicianObject(selectedRowIndex);
            
            textFieldFname.setText(p.getFirstName());
            textFieldLname.setText(p.getLastName());
            textFieldBdate.setText(p.getBirthDate());
            textFieldSdate.setText(p.getStartDate());
            textFieldEdate.setText(p.getEndDate());
            textFieldAddress.setText(p.getAddress());
            textFieldPhone.setText(p.getPhoneNumber());
            
            addButton.setEnabled(false);
            saveButton.setEnabled(true);
            deleteButton.setEnabled(true);
            clearButton.setEnabled(true);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class ButtonsListen implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
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
                        
            if (e.getSource() == addButton) 
            {
                try 
                {
                    choice = 1;
                    done = phys.workPhysician(choice, 0, fname, lname, bdate, sdate, edate, address, phone);                     
                    pmc.addRow(new Physician(0,fname,lname,bdate,sdate,edate,address,phone)); 
                    textFieldFname.setText(null);
                    textFieldLname.setText(null);
                    textFieldAddress.setText(null);
                    textFieldBdate.setText(null);
                    textFieldEdate.setText(null);
                    textFieldPhone.setText(null);
                    textFieldSdate.setText(null);
                } 
                catch (Exception ex) 
                {
                    System.out.println("Add physician error: " + ex);
                }
            } 
            
            else if (e.getSource() == saveButton) 
            {
                try 
                {
                    Physician p = pmc.getPhysicianObject(selectedRowIndex);
                    String id = Integer.toString(p.getEmployeeId());
                    //String id = table.getValueAt(selectedRowIndex, 0).toString();
                    choice = 2;                    
                    done = phys.workPhysician(choice, Integer.parseInt(id), fname, lname, bdate, sdate, edate, address, phone);
                    pmc.setValueAt(fname,lname,bdate,sdate,edate,address,phone, selectedRowIndex);             
                }                 
                catch (Exception ex) 
                {
                    System.out.println("Save physician error: " + ex);
                }
            } 
            
            else if (e.getSource() == deleteButton) 
            {               
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
                if (resp == 0) 
                {
                    Physician p = pmc.getPhysicianObject(selectedRowIndex);
                    String id = Integer.toString(p.getEmployeeId());
                    done = phys.workPhysician(choice, Integer.parseInt(id));                    
                    pmc.deleteRow(p);
                    textFieldFname.setText(null);
                    textFieldLname.setText(null);
                    textFieldAddress.setText(null);
                    textFieldBdate.setText(null);
                    textFieldEdate.setText(null);
                    textFieldPhone.setText(null);
                    textFieldSdate.setText(null);
                }
                else 
                {
                    done = "Cancelled";
                    return;
                }
              
            } 
            else if (e.getSource() == clearButton) 
            {
                textFieldFname.setText(null);
                textFieldLname.setText(null);
                textFieldAddress.setText(null);
                textFieldBdate.setText(null);
                textFieldEdate.setText(null);
                textFieldPhone.setText(null);
                textFieldSdate.setText(null);

                addButton.setEnabled(true);
                saveButton.setEnabled(false);
                deleteButton.setEnabled(false);
                clearButton.setEnabled(false);
                return;
            }
            JOptionPane.showMessageDialog(null, done);
        }
    }    
}
