package ouc.musi.service;

import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Album;
import ouc.musi.domain.Music;
import ouc.musi.domain.Result;

public class AlbumQueryMusicService {

	private MusicDao msc_dao = new MusicDao();
	
	public Result albumQuery(Album albm, int page) {
		Music[] result_array = msc_dao.albumQuery(albm, page);
		boolean success = (result_array != null);
		String reason = success ? "OK" : "Server Error";
		Result result = new Result(success, reason, result_array);
		return result;
		
	}

}
