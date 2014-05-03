package purdue.maplog.controllers;

import java.util.Comparator;

public class maplogTitleComparable implements Comparator<MaplogNode> {

	public int compare(MaplogNode o0, MaplogNode o1) {
		
		return (o0.getTitle().compareTo(o1.getTitle()));
		
	}

}
