package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ouc.musi.domain.Audit_Music;
import ouc.musi.util.JdbcUtil;

public class AuditMusicDao {

	public List<Audit_Music> queryAuditMusic() {

		try {
			String sql = "select * from audit_music";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Audit_Music> result = new ArrayList<Audit_Music>();
			while (rs.next()) {
				Audit_Music adt_msc = new Audit_Music();
				adt_msc.setMsc_id(rs.getString("msc_id"));
				adt_msc.setMsc_name(rs.getString("msc_name"));
				adt_msc.setMsc_path(rs.getString("msc_pth"));
				result.add(adt_msc);
			}
			return result;
		} catch (SQLException e) {
			System.out.println("query audit music failed");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String queryMusicPath(String name){
		try {	
			String sql = "select msc_pth from audit_music where msc_name = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = null;
			rs = ps.executeQuery();
			Audit_Music adt_msc = new Audit_Music();
			while (rs.next()) {
				adt_msc.setMsc_id(rs.getString("msc_id"));
				adt_msc.setMsc_path(rs.getString("msc_pth"));
			}
			return adt_msc.getMsc_path();
		} catch (SQLException e) {
			System.out.println("query audit music path failed");
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteAuditMusic(String msc_id) {
		try {	
			String sql = "delete from audit_music where msc_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, msc_id);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("delete audit music failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
