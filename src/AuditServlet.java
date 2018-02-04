import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Music;
import ouc.musi.domain.Result;
import ouc.musi.service.AuditService;
import ouc.musi.util.ResultWriter;

public class AuditServlet implements Servlet {

	private AuditService adt_srv = new AuditService();

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

		Result result = null;
		
		boolean adt_pass = Boolean.valueOf(req.getParameter("adt_pass"));
		
		Music music = new Music();
		
		String ctgy = req.getParameter("msc_ctgy");
		int msc_ctgy = ctgy == null ? 0 : Integer.parseInt(ctgy);
		String msc_id = req.getParameter("msc_id");
		String msc_name = req.getParameter("msc_name");
		String msc_sngr = req.getParameter("msc_sngr");
		String msc_albm = req.getParameter("msc_albm");
		String msc_path = req.getParameter("msc_path");

		music.setMsc_id(msc_id);
		music.setMsc_name(msc_name);
		music.setMsc_albm(msc_albm);
		music.setMsc_sngr(msc_sngr);
		music.setMsc_ctgy(msc_ctgy);
		music.setMsc_path(msc_path);
		
		if (adt_pass) {
			result = adt_srv.addmusic(music);
		} else {
			result = adt_srv.rejectMusic(music);
		}
		
		ResultWriter.writeResult(res, result);
	}
}
