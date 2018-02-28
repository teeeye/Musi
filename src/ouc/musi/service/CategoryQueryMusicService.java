package ouc.musi.service;

import java.util.List;

//import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Music;
import ouc.musi.domain.Result;

public class CategoryQueryMusicService {

//	private MusicDao msc_dao = new MusicDao();

	public Result categoryQuery(int ctgy_id, int page) {
			
//			List<Music> result_array = msc_dao.categoryQuery(ctgy_id, page);
			List<Music> result_array = null;
			boolean success = (result_array != null);
			String reason = success ? "OK" : "Server Error";
			Result result = new Result(success, reason, result_array);
			return result;
	
	}
}
