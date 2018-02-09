package ouc.musi.service;

import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Music;
import ouc.musi.domain.Result;

public class CategoryQueryMusicService {

	private MusicDao msc_dao = new MusicDao();

	public Result categoryQuery(int ctgy_id, int page) {
			
			Music[] result_array = msc_dao.categoryQuery(ctgy_id, page);
			boolean success = (result_array != null);
			String reason = success ? "OK" : "Server Error";
			Result result = new Result(success, reason, result_array);
			return result;
	
	}
}
