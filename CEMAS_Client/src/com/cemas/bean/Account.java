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
	private String aID;  //�˻��ţ�ID ������
	private String aType;//�˻����ͣ����С����С�����
	private String uID; //�û�ID(�����
	private String aDefaultType; //Ĭ���˻������ھ������н��ף�

}
