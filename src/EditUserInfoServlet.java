import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Result;
import ouc.musi.domain.User;
import ouc.musi.service.EditUserInfoService;
import ouc.musi.util.ResultWriter;

public class EditUserInfoServlet implements Servlet {

	private EditUserInfoService _editUserInfoService = new EditUserInfoService();

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

		User user = new User();
		user.setUsr_id(req.getParameter("usr_id"));
		user.setUsr_name(req.getParameter("usr_name"));

		Result result = _editUserInfoService.editUserInfo(user);
		ResultWriter.writeResult(res, result);
	}

}
