package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ouc.musi.domain.Playlist;
import ouc.musi.domain.Playlist_Music;
import ouc.musi.util.JdbcUtil;

public class PlaylistDao {

	public boolean addMusic(Playlist_Music plylst_msc) {
		try {
			String plylst_id = plylst_msc.getPlylst_id();
			String msc_id = plylst_msc.getMsc_id();

			String sql = "insert into playlist_music (plylst_id, msc_id)values(?,?)";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);
			ps.setString(2, msc_id);

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add music to playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean removeMusic(Playlist_Music plylst_msc) {
		try {
			String plylst_id = plylst_msc.getPlylst_id();
			String msc_id = plylst_msc.getMsc_id();

			String sql = "delete from playlist_music where plylst_id = ? and msc_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);
			ps.setString(2, msc_id);

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add music to playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public boolean newPlaylist(Playlist plylst){
		try {
			
			String sql = "insert into playlist (plylst_id, usr_id, plylst_name, plylst_cvr, plylst_hot)values(?,?,?,?,?)";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst.getPlylst_id());
			ps.setString(2, plylst.getUsr_id());
			ps.setString(3, plylst.getPlylst_name());
			ps.setString(4, plylst.getPlylst_cvr());
			ps.setInt(5, plylst.getPlylst_hot());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("news playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}



