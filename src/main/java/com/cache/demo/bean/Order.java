package com.cache.demo.bean;

public class Order {

	private String orderNo;
	private String name;
	private String cardType;
	private int accessCount;

	public Order(){

	}

	public Order(String orderNo, String name, String cardType){
		this.orderNo = orderNo;
		this.name = name;
		this.cardType = cardType;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

//	public int getPrice() {
//		return price;
//	}
//
//	public void setPrice(int price) {
//		this.price = price;
//	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
}
