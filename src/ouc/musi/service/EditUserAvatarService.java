package ouc.musi.service;

import ouc.musi.enumeration.FileType;

import ouc.musi.dao.UserDao;
import ouc.musi.domain.Result;
import ouc.musi.domain.User;
import ouc.musi.util.FileOperator;
import ouc.musi.util.UUIDGenerator;

public class EditUserAvatarService {

	private UserDao user_dao = new UserDao();

	public Result editUserAvatar(User u) {

		boolean success = false;
		if (u.getUsr_avtr() != null) {
			String picture_name = UUIDGenerator.getUUID();
			String filePath = FileOperator.getFilePath(picture_name, FileType.PNG);
			success = FileOperator.saveFile(filePath, u.getUsr_avtr());
			if (success) {
				u.setUsr_avtr(filePath);
				success = user_dao.editUserAvatar(u);
				if (!success) {
					FileOperator.deleteFile(filePath, FileType.PNG);
				}
			}
		}

		String reason = success ? "OK" : "Server Error";

		Result result = new Result(success, reason, null);
		return result;
	}
}
