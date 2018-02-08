package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.dao.UserDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.domain.User;

public class LoginService {

	private PlaylistDao plylst_dao = new PlaylistDao();
	private UserDao usr_dao = new UserDao();

	public Result login(User user) {
		User u = new User();
		Playlist p = new Playlist();

		u = usr_dao.login(user);
		String u_id = u.getUsr_id();

		p = plylst_dao.queryWithUserId(u_id);
		u.setPlylst(p);

		boolean success = false;
		if (u != null) {
			success = true;
		}

		String reason = success ? "OK" : "server error";
		return new Result(success, reason, success ? u : null);

	}

}
