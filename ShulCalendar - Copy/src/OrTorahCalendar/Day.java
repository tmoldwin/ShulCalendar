package OrTorahCalendar;

public class Day {
	Date date;
	String hebrewDate;
	String special;
	String parsha;
	Time cl;
	Time earlyCl;
	Time fridayMincha;
	Time earlyFridayMincha;
	String shabbatShacharit;
	Time shabbatMincha;
	Time shabbosHavdala;
	Time havdala;
	Time sunset;
	Time weekdayMincha;
	String sundayShacharit;
	String weekdayShacharit;
	Time alot;
	Time endFast;
	Time plag;

	/*
	 * 0=erev yom tov 1=first day yom tov 2=second day yom tov 3=day fast
	 */
	String code;
	
		

	
	public Day(String date1, String HebrewDay, String special1, String parsha1,
			String alot1, String Talis, String sunrise, String shemaGro,
			String shacharisGra, String Chatzos, String minchaGedola,
			String minchaKetana, String plag1, String cl1, String sunset1,
			String endFast1, String tzes, String code1) {

		date = new Date(date1);
		parsha = parsha1;
		special = special1;
		if (!cl1.equals(""))
			cl = new Time(cl1);

		// plag
		if (date.isSummer())
			earlyCl = new Time(plag1);

		// 20 mins after candlelighting
		sunset = new Time(sunset1);
		/*
		 * Friday Mincha 5 minutes post candlelighting, rounding to the nearest
		 * “:05” e.g. if candles are at 4:01, mincha is at 4:05, if candles at
		 * 5:29, mincha at 5:30, if candles at 6:30PM, Mincha at 6:35PM.
		 */
		if (!cl1.equals(""))
			fridayMincha = cl.roundUpAlways();
		shabbatShacharit = "8/8:30/8:50";

		/*
		 * Shabbat Mincha At least 10 Minutes pre-candlelighting, rounding down
		 * to the nearest “:05” e.g. cndllghtng 4:01=shbbat mincha 3:50pm,
		 * cndllghtng 5:29=shbbat mincha 5:15pm cndllghtng 6:30=shbbat mincha
		 * 6:20pm
		 * 
		 * New: 30 minutes before sunset of Shabbos day, not Friday night,
		 * rounded down.
		 */

		shabbatMincha = sunset.subtractMinutes(30).roundDownAlways();

		/*
		 * Havdala 42 minutes after sunset Normally works out to approximately
		 * 42 minutes after sunset; use calculation of times based on “tzeit of
		 * 8.5 degrees”
		 */
		if (sunset != null)
			shabbosHavdala = sunset.addMinutes(40);
		if (date.isSummer())
			earlyFridayMincha = new Time(plag1).roundUpAlways();

		sundayShacharit = "7:15/8:00";
		weekdayShacharit = "6:30/8:00";

		/*
		 * Weekday Mincha At least 15 minutes before sunset- this time should
		 * mostly match Friday Mincha of the previous Shabbat.
		 */
		if (sunset != null)
			weekdayMincha = sunset.subtractMinutes(15).roundDown();
		if (!alot1.equals(""))
			alot = new Time(alot1);
		endFast = new Time(endFast1);
		if (code1 != null)
			code = code1;
		else
			code = "";
	}

	public Day(String date1, String hebrewDay, String special1, String parsha1,
			String alot1, String talis, String sunrise, String shemaGro,
			String shacharisGra, String chatzos, String minchaGedola,
			String minchaKetana, String plag1, String cl1, String sunset1,
			String endFast1, String tzes, String code1, String  yesCl) {
		date = new Date(date1);
		parsha = parsha1;
		special = special1;
		if (!cl1.equals(""))
			cl = new Time(cl1);

		// plag
		if (date.isSummer())
			earlyCl = new Time(plag1);

		// 20 mins after candlelighting
		sunset = new Time(sunset1);
		/*
		 * Friday Mincha 5 minutes post candlelighting, rounding to the nearest
		 * “:05” e.g. if candles are at 4:01, mincha is at 4:05, if candles at
		 * 5:29, mincha at 5:30, if candles at 6:30PM, Mincha at 6:35PM.
		 */
		if (!cl1.equals(""))
			fridayMincha = cl.roundUpAlways();
		shabbatShacharit = "8/8:30/8:50";

		/*
		 * Shabbat Mincha At least 10 Minutes pre-candlelighting, rounding down
		 * to the nearest “:05” e.g. cndllghtng 4:01=shbbat mincha 3:50pm,
		 * cndllghtng 5:29=shbbat mincha 5:15pm cndllghtng 6:30=shbbat mincha
		 * 6:20pm
		 * 
		 * New: 30 minutes before sunset of Shabbos day, not Friday night,
		 * rounded down.
		 */
if(!yesCl.equals(""))
		shabbatMincha = new Time(yesCl).subtractMinutes(10).roundDownAlways();

		/*
		 * Havdala 42 minutes after sunset Normally works out to approximately
		 * 42 minutes after sunset; use calculation of times based on “tzeit of
		 * 8point5 degrees”
		 */
		if (!yesCl.equals("")){
			
			shabbosHavdala = new Time(yesCl).addMinutes(42);
		}
			if (date.isSummer())
		
			earlyFridayMincha = new Time(plag1).roundUpAlways();

		sundayShacharit = "7:15/8:00";
		weekdayShacharit = "6:30/8:00";

		/*
		 * Weekday Mincha At least 15 minutes before sunset- this time should
		 * mostly match Friday Mincha of the previous Shabbat.
		 */
		if (!yesCl.equals(""))
			weekdayMincha = new Time(yesCl).addMinutes(5).roundDown();
		if (!alot1.equals(""))
			alot = new Time(alot1);
		endFast = new Time(endFast1);
		if (code1 != null)
			code = code1;
		else
			code = "";	}

