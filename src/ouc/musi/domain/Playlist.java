package ouc.musi.domain;

public class Playlist {
	
	private String plylst_id;
	private String usr_id;
	private String plylst_name;
	private String plylst_cvr;
	private int plylst_hot;
	
	public String getPlylst_id() {
		return plylst_id;
	}
	public void setPlylst_id(String plylst_id) {
		this.plylst_id = plylst_id;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getPlylst_name() {
		return plylst_name;
	}
	public void setPlylst_name(String plylst_name) {
		this.plylst_name = plylst_name;
	}
	public String getPlylst_cvr() {
		return plylst_cvr;
	}
	public void setPlylst_cvr(String plylst_cvr) {
		this.plylst_cvr = plylst_cvr;
	}
	public int getPlylst_hot() {
		return plylst_hot;
	}
	public void setPlylst_hot(int plylst_hot) {
		this.plylst_hot = plylst_hot;
	}
	

}
