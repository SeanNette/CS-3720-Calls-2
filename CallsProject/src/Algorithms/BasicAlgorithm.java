/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Broker.PhysicianBroker;
import Broker.ShiftBroker;
import Container.Physician;
import Container.Shift;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * BasicAlgorithm - Generates a schedule for a specified month using limited
 * backtracking.
 *
 * @author Sean
 */
public class BasicAlgorithm
{

    private ArrayList<Physician> physicians;
    private ArrayList<Shift> shifts;
    public static final int WEEKDAY = 15;
    public static final int WEEKEND = 24;

    /**
     *
     * @param phys
     * @param shifts
     */
    public BasicAlgorithm(ArrayList<Physician> phys, ArrayList<Shift> shifts)
    {
        this.physicians = phys;
        this.shifts = shifts;
    }

    public ArrayList<Shift> generateSchedule(int m, int y)
    {
        Collections.sort(physicians, new PhysicianComparator());
        int latestWeekend = physicians.get(physicians.size() - 1).getEmployeeId();
        System.out.println("Latest Weekend: " + latestWeekend);
        boolean found = false;
        int j = 0;
        //check if previous month has a roll over
        //check if first day is a weekend day or holiday
        if (shifts.get(0).getType() != 0)
        {
            //check if that is a friday and if so ignore and start schedule
            Calendar cal = new GregorianCalendar(y, m - 1, 1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            //if not a friday, get physician from last month and schedule until weekend is done
            if (cal.get(Calendar.DAY_OF_WEEK) != 6)
            {
                //get physician id from last month
                int id = ShiftBroker.getShiftBroker().getLastPhysician(m, y);
                if (id != -1)
                {
                    int k;
                    for (k = 0; k < physicians.size(); k++)
                    {
                        if (physicians.get(k).getEmployeeId() == id)
                        {
                            break;
                        }
                    }
                    latestWeekend = id;
                    while (shifts.get(j).getType() != 0)
                    {
                        shifts.get(j).setEmployeeID(id);

                        //find a way to update this physicians hours

                        if (k != physicians.size())
                        {
                            physicians.get(k).addHours(WEEKEND);
                        }
                        j++;
                    }
                }
            }
        }


        for (int i = j; i < shifts.size(); i++)
        {
            Collections.sort(physicians, new PhysicianComparator());

            int counter = 0;
            while (!found)
            {
                boolean working = false;
                /*String monthString;
                if (m < 10)
                {
                    monthString = "0" + Integer.toString(m);
                }
                else
                {
                    monthString = Integer.toString(m);
                }*/

                //Check start and end date for this physician
                System.out.println(counter);
                if (physicians.get(counter).getStartDate() != null)
                {
                    String[] startDates = physicians.get(counter).getStartDate().split("-");
                    /*System.out.println("Getting into startDate");
                    
                    System.out.println("Date: " + (i+1));
                    System.out.println("Start Day: " + startDates[2]);
                    
                    
                    
                    if (((Integer.parseInt(startDates[2])) > (i + 1)) || ((Integer.parseInt(startDates[1])) > m) || ((Integer.parseInt(startDates[0])) >= y))
                    {
                        working = false;
                    }*/
                    
                    if(y >= Integer.parseInt(startDates[0]))
                    {
                        if(m >= Integer.parseInt(startDates[1]))
                        {
                            if((i + 1) >= Integer.parseInt(startDates[2]))
                            {
                                working = true;
                            }
                        }
                    }
                }
                if (physicians.get(counter).getEndDate() != null)
                {
                    System.out.println("Getting into endDate");
                    String[] endDates = physicians.get(counter).getEndDate().split("-");
                    /*System.out.println("Date: " + (i+1));
                    System.out.println("End Day: " + endDates[2]);
                    if (((Integer.parseInt(endDates[2])) < (i + 1)) && ((Integer.parseInt(endDates[1])) <= m) && ((Integer.parseInt(endDates[0])) <= y))
                    {
                        working = false;
                    }*/
                    if(y >= Integer.parseInt(endDates[0]))
                    {
                        if(m >= Integer.parseInt(endDates[1]))
                        {
                            if((i + 1) >= Integer.parseInt(endDates[2]))
                            {
                                working = false;
                            }
                        }
                    }
                }
                //physicians.get(counter).getStartDate().
                //check if physician at counter has day off
                if (!(ShiftBroker.getShiftBroker().isDayOff(shifts.get(i).getDate(), physicians.get(counter).getEmployeeId())) && working)
                {
                    //checking if shift is a weekday shift
                    if (shifts.get(i).getType() == 0)
                    {
                        //put physician on shift
                        shifts.get(i).setEmployeeID(physicians.get(counter).getEmployeeId());

                        //System.out.println(shifts.get(i).toString());

                        physicians.get(counter).addHours(WEEKDAY);
                        found = true;
                    }
                    else
                    {
                        if (physicians.get(counter).getEmployeeId() != latestWeekend)
                        {
                            latestWeekend = physicians.get(counter).getEmployeeId();
                            shifts.get(i).setEmployeeID(physicians.get(counter).getEmployeeId());

                            //System.out.println(shifts.get(i).toString());

                            //checking if the friday is a holiday or regular friday
                            if (shifts.get(i).getType() == 2)
                            {
                                physicians.get(counter).addHours(WEEKEND);
                            }
                            else
                            {
                                physicians.get(counter).addHours(WEEKDAY);
                            }
                            //moving to next shift of weekend
                            i++;
                            while (i < shifts.size() && shifts.get(i).getType() != 0)
                            {
                                shifts.get(i).setEmployeeID(physicians.get(counter).getEmployeeId());

                                //System.out.println(shifts.get(i).toString());

                                physicians.get(counter).addHours(WEEKEND);
                                i++;
                            }
                            //setting shift back to last day of weekend
                            i--;
                            found = true;
                        }
                    }
                }
                counter++;
            }
            found = false;

        }
        PhysicianBroker.getPhysicianBroker().updatePhysicianHours(m, y, physicians);
        return shifts;
    }
}
