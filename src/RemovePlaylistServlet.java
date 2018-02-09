import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Result;
import ouc.musi.service.RemovePlaylistService;
import ouc.musi.util.ResultWriter;

public class RemovePlaylistServlet implements Servlet{

	private RemovePlaylistService _removePlaylistService = new RemovePlaylistService();
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

		Result result = _removePlaylistService.removePlaylist(req.getParameter("plylst_id"));
		ResultWriter.writeResult(res, result);

	}

}
