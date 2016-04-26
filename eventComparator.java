package ics314;

import java.util.Comparator;

public class eventComparator implements Comparator<Event>{

	@Override
	public int compare(Event o1, Event o2) {
		int start1 = (o1.getStartTime());
		int start2 = (o2.getStartTime());
		if (start1 < start2) {
			return -1;
		}
		else if (start1 == start2) {
			return 0;
		}
		else if (start1 > start2) {
			return 1;
		}
		return 0; //Come back to me.
	}
}
