package ouc.musi.service;

import ouc.musi.dao.CommentDao;
import ouc.musi.domain.Comment;
import ouc.musi.domain.Result;
import ouc.musi.util.UUIDGenerator;

public class AddCommentService {

	private CommentDao cmnt_dao = new CommentDao();

	public Result addComment(Comment cmnt) {
		String cmnt_id = UUIDGenerator.getUUID();

		cmnt.setCmnt_id(cmnt_id);
		cmnt.setRply_id(null);

		boolean success = cmnt_dao.addComment(cmnt);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, success ? cmnt_id : null);

	}
}
