package ouc.musi.service;

import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Music_Like;
import ouc.musi.domain.Result;

public class MusicLikeService {

	private MusicDao msc_dao = new MusicDao();
	
	public Result musicLike(Music_Like msc_like){
		boolean success = msc_dao.musicLike(msc_like);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);
	}
}
