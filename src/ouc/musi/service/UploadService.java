package ouc.musi.service;

import ouc.musi.enumeration.FileType;

import ouc.musi.dao.UploadDao;
import ouc.musi.domain.Audit_Music;
import ouc.musi.domain.Result;
import ouc.musi.util.FileOperator;
import ouc.musi.util.UUIDGenerator;

public class UploadService {
	
	private UploadDao upld_dao = new UploadDao();

	/* 
	 * @func 	uploadMusic
	 * @desc 	将base64编码数据解码后存储到文件系统并在数据库的audit表中增加相应记录
	 * @param 	music			music_name的封装类
	 * @param 	base64data		音乐的base64编码
	 * @return 	result			此次操作的结果
	 */
	public Result uploadMusic(Audit_Music music, String base64data){

		// 填充Domain
		String fileName = UUIDGenerator.getUUID();
		String filePath = FileOperator.getFilePath(fileName, FileType.MP3);
		
		music.setMsc_id(fileName);
		music.setMsc_path(filePath);
		
		// success记录文件操作是否成功
		boolean success = FileOperator.saveFile(filePath, base64data);

		// 如果文件操作成功则进行数据库操作，并记录其操作是否成功
		if (success) {
			success = upld_dao.uploadMusic(music);
			
			// 如果数据库操作失败则删除文件
			if (!success) {
				FileOperator.deleteFile(filePath);
			}
		}
		
		// 返回结果
		String reason = success ? "OK" : "Server Error";
		
		Result result = new Result(success, reason, null);
		return result;
	}
}
