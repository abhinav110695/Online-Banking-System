package com.obs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.obs.bean.CustomerBean;
import com.obs.bean.TransactionBean;
import com.obs.exception.CustomerException;
import com.obs.utility.DBUtil;

public class CustomerDAOimpl implements CustomerDAO {

	@Override
	public CustomerBean LoginCustomer(String username, String password, int accountno) throws CustomerException {
		
		CustomerBean cus = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
		PreparedStatement ps= conn.prepareStatement("select * from InfoCustomer i inner join Account a on i.cid=a.cid where cmail = ? AND cpass = ? AND cACno=?;" );			
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, accountno);
			
			ResultSet rs= ps.executeQuery();
			
			
				if(rs.next()) {
				
				int ac=rs.getInt("cACno");	
					
				String n=rs.getString("cname");
				
				int b=rs.getInt("cbal");
				
				String e= rs.getString("cmail");
				
				String p= rs.getString("cpass");
				
				String m=rs.getString("cmob");
				
				String ad=rs.getString("cadd");
				
			cus=new CustomerBean(ac,n,b,e,p,m,ad);	
				
				
			}else {
				throw new CustomerException("Invalid Username or password....Try Again!");
			}
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		
		
		return cus;
	}

	
	@Override
	public int viewBalance(int cACno) throws CustomerException {
		int b=-1;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("Select cbal from Account where cACno = ?;" );			
				
				ps.setInt(1, cACno);
				
				ResultSet rs= ps.executeQuery();
				
				if(rs.next()) {
					b=rs.getInt("cbal");
				}
			
					
			} catch (SQLException e) {
				throw new CustomerException(e.getMessage());
			}
		return b;
	}
	
	
	
	
	@Override
	public int Deposit(int cACno, int amount) throws CustomerException {
		
		int b=-1;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("update Account set cbal=cbal+? where cACno = ?;" );			
				
				ps.setInt(1, amount);
				ps.setInt(2, cACno);
				
				int rs= ps.executeUpdate();
				
			if(rs>0) {
				PreparedStatement ps2=conn.prepareStatement("insert into Transaction values(?,?,0,NOW());");
				
				ps2.setInt(1, cACno);
				ps2.setInt(2, amount);
				
				int rs2=ps2.executeUpdate();
			}else {
				throw new CustomerException("Account not found");
			}
			
				
					
			} catch (SQLException e) {
				throw new CustomerException(e.getMessage());
			}
		return b;
	}


	@Override
	public int Withdraw(int cACno, int amount) throws CustomerException {
		
		int vb=viewBalance(cACno);
		if(vb>amount) {
			try(Connection conn = DBUtil.provideConnection()) {
				
				PreparedStatement ps= conn.prepareStatement("update Account set cbal=cbal-? where cACno = ?;" );			
					
					ps.setInt(1, amount);
					ps.setInt(2, cACno);
					
					int rs= ps.executeUpdate();
					
				if(rs>0) {
					PreparedStatement ps2=conn.prepareStatement("insert into Transaction values(?,0,?,NOW());");
					
					ps2.setInt(1, cACno);
					ps2.setInt(2, amount);
					
					int rs2=ps2.executeUpdate();
				}else {
					throw new CustomerException("Account not found");
				}
				
					
						
			} catch (SQLException e) {
					throw new CustomerException(e.getMessage());
			}
			
		}else {
			throw new CustomerException("Insufficient Balance");
		}
		
		return vb+amount;
		
	}


	@Override
	public int Transfer(int cACno, int amount, int cACno2) throws CustomerException {
		
		int vb=viewBalance(cACno);
		
		if(vb>amount && checkAccount(cACno2)) {
			
			int wid=Withdraw(cACno, amount);
			int dep=Deposit(cACno2, amount);
			
			
		}else {
			throw new CustomerException("Insufficient Balance");
		}
		
		return 0;
	}
	
	private boolean checkAccount(int cACno) {
		
		try(Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from Account where cACno=?;");
			
			ps.setInt(1, cACno);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}


	@Override
	public List<TransactionBean> viewTransaction(int cACno) throws CustomerException {
		
		List<TransactionBean> li=new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps=conn.prepareStatement("select * from transaction where cACno=?;");
			ps.setInt(1, cACno);
			
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) {
				int ac=rs.getInt("cACno");
				int dep=rs.getInt("deposit");
				int wid=rs.getInt("withdraw");
				Timestamp tt=rs.getTimestamp("Transaction_time");
				
				li.add(new TransactionBean(ac,dep,wid,tt));
			}
			if(li.size()==0) {
				throw new CustomerException("No Transaction Found");
			}
			
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return li;
	}


	


	

}
