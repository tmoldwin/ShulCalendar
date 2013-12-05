package OrTorahCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Date implements Comparable<Date>{
int month;
int day1;
int year;


public Date(String date1){
	String[] strings=date1.split("/");
	month=Integer.parseInt(strings[0]);
	day1=Integer.parseInt(strings[1]);
	year=Integer.parseInt(strings[2]);
}

public Date(int m, int d, int y){
	month=m;
	day1=d;
	year=y;
	
}

public String toString(){
	return month+"/"+day1+"/"+year;
}

@Override
public int compareTo(Date d2) {
	if(month==d2.month&&day1==d2.day1&&year==d2.year)
	return 0;
	else if(month>d2.month||(month==d2.month&&day1>d2.day1)) return 1;
	else if(month<d2.month||(month==d2.month&&day1<d2.day1)) return -1;
	return 0;
}

public String getStringDayOfWeek(){
	if(getDayOfWeek()==1) return "Sunday";
	if(getDayOfWeek()==2) return "Monday";
	if(getDayOfWeek()==3) return "Tuesday";
	if(getDayOfWeek()==4) return "Wednesday";
	if(getDayOfWeek()==5) return "Thursday";
	if(getDayOfWeek()==6) return "Friday";
return "Shabbos";

}
public int getDayOfWeek(){
	Calendar cal=new GregorianCalendar(year, month-1, day1);
	return cal.get(Calendar.DAY_OF_WEEK);
}
//You will have to change this for future years to reflect daylight savings
public boolean isSummer(){
if (this.compareTo(new Date(3,13,2011))>0&&this.compareTo(new Date(11,6,2011))<0)return true;
return false;
}

public static void main(String[] args){
Date a=new Date(9,10,2010);
System.out.println((a.getDayOfWeek())+", "+a.toString());
}
}
