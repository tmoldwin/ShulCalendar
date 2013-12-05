package OrTorahCalendar;

public class Hour {
int hours;

public Hour(int i){
	hours=i;
}
public int getHours() {
	return hours;
}
public String toString(){
	return Integer.toString(hours);
}
public void setHours(int hours) {
	this.hours = hours;
}
}
