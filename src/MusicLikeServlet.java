import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Music_Like;
import ouc.musi.domain.Result;
import ouc.musi.service.MusicLikeService;
import ouc.musi.util.ResultWriter;

public class MusicLikeServlet implements Servlet {
	
	private MusicLikeService _musicLikeService = new MusicLikeService();

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
		
		Music_Like msc_like = new Music_Like();
		
		msc_like.setUsr_id(req.getParameter("usr_id"));
		msc_like.setMsc_id(req.getParameter("msc_id"));
		
		Result result = _musicLikeService.musicLike(msc_like);
		
		ResultWriter.writeResult(res, result);
		
	}

}
