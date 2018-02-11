import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Comment_Like;
import ouc.musi.domain.Result;
import ouc.musi.service.CommentLikeService;
import ouc.musi.util.ResultWriter;

public class CommentLikeServlet implements Servlet{
	
	private CommentLikeService _commentLikeService = new CommentLikeService();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Comment_Like cmnt_like = new Comment_Like();
		cmnt_like.setCmnt_id(req.getParameter("cmnt_id"));
		cmnt_like.setUsr_id(req.getParameter("usr_id"));
		
		Result result = _commentLikeService.commentLike(cmnt_like);
		ResultWriter.writeResult(res, result);
	}

}
