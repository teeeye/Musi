package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.util.UUIDGenerator;

public class NewPlaylistService {
	
	private PlaylistDao plylst_dao = new PlaylistDao();
	public static final String DEFAULT_PLAYLIST_COVER_PATH ="C:/default.jpg";

	public Result newPlaylist(Playlist plylst){
		
		String plylst_id = UUIDGenerator.getUUID();
		plylst.setPlylst_id(plylst_id);
		plylst.setPlylst_hot(0);
		plylst.setPlylst_cvr(DEFAULT_PLAYLIST_COVER_PATH);
		
		boolean success = plylst_dao.newPlaylist(plylst);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, success ? plylst_id : null);
	}
}
