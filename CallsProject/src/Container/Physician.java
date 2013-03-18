package Container;

import java.util.Comparator;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dariusz
 */
public class Physician
{
    private int employeeId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String startDate;
    private String endDate;
    private String address;
    private String phoneNumber;
    private int previousHours, curHours;
    
    public Physician()
    {
        // constructor
    }
    
    public Physician(int employeeId, String firstName, String lastName, String birthDate, String startDate,
               String endDate, String address, String phoneNumber)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Physician(int employeeId, String firstName, String lastName, String birthDate, String startDate,
               String endDate, String address, String phoneNumber, int previousHours)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = address;
        this.previousHours = previousHours;
        this.curHours = 0;
    }

    public Physician(int employeeId, int previousHours, int currentHours)
    {
        this.employeeId = employeeId;
        this.previousHours = previousHours;
        this.curHours = currentHours;
    }
    
    public int getPreviousHours()
    {
        return previousHours;
    }

    public int getCurHours()
    {
        return curHours;
    }

    public void setPreviousHours(int previousHours)
    {
        this.previousHours = previousHours;
    }

    public void setCurHours(int curHours)
    {
        this.curHours = curHours;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString()
    {
        return "Physician{" + "employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", startDate=" + startDate + ", endDate=" + endDate + ", address=" + address + ", phoneNumber=" + phoneNumber + ", previousHours=" + previousHours + ", curHours=" + curHours + '}';
    }    
    
    
}
