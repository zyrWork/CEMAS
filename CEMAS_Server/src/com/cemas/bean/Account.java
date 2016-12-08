package com.cemas.bean;

public class Account {
	public String getaID() {
		return aID;
	}
	public void setaID(String aID) {
		this.aID = aID;
	}
	public String getaType() {
		return aType;
	}
	public void setaType(String aType) {
		this.aType = aType;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getaDefaultType() {
		return aDefaultType;
	}
	public void setaDefaultType(String aDefaultType) {
		this.aDefaultType = aDefaultType;
	}
	private String aID;  //账户号（ID 主键）
	private String aType;//账户类型（建行、工行。。）
	private String uID; //用户ID(外键）
	private String aDefaultType; //默认账户（用于经常进行交易）

}
