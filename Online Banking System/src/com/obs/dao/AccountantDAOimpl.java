package com.obs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.obs.bean.AccountantBean;
import com.obs.exception.AccountantException;
import com.obs.exception.CustomerException;
import com.obs.utility.DBUtil;

public class AccountantDAOimpl implements AccountantDAO{

	@Override
	public AccountantBean LoginAccountant(String username, String password) throws AccountantException {
		
		AccountantBean acc = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps= conn.prepareStatement("select * from InfoAccountant where email = ? AND epass = ?");			
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			
				if(rs.next()) {
				
				String n=rs.getString("ename");
				
				String e= rs.getString("email");
				
				String p= rs.getString("epass");
				
				
			acc=new AccountantBean(n,e,p);	
				
				
			}else
				throw new AccountantException("Invalid Username or password.. ");
			
			
		} catch (SQLException e) {
			throw new AccountantException(e.getMessage());
		}
		
		
		return acc;
		
	}

	
//######################################################################################
	
	
	@Override
	public String addCustomer(String cACno, String cname, int cbal, String cmail, String cpass, String cmob,
			String cadd) throws CustomerException {
		
		String message=null;
		
		try(Connection conn= DBUtil.provideConnection()) {
		 
		 PreparedStatement ps=conn.prepareStatement("insert into InfoCustomer values(?,?,?,?,?,?,?)");
		 ps.setString(1,cACno);
	     ps.setString(2,cname);
	     ps.setInt(3,cbal);
	     ps.setString(4,cmail);
	     ps.setString(5,cpass);
	     ps.setString(6,cmob);
	     ps.setString(7,cadd);
	     
		int x=ps.executeUpdate();
		 
		 if(x > 0)
			 System.out.println("Account added sucessfully..!");
		 else
			 System.out.println("Inserted data is not correct");
		 
		}catch(SQLException e) {
			
			e.printStackTrace();
			message=e.getMessage();
		}
		
		return message;
	}
	
	
	
	
}
