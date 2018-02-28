package ouc.musi.service;

import ouc.musi.enumeration.FileType;

import ouc.musi.dao.UserDao;
import ouc.musi.domain.Result;
import ouc.musi.domain.User;
import ouc.musi.util.FileOperator;
import ouc.musi.util.UUIDGenerator;

public class EditUserAvatarService {

	private UserDao user_dao = new UserDao();

	public Result editUserAvatar(User u, String base64data) {

		String fileName = UUIDGenerator.getUUID();
		String filePath = FileOperator.getFilePath(fileName, FileType.PNG);
		
		u.setUsr_avtr(filePath);
		
		boolean success = FileOperator.saveFile(filePath, base64data);
		
		if (success) {
			success = user_dao.editUserAvatar(u);
			if (!success) {
				FileOperator.deleteFile(filePath);
			}
		}

		String reason = success ? "OK" : "Server Error";

		Result result = new Result(success, reason, null);
		return result;
	}
}
