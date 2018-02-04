package ouc.musi.service;

import java.util.List;

import ouc.musi.dao.AuditMusicDao;
import ouc.musi.domain.Audit_Music;
import ouc.musi.domain.Result;

public class AuditQueryService {

	private AuditMusicDao adt_msc_dao = new AuditMusicDao();

	public Result queryAuditMusic() {
		
		List<Audit_Music> auditList = adt_msc_dao.queryAuditMusic();
		
		boolean success = (auditList != null);
		
		String reason = success ? "OK" : "Server Error";
		
		Result result = new Result(success, reason, auditList);
		
		return result;
	}
}
