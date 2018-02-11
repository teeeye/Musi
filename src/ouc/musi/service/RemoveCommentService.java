package ouc.musi.service;

import ouc.musi.dao.CommentDao;
import ouc.musi.domain.Result;

public class RemoveCommentService {

	private CommentDao cmnt_dao = new CommentDao();

	public Result removeComment(String cmnt_id) {

		boolean success = cmnt_dao.removeComment(cmnt_id);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);

	}
}
