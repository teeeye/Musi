package ouc.musi.service;

import java.util.List;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.dao.UserDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.domain.User;

public class LoginService {

	private PlaylistDao plylst_dao = new PlaylistDao();
	private UserDao usr_dao = new UserDao();

	public Result login(User user) {

		boolean success = usr_dao.login(user);
		
		if (success) {
			// 如果登陆成功则返回用户列表
			String u_id = user.getUsr_id();
			List<Playlist> p = plylst_dao.queryWithUserId(u_id);
			// 如果p = null则不进行错误处理
			user.setPlylsts(p);
		}

		String reason = success ? "OK" : "server error";
		return new Result(success, reason, success ? user : null);
	}

}
