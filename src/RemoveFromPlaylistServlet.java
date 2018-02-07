import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Playlist_Music;
import ouc.musi.domain.Result;
import ouc.musi.service.RemoveFromPlaylistService;
import ouc.musi.util.ResultWriter;

public class RemoveFromPlaylistServlet implements Servlet {
	
	private RemoveFromPlaylistService _removeFromPlaylistService = new RemoveFromPlaylistService();

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
		
		Playlist_Music plylst_msc = new Playlist_Music();
		plylst_msc.setMsc_id(req.getParameter("msc_id"));
		plylst_msc.setPlylst_id(req.getParameter("plylst_id"));
		
		Result result = _removeFromPlaylistService.removeMusic(plylst_msc);
		ResultWriter.writeResult(res, result);
	}

}
