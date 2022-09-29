package com.obs.mainwork;

import java.util.Scanner;

import com.obs.bean.AccountantBean;
import com.obs.bean.CustomerBean;
import com.obs.dao.AccountantDAO;
import com.obs.dao.AccountantDAOimpl;
import com.obs.exception.AccountException;
import com.obs.exception.AccountantException;
import com.obs.exception.CustomerException;

public class Main {
	static int count=10001;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
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
							+ "7. Taking care of deposit and withdrawal operations\r\n");
					
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
					
				}
			
				
			} catch (AccountantException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
