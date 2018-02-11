package ouc.musi.service;

import ouc.musi.dao.CommentDao;
import ouc.musi.domain.Comment_Like;
import ouc.musi.domain.Result;

public class CommentLikeService {

	private CommentDao cmnt_dao = new CommentDao();
	public Result commentLike(Comment_Like cmnt_like){
		boolean success = cmnt_dao.commentLike(cmnt_like);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);
	}
}
