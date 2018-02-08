package ouc.musi.service;

import ouc.musi.dao.PlaylistDao;
import ouc.musi.dao.UserDao;
import ouc.musi.domain.Playlist;
import ouc.musi.domain.Result;
import ouc.musi.domain.User;
import ouc.musi.util.UUIDGenerator;

public class RegisterService {

	private PlaylistDao plylst_dao = new PlaylistDao();
	private UserDao user_dao = new UserDao();
	public static final String DEFAULT_USER_AVATAR_PATH = "C:/default.jpg";

	public Result register(User user) {
		String usr_id = UUIDGenerator.getUUID();
		StringBuffer usr_name = new StringBuffer("u_");
		usr_name.append(usr_id);

		user.setUsr_avtr(DEFAULT_USER_AVATAR_PATH);
		user.setUsr_id(usr_id);
		user.setUsr_name(String.valueOf(usr_name));

		boolean success = user_dao.register(user);
		if (success) {
			Playlist plylst = new Playlist();
			String plylst_id = UUIDGenerator.getUUID();
			plylst.setPlylst_id(plylst_id);
			plylst.setPlylst_hot(0);
			plylst.setPlylst_cvr(NewPlaylistService.DEFAULT_PLAYLIST_COVER_PATH);
			plylst.setPlylst_name("我喜欢的");
			plylst.setUsr_id(usr_id);

			plylst_dao.newPlaylist(plylst);
		}

		String reason = success ? "OK" : "server error";
		return new Result(success, reason, success ? usr_id : null);

	}
}
