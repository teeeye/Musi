package ouc.musi.domain;

public class User {

	private String usr_id;
	private String usr_name;
	private String usr_phn_nmb;
	private String usr_avtr;
	private String usr_pwd;
	private Playlist plylst;
	
	public Playlist getPlylst() {
		return plylst;
	}
	public void setPlylst(Playlist plylst) {
		this.plylst = plylst;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	public String getUsr_phn_nmb() {
		return usr_phn_nmb;
	}
	public void setUsr_phn_nmb(String usr_phn_nmb) {
		this.usr_phn_nmb = usr_phn_nmb;
	}
	public String getUsr_avtr() {
		return usr_avtr;
	}
	public void setUsr_avtr(String usr_avtr) {
		this.usr_avtr = usr_avtr;
	}
	public String getUsr_pwd() {
		return usr_pwd;
	}
	public void setUsr_pwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}
}
