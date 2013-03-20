/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Broker.PhysicianBroker;
import Container.Physician;
import Container.Shift;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        //for(int k =0; k < 60; k++)
        //{
        Collections.sort(physicians, new PhysicianComparator());
        int latestWeekend = physicians.get(physicians.size() - 1).getEmployeeId();
        System.out.println("Latest Weekend: " + latestWeekend);
        boolean found = false;

        for (int i = 0; i < shifts.size(); i++)
        {
            /*for(int j = 0; j < physicians.size(); j++)
            {
                System.out.println(physicians.get(j).toString());
            }*/
            int counter = 0;
            while (!found)
            {
                //TODO check if physician at counter has day off
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
                        if(shifts.get(i).getType() == 2)
                            physicians.get(counter).addHours(WEEKEND);
                        else
                            physicians.get(counter).addHours(WEEKDAY);
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
                counter++;
            }
            found = false;
            Collections.sort(physicians, new PhysicianComparator());
        }
        /*for(int j = 0; j < physicians.size(); j++)
        {
            System.out.println(physicians.get(j).toString());
            physicians.get(j).setPreviousHours(physicians.get(j).getCurHours());
            physicians.get(j).setTotalHours(physicians.get(j).getCurHours() + physicians.get(j).getTotalHours());
            physicians.get(j).setCurHours(0);
        }*/
        /*for(int k = 0; k < shifts.size(); k++)
        {
            System.out.println(shifts.get(k).toString());
        }*/
        
        //}
        /*System.out.println("Testing");
        for(int j = 0; j < physicians.size(); j++)
            {
                System.out.println(physicians.get(j).toString());
            }*/
        
        //update database for physician's hours worked
        PhysicianBroker.getPhysicianBroker().updatePhysicianHours(m, y, physicians);
        return shifts;
    }
}
