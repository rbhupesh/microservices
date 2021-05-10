package com.rb.saga.domain;

public class OrchestratorRequestDTO {

	private String userId;
	private String productId;
	private String orderId = "NAN";
	private String amount = "0.0";
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuffer()
				.append(userId.toString()+",")
				.append(productId.toString()+",")
				.append(orderId.toString()+",")
				.append(amount.toString()+",")
				.toString();
	}
}
