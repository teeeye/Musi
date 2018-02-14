import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ouc.musi.domain.Result;
import ouc.musi.domain.User;
import ouc.musi.service.EditUserAvatarService;
import ouc.musi.util.ResultWriter;

public class EditUserAvatarServlet implements Servlet {

	private EditUserAvatarService _editUserAvatarService = new EditUserAvatarService();
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
		
		String user_id = req.getParameter("usr_id");	
		String base64data = req.getParameter("usr_avtr");
		
		if (user_id == null || base64data == null) {
			System.out.println("invalid user avatar parameter");
			return;
		}
		
		User user = new User();
		user.setUsr_id(user_id);
		
		Result result = _editUserAvatarService.editUserAvatar(user, base64data);
		ResultWriter.writeResult(res, result);
	}

}
