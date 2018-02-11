import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.service.EditPlaylistService;
import ouc.musi.util.ResultWriter;

public class EditPlaylistServlet implements Servlet{

	private EditPlaylistService _editPlaylistService = new EditPlaylistService();
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
		String plylst_name = req.getParameter("plylst_name");
		String plylst_cvr = req.getParameter("plylst_cover");
		
		plylst.setPlylst_id(req.getParameter("pylst_id"));
		plylst.setPlylst_name(plylst_name);
		plylst.setPlylst_cvr(plylst_cvr);
		
		if(plylst_name == null && plylst_cvr == null){
			System.out.println("invalid arguments in Edit Playlist");
			return ;
		}
		
		Result result = _editPlaylistService.editPlaylist(plylst);

		ResultWriter.writeResult(res, result);
	}

}
