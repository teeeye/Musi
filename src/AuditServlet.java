import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSON;

import ouc.musi.domain.Music;
import ouc.musi.domain.Result;
import ouc.musi.service.AuditService;

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

		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		Music msc = new Music();

		String pass = req.getParameter("adt_pass");
		boolean msc_pass = Boolean.valueOf(pass);
//		String ctgy = req.getParameter("msc_ctgy");
//		int msc_ctgy = Integer.valueOf(ctgy);
		String msc_name = req.getParameter("msc_name");
		String msc_sngr = req.getParameter("msc_sngr");
		String msc_albm = req.getParameter("msc_albm");

		msc.setMsc_name(msc_name);
		msc.setMsc_albm(msc_albm);
		msc.setMsc_sngr(msc_sngr);

		if (msc_pass) {
			boolean result = adt_srv.addmusic(msc);
			Result rult = new Result();
			if (result) {
				rult.setSuccess(true);
				out.write(JSON.toJSONString(rult));
			} else {
				rult.setSuccess(false);
				rult.setReason("add music failed");
				out.write(JSON.toJSONString(rult));
			}
		}
	}
}
