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
	private String oID;  //����ID(������ϵͳ���ɣ�
	private String oMakeOrderID;// �µ���ID�������
	private String oTakeOrderID; //  �ӵ���ID
	private String oGoodsDir;  // ��Ʒ���ڵص㣨������������������Ҫ������
	private String oGoodsCompany;// ��Ʒ���Ե�������˾��Բͨ����ͨ����ͨ��������

	private String oCoodsPhone; //  ��Ʒ�Ŀ����Ϣ���ֻ��ź���λ)
	private String oGoodsNumber;  // ��Ʒ�Ŀ����Ϣ��ȡ�����ţ�
	private String oOrderMessage;//  ��������
	private String oOrderType; //  �������ͣ��¶������Ӷ������Բ�ͬ���û��в�ͬ�Ľ��ͣ�
	private String oOrderMoney;  //  �µ����µ��˿����Լ������������߽ӵ��˵Ļ����ԣ���Ȼ����ͽ�����ƣ���
	
	private String oSendAddress;//   �ͻ���ַ
	private String oOrderPersonName; //    ��ϵ������������У�飩
	private Boolean oOrderIsTake; //   �����Ƿ񱻽���
	//�����������͵�ʱ��Ӧ���ǽӵ��˽��¸õ���ʱ��
	
	
	
}

       
   
     
