package ouc.musi.domain;

public class Comment {

	private String cmnt_id;
	private String usr_id;
	private String msc_id;
	private String cmnt_ctnt;
	private String cmnt_date;
	private String rply_id;

	private int cmnt_like_num;
	private User user;

	public int getCmnt_like_num() {
		return cmnt_like_num;
	}

	public void setCmnt_like_num(int cmnt_like_num) {
		this.cmnt_like_num = cmnt_like_num;
	}

	public User getUser() {
		return user;
	}

	public String getRply_id() {
		return rply_id;
	}

	public void setRply_id(String rply_id) {
		this.rply_id = rply_id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCmnt_id() {
		return cmnt_id;
	}

	public void setCmnt_id(String cmnt_id) {
		this.cmnt_id = cmnt_id;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getMsc_id() {
		return msc_id;
	}

	public void setMsc_id(String msc_id) {
		this.msc_id = msc_id;
	}

	public String getCmnt_ctnt() {
		return cmnt_ctnt;
	}

	public void setCmnt_ctnt(String cmnt_ctnt) {
		this.cmnt_ctnt = cmnt_ctnt;
	}

	public String getCmnt_date() {
		return cmnt_date;
	}

	public void setCmnt_date(String cmnt_date) {
		this.cmnt_date = cmnt_date;
	}

}
