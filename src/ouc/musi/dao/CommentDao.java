package ouc.musi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ouc.musi.domain.Comment;
import ouc.musi.util.JdbcUtil;

public class CommentDao {
	public boolean addComment(Comment cmnt) {
		try {
			if(cmnt.getCmnt_ctnt().length() <= 0 || cmnt.getCmnt_ctnt().length() >= 100){
				System.out.println("invalid comment");
				return false;
			}
			String sql = "insert into comment (cmnt_id, usr_id, msc_id, cmnt_ctnt, reply_id, cmnt_date)values(?,?,?,?,?,now())";

			PreparedStatement ps = JdbcUtil.conn.prepareStatement(sql);
			ps.setString(1, cmnt.getCmnt_id());
			ps.setString(2, cmnt.getUsr_id());
			ps.setString(3, cmnt.getMsc_id());
			ps.setString(4, cmnt.getCmnt_ctnt());
			ps.setString(5, cmnt.getReply_id());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add comment failed");
			e.printStackTrace();
			return false;
		}
		return true;

	}
}
