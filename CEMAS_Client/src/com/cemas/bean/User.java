package com.cemas.bean;

public class User {

	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuSchoolNumber() {
		return uSchoolNumber;
	}
	public void setuSchoolNumber(String uSchoolNumber) {
		this.uSchoolNumber = uSchoolNumber;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuAge() {
		return uAge;
	}
	public void setuAge(String uAge) {
		this.uAge = uAge;
	}
	public String getuGender() {
		return uGender;
	}
	public void setuGender(String uGender) {
		this.uGender = uGender;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuIdentify() {
		return uIdentify;
	}
	public void setuIdentify(String uIdentify) {
		this.uIdentify = uIdentify;
	}
	public String getuTele() {
		return uTele;
	}
	public void setuTele(String uTele) {
		this.uTele = uTele;
	}
	public String getuScore() {
		return uScore;
	}
	public void setuScore(String uScore) {
		this.uScore = uScore;
	}
	private String uID    ; //账号（ID)
	private String uPassword; //密码
	private String uName;//姓名（实名制、绑定学号）
	private String uAge; //年龄
	private String uGender; //性别
	private String uSchoolNumber;  //学号（主键）
	private String uEmail ; //常用邮箱
	private String uIdentify; //身份证号
	private String uTele  ; //电话
	private String uScore ; //绩效考核成绩

}
