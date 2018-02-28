package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;

public class NewPlaylistService {
	
	private PlaylistDao plylst_dao = new PlaylistDao();

	public Result newPlaylist(Playlist plylst){
		boolean success = plylst_dao.newPlaylist(plylst);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, success ? plylst : null);
	}
}
