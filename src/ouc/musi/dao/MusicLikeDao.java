package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ouc.musi.domain.Music_Like;
import ouc.musi.util.JdbcUtil;

public class MusicLikeDao {

	public boolean addMusicLike(Music_Like msc_like) {
		try {
			String sql = "insert into music_like(usr_id, msc_id) values(?,?)";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, msc_like.getUsr_id());
			ps.setString(2, msc_like.getMsc_id());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add music_like failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteMusicLike(Music_Like msc_like) {
		try {
		String sql = "delete from music_like where usr_id = ? and msc_id = ?";
		PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
		ps.setString(1, msc_like.getUsr_id());
		ps.setString(2, msc_like.getMsc_id());
		ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete music_like failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}