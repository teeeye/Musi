import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.service.CollectPlaylistService;
import ouc.musi.util.ResultWriter;

public class CollectPlaylistServlet implements Servlet {

	private CollectPlaylistService _collectPlaylistService = new CollectPlaylistService();

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
		Playlist usr_plylst = new Playlist();
		usr_plylst.setPlylst_id(req.getParameter("plylst_id"));
		usr_plylst.setUsr_id(req.getParameter("usr_id"));

		Result result = _collectPlaylistService.collectPlaylist(usr_plylst);

		ResultWriter.writeResult(res, result);

	}

}
