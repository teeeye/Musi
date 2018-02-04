package ouc.musi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ouc.musi.enumeration.FileType;

public class FileOperator {

	private static final String BASE_PATH = "/Users/wangchenghao1103/Documents/Musi/WebContent/";
	private static final String MUSIC_PATH = "music/";
	private static final String IMAGE_PATH = "image/";
	
	public static String getFilePath(String fileName, FileType type) {
		StringBuffer path = new StringBuffer();
		
		if (type == FileType.MP3) {
			path.append(MUSIC_PATH);
		} else if(type == FileType.PNG){
			path.append(IMAGE_PATH);
		}
		
		path.append(fileName);
		
		if (type == FileType.MP3) {
			path.append(".mp3");
		} else if(type == FileType.PNG){
			path.append(".png");
		}
		
		return path.toString();
	}
	
	public static boolean saveFile(String path, String fileData) {

		byte[] file_data = Base64Parser.Base64Decode(fileData);
		
		File file = new File(BASE_PATH + path);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(file_data);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void deleteFile(String path, FileType type) {
				
		File file = new File(path);
		
		if (file.exists()) {
			file.delete();
		}
	}

}
