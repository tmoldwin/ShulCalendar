package OrTorahCalendar;

public class Time {
private Minute m;
private Hour h;

public Time(String s){

	h=new Hour(Integer.parseInt(s.split(":")[0]));
	m=new Minute(Integer.parseInt(s.split(":")[1]));
	
}
public Time(int hours,int minutes){
	h=new Hour(hours);
	m=new Minute(minutes);
}

public Time(Hour hours, Minute minutes){
	h=hours;
	m=minutes;
}
public Minute getM() {
	return m;
}
public void setM(Minute m) {
	this.m = m;
}
public Hour getH() {
	return h;
}
public void setH(Hour h) {
	this.h = h;
}
public String toString(){
	return h.toString()+":"+m.toString();
}
public void update(){
	while(m.getMinutes()>=60){
		h.setHours(h.getHours()+1);
		m.setMinutes(m.getMinutes()-60);
	}
	while(m.getMinutes()<0){
		m.setMinutes(60+m.getMinutes());
		h.setHours(h.getHours()-1);
	}
	while(h.getHours()>12){
		h.setHours(h.getHours()-12);
	}
}
//Rounds to the nearest five. For example, 5:36 becomes 5:35, and 7:38 becomes 7:40. 
public Time roundNearestFive(){
	Time t=new Time(new Hour(h.getHours()), new Minute(m.getMinutes()));
	int mod=m.getMinutes()%5;
	if (mod>=3)
		t.setM(new Minute(m.getMinutes()+(5-mod)));
	else t.setM(new Minute(m.getMinutes()-mod));
	t.update();
	return t;	
	}

/*Rounds up to the nearest 5 minute mark. For example, 5:41 will become 5:45. 
However, 5:40 will remain 5:40.
*/
public Time roundUp(){
	Time t=new Time(new Hour(h.getHours()), new Minute(m.getMinutes()));
	int mod=m.getMinutes()%5;
	if(mod!=0){
	t.setM(new Minute(m.getMinutes()+(5-mod)));
	t.update();
	}
	return t;	
}

/*This one will round up even if it's already divisible by 5.
For example, 8:00 becomes 8:05.
*/
public Time roundUpAlways(){
	Time t=new Time(new Hour(h.getHours()), new Minute(m.getMinutes()));
	int mod=m.getMinutes()%5;
	t.setM(new Minute(m.getMinutes()+(5-mod)));
	t.update();
	return t;	
}
/* Round the number down to the nearest 5. For example, 5:39 becomes 5:35,
 * but 5:20 will stay 5:20
 */

public Time roundDown(){
	Time t=new Time(new Hour(h.getHours()), new Minute(m.getMinutes()));
	int mod=m.getMinutes()%5;
	if(mod!=0){
	t.setM(new Minute(m.getMinutes()-mod));
	t.update();
	}
	return t;	
}

//Always rounds down. 8:00 becomes 7:55.
public Time roundDownAlways(){
	Time t=new Time(new Hour(h.getHours()), new Minute(m.getMinutes()));
	int mod=m.getMinutes()%5;
	t.setM(new Minute(m.getMinutes()-mod));
	t.update();
	return t;
}

//adds the specified number of minutes
public Time addMinutes(int a){
	Time t=new Time(new Hour(h.getHours()), new Minute(m.getMinutes()+a));
	t.update();
	return t;
}

//subtracts the specified number of minutes
public Time subtractMinutes(int s){
	Time t=new Time(new Hour(h.getHours()), new Minute(m.getMinutes()-s));
	t.update();
	return t;
}
public static void main(String[] args){
	Time t=new Time("12:34");
	System.out.println(t);
	Time t2=t.roundNearestFive();
	Time t3=t.roundUp();
	Time t4=t.roundDown();
	System.out.println(t2);
	System.out.println(t3);
	System.out.println(t4);
	System.out.println(t.addMinutes(36));
	System.out.println(t.subtractMinutes(45));
}
}
