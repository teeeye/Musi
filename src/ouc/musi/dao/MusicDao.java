package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ouc.musi.domain.Album;
import ouc.musi.domain.Music;
import ouc.musi.domain.Music_Like;
import ouc.musi.domain.Singer;
import ouc.musi.util.JdbcUtil;

public class MusicDao {

	private MusicLikeDao mscLike_dao = new MusicLikeDao();
	private MusicDao msc_dao = new MusicDao();
	private Music[] result_array;

	public boolean addMusic(Music m) {

		try {
			String msc_id = m.getMsc_id();
			String msc_name = m.getMsc_name();
			String msc_pth = m.getMsc_path();
			String msc_albm = m.getMsc_albm();
			int msc_lnth = m.getMsc_lnth();
			String msc_sngr = m.getMsc_sngr();
			int msc_hot = m.getMsc_hot();

			String sql = "insert into music (msc_id, msc_name, msc_pth, msc_albm, msc_lnth, msc_sngr, msc_hot)values(?,?,?,?,?,?,?)";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, msc_id);
			ps.setString(2, msc_name);
			ps.setString(3, msc_pth);
			ps.setString(4, msc_albm);
			ps.setInt(5, msc_lnth);
			ps.setString(6, msc_sngr);
			ps.setInt(7, msc_hot);

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add music failed");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean musicLike(Music_Like m) {
		try {

			Music_Like msc_like = new Music_Like();
			msc_like.setUsr_id(m.getUsr_id());
			msc_like.setMsc_id(m.getMsc_id());

			String sql = "select * from music_like where usr_id = ? and msc_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, msc_like.getUsr_id());
			ps.setString(2, msc_like.getMsc_id());

			ResultSet rs = null;
			rs = ps.executeQuery();
			Music_Like msc_like2 = new Music_Like();
			while (rs.next()) {
				msc_like2.setUsr_id(rs.getString("usr_id"));
				msc_like2.setMsc_id(rs.getString("msc_id"));
			}

			if (msc_like2.getMsc_id().length() == 32 && msc_like2.getUsr_id().length() == 32) {
				mscLike_dao.deleteMusicLike(msc_like);
				msc_dao.plusMusicHot(msc_like.getMsc_id());

			} else {
				mscLike_dao.addMusicLike(msc_like);
				msc_dao.addMusicHot(msc_like.getMsc_id());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in music_like");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean plusMusicHot(String msc_id) {
		try {

			String sql = "update music set msc_hot = msc_hot-5 where msc_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, msc_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in reducing music_hot");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addMusicHot(String msc_id) {
		try {

			String sql = "update music set msc_hot = msc_hot+5 where msc_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, msc_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in increasing music_hot");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("null")
	public Music[] keyWordQuery(String keyWord, int page) {
		try {
			int num = (page + 1) * 20;
			Music music = new Music();
			Music[] music_array = null;
			Music[] result_array = null;
			StringBuffer sql = null;
			if (page >= 0) {
				if (keyWord == null) {
					sql = new StringBuffer("select top ");
					sql.append(num);
					sql.append("* from music");
				} else {
					sql = new StringBuffer("select top ");
					sql.append(num);
					sql.append("* from music where msc_name like '%");
					sql.append(keyWord);
					sql.append("%'");
				}
			} else {
				return null;
			}

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(String.valueOf(sql));

			ResultSet rs = null;
			rs = ps.executeQuery();
			for (int i = 0; i < num; i++) {
				while (rs.next()) {
					music.setMsc_id(rs.getString("msc_id"));
					music.setMsc_albm(rs.getString("msc_albm"));
					music.setMsc_ctgy(rs.getInt("msc_ctgy"));
					music.setMsc_hot(rs.getInt("msc_hot"));
					music.setMsc_lnth(rs.getInt("msc_lnth"));
					music.setMsc_name(rs.getString("msc_name"));
					music.setMsc_path(rs.getString("msc_pth"));
					music.setMsc_sngr(rs.getString("msc_sngr"));
					music_array[i] = music;
				}
			}

			for (int i = 0; i < 20; i++)
				for (int j = page * 20; j < (page + 1) * 20; j++) {
					result_array[i] = music_array[j];
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in querying music with key word");
			e.printStackTrace();
			return null;
		}
		return result_array;
	}

	@SuppressWarnings("null")
	public Music[] singerQuery(Singer sngr, int page) {
		int num = (page + 1) * 20;
		Music music = new Music();
		Music[] music_array = null;
		Music[] result = null;
		StringBuffer sql = new StringBuffer("select top ");
		sql.append(num);
		sql.append(" music.* from music m, singer_music s_m where s_m.msc_id = m.msc_id and");
		PreparedStatement ps = null;
		if (sngr.getSngr_id() != null) {
			sql.append(" s_m.sngr_id = ?");
		} else {
			sql.append(" s_m.sngr_name = ?");
		}
		try {
			ps.setString(1, (sngr.getSngr_id() != null) ? sngr.getSngr_id() : sngr.getSngr_name());
			ps = JdbcUtil.conn.prepareStatement(String.valueOf(sql));
			ResultSet rs = null;
			rs = ps.executeQuery();

			for (int i = 0; i < num; i++) {
				while (rs.next()) {
					music.setMsc_id(rs.getString("msc_id"));
					music.setMsc_albm(rs.getString("msc_albm"));
					music.setMsc_ctgy(rs.getInt("msc_ctgy"));
					music.setMsc_hot(rs.getInt("msc_hot"));
					music.setMsc_lnth(rs.getInt("msc_lnth"));
					music.setMsc_name(rs.getString("msc_name"));
					music.setMsc_path(rs.getString("msc_pth"));
					music.setMsc_sngr(rs.getString("msc_sngr"));
					music_array[i] = music;
				}
			}
			for (int i = 0; i < 20; i++)
				for (int j = page * 20; j < (page + 1) * 20; j++) {
					result[i] = music_array[j];
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;

	}
	
	@SuppressWarnings("null")
	public Music[] albumQuery(Album albm, int page) {
		int num = (page + 1) * 20;
		Music music = new Music();
		Music[] music_array = null;
		Music[] result = null;
		StringBuffer sql = new StringBuffer("select top ");
		sql.append(num);
		sql.append(" music.* from ");
		PreparedStatement ps = null;
		if (albm.getAlbm_id() != null) {
			sql.append("music m, album a, playlist_music plylst_msc where a.albm_id = plylst_msc.plylst_id and plylst_msc.msc_id = m.msc_id and a.albm_id = ?");
		} else {
			sql.append("music m, album a where m.msc_albm = a.albm_name and a.albm_name = ?");
		}
		try {
			ps.setString(1, (albm.getAlbm_id() != null) ? albm.getAlbm_id() : albm.getAlbm_name());
			ps = JdbcUtil.conn.prepareStatement(String.valueOf(sql));
			ResultSet rs = null;
			rs = ps.executeQuery();

			for (int i = 0; i < num; i++) {
				while (rs.next()) {
					music.setMsc_id(rs.getString("msc_id"));
					music.setMsc_albm(rs.getString("msc_albm"));
					music.setMsc_ctgy(rs.getInt("msc_ctgy"));
					music.setMsc_hot(rs.getInt("msc_hot"));
					music.setMsc_lnth(rs.getInt("msc_lnth"));
					music.setMsc_name(rs.getString("msc_name"));
					music.setMsc_path(rs.getString("msc_pth"));
					music.setMsc_sngr(rs.getString("msc_sngr"));
					music_array[i] = music;
				}
			}
			for (int i = 0; i < 20; i++)
				for (int j = page * 20; j < (page + 1) * 20; j++) {
					result[i] = music_array[j];
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;

	}
	
	
}
