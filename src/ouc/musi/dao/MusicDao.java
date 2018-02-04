package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ouc.musi.domain.Music;
import ouc.musi.util.JdbcUtil;

public class MusicDao {

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

}
