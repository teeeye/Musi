import javax.servlet.*;

import ouc.musi.domain.Audit_Music;
import ouc.musi.domain.Result;
import ouc.musi.service.UploadService;
import ouc.musi.util.ResultWriter;

public class UploadServlet implements Servlet {

	private UploadService upld_srv = new UploadService();

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
	public void service(ServletRequest req, ServletResponse res) {
		// TODO Auto-generated method stub

		// 获取参数
		String musicName = req.getParameter("msc_name");
		String base64Data = req.getParameter("msc_data");
		
		// 两个参数均不能为null，否则拒绝服务
		if (musicName == null || base64Data == null) {
			System.out.println("invalid data uploaded - name:"+musicName+" data:"+base64Data);
			return;
		}
		
		// 创建Domain
		Audit_Music music = new Audit_Music();
		music.setMsc_name(musicName);
		
		// 将base64文件存储到本地文件系统
		Result result = upld_srv.uploadMusic(music, base64Data);
		
		// 返回操作结果
		ResultWriter.writeResult(res, result);
	}

}
