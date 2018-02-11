import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Result;
import ouc.musi.domain.User;
import ouc.musi.service.RegisterService;
import ouc.musi.util.ResultWriter;

public class RegisterServlet implements Servlet {

	private RegisterService _registerService = new RegisterService();

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
		String usr_phn_nmb = req.getParameter("usr_phn_nmb");
		String usr_pwd = req.getParameter("usr_pwd");

		if (usr_phn_nmb.length() != 11 || usr_pwd.length() != 32) {
			System.out.println("invalid user-phone-number or user-password");
			return;
		}

		user.setUsr_phn_nmb(usr_phn_nmb);
		user.setUsr_pwd(usr_pwd);

		Result result = _registerService.register(user);

		ResultWriter.writeResult(res, result);

	}

}
