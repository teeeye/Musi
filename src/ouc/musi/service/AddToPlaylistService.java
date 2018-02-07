package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Playlist_Music;
import ouc.musi.domain.Result;

public class AddToPlaylistService {

	private PlaylistDao plylst_dao = new PlaylistDao();
	
	public Result addMusic(Playlist_Music plylst_msc) {
		boolean success = plylst_dao.addMusic(plylst_msc);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);
	}
}
