package ouc.musi.service;

import ouc.musi.dao.UserDao;
import ouc.musi.domain.Result;
import ouc.musi.domain.User;

public class EditUserInfoService {

	private UserDao usr_dao = new UserDao();

	public Result editUserInfo(User u) {
		boolean success = usr_dao.editUserInfo(u);
		String reason = success ? "OK" : "server error";
		return new Result(success, reason, null);

	}
}
