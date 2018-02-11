package ouc.musi.service;

import ouc.musi.dao.MusicDao;

public class DownloadService {

	private MusicDao msc_dao = new MusicDao();
	
	public void playMusic(String msc_id){
		msc_dao.playMusic(msc_id);
	}
}
