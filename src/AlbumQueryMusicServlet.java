import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Album;
import ouc.musi.domain.Result;
import ouc.musi.service.AlbumQueryMusicService;
import ouc.musi.util.ResultWriter;

public class AlbumQueryMusicServlet implements Servlet {

	private AlbumQueryMusicService _albumQueryMusicService = new AlbumQueryMusicService();

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
		Album albm = new Album();
		albm.setAlbm_name(req.getParameter("albm_name"));
		albm.setAlbm_id(req.getParameter("albm_id"));
		int page = Integer.parseInt(req.getParameter("page"));

		if (page < 0 || albm.getAlbm_name().equals("网络歌曲")
				|| albm.getAlbm_id().equals("00000000000000000000000000000000")) {
			System.out.println("invalid in querying music with album name");
			return;
		}

		Result result = _albumQueryMusicService.albumQuery(albm, page);

		ResultWriter.writeResult(res, result);

	}

}
