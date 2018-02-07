package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Playlist_Music;
import ouc.musi.domain.Result;

public class RemoveFromPlaylistService {

private PlaylistDao plylst_dao = new PlaylistDao();
	
	public Result removeMusic(Playlist_Music plylst_msc) {
		boolean success = plylst_dao.removeMusic(plylst_msc);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);
	}
}
