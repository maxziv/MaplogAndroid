package purdue.maplog.controllers;

import java.util.Comparator;

public class maplogTimeComparable implements Comparator<MaplogNode> {

	public int compare(MaplogNode o0, MaplogNode o1) {
		return o0.getDate().compareTo(o1.getDate());
	}

}
