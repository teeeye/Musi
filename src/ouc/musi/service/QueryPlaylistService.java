package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;

public class QueryPlaylistService {

	private PlaylistDao plylst_dao = new PlaylistDao();

	public Result queryPlaylist(int page) {

		Playlist[] result_array = plylst_dao.queryPlaylist(page);
		boolean success = (result_array != null);
		String reason = success ? "OK" : "Server Error";
		Result result = new Result(success, reason, result_array);
		return result;
	}
}
