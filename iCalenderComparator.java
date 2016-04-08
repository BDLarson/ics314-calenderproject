package ics314;

import java.util.Comparator;

public class iCalenderComparator implements Comparator<iCalender> {

	@Override
	public int compare(iCalender o1, iCalender o2) {
		int start1 = (o1.setStartTime());
		int start2 = (o2.setStartTime());
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
