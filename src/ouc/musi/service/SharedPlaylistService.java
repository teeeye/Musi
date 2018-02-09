package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Result;

public class SharedPlaylistService {

private PlaylistDao plylst_dao = new PlaylistDao();
	
	public Result sharePlaylist(String plylst_id){
		boolean success = plylst_dao.sharePlaylist(plylst_id);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);
	}
}
