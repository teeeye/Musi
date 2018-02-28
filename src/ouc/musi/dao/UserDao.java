package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import ouc.musi.domain.User;
import ouc.musi.util.Configure;
import ouc.musi.util.JdbcUtil;
import ouc.musi.util.UUIDGenerator;

public class UserDao {

	public boolean register(User user) {
		try {

			String usr_id = UUIDGenerator.getUUID();
			StringBuffer usr_name = new StringBuffer("u_");
			usr_name.append(usr_id);

			user.setUsr_avtr(Configure.DEFAULT_USER_AVATAR_PATH);
			user.setUsr_id(usr_id);
			user.setUsr_name(usr_name.toString());
			
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

	public boolean login(User user) {
		try {

			String usr_name = user.getUsr_phn_nmb();
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

			while (rs.next()) {
				user.setUsr_id(rs.getString("usr_id"));
				user.setUsr_avtr(rs.getString("usr_avtr"));
				user.setUsr_name(rs.getString("usr_name"));

				u = user;
			}
			if (u.getUsr_id() != null && u.getUsr_name() != null && u.getUsr_avtr() != null)
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.out.println("login failed");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteUser(User u) {
		try {
			String user_id = u.getUsr_id();
			
			String sql = "delete from user where usr_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in delete user");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editUserInfo(User u) {
		try {
//			boolean is_unique = usr_dao.queryUserName(usr_name);
//			String first = usr_name.substring(0, 1);
//
//			Pattern pattern = Pattern.compile("[0-9]*");
//			boolean is_number = pattern.matcher(first).matches();
//
//			if (!is_unique || is_number || usr_name.length() <= 0 || usr_name.length() >= 20) {
//				System.out.println("invalid user name");
//				return false;
//			}

			String sql = "update user set usr_name = ? where usr_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, u.getUsr_name());
			ps.setString(2, u.getUsr_id());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in editing user information");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editUserAvatar(User u) {
		try {
			String sql = "update user set usr_avtr = ? where usr_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, u.getUsr_avtr());
			ps.setString(2, u.getUsr_id());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in editing user avatar");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 允许重复用户名
//	public boolean queryUserName(String usr_name) {
//		try {
//			String sql = "select * from user where usr_name = ?";
//			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
//			ps.setString(1, usr_name);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				return true;
//			} else {
//				return false;
//			}
//
//		} catch (SQLException e) {
//			System.out.println("The user name is not unique");
//			e.printStackTrace();
//			return false;
//		}
//	}

	public User queryUserWithUserId(String usr_id) {
		
		User u = new User();

		try {
			String sql = "select * from user where usr_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, usr_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.setUsr_id(rs.getString("usr_id"));
				u.setUsr_avtr(rs.getString("usr_avtr"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("query user with user id failed");
			e.printStackTrace();
			return null;
		}
		return u;
	}

}
