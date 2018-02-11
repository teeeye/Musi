package ouc.musi.service;

import ouc.musi.dao.CommentDao;
import ouc.musi.domain.Comment;
import ouc.musi.domain.Result;

public class QueryCommentService {

	private CommentDao cmnt_dao = new CommentDao();

	public Result queryComment(String msc_id) {

		Comment[] result_array = cmnt_dao.queryComment(msc_id);
		boolean success = (result_array != null);
		String reason = success ? "OK" : "Server Error";
		Result result = new Result(success, reason, result_array);
		return result;
	}

}
