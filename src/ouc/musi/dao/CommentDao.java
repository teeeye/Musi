package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ouc.musi.domain.Comment;
import ouc.musi.domain.Comment_Like;
import ouc.musi.util.JdbcUtil;

public class CommentDao {
	public boolean addComment(Comment cmnt) {
		try {
			if (cmnt.getCmnt_ctnt().length() <= 0 || cmnt.getCmnt_ctnt().length() >= 100) {
				System.out.println("invalid comment");
				return false;
			}
			String sql = "insert into comment (cmnt_id, usr_id, msc_id, cmnt_ctnt, rply_id, cmnt_date)values(?,?,?,?,?,now())";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, cmnt.getCmnt_id());
			ps.setString(2, cmnt.getUsr_id());
			ps.setString(3, cmnt.getMsc_id());
			ps.setString(4, cmnt.getCmnt_ctnt());
			ps.setString(5, cmnt.getRply_id());

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add comment failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean removeComment(String cmnt_id) {
		try {
			String sql = "delete from comment where cmnt_id = ?";
			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, cmnt_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete comment failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("null")
	public Comment[] queryComment(String msc_id) {
		CommentDao cmnt_dao = new CommentDao();
		UserDao usr_dao = new UserDao();
		Comment[] comment_array = null;
		Comment cmnt = new Comment();
		try {

			String sql = "select c.*, u.usr_avtr from comment c, user u where c.usr_id = u.usr_id and msc_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(String.valueOf(sql));
			ps.setString(1, msc_id);

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				cmnt.setCmnt_ctnt(rs.getString("cmnt_ctnt"));
				cmnt.setCmnt_date(rs.getString("cmnt_date"));
				cmnt.setCmnt_id(rs.getString("cmnt_id"));
				cmnt.setRply_id(rs.getString("rply_id"));
				cmnt.setUsr_id(rs.getString("usr_id"));
				cmnt.setMsc_id(rs.getString("msc_id"));
				cmnt.setCmnt_like_num(cmnt_dao.queryCommentNum(msc_id));
				cmnt.setUser(usr_dao.queryUserWithUserId(cmnt.getUsr_id()));
				comment_array[i] = cmnt;
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in querying comment");
			e.printStackTrace();
			return null;
		}
		return comment_array;
	}

	public int queryCommentNum(String msc_id) {
		int num = 0;
		try {

			String sql = "select count(*) from comment where msc_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(String.valueOf(sql));
			ps.setString(1, msc_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				num++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in querying the number of comment");
			e.printStackTrace();
			return -1;
		}
		return num;
	}
	
	public boolean commentLike(Comment_Like cmnt_like) {
		CommentDao cmnt_dao = new CommentDao();
		Comment_Like c = new Comment_Like(); 
		try {
			String sql = "select * from comment_like where cmnt_id = ? and usr_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, cmnt_like.getCmnt_id());
			ps.setString(2, cmnt_like.getUsr_id());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setCmnt_id(rs.getString("cmnt_id"));
				c.setUsr_id(rs.getString("usr_id"));
			}
			if(c.getCmnt_id().length() == 32 && c.getUsr_id().length() == 32){
				cmnt_dao.deleteCommentLike(cmnt_like);
			} else{
				cmnt_dao.addCommentLike(cmnt_like);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in comment_like");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteCommentLike(Comment_Like cmnt_like) {
		try {
			String sql = "delete from comment_like where cmnt_id = ? and usr_id = ?";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, cmnt_like.getCmnt_id());
			ps.setString(2, cmnt_like.getUsr_id());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete comment_like failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addCommentLike(Comment_Like cmnt_like) {
		try {
			String sql = "insert into comment_like (cmnt_id, usr_id)values(?,?)";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, cmnt_like.getCmnt_id());
			ps.setString(2, cmnt_like.getUsr_id());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add comment_like failed");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

}
