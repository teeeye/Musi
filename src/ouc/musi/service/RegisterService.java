package ouc.musi.service;

import java.util.ArrayList;
import java.util.List;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.dao.UserDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.domain.User;

public class RegisterService {

	private PlaylistDao plylst_dao = new PlaylistDao();
	private UserDao user_dao = new UserDao();

	public Result register(User user) {

		boolean success = user_dao.register(user);
		
		if (success) {
			// 添加一个默认的 “我喜欢的” 歌单
			Playlist plylst = new Playlist();
			plylst.setPlylst_name("我喜欢的");
			plylst.setUsr_id(user.getUsr_id());
			
			success = plylst_dao.newPlaylist(plylst);
			if (!success) {
				user_dao.deleteUser(user);
			} else {
				List<Playlist> playlists = new ArrayList<Playlist>();
				playlists.add(plylst);
				user.setPlylsts(playlists);
			}
		}

		String reason = success ? "OK" : "server error";
		return new Result(success, reason, success ? user : null);
	}
}
