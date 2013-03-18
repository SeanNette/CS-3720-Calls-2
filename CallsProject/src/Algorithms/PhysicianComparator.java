/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Container.Physician;
import java.util.Comparator;

/**
 *
 * @author Sean
 */
public class PhysicianComparator implements Comparator<Physician>
{

    @Override
    public int compare(Physician o1, Physician o2)
    {
        if(o1.getCurHours() > o2.getCurHours())
            return 1;
        else if(o1.getCurHours() < o2.getCurHours())
            return -1;
        else
        {
            if(o1.getPreviousHours() > o2.getPreviousHours())
                return 1;
            else if(o1.getPreviousHours() < o2.getPreviousHours())
                return -1;
            else
                return 0;
        }
    }
    
}
