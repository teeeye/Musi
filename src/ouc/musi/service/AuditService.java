package ouc.musi.service;

import ouc.musi.dao.AuditMusicDao;
import ouc.musi.dao.MusicDao;
import ouc.musi.domain.Music;
import ouc.musi.util.MP3Parser;
import ouc.musi.util.UUIDGenerator;

public class AuditService {
	
	private MusicDao msc_dao = new MusicDao();
	private AuditMusicDao adt_msc_dao = new AuditMusicDao();

	public boolean addmusic(Music m){
		
		Music msc = new Music();
		String uuid = UUIDGenerator.getUUID();
		msc.setMsc_id(uuid);
		msc.setMsc_albm(m.getMsc_albm());
		msc.setMsc_name(m.getMsc_name());
		msc.setMsc_sngr(m.getMsc_sngr());
		
		String fileLocation = adt_msc_dao.queryMusicPath(m.getMsc_name());
		msc.setMsc_lnth(MP3Parser.parse(fileLocation));
		StringBuffer path = new StringBuffer("MUSICPATH");
		path.append("uuid");
		path.append(".MP3");
		
		msc.setMsc_pth(String.valueOf(path));
		msc.setMsc_hot(0);
		
		return msc_dao.addmusic(msc);
		
	}
}
