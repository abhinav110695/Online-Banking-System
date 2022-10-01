package com.obs.dao;

import com.obs.bean.AccountantBean;
import com.obs.bean.CustomerBean;
import com.obs.exception.AccountException;
import com.obs.exception.AccountantException;
import com.obs.exception.CustomerException;

public interface AccountantDAO {
	
	public AccountantBean LoginAccountant(String username, String password)throws AccountantException;
	
	public int addCustomer(String cname,String cmail, String cpass, String cmob, String cadd) throws CustomerException;

	public String addAccount(int cbal,int cid) throws AccountException;
	
	public String updateCustomer(int cACno,String cadd) throws CustomerException;

	public  CustomerBean viewCustomer(String cACno) throws CustomerException;
	
	public int getCustomer(String cmail,String cmob) throws CustomerException;
	
	public CustomerBean viewAllCustomer() throws CustomerException;
	
	public String deleteAccount(int cACno) throws CustomerException;
	
	
}
