package com.obs.mainwork;

import java.util.List;
import java.util.Scanner;

import com.obs.bean.AccountantBean;
import com.obs.bean.CustomerBean;
import com.obs.bean.TransactionBean;
import com.obs.dao.AccountantDAO;
import com.obs.dao.AccountantDAOimpl;
import com.obs.dao.CustomerDAO;
import com.obs.dao.CustomerDAOimpl;
import com.obs.exception.AccountException;
import com.obs.exception.AccountantException;
import com.obs.exception.CustomerException;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		boolean f=true;
		

		while(f) {
			System.out.println("Welcome To Online Banking System");
			 
			System.out.println("1.Accountant 2.Customer");
			System.out.println("choose your option");
			int choice=sc.nextInt();
			
		switch(choice) {
		case 1:
			System.out.println("Accountant-LOGIN");
			System.out.println("Enter username");
			String uname=sc.next();
			System.out.println("Enter Password");
			String pass=sc.next();
			
			AccountantDAO a=new AccountantDAOimpl();
			
			try {
				AccountantBean ab=a.LoginAccountant(uname, pass);
				//System.out.println(ab);
				System.out.println("Welcome "+ab.getEname());
				
				
				boolean y=true;
				
				while(y) {
					System.out.println("1. Adding new account for customer\r\n"
							+ "2. Edit existing available account\r\n"
							+ "3. Remove the account by account number\r\n"
							+ "4. Viewing particular account details by giving account number\r\n"
							+ "5. Viewing all the account details\r\n"
							+ "6. Add new account for existing Account holder\r\n"
							+ "7. Taking care of deposit and withdrawal operations\r\n"
							+ "8. LOGOUT\r\n");
					
					int x=sc.nextInt();
					
					if(x==1) {
	
						System.out.println("Enter Name");
			 			String a2=sc.next();
			 			System.out.println("Enter Balance");
			 			int a3=sc.nextInt();
			 			System.out.println("Enter Email");
			 			String a4=sc.next();
			 			System.out.println("Enter Password");
			 			String a5=sc.next();
			 			System.out.println("Enter Mobile number");
			 			String a6=sc.next();
			 			System.out.println("Enter Address");
			 			String a7=sc.next();
			 			
			 			
			 			int s1=-1;
						try {
							s1 = a.addCustomer(a2,a4,a5,a6,a7);
							try {
								a.addAccount(a3, s1);
							} catch (AccountException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 			if(s1!=-1) {
			 				System.out.println(s1);
			 			}
					}
					
					
					if(x==2) {
						System.out.println("Enter Customer Account No. ");
			 			int u=sc.nextInt();
			 			System.out.println("Enter new Address");
			 			String u2=sc.next();
			 			try {
							String mes=a.updateCustomer(u,u2);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(x==3) {
						System.out.println("Enter Account no to Delete");
						int ac=sc.nextInt();
						String s=null;
						try {
							s=a.deleteAccount(ac);
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
						}
						if(s!=null)
							System.out.println(s);
					}
					
					if(x==4) {
						System.out.println("Enter Customer Account No.");
						String ac=sc.next();
						
						try {
							CustomerBean mes=a.viewCustomer(ac);
							
							if(mes!=null) {
								System.out.println("******************************");
								System.out.println("Account No: " + mes.getcACno());
								System.out.println("Name: " + mes.getCname());
								System.out.println("Balance: " + mes.getCbal());
								System.out.println("Email: " + mes.getCmail());
								System.out.println("Password: " + mes.getCpass());
								System.out.println("Mobile: " + mes.getCmob());
								System.out.println("Address: " + mes.getCadd());
								System.out.println("******************************");
								
							}else
								System.out.println("Account no. does not Exist");
							
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					if(x==5) {
						try {
							
							CustomerBean mes=a.viewAllCustomer();
		
							
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(x==6) {
						System.out.println("Enter email: ");
						String e=sc.next();
						System.out.println("Enter mob: ");
						String m=sc.next();
						System.out.println("Enter new Account balance");
						int b=sc.nextInt();
						
						try {
							int c=a.getCustomer(e,m);
							try {
								a.addAccount(b, c);
							} catch (AccountException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (CustomerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					if(x==7) {
						CustomerDAO cd=new CustomerDAOimpl();
						System.out.println("Enter Account No. to view Transaction Records");
						int ac=sc.nextInt();
						List<TransactionBean> li=null;
						try {
							li=cd.viewTransaction(ac); 
							System.out.println("Account No.: "+li.get(0).getAccountNo());
							li.forEach(v->{
								if(v.getDeposit()!=0)
									System.out.println("Deposit: "+v.getDeposit());
								if(v.getWithdraw()!=0)
									System.out.println("Withdraw: "+v.getWithdraw());
								System.out.println("Date and Time: "+ v.getTransaction_time());
							});
						} catch (CustomerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(x==8) {
						System.out.println("Accountant Logged out");
						y=false;
					}
					
				}
			break;
				
			} catch (AccountantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
//	******************************************************************************
			
		case 2:
			
			System.out.println("Customer-LOGIN");
			System.out.println("Enter username");
			String username=sc.next();
			System.out.println("Enter Password");
			String password=sc.next();
			System.out.println("Enter Account No");
			int acno=sc.nextInt();
			
			CustomerDAO cd=new CustomerDAOimpl();
			
			try {
				CustomerBean cusb = cd.LoginCustomer(username, password,acno);
				//System.out.println(cusb);
				System.out.println("Welcome "+cusb.getCname());
				
				boolean m=true;
				
				while(m) {
					System.out.println("1. View Balance\r\n"
							+ "2. Deposit Money\r\n"
							+ "3. Withdraw Money\r\n"
							+ "4. Transfer Money\r\n"
							+ "5. View Transaction History\r\n"
							+ "6. LOGOUT\r\n");

					
					int x=sc.nextInt();
					
					if(x==1) {
						System.out.println("Current Account Balance");
						System.out.println(cd.viewBalance(acno)); 
					}
					if(x==2) {
						System.out.println("Enter Amount to Deposit");
						int am=sc.nextInt();
						cd.Deposit(acno, am);
						System.out.println("Your Balance after deposit");
						System.out.println(cd.viewBalance(acno));
					}
					
					if(x==3) {
						System.out.println("Enter Withdrawl amount");
						int wa=sc.nextInt();
						try {
							cd.Withdraw(acno, wa);
							System.out.println("Your Balance after Withdraw");
							System.out.println(cd.viewBalance(acno));
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
					}
					
					if(x==4) {
						System.out.println("Enter amount to Transfer");
						int t=sc.nextInt();
						System.out.println("Enter Account No. to transfer amount");
						int ac=sc.nextInt();
						
						try {
							cd.Transfer(acno, t, ac);
							System.out.println("Amount transferred Succesfully...");
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
					}
					
					if(x==5) {
						List<TransactionBean> li=null;
						try {
							li= cd.viewTransaction(acno);
						}catch(CustomerException e) {
							System.out.println(e.getMessage());
						}
						
						System.out.println("Account No.: " + li.get(0).getAccountNo());
						
						li.forEach(v->{
							
							if(v.getDeposit()!=0)
								System.out.println("Deposit: "+ v.getDeposit());
							if(v.getWithdraw()!=0)
								System.out.println("Widthdraw : "+ v.getWithdraw());
							System.out.println("Date and Time: "+ v.getTransaction_time());
						});
						
					}
					
					
				}
			
				
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			
			
			
			
		}
		
		}
		
		
	}

}
