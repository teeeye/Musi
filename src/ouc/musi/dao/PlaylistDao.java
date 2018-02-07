package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	public boolean newPlaylist(Playlist plylst) {
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

	public boolean editPlaylist(Playlist plylst, String filePath) {
		try {
			StringBuffer sql = new StringBuffer("update playlist set ");
			String plylst_name = plylst.getPlylst_name();
			String plylst_cvr = plylst.getPlylst_cvr();
			String plylst_id = plylst.getPlylst_id();
			boolean is_null = false;

			if (plylst_name == null && plylst_cvr == null) {
				System.out.println("invalid arguments in EditPlaylist");
				return false;
			}

			if (plylst_name == null) {
				sql.append("plylst_cvr = ? ");
				is_null = true;
			} else if (plylst_cvr == null) {
				sql.append("plylst_name = ? ");
				is_null = true;
			} else {
				sql.append("plylst_name = ? and plylst_cvr = ? ");
			}
			sql.append("where plylst_id = ?");
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(String.valueOf(sql));
			if (is_null) {
				ps.setString(1, (plylst_name == null) ? filePath : plylst_name);
				ps.setString(2, plylst_id);
			} else {
				ps.setString(1, plylst_name);
				ps.setString(2, filePath);
				ps.setString(3, plylst_id);
			}

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("edit playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Playlist queryPlaylist(String plylst_id) {

		Playlist plylst = new Playlist();

		try {

			String sql = "select * from playlist where plylst_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);

			ResultSet rs = null;
			rs = ps.executeQuery();
			while (rs.next()) {
				plylst.setPlylst_cvr(rs.getString("plylst_cvr"));
				plylst.setPlylst_hot(rs.getInt("plylst_id"));
				plylst.setPlylst_name(rs.getString("plylst_name"));
				plylst.setUsr_id(rs.getString("usr_id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in querying playlist");
			e.printStackTrace();
			return null;
		}
		return plylst;
	}

}
