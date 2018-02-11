package ouc.musi.service;

import ouc.musi.enumeration.FileType;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.util.FileOperator;

public class EditPlaylistService {

	private PlaylistDao plylst_dao = new PlaylistDao();

	public Result editPlaylist(Playlist plylst) {
		boolean success = false;
		if (plylst.getPlylst_cvr() != null) {
			Playlist p = plylst_dao.queryWithPlaylistId(plylst.getPlylst_id());
			String p_name = p.getPlylst_name();
			String filePath = FileOperator.getFilePath(p_name, FileType.PNG);
			success = FileOperator.saveFile(filePath, plylst.getPlylst_cvr());
			if (success) {
				success = plylst_dao.editPlaylist(plylst, filePath);
				if (!success) {
					FileOperator.deleteFile(filePath, FileType.PNG);
				}
			}
		}

		if (plylst.getPlylst_cvr() == null && plylst.getPlylst_name() != null) {
			success = plylst_dao.editPlaylist(plylst, null);
		}

		String reason = success ? "OK" : "Server Error";

		Result result = new Result(success, reason, null);
		return result;
	}

}
