package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Result;
import ouc.musi.domain.User_Playlist;

public class CollectPlaylistService {

	private PlaylistDao plylst_dao = new PlaylistDao();
	
	public Result collectPlaylist(User_Playlist usr_plylst) {
		boolean success = plylst_dao.collectPlaylist(usr_plylst);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);
	}

}
