/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Broker.PhysicianBroker;
import Container.Physician;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Wojg
 */
public class PhysicianModelController extends AbstractTableModel 
{
    PhysicianBroker pb;
    
    private ArrayList<String> columnNames;
    
    private ArrayList<Physician> ph;

    public PhysicianModelController() 
    { 
        pb = PhysicianBroker.getPhysicianBroker();
        ph = pb.getAllPhysicians();
        
        columnNames = new ArrayList<>();
        
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Phone Number");   
    }    
        
    @Override
    public int getRowCount() {
        return ph.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }
    
    @Override
    public String getColumnName(int col)
    {        
        return columnNames.get(col);
    }   
    
    public Physician getPhysicianObject(int rowIndex)
    {
        return ph.get(rowIndex);
    }
    
    @Override
    public String getValueAt(int rowIndex, int columnIndex) 
    {          
        Physician p = ph.get(rowIndex);
        switch (columnIndex)
        {              
            case 0:
                return p.getFirstName();
            case 1:
                return p.getLastName();            
            case 2:
                return p.getPhoneNumber();
            default:
                return "Incorrect input";                          
        }
    }
    
    /**
     *
     * @param p
     */
    public void addRow(Physician p) 
    {
        ph.add(p);
        int row = ph.size();
        fireTableRowsInserted(row - 1,row - 1);
        fireTableDataChanged();
    }
    
    public void deleteRow(Physician p)
    {
        ph.remove(p);
        int row = ph.indexOf(p);
        fireTableRowsDeleted(row, row);
        fireTableDataChanged();
    }
    
    public void setValueAt(String fname, String lname, String bdate, 
                           String sdate, String edate, String address, 
                           String phone, int rowIndex)
    {
        Physician p = ph.get(rowIndex);
        
        p.setFirstName(fname);
        p.setLastName(lname);
        p.setBirthDate(bdate);
        p.setStartDate(sdate);
        p.setEndDate(edate);
        p.setAddress(address);
        p.setPhoneNumber(phone);
        fireTableRowsUpdated(rowIndex, rowIndex);
        fireTableDataChanged();
        
    }
    
    @Override
    public Class getColumnClass(int c)
    {
        return getValueAt(0,c).getClass();
    }
    
}
