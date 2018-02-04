package ouc.musi.service;

import ouc.musi.dao.AuditMusicDao;
import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Music;
import ouc.musi.domain.Result;
import ouc.musi.util.FileOperator;

public class AuditService {
	
	private MusicDao msc_dao = new MusicDao();
	private AuditMusicDao adt_msc_dao = new AuditMusicDao();

	public Result addmusic(Music music){
		int msc_lnth = FileOperator.getMusicLength(music.getMsc_path());
		music.setMsc_lnth(msc_lnth);
		boolean success = adt_msc_dao.deleteAuditMusic(music.getMsc_id()) && msc_dao.addMusic(music);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);		
	}
	
	public Result rejectMusic(Music music) {
		boolean success = adt_msc_dao.deleteAuditMusic(music.getMsc_id());
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);
	}
}
