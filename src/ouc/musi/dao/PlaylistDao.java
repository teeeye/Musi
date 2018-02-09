package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ouc.musi.domain.Playlist;
import ouc.musi.domain.Playlist_Music;
import ouc.musi.domain.Shared_Playlist;
import ouc.musi.domain.User_Playlist;
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
			System.out.println("delete music from playlist failed");
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

	public Playlist queryWithPlaylistId(String plylst_id) {

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
			System.out.println("failed in querying playlist with playlist id");
			e.printStackTrace();
			return null;
		}
		return plylst;
	}

	public Playlist queryWithUserId(String usr_id) {

		Playlist plylst = new Playlist();

		try {

			String sql = "select * from playlist where usr_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, usr_id);

			ResultSet rs = null;
			rs = ps.executeQuery();
			while (rs.next()) {
				plylst.setPlylst_cvr(rs.getString("plylst_cvr"));
				plylst.setPlylst_hot(rs.getInt("plylst_id"));
				plylst.setPlylst_name(rs.getString("plylst_name"));
				plylst.setPlylst_id(rs.getString("plylst_id"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in querying playlist with user id");
			e.printStackTrace();
			return null;
		}
		return plylst;
	}

	public boolean removePlaylist(String plylst_id) {
		try {

			PlaylistDao plylst_dao = new PlaylistDao();
			String sql = "delete from playlist where plylst_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);

			ps.execute();

			plylst_dao.removeMusicInPlaylist(plylst_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeMusicInPlaylist(String plylst_id) {
		try {

			String sql = "delete from playlist_music where plylst_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete music in playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean sharePlaylist(String plylst_id) {
		PlaylistDao plylst_dao = new PlaylistDao();
		try {

			String sql = "select * from shared_playlist where plylst_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);

			ResultSet rs = null;
			rs = ps.executeQuery();
			Shared_Playlist plylst = new Shared_Playlist();
			while (rs.next()) {
				plylst.setPlylst_id(rs.getString("plylst_id"));
				plylst.setPlylst_hot(rs.getInt("plyst_hot"));
			}
			if (plylst.getPlylst_id().length() == 32 && plylst.getPlylst_hot() >= 0) {
				plylst_dao.deleteSharedPlaylist(plylst_id);
			} else {
				plylst_dao.addSharedPlaylist(plylst_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in shared_playlist");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteSharedPlaylist(String plylst_id) {
		try {
			String sql = "delete from shared_playlist where plylst_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete shared_playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addSharedPlaylist(String plylst_id) {
		try {
			String sql = "insert into shared_playlist (plylst_hot)values(?) where plylst_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setString(2, plylst_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add shared_playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("null")
	public Playlist[] queryPlaylist(int page) {
		Playlist[] rslt_array = null;
		Playlist[] plylst_array = null;
		try {
			int num = (page + 1) * 10;
			Playlist plylst = new Playlist();
			StringBuffer sql = new StringBuffer(
					"select p.* from shared_playlist s_p, playlist p where s_p.plylst_id = p.plylst_id limit ");
			sql.append(num);

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(String.valueOf(sql));

			ResultSet rs = ps.executeQuery();
			for (int i = 0; i < num; i++) {
				while (rs.next()) {
					plylst.setPlylst_id(rs.getString("plylst_id"));
					plylst.setPlylst_name(rs.getString("plylst_name"));
					plylst.setPlylst_cvr(rs.getString("plylst_cvr"));
					plylst.setUsr_id(rs.getString("usr_id"));
					plylst.setPlylst_hot(rs.getInt("plylst_hot"));
					plylst_array[i] = plylst;
				}
			}
			if (page == 0) {
				return plylst_array;
			} else {
				for (int i = 0; i < 10; i++)
					for (int j = page * 10; j < (page + 1) * 10; j++) {
						rslt_array[i] = plylst_array[j];
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in querying shared playlist");
			e.printStackTrace();
			return null;
		}
		return rslt_array;
	}

	public boolean collectPlaylist(User_Playlist usr_plylst) {
		try {
			PlaylistDao plylst_dao = new PlaylistDao();
			User_Playlist u = new User_Playlist();

			String sql = "select * from user_playlist where usr_id = ? and plylst_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, usr_plylst.getUsr_id());
			ps.setString(2, usr_plylst.getPlylst_id());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.setUsr_id(rs.getString("usr_id"));
				u.setPlylst_id(rs.getString("plylst_id"));
			}

			if (u.getUsr_id().length() == 32 && u.getPlylst_id().length() == 32) {
				plylst_dao.deleteUserPlaylist(usr_plylst);
				plylst_dao.plusPlaylistHot(usr_plylst.getPlylst_id());

			} else {
				plylst_dao.addUserPlaylist(usr_plylst);
				plylst_dao.addPlaylistHot(usr_plylst.getPlylst_id());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in user_playlist");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addUserPlaylist(User_Playlist usr_plylst) {
		try {
			String sql = "insert into user_playlist (usr_id, plylst_id)values(?,?)";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, usr_plylst.getUsr_id());
			ps.setString(2, usr_plylst.getPlylst_id());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add user_playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteUserPlaylist(User_Playlist usr_plylst) {
		try {
			String sql = "delete from user_playlist where usr_id = ? and plylst_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, usr_plylst.getUsr_id());
			ps.setString(2, usr_plylst.getPlylst_id());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete user_playlist failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean plusPlaylistHot(String plylst_id) {
		try {

			String sql = "update shared_playlist set plylst_hot = plylst_hot-1 where plylst_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in reducing plylst_hot");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addPlaylistHot(String plylst_id) {
		try {

			String sql = "update shared_playlist set plylst_hot = plylst_hot+1 where plylst_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, plylst_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in increasing plylst_hot");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
