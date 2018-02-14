package ouc.musi.service;

import java.util.List;

import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Music;
import ouc.musi.domain.Result;

public class KeyWordQueryMusicService {

	private MusicDao msc_dao = new MusicDao();

	public Result keyWordQuery(String keyWord, int page) {
		
		List<Music> result_array = msc_dao.keyWordQuery(keyWord, page);
		boolean success = (result_array != null);
		String reason = success ? "OK" : "Server Error";
		Result result = new Result(success, reason, result_array);
		return result;

	}
}
