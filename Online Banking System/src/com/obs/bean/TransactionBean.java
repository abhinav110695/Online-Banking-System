package com.obs.bean;


import java.sql.Timestamp;

public class TransactionBean {
	
	private int accountNo;
	private int deposit;
	private int withdraw;
	private Timestamp transaction_time;
	
	

	public TransactionBean(int accountNo, int deposit, int withdraw,Timestamp transaction_time) {
		super();
		this.accountNo = accountNo;
		this.deposit = deposit;
		this.withdraw = withdraw;
		this.transaction_time=transaction_time;
	}

	public TransactionBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(int withdraw) {
		this.withdraw = withdraw;
	}
	
	
	public Timestamp getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(Timestamp transaction_time) {
		this.transaction_time = transaction_time;
	}
	
}
