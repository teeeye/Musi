import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.service.NewPlaylistService;
import ouc.musi.util.ResultWriter;

public class NewPlaylistServlet implements Servlet {
	
	private NewPlaylistService _newPlaylistService = new NewPlaylistService();

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
		
		Playlist plylst = new Playlist();
		plylst.setPlylst_name("plylst_name");
		plylst.setUsr_id(req.getParameter("usr_id"));
		
		Result result = _newPlaylistService.newPlaylist(plylst);
		ResultWriter.writeResult(res, result);
		
		
	}

}
