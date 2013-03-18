/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

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

    public ArrayList<Shift> generateSchedule()
    {
        Collections.sort(physicians, new PhysicianComparator());
        

        return null;
    }
}
