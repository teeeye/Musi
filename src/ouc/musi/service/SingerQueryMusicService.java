package ouc.musi.service;

import java.util.List;

import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Music;
import ouc.musi.domain.Result;
import ouc.musi.domain.Singer;

public class SingerQueryMusicService {

	private MusicDao msc_dao = new MusicDao();

	public Result singerQuery(Singer sngr, int page) {
		
		List<Music> result_array = msc_dao.singerQuery(sngr, page);
		boolean success = (result_array != null);
		String reason = success ? "OK" : "Server Error";
		Result result = new Result(success, reason, result_array);
		return result;

	}
}
