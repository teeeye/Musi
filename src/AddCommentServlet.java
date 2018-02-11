import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Comment;
import ouc.musi.domain.Result;
import ouc.musi.service.AddCommentService;
import ouc.musi.util.ResultWriter;

public class AddCommentServlet implements Servlet {

	private AddCommentService _addCommentService = new AddCommentService();

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

		Comment cmnt = new Comment();
		cmnt.setCmnt_ctnt(req.getParameter("cmnt"));
		cmnt.setMsc_id(req.getParameter("msc_id"));
		cmnt.setUsr_id(req.getParameter("usr_id"));

		Result result = _addCommentService.addComment(cmnt);
		ResultWriter.writeResult(res, result);

	}

}
