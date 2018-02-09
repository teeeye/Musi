package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import ouc.musi.domain.User;
import ouc.musi.util.JdbcUtil;

public class UserDao {

	public boolean register(User user) {
		try {

			if (user.getUsr_phn_nmb().length() != 11 || user.getUsr_pwd().length() != 32) {
				System.out.println("invalid user-phone-number or user-password");
				return false;
			}
			String sql = "insert into user (usr_id, usr_name, usr_phn_nmb, usr_avtr, usr_pwd)values(?,?,?,?,?)";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, user.getUsr_id());
			ps.setString(2, user.getUsr_name());
			ps.setString(3, user.getUsr_phn_nmb());
			ps.setString(4, user.getUsr_avtr());
			ps.setString(5, user.getUsr_pwd());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("user register failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public User login(User user) {
		try {

			String usr_name = user.getUsr_name();
			String usr_pwd = user.getUsr_pwd();
			String first = usr_name.substring(0, 1);

			Pattern pattern = Pattern.compile("[0-9]*");
			boolean is_number = pattern.matcher(first).matches();

			String sql = null;
			if (is_number) {
				sql = "select * from user where usr_phn_nmb = ? and usr_pwd = ?";
			} else {
				sql = "select * from user where usr_name = ? and usr_pwd = ?";
			}

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, usr_name);
			ps.setString(2, usr_pwd);
			User u = new User();
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user.setUsr_id(rs.getString("usr_id"));
				user.setUsr_avtr(rs.getString("usr_avtr"));
				user.setUsr_name(rs.getString("usr_name"));
				
				u = user;
			}
			if (u.getUsr_id() != null && u.getUsr_name() != null && u.getUsr_avtr() != null)
				return u;
			else
				return null;
		} catch (SQLException e) {
			System.out.println("login failed");
			e.printStackTrace();
			return null;
		}

	}
}