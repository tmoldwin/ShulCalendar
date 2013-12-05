package OrTorahCalendar;

public class Minute {
int minutes;

public Minute(int m){
	minutes=m;
}

@Override
public String toString() {
	if (minutes>=10)
	return Integer.toString(minutes);
	else return Integer.toString(0)+Integer.toString(minutes);
}

public int getMinutes() {
	return minutes;
}

public void setMinutes(int minutes) {
	this.minutes = minutes;
}


}
