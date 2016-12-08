package com.cemas.bean;

public class Orders {
	public String getoID() {
		return oID;
	}
	public void setoID(String oID) {
		this.oID = oID;
	}
	public String getoMakeOrderID() {
		return oMakeOrderID;
	}
	public void setoMakeOrderID(String oMakeOrderID) {
		this.oMakeOrderID = oMakeOrderID;
	}
	public String getoTakeOrderID() {
		return oTakeOrderID;
	}
	public void setoTakeOrderID(String oTakeOrderID) {
		this.oTakeOrderID = oTakeOrderID;
	}
	public String getoGoodsDir() {
		return oGoodsDir;
	}
	public void setoGoodsDir(String oGoodsDir) {
		this.oGoodsDir = oGoodsDir;
	}
	public String getoGoodsCompany() {
		return oGoodsCompany;
	}
	public void setoGoodsCompany(String oGoodsCompany) {
		this.oGoodsCompany = oGoodsCompany;
	}
	public String getoCoodsPhone() {
		return oCoodsPhone;
	}
	public void setoCoodsPhone(String oCoodsPhone) {
		this.oCoodsPhone = oCoodsPhone;
	}
	public String getoGoodsNumber() {
		return oGoodsNumber;
	}
	public void setoGoodsNumber(String oGoodsNumber) {
		this.oGoodsNumber = oGoodsNumber;
	}
	public String getoOrderMessage() {
		return oOrderMessage;
	}
	public void setoOrderMessage(String oOrderMessage) {
		this.oOrderMessage = oOrderMessage;
	}
	public String getoOrderType() {
		return oOrderType;
	}
	public void setoOrderType(String oOrderType) {
		this.oOrderType = oOrderType;
	}
	public String getoOrderMoney() {
		return oOrderMoney;
	}
	public void setoOrderMoney(String oOrderMoney) {
		this.oOrderMoney = oOrderMoney;
	}
	public String getoSendAddress() {
		return oSendAddress;
	}
	public void setoSendAddress(String oSendAddress) {
		this.oSendAddress = oSendAddress;
	}
	public String getoOrderPersonName() {
		return oOrderPersonName;
	}
	public void setoOrderPersonName(String oOrderPersonName) {
		this.oOrderPersonName = oOrderPersonName;
	}
	public boolean isoOrderIsTake() {
		return oOrderIsTake;
	}
	public void setoOrderIsTake(boolean oOrderIsTake) {
		this.oOrderIsTake = oOrderIsTake;
	}
	private String oID;  //订单ID(主键，系统生成）
	private String oMakeOrderID;// 下单人ID（外键）
	private String oTakeOrderID; //  接单人ID
	private String oGoodsDir;  // 物品所在地点（北区、南区、。。重要）。。
	private String oGoodsCompany;// 物品来自的物流公司（圆通、申通、中通。。。）

	private String oCoodsPhone; //  物品的快递信息（手机号后四位)
	private String oGoodsNumber;  // 物品的快递信息（取货单号）
	private String oOrderMessage;//  订单留言
	private String oOrderType; //  订单类型（下订单、接订单，对不同的用户有不同的解释）
	private String oOrderMoney;  //  下单金额（下单人可以自己定义金额，方便提高接单人的积极性，当然有最低金额限制）、
	
	private String oSendAddress;//   送货地址
	private String oOrderPersonName; //    联系人姓名（用于校验）
	private Boolean oOrderIsTake; //   订单是否被接下
	//订单真正成型的时候，应该是接单人接下该单的时候
	
	
	
}

       
   
     
