/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Broker.ShiftBroker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Sean
 */
public class HolidayController
{
    public void generateHolidays(int year)
    {
        ArrayList<String> holidays = new ArrayList();
        /*String[] dates = date.split("-");
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);*/
        
        //add new year's
        holidays.add(year + "-01-01");
        
        
        //calculate Family Day
        Calendar cal = new GregorianCalendar(year, 1, 1);
        int familyDay;
        while(cal.get(Calendar.DAY_OF_WEEK) != 2)
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        }
        familyDay = cal.get(Calendar.DAY_OF_MONTH) + 14;
        //add Family Day
        holidays.add(year + "-02-" + familyDay);
        
        //calculate Easter Sunday
        int[] easter = getEasterSunday(year);
        //calculate Good Friday
        int goodFridayMonth = easter[0];
        int goodFriday = easter[1] - 2;
        if(goodFriday <= 0)
        {
            goodFridayMonth -= 1;
            goodFriday = 31 + goodFriday;
        }
        //add good friday
        holidays.add(year + "-" + goodFridayMonth + "-" + goodFriday);
        
        //calculate Easter Monday
        int easterMondayMonth = easter[0];
        int easterMonday = easter[1] + 1;
        cal = new GregorianCalendar(year, easterMondayMonth - 1, 1);
        int numDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(easterMonday > numDays)
        {
            easterMondayMonth += 1;
            easterMonday = 1;
        }
        //add easter monday
        holidays.add(year + "-" + easterMondayMonth + "-" + easterMonday);
        
        //calculate Victoria Day
        cal = new GregorianCalendar(year, 4, 24);
        int victoriaDay;
        while(cal.get(Calendar.DAY_OF_WEEK) != 2)
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
        }
        victoriaDay = cal.get(Calendar.DAY_OF_MONTH);
        //add Victoria Day
        holidays.add(year + "-05-" + victoriaDay);
        
        //add Canada Day
        holidays.add(year + "-07-01");
        
        //calculate Labour Day
        cal = new GregorianCalendar(year, 8, 1);
        int labourDay;
        while(cal.get(Calendar.DAY_OF_WEEK) != 2)
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        }
        labourDay = cal.get(Calendar.DAY_OF_MONTH);
        //add Labour Day
        holidays.add(year + "-09-" + labourDay);
        
        //calculate Thanksgiving
        cal = new GregorianCalendar(year, 9, 1);
        int thanksgiving;
        while(cal.get(Calendar.DAY_OF_WEEK) != 2)
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        }
        thanksgiving = cal.get(Calendar.DAY_OF_MONTH) + 7;
        //add Thanksgiving
        holidays.add(year + "-10-" + thanksgiving);
        
        //add Remembrance Day
        holidays.add(year + "-11-11");
        
        //add Christmas Day
        holidays.add(year + "-12-25");
        
        for(int i = 0; i < holidays.size(); i++)
        {
            ShiftBroker.getShiftBroker().addHoliday(holidays.get(i));
        }
    }
    
    private int[] getEasterSunday(int year)
    {
        int a = year % 19;
        int b = year / 100;
        int c = year % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int p = (h + l - 7 * m + 114) % 31;

        int month = (h + l - 7 * m + 114) / 31;
        int day = p + 1;
        int[] easter = {month, day};
        return easter;
    }
}
