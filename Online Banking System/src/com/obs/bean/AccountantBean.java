package com.obs.bean;

public class AccountantBean {
	
	private String ename;
	private String email;
	private String epass;
	
	public AccountantBean() {
		super();
	}

	public AccountantBean(String ename, String email, String epass) {
		super();
		this.ename = ename;
		this.email = email;
		this.epass = epass;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEpass() {
		return epass;
	}

	public void setEpass(String epass) {
		this.epass = epass;
	}

	@Override
	public String toString() {
		return "AccountantBean [ename=" + ename + ", email=" + email + ", epass=" + epass + "]";
	}
	
	
	
}
