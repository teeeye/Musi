
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Result;
import ouc.musi.service.AuditQueryService;
import ouc.musi.util.ResultWriter;

public class AuditQueryServlet implements Servlet{
	
	private AuditQueryService _auditQueryService = new AuditQueryService();

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
	public void service(ServletRequest req, ServletResponse res) throws ServletException {
		
		Result result = _auditQueryService.queryAuditMusic();
		
		ResultWriter.writeResult(res, result);
		
	}
}