	public String toString() {
		String s = "Date: " + date + "\nDay: " + date.getStringDayOfWeek();
		String holidayString = "\nHoliday: " + special;

		// If it's a holiday...
		if (!special.equals("")) {
			if ((special.equals("ראש חודש") || special.equals("חול המועד"))
					&& havdala == null) {
				holidayString += "\nShacharit: " + "6:20/8:00";
			}
			// if it's a fast day...
			if (code.contains("3"))
				holidayString += "\nFast starts: " + alot + "\nFast ends: "
						+ endFast;

			// If it's erev Yom Tov..
			if (code.contains("0"))
				holidayString += "\nCandle Lighting: " + cl;
			// If Shabbos is immediately after Yom Tov, you won't be saying
			// havdala
			if (code.contains("2") && (cl == null))
				holidayString += "\nHavdala: " + sunset.addMinutes(40);

			/*
			 * if (special.equals("יום כפור")) { holidayString += ""; } if
			 * (special.equals("ערב יום כיפור")) {
			 * 
			 * } if (special.equals("חול המועד")) { holidayString += ""; } if
			 * (special.equals("חנוכה")) { holidayString += ""; } if
			 * (special.equals("ערב פסח")) { holidayString +=
			 * "\nCandle Lighting: " + cl; } if (special.equals("ערב סוכות")) {
			 * holidayString += "\nCandle Lighting: " + cl; } if
			 * (special.equals("ערב ראש השנה")) { holidayString +=
			 * "\nCandle Lighting: " + cl; } if (special.equals("ערב שבועות")) {
			 * holidayString += "\nCandle Lighting: " + cl; } if
			 * (special.contains("הושענא רבה")) { holidayString +=
			 * "\nCandle Lighting: " + cl; } if
			 * (special.contains("ערב שביעי של פסח")) { holidayString +=
			 * "\nCandle Lighting: " + cl; } if (special.equals("פסח2") ||
			 * special.equals("סוכות2") || special.equals("ראש השנה2") ||
			 * special
			 * .equals("שמיני של פסח")||special.equals("שבועות2")||special
			 * .equals("יום כיפור")) {
			 */
		}
		if (special.equals("ערב תשעה באב")) {
			holidayString += "\nFast Begins: " + sunset;
		}
		if (special.equals("תשעה באב")) {
			holidayString += "\nFast Ends: " + endFast;
		}

		// Erev Shabbos, not Erev Yom Tov, will work even if no Parsha
		if (cl != null && !code.contains("0")) {
			/*
			 * If friday is a special day, it'll tell you but it'll get rid of
			 * havdala and candlelighting, because it's Shabbos, so you'll
			 * already have that
			 */
			if (!special.equals("")) {
				s += holidayString;
				if (s.contains("\nCandle Lighting: " + cl))
					s.replace("\nCandle Lighting: " + cl, "");
				if (s.contains("\n+Havdala" + shabbosHavdala)
						|| s.contains("\n+Havdala" + sunset.addMinutes(40)))
					s.replace("\nHavdala: " + sunset.addMinutes(40), "");
			}
			s += "\nParsha: " + parsha;

			/*
			 * Early mincha and candlelighting in the summer after changing the
			 * clocks, (check each year for the date). If friday is Yom Tov,
			 * there won't be early candlelighting/mincha
			 */
			if (date.isSummer() && !(code.contains("1") || code.contains("2"))) {
				s += "\nEarliest Candle Lighting: " + earlyCl
						+ "\nEarly Friday Mincha: " + earlyFridayMincha;
			}
			s += "\nCandle Lighting: " + cl + "\nFriday Mincha: "
					+ fridayMincha;
			return s;
		}
		// Shabbos Day.
		else if (date.getDayOfWeek() == 7) {
			if (!special.equals("")) {
				s += "\nHoliday: " + special;

			}
			s += "\nShabbos Shacharit: " + shabbatShacharit
					+ "\nShabbos Mincha: " + shabbatMincha;
				
			/*
			 * If Shabbos is erev or First day of Yom Tov, you'll be saying
			 * Kiddush Shabbos night (in addition to havdala)
			 */
			if (!code.contains("0") && !code.contains("1"))
				s += "\nHavdala: " + shabbosHavdala;
			else
				s += "\n Kiddush: " + shabbosHavdala;
			s += "\nSunday Shacharit: " + sundayShacharit+"\nWeekday Shacharit: " + weekdayShacharit
					+ "\nWeekday Mincha: " + weekdayMincha;

		}
		// Regular Yom Tov, not Shabbos
		else if (!special.equals("")) {
			s += holidayString;
			return s;
		}
		return s;
	}

	public static void main(String[] args) {

	}
}