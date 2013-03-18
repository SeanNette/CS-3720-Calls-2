/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Container.Physician;
import Container.Shift;
import java.util.List;
import jp.ac.kobe_u.cs.cream.*;

/**
 *
 * @author Evan
 */
public class CSPalgorithm
{

    private List<Shift> shifts;
    private List<Physician> docs;
    private int month, year;

    public CSPalgorithm(int m, int y)
    {
        this.month = m;
        this.year = y;

        //generate list of shifts for specified month and year
    }

    public List<Shift> generateSchedule()
    {
        // CSP code goes here
        // start by declaring a List of Shift objects
        Network net = new Network();
        
        return null;
    }
}
