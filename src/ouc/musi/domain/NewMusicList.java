package ouc.musi.domain;

import java.util.ArrayList;
import java.util.List;

public class NewMusicList {
	private final static int LIST_SIZE = 100;
	private static List<Music> _musicList = new ArrayList<Music>(LIST_SIZE); 
	private static int queuePointer = 0;
	
	public static void add(Music msc) {
		_musicList.add(queuePointer, msc);
		queuePointer = (queuePointer + 1) % LIST_SIZE;
	}
	
	public static final List<Music> get() {
		return _musicList;
	}
	
}
