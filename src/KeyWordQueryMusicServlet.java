import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Result;
import ouc.musi.service.KeyWordQueryMusicService;
import ouc.musi.util.ResultWriter;

public class KeyWordQueryMusicServlet implements Servlet{
	
	private KeyWordQueryMusicService _keyWordQueryMusicService = new KeyWordQueryMusicService();

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
		
		String keyWord = req.getParameter("kywd");
		int page = Integer.parseInt(req.getParameter("page"));
		
		if(page < 0){
			System.out.println("invalid page");
			return ;
		}
		
		Result result = _keyWordQueryMusicService.keyWordQuery(keyWord, page);
		
		ResultWriter.writeResult(res, result);

	}

}
