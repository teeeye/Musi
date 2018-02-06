package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ouc.musi.domain.Audit_Music;
import ouc.musi.util.JdbcUtil;

public class UploadDao {

	public boolean uploadMusic(Audit_Music m) {
		try {

			if (m == null) {
				System.out.println("invalid arguments Audit_Music in uploadMusic");
				return false;
			}

			String msc_id = m.getMsc_id();
			String msc_name = m.getMsc_name();
			String msc_pth = m.getMsc_path();

			if (msc_id == null || msc_name == null || msc_pth == null) {
				System.out.println("invalid arguments Audit_Music.property in uploadMusic");
				return false;
			}

			String sql = "insert into audit_music (msc_id,msc_name,msc_pth)values(?,?,?)";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, msc_id);
			ps.setString(2, msc_name);
			ps.setString(3, msc_pth);

			ps.execute();
		} catch (SQLException e) {
			System.out.println("Database error in uploadMusic");
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
