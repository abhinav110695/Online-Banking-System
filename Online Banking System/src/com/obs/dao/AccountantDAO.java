package com.obs.dao;

import com.obs.bean.AccountantBean;
import com.obs.exception.AccountantException;
import com.obs.exception.CustomerException;

public interface AccountantDAO {
	
	public AccountantBean LoginAccountant(String username, String password)throws AccountantException;
	
	public String addCustomer(String cACno, String cname, int cbal, String cmail, String cpass, String cmob, String cadd) throws CustomerException;
}
