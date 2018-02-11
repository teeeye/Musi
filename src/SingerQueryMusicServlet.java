import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Result;
import ouc.musi.domain.Singer;
import ouc.musi.service.SingerQueryMusicService;
import ouc.musi.util.ResultWriter;

public class SingerQueryMusicServlet implements Servlet {

	private SingerQueryMusicService _singerQueryMusicService = new SingerQueryMusicService();

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

		Singer sngr = new Singer();

		int page = Integer.parseInt(req.getParameter("page"));
		sngr.setSngr_id(req.getParameter("sngr_id"));
		sngr.setSngr_name(req.getParameter("sngr_name"));

		if (page < 0 || sngr.getSngr_name().equals("匿名歌手") || sngr.getSngr_id().equals("00000000000000000000000000000000") ) {
			System.out.println("invalid in querying music with singer name");
			return;
		}

		Result result = _singerQueryMusicService.singerQuery(sngr, page);

		ResultWriter.writeResult(res, result);

	}

}
